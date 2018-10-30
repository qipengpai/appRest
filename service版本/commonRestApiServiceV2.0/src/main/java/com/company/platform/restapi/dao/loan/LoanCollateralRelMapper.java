package com.company.platform.restapi.dao.loan;

import com.company.platform.restapi.model.loan.LoanCollateralRel;

/** 
* @ClassName: LoanCollateralRelMapper 
* @Description: TODO(贷款押品关联信息) 
* @author yangxu 
* @date 2018年2月6日 下午4:49:53 
*  
*/
public interface LoanCollateralRelMapper {
	/** 
	* @Title: modify 
	* @Description: TODO(更新借贷押品关联信息) 
	* @param @param rel    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void modify(LoanCollateralRel rel);
}
