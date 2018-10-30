package com.company.platform.base.service.activiti;

import java.util.List;
import java.util.Map;

import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FunctionInfo;

/** 
* @ClassName: ITaskSetManagerService 
* @Description: TODO(流程配置信息操作) 
* @author luyuchi
* @date 2018年3月21日 下午12:59:18 
*  
*/
public interface ITaskSetManagerService {

	/** 
	* @Title: getFirstFlowInfo 
	* @Description: TODO(获得流程节点功能配置信息) 
	* @param @param productId
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getFirstFlowInfo(String productId, String id);

	/** 
	* @Title: getTheActTaskset 
	* @Description: TODO(获得流程节点配置信息) 
	* @param @param productId
	* @param @param wfId
	* @param @param taskKey
	* @param @return    设定文件 
	* @return ActTaskset    返回类型 
	* @throws 
	*/
	ActTaskset getTheActTaskset(String productId, String wfId, String taskKey);

	/** 
	* @Title: getWsFunctions 
	* @Description: TODO(通过流程配置id获得流程功能按钮配置) 
	* @param @param taskSetId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getWsFunctions(String taskSetId);

	/**
	 * 获取节点配置信息
	 * @Title: getTaskAudit   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param productId
	 * @param: @param processDefinitionKey
	 * @param: @param nextTaskKey 下一节点taskKey
	 * @param: @return   users： id,realName || auths：TaskAuths || functions
	 * @return: Map<String, Object>     
	 * @throws
	 */
	Map<String, Object> getTaskAudit(String productId, String processDefinitionKey, ActTaskset nextTaskset);

	/** 
	* @Title: getProcessAuths 
	* @Description: TODO(获取审核流程权限信息) 
	* @param @param productId 产品id
	* @param @param processDefinitionKey 流程类型key
	* @param @param processInstanceId 流程实例id
	* @param @param taskKey 节点key
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getProcessAuths(String productId, String processDefinitionKey, String processInstanceId,
			String taskKey);
	
	/** 
	* @Title: getHandleReasons 
	* @Description: TODO(获取审核原因) 
	* @param @param processInstanceId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getHandleReasons(String processInstanceId);
	
	/** 
	* @Title: getAUComments 
	* @Description: TODO(自动流程审核意见) 
	* @param @param processInstanceId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getAUComments(String processInstanceId);
	
	/** 
	* @Title: findFunctionInfoByFunctionName 
	* @Description: TODO(获取按钮信息) 
	* @param @param productId
	* @param @param wfId
	* @param @param taskKey
	* @param @param functionName
	* @param @return    设定文件 
	* @return FunctionInfo    返回类型 
	* @throws 
	*/
	FunctionInfo findFunctionInfoByFunctionName(String productId, String wfId, String taskKey, String functionName);

	/** 
	* @Title: getTaskSetInfo 
	* @Description: TODO(获取节点配置信息) 
	* @param @param productId
	* @param @param processDefinitionKey
	* @param @param processInstanceId
	* @param @param taskKey
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getTaskSetInfo(String productId, String processDefinitionKey, String processInstanceId, String taskKey);
}
