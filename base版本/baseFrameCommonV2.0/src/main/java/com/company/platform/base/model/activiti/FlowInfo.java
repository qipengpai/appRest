package com.company.platform.base.model.activiti;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlowInfo {

	private String taskKey;
	
	private String taskName;
	
	private String varKey;
	
	private List<FunctionInfo> functions = new ArrayList<FunctionInfo>();
	
	private List<FlowParam> flowParams = new ArrayList<FlowParam>();

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

	public String getVarKey() {
		return varKey;
	}

	public void setVarKey(String varKey) {
		this.varKey = varKey;
	}

	public List<FunctionInfo> getFunctions() {
		return functions;
	}

	public void setFunctions(List<FunctionInfo> functions) {
		this.functions = functions;
	}
	
	public List<FlowParam> getFlowParams() {
		return flowParams;
	}

	public void setFlowParams(List<FlowParam> flowParams) {
		this.flowParams = flowParams;
	}

	public List<String> getFlowParamsByParams(Map<String, Object> param) {
		List<String> funs = new ArrayList<String>();
		for(FlowParam flowParam : flowParams) {
			if(flowParam.valid(param)) {
				funs.add(flowParam.getParam());
			}
		}
		return funs;
	}
	
	public List<FunctionInfo> getFunctionsByParams(Map<String, Object> param) {
		List<FunctionInfo> funs = new ArrayList<FunctionInfo>();
		for(FunctionInfo function : functions) {
			if(function.valid(param)) {
				funs.add(function);
			}
		}
		return funs;
	}
	
}
