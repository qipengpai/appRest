package com.company.platform.restapi.model.loan.handled;

import java.util.List;

import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
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
import com.company.platform.restapi.model.guarantor.GuarantorInfo;

/**
 * @ClassName: LoanHandledInfoResp
 * @Description: TODO(已办任务详情信息)
 * @author 王雪
 * @date 2018年3月27日 上午11:10:50
 * 
 */
@SuppressWarnings("all")
public class LoanHandledInfoResp extends BaseHttpParamsResp {

	/**
	 * @Fields loanProductApplyInfo : TODO(借贷申请信息)
	 */
	private LoanProductApplyInfo loanProductApplyInfo;

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
	 * @Fields modelData : TODO(业务模型信息)
	 */
	private String modelData;

	/**
	 * @Fields imageInfo : TODO(影像资料信息)
	 */
	private String imageInfo;

	/**
	 * @Fields collateralInfo : TODO(担保人信息)
	 */
	private List<CollateralInfo> collateralInfo;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
	private List<GuarantorInfo> guarantorInfo;

	/** 
	* @Fields customerMateInfo : TODO(客户配偶信息) 
	*/
	private CustomerMateInfo customerMateInfo;

	/** 
	* @Fields customerAssetInfo : TODO(客户资产信息) 
	*/
	private CustomerAssetInfo customerAssetInfo;

	public LoanProductApplyInfo getLoanProductApplyInfo() {
		return loanProductApplyInfo;
	}

	public void setLoanProductApplyInfo(LoanProductApplyInfo loanProductApplyInfo) {
		this.loanProductApplyInfo = loanProductApplyInfo;
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

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
	}

	public String getImageInfo() {
		return imageInfo;
	}

	public void setImageInfo(String imageInfo) {
		this.imageInfo = imageInfo;
	}

	public List<CollateralInfo> getCollateralInfo() {
		return collateralInfo;
	}

	public void setCollateralInfo(List<CollateralInfo> collateralInfo) {
		this.collateralInfo = collateralInfo;
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
