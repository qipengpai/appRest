package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: OrgInfo
 * @Description: TODO(当前用户组织机构信息)
 * @author liang
 * @date 2018年1月25日 下午5:32:00
 * 
 */
@SuppressWarnings("all")
public class OrgInfo extends BaseModel{

	/**
	 * @Fields orgId : TODO(组织机构id)
	 */
	private String orgId;

	/**
	 * @Fields orgId : TODO(组织机构名称)
	 */
	private String orgName;

	/**
	 * @Fields orgCode : TODO(机构编码)
	 */
	private String orgCode;
	
	/**
	 * @Fields orgType : TODO(机构类型)
	 */
	private String orgType;

	/**
	 * @Fields orgInfoUpdateTime : TODO(组织机构更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String orgInfoUpdateTime;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgInfoUpdateTime() {
		return orgInfoUpdateTime;
	}

	public void setOrgInfoUpdateTime(String orgInfoUpdateTime) {
		this.orgInfoUpdateTime = orgInfoUpdateTime;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	

}
