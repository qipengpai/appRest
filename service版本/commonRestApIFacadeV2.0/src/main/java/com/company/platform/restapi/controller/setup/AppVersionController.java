package com.company.platform.restapi.controller.setup;

import java.io.File;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.setup.AppVersion;
import com.company.platform.restapi.model.setup.AppVersionDetection;
import com.company.platform.restapi.model.setup.AppVersionResp;
import com.company.platform.restapi.service.setup.IMobileSetUpService;

/** 
* @ClassName: AppVersionController 
* @Description: TODO(版本检测) 
* @author 董建 
* @date 2017年12月12日 下午6:34:30 
*  
*/
@RestController
@RequestMapping("/openCommonApi")
public class AppVersionController extends BaseController {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(AppVersionController.class);

	@Resource
	IMobileSetUpService mobileSetUpService;

	@Resource
	SessionRegistry sessionRegistry;

	@Value("${file.root_dir}")
	private String root_dir;

	/**
	 * 
	* @Title: appVersionDetection 
	* @Description: TODO(版本检测) 
	* @param @param appVersionDetection
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/appVersionDetection", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "设置", modelType = RequestAccessConstants.SEARCH, desc = "版本检测")
	public BaseHttpParamsResp appVersionDetection(@RequestBody @Validated AppVersionDetection appVersionDetection)
			throws Exception {
		logger.info("版本检测开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		AppVersion appVersionObj = mobileSetUpService.appVersionDetection(appVersionDetection);
		AppVersionResp appVersionResp = new AppVersionResp();
		if (appVersionObj == null) {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(
					ResponseConstants.APP_VERSION_DETECTION_FAIL.getRetCode(),
					ResponseConstants.APP_VERSION_DETECTION_FAIL.getRetMsg(), "");
		} else {
			if (appVersionDetection.getAppVersion().equals(appVersionObj.getVersion())) {//版本相同 (0：当前是最新版本 1：有更新)
				String url = request.getRequestURL().toString();
				String uri = request.getRequestURI().toString();
				String requestUri = url.replace(uri, "");
				appVersionResp.setStatus("0");
				appVersionResp.setVersion(appVersionObj.getVersion());
				appVersionResp.setUrl(requestUri + "/open/loadApk?apkPath=" + appVersionObj.getUrl());
				appVersionResp.setSize(appVersionObj.getSize());
				baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(
						ResponseConstants.APP_VERSION_DETECTION_SUCCESS.getRetCode(),
						ResponseConstants.APP_VERSION_DETECTION_SUCCESS.getRetMsg(), appVersionResp);
			} else {

				String url = request.getRequestURL().toString();
				String uri = request.getRequestURI().toString();
				String requestUri = url.replace(uri, "");

				appVersionResp.setStatus("1");
				appVersionResp.setVersion(appVersionObj.getVersion());
				appVersionResp.setUrl(requestUri + "/open/loadApk?apkPath=" + appVersionObj.getUrl());
				appVersionResp.setSize(appVersionObj.getSize());

				File file = new File(root_dir + appVersionObj.getUrl());
				if (!file.exists()) {//文件不存在
					baseHttpParamsResp = BaseHttpParamsResp.requestError(
							ResponseConstants.APP_VERSION_FILE_NOTFOUND.getRetCode(),
							ResponseConstants.APP_VERSION_FILE_NOTFOUND.getRetMsg());
				} else {
					baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(
							ResponseConstants.APP_VERSION_DETECTION_SUCCESS.getRetCode(),
							ResponseConstants.APP_VERSION_DETECTION_SUCCESS.getRetMsg(), appVersionResp);
				}

			}

		}

		baseHttpParamsResp.createSign();
		logger.info("版本检测结束");
		return baseHttpParamsResp;
	}

}
