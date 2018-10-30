package com.company.platform.restapi.model.repayment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanRepayReduction implements Serializable {

	
	/**
     * 还款减免记录表
     */
    private static final long serialVersionUID = 7088045150326089231L;

    private String id;
	
    private String loanProductApplyId;
    
    private String businessId;
    
    private int businessType;
    /**
     * 还款记录Id
     */
    private String loanRepaymentRecordId;
    
    /**
     * 减免类型
     */
    private String reductionType;
    
    /**
     * 减免金额
     */
    private BigDecimal reductionAmount;
    
    private String reductionAmountState;
    
    private Date reductionTime;
    
    private String reductionReason;
    
    private String status;
    
    private String operId;
    
    private Date createTime;
    
    /**
     * 是否被使用
     */
    private int isUsed;
    
    private String repayFeeId;

    public String getId() {
        return id;
    }

    public String getLoanProductApplyId() {
        return loanProductApplyId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public int getBusinessType() {
        return businessType;
    }

    public String getLoanRepaymentRecordId() {
        return loanRepaymentRecordId;
    }

    public String getReductionType() {
        return reductionType;
    }

    public BigDecimal getReductionAmount() {
        return reductionAmount;
    }

    public String getReductionAmountState() {
        return reductionAmountState;
    }

    public Date getReductionTime() {
        return reductionTime;
    }

    public String getReductionReason() {
        return reductionReason;
    }

    public String getStatus() {
        return status;
    }

    public String getOperId() {
        return operId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoanProductApplyId(String loanProductApplyId) {
        this.loanProductApplyId = loanProductApplyId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public void setLoanRepaymentRecordId(String loanRepaymentRecordId) {
        this.loanRepaymentRecordId = loanRepaymentRecordId;
    }

    public void setReductionType(String reductionType) {
        this.reductionType = reductionType;
    }

    public void setReductionAmount(BigDecimal reductionAmount) {
        this.reductionAmount = reductionAmount;
    }

    public void setReductionAmountState(String reductionAmountState) {
        this.reductionAmountState = reductionAmountState;
    }

    public void setReductionTime(Date reductionTime) {
        this.reductionTime = reductionTime;
    }

    public void setReductionReason(String reductionReason) {
        this.reductionReason = reductionReason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public String getRepayFeeId() {
        return repayFeeId;
    }

    public void setRepayFeeId(String repayFeeId) {
        this.repayFeeId = repayFeeId;
    }

}
