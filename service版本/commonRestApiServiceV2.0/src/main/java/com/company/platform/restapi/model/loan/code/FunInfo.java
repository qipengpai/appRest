package com.company.platform.restapi.model.loan.code;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: FunInfo 
* @Description: TODO(封装流程节点功能按钮) 
* @author luyuchi
* @date 2018年4月27日 下午5:11:58 
*  
*/
public class FunInfo extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 5862533388149641211L;

	private String taskKey;
	
	private String taskName;
	
	private String choose;
	
	private String btnStyle;
	
	private String functionName;
	
	private String btnText;
	
	private List<UserInfo> users;

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
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

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	
}
