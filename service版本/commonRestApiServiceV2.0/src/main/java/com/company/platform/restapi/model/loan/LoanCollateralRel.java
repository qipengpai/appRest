package com.company.platform.restapi.model.loan;

import java.math.BigDecimal;
import java.util.Date;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanCollateralRel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yangxu
 * @date 2018年2月6日 下午5:03:37
 * 
 */
@SuppressWarnings("all")
public class LoanCollateralRel extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields loanApplyId : TODO(贷款申请Id)
	 */
	private String loanApplyId;

	/**
	 * @Fields collateralId : TODO(抵押品Id)
	 */
	private String collateralId;

	/**
	 * @Fields eOrder : TODO(排序)
	 */
	private String eOrder;

	/**
	 * @Fields effTime : TODO(添加时间)
	 */
	private String effTime;

	/**
	 * @Fields mortgageAmount : TODO(实际抵押金额)
	 */
	private String mortgageAmount;

	/**
	 * @Fields warrantStatus : TODO(权属证件状态)
	 */
	private String warrantStatus;

	/**
	 * @Fields esValue : TODO(当时评估价值)
	 */
	private String esValue;

	/**
	 * @Fields extensionApplyId : TODO(展期申请id)
	 */
	private String extensionApplyId;

	/**
	 * @Fields collateralClass : TODO(抵押类型 0：借贷申请抵押 1：展期申请抵押)
	 */
	private String collateralClass;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public String getCollateralId() {
		return collateralId;
	}

	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
	}

	public String geteOrder() {
		return eOrder;
	}

	public void seteOrder(String eOrder) {
		this.eOrder = eOrder;
	}

	public String getEffTime() {
		return effTime;
	}

	public void setEffTime(String effTime) {
		this.effTime = effTime;
	}

	public String getMortgageAmount() {
		return mortgageAmount;
	}

	public void setMortgageAmount(String mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}

	public String getWarrantStatus() {
		return warrantStatus;
	}

	public void setWarrantStatus(String warrantStatus) {
		this.warrantStatus = warrantStatus;
	}

	public String getEsValue() {
		return esValue;
	}

	public void setEsValue(String esValue) {
		this.esValue = esValue;
	}

	public String getExtensionApplyId() {
		return extensionApplyId;
	}

	public void setExtensionApplyId(String extensionApplyId) {
		this.extensionApplyId = extensionApplyId;
	}

	public String getCollateralClass() {
		return collateralClass;
	}

	public void setCollateralClass(String collateralClass) {
		this.collateralClass = collateralClass;
	}

}
