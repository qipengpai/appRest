package com.company.platform.restapi.model.guarantor;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: GuarantorInfo 
* @Description: TODO(担保人信息) 
* @author 王雪 
* @date 2018年5月29日 下午5:22:04 
*  
*/
@SuppressWarnings("all")
public class GuarantorInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields credentialType : TODO(证件类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	@NotEmpty(message = "证件类型不能为空")
	private String credentialType;

	/** 
	* @Fields credentialNo : TODO(证件号) 
	*/
	@NotEmpty(message = "证件号不能为空")
	private String credentialNo;

	/** 
	* @Fields name : TODO(姓名) 
	*/
	@NotEmpty(message = "姓名不能为空")
	private String name;
	/** 
	* @Fields relationship : TODO(与申请人关系) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "relationship")
	@NotEmpty(message = "与申请人关系不能为空")
	private String relationship;

	/** 
	* @Fields mobile : TODO(联系电话) 
	*/
	@NotEmpty(message = "联系电话不能为空")
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
	* @Fields typeOfGuarantor : TODO(担保人类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "typeOfGuarantor")
	@NotEmpty(message = "担保人类型不能为空")
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
