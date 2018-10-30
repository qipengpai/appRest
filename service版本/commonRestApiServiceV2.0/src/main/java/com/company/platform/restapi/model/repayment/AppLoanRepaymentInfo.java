package com.company.platform.restapi.model.repayment;

public class AppLoanRepaymentInfo {
	
	private String id;
	
	private String productName;
	
	private String code;
	
	private String periodNum;
	
	private String dueRepayDate;
	
	private String repayStatus;
	
	private String applyAmount;
	
	private String duePrincipal;
	
	private String dueInterest;
	
	private String duePenalty;
	
	private String dueFeeAmount;
	
	private String dueTotalAmount;
	
	private String needPayTotalAmount;
	
	private String arrivalAmount;
	
	private String loanApplyId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

	public String getDueRepayDate() {
		return dueRepayDate;
	}

	public void setDueRepayDate(String dueRepayDate) {
		this.dueRepayDate = dueRepayDate;
	}

	public String getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getDuePrincipal() {
		return duePrincipal;
	}

	public void setDuePrincipal(String duePrincipal) {
		this.duePrincipal = duePrincipal;
	}

	public String getDueInterest() {
		return dueInterest;
	}

	public void setDueInterest(String dueInterest) {
		this.dueInterest = dueInterest;
	}

	public String getDueFeeAmount() {
		return dueFeeAmount;
	}

	public void setDueFeeAmount(String dueFeeAmount) {
		this.dueFeeAmount = dueFeeAmount;
	}

	public String getDueTotalAmount() {
		return dueTotalAmount;
	}

	public void setDueTotalAmount(String dueTotalAmount) {
		this.dueTotalAmount = dueTotalAmount;
	}

	public String getNeedPayTotalAmount() {
		return needPayTotalAmount;
	}

	public void setNeedPayTotalAmount(String needPayTotalAmount) {
		this.needPayTotalAmount = needPayTotalAmount;
	}

	public String getDuePenalty() {
		return duePenalty;
	}

	public void setDuePenalty(String duePenalty) {
		this.duePenalty = duePenalty;
	}

	public String getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(String arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

}
