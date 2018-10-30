package com.company.platform.restapi.service.loan;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoReq;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanSubmittedReq;


/** 
* @ClassName: ILoanSubmittedService 
* @Description: TODO(已提交客户贷款) 
* @author dongjian 
* @date 2018年5月18日 上午9:43:20 
*  
*/
public interface ILoanSubmittedService {

	/** 
	* @Title: queryLoanSubmitInfoByPage 
	* @Description: TODO(获得某位登录用户已提交任务) 
	* @param @param req
	* @param @param userId
	* @param @return    设定文件 
	* @return BaseHttpParamsPageResp    返回类型 
	* @throws 
	*/
	BaseHttpParamsPageResp queryLoanSubmitInfoByPage(LoanSubmittedReq req, String userId);
	
	/** 
	* @Title: searchSubmitInfoById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param req
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return LoanHandledInfoResp    返回类型 
	* @throws 
	*/
	public LoanHandledInfoResp searchSubmitInfoById(LoanHandledInfoReq req) throws BusinessException;
}
