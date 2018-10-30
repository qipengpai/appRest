/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.workbench
 *
 *    Filename:    WorkbenchController.java
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
package com.company.platform.restapi.controller.workbench;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.workbench.WorkBenchResp;
import com.company.platform.restapi.service.workbench.IWorkBenchService;

/** 
* @ClassName: WorkbenchController 
* @Description: TODO(工作台相关功能集合) 
* @author zhengjn 
* @date 2018年1月18日 上午9:08:05 
*  
*/
@RestController
@RequestMapping("appApi")
public class WorkbenchController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(WorkbenchController.class);

	@Resource
	private IWorkBenchService workBenchService;

	/**
	 * 
	* @Title: getWorkBenchCount 
	* @Description: TODO(获取主页任务数量) 
	* @param @param basicDataInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/searchWorkbenchStatistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取主页任务数量", modelType = RequestAccessConstants.SEARCH, desc = "根据用户id和用户名获取首页任务数量")
	public BaseHttpParamsResp getWorkBenchCount(BaseHttpParamsAppReq baseHttpParamsAppReq) throws Exception {
		logger.info("获取主页任务数量开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取数据更新内容
		WorkBenchResp workBenchResp = workBenchService.getWorkBenchCounts();
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), workBenchResp);
		baseHttpParamsResp.createSign();
		logger.info("获取主页任务数量结束");
		return baseHttpParamsResp;
	}
}
