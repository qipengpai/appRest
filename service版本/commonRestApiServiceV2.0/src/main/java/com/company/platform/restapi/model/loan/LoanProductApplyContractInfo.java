package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductApplyContractInfo
 * @Description: TODO(借贷合同信息)
 * @author yangxu
 * @date 2018年2月6日 下午5:04:44
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyContractInfo extends BaseModel {
	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;
	/**
	 * @Fields loanProductApplyId : TODO(借贷id)
	 */
	private String loanProductApplyId;
	/**
	 * @Fields contractNo : TODO(合同编号)
	 */
	private String contractNo;
	/**
	 * @Fields contractName : TODO(合同名称)
	 */
	private String contractName;
	/**
	 * @Fields contractPath : TODO(合同路径)
	 */
	private String contractPath;
	/**
	 * @Fields templateName : TODO(模板名称)
	 */
	private String templateName;
	/**
	 * @Fields template_product_id : TODO(模板路径)
	 */
	private String template_product_id;
	/**
	 * @Fields signStatus : TODO(签订状态)
	 */
	private String signStatus;
	/**
	 * @Fields operUserId : TODO(操作人)
	 */
	private String operUserId;
	/**
	 * @Fields signTime : TODO(签订时间)
	 */
	private String signTime;
	/**
	 * @Fields contractType : TODO(合同类型)
	 */
	private String contractType;
	/**
	 * @Fields extensionApplyId : TODO(展期申请id)
	 */
	private String extensionApplyId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractPath() {
		return contractPath;
	}

	public void setContractPath(String contractPath) {
		this.contractPath = contractPath;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplate_product_id() {
		return template_product_id;
	}

	public void setTemplate_product_id(String template_product_id) {
		this.template_product_id = template_product_id;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getExtensionApplyId() {
		return extensionApplyId;
	}

	public void setExtensionApplyId(String extensionApplyId) {
		this.extensionApplyId = extensionApplyId;
	}

}
