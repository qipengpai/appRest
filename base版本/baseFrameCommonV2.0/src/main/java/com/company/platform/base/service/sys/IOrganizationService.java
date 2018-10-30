package com.company.platform.base.service.sys;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: IOrganizationService 
* @Description: TODO(组织机构信息操作) 
* @author luyuchi
* @date 2018年3月21日 下午1:02:21 
*  
*/
public interface IOrganizationService {

	/** 
	* @Title: queryOrganizationByOrgIds 
	* @Description: TODO(通过机构id集合获得多个机构信息) 
	* @param @param organizationids
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryOrganizationByOrgIds(String organizationids);

	List<String> queryByVisibleOrgIds(String childOrgId);

	List<Map<String, Object>> queryStaffByOrgId(String organizationids);

}
