package com.company.platform.restapi.service.loan;

import java.util.List;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.loan.LoanProductApplyFeeInfo;
import com.company.platform.restapi.model.loan.fee.LoanApplyFeeInfo;
import com.company.platform.restapi.model.loan.fee.LoanProductApplyFeeReq;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;

/** 
* @ClassName: ILoanApplyFeeService 
* @Description: TODO(产品费用相关信息处理) 
* @author yangxu 
* @date 2018年1月30日 下午3:42:06 
*  
*/
public interface ILoanApplyFeeService {

	/** 
	* @Title: getLoanApplyFeeInfo 
	* @Description: TODO(获取产品费用信息) 
	* @param @param loanProductApplyFeeReq
	* @param @throws BusinessException    设定文件 
	* @return List<LoanProductApplyFeeInfo>    返回类型 
	* @throws 
	*/
	public List<LoanProductApplyFeeInfo> getLoanApplyFeeInfo(LoanProductApplyFeeReq loanProductApplyFeeReq)
			throws BusinessException;

	/** 
	* @Title: getLoanFeeInfo 
	* @Description: TODO(获取产品费用信息) 
	* @param @param loanApplyFeeReq
	* @param @throws BusinessException    设定文件 
	* @return List<LoanProductApplyFeeInfo>    返回类型 
	* @throws 
	*/
	public List<LoanApplyFeeInfo> getLoanFeeInfo(LoanProductApplyFeeReq loanProductApplyFeeReq)
			throws BusinessException;

	/** 
	* @Title: saveLoanApplyFeeInfo 
	* @Description: TODO(借贷申请费用信息保存) 
	* @param @param apply
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveLoanApplyFeeInfo(LoanProductApplyEntity apply) throws BusinessException;

	/** 
	* @Title: dealLoanApplyFeeInfo 
	* @Description: TODO(处理费用信息) 
	* @param @param applyBefore 原借贷申请信息
	* @param @param loanProductApplyInfo 新借贷申请信息
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void dealLoanApplyFeeInfo(LoanProductApplyEntity applyBefore, LoanProductApplyInfo loanProductApplyInfo)
			throws BusinessException;

}
