package com.company.platform.openRestApi.dao.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: RegistAssistMapper 
* @Description: TODO(注册) 
* @author zhengjn 
* @date 2017年10月24日 下午5:07:09 
*
 */
public interface CustomRegisterMapper {
	/**
	 * 
	* @Title: addRegistUser 
	* @Description: TODO(插入sysuser记录) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void addRegistUser(Map<String, Object> info);

	/**
	 * 
	* @Title: getRoleByType 
	* @Description: TODO(查找sysrole对象) 
	* @param @param roleCode
	* @param @return    设定文件 
	* @return HashMap<String,Object>    返回类型 
	* @throws
	 */
	HashMap<String, Object> getRoleByType(String roleCode);

	/**
	 * 
	* @Title: addRegistUserRole 
	* @Description: TODO(添加sysuserrolerel记录) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void addRegistUserRole(Map<String, Object> info);
}
