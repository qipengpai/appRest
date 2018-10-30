package com.company.platform.openRestApi.service.custom.imp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.util.MD5Util;
import com.company.platform.openRestApi.dao.custom.CustomResetPwdMapper;
import com.company.platform.openRestApi.model.custom.UserResetPwdMessage;
import com.company.platform.openRestApi.service.custom.ICustomResetPwdService;

/**
 * 
* @ClassName: CustomResetPwdServiceImp 
* @Description: TODO(用于重置密码) 
* @author zhengjn 
* @date 2017年10月27日 下午2:21:00 
*
 */


@Service
public class CustomResetPwdServiceImp implements ICustomResetPwdService{
	// 日志
	private final Logger logger = LoggerFactory.getLogger(CustomResetPwdServiceImp.class);
	
	@Autowired
	CustomResetPwdMapper customResetPwdMapper;
	
	/* (非 Javadoc) 
	* <p>Title: userResetPwd</p> 
	* <p>Description: </p> 
	* @param userRegistPwdMessage
	* @return 
	* @see com.company.platform.openRestApi.service.custom.ICustomResetPwdService#userResetPwd(com.company.platform.openRestApi.model.custom.UserResetPwdMessage) 
	*/
	@Override
	@CacheEvict(value = "securityusers", key = "#userResetPwdMessage.mobile")
	public String userResetPwd(UserResetPwdMessage userResetPwdMessage) {
		try {
			String password = userResetPwdMessage.getPassword();
			String mobile = userResetPwdMessage.getMobile();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("password", MD5Util.encode(password));
			map.put("username", mobile);
			customResetPwdMapper.updateUserPwd(map);
			return ResponseConstants.USER_UPDATE_PWD_SUCCESS.getRetCode();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			return ResponseConstants.USER_UPDATE_PWD_FAIL.getRetCode();
		}
	}
}
