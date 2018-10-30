package com.company.platform.restapi.dao.collateral;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.collateral.CollateralInfo;

/** 
* @ClassName: CollateralLoanMapper 
* @Description: TODO(押品借贷mapper) 
* @author 王雪 
* @date 2018年5月25日 上午9:37:59 
*  
*/
public interface CollateralLoanMapper {

	/** 
	* @Title: getCollateralListByApplyId 
	* @Description: TODO(获取借贷申请时可引入的押品列表信息) 
	* @param @param customerId
	* @param @param list
	* @param @return    设定文件 
	* @return List<CollateralInfo>    返回类型 
	* @throws 
	*/
	List<CollateralInfo> getCollateralListByApplyId(@Param("customerId") String customerId,
			@Param("list") List<String> list);

	/** 
	* @Title: insertLoanCollateralRel 
	* @Description: TODO(引入押品) 
	* @param @param applyId
	* @param @param id
	* @param @param esValue
	* @param @param eOrder
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertLoanCollateralRel(@Param("applyId") String applyId, @Param("id") String id,
			@Param("esValue") String esValue, @Param("eOrder") String eOrder);

	/** 
	* @Title: selectLoanApplyNextEOrder 
	* @Description: TODO(获取当前借贷申请关联) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return Short    返回类型 
	* @throws 
	*/
	Short selectLoanApplyNextEOrder(String applyId);

	/** 
	* @Title: getLoanCollateralInfo 
	* @Description: TODO(获取押品显示信息) 
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
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteLoanCollateralRel(String collateralId);

	/** 
	* @Title: showCollateralListByApplyId 
	* @Description: TODO(展示借贷申请被引入押品信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return List<CollateralInfo>    返回类型 
	* @throws 
	*/
	List<CollateralInfo> showCollateralListByApplyId(String applyId);

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
