package com.company.platform.restapi.dao.loan;


import com.company.platform.restapi.model.loan.LoanProductApplyContractInfo;


/** 
* @ClassName: LoanProductApplyContractMapper 
* @Description: TODO(借贷申请合同信息) 
* @author yangxu 
* @date 2018年2月6日 下午4:52:11 
*  
*/
public interface LoanProductApplyContractMapper {
	 /** 
	* @Title: insertApplyContractInfo 
	* @Description: TODO(插入借贷申请合同信息) 
	* @param @param LoanProductApplyContractInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void insertApplyContractInfo(LoanProductApplyContractInfo LoanProductApplyContractInfo);
}
