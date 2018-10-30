package com.company.platform.restapi.model.loan.unclaimed;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: ClaimTaskReq 
* @Description: TODO(待领取任务请求参数) 
* @author 王雪
* @date 2018年7月19日 上午11:32:02 
*  
*/
@SuppressWarnings("serial")
public class ClaimTaskReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(流程id) 
	*/
	@NotEmpty(message = "流程id不能为空")
	private String taskId;

	/** 
	* @Fields taskKey : TODO(流程key) 
	*/
	@NotEmpty(message = "流程key不能为空")
	private String taskKey;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

}
