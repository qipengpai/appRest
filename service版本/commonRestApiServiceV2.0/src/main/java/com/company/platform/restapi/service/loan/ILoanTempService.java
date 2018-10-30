package com.company.platform.restapi.service.loan;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.model.loan.LoanApplyInfoReq;
import com.company.platform.restapi.model.loan.LoanApplyInfoResp;

/** 
* @ClassName: ILoanTempService 
* @Description: TODO(借贷任务暂存信息操作接口) 
* @author luyuchi
* @date 2018年1月26日 下午1:50:26 
*  
*/
public interface ILoanTempService {

	/** 
	* @Title: queryLoanProductApplySynosByPage 
	* @Description: TODO(获得暂存列表) 
	* @param @param req
	* @param @param userId
	* @param @return    设定文件 
	* @return BaseHttpParamsPageResp    返回类型 
	* @throws 
	*/
	BaseHttpParamsPageResp queryLoanProductApplySynosByPage(BaseHttpParamsPageAppReq req, String userId) throws BusinessException;
	/** 
	* @Title: getCustomerInfo 
	* @Description: TODO(获取暂存列表详情) 
	* @param @param customerInfoReq
	* @param @param type
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return CustomerInfoResp    返回类型 
	* @throws 
	*/
	public LoanApplyInfoResp searchTempInfoById(LoanApplyInfoReq req, String type, String flag) throws BusinessException;

}
