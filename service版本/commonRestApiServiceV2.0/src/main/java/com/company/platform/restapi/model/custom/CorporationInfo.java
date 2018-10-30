package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CorporationInfo 
* @Description: TODO(企业客户基本信息) 
* @author yangxu 
* @date 2018年1月29日 下午4:02:42 
*  
*/
@SuppressWarnings("all")
public class CorporationInfo extends BaseModel {
	/** 
	* @Fields id : TODO(产品id) 
	*/ 
	private String	id;
	/** 
	* @Fields customerName : TODO(客户名称) 
	*/ 
	private String	customerName;
	/** 
	* @Fields corporationName : TODO(企业客户全称) 
	*/ 
	private String	corporationName;
	/** 
	* @Fields industryType : TODO(所属行业类型) 
	*/ 
	private String	industryType;
	/** 
	* @Fields registerTime : TODO(注册时间) 
	*/ 
	private String	registerTime;
	/** 
	* @Fields registerCapital : TODO(注册资本(万元)) 
	*/ 
	private String	registerCapital;
	/** 
	* @Fields license : TODO(营业执照号) 
	*/ 
	private String	license;
	/** 
	* @Fields taxId : TODO(税务登记号) 
	*/ 
	private String	taxId;
	/** 
	* @Fields orgNo : TODO(组织机构代码号) 
	*/ 
	private String	orgNo;
	/** 
	* @Fields businessType : TODO(企业经营范围类型) 
	*/ 
	private String	businessType;
	/** 
	* @Fields lrName : TODO(法人代表姓名) 
	*/ 
	private String	lrName;
	/** 
	* @Fields lrSex : TODO(法人代表性别) 
	*/ 
	private String	lrSex;
	/** 
	* @Fields lrAge : TODO(法人代表年龄) 
	*/ 
	private String	lrAge;
	/** 
	* @Fields lrCountry : TODO(法人代表国籍) 
	*/ 
	private String	lrCountry;
	/** 
	* @Fields lrCertNo : TODO(法人代表证件号码) 
	*/ 
	private String	lrCertNo;
	/** 
	* @Fields lrMobile : TODO(法人代表联系方式) 
	*/ 
	private String	lrMobile;
	/** 
	* @Fields lrEduType : TODO(法人代表学历) 
	*/ 
	private String	lrEduType;
	/** 
	* @Fields lrPhone : TODO(法人代表办公电话) 
	*/ 
	private String	lrPhone;
	/** 
	* @Fields lrCertType : TODO(法人代表证件类型) 
	*/ 
	private String	lrCertType;
	/** 
	* @Fields staffCount : TODO(员工数) 
	*/ 
	private String	staffCount;
	/** 
	* @Fields turnover : TODO(营业额) 
	*/ 
	private String	turnover;
	/** 
	* @Fields applicantType : TODO(申请人身份) 
	*/ 
	private String	applicantType;
	/** 
	* @Fields siteOwnership : TODO(经营场所所有权) 
	*/ 
	private String	siteOwnership;
	/** 
	* @Fields beginRunTime : TODO(开始经营时间) 
	*/ 
	private String	beginRunTime;
	/** 
	* @Fields manageRange : TODO(生产经营范围) 
	*/ 
	private String	manageRange;
	/** 
	* @Fields telephone : TODO(座机号码) 
	*/ 
	private String	telephone;
	
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
	public String getCorporationName() {
		return corporationName;
	}
	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
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
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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
	public String getLrAge() {
		return lrAge;
	}
	public void setLrAge(String lrAge) {
		this.lrAge = lrAge;
	}
	public String getLrCountry() {
		return lrCountry;
	}
	public void setLrCountry(String lrCountry) {
		this.lrCountry = lrCountry;
	}
	public String getLrCertNo() {
		return lrCertNo;
	}
	public void setLrCertNo(String lrCertNo) {
		this.lrCertNo = lrCertNo;
	}
	public String getLrMobile() {
		return lrMobile;
	}
	public void setLrMobile(String lrMobile) {
		this.lrMobile = lrMobile;
	}
	public String getLrEduType() {
		return lrEduType;
	}
	public void setLrEduType(String lrEduType) {
		this.lrEduType = lrEduType;
	}
	public String getLrPhone() {
		return lrPhone;
	}
	public void setLrPhone(String lrPhone) {
		this.lrPhone = lrPhone;
	}
	public String getLrCertType() {
		return lrCertType;
	}
	public void setLrCertType(String lrCertType) {
		this.lrCertType = lrCertType;
	}
	public String getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(String staffCount) {
		this.staffCount = staffCount;
	}
	public String getTurnover() {
		return turnover;
	}
	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	public String getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}
	public String getSiteOwnership() {
		return siteOwnership;
	}
	public void setSiteOwnership(String siteOwnership) {
		this.siteOwnership = siteOwnership;
	}
	public String getBeginRunTime() {
		return beginRunTime;
	}
	public void setBeginRunTime(String beginRunTime) {
		this.beginRunTime = beginRunTime;
	}
	public String getManageRange() {
		return manageRange;
	}
	public void setManageRange(String manageRange) {
		this.manageRange = manageRange;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
