package com.company.platform.base.service.activiti.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.company.platform.base.dao.activiti.TaskSetManagerMapper;
import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.model.activiti.TaskAuths;
import com.company.platform.base.service.activiti.ITaskSetManagerService;
import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.base.service.sys.ISysUserService;

/**
 * @ClassName: TaskSetManagerServiceImp
 * @Description: TODO(流程配置信息操作)
 * @author luyuchi
 * @date 2018年3月21日 下午12:59:50
 * 
 */
@Service
public class TaskSetManagerServiceImp implements ITaskSetManagerService {
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskSetManagerMapper taskSetManagerMapper;

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private IOrganizationService organizationService;

	@Override
	public Map<String, Object> getFirstFlowInfo(String productId, String wfId) {
		return taskSetManagerMapper.getFirstFlowInfo(productId, wfId);
	}

	@Override
	public ActTaskset getTheActTaskset(String productId, String wfId, String taskKey) {
		return taskSetManagerMapper.getTheActTaskset(productId, wfId, taskKey);
	}

	@Override
	public List<Map<String, Object>> getWsFunctions(String taskSetId) {
		return taskSetManagerMapper.getWsFunctions(taskSetId);
	}

	@Override
	public Map<String, Object> getTaskAudit(String productId, String processDefinitionKey, ActTaskset nextTaskset) {
		Map<String, Object> result = new HashMap<String, Object>();// 返回值
		String authsstr = nextTaskset.getAuthids();// 设置当前节点权限
		// List<TaskAuths> auths = (List<TaskAuths>)
		// JSONArray.parse(authsstr);//当前节点权限
		// String functions = nextTaskset.getFunctionids();
		getUsers(nextTaskset, result);
		// result.put("auths", auths);
		// result.put("functions", functions);
		result.put("taskKey", nextTaskset.getTaskkey());
		return result;
	}

	/**
	 * 设定审核用户信息 @Title: getUsers @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param taskSet 审核节点 @param: @param result
	 * 结果集 @return: void @throws
	 */
	private void getUsers(ActTaskset taskSet, Map<String, Object> result) {
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();// 审核人员配置（下一节点）
		if (!StringUtils.isBlank(taskSet.getUserids())) {// 配置用户返回
			users = sysUserService.queryStaffDetailByIds(taskSet.getUserids());
		}
		if (!StringUtils.isBlank(taskSet.getOrganizationids())) {// 配置组织返回
			users = organizationService.queryStaffByOrgId(taskSet.getOrganizationids());
			if (users != null) {
				for (Map<String, Object> map : users) {
					map.put("userId", map.get("id"));
				}
			}
		}
		// if (!StringUtils.isBlank(taskSet.getRoleids())) {//配置角色返回
		// users = roleService.queryUserByRoleIds(taskSet.getRoleids());
		// }
		result.put("users", users);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getProcessAuths(String productId, String processDefinitionKey, String processInstanceId,
			String taskKey) {
		Map<String, Object> result = new HashMap<String, Object>();// 返回值
		List<TaskAuths> auths = new ArrayList<TaskAuths>();// 当前节点权限
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(pi.getProcessDefinitionId())
				.processDefinitionVersion(pi.getProcessDefinitionVersion()).singleResult();
		List<ActTaskset> atsList = this.selectByProductId(productId, pd.getId());// 获取配置信息
		if (StringUtils.isBlank(taskKey)) {// 当前节点为空
			if (atsList.size() > 2) {// 设置节点信息为第二个节点
				String authsstr = atsList.get(0).getAuthids();// 设置当前节点权限
				auths = (List<TaskAuths>) JSONArray.parse(authsstr);
				taskKey = atsList.get(0).getTaskkey();// 设置taskId
			}
		} else {// 查找当前节点下一节点配置
			for (int i = 0; i < atsList.size(); i++) {
				ActTaskset atk = atsList.get(i);
				if (taskKey.equals(atk.getTaskkey()) && i + 1 <= atsList.size()) {
					String authsstr = atsList.get(i).getAuthids();// 设置当前节点权限
					auths = (List<TaskAuths>) JSONArray.parse(authsstr);
					break;
				}
			}
		}
		result.put("auths", auths);
		result.put("taskKey", taskKey);
		return result;
	}
	
	/**
	 * @Title: selectByProductId   
	 * @Description: TODO(据产品ID查询节点设置信息)   
	 * @param: @param productId
	 * @param @param processId 工作流id
	 * @param: @return      
	 * @return: List<ActTaskset>      
	 * @throws
	 */
	public List<ActTaskset> selectByProductId(String productId, String processId) {
		return taskSetManagerMapper.selectByProductId(productId, processId);// 获取配置信息;
	}

	@Override
	public List<Map<String, Object>> getHandleReasons(String processInstanceId) {
		return taskSetManagerMapper.getHandleReasons(processInstanceId);
	}

	@Override
	public List<Map<String, Object>> getAUComments(String processInstanceId) {
		return taskSetManagerMapper.getAUComments(processInstanceId);
	}

	@Override
	public FunctionInfo findFunctionInfoByFunctionName(String productId, String wfId, String taskKey,
			String functionName) {
		return taskSetManagerMapper.findFunctionInfoByFunctionName(productId, wfId, taskKey, functionName);
	}

	@Override
	public Map<String, Object> getTaskSetInfo(String productId, String processDefinitionKey, String processInstanceId,
			String taskKey) {
		Map<String, Object> result = new HashMap<String, Object>();//返回值
		if (StringUtils.isBlank(processInstanceId)) {//流程没有启动
			noStartProcess(productId, result, processDefinitionKey, taskKey);
        } else {//流程已启动
    		startProcess(productId, result, processDefinitionKey, taskKey, processInstanceId);
        }
		return result;
	}
	@SuppressWarnings("unchecked")
	private void noStartProcess(String productId, Map<String, Object> result,
			String processDefinitionKey, String taskKey) {
		List<TaskAuths> auths = new ArrayList<TaskAuths>();//当前节点权限
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).latestVersion().singleResult();  
   		List<ActTaskset> atsList = this.selectByProductId(productId, pd.getId());//获取配置信息
   		if (atsList != null && atsList.size() > 1) {
   			String authsstr = atsList.get(0).getAuthids();//设置当前节点权限
   			auths = (List<TaskAuths>) JSONArray.parse(authsstr);
   			taskKey = atsList.get(0).getTaskkey();//设置taskId
   		}
		result.put("auths", auths);
		result.put("taskKey", taskKey);
	}
	
	@SuppressWarnings("unchecked")
	private void startProcess(String productId, Map<String, Object> result,
			String processDefinitionKey,  String taskKey, String processInstanceId) {
		List<TaskAuths> auths = new ArrayList<TaskAuths>();//当前节点权限
	   ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	   ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).processDefinitionVersion(pi.getProcessDefinitionVersion()).singleResult();
	   List<ActTaskset> atsList = this.selectByProductId(productId, pd.getId());//获取配置信息
	   if (StringUtils.isBlank(taskKey)) {//当前节点为空
			if (atsList.size() > 2) {//设置节点信息为第二个节点
	   		  	String authsstr = atsList.get(0).getAuthids();//设置当前节点权限
			   	auths = (List<TaskAuths>) JSONArray.parse(authsstr);
	   			taskKey = atsList.get(0).getTaskkey();//设置taskId
	   		}
	   } else {//查找当前节点下一节点配置
		   for (int i = 0 ; i < atsList.size(); i++) {
			   ActTaskset atk = atsList.get(i);
			   if (taskKey.equals(atk.getTaskkey()) && i + 1 <= atsList.size() ) {
				   	String authsstr = atsList.get(i).getAuthids();//设置当前节点权限
				   	auths = (List<TaskAuths>) JSONArray.parse(authsstr);
		   			break;
			   }
		   }
	   }
		result.put("auths", auths);
		result.put("taskKey", taskKey);
	}
}
