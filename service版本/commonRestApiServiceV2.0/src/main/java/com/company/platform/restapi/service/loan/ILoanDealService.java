package com.company.platform.restapi.service.loan;

import java.util.List;
import java.util.Map;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.activiti.WaitHandleTask;
import com.company.platform.restapi.model.loan.deal.*;
import com.company.platform.restapi.model.loan.unclaimed.FlowTaskInfo;

/** 
* @ClassName: ILoanDealService 
* @Description: TODO(待办-审核相关service) 
* @author wangxue 
* @date 2018年7月13日 上午11:11:25 
*  
*/
public interface ILoanDealService {

	/** 
	* @Title: getRepaymentTrial 
	* @Description: TODO(还款试算)
	* @param @param loanProductApplyFeeReq
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return LoanRepaymentTrialResp    返回类型 
	* @throws 
	*/
	LoanRepaymentTrialResp getRepaymentTrial(LoanRepaymentTrailReq loanProductApplyFeeReq) throws BusinessException;

	/** 
	* @Title: showCommnet 
	* @Description: TODO(流程轨迹) 
	* @param @param processType
	* @param @param processInstanceId
	* @param @param taskKeyMap
	* @param @param theUserId
	* @param @param theTaskId
	* @param @return    设定文件 
	* @return List<FlowInfo>    返回类型 
	* @throws 
	*/
	List<FlowTaskInfo> showCommnet(String processType, String processInstanceId, Map<String, String> taskKeyMap,
			String theUserId, String theTaskId);

	/** 
	* @Title: judgeLoanProductRelation 
	* @Description: TODO(获取html5页面的url) 
	* @param @param loanProductId
	* @param @param loanProductApplyId
	* @param @param taskKey
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String judgeLoanProductRelation(String loanProductId, String loanProductApplyId, String taskKey);

	/** 
	* @Title: getDealInfoList 
	* @Description: TODO(待办列表详细信息组合) 
	* @param @param list
	* @param @return    设定文件 
	* @return List<WaitHandleTask>    返回类型 
	* @throws 
	*/
	List<WaitHandleTask> getDealInfoList(List<WaitHandleTask> list);
	
	/** 
	* @Title: getTaskInfo 
	* @Description: TODO(获取功能按钮信息) 
	* @param @param req
	* @param @return    设定文件 
	* @return FunctionBtnInfo    返回类型 
	* @throws 
	*/
	List<FunctionBtnInfo> getTaskInfo(LoanTaskInfoReq req);
	
	/** 
	* @Title: finishTask 
	* @Description: TODO(流程节点完成处理) 
	* @param @param result
	* @param @param processInstanceId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void finishTask(String result, String processInstanceId);
	
	/** 
	* @Title: getLoanProcessTaskInfo 
	* @Description: TODO(获取流程实例id和任务信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	Map<String, String> getLoanProcessTaskInfo(String applyId);

	/**
	 * @Author qipengpai
	 * @Description //TODO (还款试算)
	 * @Date 11:54 2018/9/17
	 * @Param [loanRepaymentTrailInfo]
	 * @return com.company.platform.restapi.model.loan.deal.LoanRepaymentTrialResp
	 **/
    LoanRepaymentTrialResp getRepaymentTrialNoValidate(LoanRepaymentTrailInfo loanRepaymentTrailInfo);
}
