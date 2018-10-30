package com.company.platform.restapi.model.custom.personal;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: PersonalCustomerReq 
* @Description: TODO(保存个人客户信息) 
* @author 王雪 
* @date 2018年1月31日 下午2:24:30 
*  
*/
@SuppressWarnings("serial")
public class PersonalCustomerReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(客户id) 
	*/
	@NotEmpty(message = "客户id不能为空")
	private String id;

	/** 
	* @Fields customerOrgId : TODO(用户机构id不能为空) 
	*/
	@NotEmpty(message = "用户机构id不能为空")
	private String customerOrgId;

	/** 
	* @Fields personalCustomerBaseInfo : TODO(客户个人信息) 
	*/
	@Valid
	private PersonalCustomerBaseInfo personalCustomerBaseInfo;

	/** 
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	@Valid
	private List<CustomerLocationInfo> customerLocationInfo;

	/** 
	* @Fields customerContactInfo : TODO(客户联系信息) 
	*/
	@Valid
	private List<CustomerContactInfo> customerContactInfo;

	/** 
	* @Fields customerJobDetailInfo : TODO(客户工作信息) 
	*/
	@Valid
	private CustomerJobDetailInfo customerJobDetailInfo;

	/** 
	* @Fields customerRelationshipInfo : TODO(客户联系人信息) 
	*/
	@Valid
	private List<CustomerRelationshipInfo> customerRelationshipInfo;

	/** 
	* @Fields customerMateInfo : TODO(客户配偶信息) 
	*/
	@Valid
	private CustomerMateInfo customerMateInfo;

	/** 
	* @Fields customerAssetInfo : TODO(客户资产信息) 
	*/
	@Valid
	private CustomerAssetInfo customerAssetInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonalCustomerBaseInfo getPersonalCustomerBaseInfo() {
		return personalCustomerBaseInfo;
	}

	public void setPersonalCustomerBaseInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo) {
		this.personalCustomerBaseInfo = personalCustomerBaseInfo;
	}

	public List<CustomerLocationInfo> getCustomerLocationInfo() {
		return customerLocationInfo;
	}

	public void setCustomerLocationInfo(List<CustomerLocationInfo> customerLocationInfo) {
		this.customerLocationInfo = customerLocationInfo;
	}

	public List<CustomerContactInfo> getCustomerContactInfo() {
		return customerContactInfo;
	}

	public void setCustomerContactInfo(List<CustomerContactInfo> customerContactInfo) {
		this.customerContactInfo = customerContactInfo;
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

	public String getCustomerOrgId() {
		return customerOrgId;
	}

	public void setCustomerOrgId(String customerOrgId) {
		this.customerOrgId = customerOrgId;
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

}
