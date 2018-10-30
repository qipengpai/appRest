package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductApplyFeeInfo
 * @Description: TODO(借贷申请费用信息)
 * @author yangxu
 * @date 2018年1月30日 下午5:12:56
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyFeeInfo extends BaseModel {
	/**
	 * @Fields id : TODO(id)
	 */
	private String id;
	/**
	 * @Fields name : TODO(费用描述)
	 */
	private String name;
	/**
	 * @Fields feeType : TODO(费用类型)
	 */
	private String feeType;
	/**
	 * @Fields chargeType : TODO(收取类型)
	 */
	private String chargeType;
	/**
	 * @Fields feeAmount : TODO(费用金额)
	 */
	private String feeAmount;
	/**
	 * @Fields calculationBasic : TODO(比例计算基础)
	 */
	private String calculationBasic;
	/**
	 * @Fields feeRate : TODO(收取比例)
	 */
	private String feeRate;
	/**
	 * @Fields feeReduction : TODO(费用减免)
	 */
	private String feeReduction;
	/**
	 * @Fields actualFeeAmount : TODO(实际费用金额)
	 */
	private String actualFeeAmount;
	/**
	 * @Fields actualFeeAmount : TODO(当前期数)
	 */
	private String periodNum;
	/**
	 * @Fields status : TODO(状态)
	 */
	private String status;
	/**
	 * @Fields loanProductFeeId : TODO(产品费用id)
	 */
	private String loanProductFeeId;
	/**
	 * @Fields feeConditionId : TODO(收费条件id)
	 */
	private String feeConditionId;
	/**
	 * @Fields loanProductApplyId : TODO(借贷申请id)
	 */
	private String loanProductApplyId;
	/**
	 * @Fields extensionApplyId : TODO(展期申请id)
	 */
	private String extensionApplyId;
	/**
	 * @Fields applyType : TODO(借贷申请类型)
	 */
	private String applyType;
	/**
	 * @Fields feeReductionState : TODO(减免费用状态,in + ; out -)
	 */
	private String feeReductionState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getCalculationBasic() {
		return calculationBasic;
	}

	public void setCalculationBasic(String calculationBasic) {
		this.calculationBasic = calculationBasic;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getFeeReduction() {
		return feeReduction;
	}

	public void setFeeReduction(String feeReduction) {
		this.feeReduction = feeReduction;
	}

	public String getActualFeeAmount() {
		return actualFeeAmount;
	}

	public void setActualFeeAmount(String actualFeeAmount) {
		this.actualFeeAmount = actualFeeAmount;
	}

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoanProductFeeId() {
		return loanProductFeeId;
	}

	public void setLoanProductFeeId(String loanProductFeeId) {
		this.loanProductFeeId = loanProductFeeId;
	}

	public String getFeeConditionId() {
		return feeConditionId;
	}

	public void setFeeConditionId(String feeConditionId) {
		this.feeConditionId = feeConditionId;
	}

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getExtensionApplyId() {
		return extensionApplyId;
	}

	public void setExtensionApplyId(String extensionApplyId) {
		this.extensionApplyId = extensionApplyId;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getFeeReductionState() {
		return feeReductionState;
	}

	public void setFeeReductionState(String feeReductionState) {
		this.feeReductionState = feeReductionState;
	}

}
