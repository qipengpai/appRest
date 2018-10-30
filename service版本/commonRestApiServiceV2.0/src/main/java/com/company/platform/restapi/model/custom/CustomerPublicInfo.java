package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * 
* @ClassName: CustomerPublicInfo 
* @Description: TODO(客户基本信息) 
* @author 王雪 
* @date 2018年1月25日 上午10:42:21 
*
 */
@SuppressWarnings("all")
public class CustomerPublicInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键，客户id) 
	*/
	private String id;

	/**
	* @Fields customerNo : TODO(客户编号) 
	*/
	private String customerNo;

	/** 
	* @Fields customerName : TODO(客户名称) 
	*/
	private String customerName;

	/** 
	* @Fields credentialType : TODO(客户证件类型) 
	*/
	private String credentialType;

	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/
	private String credentialNo;

	/** 
	* @Fields eType : TODO(客户类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "customerType")
	private String eType;

	/** 
	* @Fields workType : TODO(工作性质) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "jobNature")
	private String workType;

	/** 
	* @Fields orgId : TODO(客户所属机构id) 
	*/
	private String orgId;
	
	/** 
	* @Fields orgName : TODO(客户所属机构名称) 
	*/
	private String orgName;

	/** 
	* @Fields eStatus : TODO(客户状态) 
	*/
	private String eStatus;

	/** 
	* @Fields deleteStatus : TODO(注销状态) 
	*/
	private String deleteStatus;

	/** 
	* @Fields isBlackList : TODO(是否在黑名单) 
	*/
	private String isBlackList;

	/** 
	* @Fields isRisk : TODO(0：非风险客户；1：风险客户) 
	*/
	private String isRisk;

	/** 
	* @Fields createTime : TODO(创建时间) 
	*/
	private String createTime;

	/** 
	* @Fields createUserId : TODO(创建平台用户名) 
	*/
	private String createUserId;

	/** 
	* @Fields isRestricted : TODO(是否受限) 
	*/
	private String isRestricted;

	/** 
	* @Fields authority : TODO(可见度控制) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "customerAuthority")
	private String authority;

	/** 
	* @Fields availableCredit : TODO(可用额度) 
	*/
	private String availableCredit;

	/** 
	* @Fields usedCredit : TODO(已用额度) 
	*/
	private String usedCredit;

	/** 
	* @Fields creditStatus : TODO(额度状态) 
	*/
	private String creditStatus;

	/** 
	* @Fields cusManagerId : TODO(客户经理 ID) 
	*/
	private String cusManagerId;

	/** 
	* @Fields authenticationStatus : TODO(人脸身份验证状态 0：未通过 1：通过) 
	*/
	private String authenticationStatus;

	/** 
	* @Fields authenticationName : TODO(人脸身份验证姓名) 
	*/
	private String authenticationName;

	/** 
	* @Fields authenticationNo : TODO(人脸身份验证身份证号) 
	*/
	private String authenticationNo;

	/** 
	* @Fields noticeConfigure : TODO(通知配置:通知类别“,”分割) 
	*/
	private String noticeConfigure;

	/** 
	* @Fields overDueType : TODO(授信逾期标识 0：正常 1：逾期未还) 
	*/
	private String overDueType;

	/** 
	* @Fields updateTime : TODO(更新时间) 
	*/
	private String updateTime;
	
	private String realNameAuthStatus;
	
	/** 
	* @Fields mobilePhone : TODO(手机号码) 
	*/
	private String mobilePhone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String geteStatus() {
		return eStatus;
	}

	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getIsBlackList() {
		return isBlackList;
	}

	public void setIsBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}

	public String getIsRisk() {
		return isRisk;
	}

	public void setIsRisk(String isRisk) {
		this.isRisk = isRisk;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getIsRestricted() {
		return isRestricted;
	}

	public void setIsRestricted(String isRestricted) {
		this.isRestricted = isRestricted;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAvailableCredit() {
		return availableCredit;
	}

	public void setAvailableCredit(String availableCredit) {
		this.availableCredit = availableCredit;
	}

	public String getUsedCredit() {
		return usedCredit;
	}

	public void setUsedCredit(String usedCredit) {
		this.usedCredit = usedCredit;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public String getCusManagerId() {
		return cusManagerId;
	}

	public void setCusManagerId(String cusManagerId) {
		this.cusManagerId = cusManagerId;
	}

	public String getAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(String authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	public String getAuthenticationName() {
		return authenticationName;
	}

	public void setAuthenticationName(String authenticationName) {
		this.authenticationName = authenticationName;
	}

	public String getAuthenticationNo() {
		return authenticationNo;
	}

	public void setAuthenticationNo(String authenticationNo) {
		this.authenticationNo = authenticationNo;
	}

	public String getNoticeConfigure() {
		return noticeConfigure;
	}

	public void setNoticeConfigure(String noticeConfigure) {
		this.noticeConfigure = noticeConfigure;
	}

	public String getOverDueType() {
		return overDueType;
	}

	public void setOverDueType(String overDueType) {
		this.overDueType = overDueType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRealNameAuthStatus() {
		return realNameAuthStatus;
	}

	public void setRealNameAuthStatus(String realNameAuthStatus) {
		this.realNameAuthStatus = realNameAuthStatus;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
}
