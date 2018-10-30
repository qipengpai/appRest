package com.company.platform.restapi.model.loan.fee;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductApplyFeeInfo
 * @Description: TODO(借贷申请费用信息)
 * @author yangxu
 * @date 2018年1月30日 下午5:12:56
 * 
 */
@SuppressWarnings("all")
public class LoanApplyFeeInfo extends BaseModel {
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
	 * @Fields periodNum : TODO(当前期数)
	 */
	private String periodNum;

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

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

}
