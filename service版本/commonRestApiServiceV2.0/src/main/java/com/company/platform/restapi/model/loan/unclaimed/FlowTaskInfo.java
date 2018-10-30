package com.company.platform.restapi.model.loan.unclaimed;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: FlowTaskInfo 
* @Description: TODO(流程轨迹) 
* @author wangxue 
* @date 2018年7月19日 上午11:46:48 
*  
*/
@SuppressWarnings("all")
public class FlowTaskInfo extends BaseModel {
	/**
	 * @Fields auditName : TODO(审核人)
	 */
	private String auditName;
	/**
	 * @Fields taskName : TODO(审核节点)
	 */
	private String taskName;

	/** 
	* @Fields taskKey : TODO(审核类型) 
	*/
	private String taskKey;
	/**
	 * @Fields aduitResult : TODO(审核结果)
	 */
	private String aduitResult;
	/**
	 * @Fields comment : TODO(审核意见)
	 */
	private String comment;
	/**
	 * @Fields auditTime : TODO(审核时间，yyyy-MM-dd hh:mm:ss)
	 */
	private String auditTime;

	/** 
	* @Fields reason : TODO(操作原因) 
	*/
	private String reason;

	/** 
	* @Fields type : TODO(操作类型) 
	*/
	private String type;

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAduitResult() {
		return aduitResult;
	}

	public void setAduitResult(String aduitResult) {
		this.aduitResult = aduitResult;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

}
