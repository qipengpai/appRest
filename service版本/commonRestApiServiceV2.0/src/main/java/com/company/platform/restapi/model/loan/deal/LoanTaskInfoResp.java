package com.company.platform.restapi.model.loan.deal;

import java.util.List;

import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.loan.unclaimed.FlowTaskInfo;

/** 
* @ClassName: LoanTaskInfoResp 
* @Description: TODO(审核信息返回参数) 
* @author wangxue
* @date 2018年7月23日 下午4:20:54 
*  
*/
@SuppressWarnings("all")
public class LoanTaskInfoResp extends BaseHttpParamsResp {

	/** 
	* @Fields functions : TODO(功能按钮信息) 
	*/
	private List<FunctionBtnInfo> functions;

	/** 
	* @Fields flows : TODO(审核流程历史信息（轨迹）) 
	*/
	private List<FlowTaskInfo> flowTaskInfo;

	public List<FunctionBtnInfo> getFunctions() {
		return functions;
	}

	public void setFunctions(List<FunctionBtnInfo> functions) {
		this.functions = functions;
	}

	public List<FlowTaskInfo> getFlowTaskInfo() {
		return flowTaskInfo;
	}

	public void setFlowTaskInfo(List<FlowTaskInfo> flowTaskInfo) {
		this.flowTaskInfo = flowTaskInfo;
	}

}
