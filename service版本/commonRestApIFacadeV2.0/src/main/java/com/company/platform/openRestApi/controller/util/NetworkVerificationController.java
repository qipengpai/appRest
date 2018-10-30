/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.controller.util
 *
 *    Filename:    NetworkVerificationController.java
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
package com.company.platform.openRestApi.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.network.ProductUpdateInfoReq;
import com.company.platform.restapi.validated.network.LoanProductUpdateTimeValidate;

/** 
* @ClassName: NetworkVerificationController 
* @Description: TODO(网络检测专用工具类) 
* @author zhengjn 
* @date 2018年3月15日 下午2:47:23 
*  
*/
@RequestMapping("/openApi")
@RestController
public class NetworkVerificationController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(NetworkVerificationController.class);

	@Resource
	private LoanProductUpdateTimeValidate loanProductUpdateTimeValidate;
	
	/** 
	* @Title: getCustomMessage 
	* @Description: TODO(网络检测方法) 
	* @param @param baseHttpParamsAppReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/networkVerification", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取网络请求", modelType = RequestAccessConstants.SEARCH, desc = "获取网络请求,并且判断产品更新是否同步")
	public BaseHttpParamsResp getCustomMessage(@RequestBody @Validated ProductUpdateInfoReq productUpdateInfoReq) 
			throws Exception {
		
		logger.info("网络认证开始");
		logger.info("验证产品更新时间开始");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(0);
		String InitializationTime = dateFormat.format(date);
		if(!productUpdateInfoReq.getUpdateTime().equals(InitializationTime)){
			loanProductUpdateTimeValidate.getProductUpdateTimeValidate(productUpdateInfoReq.getUpdateTime());
		}
		BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("网络验证通过", "");
		logger.info("验证产品更新时间结束");
		baseHttpParamsResp.createSign();
		logger.info("网络认证结束");
		return baseHttpParamsResp;
	}
}
