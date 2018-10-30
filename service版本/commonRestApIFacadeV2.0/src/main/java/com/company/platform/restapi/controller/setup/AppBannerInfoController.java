package com.company.platform.restapi.controller.setup;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.setup.AppBannerInfoReq;
import com.company.platform.restapi.model.setup.AppBannerInfoResp;
import com.company.platform.restapi.service.setup.IAppBannerInfoService;



/** 
* @ClassName: AppBannerInfoController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 董建 
* @date 2017年12月13日 上午10:19:27 
*  
*/
@RestController
@RequestMapping("/openCommonApi")
public class AppBannerInfoController extends BaseController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(AppBannerInfoController.class);

    @Resource
    IAppBannerInfoService appBannerInfoService;

    @RequestMapping(path = "/getBannerInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName="获取banner",modelType=RequestAccessConstants.SEARCH,desc="获取banner")
    public BaseHttpParamsResp getBannerInfoList(@RequestBody @Validated AppBannerInfoReq appBannerInfoReq)
            throws Exception {
        BaseHttpParamsResp baseHttpParamsResp = null;
        List<AppBannerInfoResp> infoResps = appBannerInfoService.getAppBannerInfo(appBannerInfoReq.getAppName());
        if (infoResps == null) {
            baseHttpParamsResp = BaseHttpParamsResp.requestError("获取banner信息失败");
        } else {
        	String url = request.getRequestURL().toString();
    		String uri = request.getRequestURI().toString();
    		String requestUri = url.replace(uri, "");
    		
        	for (AppBannerInfoResp banner : infoResps) {
        		banner.setPath(requestUri+"/open/loadPicture?picturePath="+banner.getPath());
			}
            baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("获取banner信息成功", infoResps);
        }
        baseHttpParamsResp.createSign();
        return baseHttpParamsResp;
    }

}
