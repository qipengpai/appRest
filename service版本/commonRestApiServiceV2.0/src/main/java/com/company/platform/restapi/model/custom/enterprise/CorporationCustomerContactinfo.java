package com.company.platform.restapi.model.custom.enterprise;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CorporationCustomerContactinfo 
* @Description: TODO(企业客户联系人信息) 
* @author 王雪 
* @date 2018年1月26日 上午10:49:15 
*  
*/
@SuppressWarnings("all")
public class CorporationCustomerContactinfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields customerId : TODO(客户id) 
	*/
	private String customerId;

	/** 
	* @Fields name : TODO(姓名) 
	*/
	@NotEmpty(message = "姓名不能为空")
	private String name;

	/** 
	* @Fields relationship : TODO(与客户关系) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "relType")
	@NotEmpty(message = "与客户关系不能为空")
	private String relationship;

	/** 
	* @Fields telephone : TODO(电话号码) 
	*/
	@NotEmpty(message = "电话号码不能为空")
	private String telephone;

	/** 
	* @Fields company : TODO(公司) 
	*/
	private String company;

	/** 
	* @Fields typeOfCertificate : TODO(证件类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	private String typeOfCertificate;

	/** 
	* @Fields certificateNnumber : TODO(证件号码) 
	*/
	private String certificateNnumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTypeOfCertificate() {
		return typeOfCertificate;
	}

	public void setTypeOfCertificate(String typeOfCertificate) {
		this.typeOfCertificate = typeOfCertificate;
	}

	public String getCertificateNnumber() {
		return certificateNnumber;
	}

	public void setCertificateNnumber(String certificateNnumber) {
		this.certificateNnumber = certificateNnumber;
	}

}
