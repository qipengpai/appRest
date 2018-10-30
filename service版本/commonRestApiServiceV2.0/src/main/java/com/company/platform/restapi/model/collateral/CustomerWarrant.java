package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CustomerWarrant 
* @Description: TODO(权属人信息) 
* @author 王雪 
* @date 2018年5月23日 上午11:42:23 
*  
*/
@SuppressWarnings("all")
public class CustomerWarrant extends BaseModel {

	/** 
	* @Fields id : TODO(权属人id) 
	*/
	private String id;

	/** 
	* @Fields customerName : TODO(权属人姓名) 
	*/
	private String customerName;

	/** 
	* @Fields credentialNo : TODO(权属人证件号码) 
	*/
	private String credentialNo;

	/** 
	* @Fields customerDesc : TODO(权属人显示信息) 
	*/
	private String customerDesc;
	/** 
	* @Fields orgName : TODO(所在组织机构) 
	*/
	private String orgName;

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

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getCustomerDesc() {
		return customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
