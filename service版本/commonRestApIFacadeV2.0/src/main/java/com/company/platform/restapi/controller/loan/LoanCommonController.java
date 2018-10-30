/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.loan
 *
 *    Filename:    LoanCommonController.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FlowInfo;
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.activiti.IProcessManagerService;
import com.company.platform.base.service.activiti.ITaskSetManagerService;
import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.base.service.sys.ISysUserService;
import com.company.platform.base.util.DataDictionaryUtil;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.code.FunInfo;
import com.company.platform.restapi.model.loan.code.LoanCodeReq;
import com.company.platform.restapi.model.loan.code.LoanCodeResp;
import com.company.platform.restapi.model.loan.code.UserInfo;
import com.company.platform.restapi.model.loan.code.WorkflowFunInfosReq;
import com.company.platform.restapi.model.loan.code.WorkflowFunInfosResp;
import com.company.platform.restapi.service.collateral.ICollateralLoanService;
import com.company.platform.restapi.service.loan.ILoanCodeService;
import com.company.platform.restapi.validated.loan.LoanProductValidate;
import com.company.platform.security.model.SecurityOrg;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: LoanCommonController 
* @Description: TODO(借贷通用方法集合) 
* @author zhengjn 
* @date 2018年1月18日 上午9:43:45 
*  
*/
@RestController
@RequestMapping("wechat")
public class LoanCommonController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanCommonController.class);

	@Autowired
	private LoanProductValidate loanProductValidate;

	@Resource
	private ILoanCodeService loanCodeService;

	@Autowired
	private IProcessManagerService processManagerService;

	@Autowired
	private ITaskSetManagerService taskSetManagerService;

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private ISysUserService sysUserService;

	@Resource
	DataDictionaryUtil dictUtil;

	@Autowired
	private ICollateralLoanService collateralLoanService;

	/** 
	* @Title: getLoanCode 
	* @Description: TODO(获取贷款申请编码) 
	* @param @param loanCodeReq 借贷申请请求（产品id）
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getLoanCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取贷款申请编码", modelType = RequestAccessConstants.SEARCH, desc = "通过产品id获取贷款申请编码")
	public BaseHttpParamsResp getLoanCode(LoanCodeReq loanCodeReq,String userId) throws Exception {
		logger.info("获取贷款申请编码开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 验证产品的存在性与生效性，获取借贷编码类型
		String loanCodeType = loanProductValidate.loanProductValidate(loanCodeReq.getProductId());
		// 获取当前用户所在的组织机构信息（机构id和机构编码）
		Map<String, String> org = loanCodeService.getOrgInfoByUserId(userId);
		// 验证用户权限
		loanProductValidate.loanProductOrgRelValidate(loanCodeReq.getProductId(), org.get("orgId").toString());
		// 获取贷款申请编码
		String code = loanCodeService.getSysCode(loanCodeType + "_LOAN",
				!org.containsKey("orgCode") || org.get("orgCode") == null ? "" : org.get("orgCode").toString());
		LoanCodeResp resp = new LoanCodeResp();
		resp.setCode(code);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		//baseHttpParamsResp.createSign();
		logger.info("获取贷款申请编码结束");
		return baseHttpParamsResp;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/getWorkflowFunInfos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取流程配置信息", modelType = RequestAccessConstants.SEARCH, desc = "通过产品id获取流程配置信息")
	public BaseHttpParamsResp getWorkflowFunInfos(@RequestBody @Validated WorkflowFunInfosReq workflowFunInfosReq)
			throws Exception {

		WorkflowFunInfosResp resp = new WorkflowFunInfosResp();
		logger.info("获取系统预估贷款范围");
		String esTop = dictUtil.getConfigData("ES_LOAN_POSITION_TOP");
		String esLow = dictUtil.getConfigData("ES_LOAN_POSITION_LOWER");
		if (StringUtils.isNotEmpty(esTop) || StringUtils.isNotEmpty(esLow)) {
			// 配置全局变量，获取人工总估值
			BigDecimal esValue = collateralLoanService.getEstimateTotalValue(workflowFunInfosReq.getLoanApplyId());
			if (esValue != null) {
				// 预估可贷额度比例上限
				if (StringUtils.isNotEmpty(esTop)) {
					resp.setEsValueTop(esValue.multiply(new BigDecimal(esTop)).toString());
				}
				// 预估可贷额度比例下限
				if (StringUtils.isNotEmpty(esLow)) {
					resp.setEsValueLow(esValue.multiply(new BigDecimal(esLow)).toString());
				}
			}
		}
		logger.info("获取流程配置信息");
		LoanProductInfo loanProductInfo = loanProductValidate
				.getLoanProductInfoById(workflowFunInfosReq.getLoanProductId());

		//获得流程首个节点所有配置，包括按钮，以及下一步待办人，此处流程启动使用
		FlowInfo flowInfo = processManagerService.getStartFlowInfo(workflowFunInfosReq.getLoanProductId(),
				loanProductInfo.getProcdefKey());

		//获得首节点所有按钮配置
		List<FunctionInfo> functionInfos = flowInfo.getFunctionsByParams(new HashMap<String, Object>());

		//组装返回页面的按钮配置
		List<FunInfo> funInfos = new ArrayList<FunInfo>();

		for (FunctionInfo functionInfo : functionInfos) {
			ActTaskset nextActTaskSet = processManagerService.getActTaskset(workflowFunInfosReq.getLoanProductId(),
					loanProductInfo.getProcdefKey(), functionInfo.getNextTaskKey());

			Map<String, Object> taskAudit = taskSetManagerService.getTaskAudit(workflowFunInfosReq.getLoanProductId(),
					loanProductInfo.getProcdefKey(), nextActTaskSet);

			FunInfo funInfo = new FunInfo();

			funInfo.setTaskKey(nextActTaskSet.getTaskkey());

			funInfo.setTaskName(nextActTaskSet.getTaskName());

			funInfo.setChoose(String.valueOf(functionInfo.isChoose()));

			funInfo.setBtnStyle(functionInfo.getBtnStyle());

			funInfo.setFunctionName(functionInfo.getFunctionName());

			funInfo.setBtnText(functionInfo.getBtnText());

			if (functionInfo.isChoose()) {//choose=true 选人 需要查出下一节点流程待办人
				if (StringUtils.isNoneBlank(nextActTaskSet.getRoleids())) {
					String roleids = nextActTaskSet.getRoleids();//获得流程配置，角色ID，多个逗号分隔
					SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal();
					List<SecurityOrg> orgs = user.getSecurityOrg();
					if (orgs != null && !orgs.isEmpty()) {
						SecurityOrg sorg = orgs.get(0);
						String orgId = sorg.getId();
						List<String> orgIds = organizationService.queryByVisibleOrgIds(orgId);//获得该机构用户下，可见机构权限
						List<Map<String, Object>> us = sysUserService.queryUsersByOrgsAndRoles(orgIds, roleids);//通过机构、角色获得待办人
						List<UserInfo> users_ = new ArrayList<UserInfo>();
						for (Map<String, Object> u : us) {
							UserInfo userInfo = new UserInfo();

							userInfo.setUserId((String) u.get("userId"));
							userInfo.setRealName((String) u.get("realName"));
							userInfo.setUserName((String) u.get("userName"));

							users_.add(userInfo);
						}
						funInfo.setUsers(users_);
					}
				} else if (StringUtils.isNoneBlank(nextActTaskSet.getPosterids())) {
					String posterids = nextActTaskSet.getPosterids();//获得流程配置，岗位ID，多个逗号分隔
					SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal();
					List<SecurityOrg> orgs = user.getSecurityOrg();
					if (orgs != null && !orgs.isEmpty()) {
						SecurityOrg sorg = orgs.get(0);
						String orgId = sorg.getId();
						List<String> orgIds = organizationService.queryByVisibleOrgIds(orgId);//获得该机构用户下，可见机构权限
						List<Map<String, Object>> us = sysUserService.queryUsersByOrgsAndPosts(orgIds, posterids);//通过机构、岗位获得待办人
						List<UserInfo> users_ = new ArrayList<UserInfo>();
						for (Map<String, Object> u : us) {//设置流程待办人
							UserInfo userInfo = new UserInfo();

							userInfo.setUserId((String) u.get("userId"));
							userInfo.setRealName((String) u.get("realName"));
							userInfo.setUserName((String) u.get("userName"));

							users_.add(userInfo);
						}
						funInfo.setUsers(users_);
					}
				} else {
					List<Map<String, Object>> users = (List<Map<String, Object>>) taskAudit.get("users");
					List<UserInfo> users_ = new ArrayList<UserInfo>();
					for (Map<String, Object> u : users) {//设置流程待办人
						UserInfo userInfo = new UserInfo();

						userInfo.setUserId((String) u.get("userId"));
						userInfo.setRealName((String) u.get("realName"));
						userInfo.setUserName((String) u.get("userName"));

						users_.add(userInfo);
					}
					funInfo.setUsers(users_);
				}
			}

			funInfos.add(funInfo);
		}
		resp.setFunInfos(funInfos);

		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();

		logger.info("获取流程配置信息结束");

		return baseHttpParamsResp;
	}
}
