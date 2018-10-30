package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;

/** 
* @ClassName: ProductInfoMapper 
* @Description: TODO(获取产品信息) 
* @author yangxu 
* @date 2018年1月29日 上午11:02:03 
*  
*/
public interface ProductInfoMapper {
	/**
	 * 
	* @Title: queryByPrimaryId 
	* @Description: TODO(根据产品Id获取产品信息) 
	* @param @param info (产品Id)
	* @param @return    设定文件 
	* @return ProductInfo    返回类型 
	* @throws
	 */
	LoanProductInfo queryByPrimaryId(String id);

	/** 
	* @Title: selectLoanProductFeeConditionsByLoanProductId 
	* @Description: TODO(产品id) 
	* @param @param id
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> selectLoanProductFeeConditionsByLoanProductId(String id);

	/** 
	* @Title: getLoanProductApplyById 
	* @Description: TODO(根据借贷申请id获取借贷信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return LoanProductApplyEntity    返回类型 
	* @throws 
	*/
	LoanProductApplyEntity getLoanProductApplyById(String id);

	/** 
	* @Title: getLoanProductRule 
	* @Description: TODO(根据产品id获取产品关联的规则类型) 
	* @param @param loanProductId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getLoanProductRule(String loanProductId);
	
	/** 
	* @Title: getRepayType 
	* @Description: TODO(获取还款方式) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getRepayType();
	
}
