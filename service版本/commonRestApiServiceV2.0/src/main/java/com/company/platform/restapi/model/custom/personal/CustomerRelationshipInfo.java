package com.company.platform.restapi.model.custom.personal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * 
* @ClassName: CustomerRelationshipInfo 
* @Description: TODO(客户联系人信息) 
* @author 王雪 
* @date 2018年1月25日 下午1:55:32 
*
 */
@SuppressWarnings("all")
public class CustomerRelationshipInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields customerId : TODO(客户id) 
	*/
	private String customerId;

	/** 
	* @Fields realName : TODO(姓名) 
	*/
	@NotEmpty(message = "姓名不能为空")
	private String realName;

	/** 
	* @Fields credentialType : TODO(证件类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	@NotEmpty(message = "证件类型不能为空")
	private String credentialType;

	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/
	@NotEmpty(message = "证件号码不能为空")
	private String credentialNo;

	/** 
	* @Fields relType : TODO(与申请人关系) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "relType")
	@NotEmpty(message = "与申请人关系不能为空")
	private String relType;

	/** 
	* @Fields phoneNum : TODO(联系电话) 
	*/
	@NotEmpty(message = "联系电话不能为空")
	private String phoneNum;

	/** 
	* @Fields address : TODO(联系人地址) 
	*/
	private String address;

	/** 
	* @Fields unit : TODO(单位名称) 
	*/
	private String unit;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
