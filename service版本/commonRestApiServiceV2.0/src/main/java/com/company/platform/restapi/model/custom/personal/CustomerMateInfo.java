package com.company.platform.restapi.model.custom.personal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CustomerMateInfo 
* @Description: TODO(客户配偶信息) 
* @author 王雪
* @date 2018年6月1日 上午9:19:25 
*  
*/
@SuppressWarnings("all")
public class CustomerMateInfo extends BaseModel {

	/** 
	* @Fields id : TODO(客户id) 
	*/
	private String id;

	/** 
	* @Fields name : TODO(姓名) 
	*/
	@NotEmpty(message = "配偶姓名不能为空")
	private String name;

	/** 
	* @Fields credentialType : TODO(证件类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	@NotEmpty(message = "配偶证件类型不能为空")
	private String credentialType;

	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/
	@NotEmpty(message = "配偶证件号码不能为空")
	private String credentialNo;

	/** 
	* @Fields telephoneNumber : TODO(手机号) 
	*/
	@NotEmpty(message = "配偶手机号不能为空")
	private String telephoneNumber;

	/** 
	* @Fields remark : TODO(备注) 
	*/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
