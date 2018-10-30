package com.company.platform.restapi.service.loan.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import com.company.platform.restapi.model.loan.deal.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FlowInfo;
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.model.repayment.ReqRepaymentModel;
import com.company.platform.base.model.repayment.RespRepaymentModel;
import com.company.platform.base.service.activiti.IProcessManagerService;
import com.company.platform.base.service.activiti.ITaskSetManagerService;
import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.base.service.sys.ISysUserService;
import com.company.platform.base.util.DataDictionaryUtil;
import com.company.platform.base.util.DateUtil;
import com.company.platform.base.util.RegulationUtil;
import com.company.platform.base.util.RepaymentUtil;
import com.company.platform.base.util.RoundUtil;
import com.company.platform.base.util.TextFormatUtil;
import com.company.platform.restapi.dao.collateral.CollateralLoanMapper;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerPublicInfoMapper;
import com.company.platform.restapi.dao.guarantor.GuarantorInfoMapper;
import com.company.platform.restapi.dao.loan.LoanProductApplyMapper;
import com.company.platform.restapi.dao.loan.LoanTaskBuilderMapper;
import com.company.platform.restapi.dao.loan.LoanTempMapper;
import com.company.platform.restapi.dao.loan.ProductApplyInfoMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.dao.modelmanager.BusinessModelMapper;
import com.company.platform.restapi.model.activiti.WaitHandleTask;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.LoanProductApply;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.LoanProductModelV;
import com.company.platform.restapi.model.loan.code.UserInfo;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;
import com.company.platform.restapi.model.loan.unclaimed.FlowTaskInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusDataInfo;
import com.company.platform.restapi.model.repayment.LoanRepayment;
import com.company.platform.restapi.service.loan.ILoanDealService;
import com.company.platform.restapi.service.repayment.ILoanRepaymentService;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: LoanDealServiceImpl 
* @Description: TODO(待办-审核相关service实现类) 
* @author wangxue 
* @date 2018年7月13日 下午1:42:17 
*  
*/
@Service
public class LoanDealServiceImpl implements ILoanDealService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanDealServiceImpl.class);

	@Resource
	private LoanProductApplyMapper loanProductApplyMapper;

	@Resource
	private ProductInfoMapper productInfoMapper;

	@Resource
	private ILoanRepaymentService loanRepaymentService;

	@Resource
	private DataDictionaryUtil dictUtil;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ProductApplyInfoMapper productApplyInfoMapper;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ITaskSetManagerService taskSetManagerService;

	@Autowired
	private LoanTempMapper loanTempMapper;

	@Autowired
	private CustomerPublicInfoMapper customerPublicInfoMapper;

	@Autowired
	private BusinessModelMapper businessModelMapper;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Autowired
	private CollateralLoanMapper collateralLoanMapper;

	@Autowired
	private GuarantorInfoMapper guarantorInfoMapper;

	@Autowired
	private IProcessManagerService processManagerService;

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private LoanTaskBuilderMapper loanTaskBuilderMapper;

	@Value("${html.url}")
	private String htmlUrl;

	/*
	 * (非 Javadoc) <p>Title: getRepaymentTrial</p> <p>Description: </p>
	 * 
	 * @param loanProductApplyFeeReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanDealService#
	 * getRepaymentTrial(com.company.platform.restapi.model.loan.fee.
	 * LoanProductApplyFeeReq)
	 */
	@Override
	public LoanRepaymentTrialResp getRepaymentTrial(LoanRepaymentTrailReq req) throws BusinessException {
		LoanRepaymentTrialResp resp = new LoanRepaymentTrialResp();
		LoanProductApply apply = null;
		// 是否首息收取 0：否 1：是
		boolean isFirstInterestCharge = false;
		// 是否首期收取 0：否 1：是
		boolean isFirstPrincipalInterestCharge = false;
		if (StringUtils.isNotBlank(req.getProductApplyId())) {
			apply = loanProductApplyMapper.selectById(req.getProductApplyId());
		}
		if (apply != null) {
			LoanProductInfo loanProduct = productInfoMapper.queryByPrimaryId(apply.getLoanProductId());
			if (loanProduct != null) {
				isFirstInterestCharge = Boolean.valueOf(loanProduct.getIsFirstInterestCharge());
				isFirstPrincipalInterestCharge = Boolean.valueOf(loanProduct.getIsFirstPrincipalInterestCharge());
			}
		}
		List<LoanRepaymentInfo> list = new ArrayList<LoanRepaymentInfo>();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalInterest = BigDecimal.ZERO;
		if (apply == null || apply.getActionStatus() < 2) {/* 未放款，生成还款计划 */
			ReqRepaymentModel reqRepaymentModel = new ReqRepaymentModel();
			reqRepaymentModel.setAmount(new BigDecimal(req.getAmount()));
			reqRepaymentModel.setInterestRate(new BigDecimal(req.getInterestRate()));
			reqRepaymentModel.setTermCount(Integer.parseInt(req.getTermCount()));
			reqRepaymentModel.setTermUnit(req.getTermUnit());
			if (!"1".equals(req.getRepayType())) {
				reqRepaymentModel.setRepayTermCount(Integer.parseInt(req.getRepayTermCount()));
				reqRepaymentModel.setRepayTermUnit(req.getRepayTermUnit());
			}
			reqRepaymentModel.setRepayType(req.getRepayType());
			reqRepaymentModel.setDaysOneMonth(dictUtil.getConfigData("DAYS_ONE_MONTH"));
			reqRepaymentModel.setDaysOneYear(dictUtil.getConfigData("DAYS_ONE_YEAR"));
			reqRepaymentModel.setRound(RoundUtil.getRound(dictUtil.getConfigData("ROUND")));
			List<RespRepaymentModel> respRepaymentModels = RepaymentUtil.createRepayment(reqRepaymentModel);
			if (respRepaymentModels != null && respRepaymentModels.size() > 0) {
				for (RespRepaymentModel respRepaymentModel : respRepaymentModels) {
					LoanRepaymentInfo repayment = new LoanRepaymentInfo();
					repayment.setPeriodNum(respRepaymentModel.getPeriodNum().toString());
					repayment.setPrincipal(TextFormatUtil.getCAPITALText(respRepaymentModel.getPrincipal()));
					repayment.setInterest(TextFormatUtil.getCAPITALText(respRepaymentModel.getInterest()));
					repayment.setAmount(TextFormatUtil
							.getCAPITALText(respRepaymentModel.getPrincipal().add(respRepaymentModel.getInterest())));
					repayment.setRepayTime(DateUtil.dateFormat(respRepaymentModel.getRepayTime()));
					list.add(repayment);
				}
				totalInterest = respRepaymentModels.get(0).getTotalInterest();
				totalAmount = respRepaymentModels.get(0).getTotalPrincipal().add(totalInterest);
			}
		} else {/* 已放款，直接查询还款计划 */
			List<LoanRepayment> repays = loanRepaymentService.selectByLoanProductApplyId(req.getProductApplyId());
			for (LoanRepayment r : repays) {
				LoanRepaymentInfo repayment = new LoanRepaymentInfo();
				repayment.setPeriodNum(String.valueOf(r.getPeriodNum()));
				repayment.setPrincipal(TextFormatUtil.getCAPITALText(r.getDuePrincipal()));
				repayment.setInterest(TextFormatUtil.getCAPITALText(r.getDueInterest()));
				repayment.setAmount(TextFormatUtil.getCAPITALText(r.getDuePrincipal().add(r.getDueInterest())));
				repayment.setRepayTime(DateUtil.dateFormat(r.getDueRepayDate()));
				list.add(repayment);
				totalAmount = totalAmount.add(r.getDuePrincipal());
				totalInterest = totalInterest.add(r.getDueInterest());
			}
			totalAmount = totalAmount.add(totalInterest);
		}

		resp.setIsFirstInterestCharge(isFirstInterestCharge ? "是" : "否");
		resp.setIsFirstPrincipalInterestCharge(isFirstPrincipalInterestCharge ? "是" : "否");
		resp.setTotalAmount(TextFormatUtil.getCAPITALText(totalAmount));
		resp.setTotalInterest(TextFormatUtil.getCAPITALText(totalInterest));
		resp.setRepaymentList(list);
		return resp;
	}

	@Override
	public List<FlowTaskInfo> showCommnet(String processType, String processInstanceId, Map<String, String> taskKeyMap,
			String theUserId, String theTaskId) {
		List<FlowTaskInfo> result = new ArrayList<FlowTaskInfo>();
		String productId = null;
		String wfId = null;

		if (theTaskId != null && !"undefined".equals(theTaskId) && !"".equals(theTaskId)) {
			ProcessInstance pd = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
					.singleResult();
			if ("loan".equals(processType)) {
				FullLoanProductApplyInfo temp = productApplyInfoMapper.getLoanProductApplyById(pd.getBusinessKey());
				productId = temp.getLoanProductId();
			}
			wfId = pd.getProcessDefinitionId();
		} else {
			HistoricProcessInstance hi = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if ("loan".equals(processType)) {
				FullLoanProductApplyInfo temp = productApplyInfoMapper.getLoanProductApplyById(hi.getBusinessKey());
				if (temp != null) {
					productId = temp.getLoanProductId();
				}
			}
			wfId = hi.getProcessDefinitionId();
		}

		if (theTaskId != null && !"undefined".equals(theTaskId) && !"".equals(theTaskId)) {
			// 执行实例
			ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (execution != null) {
				// 当前实例的执行到哪个节点
				String activitiId = execution.getActivityId();
				FlowInfo theFlow = this.getFlowInfoByTaskKey(productId, wfId, activitiId);
				FlowTaskInfo theTask = new FlowTaskInfo();
				theTask.setTaskName(theFlow.getTaskName() + "（进行中）");// 审核节点
				theTask.setTaskKey(theFlow.getTaskKey());
				theTask.setAduitResult("");// 审核结果
				theTask.setAuditTime("");// 审核时间
				theTask.setComment("");// 审核意见
				String assignee = (String) taskService.getVariable(theTaskId, theFlow.getTaskKey() + "Assignee");
				if (assignee != null) {
					Map<String, String> theUser = getUser(assignee);
					theTask.setAuditName(theUser.get("name"));// 审核人
				} else {
					theTask.setAuditName("待领用");// 审核人
				}
				result.add(theTask);
			}
		}

		if (productId != null && wfId != null) {
			List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
			List<String> hideKeyList = new ArrayList<String>();
			if (commentList != null && !commentList.isEmpty()) {
				Collections.sort(commentList, new Comparator<Comment>() {
					public int compare(Comment o1, Comment o2) {
						Integer i1 = Integer.valueOf(o1.getId());
						Integer i2 = Integer.valueOf(o2.getId());
						return i2 - i1;
					}
				});
				List<HistoricActivityInstance> activitys = historyService.createHistoricActivityInstanceQuery()
						.processInstanceId(processInstanceId).list();
				List<HistoricVariableInstance> vars = historyService.createHistoricVariableInstanceQuery()
						.processInstanceId(processInstanceId).list();

				List<Map<String, Object>> handleReasons = taskSetManagerService.getHandleReasons(processInstanceId);

				for (Comment comment : commentList) {
					String comId = comment.getId();
					String activityId = null;
					for (HistoricVariableInstance vi : vars) {
						if (comId.equals(vi.getVariableName())) {
							activityId = (String) vi.getValue();
							break;
						}
					}
					HistoricActivityInstance act = null;
					if (activityId != null) {
						for (HistoricActivityInstance ai : activitys) {
							if (activityId.equals(ai.getActivityId())) {
								act = ai;
								break;
							}
						}
					}
					FlowTaskInfo theTask = new FlowTaskInfo();
					String reason = null;
					String type = null;
					if (handleReasons != null) {
						for (Map<String, Object> handleReason : handleReasons) {
							String cId = (String) handleReason.get("commentId");
							if (comId.equals(cId)) {
								reason = (String) handleReason.get("reason");
								type = (String) handleReason.get("type");
								break;
							}
						}
					}
					if (StringUtils.isNotBlank(reason)) {
						theTask.setReason(reason);// 操作原因，退回原因、拒绝原因
						theTask.setType(type);// 操作类型，退回或拒绝
					}
					String userId = comment.getUserId();
					Map<String, String> user = getUser(userId);
					theTask.setAuditName(user.get("name"));// 审核人
					String taskId = comment.getTaskId();
					boolean f = false;
					String hideKey = null;
					if (!StringUtils.isEmpty(taskId)) {
						HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId)
								.singleResult();
						String taskKey = task.getTaskDefinitionKey();
						if (act != null) {
							theTask.setTaskName(act.getActivityName());
							theTask.setTaskKey(act.getActivityId());
						} else {
							theTask.setTaskName(task.getName());// 节点名称
							theTask.setTaskKey(taskKey);
						}

						FunctionInfo functionInfo = taskSetManagerService.findFunctionInfoByFunctionName(productId,
								wfId, taskKey, comment.getType());
						hideKey = taskKeyMap.get(taskKey);
						if (hideKey != null) {
							if (theUserId != null && theUserId.equals(user.get("id"))) {
								f = true;
							}
						}

						if (functionInfo == null) {
							String a = comment.getType();
							if ("pass".equals(a)) {
								theTask.setAduitResult("通过");
							} else if ("reject".equals(a)) {
								theTask.setAduitResult("拒绝");
							} else {
								theTask.setAduitResult("未知，请联系管理员");
							}
						} else {
							String aduitResult = functionInfo.getBtnText();
							theTask.setAduitResult(aduitResult == null ? "" : aduitResult);// 审核结果
						}
					}
					String message = comment.getFullMessage();
					theTask.setComment(message);// 批注
					if ("agree".equals(comment.getType()) && f) {
						hideKeyList.add(hideKey);
					}
					theTask.setAuditTime(DateUtil.dateTimeFormat(comment.getTime()));// 审核时间
					result.add(theTask);
				}

				for (int i = 0; i < hideKeyList.size(); i++) {
					String hideKey = hideKeyList.get(i);
					for (int j = 0; j < result.size(); j++) {
						FlowTaskInfo data = result.get(j);
						if (hideKey.equals(data.getTaskKey())) {
							data.setAduitResult("******");
						}
					}
				}
			}

			List<Map<String, Object>> aucommentList = taskSetManagerService.getAUComments(processInstanceId);
			if (aucommentList != null && !aucommentList.isEmpty()) {
				for (Map<String, Object> aucomment : aucommentList) {
					FlowTaskInfo theTask = new FlowTaskInfo();
					theTask.setAuditName("自动");// 审核人
					theTask.setTaskName(aucomment.get("ACT_NAME_").toString());
					theTask.setTaskKey(aucomment.get("ACTIVITI_ID_").toString());

					String handleType = (String) aucomment.get("TYPE_");
					if ("pass".equals(handleType)) {
						theTask.setAduitResult("通过");
					} else if ("reject".equals(handleType)) {
						theTask.setAduitResult("拒绝");
					} else {
						theTask.setAduitResult("未知，请联系管理员");
					}

					String message = (String) aucomment.get("MESSAGE_");
					theTask.setComment(message);// 批注
					theTask.setAuditTime(DateUtil.dateTimeFormat((Date) aucomment.get("TIME_")));// 审核时间
					result.add(theTask);
				}
			}
		}
		return result;
	}

	public FlowInfo getFlowInfoByTaskKey(String productId, String wfId, String nextTaskKey) {
		FlowInfo flow = new FlowInfo();
		ActTaskset task = taskSetManagerService.getTheActTaskset(productId, wfId, nextTaskKey);
		if (task != null) {
			flow.setTaskName(task.getTaskName());
			flow.setTaskKey(task.getTaskkey());

			List<Map<String, Object>> functions = taskSetManagerService.getWsFunctions(task.getId());

			if (functions != null && !functions.isEmpty()) {
				for (Map<String, Object> function : functions) {
					FunctionInfo functionInfo = new FunctionInfo();

					functionInfo.setFunctionName((String) function.get("functionName"));
					functionInfo.setBtnText((String) function.get("btnText"));
					functionInfo.setBtnStyle((String) function.get("btnStyle"));
					functionInfo.setChoose((Boolean) function.get("choose"));
					functionInfo.setNoticeFlag((Boolean) function.get("noticeFlag"));
					functionInfo.setNoticeTemp((String) function.get("noticeTemp"));
					functionInfo.setNextTaskKey((String) function.get("nextTaskKey"));
					functionInfo.setExpression((String) function.get("expression"));
					functionInfo.setVariableJson((String) function.get("variableJson"));

					flow.getFunctions().add(functionInfo);
				}
			}

		}
		return flow;
	}

	/**
	 * 获取审核用户信息
	 * @Title: getUser   
	 * @Description: TODO(获取审核用户信息)   
	 * @param: @param assignee
	 * @param: @return      
	 * @return: Map<String,String>      
	 * @throws
	 */
	public static Map<String, String> getUser(String assignee) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("name", "");// 审核人
		result.put("id", "");// 审核人ID
		if (StringUtils.isNoneEmpty(assignee)) {
			String[] str = assignee.split(":");
			if (str.length > 1) {
				result.put("name", str[1]);// 审核人
			} else {
				result.put("name", "");// 审核人
			}
			result.put("id", str[0]);// 审核人ID
		}
		return result;
	}

	@Override
	public String judgeLoanProductRelation(String loanProductId, String loanProductApplyId, String taskKey) {
		boolean flag = false;
		// 判断产品是否关联风控征信模型、规则类型
		// 查看产品是否关联风控征信模型
		List<Map<String, Object>> ruleList = productInfoMapper.getLoanProductRule(loanProductId);
		if (ruleList != null && ruleList.size() > 0) {
			flag = true;
		}
		// 查看产品是否关联规则模型
		if (!flag) {
			LoanProductModelV modelV = productApplyInfoMapper.getLoanProductModelVInfo(loanProductId, "CTM_WCL");
			if (modelV != null) {
				flag = true;
			}
		}
		String url = null;
		if (flag) {
			url = htmlUrl + "/loan/showBusiness_app?loanProductApplyId=" + loanProductApplyId + "&taskKey=" + taskKey;
		}
		return url;
	}

	@Override
	public List<WaitHandleTask> getDealInfoList(List<WaitHandleTask> list) {
		Map<String, Object> customerTemp = new HashMap<String, Object>();
		for (WaitHandleTask task : list) {
			String loanProductApplyId = task.getBusinessKey();
			LoanProductApplyInfo loanProductApplyInfo = loanTempMapper.getLoanApplyinfo(loanProductApplyId);
			if (loanProductApplyInfo == null) {
				logger.error("借贷申请不存在");
				continue;
			} else {
				String slUpdateTime = loanProductApplyInfo.getUpdateTime();
				CustomerPublicInfo customerPublicInfo = customerPublicInfoMapper
						.selectByPrimaryKey(loanProductApplyInfo.getCustomerId());
				if ("0".equals(customerPublicInfo.geteType())) {// 个人客户
					LoanApplyAuditingForPersonal loanApplyInfo = new LoanApplyAuditingForPersonal();

					loanApplyInfo.setUpdateTimeProductApply(slUpdateTime);
					Map<String, Object> busRecord = businessModelMapper.getBusRecordByBusId(loanProductApplyId);

					if (busRecord != null) {
						List<ModelBusDataInfo> busData = businessModelMapper.getModelBusDataInfo(loanProductApplyId);
						Map<String, Object> modelDataMap = new HashMap<String, Object>();
						modelDataMap.put("id", busRecord.get("busmodId"));
						if (!busData.isEmpty()) {
							List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
							for (ModelBusDataInfo d : busData) {
								Map<String, Object> item = new HashMap<String, Object>();
								item.put("code", d.getBusCode());
								item.put("val", d.getBusVal());
								data.add(item);
							}
							modelDataMap.put("data", data);
						}
						loanApplyInfo.setModelData(JSON.toJSONString(modelDataMap));
					}

					loanApplyInfo.setLoanProductApplyInfo(loanProductApplyInfo);

					loanApplyInfo.setCustomerType(customerPublicInfo.geteType());

					CustomerInfoResp cus = (CustomerInfoResp) customerTemp.get(loanProductApplyInfo.getCustomerId());

					if (cus == null) {
						CustomerInfoResp customer = new CustomerInfoResp();
						// 返回客户基本信息
						customer.setCustomerPublicInfo(customerPublicInfo);
						String customerId = customerPublicInfo.getId();
						// 返回客户联系信息
						customer.setCustomerContactInfo(customerInfoMapper.getCustomerContactInfo(customerId));
						// 返回客户地址信息
						customer.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
						// 返回客户工作信息
						customer.setCustomerJobDetailInfo(customerInfoMapper.getCustomerJobDetailInfo(customerId));
						// 返回客户联系人信息
						customer.setCustomerRelationshipInfo(
								customerInfoMapper.getCustomerRelationshipInfo(customerId));
						// 返回客户个人基本信息
						customer.setPersonalCustomerBaseInfo(customerInfoMapper.getPersonalCustomerInfo(customerId));
						// 返回客户配偶信息
						customer.setCustomerMateInfo(customerInfoMapper.getCustomerMateInfo(customerId));
						// 返回客户资产信息
						customer.setCustomerAssetInfo(customerInfoMapper.getCustomerAssetInfo(customerId));
						cus = customer;
						customerTemp.put(loanProductApplyInfo.getCustomerId(), cus);
					}

					CustomerPublicInfo cpi = cus.getCustomerPublicInfo();
					if (cpi != null) {
						loanApplyInfo.setUpdateTimeCustomer(cpi.getUpdateTime());
					}
					BeanUtils.copyProperties(cus, loanApplyInfo);
					Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanProductApplyInfo.getId());
					if (imgRecord != null) {
						loanApplyInfo.setImgModelId(imgRecord.get("modelId").toString());
					}
					loanApplyInfo
							.setCollateralInfo(collateralLoanMapper.showCollateralListByApplyId(loanProductApplyId));
					loanApplyInfo.setGuarantorInfo(guarantorInfoMapper.showGuarantorInByApplyId(loanProductApplyId));
					loanApplyInfo.setHtmlUrl(this.judgeLoanProductRelation(loanProductApplyInfo.getLoanProductId(),
							loanProductApplyId, task.getTaskKey()));
					task.setApplyDetail(loanApplyInfo);
				} else {// 企业客户
					LoanApplyAuditingForEnterprise loanApplyInfo = new LoanApplyAuditingForEnterprise();

					loanApplyInfo.setUpdateTimeProductApply(slUpdateTime);
					Map<String, Object> busRecord = businessModelMapper.getBusRecordByBusId(loanProductApplyId);

					if (busRecord != null) {
						List<ModelBusDataInfo> busData = businessModelMapper.getModelBusDataInfo(loanProductApplyId);
						Map<String, Object> modelDataMap = new HashMap<String, Object>();
						modelDataMap.put("id", busRecord.get("busmodId"));
						if (!busData.isEmpty()) {
							List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
							for (ModelBusDataInfo d : busData) {
								Map<String, Object> item = new HashMap<String, Object>();
								item.put("code", d.getBusCode());
								item.put("val", d.getBusVal());
								data.add(item);
							}
							modelDataMap.put("data", data);
						}
						loanApplyInfo.setModelData(JSON.toJSONString(modelDataMap));
					}

					loanApplyInfo.setLoanProductApplyInfo(loanProductApplyInfo);

					loanApplyInfo.setCustomerType(customerPublicInfo.geteType());

					EnterpriseInfoResp cus = (EnterpriseInfoResp) customerTemp
							.get(loanProductApplyInfo.getCustomerId());

					if (cus == null) {
						EnterpriseInfoResp customer = new EnterpriseInfoResp();
						// 返回客户基本信息
						customer.setCustomerPublicInfo(customerPublicInfo);
						String customerId = customerPublicInfo.getId();
						// 返回客户地址信息
						customer.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
						// 返回企业客户联系人信息
						customer.setCorporationCustomerContactinfo(
								customerInfoMapper.getCorporationCustomerContactinfo(customerId));
						// 返回企业客户股东信息
						customer.setCorporationCustomerShareholderInfo(
								customerInfoMapper.getCorporationCustomerShareholderInfo(customerId));
						// 返回企业客户法人信息
						customer.setCorporationBaseAndLrInfo(
								customerInfoMapper.getCorporationBaseAndLrInfo(customerId));
						cus = customer;
						customerTemp.put(loanProductApplyInfo.getCustomerId(), cus);
					}
					CustomerPublicInfo cpi = cus.getCustomerPublicInfo();
					if (cpi != null) {
						loanApplyInfo.setUpdateTimeCustomer(cpi.getUpdateTime());
					}
					BeanUtils.copyProperties(cus, loanApplyInfo);

					Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanProductApplyInfo.getId());
					if (imgRecord != null) {
						loanApplyInfo.setImgModelId(imgRecord.get("modelId").toString());
					}
					loanApplyInfo
							.setCollateralInfo(collateralLoanMapper.showCollateralListByApplyId(loanProductApplyId));
					loanApplyInfo.setGuarantorInfo(guarantorInfoMapper.showGuarantorInByApplyId(loanProductApplyId));
					loanApplyInfo.setHtmlUrl(this.judgeLoanProductRelation(loanProductApplyInfo.getLoanProductId(),
							loanProductApplyId, task.getTaskKey()));
					task.setApplyDetail(loanApplyInfo);
				}
			}

		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionBtnInfo> getTaskInfo(LoanTaskInfoReq req) {
		List<FunctionBtnInfo> list = new ArrayList<FunctionBtnInfo>();
		String processInstanceId = req.getProcessInstanceId();
		if (processInstanceId == null || "".equals(processInstanceId)) {
			Task task = taskService.createTaskQuery().taskId(req.getTaskId()).singleResult();
			processInstanceId = task.getProcessInstanceId();
		}
		ProcessInstance pd = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		Task task = taskService.createTaskQuery().taskId(req.getTaskId()).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
		String productId = null;
		if ("loan".equals(req.getProcessType())) {// 借贷申请
			FullLoanProductApplyInfo loanProductApply = productApplyInfoMapper
					.getLoanProductApplyById(req.getLoanProductApplyId());
			productId = loanProductApply.getLoanProductId();
			if (productId != null) {
				// 完整流程配置信息
				FlowInfo flowInfo = processManagerService.getFlowInfoByTaskKey(productId, pd.getProcessDefinitionId(),
						req.getTaskKey());
				String variableJson = processManagerService.getVariableByNextTaskKey(productId,
						pd.getProcessDefinitionId(), req.getTaskKey());
				if (StringUtils.isNoneBlank(variableJson)) {
					Map<String, Object> vs = JSONObject.parseObject(variableJson);
					if (vs != null && !vs.isEmpty()) {
						Set<String> variablesSet = vs.keySet();
						Iterator<String> it = variablesSet.iterator();
						while (it.hasNext()) {
							String key = it.next();
							Object v = vs.get(key);
							if (v instanceof String) {
								String var = (String) v;
								if (var.startsWith("#") && var.endsWith("#")) {
									var = var.replaceAll("#", "");

									Map<String, Object> param = new HashMap<String, Object>();
									param.put("loanProductApply", loanProductApply);

									Object r = RegulationUtil.invokeMethod(var, param);

									if (r instanceof BigDecimal) {
										Object x = runtimeService.getVariable(task.getExecutionId(), key);
										if (!x.equals(r) && r != null) {
											BigDecimal b = (BigDecimal) r;
											runtimeService.setVariable(task.getExecutionId(), key, b.doubleValue());
											variables.put(key, b.doubleValue());
										}
									} else {
										Object x = runtimeService.getVariable(task.getExecutionId(), key);
										if (!x.equals(r) && r != null) {
											runtimeService.setVariable(task.getExecutionId(), key, r);
											variables.put(key, r);
										}
									}
								}
							}
						}
					}
				}
				// 获得是否自带合同
				String hasContract = "1".equals(loanProductApply.getOwnContract()) ? "y" : "n";
				String hasContract_ = (String) runtimeService.getVariable(task.getExecutionId(), "hasContract");
				if (!hasContract.equals(hasContract_) && hasContract_ != null) {
					runtimeService.setVariable(task.getExecutionId(), "hasContract", hasContract);
					variables.put("hasContract", hasContract);
				}
				// 获得流程按钮
				List<FunctionInfo> functions = flowInfo.getFunctionsByParams(variables);
				for (FunctionInfo function : functions) {
					// 返回按钮信息
					FunctionBtnInfo fun = new FunctionBtnInfo();
					BeanUtils.copyProperties(function, fun);
					if (function.isChoose()) {
						fun.setChoose("true");
					} else {
						fun.setChoose("false");
					}
					if (function.isNoticeFlag()) {
						fun.setNoticeFlag("true");
					} else {
						fun.setNoticeFlag("false");
					}
					if (function.getNextTaskKey() != null && function.isChoose()) {// 下一节点配置不能为null，选人为是，才能获得选人列表
						ActTaskset nextActTaskSet = processManagerService.getActTaskset(productId,
								pd.getProcessDefinitionKey(), function.getNextTaskKey());
						if (StringUtils.isNoneBlank(nextActTaskSet.getRoleids())) {// 角色
							String roleids = nextActTaskSet.getRoleids();
							String orgId = loanProductApply.getOrgId();

							List<String> orgIds = organizationService.queryByVisibleOrgIds(orgId);

							List<Map<String, Object>> us = sysUserService.queryUsersByOrgsAndRoles(orgIds, roleids);
							fun.setUsers(this.getUsers(us));
						} else if (StringUtils.isNoneBlank(nextActTaskSet.getPosterids())) {// 岗位
							String posterids = nextActTaskSet.getPosterids();
							String orgId = loanProductApply.getOrgId();
							List<String> orgIds = organizationService.queryByVisibleOrgIds(orgId);
							List<Map<String, Object>> us = sysUserService.queryUsersByOrgsAndPosts(orgIds, posterids);
							fun.setUsers(this.getUsers(us));
						} else {// 用户及机构
							Map<String, Object> taskAudit = taskSetManagerService.getTaskAudit(productId,
									pd.getProcessDefinitionKey(), nextActTaskSet);
							fun.setUsers(this.getUsers((List<Map<String, Object>>) taskAudit.get("users")));
						}
					}
					list.add(fun);
				}
			}
		}
		return list;
	}

	private List<UserInfo> getUsers(List<Map<String, Object>> us) {
		List<UserInfo> users = new ArrayList<UserInfo>();
		if (us != null && us.size() > 0) {
			for (Map<String, Object> map : us) {
				UserInfo info = new UserInfo();
				info.setUserId(map.get("userId").toString());
				info.setUserName(map.get("userName").toString());
				info.setRealName(map.get("realName").toString());
				users.add(info);
			}
		}
		return users;
	}

	@Override
	public void finishTask(String result, String processInstanceId) {
		HistoricProcessInstance pdHistroty = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		if (pdHistroty != null) {
			String applyId = pdHistroty.getBusinessKey();
			String status = "1";
			// 更新借贷申请审核状态和更新时间
			if ("9999".equals(result)) {
				status = "2";// 审核成功
			} else if ("9000".equals(result)) {
				status = "3";// 审核拒绝
			}
			productApplyInfoMapper.updateLoanApplyStatus(status, applyId);
			if ("9999".equals(result)) {// 流程正常结束
				// 创建贷后检查任务
				this.buildTask(applyId);
			}
		}
	}

	/** 
	* @Title: buildTask 
	* @Description: TODO(创建贷后检查任务) 
	* @param @param applyId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void buildTask(String applyId) {
		// 贷后检查人员信息
		List<Map<String, Object>> handlers = loanTaskBuilderMapper.findHandlersByApplyId(applyId);
		FullLoanProductApplyInfo loanApply = productApplyInfoMapper.getLoanProductApplyById(applyId);
		// 产品中设置的贷后检查信息
		Map<String, Object> productTask = loanTaskBuilderMapper.findProductInfoById(loanApply.getLoanProductId());
		if (productTask != null) {
			// 新建贷后检查信息
			loanTaskBuilderMapper.addLoanCheckedApplyInfo(applyId);
			String c_handler = null;
			if (handlers != null && !handlers.isEmpty()) {
				for (Map<String, Object> handler : handlers) {
					String userPost = (String) handler.get("userPost");
					if ("loanedCheck".equals(userPost)) {// 贷后检查任务创建
						c_handler = (String) handler.get("userId");
					}
				}
			}
			Integer remindNum = (Integer) productTask.get("remindNum");// 贷后提醒次数
			if (remindNum != null) {
				for (int i = 0; i < remindNum; i++) {
					LoanedCheckTask task = new LoanedCheckTask();
					task.setId(UUID.randomUUID().toString());
					task.setAppId(applyId);
					task.setCreateTime(new Date());
					task.setHandler(c_handler);
					task.setOrgId(loanApply.getOrgId());
					task.setTaskType("01"); // 常规
					task.setStatus(0);
					loanTaskBuilderMapper.createLoanCheckedTask(task);
					if (c_handler != null && !"".equals(c_handler)) {
						/* 发送贷后检查任务通知，待完善 */
					}
				}
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: getLoanProcessTaskInfo</p> <p>Description: </p>
	 * 
	 * @param applyId
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanDealService#
	 * getLoanProcessTaskInfo(java.lang.String)
	 */
	@Override
	public Map<String, String> getLoanProcessTaskInfo(String applyId) {
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return loanTempMapper.getLoanProcessTaskInfo(user.getId() + ":" + user.getRealName(), applyId);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 获取贷款申请费用信息
	 * @Date 15:45 2018/9/17
	 * @Param [req]
	 * @return com.company.platform.restapi.model.loan.deal.LoanRepaymentTrialResp
	 **/
	@Override
	public LoanRepaymentTrialResp getRepaymentTrialNoValidate(LoanRepaymentTrailInfo req) {
		req.setRepayTermCount("1");
		req.setRepayTermUnit("2");
		req.setTermUnit("2");
		req.setInterestRate("17.55");
		LoanRepaymentTrialResp resp = new LoanRepaymentTrialResp();
		LoanProductApply apply = null;
		// 是否首息收取 0：否 1：是
		boolean isFirstInterestCharge = false;
		// 是否首期收取 0：否 1：是
		boolean isFirstPrincipalInterestCharge = false;
		if (StringUtils.isNotBlank(req.getProductApplyId())) {
			apply = loanProductApplyMapper.selectById(req.getProductApplyId());
		}
		if (apply != null) {
			LoanProductInfo loanProduct = productInfoMapper.queryByPrimaryId(apply.getLoanProductId());
			if (loanProduct != null) {
				isFirstInterestCharge = Boolean.valueOf(loanProduct.getIsFirstInterestCharge());
				isFirstPrincipalInterestCharge = Boolean.valueOf(loanProduct.getIsFirstPrincipalInterestCharge());
			}
		}
		List<LoanRepaymentInfo> list = new ArrayList<LoanRepaymentInfo>();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalInterest = BigDecimal.ZERO;
		if (apply == null || apply.getActionStatus() < 2) {/* 未放款，生成还款计划 */ //还款状态 0：未签订合同 1：已签定合同 2：还款中 3：已还完  -1 支付失败   9  支付处理中'
			ReqRepaymentModel reqRepaymentModel = new ReqRepaymentModel();
			reqRepaymentModel.setAmount(new BigDecimal(req.getAmount()));
			reqRepaymentModel.setInterestRate(new BigDecimal(req.getInterestRate()).divide(new BigDecimal(100)));
			reqRepaymentModel.setTermCount(Integer.parseInt(req.getTermCount()));
			reqRepaymentModel.setTermUnit(req.getTermUnit());
			if (!"1".equals(req.getRepayType())) {
				reqRepaymentModel.setRepayTermCount(Integer.parseInt(req.getRepayTermCount()));
				reqRepaymentModel.setRepayTermUnit(req.getRepayTermUnit());
			}
			reqRepaymentModel.setRepayType(req.getRepayType());
			reqRepaymentModel.setDaysOneMonth(dictUtil.getConfigData("DAYS_ONE_MONTH"));
			reqRepaymentModel.setDaysOneYear(dictUtil.getConfigData("DAYS_ONE_YEAR"));
			reqRepaymentModel.setRound(RoundUtil.getRound(dictUtil.getConfigData("ROUND")));
			List<RespRepaymentModel> respRepaymentModels = RepaymentUtil.createRepayment(reqRepaymentModel);
			if (respRepaymentModels != null && respRepaymentModels.size() > 0) {
				for (RespRepaymentModel respRepaymentModel : respRepaymentModels) {
					LoanRepaymentInfo repayment = new LoanRepaymentInfo();
					repayment.setPeriodNum(respRepaymentModel.getPeriodNum().toString());
					repayment.setPrincipal(TextFormatUtil.getCAPITALText(respRepaymentModel.getPrincipal()));
					repayment.setInterest(TextFormatUtil.getCAPITALText(respRepaymentModel.getInterest()));
					repayment.setAmount(TextFormatUtil
							.getCAPITALText(respRepaymentModel.getPrincipal().add(respRepaymentModel.getInterest())));
					repayment.setRepayTime(DateUtil.dateFormat(respRepaymentModel.getRepayTime()));
					list.add(repayment);
				}
				totalInterest = respRepaymentModels.get(0).getTotalInterest();
				totalAmount = respRepaymentModels.get(0).getTotalPrincipal().add(totalInterest);
			}
		} else {/* 已放款，直接查询还款计划 */
			List<LoanRepayment> repays = loanRepaymentService.selectByLoanProductApplyId(req.getProductApplyId());
			for (LoanRepayment r : repays) {
				LoanRepaymentInfo repayment = new LoanRepaymentInfo();
				repayment.setPeriodNum(String.valueOf(r.getPeriodNum()));
				repayment.setPrincipal(TextFormatUtil.getCAPITALText(r.getDuePrincipal()));
				repayment.setInterest(TextFormatUtil.getCAPITALText(r.getDueInterest()));
				repayment.setAmount(TextFormatUtil.getCAPITALText(r.getDuePrincipal().add(r.getDueInterest())));
				repayment.setRepayTime(DateUtil.dateFormat(r.getDueRepayDate()));
				list.add(repayment);
				totalAmount = totalAmount.add(r.getDuePrincipal());
				totalInterest = totalInterest.add(r.getDueInterest());
			}
			totalAmount = totalAmount.add(totalInterest);
		}

		resp.setIsFirstInterestCharge(isFirstInterestCharge ? "是" : "否");
		resp.setIsFirstPrincipalInterestCharge(isFirstPrincipalInterestCharge ? "是" : "否");
		resp.setTotalAmount(TextFormatUtil.getCAPITALText(totalAmount));
		resp.setTotalInterest(TextFormatUtil.getCAPITALText(totalInterest));
		resp.setRepaymentList(list);
		return resp;
	}
}
