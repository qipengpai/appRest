package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: FeeCondition
 * @Description: TODO(收费条件模型)
 * @author yangxu
 * @date 2018年1月30日 下午7:35:12
 * 
 */
@SuppressWarnings("all")
public class FeeCondition extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields feeId : TODO(费用id)
	 */
	private String feeId;

	/**
	 * @Fields minTermCount : TODO(支持最小期限 )
	 */
	private String minTermCount;

	/**
	 * @Fields maxTermCount : TODO(支持最大期限)
	 */
	private String maxTermCount;

	/**
	 * @Fields termUnit : TODO(期限单位)
	 */
	private String termUnit;

	/**
	 * @Fields calculationType : TODO(计算类型 0：自定义费用 1：按总比例收费)
	 */
	private String calculationType;

	/**
	 * @Fields calculationBasics : TODO(计算基础)
	 */
	private String calculationBasics;

	/**
	 * @Fields feeAmount : TODO(费用金额)
	 */
	private String feeAmount;

	/**
	 * @Fields feeRate : TODO(费用比例)
	 */
	private String feeRate;

	/**
	 * @Fields minFee : TODO(最低费用)
	 */
	private String minFee;

	/**
	 * @Fields maxFee : TODO(最高费用)
	 */
	private String maxFee;

	/**
	 * @Fields isOn : TODO(是否启用 0：停用 1：启用
	 */
	private String isOn;

	/**
	 * @Fields operDate : TODO(操作日期)
	 */
	private String operDate;

	/**
	 * @Fields userId : TODO(操作用户)
	 */
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getMinTermCount() {
		return minTermCount;
	}

	public void setMinTermCount(String minTermCount) {
		this.minTermCount = minTermCount;
	}

	public String getMaxTermCount() {
		return maxTermCount;
	}

	public void setMaxTermCount(String maxTermCount) {
		this.maxTermCount = maxTermCount;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public String getCalculationBasics() {
		return calculationBasics;
	}

	public void setCalculationBasics(String calculationBasics) {
		this.calculationBasics = calculationBasics;
	}

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getMinFee() {
		return minFee;
	}

	public void setMinFee(String minFee) {
		this.minFee = minFee;
	}

	public String getMaxFee() {
		return maxFee;
	}

	public void setMaxFee(String maxFee) {
		this.maxFee = maxFee;
	}

	public String getIsOn() {
		return isOn;
	}

	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
