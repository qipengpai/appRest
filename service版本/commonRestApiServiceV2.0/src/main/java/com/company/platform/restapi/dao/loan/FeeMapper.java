package com.company.platform.restapi.dao.loan;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.FeeCondition;
import com.company.platform.restapi.model.loan.LoanProductApplyFeeInfo;

/** 
* @ClassName: FeeMapper 
* @Description: TODO(费用信息) 
* @author yangxu 
* @date 2018年1月30日 下午7:49:21 
*  
*/
public interface FeeMapper {

	/** 
	* @Title: selectFeeConditionByFeeIdAndTerm 
	* @Description: TODO(获得费用情况) 
	* @param @return    设定文件 (费用id,借贷期限,借贷期限单位)
	* @return FeeCondition    返回类型 
	* @throws 
	*/
	FeeCondition selectFeeConditionByFeeIdAndTerm(@Param("feeId")String feeId, @Param("termCount") String termCount, @Param("termUnit") String termUnit);
	
	/** 
	* @Title: deleteFeeInfo 
	* @Description: TODO(删除费用信息) 
	* @param @param id    设定文件 (费用id)
	* @return void    返回类型 
	* @throws 
	*/
	void deleteFeeInfo(String loanProductApplyId);
	
	/** 
	* @Title: addFeeInfo 
	* @Description: TODO(插入费用信息) 
	* @param @param LoanProductApplyFeeInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void addFeeInfo(LoanProductApplyFeeInfo LoanProductApplyFeeInfo);

}
