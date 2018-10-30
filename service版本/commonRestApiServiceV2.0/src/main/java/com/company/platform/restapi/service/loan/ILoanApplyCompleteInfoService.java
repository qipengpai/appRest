package com.company.platform.restapi.service.loan;

import javax.servlet.http.HttpServletRequest;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.deal.LoanProductApplyStorage;

/** 
* @ClassName: ILoanApplyCompleteInfoService 
* @Description: TODO(借贷信息最终处理) 
* @author yangxu 
* @date 2018年2月1日 下午2:45:20 
*  
*/
public interface ILoanApplyCompleteInfoService {

	/** 
	* @Title: applyCompleteInfoReqValidate 
	* @Description: TODO(校验最终借贷信息) 
	* @param @param LoanProductApplyStorage    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public FullLoanProductApplyInfo applyCompleteInfoReqValidate(
			LoanProductApplyStorage loanProductApplyCompleteInfoReq, HttpServletRequest request)
			throws BusinessException;

	/** 
	* @Title: insertapplyCompleteInfo 
	* @Description: TODO(提交最终借贷信息) 
	* @param @param LoanProductApplyStorage
	* @param @param request
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void insertapplyCompleteInfo(LoanProductApplyStorage loanProductApplyCompleteInfoReq,
			HttpServletRequest request) throws BusinessException;
}
