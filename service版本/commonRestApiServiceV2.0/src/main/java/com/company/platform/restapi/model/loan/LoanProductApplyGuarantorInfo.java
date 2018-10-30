package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductApplyGuarantorInfo
 * @Description: TODO(借贷申请担保人信息)
 * @author yangxu
 * @date 2018年2月6日 下午5:05:55
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyGuarantorInfo extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields credentialType : TODO(证件类型)
	 */
	private String credentialType;

	/**
	 * @Fields credentialNo : TODO(证件号)
	 */
	private String credentialNo;

	/**
	 * @Fields name : TODO(担保人姓名)
	 */
	private String name;

	/**
	 * @Fields relationship : TODO(担保人关系)
	 */
	private String relationship;

	/**
	 * @Fields mobile : TODO(担保人手机号)
	 */
	private String mobile;

	/**
	 * @Fields address : TODO(担保人地址)
	 */
	private String address;

	/**
	 * @Fields company : TODO(担保人公司)
	 */
	private String company;

	/**
	 * @Fields typeOfGuarantor : TODO(担保人类型)
	 */
	private String typeOfGuarantor;

	/**
	 * @Fields duty : TODO(担保人具体职位)
	 */
	private String duty;

	/**
	 * @Fields income : TODO(担保人收入)
	 */
	private String income;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
