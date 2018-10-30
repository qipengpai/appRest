package com.company.platform.restapi.dao.loan;

import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.PersonalCustomerInfo;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoResp;

/** 
* @ClassName: OneSetpApplyInfoMapper 
* @Description: TODO(第一步借贷信息取得与提交) 
* @author yangxu 
* @date 2018年1月28日 下午1:34:46 
*  
*/
public interface OneSetpApplyInfoMapper {
	/**
	 * 
	* @Title: getOneSetpApplyInfo 
	* @Description: TODO(通过借贷id,获得输出的的借贷信息) 
	* @param @param info (借贷申请Id)
	* @param @return    设定文件 
	* @return OneSetpApplyInfoResp  返回类型 
	* @throws
	 */
	OneSetpApplyInfoResp getOneSetpApplyInfo(String id);
	
	/**
	 * 
	* @Title: insertProductApplyInfo 
	* @Description: TODO(通过产品Id插入相应的借贷信息) 
	* @param @param info (产品Id等)
	* @param @return    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void insertProductApplyInfo(FullLoanProductApplyInfo loanProductApplyInfo);	
	/**
	 * 
	* @Title: insertCustomerInfo 
	* @Description: TODO(根据客户Id插入相应的客户信息) 
	* @param @param customerPublicInfo (客户Id等)
	* @param @return    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void insertCustomerInfo(CustomerPublicInfo customerPublicInfo);
	
	/**
	 * 
	* @Title: insertPersonalCustomerInfo 
	* @Description: TODO(根据客户Id插入相应的客户信息) 
	* @param @param personalCustomerInfo (个人客户Id等)
	* @param @return    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void insertPersonalCustomerInfo(PersonalCustomerInfo personalCustomerInfo);
	
}
