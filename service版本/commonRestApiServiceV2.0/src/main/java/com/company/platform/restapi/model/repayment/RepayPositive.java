package com.company.platform.restapi.model.repayment;

import java.io.Serializable;
import java.util.Date;

public class RepayPositive implements Serializable {


    /**
     * 还款冲正关联表
     */
    private static final long serialVersionUID = -1399966382144555688L;

    private String id;
	
	/**
	 * 还款计划id
	 */
	private String loanRepaymentId;

	/**
	 * 冲正记录表Id
	 */
	private String loanPositiveId;
	
	/**
	 * 冲正原因
	 */
	private String positiveReason;
	
	/**
	 * 还款类型
	 */
	private String repayType;
	
	/**
	 * 冲正时间
	 */
	private Date positiveTime;

    public String getId() {
        return id;
    }

    public String getLoanRepaymentId() {
        return loanRepaymentId;
    }

    public String getLoanPositiveId() {
        return loanPositiveId;
    }

    public String getPositiveReason() {
        return positiveReason;
    }

    public String getRepayType() {
        return repayType;
    }

    public Date getPositiveTime() {
        return positiveTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoanRepaymentId(String loanRepaymentId) {
        this.loanRepaymentId = loanRepaymentId;
    }

    public void setLoanPositiveId(String loanPositiveId) {
        this.loanPositiveId = loanPositiveId;
    }

    public void setPositiveReason(String positiveReason) {
        this.positiveReason = positiveReason;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public void setPositiveTime(Date positiveTime) {
        this.positiveTime = positiveTime;
    }

}
