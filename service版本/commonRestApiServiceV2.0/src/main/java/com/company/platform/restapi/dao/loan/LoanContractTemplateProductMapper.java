package com.company.platform.restapi.dao.loan;

import java.util.List;

import com.company.platform.restapi.model.loan.LoanContractTemplateProductInfo;

/** 
* @ClassName: LoanContractTemplateProductMapper 
* @Description: TODO(合同模板信息) 
* @author yangxu 
* @date 2018年2月6日 下午4:51:11 
*  
*/
public interface LoanContractTemplateProductMapper {

	/** 
	* @Title: listLoanContractTemplateProductByProductId 
	* @Description: TODO(查询合同模板信息) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<LoanContractTemplateProductInfo>    返回类型 
	* @throws 
	*/
	List<LoanContractTemplateProductInfo> listLoanContractTemplateProductByProductId(String productId);
}
