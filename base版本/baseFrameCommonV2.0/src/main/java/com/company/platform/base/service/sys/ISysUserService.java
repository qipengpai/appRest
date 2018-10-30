package com.company.platform.base.service.sys;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: ISysUserService 
* @Description: TODO(系统用户信息操作) 
* @author luyuchi
* @date 2018年3月21日 下午1:06:48 
*  
*/
public interface ISysUserService {

	/** 
	* @Title: queryStaffDetailByIds 
	* @Description: TODO(通过用户id集合获得多个用户信息) 
	* @param @param userids
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryStaffDetailByIds(String userids);

	List<Map<String, Object>> queryUsersByOrgsAndRoles(List<String> orgIds, String roleids);

	List<Map<String, Object>> queryUsersByOrgsAndPosts(List<String> orgIds, String posterids);

}
