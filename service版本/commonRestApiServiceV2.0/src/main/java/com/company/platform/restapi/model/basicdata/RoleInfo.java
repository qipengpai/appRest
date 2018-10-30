package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: RoleInfo
 * @Description: TODO(用户信息)
 * @author liang
 * @date 2018年1月25日 下午5:22:38
 * 
 */
@SuppressWarnings("all")
public class RoleInfo extends BaseModel{

	/**
	 * @Fields roleId : TODO(角色id)
	 */
	private String roleId;

	/**
	 * @Fields roleId : TODO(角色名称)
	 */
	private String roleName;

	/**
	 * @Fields roleCode : TODO(角色编码)
	 */
	private String roleCode;

	/**
	 * @Fields roleInfoUpdateTime : TODO(角色更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String roleInfoUpdateTime;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleInfoUpdateTime() {
		return roleInfoUpdateTime;
	}

	public void setRoleInfoUpdateTime(String roleInfoUpdateTime) {
		this.roleInfoUpdateTime = roleInfoUpdateTime;
	}

}
