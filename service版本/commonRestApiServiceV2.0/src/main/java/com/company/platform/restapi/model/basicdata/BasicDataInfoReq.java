package com.company.platform.restapi.model.basicdata;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * 
 * @ClassName: BasicDataInfoReq
 * @Description: TODO(更新基础数据信息)
 * @author liang
 * @date 2018年1月24日 下午7:26:31
 *
 */
@SuppressWarnings("serial")
public class BasicDataInfoReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields userInfoUpdateTime : TODO(用户信息更新数据时间)
	 */
	@NotEmpty(message = "用户信息更新数据时间不能为空")
	private String userInfoUpdateTime;

	/**
	 * @Fields roleInfoUpdateTime : TODO(角色信息更新数据时间)
	 */
	@NotEmpty(message = "角色信息更新数据时间不能为空")
	private String roleInfoUpdateTime;

	/**
	 * @Fields posterInfoUpdateTime : TODO(岗位信息更新数据时间)
	 */
	@NotEmpty(message = "岗位信息更新数据时间不能为空")
	private String posterInfoUpdateTime;

	/**
	 * @Fields orgInfoUpdateTime : TODO(组织机构信息更新数据时间)
	 */
	@NotEmpty(message = "组织机构信息更新数据时间不能为空")
	private String orgInfoUpdateTime;

	/**
	 * @Fields dicRangeInfoUpdateTime : TODO(字典信息更新数据时间)
	 */
	@NotEmpty(message = "字典信息更新数据时间不能为空")
	private String dicRangeInfoUpdateTime;

	/**
	 * @Fields dicInfoUpdateTime : TODO(字典字段更新数据时间)
	 */
	@NotEmpty(message = "字典字段更新数据时间不能为空")
	private String dicInfoUpdateTime;

	/**
	 * @Fields configInfoUpdateTime : TODO(全局变量更新数据时间)
	 */
	@NotEmpty(message = "全局变量更新数据时间不能为空")
	private String configInfoUpdateTime;

	/**
	 * @Fields cityInfoUpdateTime : TODO(行政区划更新数据时间)
	 */
	@NotEmpty(message = "行政区划更新数据时间不能为空")
	private String cityInfoUpdateTime;

	/**
	 * @Fields productInfoUpdateTime : TODO(产品信息更新数据时间)
	 */
	@NotEmpty(message = "产品信息更新数据时间不能为空")
	private String productInfoUpdateTime;

	/**
	 * @Fields orgDeptInfoUpdateTime : TODO(产品组织机构更新数据时间)
	 */
	@NotEmpty(message = "产品组织机构更新数据时间不能为空")
	private String orgDeptInfoUpdateTime;

	/**
	 * @Fields procdefInfoUpdateTime : TODO(流程信息更新数据时间)
	 */
	@NotEmpty(message = "流程信息更新数据时间不能为空")
	private String procdefInfoUpdateTime;

	/**
	 * @Fields jurisdictionInfoUpdateTime : TODO(权限信息更新数据时间)
	 */
	@NotEmpty(message = "权限信息更新数据时间不能为空")
	private String jurisdictionInfoUpdateTime;

	public String getUserInfoUpdateTime() {
		return userInfoUpdateTime;
	}

	public void setUserInfoUpdateTime(String userInfoUpdateTime) {
		this.userInfoUpdateTime = userInfoUpdateTime;
	}

	public String getRoleInfoUpdateTime() {
		return roleInfoUpdateTime;
	}

	public void setRoleInfoUpdateTime(String roleInfoUpdateTime) {
		this.roleInfoUpdateTime = roleInfoUpdateTime;
	}

	public String getPosterInfoUpdateTime() {
		return posterInfoUpdateTime;
	}

	public void setPosterInfoUpdateTime(String posterInfoUpdateTime) {
		this.posterInfoUpdateTime = posterInfoUpdateTime;
	}

	public String getOrgInfoUpdateTime() {
		return orgInfoUpdateTime;
	}

	public void setOrgInfoUpdateTime(String orgInfoUpdateTime) {
		this.orgInfoUpdateTime = orgInfoUpdateTime;
	}

	public String getDicRangeInfoUpdateTime() {
		return dicRangeInfoUpdateTime;
	}

	public void setDicRangeInfoUpdateTime(String dicRangeInfoUpdateTime) {
		this.dicRangeInfoUpdateTime = dicRangeInfoUpdateTime;
	}

	public String getDicInfoUpdateTime() {
		return dicInfoUpdateTime;
	}

	public void setDicInfoUpdateTime(String dicInfoUpdateTime) {
		this.dicInfoUpdateTime = dicInfoUpdateTime;
	}

	public String getConfigInfoUpdateTime() {
		return configInfoUpdateTime;
	}

	public void setConfigInfoUpdateTime(String configInfoUpdateTime) {
		this.configInfoUpdateTime = configInfoUpdateTime;
	}

	public String getCityInfoUpdateTime() {
		return cityInfoUpdateTime;
	}

	public void setCityInfoUpdateTime(String cityInfoUpdateTime) {
		this.cityInfoUpdateTime = cityInfoUpdateTime;
	}

	public String getProductInfoUpdateTime() {
		return productInfoUpdateTime;
	}

	public void setProductInfoUpdateTime(String productInfoUpdateTime) {
		this.productInfoUpdateTime = productInfoUpdateTime;
	}

	public String getOrgDeptInfoUpdateTime() {
		return orgDeptInfoUpdateTime;
	}

	public void setOrgDeptInfoUpdateTime(String orgDeptInfoUpdateTime) {
		this.orgDeptInfoUpdateTime = orgDeptInfoUpdateTime;
	}

	public String getProcdefInfoUpdateTime() {
		return procdefInfoUpdateTime;
	}

	public void setProcdefInfoUpdateTime(String procdefInfoUpdateTime) {
		this.procdefInfoUpdateTime = procdefInfoUpdateTime;
	}

	public String getJurisdictionInfoUpdateTime() {
		return jurisdictionInfoUpdateTime;
	}

	public void setJurisdictionInfoUpdateTime(String jurisdictionInfoUpdateTime) {
		this.jurisdictionInfoUpdateTime = jurisdictionInfoUpdateTime;
	}

}
