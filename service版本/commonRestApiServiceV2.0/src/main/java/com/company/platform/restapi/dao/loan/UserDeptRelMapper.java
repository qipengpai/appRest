package com.company.platform.restapi.dao.loan;

import java.util.List;

/** 
* @ClassName: UserDeptRelMapper 
* @Description: TODO(获取组织id) 
* @author yangxu 
* @date 2018年2月2日 上午9:25:41 
*  
*/
public interface UserDeptRelMapper {
	/** 
	* @Title: queryOrgIdByUserId 
	* @Description: TODO(获取组织id) 
	* @param @param id(用户id)
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	List<String> getQueryOrgIdByUserId(String id);
}
