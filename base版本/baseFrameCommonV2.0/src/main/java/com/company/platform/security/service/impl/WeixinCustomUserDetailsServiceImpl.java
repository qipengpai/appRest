package com.company.platform.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.dao.sys.SysUserMapper;
import com.company.platform.security.dao.SecurityMapper;
import com.company.platform.security.model.SecurityOrg;
import com.company.platform.security.model.SecurityPoster;
import com.company.platform.security.model.SecurityResource;
import com.company.platform.security.model.SecurityRole;
import com.company.platform.security.model.SecurityUser;
import com.company.platform.security.service.ISecurityService;
import com.company.platform.security.service.WeixinCustomUserDetailsService;
/**
 * 微信登陆用户信息获取
* @ClassName: WeixinCustomUserDetailsServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 下午7:22:01 
*
 */
@Service
public class WeixinCustomUserDetailsServiceImpl implements WeixinCustomUserDetailsService {
	@Autowired
	private SysUserMapper sysUserMapper;
	private Map<String,SecurityUser>  sessionUser= new HashMap<String,SecurityUser>();
	private Map<String,String>  codeOrOpenId= new HashMap<String,String>();
	@Autowired
	ISecurityService securityServiceImpl;
	@Autowired
	SecurityMapper securityMapper;
	@Override
	public SecurityUser loadUserByUsername(String userName) {
		HashMap<String, String> userMap = securityServiceImpl.findByUserName(userName);
		if(userMap==null) return null;
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


		SecurityUser securityUser = new SecurityUser(userMap.get("id"), userMap.get("userName"),
				userMap.get("passWord"), true, true, userMap.get("nickName"), userMap.get("realName"),
				userMap.get("gender"), userMap.get("officePhone"), userMap.get("mobile"), userMap.get("email"),
				userMap.get("noticeConfigure"), securityResource, SecurityRole, SecurityPoster, SecurityOrg,
				null);
		return securityUser;
	
	}
	@Override
	public SecurityUser loadUserByOpenId(String openId) {

		Map<String,Object> user = securityServiceImpl.getUser(openId);
		if(user==null) return null;
		HashMap<String, String> userMap = securityServiceImpl.findByUserName(user.get("userName").toString());
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


		SecurityUser securityUser = new SecurityUser(userMap.get("id"), userMap.get("userName"),
				userMap.get("passWord"), true, true, userMap.get("nickName"), userMap.get("realName"),
				userMap.get("gender"), userMap.get("officePhone"), userMap.get("mobile"), userMap.get("email"),
				userMap.get("noticeConfigure"), securityResource, SecurityRole, SecurityPoster, SecurityOrg,
				null);
		securityUser.setOpenId(userMap.get("openId"));
		return securityUser;
	
	}

	
	
	@Override
	public SecurityUser getUserDetails(String openId) {
		
		return sessionUser.get(openId);
	}
	@Override
	public void setUserDetails(String openId, SecurityUser userDetails) {
		
		 sessionUser.put(openId, userDetails);
	}
	@Override
	public int upUserOpenId(String userName, String openId) {
		
		return sysUserMapper.upUserOpenId(userName, openId);
	}
	@Override
	public String getCodeOrOpenId(String code) {
		
		return codeOrOpenId.get(code);
	}
	@Override
	public void setCodeOrOpenId(String code, String openId) {
		codeOrOpenId.put(code, openId);
	}
	@Override
	public String roleTypeByUserId(String userId) {
		return securityMapper.getRoleTypeByUserId(userId);
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 清除用户缓存 清除数据
	 * @Date 9:30 2018/9/28
	 * @Param [openId]
	 * @return java.lang.Boolean
	 **/
    @Override
    public Boolean delUserDetails(String openId) {
        return null != sessionUser.remove(openId) && 1 == sysUserMapper.setUserInfoByOpenId(openId);
    }

}
