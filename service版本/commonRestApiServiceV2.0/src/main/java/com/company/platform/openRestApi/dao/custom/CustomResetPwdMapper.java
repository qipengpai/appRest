package com.company.platform.openRestApi.dao.custom;

import java.util.Map;


/**
 * 
* @ClassName: CustomResetPwdMapper 
* @Description: TODO(重置密码) 
* @author zhengjn 
* @date 2017年10月27日 下午2:29:04 
*
 */
public interface CustomResetPwdMapper {
	/**
	 * 
	* @Title: resetPwd 
	* @Description: TODO(重置密码) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void updateUserPwd(Map<String, Object> info);
}
