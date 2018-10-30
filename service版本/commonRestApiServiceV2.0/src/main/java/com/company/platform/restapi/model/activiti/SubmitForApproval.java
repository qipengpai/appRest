package com.company.platform.restapi.model.activiti;

import java.util.HashMap;
import java.util.Map;

/**
 * 审批按钮提交必要元素model
* @ClassName: SubmitForApprovalWhere 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年7月12日 下午2:21:38 
*
 */
public class SubmitForApproval {
	/**
	 * 产品申请iD
	 */
	private String loanProductApplyId;
	//流程id
	private String processInstanceId;
	//当亲流程审批节点id
	private String taskId;
	//下一审核节点ID
	private String nextTaskKey;
	//全局流程变量 按钮流程变量
	private Map<String,Object> variables = new HashMap<String,Object>();
//	//本地设置的流程变量
//	private String localVariables;
	//审批意见
	private String comment;
	//审批结果
	private String audit;
	//退回/拒绝原因
	private String reason;
	//当前审批人
	private String auditUserId;
	//审批人真实姓名
	private String auditUserName;
	//下一节点 审批人id
	private String nextUserId;
	//下一节点审批人真实姓名
	private String nextUserName;
	/**   
	 * 产品申请iD   
	 * @return 产品申请iD   
	 */
	
	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}
	
	/**   
	 * 产品申请iD   
	 * @param 产品申请iD   
	 */
	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}
	
	/**   
	 * 流程id   
	 * @return 流程id   
	 */
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	
	/**   
	 * 流程id   
	 * @param 流程id   
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	/**   
	 * 当亲流程审批节点id   
	 * @return 当亲流程审批节点id   
	 */
	
	public String getTaskId() {
		return taskId;
	}
	
	/**   
	 * 当亲流程审批节点id   
	 * @param 当亲流程审批节点id   
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**   
	 * 下一审核节点ID   
	 * @return 下一审核节点ID   
	 */
	
	public String getNextTaskKey() {
		return nextTaskKey;
	}
	
	/**   
	 * 下一审核节点ID   
	 * @param 下一审核节点ID   
	 */
	public void setNextTaskKey(String nextTaskKey) {
		this.nextTaskKey = nextTaskKey;
	}
	
	/**   
	 * 全局流程变量 按钮流程变量   
	 * @return 全局流程变量 按钮流程变量   
	 */
	
	public Map<String, Object> getVariables() {
		return variables;
	}
	
	/**   
	 * 全局流程变量 按钮流程变量   
	 * @param 全局流程变量 按钮流程变量   
	 */
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	

	
	/**   
	 * 审批意见   
	 * @return 审批意见   
	 */
	
	public String getComment() {
		return comment;
	}
	
	/**   
	 * 审批意见   
	 * @param 审批意见   
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**   
	 * 审批结果   
	 * @return 审批结果   
	 */
	
	public String getAudit() {
		return audit;
	}
	
	/**   
	 * 审批结果   
	 * @param 审批结果   
	 */
	public void setAudit(String audit) {
		this.audit = audit;
	}
	
	/**   
	 * 退回/拒绝原因   
	 * @return 退回/拒绝原因   
	 */
	
	public String getReason() {
		return reason;
	}
	
	/**   
	 * 退回/拒绝原因   
	 * @param 退回/拒绝原因   
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**   
	 * 当前审批人   
	 * @return 当前审批人   
	 */
	
	public String getAuditUserId() {
		return auditUserId;
	}
	
	/**   
	 * 当前审批人   
	 * @param 当前审批人   
	 */
	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}
	
	/**   
	 * 审批人真实姓名   
	 * @return 审批人真实姓名   
	 */
	
	public String getAuditUserName() {
		return auditUserName;
	}
	
	/**   
	 * 审批人真实姓名   
	 * @param 审批人真实姓名   
	 */
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	
	/**   
	 * 下一节点 审批人id   
	 * @return 下一节点 审批人id   
	 */
	
	public String getNextUserId() {
		return nextUserId;
	}
	
	/**   
	 * 下一节点 审批人id   
	 * @param 下一节点 审批人id   
	 */
	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}
	
	/**   
	 * 下一节点审批人真实姓名   
	 * @return 下一节点审批人真实姓名   
	 */
	
	public String getNextUserName() {
		return nextUserName;
	}
	
	/**   
	 * 下一节点审批人真实姓名   
	 * @param 下一节点审批人真实姓名   
	 */
	public void setNextUserName(String nextUserName) {
		this.nextUserName = nextUserName;
	}
	

}
