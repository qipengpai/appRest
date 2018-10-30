package com.company.platform.base.service.activiti;

import java.util.Map;

import org.activiti.engine.task.Task;

import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FlowInfo;

/** 
* @ClassName: IProcessManagerService 
* @Description: TODO(流程发起、流转，查询等功能) 
* @author luyuchi
* @date 2018年3月21日 下午12:56:49 
*  
*/
public interface IProcessManagerService {
	
	/** 
	* @Title: startProcess 
	* @Description: TODO(发起流程) 
	* @param @param productId
	* @param @param processDefinitionKey
	* @param @param applyId
	* @param @param userId
	* @param @param userName
	* @param @param nextUserId
	* @param @param nextUsername
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String startProcess(String productId, String processDefinitionKey, String applyId, String userId, String userName, String nextUserId, String nextUsername) throws Exception;

	/** 
	* @Title: complete 
	* @Description: TODO(执行流程) 
	* @param @param taskId
	* @param @param processInstanceId
	* @param @param productId
	* @param @param applyId
	* @param @param comment
	* @param @param userId
	* @param @param userName
	* @param @param variables
	* @param @param localVariables
	* @param @param method
	* @param @param nextTaskKey
	* @param @param audit
	* @param @param processType
	* @param @param auditUserId
	* @param @param auditUserName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String complete(String taskId, String processInstanceId, String productId, String applyId,
			String comment, String userId, String userName, Map<String, Object> variables, Map<String, Object> localVariables, 
			String method, String nextTaskKey, String audit, String processType, String auditUserId, String auditUserName);
	
	/** 
	* @Title: getActTaskset 
	* @Description: TODO(获得流程配置信息) 
	* @param @param productId
	* @param @param processDefinitionKey
	* @param @param taskKey
	* @param @return    设定文件 
	* @return ActTaskset    返回类型 
	* @throws 
	*/
	ActTaskset getActTaskset(String productId, String processDefinitionKey, String taskKey);
	
	/** 
	* @Title: getProcessInstanceTaskIdByUser 
	* @Description: TODO(根据流程待办人获得流程任务) 
	* @param @param processInstanceId
	* @param @param userId
	* @param @param userName
	* @param @return    设定文件 
	* @return Task    返回类型 
	* @throws 
	*/
	Task getProcessInstanceTaskIdByUser(String processInstanceId, String userId, String userName);

	/** 
	* @Title: getUserProcessInstanceTaskId 
	* @Description: TODO(根据流程待办人获得流程任务id) 
	* @param @param processId
	* @param @param id
	* @param @param realName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getUserProcessInstanceTaskId(String processId, String id, String realName);

	/** 
	* @Title: getStartFlowInfo 
	* @Description: TODO(获得某个流程首个节点配置，启动流程时的节点) 
	* @param @param productId
	* @param @param processDefinitionKey
	* @param @return    设定文件 
	* @return FlowInfo    返回类型 
	* @throws 
	*/
	FlowInfo getStartFlowInfo(String productId, String processDefinitionKey);

	/** 
	* @Title: getFlowInfoByTaskKey 
	* @Description: TODO(通过流程节点key获得流程配置信息) 
	* @param @param productId
	* @param @param wfId
	* @param @param nextTaskKey
	* @param @return    设定文件 
	* @return FlowInfo    返回类型 
	* @throws 
	*/
	FlowInfo getFlowInfoByTaskKey(String productId, String wfId, String nextTaskKey);
	
	/** 
	* @Title: getVariableByNextTaskKey 
	* @Description: TODO(获取下一个流程节点的变量信息) 
	* @param @param productId
	* @param @param wfId
	* @param @param taskKey
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getVariableByNextTaskKey(String productId, String wfId, String taskKey);
}
