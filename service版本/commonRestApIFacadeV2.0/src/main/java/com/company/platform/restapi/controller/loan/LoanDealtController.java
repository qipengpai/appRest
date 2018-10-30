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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.activiti.SubmitForApproval;
import com.company.platform.restapi.model.activiti.WaitHandleTask;
import com.company.platform.restapi.model.custom.CustomerInfoReq;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.enterprise.CorpCustomerInfoReq;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.loan.LoanApplyInfoReq;
import com.company.platform.restapi.model.loan.LoanApplyInfoResp;
import com.company.platform.restapi.model.loan.deal.FunctionBtnInfo;
import com.company.platform.restapi.model.loan.deal.LoanApplyAuditingForEnterpriseResp;
import com.company.platform.restapi.model.loan.deal.LoanApplyAuditingForPersonalResp;
import com.company.platform.restapi.model.loan.deal.LoanApplyAuditingReq;
import com.company.platform.restapi.model.loan.deal.LoanApplyAuditingResp;
import com.company.platform.restapi.model.loan.deal.LoanRepaymentTrailReq;
import com.company.platform.restapi.model.loan.deal.LoanRepaymentTrialResp;
import com.company.platform.restapi.model.loan.deal.LoanTaskInfoReq;
import com.company.platform.restapi.model.loan.deal.LoanTaskInfoResp;
import com.company.platform.restapi.model.loan.deal.SubmitApprovalReq;
import com.company.platform.restapi.model.loan.unclaimed.FlowTaskInfo;
import com.company.platform.restapi.service.activiti.ActivitiService;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.restapi.service.loan.ILoanDealService;
import com.company.platform.restapi.service.loan.ILoanTempService;
import com.company.platform.restapi.validated.loan.LoanDealValidate;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: LoanHandledController 
* @Description: TODO(待办-审核相关功能集合) 
* @author wangxue 
* @date 2018年7月13日 上午10:46:50 
*  
*/
@RestController
@RequestMapping("appApi")
public class LoanDealtController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanDealtController.class);

	@Autowired
	private ILoanDealService loanDealService;

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private LoanDealValidate loanDealValidate;

	@Autowired
	private ILoanTempService loanTempService;

	@Autowired
	private ICustomerInfoService customerInfoService;

	/** 
	* @Title: getRepaymentTrial 
	* @Description: TODO(还款试算) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getRepaymentTrial", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "还款试算", modelType = RequestAccessConstants.SEARCH, desc = "获取还款计划")
	public BaseHttpParamsResp getRepaymentTrial(@RequestBody @Validated LoanRepaymentTrailReq req) throws Exception {
		logger.info("还款试算开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 数据校验
		loanDealValidate.loanRepaymentTrailValidate(req);
		// 获取贷款申请费用信息
		LoanRepaymentTrialResp resp = loanDealService.getRepaymentTrial(req);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		logger.info("还款试算结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: searchDealtInfo 
	* @Description: TODO(查询待办信息列表) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsPageResp    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/searchDealtInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "查询待办信息列表", modelType = RequestAccessConstants.SEARCH, desc = "通过登录人ID获得登录人待办列表")
	public BaseHttpParamsPageResp searchDealtInfo(@RequestBody @Validated BaseHttpParamsPageAppReq req)
			throws Exception {
		logger.debug("开始查询待办信息列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获得当前用户借贷待办任务列表响应信息(此处不做分页，需调整)
		Map<String, Object> map = activitiService.getPersonalTask(user.getId(), user.getRealName(), 0, null);
		BaseHttpParamsPageResp resp = new BaseHttpParamsPageResp();
		// 待办列表详细信息组合
		List<WaitHandleTask> list = loanDealService.getDealInfoList((List<WaitHandleTask>) map.get("list"));
		resp.setResultSet(list);
		resp.setPageNum(1);
		resp.setPageSize(req.getPageSize());
		resp.setPages(1);
		resp.setTotal(Long.valueOf(map.get("count").toString()));
		resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
		resp.setResponseMessage(ResponseConstants.REQUEST_SUCCESS.getRetMsg());
		resp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
		resp.createSign();
		return resp;
	}

	/** 
	* @Title: completeTask 
	* @Description: TODO(保存待办任务审核信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/completeTask", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "保存待办任务审核信息", modelType = RequestAccessConstants.SEARCH, desc = "保存待办任务审核信息")
	public BaseHttpParamsResp completeTask(@RequestBody @Validated SubmitApprovalReq req) throws Exception {
		logger.info("保存待办任务审核信息开始");
		loanDealValidate.submitApprovalValidate(req);
		BaseHttpParamsResp baseHttpParamsResp = null;
		SubmitForApproval submit = new SubmitForApproval();
		BeanUtils.copyProperties(req, submit);
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		submit.setAuditUserId(user.getId());
		submit.setAuditUserName(user.getRealName());
		submit.setVariables(JSONObject.parseObject(req.getLocalVariables()));
		// 提交审核信息
		Map<String, String> map = activitiService.submittedForApproval(submit);
		// 流程节点完成处理
		loanDealService.finishTask(map.get("code"), req.getProcessInstanceId());
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg());
		baseHttpParamsResp.createSign();
		logger.info("保存待办任务审核信息结束");
		return baseHttpParamsResp;
	}

	@RequestMapping(path = "/searchDealtInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取待办信息详情", modelType = RequestAccessConstants.SEARCH, desc = "通过借贷申请id获取待办信息详情")
	public BaseHttpParamsResp searchDealtInfoById(@RequestBody @Validated LoanApplyAuditingReq req) throws Exception {

		logger.debug("开始获取待办信息详情");
		BaseHttpParamsResp baseHttpParamsResp = null;

		// 借贷申请在其他终端被修改，流程相关信息可能会改变
		Map<String, String> map = loanDealService.getLoanProcessTaskInfo(req.getLoanProductApplyId());
		if (map != null && !map.isEmpty()) {// 判断流程节点是否一致
			if (!req.getTaskId().equals(map.get("taskId"))) {
				logger.error("借贷申请已进入下一节点");
				throw new BusinessException(ResponseConstants.LOAN_APPLY_PROCESS_TASK_ERROR.getRetCode(),
						ResponseConstants.LOAN_APPLY_PROCESS_TASK_ERROR.getRetMsg());
			}
		} else {
			logger.error("借贷申请不在待办列表中");
			throw new BusinessException(ResponseConstants.LOAN_APPLY_NOT_IN_DEAL.getRetCode(),
					ResponseConstants.LOAN_APPLY_NOT_IN_DEAL.getRetMsg());
		}

		// 获取待办信息
		LoanApplyInfoReq loanReq = new LoanApplyInfoReq();
		BeanUtils.copyProperties(req, loanReq);
		LoanApplyInfoResp loanResp = loanTempService.searchTempInfoById(loanReq, "auditing","");

		LoanApplyAuditingResp auditResp = new LoanApplyAuditingResp();
		BeanUtils.copyProperties(loanResp, auditResp);

		// 返回html5页面url
		auditResp.setHtmlUrl(loanDealService.judgeLoanProductRelation(req.getLoanProductId(),
				req.getLoanProductApplyId(), req.getTaskKey()));

		String customerType = auditResp.getCustomerType();
		CustomerPublicInfo customerInfo = auditResp.getCustomerPublicInfo();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aCusUpdateTime = req.getUpdateTimeCustomer();
		Date acTime = f.parse(aCusUpdateTime);
		String sCusUpdateTime = customerInfo.getUpdateTime();
		Date scTime = f.parse(sCusUpdateTime);
		if (acTime.getTime() < scTime.getTime()) {
			auditResp.setUpdateTimeCustomer(sCusUpdateTime);
			if ("0".equals(customerType)) {// 个人
				CustomerInfoReq customerInfoReq = new CustomerInfoReq();
				customerInfoReq.setCredentialType(customerInfo.getCredentialType());
				customerInfoReq.setCredentialNo(customerInfo.getCredentialNo());
				customerInfoReq.setCustomerOrgId(customerInfo.getOrgId());
				CustomerInfoResp cus = customerInfoService.getCustomerInfo(customerInfoReq);
				LoanApplyAuditingForPersonalResp laip = new LoanApplyAuditingForPersonalResp();
				BeanUtils.copyProperties(auditResp, laip);
				BeanUtils.copyProperties(cus, laip);
				baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
						ResponseConstants.SUCCESS.getRetMsg(), laip);
			} else {// 企业
				CorpCustomerInfoReq customerInfoReq = new CorpCustomerInfoReq();
				customerInfoReq.setCredentialType(customerInfo.getCredentialType());
				customerInfoReq.setCredentialNo(customerInfo.getCredentialNo());
				customerInfoReq.setCustomerOrgId(customerInfo.getOrgId());
				EnterpriseInfoResp cus = customerInfoService.getEnterpriseInfo(customerInfoReq);
				LoanApplyAuditingForEnterpriseResp laie = new LoanApplyAuditingForEnterpriseResp();
				BeanUtils.copyProperties(auditResp, laie);
				BeanUtils.copyProperties(cus, laie);
				baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
						ResponseConstants.SUCCESS.getRetMsg(), laie);
			}
		} else {
			auditResp.setCustomerPublicInfo(null);
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
					ResponseConstants.SUCCESS.getRetMsg(), auditResp);
		}
		baseHttpParamsResp.createSign();

		logger.debug("结束获取待办信息详情");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: getTaskInfo 
	* @Description: TODO(获取审核信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getTaskInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取审核信息", modelType = RequestAccessConstants.SEARCH, desc = "获取审核信息")
	public BaseHttpParamsResp getTaskInfo(@RequestBody @Validated LoanTaskInfoReq req) throws Exception {
		logger.info("获取审核信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取流程节点信息（功能按钮信息+人员信息）
		List<FunctionBtnInfo> funs = loanDealService.getTaskInfo(req);
		Map<String, String> taskKeys = new HashMap<String, String>();
		taskKeys.put("loanApp", "headFirstCheck");
		taskKeys.put("applyCheck", "headFirstCheck");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FlowTaskInfo> flows = loanDealService.showCommnet(req.getProcessType(), req.getProcessInstanceId(),
				taskKeys, user.getId(), req.getTaskId());
		LoanTaskInfoResp resp = new LoanTaskInfoResp();
		resp.setFunctions(funs);
		resp.setFlowTaskInfo(flows);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		logger.info("获取审核信息结束");
		return baseHttpParamsResp;
	}
}
