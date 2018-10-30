/******************************************************************
 *
 *    Package:     com.company.platform.open.controller
 *
 *    Filename:    appDownLoadController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.open.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.util.DownLoadFile;
import com.company.platform.base.util.OrderID;
import com.company.platform.restapi.model.setup.AppVersion;
import com.company.platform.restapi.model.setup.AppVersionDetection;
import com.company.platform.restapi.service.setup.IMobileSetUpService;

/** 
* @ClassName: appDownLoadController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年1月17日 下午2:34:32 
*  
*/
@Controller
@RequestMapping("/open")
public class AppDownLoadController extends BaseController {

	@Resource
	IMobileSetUpService mobileSetUpService;
	// 日志
	private final Logger logger = LoggerFactory.getLogger(AppDownLoadController.class);

	@Value("${file.app_dir}")
	private String app_dir;

	@Value("${file.root_dir}")
	private String root_dir;

	/** 
	* @Title: loanApk 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param model
	* @param @param path
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "loadApk")
	public void loanApk(ModelMap model, String apkPath) throws IOException {
		logger.info("下载apk开始");
		DownLoadFile.readFile(root_dir + apkPath, response, OrderID.getPK() + apkPath.substring(apkPath.length() - 4));
		logger.info("下载apk结束");
	}

	/**
	 * @throws BusinessException  
	* @Title: appVersion 
	* @Description: TODO(下载app最新版本) 
	* @param @param model
	* @param @param appType
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "appVer")
	public void appVersion(ModelMap model, String appType) throws IOException, BusinessException {
		AppVersionDetection appVersionDetection = new AppVersionDetection();
		appVersionDetection.setAppType(appType);// App类型 0：Android 1：IOS
		appVersionDetection.setAppName("04");// 蚂蚁金盾
		AppVersion appVersionObj = mobileSetUpService.appVersionDetection(appVersionDetection);
		if (appVersionObj != null) {
			this.loanApk(model, appVersionObj.getUrl());
		} else {
			throw new BusinessException(ResponseConstants.APP_VERSION_NOT_USED.getRetCode(),
					ResponseConstants.APP_VERSION_NOT_USED.getRetMsg());
		}
		
	}
}
