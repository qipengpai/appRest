package com.company.platform.restapi.service.loan.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.company.platform.base.model.sys.Organization;
import com.company.platform.base.service.activiti.ITaskSetManagerService;
import com.company.platform.restapi.dao.custom.*;
import com.company.platform.restapi.dao.loan.*;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.loan.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.dao.sys.SysUserMapper;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.activiti.FlowInfo;
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.service.activiti.IProcessManagerService;
import com.company.platform.base.util.DataDictionaryUtil;
import com.company.platform.base.util.RoundUtil;
import com.company.platform.restapi.dao.modelmanager.BusinessModelMapper;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;
import com.company.platform.restapi.service.loan.ICorporationCustomerService;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.restapi.service.loan.IProductApplyInfoService;
import com.company.platform.restapi.validated.loan.LoanProductValidate;
import com.company.platform.security.model.SecurityUser;

/**
 * @ClassName: LoanProductApplyServiceImpl
 * @Description: TODO(申请提交处理)
 * @author yangxu
 * @date 2018年2月2日 上午10:33:08
 * 
 */
@Service
public class LoanProductApplyServiceImpl implements ILoanProductApplyService {
	private final Logger logger = LoggerFactory.getLogger(LoanProductApplyServiceImpl.class);

	@Resource
	DataDictionaryUtil dictUtil;

	@Autowired
	OneSetpApplyInfoMapper oneSetpApplyInfoMapper;

	@Autowired
	CustomerInfoMapper customerInfoMapper;

	@Autowired
	CustomerPublicInfoMapper customerPublicInfoMapper;

	@Autowired
	OrgProductRelationMapper orgProductRelationMapper;

	@Autowired
	OrgProductCreditMapper orgProductCreditMapper;

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	CustomerJobDetailMapper customerJobDetailMapper;

	@Autowired
	CustomerHouseInfoMapper customerHouseInfoMapper;

	@Autowired
	SelfEmployeeInfoMapper selfEmployeeInfoMapper;

	@Autowired
	CustomerVehicleInfoMapper customerVehicleInfoMapper;

	@Autowired
	CustomerAlterRecordMapper customerAlterRecordMapper;

	@Autowired
	private MerchantApplyMapper merchantApplyMapper;

	@Autowired
	private ProductApplyInfoMapper productApplyInfoMapper;

	@Autowired
	private LoanContractTemplateProductMapper loanContractTemplateProductMapper;

	@Autowired
	private LoanProductApplyContractMapper loanProductApplyContractMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private BusinessModelVersionAppMapper businessModelVersionAppMapper;

	@Resource
	private ICorporationCustomerService corporationCustomerService;

	@Resource
	private LoanApplyFeeServiceImpl loanApplyFeeServiceImpl;

	@Resource
	private IProcessManagerService processManagerService;

	@Autowired
	private BusinessModelMapper businessModelMapper;

	@Autowired
	private LoanProductValidate loanProductValidate;

	@Autowired
	private IProductApplyInfoService productApplyInfoService;

	@Autowired
	private ILoanProductApplyService loanProductApplyService;

	@Autowired
	private LoanProductApplyMapper loanProductApplyMapper;

	@Autowired
    private CustomerLocationInfoMapper customerLocationInfoMapper;

    @Autowired
    private ProductInfoMapper loanProductInfoMapper;

    @Autowired
    private CustomerInfoMapper customerPublicMapper;

    @Autowired
    private ITaskSetManagerService taskSetManagerService;
    @Autowired
    private LoanProductApplyModelMapper loanProductApplyModelVMapper;

	/*
	 * (非 Javadoc) <p>Title: saveOrUpdateLoanProductApply</p> <p>Description:
	 * </p>
	 * 
	 * @param loanProductApplyInfo
	 * 
	 * @param beforeApplyAmount
	 * 
	 * @param isStart
	 * 
	 * @param userId
	 * 
	 * @param nextTaskKey
	 * 
	 * @param auditUserId
	 * 
	 * @param audit
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanProductApplyService#
	 * saveOrUpdateLoanProductApply(com.company.platform.restapi.model.loan.
	 * FullLoanProductApplyInfo, java.math.BigDecimal, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void saveOrUpdateLoanProductApply(FullLoanProductApplyInfo loanProductApplyInfo,
			BigDecimal beforeApplyAmount, String isStart, String userId, String nextTaskKey, String auditUserId,
			String audit, String jsonData, List<GuarantorInfo> guarantor) throws BusinessException {

		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (StringUtils.isBlank(loanProductApplyInfo.getId())) {/* 申请id传null */
			logger.error("申请id传null，第二步提交无效！");
		} else {/* 借贷申请第二步 */
			String resultCode = null;// 流程执行返回结果code
			FullLoanProductApplyInfo temp = productApplyInfoMapper
					.getLoanProductApplyById(loanProductApplyInfo.getId());// 从数据库获得完整借贷申请信息
			if ("2".equals(isStart)) {// isStart=2审核
				/* 提交审核 */
				String processId = temp.getProcdefId();
				// 获得借贷产品
				LoanProductInfo loanProduct = productInfoMapper.queryByPrimaryId(temp.getLoanProductId());

				/* 校验是否为商户客户，初始化借贷商户信息 */
				if (temp.getMerchantState() != null && temp.getMerchantState().equals("1")) {
					MerchantApplyInfo merchantApply = new MerchantApplyInfo();
					merchantApply.setId(UUID.randomUUID().toString());
					merchantApply.setBussinessType("0");
					merchantApply.setBussinessId(loanProductApplyInfo.getId());
					merchantApply.setMerchantId(loanProductApplyInfo.getMerchantId());
					merchantApply.setOrgId(loanProductApplyInfo.getOrgId());
					merchantApply.setCustomerId(temp.getCustomerId());
					merchantApply.setRechargeType(loanProductApplyInfo.getMerchantChargeType());
					merchantApply.setApplyAmount(loanProductApplyInfo.getApplyAmount());
					merchantApply.setReplyAmount(loanProductApplyInfo.getApplyAmount());
					if ("0".equals(loanProductApplyInfo.getMerchantChargeType())) {
						if (loanProductApplyInfo.getMerchantChargeRate() != null) {
							merchantApply.setApplySettlementAmount(new BigDecimal(loanProductApplyInfo.getApplyAmount())
									.multiply(new BigDecimal(loanProductApplyInfo.getMerchantChargeRate()))
									.setScale(2, RoundUtil.getRound(dictUtil.getConfigData("ROUND"))).toString());
						} else {
							merchantApply.setApplySettlementAmount("0");
						}
					} else {
						merchantApply.setApplySettlementAmount(loanProductApplyInfo.getMerchantChargeAmount());
					}

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String applyTime = formatter.format(new Date());
					merchantApply.setApplyTime(applyTime);
					merchantApply.setSettlementState("0");
					merchantApply.setApplyState("0");
					merchantApplyMapper.insertMerchantApplyInfo(merchantApply);
				}

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String commitTime = formatter.format(new Date());// 生成提交、更新时间
				loanProductApplyInfo.setCommitTime(commitTime);
				loanProductApplyInfo.setUpdateTime(commitTime);

				/* 提交申请 */
				if (temp.getStatus().equals("0") || temp.getStatus().equals("4")) {// status=0流程未启动，status=4违规申请
					loanProductApplyInfo.setReplyAmount(loanProductApplyInfo.getApplyAmount());// 批复金额
					loanProductApplyInfo.setAuthTermCount(loanProductApplyInfo.getTermCount());// 批复期限
					loanProductApplyInfo.setAuthTermUnit(loanProductApplyInfo.getTermUnit());// 批复期限单位termUnit=1日；
																								// termUnit=3月
					/* 初始化借贷合同 */
					List<LoanProductApplyContractInfo> contracts = null;

					// 获得合同模板
					List<LoanContractTemplateProductInfo> templates = loanContractTemplateProductMapper
							.listLoanContractTemplateProductByProductId(temp.getLoanProductId());

					if (templates != null && templates.size() > 0) {
						contracts = new LinkedList<LoanProductApplyContractInfo>();
						for (LoanContractTemplateProductInfo template : templates) {
							LoanProductApplyContractInfo contract = new LoanProductApplyContractInfo();
							contract.setId(UUID.randomUUID().toString());
							contract.setLoanProductApplyId(loanProductApplyInfo.getId());
							contract.setTemplateName(template.getCtp_contract_template_name());
							contract.setTemplate_product_id(template.getCtp_id());
							contract.setSignStatus("0");
							contract.setOperUserId(userId);
							contract.setContractType("0");

							contracts.add(contract);
						}
					}
					if (contracts != null && contracts.size() > 0) {
						for (LoanProductApplyContractInfo contract : contracts) {
							loanProductApplyContractMapper.insertApplyContractInfo(contract);
						}
					} else {
						/* 如果没有合同，则更新借贷申请状态为已签定 */
						temp.setActionStatus("1");
					}
				}

//start
				if (StringUtils.isBlank(processId)) {// 流程实例id为null，启动新流程
					try {
						// 流程启动
						processId = processManagerService.startProcess(loanProduct.getId(), loanProduct.getProcdefKey(),
								loanProductApplyInfo.getId(), user.getId(), user.getRealName(), user.getId(),
								user.getRealName());
						// 保存流程实例id
						temp.setProcdefId(processId);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("9999", "流程启动失败" + loanProductApplyInfo.getCode() + "！");
					}
				}
				if (StringUtils.isNotBlank(nextTaskKey)) {// 流程指向下一个节点key，用来获得此流程节点待办人配置
					// 通过待办人获得任务id
					String taskId = processManagerService.getUserProcessInstanceTaskId(processId, user.getId(),
							user.getRealName());
					if (taskId != null) {// 任务id不能为null
						Map<String, Object> variables = new HashMap<String, Object>();
						// 获得启动流程节点所有流程配置（流程首个节点配置）
						FlowInfo flowInfo = processManagerService.getStartFlowInfo(loanProduct.getId(),
								loanProduct.getProcdefKey());
						// 获得流程提交按钮
						List<FunctionInfo> functions = flowInfo.getFunctionsByParams(new HashMap<String, Object>());
						Map<String, Object> localVariables = null;
						for (FunctionInfo function : functions) {
							if (nextTaskKey.equals(function.getNextTaskKey())) {
								if (function.getVariables() != null) {
									variables.putAll(function.getVariables());
								}
								localVariables = new HashMap<String, Object>();
								boolean noticeFlag = function.isNoticeFlag();
								localVariables.put("noticeFlag", noticeFlag);
								localVariables.put("noticeTemp", function.getNoticeTemp());
								break;
							}
						}
						String realName = null;
						if (!StringUtils.isBlank(userId)) {
							realName = user.getRealName();
						}
						variables.put("bussinessId", loanProductApplyInfo.getId());

						String auditUserName = null;

						if (StringUtils.isNotBlank(auditUserId)) {// 下一步审核人
							Map<String, Object> userInfo = sysUserMapper.getUserInfoById(auditUserId);
							auditUserName = (String) userInfo.get("realName");// 下一步审核人真是姓名
						} else {
							auditUserId = null;// 领用或退回节点提交，此处审核人id为null
						}
						variables.put("orgId", loanProductApplyInfo.getOrgId());
						// 提交当前流程任务，并返回操作结果代码
						resultCode = processManagerService.complete(taskId, processId, loanProduct.getId(),
								loanProductApplyInfo.getId(), "", userId, realName, variables, localVariables, "Y",
								nextTaskKey, audit, "loan", auditUserId, auditUserName);
						if (!"0000".equals(resultCode) && !"9999".equals(resultCode) && !"9000".equals(resultCode)) {
							throw new BusinessException(resultCode, "流程执行失败" + loanProductApplyInfo.getCode() + "！");
						}
					}
				}
//end
			} else {
				loanProductApplyInfo.setStatus(temp.getStatus());
			}
			/* 处理借贷申请基本信息 */
			loanProductApplyInfo.setActionStatus(temp.getActionStatus());
			loanProductApplyInfo.setProcdefId(temp.getProcdefId());
			if (temp.getStatus().equals("1")) {
				loanProductApplyInfo.setCommitTime(temp.getCommitTime());
			}
			loanProductApplyInfo
					.setInterestRate(new BigDecimal(loanProductApplyInfo.getInterestRate()).setScale(4).toString());
			loanProductApplyInfo
					.setPenaltyRate(new BigDecimal(loanProductApplyInfo.getPenaltyRate()).setScale(4).toString());
			if (StringUtils.isNotBlank(loanProductApplyInfo.getMerchantId())) {
				if (loanProductApplyInfo.getMerchantChargeType().equals("0")) {
					loanProductApplyInfo.setMerchantChargeAmount(null);
					loanProductApplyInfo.setMerchantChargeRate(loanProductApplyInfo.getMerchantChargeRate() == null
							? null
							: (new BigDecimal(loanProductApplyInfo.getMerchantChargeRate()).setScale(4)).toString());
					loanProductApplyInfo.setReplyMerchantChargeRate(loanProductApplyInfo.getMerchantChargeRate());
				} else {
					loanProductApplyInfo.setMerchantChargeRate(null);
					loanProductApplyInfo.setReplyMerchantChargeAmount(loanProductApplyInfo.getMerchantChargeAmount());
				}
			}
//			String resultCode="0000";
			if ("0000".equals(resultCode)) {
				loanProductApplyInfo.setStatus("1");// 0000流程正常启动，状态改成审核中
			}
			if ("9000".equals(resultCode)) {
				loanProductApplyInfo.setStatus("3");// 9000流程执行结束，执行拒绝，状态改成审核拒绝
			}
			if ("9999".equals(resultCode)) {
				loanProductApplyInfo.setStatus("2");// 9999流程执行结束，状态改成审核成功
				loanProductApplyInfo.setActionStatus("1");// 合同签订状态改成已签订
				loanProductApplyInfo.setTaskType("4");// taskType=4 已签订合同
			}

			// 更新借贷申请信息
			productApplyInfoMapper.updateLoanProductApplyInfo(loanProductApplyInfo);

			/* 处理费用信息 */
			LoanProductApplyEntity beforeApply = new LoanProductApplyEntity();
			BeanUtils.copyProperties(temp, beforeApply);
			LoanProductApplyInfo apply = new LoanProductApplyInfo();
			BeanUtils.copyProperties(loanProductApplyInfo, apply);
			loanApplyFeeServiceImpl.dealLoanApplyFeeInfo(beforeApply, apply);
			// 保存业务模型信息
			Map<String, Object> recordMap = loanProductApplyService.selectBusModelRecord(apply.getId());
			if (StringUtils.isNotEmpty(jsonData)) {
				boolean flag = loanProductValidate.modelDataValidate(jsonData, loanProductApplyInfo.getLoanProductId(),
						apply.getId(), "online");
				if (flag) {// 关联业务模型
					// 保存业务模型信息
					JSONObject modelDataJson = JSONObject.parseObject(jsonData);
					if (recordMap != null && recordMap.size() > 0) {// 有记录，更新
						loanProductApplyService.modifyModelData(modelDataJson, recordMap.get("id").toString());
					} else {// 新建
						// 保存业务模型数据，并返回业务模型数据记录id
						String recordId = loanProductApplyService.saveModelData(apply.getId(), modelDataJson,
								"借贷申请提交审核，保存业务模型数据");
						if (StringUtils.isNotBlank(recordId)) {
							String modelId = modelDataJson.getString("id");
							// 保存借贷申请与业务模型数据关联，为之后可以通过借贷申请找到对应的业务模型数据
							loanProductApplyService.saveLoanApplyModelS(apply.getId(), modelId, "BUM", recordId);
						}
					}
				}
			} else {// 清空模型中所有数据为null
				if (recordMap != null && recordMap.size() > 0) {
					loanProductApplyService.emptyModelData(recordMap.get("id").toString());
				}
			}
			// 保存担保人信息
			productApplyInfoService.saveLoanGuarantorInfo(guarantor, apply.getId());
		}
	}

	/*
	 * (非 Javadoc) <p>Title: updateLoanProductApply</p> <p>Description: </p>
	 * 
	 * @param loanProductApply
	 * 
	 * @param modelData
	 * 
	 * @param authsMap
	 * 
	 * @param beforeApplyAmount
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanProductApplyService#
	 * updateLoanProductApply(com.company.platform.restapi.model.loan.
	 * FullLoanProductApplyInfo, java.lang.String,
	 * com.alibaba.fastjson.JSONObject,
	 * com.company.platform.restapi.model.loan.LoanReqFormModel, java.util.Map,
	 * java.math.BigDecimal)
	 */
	public void updateLoanProductApply(FullLoanProductApplyInfo loanProductApply, String modelData,
			Map<String, String> authsMap, BigDecimal beforeApplyAmount, List<GuarantorInfo> guarantor)
			throws BusinessException {
		FullLoanProductApplyInfo temp = new FullLoanProductApplyInfo();
		if ("edit".equals(authsMap.get("8"))) {/* 借贷信息可编辑 */
			/* 处理借贷基本信息 */
			temp = productApplyInfoMapper.getLoanProductApplyById(loanProductApply.getId());
			// 完善数据
			loanProductApply.setActionStatus(temp.getActionStatus());
			loanProductApply.setTaskType(temp.getTaskType());
			loanProductApply.setStatus(temp.getStatus());
			loanProductApply.setCommitTime(temp.getCommitTime());
			loanProductApply.setProcdefId(temp.getProcdefId());
			loanProductApply.setInterestRate(new BigDecimal(loanProductApply.getInterestRate()).setScale(4).toString());
			loanProductApply.setPenaltyRate(new BigDecimal(loanProductApply.getPenaltyRate()).setScale(4).toString());
			if (temp.getMerchantState().equals("1")) {
				if (loanProductApply.getMerchantChargeType().equals("0")) {
					loanProductApply.setMerchantChargeRate(loanProductApply.getMerchantChargeRate() == null ? null
							: new BigDecimal(loanProductApply.getMerchantChargeRate()).setScale(4).toString());
					loanProductApply.setReplyMerchantChargeRate(loanProductApply.getReplyMerchantChargeRate() == null
							? null
							: new BigDecimal(loanProductApply.getReplyMerchantChargeRate()).setScale(4).toString());
					loanProductApply.setMerchantChargeAmount(null);
					loanProductApply.setReplyMerchantChargeAmount(null);
				} else {
					loanProductApply.setMerchantChargeRate(null);
					loanProductApply.setReplyMerchantChargeRate(null);
				}
				/* 更新商户申请信息 */
				MerchantApplyInfo merchantApply = merchantApplyMapper.getMerchantApplyByBusiness("0",
						loanProductApply.getId());
				if (merchantApply != null) {
					BigDecimal merchantAmount = null;// 商户收款金额
					if (loanProductApply.getMerchantChargeType().equals("0")) {
						if (loanProductApply.getReplyMerchantChargeRate() != null && BigDecimal.ZERO
								.compareTo(new BigDecimal(loanProductApply.getReplyMerchantChargeRate())) < 0) {
							merchantAmount = new BigDecimal(loanProductApply.getReplyAmount())
									.multiply(new BigDecimal(loanProductApply.getReplyMerchantChargeRate()))
									.setScale(2, RoundUtil.getRound(dictUtil.getConfigData("ROUND")));
						}
					} else {
						if (loanProductApply.getReplyMerchantChargeAmount() != null && BigDecimal.ZERO
								.compareTo(new BigDecimal(loanProductApply.getReplyMerchantChargeAmount())) < 0) {
							merchantAmount = new BigDecimal(loanProductApply.getReplyMerchantChargeAmount());
						}
					}
					merchantApply.setReplyAmount(loanProductApply.getReplyAmount());
					merchantApply.setReplySettlementAmount(merchantAmount.toString());
					merchantApplyMapper.updateMerchantApplyInfo(merchantApply);
				}
			}
			productApplyInfoMapper.updateLoanProductApplyInfo(loanProductApply);
		}
		if ("edit".equals(authsMap.get("11")) || "readOnly".equals(authsMap.get("11"))) {
			/* 处理费用信息 */
			LoanProductApplyEntity beforeApply = new LoanProductApplyEntity();
			BeanUtils.copyProperties(temp, beforeApply);
			LoanProductApplyInfo apply = new LoanProductApplyInfo();
			BeanUtils.copyProperties(loanProductApply, apply);
			loanApplyFeeServiceImpl.dealLoanApplyFeeInfo(beforeApply, apply);
		}

		if ("edit".equals(authsMap.get("10"))) {/* 担保人可编辑 */
			productApplyInfoService.saveLoanGuarantorInfo(guarantor, loanProductApply.getId());
		}

		if ("edit".equals(authsMap.get("1"))) {/* 业务模型可编辑 */
			Map<String, Object> recordMap = this.selectBusModelRecord(loanProductApply.getId());
			if (StringUtils.isNotEmpty(modelData)) {
				boolean flag = loanProductValidate.modelDataValidate(modelData, loanProductApply.getLoanProductId(),
						loanProductApply.getId(), "online");
				if (flag) {// 关联业务模型
					// 保存业务模型信息
					JSONObject modelDataJson = JSONObject.parseObject(modelData);
					if (recordMap != null && recordMap.size() > 0) {// 有记录，更新
						this.modifyModelData(modelDataJson, recordMap.get("id").toString());
					} else {// 新建
						// 保存业务模型数据，并返回业务模型数据记录id
						String recordId = this.saveModelData(loanProductApply.getId(), modelDataJson,
								"借贷申请提交审核，保存业务模型数据");
						if (StringUtils.isNotBlank(recordId)) {
							String modelId = modelDataJson.getString("id");
							// 保存借贷申请与业务模型数据关联，为之后可以通过借贷申请找到对应的业务模型数据
							this.saveLoanApplyModelS(loanProductApply.getId(), modelId, "BUM", recordId);
						}
					}
				}
			} else {// 清空模型中所有数据为null
				if (recordMap != null && recordMap.size() > 0) {
					this.emptyModelData(recordMap.get("id").toString());
				}
			}
		}
	}

	public boolean updateModelData(JSONObject data, String code, String version, String businessId) {
		Boolean validate = data.getBoolean("validate");
		if (validate) {
			boolean handelRst = false;
			// 通过code和version获取modelId
			String busmodId = businessModelVersionAppMapper.getModelIdByCodeAndVersion(code, version);
			// 通过modelId和business获取recordId
			Map<String, Object> record = businessModelVersionAppMapper.getRecordIdByModelIdAndBusinessId(businessId,
					busmodId);
			if (record == null)
				return false;
			String modelId = busmodId;
			String recordId = record.get("id").toString();
			JSONObject formData = data.getJSONObject("data");
			handelRst = updateModelData(modelId, recordId, formData);
			return handelRst;
		} else {
			return false;
		}
	}

	public boolean updateModelData(String modelId, String recordId, JSONObject formData) {
		if (formData.size() == 0) {
			return true;
		} else {
			Map<String, Object> record = businessModelVersionAppMapper.findRecordById(recordId);
			Date createDate = (Date) record.get("createDate");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createDateString = formatter.format(createDate);
			List<Map<String, Object>> params = businessModelVersionAppMapper.findParamsLogicByModelId(modelId,
					createDateString);
			int j = 0;
			for (Map<String, Object> param : params) {
				String code = (String) param.get("code");
				if (formData.containsKey(code)) {
					String data = formData.getString(code);
					int i = businessModelVersionAppMapper.updateModelData(recordId, code, data);
					if (i > 0) {
						j++;
					}
				}
			}
			return j > 0;
		}
	}

	/**
	 * 保存业务数据
	 */
	public String saveModelData(String businessId, JSONObject jsonData, String explain) {
		Boolean validate = jsonData.getBoolean("validate");
		String dataId = null;
		if (validate) {
			Date now = new Date();
			String modelId = jsonData.getString("id");
			JSONObject formData = jsonData.getJSONObject("data");
			dataId = saveModelData(businessId, modelId, now, explain, formData);
		}
		return dataId;
	}

	public String saveModelData(String businessId, String modelId, Date createDate, String functionExplain,
			JSONObject formData) {
		String dataId = UUID.randomUUID().toString();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDateString = formatter.format(createDate);
		int i = businessModelVersionAppMapper.saveModelRecord(dataId, businessId, modelId, createDateString,
				functionExplain);
		if (i > 0) {
			List<Map<String, Object>> params = businessModelVersionAppMapper.findParamsByModelId(modelId);
			for (Map<String, Object> param : params) {
				String code = (String) param.get("code");
				String inputType = (String) param.get("inputType");
				String data = formData.getString(code);
				businessModelVersionAppMapper.saveModelData(dataId, inputType, code, data);
			}
		} else {
			dataId = null;
		}
		return dataId;
	}

	/** 
	* @Title: insertOffLineLoanProductApply 
	* @Description: TODO(新建离线转在线借贷信息) 
	* @param @param loanProductApplyInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	@Override
	public int insertOffLineLoanProductApply(LoanProductApplyEntity loanProductApplyInfo) {
		logger.info("新建离线转在线借贷信息");
		return productApplyInfoMapper.insertOffLineLoanProductApply(loanProductApplyInfo);
	}

	@Override
	public Map<String, Object> selectBusModelRecord(String businessId) throws BusinessException {
		return productApplyInfoMapper.selectBusModelRecord(businessId);
	}

	@Override
	public boolean modifyModelData(JSONObject data, String recordId) {
		Boolean validate = data.getBoolean("validate");
		if (validate) {
			boolean handelRst = false;
			String modelId = data.getString("id");
			JSONObject formData = data.getJSONObject("data");
			handelRst = updateModelData(modelId, recordId, formData);
			return handelRst;
		} else {
			return false;
		}
	}

	@Override
	public int saveLoanApplyModelS(String loanProductApplyId, String modelId, String modelType, String recordId) {
		Map<String, Object> busModel = businessModelMapper.getBusModelById(modelId);
		if (busModel != null) {
			return productApplyInfoMapper.saveLoanApplyModelS(loanProductApplyId, (String) busModel.get("code"),
					(String) busModel.get("version"), modelType, recordId);
		}
		return 0;
	}

	@Override
	public int saveLoanApplyImgModelS(String loanProductApplyId, String modelId, String modelType, String recordId)
			throws BusinessException {
		Map<String, Object> imgModel = productApplyInfoMapper.getImgModelById(modelId);
		if (imgModel != null) {
			return productApplyInfoMapper.saveLoanApplyModelS(loanProductApplyId, (String) imgModel.get("code"),
					(String) imgModel.get("version"), modelType, recordId);
		}
		return 0;
	}

	@Override
	public String saveImgModelRecord(String loanApplyId, String imgModelId, String functionExplain)
			throws BusinessException {
		String id = UUID.randomUUID().toString();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDateString = formatter.format(new Date());
		productApplyInfoMapper.saveImgModelRecord(id, loanApplyId, imgModelId, createDateString, functionExplain);
		return id;
	}

	@Override
	public int modifyUpdateTime(String loanProductApplyId) {
		return productApplyInfoMapper.modifyUpdateTime(loanProductApplyId);
	}

	@Override
	public int emptyModelData(String recordId) {
		return businessModelMapper.emptyModelData(recordId);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 获取拒绝原因（人工审核原因，该原因只针对于核验结果）
	 * @Date 21:22 2018/9/13
	 * @Param [loanProductApplyId]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@Override
	public Map<String, Object> getApprovalLog(String loanProductApplyId) {
		return loanProductApplyMapper.getApprovalLogMessage(loanProductApplyId);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 通过借贷申请id查询借贷申请信息
	 * @Date 11:04 2018/9/14
	 * @Param [loanProductApplyId]
	 * @return com.company.platform.restapi.model.loan.LoanProductApply
	 **/
	@Override
	public LoanProductApply selectLoanProductApplyById(String loanProductApplyId) {
		return loanProductApplyMapper.selectById(loanProductApplyId);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 删除未提交进件信息
	 * @Date 11:06 2018/9/14
	 * @Param [loanProductApplyId]
	 * @return int
	 **/
	@Override
	public boolean deleteApply(String loanProductApplyId) {
		return  1 == loanProductApplyMapper.deleteById(loanProductApplyId)? true : false;
	}
}



