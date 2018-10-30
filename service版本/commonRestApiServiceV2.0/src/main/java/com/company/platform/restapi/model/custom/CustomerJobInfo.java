package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: CustomerJobInfo
 * @Description: TODO(客户工作信息(全字段))
 * @author yangxu
 * @date 2018年1月30日 上午7:30:15
 * 
 */
@SuppressWarnings("all")
public class CustomerJobInfo extends BaseModel {

	/**
	 * @Fields id : TODO(id)
	 */
	private String id;
	/**
	 * @Fields eType : TODO(客户类型)
	 */
	private String eType;
	/**
	 * @Fields monthIncome : TODO(月收入)
	 */
	private String monthIncome;
	/**
	 * @Fields incomeScope : TODO(收入范围)
	 */
	private String incomeScope;
	/**
	 * @Fields currentWorkType : TODO(当前工作信息)
	 */
	private String currentWorkInfo;
	/**
	 * @Fields currentDepInfo : TODO(当前储蓄信息)
	 */
	private String currentDepInfo;
	/**
	 * @Fields currentWorkAge : TODO(当前工龄)
	 */
	private String currentWorkAge;
	/**
	 * @Fields currentWorkType : TODO(当前工作性质)
	 */
	private String currentWorkType;
	/**
	 * @Fields currentPosition : TODO(财务状况)
	 */
	private String currentPosition;
	/**
	 * @Fields currentPosType : TODO(当前财务类型)
	 */
	private String currentPosType;
	/**
	 * @Fields totalWorkAge : TODO(总计工龄)
	 */
	private String totalWorkAge;
	/**
	 * @Fields industryType : TODO(行业类型)
	 */
	private String industryType;
	/**
	 * @Fields otherIncome : TODO(其他收入)
	 */
	private String otherIncome;
	/**
	 * @Fields incomeDay : TODO(进款日)
	 */
	private String incomeDay;
	/**
	 * @Fields payType : TODO(付款类型)
	 */
	private String payType;
	/**
	 * @Fields eDesc : TODO(描述)
	 */
	private String eDesc;
	/**
	 * @Fields workingHours : TODO(工作时长)
	 */
	private String workingHours;
	/**
	 * @Fields payrollDay : TODO(发薪日)
	 */
	private String payrollDay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

	public String getIncomeScope() {
		return incomeScope;
	}

	public void setIncomeScope(String incomeScope) {
		this.incomeScope = incomeScope;
	}

	public String getCurrentWorkInfo() {
		return currentWorkInfo;
	}

	public void setCurrentWorkInfo(String currentWorkInfo) {
		this.currentWorkInfo = currentWorkInfo;
	}

	public String getCurrentDepInfo() {
		return currentDepInfo;
	}

	public void setCurrentDepInfo(String currentDepInfo) {
		this.currentDepInfo = currentDepInfo;
	}

	public String getCurrentWorkAge() {
		return currentWorkAge;
	}

	public void setCurrentWorkAge(String currentWorkAge) {
		this.currentWorkAge = currentWorkAge;
	}

	public String getCurrentWorkType() {
		return currentWorkType;
	}

	public void setCurrentWorkType(String currentWorkType) {
		this.currentWorkType = currentWorkType;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getCurrentPosType() {
		return currentPosType;
	}

	public void setCurrentPosType(String currentPosType) {
		this.currentPosType = currentPosType;
	}

	public String getTotalWorkAge() {
		return totalWorkAge;
	}

	public void setTotalWorkAge(String totalWorkAge) {
		this.totalWorkAge = totalWorkAge;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getIncomeDay() {
		return incomeDay;
	}

	public void setIncomeDay(String incomeDay) {
		this.incomeDay = incomeDay;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String geteDesc() {
		return eDesc;
	}

	public void seteDesc(String eDesc) {
		this.eDesc = eDesc;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getPayrollDay() {
		return payrollDay;
	}

	public void setPayrollDay(String payrollDay) {
		this.payrollDay = payrollDay;
	}
}
