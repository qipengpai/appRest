package com.company.platform.restapi.model.loan.deal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: LoanApplyAuditingReq 
* @Description: TODO(获取待办任务详情请求参数) 
* @author wangxue 
* @date 2018年7月21日 上午11:19:14 
*  
*/
@SuppressWarnings("serial")
public class LoanApplyAuditingReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields loanProductApplyId : TODO(借贷申请id不能为空)
	 */
	@NotEmpty(message = "借贷申请id不能为空")
	private String loanProductApplyId;

	/** 
	* @Fields loanProductId : TODO(借贷产品id) 
	*/
	@NotEmpty(message = "借贷产品id不能为空")
	private String loanProductId;

	/** 
	* @Fields taskKey : TODO(节点key) 
	*/
	@NotEmpty(message = "节点key不能为空")
	private String taskKey;

	/** 
	* @Fields taskId : TODO(节点id) 
	*/
	@NotEmpty(message = "节点id不能为空")
	private String taskId;

	/**
	 * @Fields updateTimeProductApply : TODO(借款申请更新时间)
	 */
	@NotEmpty(message = "借款申请更新时间不能为空")
	private String updateTimeProductApply;

	/**
	 * @Fields updateTimeCustomer : TODO(申请人更新时间)
	 */
	@NotEmpty(message = "申请人更新时间不能为空")
	private String updateTimeCustomer;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getUpdateTimeProductApply() {
		return updateTimeProductApply;
	}

	public void setUpdateTimeProductApply(String updateTimeProductApply) {
		this.updateTimeProductApply = updateTimeProductApply;
	}

	public String getUpdateTimeCustomer() {
		return updateTimeCustomer;
	}

	public void setUpdateTimeCustomer(String updateTimeCustomer) {
		this.updateTimeCustomer = updateTimeCustomer;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
