package com.company.platform.restapi.model.loan.deal;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: LoanRepaymentInfo 
* @Description: TODO(还款计划) 
* @author wangxue 
* @date 2018年7月13日 上午11:22:00 
*  
*/
@SuppressWarnings("all")
public class LoanRepaymentInfo extends BaseModel {
	/**
	 * @Fields periodNum : TODO(期数)
	 */
	private String periodNum;
	/**
	 * @Fields principal : TODO(应还本金)
	 */
	private String principal;
	/**
	 * @Fields interest : TODO(应还利息)
	 */
	private String interest;
	/**
	 * @Fields amount : TODO(应还总额)
	 */
	private String amount;
	/**
	 * @Fields repayTime : TODO(约定还款日期，yyyy-MM-dd)
	 */
	private String repayTime;

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

}
