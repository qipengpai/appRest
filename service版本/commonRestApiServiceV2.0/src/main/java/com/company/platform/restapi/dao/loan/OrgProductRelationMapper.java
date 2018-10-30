package com.company.platform.restapi.dao.loan;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.OrgProductRelation;

/** 
* @ClassName: OrgProductRelationMapper 
* @Description: TODO(获取组织机构的可用额度) 
* @author yangxu 
* @date 2018年1月29日 上午10:01:57 
*  
*/
public interface OrgProductRelationMapper {
	/**
	 * 
	* @Title: selectByOrgIdAndLoanProductId 
	* @Description: TODO(根据组织机构Id与产品Id来获取产品机构关联信息) 
	* @param @param info (组织机构Id与产品Id)
	* @param @return    设定文件 
	* @return OrgProductRelation    返回类型 
	* @throws
	 */
	OrgProductRelation selectByOrgIdAndLoanProductId(@Param("orgId") String orgId, @Param("loanProductId") String loanProductId);
}
