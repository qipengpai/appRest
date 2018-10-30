package com.company.platform.restapi.dao.loan;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.LoanProductApplySubmit;

/** 
* @ClassName: LoanSubmittedMapper 
* @Description: TODO(已提交进件) 
* @author dongjian 
* @date 2018年5月18日 上午11:31:22 
*  
*/
public interface LoanSubmittedMapper {

	/** 
	* @Title: getLoanHandledInfoByUserId 
	* @Description: TODO(通过登录用户ID获得其已提交进件) 
	* @param @param userId
	* @param @return    设定文件 
	* @return List<LoanProductApplySyno>    返回类型 
	* @throws 
	*/
	List<LoanProductApplySubmit> getLoanSubmittedInfoByUserId(@Param("userId") String userId,
			@Param("customerName") String customerName, @Param("credentialNo") String credentialNo,
			@Param("applyTimeStart") String applyTimeStart, @Param("applyTimeEnd") String applyTimeEnd);

}
