/******************************************************************
 *
 *    Package:     com.company.platform.security.service.impl
 *
 *    Filename:    SecurityServiceImpl.java
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
package com.company.platform.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.security.dao.SecurityMapper;
import com.company.platform.security.service.ISecurityService;

/**
 * @ClassName: SecurityServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月5日 下午7:26:53
 * 
 */
@Service
public class SecurityServiceImpl implements ISecurityService {

	@Autowired
	SecurityMapper securityMapper;

	@Override
	public HashMap<String, String> findByUserName(String username) {
		// TODO Auto-generated method stub
		return securityMapper.findByUserName(username);
	}

	@Override
	public ArrayList<HashMap<String, String>> findRoleByUserId(String id) {
		// TODO Auto-generated method stub
		return securityMapper.findRoleByUserId(id);
	}

	@Override
	public ArrayList<HashMap<String, String>> findResourceByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return securityMapper.findResourceByRoleId(roleId);
	}

	@Override
	public ArrayList<HashMap<String, String>> findOrgByUserId(String id) {
		// TODO Auto-generated method stub
		return securityMapper.findOrgByUserId(id);
	}

	@Override
	public ArrayList<HashMap<String, String>> findPosterByUserId(String id) {
		// TODO Auto-generated method stub
		return securityMapper.findPosterByUserId(id);
	}

	@Override
	public Map<String, Object> getUser(String openId) {
		return securityMapper.getUser(openId);
	}

}
