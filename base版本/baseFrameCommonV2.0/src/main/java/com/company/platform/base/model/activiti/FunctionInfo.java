package com.company.platform.base.model.activiti;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.util.RegulationUtil;

public class FunctionInfo {
	
	private String id;
	
	private String taskSetId;
	
	private String btnStyle;

	private String functionName;
	
	private String btnText;
	
	private String nextTaskKey;
	
	private boolean choose = false;
	
	private boolean noticeFlag = false;
	
	private String noticeTemp;
	
	private String expression;
	
	private String variableJson;
	
	private Integer sort;
	
	private Map<String, Object> variables = new HashMap<String, Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskSetId() {
		return taskSetId;
	}

	public void setTaskSetId(String taskSetId) {
		this.taskSetId = taskSetId;
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

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public boolean isNoticeFlag() {
		return noticeFlag;
	}

	public void setNoticeFlag(boolean noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	public String getNoticeTemp() {
		return noticeTemp;
	}

	public void setNoticeTemp(String noticeTemp) {
		this.noticeTemp = noticeTemp;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getVariableJson() {
		return variableJson;
	}

	public void setVariableJson(String variableJson) {
		this.variableJson = variableJson;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Map<String, Object> getVariables() {
		if(variables.isEmpty()) {
			if(variableJson != null && !"".equals(variableJson)) {
				JSONObject json = JSON.parseObject(variableJson);
				Set<String> jsonKeySet = json.keySet();
				Iterator<String> it = jsonKeySet.iterator();
				while(it.hasNext()) {
					String jsonKey = it.next();
					variables.put(jsonKey, json.getString(jsonKey));
				}
			}
		}
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	
	protected boolean valid(Map<String, Object> param) {
		if(StringUtils.isBlank(expression)) {
			return true;
		} else {
			return (Boolean) RegulationUtil.invokeMethod(expression, param);
		}
	}

}
