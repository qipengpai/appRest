package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;


/** 
* @ClassName: LoanProductApplySubmit 
* @Description: TODO(已提交) 
* @author dongjian 
* @date 2018年5月23日 上午9:29:27 
*  
*/
@SuppressWarnings("all")
public class LoanProductApplySubmit extends BaseModel {

	/** 
	* @Fields loanProductApplyId : TODO(借贷申请id) 
	*/
	private String loanProductApplyId;
	/** 
	* @Fields intoPieceNo : TODO(进件编号) 
	*/
	private String intoPieceNo;
	/** 
	* @Fields productName : TODO(产品名称) 
	*/
	private String productName;
	/** 
	* @Fields applyTime : TODO(申请时间，时间格式yyyy-MM-dd hh:mm:ss) 
	*/
	private String applyTime;
	/** 
	* @Fields applyAmount : TODO(申请金额) 
	*/
	private String applyAmount;
	/** 
	* @Fields termCount : TODO(申请期限) 
	*/
	private String termCount;
	/** 
	* @Fields termUnit : TODO(申请期限单位) 
	*/
	private String termUnit;
	/** 
	* @Fields customerName : TODO(申请人姓名) 
	*/
	private String customerName;
	/** 
	* @Fields customerPhone : TODO(申请人联系方式) 
	*/
	private String customerPhone;
	/** 
	* @Fields customerAddress : TODO(联系住址) 
	*/
	private String customerAddress;
	/** 
	* @Fields intoPieceType : TODO(进件类型) 
	*/
	private String intoPieceType;
	/** 
	* @Fields workflowBusType : TODO(业务类型) 
	*/
	private String workflowBusType;
	/** 
	* @Fields applyDetail : TODO(借贷申请详情信息) 
	*/
	private LoanApplyInfo applyDetail;
	
	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/ 
	private String credentialNo;
	
	private String actionStatus;
	
	private String status;
	
	private String replyAmount;
	
	private String authTermCount;
	
	private String authTermUnit;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getIntoPieceNo() {
		return intoPieceNo;
	}

	public void setIntoPieceNo(String intoPieceNo) {
		this.intoPieceNo = intoPieceNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getTermCount() {
		return termCount;
	}

	public void setTermCount(String termCount) {
		this.termCount = termCount;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getIntoPieceType() {
		return intoPieceType;
	}

	public void setIntoPieceType(String intoPieceType) {
		this.intoPieceType = intoPieceType;
	}

	public LoanApplyInfo getApplyDetail() {
		return applyDetail;
	}

	public void setApplyDetail(LoanApplyInfo applyDetail) {
		this.applyDetail = applyDetail;
	}

	public String getWorkflowBusType() {
		return workflowBusType;
	}

	public void setWorkflowBusType(String workflowBusType) {
		this.workflowBusType = workflowBusType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(String replyAmount) {
		this.replyAmount = replyAmount;
	}

	public String getAuthTermCount() {
		return authTermCount;
	}

	public void setAuthTermCount(String authTermCount) {
		this.authTermCount = authTermCount;
	}

	public String getAuthTermUnit() {
		return authTermUnit;
	}

	public void setAuthTermUnit(String authTermUnit) {
		this.authTermUnit = authTermUnit;
	}

}
