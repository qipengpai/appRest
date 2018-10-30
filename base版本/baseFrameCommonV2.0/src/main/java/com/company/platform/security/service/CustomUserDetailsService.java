/**
 * 
 */
package com.company.platform.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.security.model.SecurityGrantedAuthority;
import com.company.platform.security.model.SecurityOrg;
import com.company.platform.security.model.SecurityPoster;
import com.company.platform.security.model.SecurityResource;
import com.company.platform.security.model.SecurityRole;
import com.company.platform.security.model.SecurityUser;

/**
 * @ClassName: CustomUserDetailsService
 * @Description: TODO(用户登陆认证)
 * @author zhengjn
 * @date 2017年9月28日 下午2:35:01
 * 
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	ISecurityService securityServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		HashMap<String, String> userMap = securityServiceImpl.findByUserName(username);
		if (userMap == null || userMap.isEmpty() || !userMap.containsKey("id")
				|| StringUtils.isEmpty(userMap.get("id").toString())) {
			throw new UsernameNotFoundException(username + "该账号不存在／无权限");
		}
		ArrayList<HashMap<String, String>> roleList = securityServiceImpl.findRoleByUserId(userMap.get("id"));
		List<SecurityRole> SecurityRole = new ArrayList<SecurityRole>();
		for (HashMap<String, String> tempRoleMap : roleList) {
			SecurityRole tempRoleBean = new SecurityRole();
			tempRoleBean.setId(tempRoleMap.get("id"));
			tempRoleBean.setRoleCode(tempRoleMap.get("roleCode"));
			tempRoleBean.setRoleName(tempRoleMap.get("roleName"));
			tempRoleBean.setRoleDesc(tempRoleMap.get("roleDesc"));
			tempRoleBean.setRoleType(tempRoleMap.get("roleType"));
			SecurityRole.add(tempRoleBean);
		}
		ArrayList<HashMap<String, String>> resourceList = securityServiceImpl
				.findResourceByRoleId(GLOBALCONFIG.DEFAULT_MANAGER_ROLE);
		List<SecurityResource> securityResource = new ArrayList<SecurityResource>();
		for (HashMap<String, String> tempResourceMap : resourceList) {
			SecurityResource tempResourceBean = new SecurityResource();
			tempResourceBean.setId(tempResourceMap.get("id"));
			tempResourceBean.setResourceCode(tempResourceMap.get("resourceCode"));
			tempResourceBean.setResourceName(tempResourceMap.get("resourceName"));
			tempResourceBean.setUrl(tempResourceMap.get("url"));
			tempResourceBean.setMethod(tempResourceMap.get("method"));
			tempResourceBean.setPid(tempResourceMap.get("pid"));
			tempResourceBean.setCategoryCode(String.valueOf(tempResourceMap.get("categoryCode")));
			tempResourceBean.setrSort(String.valueOf(tempResourceMap.get("rSort")));
			tempResourceBean.setrLevel(String.valueOf(tempResourceMap.get("rLevel")));
			tempResourceBean.setResourceIcon(tempResourceMap.get("resourceIcon"));
			tempResourceBean.setIsLeaf(String.valueOf(tempResourceMap.get("isLeaf")));
			securityResource.add(tempResourceBean);
		}

		ArrayList<HashMap<String, String>> orgList = securityServiceImpl.findOrgByUserId(userMap.get("id"));
		List<SecurityOrg> SecurityOrg = new ArrayList<SecurityOrg>();
		for (HashMap<String, String> tempOrgMap : orgList) {
			SecurityOrg tempOrgBean = new SecurityOrg();
			tempOrgBean.setId(tempOrgMap.get("id"));
			tempOrgBean.setOrgName(tempOrgMap.get("orgName"));
			tempOrgBean.setOrgCode(tempOrgMap.get("orgCode"));
			tempOrgBean.setPid(tempOrgMap.get("pid"));
			tempOrgBean.seteLevel(String.valueOf(tempOrgMap.get("eLevel")));
			tempOrgBean.setIsLeaf(String.valueOf(tempOrgMap.get("isLeaf")));
			tempOrgBean.seteSort(String.valueOf(tempOrgMap.get("eSort")));
			tempOrgBean.seteType(String.valueOf(tempOrgMap.get("eType")));
			SecurityOrg.add(tempOrgBean);
		}

		ArrayList<HashMap<String, String>> posterList = securityServiceImpl.findPosterByUserId(userMap.get("id"));
		List<SecurityPoster> SecurityPoster = new ArrayList<SecurityPoster>();
		for (HashMap<String, String> tempPosterMap : posterList) {
			SecurityPoster tempPosterBean = new SecurityPoster();
			tempPosterBean.setId(tempPosterMap.get("id"));
			tempPosterBean.setPosterCode(tempPosterMap.get("posterCode"));
			tempPosterBean.setPosterName(tempPosterMap.get("posterName"));
			SecurityPoster.add(tempPosterBean);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (SecurityResource tempResource : securityResource) {
			if (tempResource != null && tempResource.getResourceCode() != null) {
				GrantedAuthority grantedAuthority = new SecurityGrantedAuthority(tempResource.getUrl(),
						tempResource.getMethod());
				// 1：此处将权限信息添加到 GrantedAuthority
				// 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
				grantedAuthorities.add(grantedAuthority);
			}
		}

		SecurityUser securityUser = new SecurityUser(userMap.get("id"), userMap.get("userName"),
				userMap.get("passWord"), true, true, userMap.get("nickName"), userMap.get("realName"),
				userMap.get("gender"), userMap.get("officePhone"), userMap.get("mobile"), userMap.get("email"),
				userMap.get("noticeConfigure"), securityResource, SecurityRole, SecurityPoster, SecurityOrg,
				grantedAuthorities);
		return securityUser;
	}

}
