package com.company.platform.restapi.model.loan.deal;

import java.util.Date;

/**
 * 贷后任务model
 */
public class LoanedCheckTask {
	
	private String id;
	/**
	 * 处理人 系统用户Id
	 */
	private String handler;
	/**
	 * 任务类型 01常规； 02一般； 03紧急
	 */
	private String taskType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 要求、规定完成时间
	 */
	private Date reqComTime;
	/**
	 * 实际完成时间
	 */
	private Date completeTime;
	/**
	 * 任务要求
	 * */
	private String taskRequire;
	/**
	 * 0 待完成 1 已完成 2 已撤销
	 */
	private int status;
	/**
	 * 借贷申请ID
	 */
	private String appId;
	/**
	 * 机构id
	 */
	private String orgId;
	/**
	 * 创建时间String
	 */
	private String createTimeStr;
	/**
	 * 实际完成时间String
	 */
	private String completeTimeStr;
	/**
	 * 要求、规定完成时间
	 */
	private String reqComTimeStr;

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCompleteTimeStr() {
		return completeTimeStr;
	}

	public void setCompleteTimeStr(String completeTimeStr) {
		this.completeTimeStr = completeTimeStr;
	}

	public String getReqComTimeStr() {
		return reqComTimeStr;
	}

	public void setReqComTimeStr(String reqComTimeStr) {
		this.reqComTimeStr = reqComTimeStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReqComTime() {
		return reqComTime;
	}

	public void setReqComTime(Date reqComTime) {
		this.reqComTime = reqComTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getTaskRequire() {
		return taskRequire;
	}

	public void setTaskRequire(String taskRequire) {
		this.taskRequire = taskRequire;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
