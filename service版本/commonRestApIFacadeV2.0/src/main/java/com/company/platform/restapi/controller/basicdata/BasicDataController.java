/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.basicdata
 *
 *    Filename:    BasicDataController.java
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
package com.company.platform.restapi.controller.basicdata;

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
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.basicdata.BasicDataInfoReq;
import com.company.platform.restapi.model.basicdata.BasicDataInfoResp;
import com.company.platform.restapi.service.basicdata.IBasucDataInfoService;

/** 
* @ClassName: BasicDataController 
* @Description: TODO(基础数据更新服务) 
* @author liang 
* @date 2018年1月18日 上午9:20:34 
*  
*/
@RestController
@RequestMapping("appCommonApi")
public class BasicDataController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(BasicDataController.class);

	@Resource
	private IBasucDataInfoService basucDataInfoService;

	/** 
	* @Title: updateBasicData 
	* @Description: TODO(更新基础数据) 
	* @param @param customerInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/updateBasicData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "更新基础数据", modelType = RequestAccessConstants.SEARCH, desc = "通过更新时间获取需要更新的基础数据信息")
	public BaseHttpParamsResp updateBasicData(@RequestBody @Validated BasicDataInfoReq basicDataInfoReq)
			throws Exception {
		logger.info("更新基础数据信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取数据更新内容
		BasicDataInfoResp basicDataInfoResp = basucDataInfoService.updateBasicData(basicDataInfoReq);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), basicDataInfoResp);
		baseHttpParamsResp.createSign();
		logger.info("更新基础数据信息结束");
		return baseHttpParamsResp;
	}
}
