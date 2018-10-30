package com.company.platform.restapi.model.loan.deal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: LoanTaskInfoReq 
* @Description: TODO(获取审核信息输入参数) 
* @author wangxue 
* @date 2018年7月23日 上午9:43:34 
*  
*/
@SuppressWarnings("serial")
public class LoanTaskInfoReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields credentialType : TODO(借贷申请id不能为空)
	 */
	@NotEmpty(message = "借贷申请id不能为空")
	private String loanProductApplyId;

	/** 
	* @Fields taskId : TODO(节点id) 
	*/
	@NotEmpty(message = "节点id不能为空")
	private String taskId;

	/** 
	* @Fields taskKey : TODO(节点key) 
	*/
	@NotEmpty(message = "节点key不能为空")
	private String taskKey;

	/**
	 * @Fields processInstanceId : TODO(流程实例id)
	 */
	@NotEmpty(message = "流程实例id不能为空")
	private String processInstanceId;

	/**
	 * @Fields processType : TODO(业务类型)
	 */
	@NotEmpty(message = "业务类型不能为空")
	private String processType;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

}
