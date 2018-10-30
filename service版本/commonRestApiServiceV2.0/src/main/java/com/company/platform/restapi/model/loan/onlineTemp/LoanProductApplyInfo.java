package com.company.platform.restapi.model.loan.onlineTemp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.decimalValidated.DecimalFormat;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * 
* @ClassName: LoanProductApplyInfo 
* @Description: TODO(借贷产品申请信息) 
* @author 王雪 
* @date 2018年3月9日 上午9:27:20 
*
 */
@SuppressWarnings("all")
public class LoanProductApplyInfo extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	//@NotEmpty(message = "主键id不能为空")
	private String id;
	/**
	 * @Fields applyAmount : TODO(申请金额)
	 */
	//@DecimalFormat(format = "14,2", var = "申请金额")
	//@NotEmpty(message = "申请金额不能为空")
	private String applyAmount;
	/**
	 * @Fields termCount : TODO(借贷期限)
	 */
	//@NotEmpty(message = "借贷期限不能为空")
	private String termCount;
	/**
	 * @Fields termUnit : TODO(借贷期限单位)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "termUnit")
	//@NotEmpty(message = "借贷期限单位不能为空")
	private String termUnit;
	/**
	 * @Fields repayTermCount : TODO(还款间隔期限)
	 */
	private String repayTermCount;
	/**
	 * @Fields repayTermUnit : TODO(还款间隔期限单位)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "repayTermUnit")
	private String repayTermUnit;
	/**
	 * @Fields repayType : TODO(还款方式)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "repayType")
	//@NotEmpty(message = "还款方式不能为空")
	private String repayType;

	/**
	 * @Fields repayType : TODO(月还款额)
	 */
	//@NotEmpty(message = "月还款额不能为空")
	private String monthlyPayments;
	
	/**
	 * @Fields interestRate : TODO(年化利率)
	 */
	//@DecimalFormat(format = "4,2", var = "年化利率")
	//@NotEmpty(message = "年化利率不能为空")
	private String interestRate;
	/**
	 * @Fields penaltyRate : TODO(罚息利率)
	 */
	//@DecimalFormat(format = "4,2", var = "罚息利率")
	//@NotEmpty(message = "罚息利率不能为空")
	private String penaltyRate;
	/**
	 * @Fields guaranteeType : TODO(担保方式)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "guaranteeType")
	//@NotEmpty(message = "担保方式不能为空")
	private String guaranteeType;
	/**
	 * @Fields useType : TODO(借款用途)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "useType")
	private String useType;
	/**
	 * @Fields intoPieceType : TODO(进件类型)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "intoPieceType")
	private String intoPieceType;
	/**
	 * @Fields ownContract : TODO(是否自带合同,0：否 1：是)
	 */
	private String ownContract;

	/**
	 * @Fields loanAccount : TODO(放款账户名)
	 */
	//@Length(max = 64, message = "放款账户名最多64位字符")
	private String loanAccount;
	/**
	 * @Fields loanCardNo : TODO(放款账号/卡号)
	 */
	//@Length(max = 32, message = "放款账号/卡号最多32位字符")
	private String loanCardNo;
	/**
	 * @Fields loanBank : TODO(放款开户银行)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "bank")
	private String loanBank;
	/**
	 * @Fields loanOrg : TODO(放款开户机构)
	 */
	private String loanOrg;
	/**
	 * @Fields repaymentAccount : TODO(还款账户名)
	 */
	//@Length(max = 64, message = "还款账户名最多64位字符")
	private String repaymentAccount;
	/**
	 * @Fields repaymentCardNo : TODO(还款账号/卡号)
	 */
	//@Length(max = 32, message = "还款账号/卡号最多32位字符")
	private String repaymentCardNo;
	/**
	 * @Fields repaymentBank : TODO(还款开户银行)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "bank")
	private String repaymentBank;
	/**
	 * @Fields repaymentOrg : TODO(还款开户机构)
	 */
	private String repaymentOrg;
	/**
	 * @Fields supplementMsg : TODO(补充信息)
	 */
	//@Length(max = 256, message = "补充信息最多256位字符")
	private String supplementMsg;
	/**
	 * @Fields merchantState : TODO(是否为商户进件， 0：否 1：是)
	 */
	private String merchantState;
	/**
	 * @Fields merchantId : TODO(商户id)
	 */
	private String merchantId;
	/**
	 * @Fields merchantChargeType : TODO(商户类型)
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
	 * @Fields updateTime : TODO(更新时间)
	 */
	private String updateTime;

	/** 
	* @Fields downPaymentsAmount : TODO(首付金额) 
	*/
//	@DecimalFormat(format = "14,2", var = "首付金额")
//	private String downPaymentsAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMonthlyPayments() {
		return monthlyPayments;
	}

	public void setMonthlyPayments(String monthlyPayments) {
		this.monthlyPayments = monthlyPayments;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

//	public String getDownPaymentsAmount() {
//		return downPaymentsAmount;
//	}
//
//	public void setDownPaymentsAmount(String downPaymentsAmount) {
//		this.downPaymentsAmount = downPaymentsAmount;
//	}

}
