package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: ContactsInfo 
* @Description: TODO(企业用户的联系人信息) 
* @author lifeng 
* @date 2018年1月30日 下午5:35:31 
*  
*/
@SuppressWarnings("all")
public class ContactsInfo  extends BaseModel {
	
	/** 
	* @Fields certificateNnumber : TODO(证件号码) 
	*/ 
	private String certificateNnumber;
	/** 
	* @Fields company : TODO(所在公司) 
	*/ 
	private String company;
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
	private String name;
	/** 
	* @Fields relationship : TODO(关系) 
	*/ 
	private String relationship;
	/** 
	* @Fields telephone : TODO(电话) 
	*/ 
	private String telephone;
	/** 
	* @Fields typeOfCertificate : TODO(证件类型) 
	*/ 
	private String typeOfCertificate;
	
	public String getCertificateNnumber() {
		return certificateNnumber;
	}
	public void setCertificateNnumber(String certificateNnumber) {
		this.certificateNnumber = certificateNnumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTypeOfCertificate() {
		return typeOfCertificate;
	}
	public void setTypeOfCertificate(String typeOfCertificate) {
		this.typeOfCertificate = typeOfCertificate;
	}
	
}
