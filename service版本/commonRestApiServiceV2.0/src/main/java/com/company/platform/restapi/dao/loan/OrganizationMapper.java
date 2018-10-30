package com.company.platform.restapi.dao.loan;

import com.company.platform.restapi.model.loan.OrganizationInfo;

/** 
* @ClassName: OrganizationMapper 
* @Description: TODO(组织机构情报) 
* @author yangxu 
* @date 2018年2月2日 下午1:46:02 
*  
*/
public interface OrganizationMapper {
	/** 
	* @Title: getOrgInfoByUserId 
	* @Description: TODO(获取组织机构情报) 
	* @param @param userId(组织机构Id)
	* @param @return    设定文件 
	* @return OrganizationInfo    返回类型 
	* @throws 
	*/
	OrganizationInfo getOrgInfoByPrimaryKey(String orgId);
}
