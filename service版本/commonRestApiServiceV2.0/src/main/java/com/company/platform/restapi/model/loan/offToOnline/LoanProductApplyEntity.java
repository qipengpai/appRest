package com.company.platform.restapi.model.loan.offToOnline;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: LoanProductApplyEntity 
* @Description: TODO(借款申请的信息详情) 
* @author 王雪 
* @date 2018年2月5日 下午2:23:12 
*  
*/
@SuppressWarnings("all")
public class LoanProductApplyEntity extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/** 
	* @Fields loanProductId : TODO(产品id) 
	*/
	@NotEmpty(message = "产品id不能为空")
	private String loanProductId;

	/** 
	* @Fields applyAmount : TODO(借贷金额)
	*/
	@NotEmpty(message = "申请金额不能为空")
	private String applyAmount;

	/** 
	* @Fields termCount : TODO(借贷期限) 
	*/
	@NotEmpty(message = "借贷期限不能为空")
	private String termCount;

	/** 
	* @Fields termUnit : TODO(借贷期限单位) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "termUnit")
	@NotEmpty(message = "借贷期限单位不能为空")
	private String termUnit;

	/** 
	* @Fields repayTermCount : TODO(还款间隔期限) 
	*/
	private String repayTermCount;

	/** 
	* @Fields repayTermUnit : TODO(还款间隔期限单位) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "repayTermUnit")
	private String repayTermUnit;

	/** 
	* @Fields repayType : TODO(还款方式) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "repayType")
	private String repayType;

	/** 
	* @Fields applyTime : TODO(申请时间) 
	*/
	@NotEmpty(message = "申请时间不能为空")
	private String applyTime;

	/** 
	* @Fields interestRate : TODO(年化利率) 
	*/
	private String interestRate;

	/** 
	* @Fields penaltyRate : TODO(罚息利率) 
	*/
	private String penaltyRate;

	/** 
	* @Fields guaranteeType : TODO(担保方式) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "guaranteeType")
	private String guaranteeType;

	/** 
	* @Fields useType : TODO(借款用途) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "useType")
	private String useType;

	/** 
	* @Fields intoPieceType : TODO(进件类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "intoPieceType")
	private String intoPieceType;

	/** 
	* @Fields ownContract : TODO(是否自带合同) 
	*/
	private String ownContract;

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
	@NotEmpty(message = "登记日期不能为空")
	private String registerTime;

	/** 
	* @Fields loanAccount : TODO(放款账户名) 
	*/
	private String loanAccount;

	/** 
	* @Fields loanCardNo : TODO(放款账号/卡号) 
	*/
	private String loanCardNo;

	/** 
	* @Fields loanBank : TODO(放款开户银行) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "bank")
	private String loanBank;

	/** 
	* @Fields loanOrg : TODO(放款开户机构) 
	*/
	private String loanOrg;

	/** 
	* @Fields repaymentAccount : TODO(还款账户名) 
	*/
	private String repaymentAccount;

	/** 
	* @Fields repaymentCardNo : TODO(还款账号/卡号) 
	*/
	private String repaymentCardNo;

	/** 
	* @Fields repaymentBank : TODO(还款开户银行) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "bank")
	private String repaymentBank;

	/** 
	* @Fields repaymentOrg : TODO(还款开户机构) 
	*/
	private String repaymentOrg;

	/** 
	* @Fields supplementMsg : TODO(补充信息) 
	*/
	private String supplementMsg;

	/** 
	* @Fields merchantState : TODO(是否为商户进件) 
	*/
	private String merchantState;

	/** 
	* @Fields merchantId : TODO(商户id) 
	*/
	private String merchantId;

	/** 
	* @Fields merchantChargeType : TODO(商户收款类型) 
	*/
	private String merchantChargeType;

	/** 
	* @Fields merchantChargeRate : TODO(商户收取比例) 
	*/
	private String merchantChargeRate;

	/** 
	* @Fields merchantChargeAmount : TODO(商户收取固定金额) 
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
	* @Fields updateTime : TODO(产品更新时间) 
	*/
	@NotEmpty(message = "产品更新时间不能为空")
	private String updateTime;

	/** 
	* @Fields code : TODO(编码) 
	*/
	private String code;

	/** 
	* @Fields customerId : TODO(申请借款客户id) 
	*/
	private String customerId;

	/** 
	* @Fields orgId : TODO(客户经理所在机构id) 
	*/
	private String orgId;

	/** 
	* @Fields downPaymentsAmount : TODO(首付金额) 
	*/
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

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
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

	public String getOwnContract() {
		return ownContract;
	}

	public void setOwnContract(String ownContract) {
		this.ownContract = ownContract;
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

	public String getSupplementMsg() {
		return supplementMsg;
	}

	public void setSupplementMsg(String supplementMsg) {
		this.supplementMsg = supplementMsg;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDownPaymentsAmount() {
		return downPaymentsAmount;
	}

	public void setDownPaymentsAmount(String downPaymentsAmount) {
		this.downPaymentsAmount = downPaymentsAmount;
	}

}
