package com.company.platform.restapi.service.loan;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoReq;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoResp;

/** 
* @ClassName: ILoanHandledService 
* @Description: TODO(已办任务操作接口) 
* @author luyuchi
* @date 2018年1月26日 下午1:51:46 
*  
*/
public interface ILoanHandledService {

	/** 
	* @Title: queryLoanHandledInfoByPage 
	* @Description: TODO(获得某位登录用户已办任务) 
	* @param @param req
	* @param @param userId
	* @param @return    设定文件 
	* @return BaseHttpParamsPageResp    返回类型 
	* @throws 
	*/
	BaseHttpParamsPageResp queryLoanHandledInfoByPage(BaseHttpParamsPageAppReq req, String userId);
	
	/** 
	* @Title: searchHandleInfoById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param req
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return LoanHandledInfoResp    返回类型 
	* @throws 
	*/
	public LoanHandledInfoResp searchHandleInfoById(LoanHandledInfoReq req) throws BusinessException;
}
