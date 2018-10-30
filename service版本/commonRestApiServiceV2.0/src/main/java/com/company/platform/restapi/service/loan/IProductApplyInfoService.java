package com.company.platform.restapi.service.loan;

import java.util.List;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.ProductApplyInfoReq;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;

/** 
* @ClassName: IProductApplyInfoService 
* @Description: TODO(删除暂存的借贷申请信息) 
* @author yangxu 
* @date 2018年1月25日 下午3:56:40 
*  
*/
public interface IProductApplyInfoService {
	/**
	 * 
	* @Title: delTempInfo 
	* @Description: TODO(删除暂存的借贷申请信息) 
	* @param @param productApplyInfoReq (loanProductApplyId 借贷申请Id)
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return boolean    返回类型 true 删除成功； false 删除失败 
	* @throws
	 */
	public boolean delTempInfo(ProductApplyInfoReq productApplyInfoReq) throws BusinessException;

	/** 
	* @Title: modifyLoanProductApply 
	* @Description: TODO(暂存借贷申请信息) 
	* @param @param loanProductApplyInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void modifyLoanProductApply(LoanProductApplyInfo loanProductApplyInfo) throws BusinessException;

	/** 
	* @Title: saveLoanGuarantorInfo 
	* @Description: TODO(保存担保人信息) 
	* @param @param list    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveLoanGuarantorInfo(List<GuarantorInfo> list, String applyId);
}
