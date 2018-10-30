package com.company.platform.restapi.dao.loan;

import java.util.List;

import com.company.platform.restapi.model.loan.LoanProductApplySyno;

/** 
* @ClassName: LoanHandledMapper 
* @Description: TODO(已办任务myBatis Mapper) 
* @author luyuchi
* @date 2018年1月26日 下午5:26:19 
*  
*/
public interface LoanHandledMapper {

	/** 
	* @Title: getLoanHandledInfoByUserId 
	* @Description: TODO(通过登录用户ID获得其已办任务) 
	* @param @param userId
	* @param @return    设定文件 
	* @return List<LoanProductApplySyno>    返回类型 
	* @throws 
	*/
	List<LoanProductApplySyno> getLoanHandledInfoByUserId(String userId);
	
	/** 
	* @Title: getLoanHandledInfoByUserIdNew 
	* @Description: TODO(通过登录用户ID获得其已办任务(新)) 
	* @param @param userId
	* @param @return    设定文件 
	* @return List<LoanProductApplySyno>    返回类型 
	* @throws 
	*/
	List<LoanProductApplySyno> getLoanHandledInfoByUserIdNew(String userId);

}
