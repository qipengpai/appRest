package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.loan.deal.LoanedCheckTask;

/** 
* @ClassName: LoanTaskBuilderMapper 
* @Description: TODO(贷后检查) 
* @author wangxue 
* @date 2018年8月24日 上午11:01:29 
*  
*/
public interface LoanTaskBuilderMapper {

	/** 
	* @Title: findHandlersByApplyId 
	* @Description: TODO(根据借贷申请id获取贷后人员信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> findHandlersByApplyId(String applyId);

	/** 
	* @Title: findProductInfoById 
	* @Description: TODO(产品中设置的贷后检查信息) 
	* @param @param productId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> findProductInfoById(String productId);

	/** 
	* @Title: addLoanCheckedApplyInfo 
	* @Description: TODO(创建贷后检查信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int addLoanCheckedApplyInfo(String applyId);

	/** 
	* @Title: createLoanCheckedTask 
	* @Description: TODO(创建贷后检查任务) 
	* @param @param task
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int createLoanCheckedTask(LoanedCheckTask task);
}
