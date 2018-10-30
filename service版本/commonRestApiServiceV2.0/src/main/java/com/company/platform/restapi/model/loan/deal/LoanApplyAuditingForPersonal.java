package com.company.platform.restapi.model.loan.deal;

import java.util.List;

import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerJobDetailInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;

public class LoanApplyAuditingForPersonal extends LoanApplyAuditingInfo {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 6307474517204035821L;

	/** 
	* @Fields customerPublicInfo : TODO(客户基本信息) 
	*/
	private CustomerPublicInfo customerPublicInfo;

	/** 
	* @Fields customerContactInfo : TODO(客户联系信息) 
	*/
	private List<CustomerContactInfo> customerContactInfo;

	/** 
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	private List<CustomerLocationInfo> customerLocationInfo;

	/** 
	* @Fields customerJobDetailInfo : TODO(客户工作信息) 
	*/
	private CustomerJobDetailInfo customerJobDetailInfo;

	/** 
	* @Fields customerRelationshipInfo : TODO(客户联系人信息) 
	*/
	private List<CustomerRelationshipInfo> customerRelationshipInfo;

	/** 
	* @Fields personalCustomerBaseInfo : TODO(客户个人基本信息) 
	*/
	private PersonalCustomerBaseInfo personalCustomerBaseInfo;

	/** 
	* @Fields customerMateInfo : TODO(客户配偶信息) 
	*/
	private CustomerMateInfo customerMateInfo;

	/** 
	* @Fields customerAssetInfo : TODO(客户资产信息) 
	*/
	private CustomerAssetInfo customerAssetInfo;

	public CustomerPublicInfo getCustomerPublicInfo() {
		return customerPublicInfo;
	}

	public void setCustomerPublicInfo(CustomerPublicInfo customerPublicInfo) {
		this.customerPublicInfo = customerPublicInfo;
	}

	public List<CustomerContactInfo> getCustomerContactInfo() {
		return customerContactInfo;
	}

	public void setCustomerContactInfo(List<CustomerContactInfo> customerContactInfo) {
		this.customerContactInfo = customerContactInfo;
	}

	public List<CustomerLocationInfo> getCustomerLocationInfo() {
		return customerLocationInfo;
	}

	public void setCustomerLocationInfo(List<CustomerLocationInfo> customerLocationInfo) {
		this.customerLocationInfo = customerLocationInfo;
	}

	public CustomerJobDetailInfo getCustomerJobDetailInfo() {
		return customerJobDetailInfo;
	}

	public void setCustomerJobDetailInfo(CustomerJobDetailInfo customerJobDetailInfo) {
		this.customerJobDetailInfo = customerJobDetailInfo;
	}

	public List<CustomerRelationshipInfo> getCustomerRelationshipInfo() {
		return customerRelationshipInfo;
	}

	public void setCustomerRelationshipInfo(List<CustomerRelationshipInfo> customerRelationshipInfo) {
		this.customerRelationshipInfo = customerRelationshipInfo;
	}

	public PersonalCustomerBaseInfo getPersonalCustomerBaseInfo() {
		return personalCustomerBaseInfo;
	}

	public void setPersonalCustomerBaseInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo) {
		this.personalCustomerBaseInfo = personalCustomerBaseInfo;
	}

	public CustomerMateInfo getCustomerMateInfo() {
		return customerMateInfo;
	}

	public void setCustomerMateInfo(CustomerMateInfo customerMateInfo) {
		this.customerMateInfo = customerMateInfo;
	}

	public CustomerAssetInfo getCustomerAssetInfo() {
		return customerAssetInfo;
	}

	public void setCustomerAssetInfo(CustomerAssetInfo customerAssetInfo) {
		this.customerAssetInfo = customerAssetInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
