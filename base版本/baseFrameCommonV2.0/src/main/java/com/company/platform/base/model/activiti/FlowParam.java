package com.company.platform.base.model.activiti;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.company.platform.base.util.RegulationUtil;

public class FlowParam {
	
	private String param;

	private String expression;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	protected boolean valid(Map<String, Object> param) {
		if(StringUtils.isBlank(expression)) {
			return true;
		} else {
			return (Boolean) RegulationUtil.invokeMethod(expression, param);
		}
	}
}
