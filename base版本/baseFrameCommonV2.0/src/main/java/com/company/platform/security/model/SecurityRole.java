/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityRole.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.security.model;

import java.io.Serializable;

/**
 * @ClassName: SecurityRole
 * @Description: TODO(系统角色表)
 * @author zhengjn
 * @date 2017年9月28日 下午3:19:00
 * 
 */
@SuppressWarnings("serial")
public class SecurityRole implements Serializable {

	private String id;// '主键'

	private String roleCode;// '角色编码'

	private String roleName;// '角色名称'

	private String roleDesc;// '描述'

	private String isValid;// '是否可用 1 可用 0 不可用'

	private String roleType;// '角色类型'

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}
