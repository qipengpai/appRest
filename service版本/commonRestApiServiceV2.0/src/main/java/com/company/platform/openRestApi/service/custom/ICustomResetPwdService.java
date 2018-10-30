package com.company.platform.openRestApi.service.custom;

import com.company.platform.openRestApi.model.custom.UserResetPwdMessage;

/**
 * 
* @ClassName: ICustomResetPwdService 
* @Description: TODO(重置密码) 
* @author zhengjn 
* @date 2017年10月27日 下午2:18:30 
*
 */
public interface ICustomResetPwdService {
	/**
	 * 
	* @Title: userResetPwd 
	* @Description: TODO(重置密码) 
	* @param @param userResetPwdMessage
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	String userResetPwd(UserResetPwdMessage userResetPwdMessage);
}
