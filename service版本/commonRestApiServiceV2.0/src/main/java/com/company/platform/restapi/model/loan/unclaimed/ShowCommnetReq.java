package com.company.platform.restapi.model.loan.unclaimed;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: ShowCommnetReq 
* @Description: TODO(查看轨迹请求参数) 
* @author 王雪 
* @date 2018年7月19日 上午11:41:22 
*  
*/
@SuppressWarnings("serial")
public class ShowCommnetReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(流程id) 
	*/
	@NotEmpty(message = "流程id不能为空")
	private String taskId;

	/** 
	* @Fields processInstanceId : TODO(流程实例id) 
	*/
	@NotEmpty(message = "流程实例id不能为空")
	private String processInstanceId;

	/** 
	* @Fields processType : TODO(流程类型) 
	*/
	@NotEmpty(message = "流程类型不能为空")
	private String processType;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

}
