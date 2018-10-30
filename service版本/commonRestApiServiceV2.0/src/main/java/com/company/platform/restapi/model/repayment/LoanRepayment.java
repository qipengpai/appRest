package com.company.platform.restapi.model.repayment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanRepayment implements Serializable {

	private static final long serialVersionUID = -2931716919731172671L;
	
	private String id;
	/**
	 * 借贷id
	 */
	private String loanProductApplyId;
	/**
	 * 展期id（如果该还款计划为展期调整后生成，对应展期申请）
	 */
	private String extensionApplyId;
	/**
	 * 应还本金
	 */
	private BigDecimal duePrincipal;
	/**
	 * 应还利息
	 */
	private BigDecimal dueInterest;
	/**
	 * 约定还款时间
	 */
	private Date dueRepayDate;
	/**
	 * 还款账期（天）
	 */
	private Integer repayDate;
	/**
	 * 期数
	 */
	private int periodNum;
	/**
	 * 还款状态 0：未还 1：已还
	 */
	private int repayStatus;
	/**
	 * 实际还款时间
	 */
	private Date realRepayDate;
	/**
	 * 逾期天数
	 */
	private Integer overDueDays;
	/**
	 * 还款类型
	 */
	private String repayType;
	/**
	 * 实际还款本金
	 */
	private BigDecimal realPrincipal;
	/**
	 * 实际还款利息
	 */
	private BigDecimal realInterest;
	/**
	 * 罚息
	 */
	private BigDecimal penalty;
	
	/**
	 * 实际罚息
	 */
	private BigDecimal realPenalty;
	
	/**
	 * 冲正金额
	 */
	private BigDecimal positiveAmount;
	/**
	 * in：+冲正金额  out：-冲正金额
	 */
	private String positiveAmountState;
	/**
	 * 冲正标识 0：未冲正 1：已冲正
	 */
	private Integer positiveState;
	/**
	 * 费用减免标识 0：否 1：是
	 */
	private int feeReduceState;
	/**
	 * 提前还款标识 0 ：否 1：是
	 */
	private int advanceRepayState;
	/**
	 * 展期标识 0：否 1：是
	 */
	private int extensionState;
	/**
	 * 冲正原因
	 */
	private String positiveReason;
	
	/**
	 * 减免标识
	 */
	private String reductionState;
	
	/**
	 * 还款模式 1：逾期还全部 2：逾期还部分 3：主动还全部 4：主动还部分 5：主动还当期
	 */
	private String repayPattern;
	/**
     * 应还部分金额
     */
	private BigDecimal partRepayAmount;
	
	/**
	 * 实还费用总额
	 */
	private BigDecimal realFeeAmount;
	
	/**
     * 实还总金额
     */
    private BigDecimal realTotalAmount;
	
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
	/**
	 * get[展期id（如果该还款计划为展期调整后生成，对应展期申请）]
	 */
	public String getExtensionApplyId() {
		return extensionApplyId;
	}
	/**
	 * set[展期id（如果该还款计划为展期调整后生成，对应展期申请）]
	 */
	public void setExtensionApplyId(String extensionApplyId) {
		this.extensionApplyId = extensionApplyId;
	}
	/**
	 * get[应还本金]
	 */
	public BigDecimal getDuePrincipal() {
		return duePrincipal;
	}
	/**
	 * set[应还本金]
	 */
	public void setDuePrincipal(BigDecimal duePrincipal) {
		this.duePrincipal = duePrincipal;
	}
	/**
	 * get[应还利息]
	 */
	public BigDecimal getDueInterest() {
		return dueInterest;
	}
	/**
	 * set[应还利息]
	 */
	public void setDueInterest(BigDecimal dueInterest) {
		this.dueInterest = dueInterest;
	}
	/**
	 * get[约定还款时间]
	 */
	public Date getDueRepayDate() {
		return dueRepayDate;
	}
	/**
	 * set[约定还款时间]
	 */
	public void setDueRepayDate(Date dueRepayDate) {
		this.dueRepayDate = dueRepayDate;
	}
	/**
	 * get[还款账期（天）]
	 */
	public Integer getRepayDate() {
		return repayDate;
	}
	/**
	 * set[还款账期（天）]
	 */
	public void setRepayDate(Integer repayDate) {
		this.repayDate = repayDate;
	}
	/**
	 * get[期数]
	 */
	public int getPeriodNum() {
		return periodNum;
	}
	/**
	 * set[期数]
	 */
	public void setPeriodNum(int periodNum) {
		this.periodNum = periodNum;
	}
	/**
	 * get[还款状态0：未还1：已还]
	 */
	public int getRepayStatus() {
		return repayStatus;
	}
	/**
	 * set[还款状态0：未还1：已还]
	 */
	public void setRepayStatus(int repayStatus) {
		this.repayStatus = repayStatus;
	}
	/**
	 * get[实际还款时间]
	 */
	public Date getRealRepayDate() {
		return realRepayDate;
	}
	/**
	 * set[实际还款时间]
	 */
	public void setRealRepayDate(Date realRepayDate) {
		this.realRepayDate = realRepayDate;
	}
	/**
	 * get[逾期天数]
	 */
	public Integer getOverDueDays() {
		return overDueDays;
	}
	/**
	 * set[逾期天数]
	 */
	public void setOverDueDays(Integer overDueDays) {
		this.overDueDays = overDueDays;
	}
	/**
	 * get[还款类型]
	 */
	public String getRepayType() {
		return repayType;
	}
	/**
	 * set[还款类型]
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	/**
	 * get[实际还款本金]
	 */
	public BigDecimal getRealPrincipal() {
		return realPrincipal;
	}
	/**
	 * set[实际还款本金]
	 */
	public void setRealPrincipal(BigDecimal realPrincipal) {
		this.realPrincipal = realPrincipal;
	}
	/**
	 * get[实际还款利息]
	 */
	public BigDecimal getRealInterest() {
		return realInterest;
	}
	/**
	 * set[实际还款利息]
	 */
	public void setRealInterest(BigDecimal realInterest) {
		this.realInterest = realInterest;
	}
	/**
	 * get[罚息]
	 */
	public BigDecimal getPenalty() {
		return penalty;
	}
	/**
	 * set[罚息]
	 */
	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}
	/**
	 * get[冲正金额]
	 */
	public BigDecimal getPositiveAmount() {
		return positiveAmount;
	}
	/**
	 * set[冲正金额]
	 */
	public void setPositiveAmount(BigDecimal positiveAmount) {
		this.positiveAmount = positiveAmount;
	}
	/**
	 * get[in：+冲正金额  out：-冲正金额]
	 */
	public String getPositiveAmountState() {
		return positiveAmountState;
	}
	/**
	 * set[in：+冲正金额  out：-冲正金额]
	 */
	public void setPositiveAmountState(String positiveAmountState) {
		this.positiveAmountState = positiveAmountState;
	}
	/**
	 * get[冲正标识0：未冲正1：已冲正]
	 */
	public Integer getPositiveState() {
		return positiveState;
	}
	/**
	 * set[冲正标识0：未冲正1：已冲正]
	 */
	public void setPositiveState(Integer positiveState) {
		this.positiveState = positiveState;
	}
	/**
	 * get[费用减免标识0：否1：是]
	 */
	public int getFeeReduceState() {
		return feeReduceState;
	}
	/**
	 * set[费用减免标识0：否1：是]
	 */
	public void setFeeReduceState(int feeReduceState) {
		this.feeReduceState = feeReduceState;
	}
	/**
	 * get[提前还款标识0：否1：是]
	 */
	public int getAdvanceRepayState() {
		return advanceRepayState;
	}
	/**
	 * set[提前还款标识0：否1：是]
	 */
	public void setAdvanceRepayState(int advanceRepayState) {
		this.advanceRepayState = advanceRepayState;
	}
	/**
	 * get[展期标识0：否1：是]
	 */
	public int getExtensionState() {
		return extensionState;
	}
	/**
	 * set[展期标识0：否1：是]
	 */
	public void setExtensionState(int extensionState) {
		this.extensionState = extensionState;
	}
	/**
	 * get[冲正原因]
	 */
	public String getPositiveReason() {
		return positiveReason;
	}
	/**
	 * set[冲正原因]
	 */
	public void setPositiveReason(String positiveReason) {
		this.positiveReason = positiveReason;
	}
    public BigDecimal getRealPenalty() {
        return realPenalty;
    }
    public void setRealPenalty(BigDecimal realPenalty) {
        this.realPenalty = realPenalty;
    }

    public BigDecimal getPartRepayAmount() {
        return partRepayAmount;
    }
    public void setPartRepayAmount(BigDecimal partRepayAmount) {
        this.partRepayAmount = partRepayAmount;
    }
    public String getRepayPattern() {
        return repayPattern;
    }
    public void setRepayPattern(String repayPattern) {
        this.repayPattern = repayPattern;
    }
    public String getReductionState() {
        return reductionState;
    }
    public void setReductionState(String reductionState) {
        this.reductionState = reductionState;
    }
    public BigDecimal getRealFeeAmount() {
        return realFeeAmount;
    }
    public BigDecimal getRealTotalAmount() {
        return realTotalAmount;
    }
    public void setRealFeeAmount(BigDecimal realFeeAmount) {
        this.realFeeAmount = realFeeAmount;
    }
    public void setRealTotalAmount(BigDecimal realTotalAmount) {
        this.realTotalAmount = realTotalAmount;
    }
	
}
