package com.company.platform.restapi.dao.loan;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.OrgProductCredit;

/** 
* @ClassName: OrgProductCreditMapper 
* @Description: TODO(获取组织已使用配额信息) 
* @author yangxu 
* @date 2018年1月29日 上午10:14:44 
*  
*/

public interface OrgProductCreditMapper {
	/**
	 * 
	* @Title: selectByOrgIdAndLoanProductId 
	* @Description: TODO(根据组织机构Id与产品Id来获取组织已使用配额信息) 
	* @param @param info (组织机构Id与产品Id)
	* @param @return    设定文件 
	* @return OrgProductCredit    返回类型 
	* @throws
	 */
	OrgProductCredit selectByOrgIdAndLoanProductId(@Param("orgId") String orgId, @Param("loanProductId")String loanProductId);
}
