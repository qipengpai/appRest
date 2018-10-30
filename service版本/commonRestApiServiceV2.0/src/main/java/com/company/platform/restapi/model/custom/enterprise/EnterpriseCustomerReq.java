package com.company.platform.restapi.model.custom.enterprise;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;

/** 
* @ClassName: EnterpriseCustomerReq 
* @Description: TODO(保存企业客户信息) 
* @author 王雪 
* @date 2018年2月2日 下午3:47:34 
*  
*/
@SuppressWarnings("serial")
public class EnterpriseCustomerReq extends BaseHttpParamsAppReq {

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
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	@Valid
	private List<CustomerLocationInfo> customerLocationInfo;

	/** 
	* @Fields corporationCustomerContactinfo : TODO(企业客户联系人信息) 
	*/
	@Valid
	private List<CorporationCustomerContactinfo> corporationCustomerContactinfo;

	/** 
	* @Fields corporationCustomerShareholderInfo : TODO(企业客户股东信息) 
	*/
	@Valid
	private List<CorporationCustomerShareholderInfo> corporationCustomerShareholderInfo;

	/** 
	* @Fields corporationBaseAndLrInfo : TODO(企业法人信息) 
	*/
	@Valid
	private CorporationBaseAndLrInfo corporationBaseAndLrInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCustomerOrgId() {
		return customerOrgId;
	}

	public void setCustomerOrgId(String customerOrgId) {
		this.customerOrgId = customerOrgId;
	}

}
