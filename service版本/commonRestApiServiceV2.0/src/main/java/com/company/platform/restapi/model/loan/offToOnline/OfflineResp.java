package com.company.platform.restapi.model.loan.offToOnline;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationBaseAndLrInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerContactinfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerShareholderInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerJobDetailInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;

/** 
* @ClassName: OfflineCode 
* @Description: TODO(离线转在线编码) 
* @author 王雪 
* @date 2018年2月6日 下午2:34:37 
*  
*/
@SuppressWarnings("all")
public class OfflineResp extends BaseModel {

	/** 
	* @Fields loanProductId : TODO(借贷申请id) 
	*/
	private String loanProductApplyId;

	/** 
	* @Fields code : TODO(借贷编码) 
	*/
	private String code;

	/** 
	* @Fields updateTime : TODO(借款申请更新时间)
	*/
	private String updateTime;

	/** 
	* @Fields busModelId : TODO(业务模型id) 
	*/
	private String busModelId;

	/** 
	* @Fields imgModelId : TODO(影像模型id) 
	*/
	private String imgModelId;

	/** 
	* @Fields customerPublicInfo : TODO(客户公共基础信息) 
	*/
	private CustomerPublicInfo customerPublicInfo;

	/** 
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	private List<CustomerLocationInfo> customerLocationInfo;

	/** 
	* @Fields personalCustomerBaseInfo : TODO(客户个人基本信息) 
	*/
	private PersonalCustomerBaseInfo personalCustomerBaseInfo;

	/** 
	* @Fields customerContactInfo : TODO(客户联系信息) 
	*/
	private List<CustomerContactInfo> customerContactInfo;

	/** 
	* @Fields customerJobDetailInfo : TODO(客户工作信息) 
	*/
	private CustomerJobDetailInfo customerJobDetailInfo;

	/** 
	* @Fields customerRelationshipInfo : TODO(客户联系人信息) 
	*/
	private List<CustomerRelationshipInfo> customerRelationshipInfo;

	/** 
	* @Fields corporationCustomerContactinfo : TODO(企业客户联系人信息) 
	*/
	private List<CorporationCustomerContactinfo> corporationCustomerContactinfo;

	/** 
	* @Fields corporationCustomerShareholderInfo : TODO(企业客户股东信息) 
	*/
	private List<CorporationCustomerShareholderInfo> corporationCustomerShareholderInfo;

	/** 
	* @Fields corporationBaseAndLrInfo : TODO(企业法人信息) 
	*/
	private CorporationBaseAndLrInfo corporationBaseAndLrInfo;

	/** 
	* @Fields customerMateInfo : TODO(客户配偶信息) 
	*/
	private CustomerMateInfo customerMateInfo;

	/** 
	* @Fields customerAssetInfo : TODO(客户资产信息) 
	*/
	private CustomerAssetInfo customerAssetInfo;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

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

	public PersonalCustomerBaseInfo getPersonalCustomerBaseInfo() {
		return personalCustomerBaseInfo;
	}

	public void setPersonalCustomerBaseInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo) {
		this.personalCustomerBaseInfo = personalCustomerBaseInfo;
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

	public String getBusModelId() {
		return busModelId;
	}

	public void setBusModelId(String busModelId) {
		this.busModelId = busModelId;
	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
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
