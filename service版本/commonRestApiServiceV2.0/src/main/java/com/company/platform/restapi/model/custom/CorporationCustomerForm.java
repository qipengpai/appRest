package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * @ClassName: CorporationCustomerForm
 * @Description: TODO(企业用户相关信息)
 * @author yangxu
 * @date 2018年1月29日 下午5:16:20
 * 
 */
@SuppressWarnings("all")
public class CorporationCustomerForm extends BaseModel {

	/**
	 * @Fields customerId : TODO(客户Id)
	 */
	private String customerId;

	/**
	 * @Fields customerNo : TODO(客户编号)
	 */
	private String customerNo;

	/**
	 * @Fields customerName : TODO(客户姓名)
	 */
	private String customerName;

	/**
	 * @Fields ifRestrict : TODO(是否受限)
	 */
	private String ifRestrict;

	/**
	 * @Fields ifBlack : TODO(是否在黑名单中)
	 */
	private String ifBlack;

	/**
	 * @Fields orgId : TODO(所属机构Id)
	 */
	private String orgId;

	/**
	 * @Fields customerStatus : TODO(客户状态)
	 */
	@DictionaryStandardOrNot(dictionaryType = "customerStatus")
	private String customerStatus;

	/**
	 * @Fields credentialType : TODO(证件类型)
	 */
	@DictionaryStandardOrNot(dictionaryType = "corCredentialType")
	private String credentialType;

	/**
	 * @Fields credentialNo : TODO(证件号码)
	 */
	private String credentialNo;

	/**
	 * @Fields corporationName : TODO(企业全称)
	 */
	private String corporationName;

	/**
	 * @Fields taxId : TODO(税务登记号)
	 */
	private String taxId;

	/**
	 * @Fields staffCount : TODO(企业规模)
	 */
	private String staffCount;

	/**
	 * @Fields industryType : TODO(所属行业)
	 */
	@DictionaryStandardOrNot(dictionaryType = "industryType")
	private String industryType;

	/**
	 * @Fields registerTime : TODO(注册日期)
	 */
	private String registerTime;

	/**
	 * @Fields registerTime : TODO(注册资本)
	 */
	private String registerCapital;

	/**
	 * @Fields register_province : TODO(注册所在省)
	 */
	private String register_province;

	/**
	 * @Fields register_city : TODO(注册所在城市)
	 */
	private String register_city;

	/**
	 * @Fields register_county : TODO(注册所在县)
	 */
	private String register_county;

	/**
	 * @Fields business_address_province : TODO(业务所在省地址)
	 */
	private String business_address_province;

	/**
	 * @Fields business_address_city : TODO(业务所在城市地址)
	 */
	private String business_address_city;

	/**
	 * @Fields business_address_county : TODO(业务所在国家地址)
	 */
	private String business_address_county;

	/**
	 * @Fields business_address_detail : TODO(地址详细信息)
	 */
	private String business_address_detail;

	/**
	 * @Fields lrName : TODO(法人代表姓名)
	 */
	private String lrName;

	/**
	 * @Fields lrSex : TODO(法人代表性别)
	 */
	@DictionaryStandardOrNot(dictionaryType = "gender")
	private String lrSex;

	/**
	 * @Fields lrCertType : TODO(法人代表证件类型)
	 */
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	private String lrCertType;

	/**
	 * @Fields lrCertNo : TODO(法人代表证件号码)
	 */
	private String lrCertNo;

	/**
	 * @Fields lrAge : TODO(法人代表年龄)
	 */
	private Integer lrAge;

	/**
	 * @Fields lrMobile : TODO(法人代表手机号)
	 */
	private String lrMobile;

	/**
	 * @Fields license : TODO(营业执照号)
	 */
	private String license;

	/**
	 * @Fields beginRunTime : TODO(开始经营时间)
	 */
	private String beginRunTime;

	/**
	 * @Fields turnover : TODO(营业额)
	 */
	private String turnover;

	/**
	 * @Fields siteOwnership : TODO(经营场所所有权)
	 */
	private String siteOwnership;

	/**
	 * @Fields applicantType : TODO(申请人身份)
	 */
	@DictionaryStandardOrNot(dictionaryType = "applicantType")
	private String applicantType;

	/**
	 * @Fields telephone : TODO(申请人电话号)
	 */
	private String telephone;

	/**
	 * @Fields manageRange : TODO(生产经营范围)
	 */
	private String manageRange;

	/**
	 * @Fields operatorName : TODO(经办人姓名)
	 */
	private String operatorName;

	/**
	 * @Fields operatorPhoneNumber : TODO(经办人手机号码)
	 */
	private String operatorPhoneNumber;

	/**
	 * @Fields operatorCertificatesType : TODO(经办人证件类型)
	 */
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	private String operatorCertificatesType;

	/**
	 * @Fields operatorNumber : TODO(经办人证件号码)
	 */
	private String operatorNumber;

	/**
	 * @Fields operatorMaritalStatus : TODO(经办人婚姻状况)
	 */
	@DictionaryStandardOrNot(dictionaryType = "martialStatus")
	private String operatorMaritalStatus;

	/**
	 * @Fields operatorSex : TODO(经办人性别)
	 */
	@DictionaryStandardOrNot(dictionaryType = "gender")
	private String operatorSex;

	/**
	 * @Fields permanent_address_province : TODO(固定地址所在省)
	 */
	private String permanent_address_province;

	/**
	 * @Fields permanent_address_city : TODO(固定地址所在城市)
	 */
	private String permanent_address_city;

	/**
	 * @Fields permanent_address_county : TODO(固定地址所在县)
	 */
	private String permanent_address_county;

	/**
	 * @Fields permanent_address_detail : TODO(固定地址详细描述)
	 */
	private String permanent_address_detail;

	/**
	 * @Fields residence_address_province : TODO(居住地所在省)
	 */
	private String residence_address_province;

	/**
	 * @Fields residence_address_city : TODO(居住地所在城市)
	 */
	private String residence_address_city;

	/**
	 * @Fields residence_address_county : TODO(居住地所在县)
	 */
	private String residence_address_county;

	/**
	 * @Fields residence_address_detail : TODO(居住地址详细信息)
	 */
	private String residence_address_detail;

	/**
	 * @Fields operatorDuties : TODO(运营商职务)
	 */
	private String operatorDuties;

	/**
	 * @Fields addManager : TODO(判断是否添加客户经理)
	 */
	private String addManager;

	/**
	 * @Fields register_detail : TODO(注册地址 详情)
	 */
	private String register_detail;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIfRestrict() {
		return ifRestrict;
	}

	public void setIfRestrict(String ifRestrict) {
		this.ifRestrict = ifRestrict;
	}

	public String getIfBlack() {
		return ifBlack;
	}

	public void setIfBlack(String ifBlack) {
		this.ifBlack = ifBlack;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
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

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(String staffCount) {
		this.staffCount = staffCount;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getRegister_province() {
		return register_province;
	}

	public void setRegister_province(String register_province) {
		this.register_province = register_province;
	}

	public String getRegister_city() {
		return register_city;
	}

	public void setRegister_city(String register_city) {
		this.register_city = register_city;
	}

	public String getRegister_county() {
		return register_county;
	}

	public void setRegister_county(String register_county) {
		this.register_county = register_county;
	}

	public String getBusiness_address_province() {
		return business_address_province;
	}

	public void setBusiness_address_province(String business_address_province) {
		this.business_address_province = business_address_province;
	}

	public String getBusiness_address_city() {
		return business_address_city;
	}

	public void setBusiness_address_city(String business_address_city) {
		this.business_address_city = business_address_city;
	}

	public String getBusiness_address_county() {
		return business_address_county;
	}

	public void setBusiness_address_county(String business_address_county) {
		this.business_address_county = business_address_county;
	}

	public String getBusiness_address_detail() {
		return business_address_detail;
	}

	public void setBusiness_address_detail(String business_address_detail) {
		this.business_address_detail = business_address_detail;
	}

	public String getLrName() {
		return lrName;
	}

	public void setLrName(String lrName) {
		this.lrName = lrName;
	}

	public String getLrSex() {
		return lrSex;
	}

	public void setLrSex(String lrSex) {
		this.lrSex = lrSex;
	}

	public String getLrCertType() {
		return lrCertType;
	}

	public void setLrCertType(String lrCertType) {
		this.lrCertType = lrCertType;
	}

	public String getLrCertNo() {
		return lrCertNo;
	}

	public void setLrCertNo(String lrCertNo) {
		this.lrCertNo = lrCertNo;
	}

	public Integer getLrAge() {
		return lrAge;
	}

	public void setLrAge(Integer lrAge) {
		this.lrAge = lrAge;
	}

	public String getLrMobile() {
		return lrMobile;
	}

	public void setLrMobile(String lrMobile) {
		this.lrMobile = lrMobile;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getBeginRunTime() {
		return beginRunTime;
	}

	public void setBeginRunTime(String beginRunTime) {
		this.beginRunTime = beginRunTime;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getSiteOwnership() {
		return siteOwnership;
	}

	public void setSiteOwnership(String siteOwnership) {
		this.siteOwnership = siteOwnership;
	}

	public String getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getManageRange() {
		return manageRange;
	}

	public void setManageRange(String manageRange) {
		this.manageRange = manageRange;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorPhoneNumber() {
		return operatorPhoneNumber;
	}

	public void setOperatorPhoneNumber(String operatorPhoneNumber) {
		this.operatorPhoneNumber = operatorPhoneNumber;
	}

	public String getOperatorCertificatesType() {
		return operatorCertificatesType;
	}

	public void setOperatorCertificatesType(String operatorCertificatesType) {
		this.operatorCertificatesType = operatorCertificatesType;
	}

	public String getOperatorNumber() {
		return operatorNumber;
	}

	public void setOperatorNumber(String operatorNumber) {
		this.operatorNumber = operatorNumber;
	}

	public String getOperatorMaritalStatus() {
		return operatorMaritalStatus;
	}

	public void setOperatorMaritalStatus(String operatorMaritalStatus) {
		this.operatorMaritalStatus = operatorMaritalStatus;
	}

	public String getOperatorSex() {
		return operatorSex;
	}

	public void setOperatorSex(String operatorSex) {
		this.operatorSex = operatorSex;
	}

	public String getPermanent_address_province() {
		return permanent_address_province;
	}

	public void setPermanent_address_province(String permanent_address_province) {
		this.permanent_address_province = permanent_address_province;
	}

	public String getPermanent_address_city() {
		return permanent_address_city;
	}

	public void setPermanent_address_city(String permanent_address_city) {
		this.permanent_address_city = permanent_address_city;
	}

	public String getPermanent_address_county() {
		return permanent_address_county;
	}

	public void setPermanent_address_county(String permanent_address_county) {
		this.permanent_address_county = permanent_address_county;
	}

	public String getPermanent_address_detail() {
		return permanent_address_detail;
	}

	public void setPermanent_address_detail(String permanent_address_detail) {
		this.permanent_address_detail = permanent_address_detail;
	}

	public String getResidence_address_province() {
		return residence_address_province;
	}

	public void setResidence_address_province(String residence_address_province) {
		this.residence_address_province = residence_address_province;
	}

	public String getResidence_address_city() {
		return residence_address_city;
	}

	public void setResidence_address_city(String residence_address_city) {
		this.residence_address_city = residence_address_city;
	}

	public String getResidence_address_county() {
		return residence_address_county;
	}

	public void setResidence_address_county(String residence_address_county) {
		this.residence_address_county = residence_address_county;
	}

	public String getResidence_address_detail() {
		return residence_address_detail;
	}

	public void setResidence_address_detail(String residence_address_detail) {
		this.residence_address_detail = residence_address_detail;
	}

	public String getOperatorDuties() {
		return operatorDuties;
	}

	public void setOperatorDuties(String operatorDuties) {
		this.operatorDuties = operatorDuties;
	}

	public String getAddManager() {
		return addManager;
	}

	public void setAddManager(String addManager) {
		this.addManager = addManager;
	}

	public String getRegister_detail() {
		return register_detail;
	}

	public void setRegister_detail(String register_detail) {
		this.register_detail = register_detail;
	}

}
