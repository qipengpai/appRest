/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityUser.java
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

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName: SecurityUser
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年9月28日 下午2:37:45
 */
@SuppressWarnings("serial")
public class SecurityUser implements UserDetails {

	/**
	 * @Fields id : TODO(系统账号表id)
	 */
	private String id;

	/**
	 * @Fields username : TODO(登陆系统账号)
	 */
	private String username;

	/**
	 * @Fields password : TODO(登陆系统密码)
	 */
	private String password;

	/**
	 * @Fields securityResource : TODO(具有权限的系统可访问资源集合)
	 */
	private List<SecurityResource> securityResource;

	/**
	 * @Fields isEnabled : TODO(是否可用 0 可用 1 不可用)
	 */
	private boolean isEnabled;

	/**
	 * @Fields isAccountNonLocked : TODO(是否锁 0 未锁 1 已锁)
	 */
	private boolean isAccountNonLocked;

	/**
	 * @Fields nickName : TODO(用一句话描述这个变量表示什么)
	 */
	private String nickName;
	/**
	 * @Fields realName : TODO(真实姓名)
	 */
	private String realName;
	/**
	 * @Fields gender : TODO(性别)
	 */
	private String gender;
	/**
	 * @Fields officePhone : TODO(办公室电话)
	 */
	private String officePhone;
	/**
	 * @Fields mobile : TODO(手机)
	 */
	private String mobile;
	/**
	 * @Fields email : TODO(邮件)
	 */
	private String email;
	/**
	 * @Fields noticeConfigure : TODO(通知配置:通知类别“,”分割)
	 */
	private String noticeConfigure;
	/**
	 * @Fields noticeConfigure : TODO(openID)
	 */
	private String openId;

	/**
	 * @Fields SecurityRole : TODO(角色集合)
	 */
	private List<SecurityRole> SecurityRole;

	/**
	 * @Fields SecurityOrg : TODO(机构集合)
	 */
	private List<SecurityOrg> SecurityOrg;

	/**
	 * @Fields SecurityPoster : TODO(岗位集合)
	 */
	private List<SecurityPoster> SecurityPoster;

	private List<? extends GrantedAuthority> authorities;

	public SecurityUser() {
	}

	public SecurityUser(String id, String username, String password, boolean isEnabled, boolean isAccountNonLocked,
			String nickName, String realName, String gender, String officePhone, String mobile, String email,
			String noticeConfigure, List<SecurityResource> securityResource, List<SecurityRole> SecurityRole,
			List<SecurityPoster> SecurityPoster, List<SecurityOrg> SecurityOrg,
			List<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isAccountNonLocked = isAccountNonLocked;
		this.nickName = nickName;
		this.realName = realName;
		this.gender = gender;
		this.officePhone = officePhone;
		this.mobile = mobile;
		this.email = email;
		this.noticeConfigure = noticeConfigure;
		this.securityResource = securityResource;
		this.SecurityRole = SecurityRole;
		this.SecurityOrg = SecurityOrg;
		this.SecurityPoster = SecurityPoster;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SecurityResource> getSecurityResource() {
		return securityResource;
	}

	public List<SecurityRole> getSecurityRole() {
		return SecurityRole;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNoticeConfigure() {
		return noticeConfigure;
	}

	public void setNoticeConfigure(String noticeConfigure) {
		this.noticeConfigure = noticeConfigure;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecurityResource(List<SecurityResource> securityResource) {
		this.securityResource = securityResource;
	}

	public void setSecurityRole(List<SecurityRole> securityRole) {
		SecurityRole = securityRole;
	}

	public void setAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public List<SecurityOrg> getSecurityOrg() {
		return SecurityOrg;
	}

	public void setSecurityOrg(List<SecurityOrg> securityOrg) {
		SecurityOrg = securityOrg;
	}

	/**
	 * 重写比较方法,SpringSecurity根据用户名来比较是否同一个用户
	 */
	@Override
	public boolean equals(Object o) {
		if (o.toString().equals(this.username))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public List<SecurityPoster> getSecurityPoster() {
		return SecurityPoster;
	}

	public void setSecurityPoster(List<SecurityPoster> securityPoster) {
		SecurityPoster = securityPoster;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
