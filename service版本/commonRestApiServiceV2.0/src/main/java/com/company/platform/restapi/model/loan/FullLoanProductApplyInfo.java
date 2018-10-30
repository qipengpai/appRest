package com.company.platform.restapi.model.loan;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.decimalValidated.DecimalFormat;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * @ClassName: LoanProductApplyInfo
 * @Description: TODO(借款申请的信息详情)
 * @author lifeng
 * @date 2018年1月30日 下午4:32:43
 * 
 */
@SuppressWarnings("all")
public class FullLoanProductApplyInfo extends BaseModel {

	/**useType
	 * @Fields id : TODO(借贷申请id)--
	 */
	//@NotEmpty(message = "借贷申请id不能为空")
	private String id;
	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	//@NotEmpty(message = "借贷产品id不能为空")
	private String loanProductId;
	/**
	 * @Fields customerId : TODO(申请借款客户id)
	 */
	//@NotEmpty(message = "客户id不能为空")
	private String customerId;
	/**
	 * @Fields code : TODO(编码)
	 */
	//@NotEmpty(message = "借贷申请编码不能为空")
	private String code;
	/**
	 * @Fields applyAmount : TODO(借贷金额)--
	 */
	//@DecimalFormat(format = "14,2", var = "借贷金额")
	//@NotEmpty(message = "借贷金额不能为空")
	private String applyAmount;
	/**
	 * @Fields replyAmount : TODO(批复金额)
	 */
	//@DecimalFormat(format = "14,2", var = "批复金额")
	private String replyAmount;
	/**
	 * @Fields termCount : TODO(借贷期限)--
	 */
	//@NotEmpty(message = "借贷期限不能为空")
	private String termCount;
	/**
	 * @Fields termUnit : TODO(借贷期限单位)--
	 */
	//@DictionaryStandardOrNot(dictionaryType = "termUnit")
	//@NotEmpty(message = "借贷期限单位不能为空")
	private String termUnit;
	/**
	 * @Fields authTermCount : TODO(批准期限)
	 */
	private String authTermCount;
	/**
	 * @Fields authTermUnit : TODO(批准期限单位)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "termUnit")
	private String authTermUnit;
	/**
	 * @Fields repayTermCount : TODO(还款间隔期限)--
	 */
	//@NotEmpty(message = "还款间隔期限不能为空")
	private String repayTermCount;
	/**
	 * @Fields repayTermUnit : TODO(还款间隔期限单位)--
	 */
	//@DictionaryStandardOrNot(dictionaryType = "repayTermUnit")
	//@NotEmpty(message = "还款间隔期限单位不能为空")
	private String repayTermUnit;
	/**
	 * @Fields repayType : TODO(还款方式)--
	 */
	//@DictionaryStandardOrNot(dictionaryType = "repayType")
	//@NotEmpty(message = "还款方式不能为空")
	private String repayType;

	/**
	 * @Fields repayType : TODO(月还款额)
	 */
	private String monthlyPayments;
	
	/**
	 * @Fields applyTime : TODO(申请时间)
	 */
	// @NotEmpty(message = "申请时间不能为null")
	private String applyTime;
	/**
	 * @Fields interestRate : TODO(年化利率)--
	 */
	//@DecimalFormat(format = "4,2", var = "年化利率")
	//@NotEmpty(message = "年化利率不能为空")
	private String interestRate;
	/**
	 * @Fields penaltyRate : TODO(罚息利率)--
	 */
	//@DecimalFormat(format = "4,2", var = "罚息利率")
	//@NotEmpty(message = "罚息利率不能为空")
	private String penaltyRate;
	/**
	 * @Fields guaranteeType : TODO(担保方式)--
	 */
	//@DictionaryStandardOrNot(dictionaryType = "guaranteeType")
	//@NotEmpty(message = "担保方式不能为空")
	private String guaranteeType;
	/**
	 * @Fields useType : TODO(借款用途)--
	 */
	@DictionaryStandardOrNot(dictionaryType = "useType")
	private String useType;
	/**
	 * @Fields intoPieceType : TODO(进件类型)--
	 */
	@DictionaryStandardOrNot(dictionaryType = "intoPieceType")
	private String intoPieceType;
	/**
	 * @Fields productType : TODO(产品分类)
	 */
	@DictionaryStandardOrNot(dictionaryType = "productType")
	private String productType;
	/**
	 * @Fields ownContract : TODO(是否自带合同)--
	 */
	private String ownContract;
	/**
	 * @Fields status : TODO(状态)
	 */
	private String status;
	/**
	 * @Fields actionStatus : TODO(合同状态)
	 */
	private String actionStatus;
	/**
	 * @Fields accountManagerId : TODO(客户经理id)
	 */
	private String accountManagerId;
	/**
	 * @Fields registerId : TODO(登记人员)
	 */
	private String registerId;
	/**
	 * @Fields registerTime : TODO(登记日期，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String registerTime;
	/**
	 * @Fields intoPieceNo : TODO(进件编号)
	 */
	private String intoPieceNo;
	/**
	 * @Fields loanAccount : TODO(放款账户名)--
	 */
	@Length(max = 64, message = "放款账户名最多64位字符")
	private String loanAccount;
	/**
	 * @Fields loanCardNo : TODO(放款账号/卡号)--
	 */
	@Length(max = 32, message = "放款账号/卡号最多32位字符")
	private String loanCardNo;
	/**
	 * @Fields loanBank : TODO(放款开户银行)--
	 */
	@DictionaryStandardOrNot(dictionaryType = "bank")
	private String loanBank;
	/**
	 * @Fields loanOrg : TODO(放款开户机构)--
	 */
	private String loanOrg;
	/**
	 * @Fields repaymentAccount : TODO(还款账户名)--
	 */
	@Length(max = 64, message = "还款账户名最多64位字符")
	private String repaymentAccount;
	/**
	 * @Fields repaymentCardNo : TODO(还款账号/卡号)--
	 */
	@Length(max = 32, message = "还款账号/卡号最多32位字符")
	private String repaymentCardNo;
	/**
	 * @Fields repaymentBank : TODO(还款开户银行)--
	 */
	@DictionaryStandardOrNot(dictionaryType = "bank")
	private String repaymentBank;
	/**
	 * @Fields repaymentOrg : TODO(还款开户机构)--
	 */
	private String repaymentOrg;
	/**
	 * @Fields commitTime : TODO(提交时间)
	 */
	private String commitTime;
	/**
	 * @Fields supplementMsg : TODO(补充信息)--
	 */
	@Length(max = 256, message = "补充信息最多256位字符")
	private String supplementMsg;
	/**
	 * @Fields procdefId : TODO(流程id)
	 */
	private String procdefId;
	/**
	 * @Fields orgId : TODO(客户经理所在机构id)
	 */
	private String orgId;
	/**
	 * @Fields releaseType : TODO(放款类型)
	 */
	private String releaseType;
	/**
	 * @Fields releaseDate : TODO(放款时间)
	 */
	private String releaseDate;
	/**
	 * @Fields taskType : TODO(任务类型)
	 */
	@DictionaryStandardOrNot(dictionaryType = "taskType")
	private String taskType;
	/**
	 * @Fields merchantState : TODO(是否为商户进件)
	 */
	private String merchantState;
	/**
	 * @Fields merchantId : TODO(商户id)--
	 */
	private String merchantId;
	/**
	 * @Fields merchantChargeType : TODO(商户收款类型)--
	 */
	private String merchantChargeType;
	/**
	 * @Fields merchantChargeRate : TODO(商户收取比例)--
	 */
	private String merchantChargeRate;
	/**
	 * @Fields merchantChargeAmount : TODO(商户收取固定金额)--
	 */
	private String merchantChargeAmount;
	/**
	 * @Fields replyMerchantChargeRate : TODO(批复商户收取比例)
	 */
	private String replyMerchantChargeRate;
	/**
	 * @Fields replyMerchantChargeAmount : TODO(批复商户收取固定金额)
	 */
	private String replyMerchantChargeAmount;
	/**
	 * @Fields otherChannels : TODO(其他渠道借款信息)
	 */
	private String otherChannels;
	/**
	 * @Fields callRecordOrderId : TODO(运营商授权验证订单号)
	 */
	private String callRecordOrderId;
	/**
	 * @Fields updateTime : TODO(更新时间)
	 */
	private String updateTime;
	/** 
	* @Fields downPaymentsAmount : TODO(首付金额) 
	*/
	@DecimalFormat(format = "14,2", var = "首付金额")
	private String downPaymentsAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(String replyAmount) {
		this.replyAmount = replyAmount;
	}

	public String getTermCount() {
		return termCount;
	}

	public void setTermCount(String termCount) {
		this.termCount = termCount;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getAuthTermCount() {
		return authTermCount;
	}

	public void setAuthTermCount(String authTermCount) {
		this.authTermCount = authTermCount;
	}

	public String getAuthTermUnit() {
		return authTermUnit;
	}

	public void setAuthTermUnit(String authTermUnit) {
		this.authTermUnit = authTermUnit;
	}

	public String getRepayTermCount() {
		return repayTermCount;
	}

	public void setRepayTermCount(String repayTermCount) {
		this.repayTermCount = repayTermCount;
	}

	public String getRepayTermUnit() {
		return repayTermUnit;
	}

	public void setRepayTermUnit(String repayTermUnit) {
		this.repayTermUnit = repayTermUnit;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getIntoPieceType() {
		return intoPieceType;
	}

	public void setIntoPieceType(String intoPieceType) {
		this.intoPieceType = intoPieceType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getOwnContract() {
		return ownContract;
	}

	public void setOwnContract(String ownContract) {
		this.ownContract = ownContract;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getAccountManagerId() {
		return accountManagerId;
	}

	public void setAccountManagerId(String accountManagerId) {
		this.accountManagerId = accountManagerId;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getIntoPieceNo() {
		return intoPieceNo;
	}

	public void setIntoPieceNo(String intoPieceNo) {
		this.intoPieceNo = intoPieceNo;
	}

	public String getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}

	public String getLoanCardNo() {
		return loanCardNo;
	}

	public void setLoanCardNo(String loanCardNo) {
		this.loanCardNo = loanCardNo;
	}

	public String getLoanBank() {
		return loanBank;
	}

	public void setLoanBank(String loanBank) {
		this.loanBank = loanBank;
	}

	public String getLoanOrg() {
		return loanOrg;
	}

	public void setLoanOrg(String loanOrg) {
		this.loanOrg = loanOrg;
	}

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public String getRepaymentCardNo() {
		return repaymentCardNo;
	}

	public void setRepaymentCardNo(String repaymentCardNo) {
		this.repaymentCardNo = repaymentCardNo;
	}

	public String getRepaymentBank() {
		return repaymentBank;
	}

	public void setRepaymentBank(String repaymentBank) {
		this.repaymentBank = repaymentBank;
	}

	public String getRepaymentOrg() {
		return repaymentOrg;
	}

	public void setRepaymentOrg(String repaymentOrg) {
		this.repaymentOrg = repaymentOrg;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getSupplementMsg() {
		return supplementMsg;
	}

	public void setSupplementMsg(String supplementMsg) {
		this.supplementMsg = supplementMsg;
	}

	public String getProcdefId() {
		return procdefId;
	}

	public void setProcdefId(String procdefId) {
		this.procdefId = procdefId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getReleaseType() {
		return releaseType;
	}

	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getMerchantState() {
		return merchantState;
	}

	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantChargeType() {
		return merchantChargeType;
	}

	public void setMerchantChargeType(String merchantChargeType) {
		this.merchantChargeType = merchantChargeType;
	}

	public String getMerchantChargeRate() {
		return merchantChargeRate;
	}

	public void setMerchantChargeRate(String merchantChargeRate) {
		this.merchantChargeRate = merchantChargeRate;
	}

	public String getMerchantChargeAmount() {
		return merchantChargeAmount;
	}

	public void setMerchantChargeAmount(String merchantChargeAmount) {
		this.merchantChargeAmount = merchantChargeAmount;
	}

	public String getReplyMerchantChargeRate() {
		return replyMerchantChargeRate;
	}

	public void setReplyMerchantChargeRate(String replyMerchantChargeRate) {
		this.replyMerchantChargeRate = replyMerchantChargeRate;
	}

	public String getReplyMerchantChargeAmount() {
		return replyMerchantChargeAmount;
	}

	public void setReplyMerchantChargeAmount(String replyMerchantChargeAmount) {
		this.replyMerchantChargeAmount = replyMerchantChargeAmount;
	}

	public String getOtherChannels() {
		return otherChannels;
	}

	public void setOtherChannels(String otherChannels) {
		this.otherChannels = otherChannels;
	}

	public String getCallRecordOrderId() {
		return callRecordOrderId;
	}

	public void setCallRecordOrderId(String callRecordOrderId) {
		this.callRecordOrderId = callRecordOrderId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDownPaymentsAmount() {
		return downPaymentsAmount;
	}

	public void setDownPaymentsAmount(String downPaymentsAmount) {
		this.downPaymentsAmount = downPaymentsAmount;
	}
}
