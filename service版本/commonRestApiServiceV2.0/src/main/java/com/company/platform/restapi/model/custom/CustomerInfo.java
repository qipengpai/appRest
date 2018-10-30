package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: CustomerInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lifeng
 * @date 2018年1月30日 下午3:47:32
 * 
 */

@SuppressWarnings("all")
public class CustomerInfo extends BaseModel {

	/**
	 * @Fields customerId : TODO(申请人id)
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
	 * @Fields credentialType : TODO(证件类型)
	 */
	private String credentialType;
	/**
	 * @Fields credentialNo : TODO(证件号码)
	 */
	private String credentialNo;
	/**
	 * @Fields gender : TODO(性别)
	 */
	private String gender;
	/**
	 * @Fields createTime : TODO(注册日期，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String createTime;
	/**
	 * @Fields birthday : TODO(出生日期，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String birthday;
	/**
	 * @Fields hasChild : TODO(有无子女)
	 */
	private String hasChild;
	/**
	 * @Fields education : TODO(最高学历)
	 */
	private String education;
	/**
	 * @Fields martialStatus : TODO(婚姻状况)
	 */
	private String martialStatus;
	/**
	 * @Fields addressType : TODO(户籍地址类型，字典表对应registry)
	 */
	private String addressType;
	/**
	 * @Fields registryProvinceCode : TODO(户籍省编码)
	 */
	private String registryProvinceCode;
	/**
	 * @Fields registryCityCode : TODO(户籍市编码)
	 */
	private String registryCityCode;
	/**
	 * @Fields registryCountyCode : TODO(户籍区编码)
	 */
	private String registryCountyCode;
	/**
	 * @Fields registryAddress : TODO(户籍详细地址)
	 */
	private String registryAddress;
	/**
	 * @Fields addressType : TODO(住房地址类型，字典表对应live)
	 */
	private String liveAddressType;
	/**
	 * @Fields liveProvinceCode : TODO(住房省编码)
	 */
	private String liveProvinceCode;
	/**
	 * @Fields liveCityCode : TODO(住房市编码)
	 */
	private String liveCityCode;
	/**
	 * @Fields liveCountyCode : TODO(住房区编码)
	 */
	private String liveCountyCode;
	/**
	 * @Fields liveAddress : TODO(住房详细地址)
	 */
	private String liveAddress;
	/**
	 * @Fields currentWorkInfo : TODO(单位名称)
	 */
	private String currentWorkInfo;
	/**
	 * @Fields currentWorkType : TODO(单位性质)
	 */
	private String currentWorkType;
	/**
	 * @Fields currentWorkAge : TODO(工龄)
	 */
	private String currentWorkAge;
	/**
	 * @Fields totalWorkAge : TODO(总工龄（年）)
	 */
	private String totalWorkAge;
	/**
	 * @Fields currentPosition : TODO(职位名称)
	 */
	private String currentPosition;
	/**
	 * @Fields currentMobile : TODO(办公电话)
	 */
	private String currentMobile;
	/**
	 * @Fields workAddressType : TODO(单位地址类型,字典表对应work)
	 */
	private String workAddressType;
	/**
	 * @Fields workProvinceCode : TODO(单位省编码)
	 */
	private String workProvinceCode;
	/**
	 * @Fields workCityCode : TODO(单位市编码)
	 */
	private String workCityCode;
	/**
	 * @Fields workCountyCode : TODO(单位区编码)
	 */
	private String workCountyCode;
	/**
	 * @Fields workAddress : TODO(单位详细地址)
	 */
	private String workAddress;
	
	/** 
	* @Fields eType : TODO(客户类型) 
	*/ 
	private String eType;
	
	/** 
	* @Fields updateTime : TODO(更新时间) 
	*/ 
	private String updateTime;
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getRegistryProvinceCode() {
		return registryProvinceCode;
	}

	public void setRegistryProvinceCode(String registryProvinceCode) {
		this.registryProvinceCode = registryProvinceCode;
	}

	public String getRegistryCityCode() {
		return registryCityCode;
	}

	public void setRegistryCityCode(String registryCityCode) {
		this.registryCityCode = registryCityCode;
	}

	public String getRegistryCountyCode() {
		return registryCountyCode;
	}

	public void setRegistryCountyCode(String registryCountyCode) {
		this.registryCountyCode = registryCountyCode;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getLiveAddressType() {
		return liveAddressType;
	}

	public void setLiveAddressType(String liveAddressType) {
		this.liveAddressType = liveAddressType;
	}

	public String getLiveProvinceCode() {
		return liveProvinceCode;
	}

	public void setLiveProvinceCode(String liveProvinceCode) {
		this.liveProvinceCode = liveProvinceCode;
	}

	public String getLiveCityCode() {
		return liveCityCode;
	}

	public void setLiveCityCode(String liveCityCode) {
		this.liveCityCode = liveCityCode;
	}

	public String getLiveCountyCode() {
		return liveCountyCode;
	}

	public void setLiveCountyCode(String liveCountyCode) {
		this.liveCountyCode = liveCountyCode;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getCurrentWorkInfo() {
		return currentWorkInfo;
	}

	public void setCurrentWorkInfo(String currentWorkInfo) {
		this.currentWorkInfo = currentWorkInfo;
	}

	public String getCurrentWorkType() {
		return currentWorkType;
	}

	public void setCurrentWorkType(String currentWorkType) {
		this.currentWorkType = currentWorkType;
	}

	public String getCurrentWorkAge() {
		return currentWorkAge;
	}

	public void setCurrentWorkAge(String currentWorkAge) {
		this.currentWorkAge = currentWorkAge;
	}

	public String getTotalWorkAge() {
		return totalWorkAge;
	}

	public void setTotalWorkAge(String totalWorkAge) {
		this.totalWorkAge = totalWorkAge;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getCurrentMobile() {
		return currentMobile;
	}

	public void setCurrentMobile(String currentMobile) {
		this.currentMobile = currentMobile;
	}

	public String getWorkAddressType() {
		return workAddressType;
	}

	public void setWorkAddressType(String workAddressType) {
		this.workAddressType = workAddressType;
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

}
