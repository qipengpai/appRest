package com.company.platform.restapi.service.loan;

import javax.servlet.http.HttpServletRequest;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoReq;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoResp;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: OneSetpApplyInfoService 
* @Description: TODO(贷款申请第一步提交) 
* @author yangxu 
* @date 2018年1月28日 下午1:13:49 
*  
*/
public interface IOneSetpApplyInfoService {
	
	/**
	 * @return 
	 * 
	* @Title: insertProductApplyInfo 
	* @Description: TODO(更新借贷申请信息) 
	* @param @param OneSetpApplyInfoReq (loanProductId 产品Id)
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public FullLoanProductApplyInfo insertProductApplyInfo(OneSetpApplyInfoReq oneSetpApplyInfoReq,SecurityUser user, HttpServletRequest request) throws BusinessException;
	
	/**
	 * 
	* @Title: getOneSetpApplyInfo 
	* @Description: TODO(获取借贷申请信息) 
	* @param @param String id
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return OneSetpApplyInfoResp    返回类型 
	* @throws
	 */
	public OneSetpApplyInfoResp getOneSetpApplyInfo(String id) throws BusinessException;
	
	
	/**
	 * 
	* @Title: oneSetpApplyInfoReqValidate 
	* @Description: TODO(获取借贷申请信息) 
	* @param @param OneSetpApplyInfoReq
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return OneSetpApplyInfoReq    返回类型 
	* @throws
	 */
	public OneSetpApplyInfoReq oneSetpApplyInfoReqValidate(OneSetpApplyInfoReq oneSetpApplyInfoReq) throws BusinessException;

}
