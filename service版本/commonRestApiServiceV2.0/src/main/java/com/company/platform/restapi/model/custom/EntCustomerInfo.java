package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: entCustomerInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lifeng 
* @date 2018年1月30日 下午5:15:47 
*  
*/
@SuppressWarnings("all")
public class EntCustomerInfo extends BaseModel{
	
	/** 
	* @Fields customerId : TODO(客户id) 
	*/ 
	private String customerId;
	/** 
	* @Fields customerNo : TODO(客户编号) 
	*/ 
	private String customerNo;
	/** 
	* @Fields customerName : TODO(客户名称) 
	*/ 
	private String customerName;
	/** 
	* @Fields corporationName : TODO(企业全称) 
	*/ 
	private String corporationName;
	/** 
	* @Fields taxId : TODO(税务登记号) 
	*/ 
	private String taxId;
	/** 
	* @Fields operatorCertificatesType : TODO(证件类型) 
	*/ 
	private String operatorCertificatesType;
	/** 
	* @Fields operatorNumber : TODO(证件号码) 
	*/ 
	private String operatorNumber;
	/** 
	* @Fields industryType : TODO(所属行业类型) 
	*/ 
	private String industryType;
	/** 
	* @Fields staffCount : TODO(企业规模(人)) 
	*/ 
	private String staffCount;
	/** 
	* @Fields registerTime : TODO(注册日期，时间格式：yyyy-MM-dd hh:mm:ss) 
	*/ 
	private String registerTime;
	/** 
	* @Fields registered_capital : TODO(注册资本(万元)) 
	*/ 
	private String registered_capital;
	/** 
	* @Fields address_province : TODO(注册地址省编码) 
	*/ 
	private String address_province;
	/** 
	* @Fields address_city : TODO(注册地址市编码) 
	*/ 
	private String address_city;
	/** 
	* @Fields address_detailed : TODO(注册地址详细) 
	*/ 
	private String address_detailed;
	/** 
	* @Fields workProvinceCode : TODO(办公地址省编码) 
	*/ 
	private String workProvinceCode;
	/** 
	* @Fields workCityCode : TODO(办公地址市编码) 
	*/ 
	private String workCityCode;
	/** 
	* @Fields workCountyCode : TODO(办公地址区编码) 
	*/ 
	private String workCountyCode;
	/** 
	* @Fields workAddress : TODO(办公地址详细) 
	*/ 
	private String workAddress;
	/** 
	* @Fields orgId : TODO(所属机构id) 
	*/ 
	private String orgId;
	
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
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(String staffCount) {
		this.staffCount = staffCount;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getRegistered_capital() {
		return registered_capital;
	}
	public void setRegistered_capital(String registered_capital) {
		this.registered_capital = registered_capital;
	}
	public String getAddress_province() {
		return address_province;
	}
	public void setAddress_province(String address_province) {
		this.address_province = address_province;
	}
	public String getAddress_city() {
		return address_city;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public String getAddress_detailed() {
		return address_detailed;
	}
	public void setAddress_detailed(String address_detailed) {
		this.address_detailed = address_detailed;
	}
	public String getWorkProvinceCode() {
		return workProvinceCode;
	}
	public void setWorkProvinceCode(String workProvinceCode) {
		this.workProvinceCode = workProvinceCode;
	}
	public String getWorkCityCode() {
		return workCityCode;
	}
	public void setWorkCityCode(String workCityCode) {
		this.workCityCode = workCityCode;
	}
	public String getWorkCountyCode() {
		return workCountyCode;
	}
	public void setWorkCountyCode(String workCountyCode) {
		this.workCountyCode = workCountyCode;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
 
}
