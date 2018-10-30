package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: selContactsInfo 
* @Description: TODO(个人客户联系人信息) 
* @author lifeng 
* @date 2018年1月30日 下午5:35:31 
*  
*/
@SuppressWarnings("all")
public class SelContactsInfo extends BaseModel{
	
	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/ 
	private String credentialNo;
	/** 
	* @Fields address : TODO(联系人地址) 
	*/ 
	private String address;
	/** 
	* @Fields unit : TODO(单位名称) 
	*/ 
	private String unit;
	/** 
	* @Fields customerId : TODO(关联客户ID) 
	*/ 
	private String customerId;
	/** 
	* @Fields id : TODO(联系人id) 
	*/ 
	private String id;
	/** 
	* @Fields name : TODO(姓名) 
	*/ 
	private String realName;
	/** 
	* @Fields relType : TODO(关系) 
	*/ 
	private String relType;
	/** 
	* @Fields phoneNum : TODO(电话) 
	*/ 
	private String phoneNum;
	/** 
	* @Fields credentialType : TODO(证件类型) 
	*/ 
	private String credentialType;
	public String getCredentialNo() {
		return credentialNo;
	}
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRelType() {
		return relType;
	}
	public void setRelType(String relType) {
		this.relType = relType;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCredentialType() {
		return credentialType;
	}
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}
	
	
}
