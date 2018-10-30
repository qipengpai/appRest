package com.company.platform.restapi.service.setup.imp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.util.MD5Util;
import com.company.platform.restapi.dao.setup.MobileSetUpMapper;
import com.company.platform.restapi.model.setup.AppVersion;
import com.company.platform.restapi.model.setup.AppVersionDetection;
import com.company.platform.restapi.model.setup.ModifyPwdMessage;
import com.company.platform.restapi.service.setup.IMobileSetUpService;
import com.company.platform.security.model.SecurityUser;

/**
 * @ClassName: MobileSetUpServiceImp
 * @Description: TODO(app设置页面功能汇总)
 * @author zhengjn
 * @date 2017年11月2日 下午4:25:44
 * 
 */
@Service
public class MobileSetUpServiceImp implements IMobileSetUpService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(MobileSetUpServiceImp.class);

	@Autowired
	MobileSetUpMapper mobileSetUpMapper;

	@Override
	public String userModifyPwd(ModifyPwdMessage modifyPwdMessage) throws BusinessException {
		String newPassword = modifyPwdMessage.getNewPassword();
		String oldPassword = modifyPwdMessage.getOldPassword();
		String userId = ((SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		Map<String, Object> userMap = mobileSetUpMapper.getUserById(userId);
		if (!(userMap.get("passWord").toString()).equals(MD5Util.encode(oldPassword))) {
			logger.error("原密码错误");
			throw new BusinessException(ResponseConstants.USER_MODIFY_PWD_WRONG.getRetCode(),
					ResponseConstants.USER_MODIFY_PWD_WRONG.getRetMsg());
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("passWord", MD5Util.encode(newPassword));
			map.put("id", userId);
			mobileSetUpMapper.modifyPwd(map);
			return ResponseConstants.USER_MODIFY_PWD_SUCCESS.getRetCode();
		}
	}

	/*
	 * (非 Javadoc) <p>Title: appVersionDetection</p> <p>Description: </p>
	 * 
	 * @param appVersionDetection
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.setup.IMobileSetUpService#
	 * appVersionDetection(com.company.platform.restapi.model.setup.
	 * AppVersionDetection)
	 */
	@Override
	public AppVersion appVersionDetection(AppVersionDetection appVersionDetection) {
		String appType = appVersionDetection.getAppType();
		String appName = appVersionDetection.getAppName();
		Map<String, String> info = new HashMap<String, String>();
		info.put("appType", appType);
		info.put("appName", appName);
		AppVersion appVer = mobileSetUpMapper.getAppVserionByType(info);
		return appVer;
	}
}
