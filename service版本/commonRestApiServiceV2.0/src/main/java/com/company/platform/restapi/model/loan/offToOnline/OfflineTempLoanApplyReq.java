package com.company.platform.restapi.model.loan.offToOnline;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;
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
* @ClassName: OfflineTempLoanApplyReq 
* @Description: TODO(连线转在线，保存借贷申请及客户信息) 
* @author 王雪 
* @date 2018年2月5日 下午2:42:30 
*  
*/
@SuppressWarnings("serial")
public class OfflineTempLoanApplyReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields customerType : TODO(客户类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "customerType")
	@NotEmpty(message = "客户类型不能为空")
	private String customerType;

	/** 
	* @Fields credentialType : TODO(证件类型) 
	*/
	@NotEmpty(message = "证件类型不能为空")
	private String credentialType;

	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/
	@Length(max = 32, message = "证件号码最多32个字符")
	@NotEmpty(message = "证件号码不能为空")
	private String credentialNo;

	/** 
	* @Fields name : TODO(客户姓名) 
	*/
	@Length(max = 20, message = "客户名称最多20个字符")
	@NotEmpty(message = "客户名称不能为空")
	private String name;

	/** 
	* @Fields customerOrgId : TODO(客户机构id) 
	*/
	@NotEmpty(message = "客户机构id不能为空")
	private String customerOrgId;

	/** 
	* @Fields loanProductApplyInfo : TODO(借贷产品申请信息) 
	*/
	@Valid
	private LoanProductApplyEntity loanProductApplyEntity;

	/** 
	* @Fields customerLocationInfo : TODO(客户地址信息) 
	*/
	@Valid
	private List<CustomerLocationInfo> customerLocationInfo;

	/** 
	* @Fields personalCustomerBaseInfo : TODO(客户个人信息) 
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
	 * @Fields modelData : TODO(模型数据)
	 */
	private String modelData;

	/** 
	* @Fields imgModelId : TODO(影像模型id) 
	*/
	private String imgModelId;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
	@Valid
	private List<GuarantorInfo> guarantorInfo;

	/** 
	* @Fields customerMateInfo : TODO(配偶信息) 
	*/
	private CustomerMateInfo customerMateInfo;

	/** 
	* @Fields customerAssetInfo : TODO(资产信息) 
	*/
	private CustomerAssetInfo customerAssetInfo;

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerOrgId() {
		return customerOrgId;
	}

	public void setCustomerOrgId(String customerOrgId) {
		this.customerOrgId = customerOrgId;
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

	public LoanProductApplyEntity getLoanProductApplyEntity() {
		return loanProductApplyEntity;
	}

	public void setLoanProductApplyEntity(LoanProductApplyEntity loanProductApplyEntity) {
		this.loanProductApplyEntity = loanProductApplyEntity;
	}

	public PersonalCustomerBaseInfo getPersonalCustomerBaseInfo() {
		return personalCustomerBaseInfo;
	}

	public void setPersonalCustomerBaseInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo) {
		this.personalCustomerBaseInfo = personalCustomerBaseInfo;
	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
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
