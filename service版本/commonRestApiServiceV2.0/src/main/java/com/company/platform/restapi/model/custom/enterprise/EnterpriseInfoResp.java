package com.company.platform.restapi.model.custom.enterprise;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;

/** 
* @ClassName: EnterpriseInfoResp 
* @Description: TODO(企业客户信息结果集) 
* @author 王雪 
* @date 2018年1月26日 上午9:20:41 
*  
*/
@SuppressWarnings("all")
public class EnterpriseInfoResp extends BaseModel {

	/** 
	* @Fields customerPublicInfo : TODO(客户基本信息) 
	*/
	private CustomerPublicInfo customerPublicInfo;

	/** 
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	private List<CustomerLocationInfo> customerLocationInfo;

	/** 
	* @Fields corporationCustomerContactinformat : TODO(企业客户联系人信息) 
	*/
	private List<CorporationCustomerContactinfo> corporationCustomerContactinfo;

	/** 
	* @Fields corporationCustomerShareholderInfo : TODO(企业客户股东信息) 
	*/
	private List<CorporationCustomerShareholderInfo> corporationCustomerShareholderInfo;

	/** 
	* @Fields corporationBaseAndLrInfo : TODO(企业法人和企业基本信息) 
	*/
	private CorporationBaseAndLrInfo corporationBaseAndLrInfo;

	public CustomerPublicInfo getCustomerPublicInfo() {
		return customerPublicInfo;
	}

	public void setCustomerPublicInfo(CustomerPublicInfo customerPublicInfo) {
		this.customerPublicInfo = customerPublicInfo;
	}

	public List<CustomerLocationInfo> getCustomerLocationInfo() {
		return customerLocationInfo;
	}

	public void setCustomerLocationInfo(List<CustomerLocationInfo> customerLocationInfo) {
		this.customerLocationInfo = customerLocationInfo;
	}

	public List<CorporationCustomerContactinfo> getCorporationCustomerContactinfo() {
		return corporationCustomerContactinfo;
	}

	public void setCorporationCustomerContactinfo(List<CorporationCustomerContactinfo> corporationCustomerContactinfo) {
		this.corporationCustomerContactinfo = corporationCustomerContactinfo;
	}

	public List<CorporationCustomerShareholderInfo> getCorporationCustomerShareholderInfo() {
		return corporationCustomerShareholderInfo;
	}

	public void setCorporationCustomerShareholderInfo(
			List<CorporationCustomerShareholderInfo> corporationCustomerShareholderInfo) {
		this.corporationCustomerShareholderInfo = corporationCustomerShareholderInfo;
	}

	public CorporationBaseAndLrInfo getCorporationBaseAndLrInfo() {
		return corporationBaseAndLrInfo;
	}

	public void setCorporationBaseAndLrInfo(CorporationBaseAndLrInfo corporationBaseAndLrInfo) {
		this.corporationBaseAndLrInfo = corporationBaseAndLrInfo;
	}

}
