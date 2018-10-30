package com.company.platform.restapi.model.custom.personal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CustomerContactInfo 
* @Description: TODO(客户联系信息) 
* @author 王雪 
* @date 2018年1月25日 上午11:36:10 
*  
*/
@SuppressWarnings("all")
public class CustomerContactInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields customerId : TODO(客户id) 
	*/
	private String customerId;

	/** 
	* @Fields etype : TODO(联系方式) 
	*/
	@NotEmpty(message = "联系方式不能为空")
	private String etype;

	/** 
	* @Fields contact : TODO(联系号码) 
	*/
	private String contact;

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

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
