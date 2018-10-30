package com.company.platform.restapi.service.collateral;

import java.math.BigDecimal;
import java.util.List;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.collateral.CollateralInfo;

/** 
* @ClassName: ICollateralManageService 
* @Description: TODO(借贷押品Service) 
* @author 王雪 
* @date 2018年5月22日 上午10:04:00 
*  
*/
public interface ICollateralLoanService {

	/** 
	* @Title: getCollateralListByApplyId 
	* @Description: TODO(获取借贷申请时可引入的押品列表信息) 
	* @param @param applyId
	* @param @param orgList
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return List<CollateralInfo>    返回类型 
	* @throws 
	*/
	List<CollateralInfo> getCollateralListByApplyId(String applyId, List<String> orgList) throws BusinessException;

	/** 
	* @Title: insertLoanCollateralRel 
	* @Description: TODO(引入押品) 
	* @param @param applyId
	* @param @param id
	* @param @param esValue
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertLoanCollateralRel(String applyId, String id, String esValue);

	/** 
	* @Title: getLoanCollateralInfo 
	* @Description: TODO(获取押品展示信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return CollateralInfo    返回类型 
	* @throws 
	*/
	CollateralInfo getLoanCollateralInfo(String id);

	/** 
	* @Title: deleteLoanCollateralRel 
	* @Description: TODO(取消引入押品信息) 
	* @param @param collateralId
	* @param @param loanApplyId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void deleteLoanCollateralRel(String collateralId, String loanApplyId);
	
	/** 
	* @Title: getEstimateTotalValue 
	* @Description: TODO(获取借贷申请中押品人工总估值) 
	* @param @param loanApplyId
	* @param @return    设定文件 
	* @return BigDecimal    返回类型 
	* @throws 
	*/
	BigDecimal getEstimateTotalValue(String loanApplyId);
}
