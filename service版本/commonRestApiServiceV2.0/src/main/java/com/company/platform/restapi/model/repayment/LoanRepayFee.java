package com.company.platform.restapi.model.repayment;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanRepayFee implements Serializable {

	private static final long serialVersionUID = -696319209274634374L;
	
	private String id;
	/**
	 * 还款计划id
	 */
	private String loanRepayId;
	/**
	 * 费用金额
	 */
	private BigDecimal feeAmount;
	/**
	 * 是否缴费 0：否 1：是 2:无需缴费
	 */
	private int isPayment;
	/**
	 * 借贷费用id
	 */
	private String loanProductApplyFeeId;
	/**
	 * 借贷id
	 */
	private String loanProductApplyId;
	/**
	 * 实还金额
	 */
	private BigDecimal realAmount;
	/**
	 * get[id]
	 */
	public String getId() {
		return id;
	}
	/**
	 * set[id]
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * get[还款计划id]
	 */
	public String getLoanRepayId() {
		return loanRepayId;
	}
	/**
	 * set[还款计划id]
	 */
	public void setLoanRepayId(String loanRepayId) {
		this.loanRepayId = loanRepayId;
	}
	/**
	 * get[费用金额]
	 */
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	/**
	 * set[费用金额]
	 */
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	/**
	 * get[是否缴费0：否1：是 2:无需缴费]
	 */
	public int getIsPayment() {
		return isPayment;
	}
	/**
	 * set[是否缴费0：否1：是 2:无需缴费]
	 */
	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}
	/**
	 * get[借贷费用id]
	 */
	public String getLoanProductApplyFeeId() {
		return loanProductApplyFeeId;
	}
	/**
	 * set[借贷费用id]
	 */
	public void setLoanProductApplyFeeId(String loanProductApplyFeeId) {
		this.loanProductApplyFeeId = loanProductApplyFeeId;
	}
	/**
	 * get[借贷id]
	 */
	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}
	/**
	 * set[借贷id]
	 */
	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}
    public BigDecimal getRealAmount() {
        return realAmount;
    }
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }
	
}
