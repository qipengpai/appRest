package com.company.platform.restapi.model.custom;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: PersonalCustomerInfo 
* @Description: TODO(客户个人基本信息) 
* @author yangxu 
* @date 2018年1月29日 下午4:59:08 
*  
*/
@SuppressWarnings("all")
public class PersonalCustomerInfo extends BaseModel {
	/** 
	* @Fields id : TODO(id) 
	*/ 
	@NotEmpty(message = "个人客户信息不能为null")
	private String	id;
	/** 
	* @Fields customerName : TODO(客户名称) 
	*/ 
	private String	customerName;
	/** 
	* @Fields credentialType : TODO(客户证件类型) 
	*/ 
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	private String	credentialType;
	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/ 
	private String	credentialNo;
	/** 
	* @Fields gender : TODO(客户性别) 
	*/ 
	@DictionaryStandardOrNot(dictionaryType = "gender")
	private String	gender;
	/** 
	* @Fields education : TODO(教育情况) 
	*/ 
	@DictionaryStandardOrNot(dictionaryType = "educationType")
	private String	education;
	/** 
	* @Fields birthday : TODO(生日) 
	*/ 
	private String	birthday;
	/** 
	* @Fields residenceType : TODO(住宅类型) 
	*/ 
	private String	residenceType;
	/** 
	* @Fields martialStatus : TODO(婚姻状况) 
	*/ 
	@DictionaryStandardOrNot(dictionaryType = "martialStatus")
	private String	martialStatus;
	/** 
	* @Fields hasChild : TODO(有无孩子) 
	*/ 
	private String	hasChild;
	/** 
	* @Fields childCount : TODO(子女数量) 
	*/ 
	private String	childCount;
	/** 
	* @Fields remark : TODO(评价) 
	*/ 
	private String	remark;
	/** 
	* @Fields occupationStatus : TODO(职业状态) 
	*/ 
	@DictionaryStandardOrNot(dictionaryType = "occupationStatus")
	private String	occupationStatus;
	/** 
	* @Fields residenceLength : TODO(居住时长) 
	*/ 
	@DictionaryStandardOrNot(dictionaryType = "residenceLength")
	private String	residenceLength;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}
	public String getMartialStatus() {
		return martialStatus;
	}
	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}
	public String getHasChild() {
		return hasChild;
	}
	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}
	public String getChildCount() {
		return childCount;
	}
	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOccupationStatus() {
		return occupationStatus;
	}
	public void setOccupationStatus(String occupationStatus) {
		this.occupationStatus = occupationStatus;
	}
	public String getResidenceLength() {
		return residenceLength;
	}
	public void setResidenceLength(String residenceLength) {
		this.residenceLength = residenceLength;
	}
}
