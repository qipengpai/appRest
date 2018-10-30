package com.company.platform.restapi.model.loan.deal;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.restapi.model.loan.code.UserInfo;

@SuppressWarnings("serial")
public class FunctionBtnInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields btnStyle : TODO(按钮样式) 
	*/
	private String btnStyle;

	/** 
	* @Fields functionName : TODO(功能名称) 
	*/
	private String functionName;

	/** 
	* @Fields btnText : TODO(按钮文字) 
	*/
	private String btnText;

	/** 
	* @Fields nextTaskKey : TODO(下一节点key) 
	*/
	private String nextTaskKey;

	/** 
	* @Fields taskName : TODO(流程节点名称) 
	*/
	private String taskName;

	/** 
	* @Fields choose : TODO(是否选人,true：是false：否) 
	*/
	private String choose;

	/** 
	* @Fields noticeFlag : TODO(是否发送消息,true是，false否) 
	*/
	private String noticeFlag;

	/** 
	* @Fields noticeTemp : TODO(消息模板Key) 
	*/
	private String noticeTemp;

	/** 
	* @Fields variableJson : TODO(提交的流程变量集) 
	*/
	private String variableJson;

	/** 
	* @Fields sort : TODO(按钮排序) 
	*/
	private String sort;

	/** 
	* @Fields users : TODO(可选待办人信息) 
	*/
	private List<UserInfo> users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBtnStyle() {
		return btnStyle;
	}

	public void setBtnStyle(String btnStyle) {
		this.btnStyle = btnStyle;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}

	public String getNextTaskKey() {
		return nextTaskKey;
	}

	public void setNextTaskKey(String nextTaskKey) {
		this.nextTaskKey = nextTaskKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getNoticeTemp() {
		return noticeTemp;
	}

	public void setNoticeTemp(String noticeTemp) {
		this.noticeTemp = noticeTemp;
	}

	public String getVariableJson() {
		return variableJson;
	}

	public void setVariableJson(String variableJson) {
		this.variableJson = variableJson;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getNoticeFlag() {
		return noticeFlag;
	}

	public void setNoticeFlag(String noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	
}
