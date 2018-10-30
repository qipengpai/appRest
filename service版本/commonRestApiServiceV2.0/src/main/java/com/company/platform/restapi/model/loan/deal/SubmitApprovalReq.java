package com.company.platform.restapi.model.loan.deal;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: SubmitApprovalReq 
* @Description: TODO(保存审核信息请求参数) 
* @author wangxue 
* @date 2018年7月21日 上午9:15:32 
*  
*/
@SuppressWarnings("all")
public class SubmitApprovalReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields loanProductApplyId : TODO(借贷申请id) 
	*/
	@NotEmpty(message = "借贷申请id不能为空")
	private String loanProductApplyId;
	/** 
	* @Fields processInstanceId : TODO(流程实体id) 
	*/
	@NotEmpty(message = "流程实体id不能为空")
	private String processInstanceId;
	/** 
	* @Fields taskId : TODO(当前流程审批节点id) 
	*/
	@NotEmpty(message = "当前流程审批节点id不能为空")
	private String taskId;
	/** 
	* @Fields nextTaskKey : TODO(下一审核节点ID) 
	*/
	private String nextTaskKey;
	/** 
	* @Fields localVariables : TODO(全局流程变量 按钮流程变量) 
	*/
	@NotEmpty(message = "按钮流程变量不能为空")
	private String localVariables;
	/** 
	* @Fields comment : TODO(审批意见) 
	*/
	@NotEmpty(message = "审批意见不能为空")
	private String comment;
	/** 
	* @Fields audit : TODO(审批结果) 
	*/
	@NotEmpty(message = "审批结果不能为空")
	private String audit;
	/** 
	* @Fields reason : TODO(退回/拒绝原因) 
	*/
	private String reason;
	/** 
	* @Fields nextUserId : TODO(下一节点审批人id) 
	*/
	private String nextUserId;
	/** 
	* @Fields nextUserName : TODO(下一节点审批人真实姓名) 
	*/
	private String nextUserName;

	/** 
	* @Fields processType : TODO(流程分类类型,默认loan) 
	*/
	@NotEmpty(message = "流程分类类型不能为空")
	private String processType;
	
	/** 
	* @Fields choose : TODO(是否选人) 
	*/ 
	@NotEmpty(message = "是否选人不能为空")
	private String choose;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getNextTaskKey() {
		return nextTaskKey;
	}

	public void setNextTaskKey(String nextTaskKey) {
		this.nextTaskKey = nextTaskKey;
	}

	public String getLocalVariables() {
		return localVariables;
	}

	public void setLocalVariables(String localVariables) {
		this.localVariables = localVariables;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getNextUserName() {
		return nextUserName;
	}

	public void setNextUserName(String nextUserName) {
		this.nextUserName = nextUserName;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}
}
