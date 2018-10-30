package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductApplyGuarantor
 * @Description: TODO(借贷申请担保人关联信息)
 * @author yangxu
 * @date 2018年2月6日 下午5:05:18
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyGuarantor extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields loanProductApplyId : TODO(借贷申请id)
	 */
	private String loanProductApplyId;

	/**
	 * @Fields guarantorId : TODO(用一句话描述这个变量表示什么)
	 */
	private String guarantorId;

	/**
	 * @Fields extensionApplyId : TODO(展期申请id)
	 */
	private String extensionApplyId;

	/**
	 * @Fields guarantorType : TODO(担保人类型 0：借贷申请担保人 1：展期申请担保人)
	 */
	private String guarantorType;

	/**
	 * @Fields relationship : TODO(与申请人关系)
	 */
	private String relationship;

	/**
	 * @Fields mobile : TODO(联系电话)
	 */
	private String mobile;

	/**
	 * @Fields address : TODO(联系地址)
	 */
	private String address;

	/**
	 * @Fields company : TODO(单位名称)
	 */
	private String company;

	/**
	 * @Fields typeOfGuarantor : TODO(担保人类型，对应字典typeOfGuarantor)
	 */
	private String typeOfGuarantor;

	/**
	 * @Fields duty : TODO(具体职位)
	 */
	private String duty;

	/**
	 * @Fields income : TODO(月均收入)
	 */
	private String income;

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

	public String getGuarantorId() {
		return guarantorId;
	}

	public void setGuarantorId(String guarantorId) {
		this.guarantorId = guarantorId;
	}

	public String getExtensionApplyId() {
		return extensionApplyId;
	}

	public void setExtensionApplyId(String extensionApplyId) {
		this.extensionApplyId = extensionApplyId;
	}

	public String getGuarantorType() {
		return guarantorType;
	}

	public void setGuarantorType(String guarantorType) {
		this.guarantorType = guarantorType;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTypeOfGuarantor() {
		return typeOfGuarantor;
	}

	public void setTypeOfGuarantor(String typeOfGuarantor) {
		this.typeOfGuarantor = typeOfGuarantor;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

}
