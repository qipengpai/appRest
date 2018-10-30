package com.company.platform.base.service.sys;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: IRoleService 
* @Description: TODO(角色信息操作) 
* @author luyuchi
* @date 2018年3月21日 下午1:06:10 
*  
*/
public interface IRoleService {

	/** 
	* @Title: queryRoleDetailByIds 
	* @Description: TODO(通过角色id集合获得多个角色信息) 
	* @param @param roleids
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryRoleDetailByIds(String roleids);

}
