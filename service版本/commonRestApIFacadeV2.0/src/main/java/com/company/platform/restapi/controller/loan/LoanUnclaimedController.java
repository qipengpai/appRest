/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.loan
 *
 *    Filename:    LoanHandledController.java
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
package com.company.platform.restapi.controller.loan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.activiti.WaitReceiveTask;
import com.company.platform.restapi.model.loan.unclaimed.ClaimTaskReq;
import com.company.platform.restapi.model.loan.unclaimed.FlowTaskInfo;
import com.company.platform.restapi.model.loan.unclaimed.ShowCommnetReq;
import com.company.platform.restapi.service.activiti.ActivitiService;
import com.company.platform.restapi.service.loan.ILoanDealService;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: LoanUnclaimedController 
* @Description: TODO(待领取) 
* @author wangxue 
* @date 2018年7月19日 上午9:20:44 
*  
*/
@RestController
@RequestMapping("appApi")
public class LoanUnclaimedController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanDealtController.class);

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private ILoanDealService loanDealService;

	/** 
	* @Title: searchUnclaimedInfo 
	* @Description: TODO(待领取任务列表) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsPageResp    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/searchUnclaimedInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "查询待领取信息列表", modelType = RequestAccessConstants.SEARCH, desc = "通过登录人ID获得登录人待领取列表")
	public BaseHttpParamsPageResp searchUnclaimedInfo(@RequestBody @Validated BaseHttpParamsPageAppReq req)
			throws Exception {
		logger.debug("开始查询待领取信息列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获得当前用户借贷待领取列表响应信息
		Map<String, Object> map = activitiService.getPerLeisureTask(user.getId(), user.getRealName(),
				Integer.valueOf(req.getPageSize()) * Integer.valueOf(req.getPageNum() - 1), req.getPageSize());
		BaseHttpParamsPageResp resp = new BaseHttpParamsPageResp();
		resp.setResultSet((List<WaitReceiveTask>) map.get("list"));
		resp.setPageNum(req.getPageNum());
		resp.setPageSize(req.getPageSize());
		resp.setPages((int) Math.ceil(Double.valueOf(map.get("count").toString()) / req.getPageSize()));
		resp.setTotal(Long.valueOf(map.get("count").toString()));
		resp.setIsFirstPage(req.getPageNum() == 1);
		resp.setIsLastPage(req.getPageNum() == Integer.valueOf(resp.getPages()));
		resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
		resp.setResponseMessage(ResponseConstants.REQUEST_SUCCESS.getRetMsg());
		resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
		resp.createSign();
		return resp;
	}

	/** 
	* @Title: claimTask 
	* @Description: TODO(领取任务) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/claimTask", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "领取任务", modelType = RequestAccessConstants.UPDATE, desc = "领取任务")
	public BaseHttpParamsResp claimTask(@RequestBody @Validated ClaimTaskReq req) throws Exception {
		logger.info("开始领取任务");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 领取任务
		activitiService.claim(req.getTaskId(), req.getTaskKey(), user.getId(), user.getRealName());
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg());
		baseHttpParamsResp.createSign();
		logger.info("结束领取任务");
		return baseHttpParamsResp;
	}

	@RequestMapping(path = "/showCommnet", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "查看轨迹", modelType = RequestAccessConstants.SEARCH, desc = "获取还款计划")
	public BaseHttpParamsResp showCommnet(@RequestBody @Validated ShowCommnetReq req) throws Exception {
		logger.info("查看轨迹开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		Map<String, String> taskKeys = new HashMap<String, String>();
		taskKeys.put("loanApp", "headFirstCheck");
		taskKeys.put("applyCheck", "headFirstCheck");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FlowTaskInfo> resp = loanDealService.showCommnet(req.getProcessType(), req.getProcessInstanceId(),
				taskKeys, user.getId(), req.getTaskId());
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		logger.info("查看轨迹结束");
		return baseHttpParamsResp;
	}
}
