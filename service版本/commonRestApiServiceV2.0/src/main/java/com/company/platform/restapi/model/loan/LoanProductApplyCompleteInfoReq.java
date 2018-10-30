package com.company.platform.restapi.model.loan;

import java.util.List;

import javax.validation.Valid;

//import com.alibaba.fastjson.annotation.JSONField;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
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
import com.company.platform.restapi.model.guarantor.GuarantorInfo;

/**
 * @ClassName: LoanProductApplyCompleteInfoReq
 * @Description: TODO(提交的借贷申请信息结果集)
 * @author yangxu
 * @date 2018年2月1日 下午2:38:00
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyCompleteInfoReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields LoanProductApplyInfo : TODO(借贷申请信息)
	 */
	@Valid
	private FullLoanProductApplyInfo loanProductApplyInfo;

	/**
	 * @Fields modelData : TODO(模型数据 json字符串)
	 */
	private String modelData;

	/**
	 * @Fields auths : TODO(权限标识 json字符串)
	 */
	private String auths;

	/** 
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	@Valid
	private List<CustomerLocationInfo> customerLocationInfo;

	// ======================个人客户信息========================

	/** 
	* @Fields personalCustomerInfo : TODO(客户个人信息) 
	*/
	@Valid
	private PersonalCustomerBaseInfo personalCustomerBaseInfo;

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

	// ======================企业客户信息========================

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

	/** 
	* @Fields nextTaskKey : TODO(下一节点流程key) 
	*/
	private String nextTaskKey;

	/** 
	* @Fields auditUserId : TODO(下一节点审核人id) 
	*/
	private String auditUserId;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
	@Valid
	private List<GuarantorInfo> guarantorInfo;

	/** 
	* @Fields customerMateInfo : TODO(配偶信息) 
	*/
	@Valid
	private CustomerMateInfo customerMateInfo;

	/** 
	* @Fields customerAssetInfo : TODO(资产信息) 
	*/
	@Valid
	private CustomerAssetInfo customerAssetInfo;

	public FullLoanProductApplyInfo getLoanProductApplyInfo() {
		return loanProductApplyInfo;
	}

	public void setLoanProductApplyInfo(FullLoanProductApplyInfo loanProductApplyInfo) {
		this.loanProductApplyInfo = loanProductApplyInfo;
	}

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
	}

	public String getAuths() {
		return auths;
	}

	public void setAuths(String auths) {
		this.auths = auths;
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

	public String getNextTaskKey() {
		return nextTaskKey;
	}

	public void setNextTaskKey(String nextTaskKey) {
		this.nextTaskKey = nextTaskKey;
	}

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public List<GuarantorInfo> getGuarantorInfo() {
		return guarantorInfo;
	}

	public void setGuarantorInfo(List<GuarantorInfo> guarantorInfo) {
		this.guarantorInfo = guarantorInfo;
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
