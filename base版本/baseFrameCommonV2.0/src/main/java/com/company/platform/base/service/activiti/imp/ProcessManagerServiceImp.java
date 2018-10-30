package com.company.platform.base.service.activiti.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.activiti.TaskSetManagerMapper;
import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FlowInfo;
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.service.activiti.IProcessManagerService;
import com.company.platform.base.service.activiti.ITaskSetManagerService;
import com.company.platform.base.service.activiti.IVariateConfig;
import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.base.service.sys.IPosterService;
import com.company.platform.base.service.sys.IRoleService;
import com.company.platform.base.service.sys.ISysUserService;

/**
 * @ClassName: ProcessManagerServiceImp
 * @Description: TODO(流程发起、流转，查询等功能)
 * @author luyuchi
 * @date 2018年3月21日 下午12:55:44
 * 
 */
@Service
public class ProcessManagerServiceImp implements IProcessManagerService {

	private static Logger logger = Logger.getLogger(ProcessManagerServiceImp.class);

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ITaskSetManagerService taskSetManagerService;
	@Autowired
	private IVariateConfig variateConfig;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPosterService posterService;
	@Autowired
	private TaskSetManagerMapper taskSetManagerMapper;

	/*
	 * (非 Javadoc) <p>Title: startProcess</p> <p>Description: 流程启动</p>
	 * 
	 * @param productId 产品id
	 * 
	 * @param processDefinitionKey 流程定义key
	 * 
	 * @param applyId 业务id
	 * 
	 * @param userId 用户id
	 * 
	 * @param userName 用户名
	 * 
	 * @param nextUserId 下一节点用户id
	 * 
	 * @param nextUsername 下一节点用户名
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see com.company.platform.base.service.activiti.IProcessManagerService#
	 * startProcess(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String startProcess(String productId, String processDefinitionKey, String applyId, String userId,
			String userName, String nextUserId, String nextUsername) throws Exception {
		String result = "";
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).latestVersion().singleResult();

		// 获得起始流程配置
		Map<String, Object> flowInfo = taskSetManagerService.getFirstFlowInfo(productId, pd.getId());

		Map<String, Object> variables = new HashMap<String, Object>();
		if (flowInfo != null) {
			String taskkey = (String) flowInfo.get("taskKey");
			variables.put(taskkey + "Assignee", createAssignee(userName, userId));
		}
		// 获得该流程流程变量常量
		Map<String, Object> flowVariate = variateConfig.getFlowVariateByWfKey(processDefinitionKey);

		if (flowVariate != null && !flowVariate.isEmpty()) {
			variables.putAll(flowVariate);
		}

		try {
			result = startProcessWithBusId(processDefinitionKey, variables, applyId, nextUserId, nextUsername);// 启动流程，并设置业务id(applyId)
		} catch (Exception e) {
			logger.error("启动流程异常：processKey=" + processDefinitionKey + ":applyId=" + applyId + ":userId=" + userId);
			throw e;
		}

		return result;
	}

	/**
	 * @Title: startProcessWithBusId @Description: TODO(流程启动) @param @param
	 *         processDefinitionKey 流程定义key @param @param variables
	 *         流程变量 @param @param businessKey 业务id(等同 businessId) @param @param
	 *         userId 用户id @param @param userName 用户名称 @param @return
	 *         设定文件 @return String 返回类型 @throws
	 */
	private String startProcessWithBusId(String processDefinitionKey, Map<String, Object> variables, String businessKey,
			String userId, String userName) {
		if (StringUtils.isNotBlank(userId)) {
			// 设置流程启动人
			Authentication.setAuthenticatedUserId(userId + ":" + userName);
		} else {
			// 自动流程启动，无启动人
			Authentication.setAuthenticatedUserId("-1:自动");
		}
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		if (pi != null) {
			return pi.getId();// 返回流程实例ID
		} else {
			return null;
		}
	}

	/*
	 * (非 Javadoc) <p>Title: complete</p> <p>Description: 流程流转</p>
	 * 
	 * @param taskId 流程id
	 * 
	 * @param processInstanceId 流程实例id
	 * 
	 * @param productId 产品id
	 * 
	 * @param applyId 业务id
	 * 
	 * @param comment 批注（审批内容）
	 * 
	 * @param userId 用户id
	 * 
	 * @param userName 用户名
	 * 
	 * @param variables 流程变量
	 * 
	 * @param localVariables 本地流程变量，只限于当前节点有效
	 * 
	 * @param method "Y"表示选人、领用；"N"表示已走过节点，不需要设置处理人
	 * 
	 * @param nextTaskKey 下一节点key
	 * 
	 * @param audit 流程按钮值=审批结果
	 * 
	 * @param processType 流程业务类型
	 * 
	 * @param auditUserId 审批人id
	 * 
	 * @param auditUserName 审批人名称
	 * 
	 * @return
	 * 
	 * @see com.company.platform.base.service.activiti.IProcessManagerService#
	 * complete(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Map, java.util.Map, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String complete(String taskId, String processInstanceId, String productId, String applyId, String comment,
			String userId, String userName, Map<String, Object> variables, Map<String, Object> localVariables,
			String method, String nextTaskKey, String audit, String processType, String auditUserId,
			String auditUserName) {
		/**
		 * 设置流程执行人
		 */
		String c_assignee = createAssignee(userName, userId);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String assignee_ = task.getAssignee();// 获得当前流程处理人

		String result = null;
		if (c_assignee.equals(assignee_)) {// 流程执行人 与 当前流程处理人 必须一致才能执行流程
			Map<String, String> message = new HashMap<String, String>();

			ProcessInstance pd = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
					.singleResult();

			if (variables == null) {
				variables = new HashMap<String, Object>();
			}

			if (StringUtils.isEmpty(applyId)) {
				applyId = pd.getBusinessKey();
			}

			if ("Y".equals(method) && StringUtils.isNotBlank(nextTaskKey)) {// 同意时指定下一节点审批人/候选审批人
				ActTaskset nextActTaskset = getActTaskset(productId, pd.getProcessDefinitionKey(), nextTaskKey);
				message.put("taskName", nextActTaskset.getTaskName());
				if (!StringUtils.isBlank(auditUserName) && !StringUtils.isBlank(auditUserId)) {// 设置下一节点处理人
					variables.put("assigneeId", auditUserId);
					variables.put("assigneeName", auditUserName);
					runtimeService.removeVariable(task.getExecutionId(), nextTaskKey + "Assignee");// 从流程变量中删除下一节点流程处理人
				} else {// 设置下一节点候选人，候选机构，候选角色
					List<String> candidate = new ArrayList<String>();
					if (!StringUtils.isBlank(nextActTaskset.getUserids())) {// 人员
						List<Map<String, Object>> users = sysUserService
								.queryStaffDetailByIds(nextActTaskset.getUserids());
						for (Map<String, Object> user : users) {
							String uId = (String) user.get("userId");
							String uName = (String) user.get("userName");
							candidate.add("u:" + uId + ":" + uName);
						}
					}
					if (!StringUtils.isBlank(nextActTaskset.getOrganizationids())) {// 机构
						List<Map<String, Object>> orgs = organizationService
								.queryOrganizationByOrgIds(nextActTaskset.getOrganizationids());
						for (Map<String, Object> org : orgs) {
							String orgId = (String) org.get("id");
							String orgName = (String) org.get("orgName");
							candidate.add("o:" + orgId + ":" + orgName);
						}
					}
					if (!StringUtils.isBlank(nextActTaskset.getRoleids())) {// 角色
						List<Map<String, Object>> roles = roleService.queryRoleDetailByIds(nextActTaskset.getRoleids());
						for (Map<String, Object> role : roles) {
							String roleId = (String) role.get("id");
							String roleName = (String) role.get("roleName");
							candidate.add("r:" + roleId + ":" + roleName);
						}
					}
					if (!StringUtils.isBlank(nextActTaskset.getPosterids())) {// 岗位
						List<Map<String, Object>> posters = posterService
								.queryPosterByIds(nextActTaskset.getPosterids());
						for (Map<String, Object> poster : posters) {
							String posterId = (String) poster.get("id");
							String posterName = (String) poster.get("posterName");
							candidate.add("p:" + posterId + ":" + posterName);
						}
					}
					variables.put("candidate", candidate);
				}
			}

			try {
				Boolean noticeFlag = null;

				if (localVariables != null && !localVariables.isEmpty()) {
					noticeFlag = (Boolean) localVariables.get("noticeFlag");
					taskService.setVariablesLocal(taskId, localVariables);

					if (noticeFlag != null) {
						variables.put("noticeFlag_", noticeFlag);
					}
				}

				this.complete(taskId, variables, processInstanceId, comment, audit, auditUserId, auditUserName);

				message.put("comment", comment);

				if (noticeFlag != null && noticeFlag) {

				}
			} catch (Exception e) {
				logger.error("流程处理异常processKey=" + processInstanceId + ":applyId=" + applyId + ":userId=" + userId
						+ ":comment=" + comment + ":audit" + audit);
				e.printStackTrace();
				return "1111";
			}
			// 判断流程是否结束
			pd = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if (pd == null) {// 流程结束
				HistoricProcessInstance pdHistroty = historyService.createHistoricProcessInstanceQuery()
						.processInstanceId(processInstanceId).singleResult();
				if (pdHistroty.getEndActivityId().equals("end4")) {// 结束全部流程
					result = "9999";
				} else {// 拒绝
					result = "9000";
				}
			} else {// 流程继续执行
				result = "0000";
			}
		} else {// 操作人与流程待处理人不一致
			logger.error("流程执行人已更改！processKey=" + processInstanceId + ":applyId=" + applyId + ":已变更" + c_assignee
					+ "-->" + assignee_);
			result = "9500";
		}

		return result;
	}

	/**
	 * @Title: complete @Description: TODO(流程流转) @param @param taskId
	 *         任务id @param @param variables 流程变量 @param @param processInstanceId
	 *         流程实例id @param @param comment 批注 @param @param audit
	 *         审核结果 @param @param auditUserId 审核人id @param @param auditUserName
	 *         审核人姓名 @return void 返回类型 @throws
	 */
	private void complete(String taskId, Map<String, Object> variables, String processInstanceId, String comment,
			String audit, String auditUserId, String auditUserName) {
		Authentication.setAuthenticatedUserId(auditUserId + ":" + auditUserName);// 设定创建人
		// 添加批注信息
		if (!StringUtils.isEmpty(comment)) {
			taskService.addComment(taskId, processInstanceId, audit, comment);// comment为批注内容
		}
		taskService.setVariables(taskId, variables);
		// 启动流程
		taskService.complete(taskId);
	}

	/**
	 * @Title: createAssignee @Description: TODO(组成流程处理人标识) @param @param
	 *         userName @param @param userId @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	private String createAssignee(String userName, String userId) {
		return userId + ":" + userName;// 例如：25e53d4b-4377-40e4-a119-480ef67eb09d:超级管理员
	}

	/*
	 * (非 Javadoc) <p>Title: getActTaskset</p> <p>Description: 获得流程配置</p>
	 * 
	 * @param productId 产品id
	 * 
	 * @param processDefinitionKey 流程定义key
	 * 
	 * @param taskKey 流程节点key
	 * 
	 * @return
	 * 
	 * @see
	 * com.zkbr.platform.service.activiti.IProcessManagerService#getActTaskset(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ActTaskset getActTaskset(String productId, String processDefinitionKey, String taskKey) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
		String wfId = pd.getId();
		ActTaskset actTaskset = taskSetManagerService.getTheActTaskset(productId, wfId, taskKey);
		return actTaskset;
	}

	/*
	 * (非 Javadoc) <p>Title: getProcessInstanceTaskIdByUser</p> <p>Description:
	 * 获得流程任务</p>
	 * 
	 * @param processInstanceId 流程实例id
	 * 
	 * @param userId 用户id
	 * 
	 * @param userName 用户名
	 * 
	 * @return
	 * 
	 * @see com.company.platform.base.service.activiti.IProcessManagerService#
	 * getProcessInstanceTaskIdByUser(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Task getProcessInstanceTaskIdByUser(String processInstanceId, String userId, String userName) {
		return taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(createAssignee(userName, userId)).singleResult();
	}

	/*
	 * (非 Javadoc) <p>Title: getUserProcessInstanceTaskId</p> <p>Description:
	 * 获得流程实例id</p>
	 * 
	 * @param processInstanceId 流程实例id
	 * 
	 * @param userId 用户id
	 * 
	 * @param userName 用户名
	 * 
	 * @return
	 * 
	 * @see com.company.platform.base.service.activiti.IProcessManagerService#
	 * getUserProcessInstanceTaskId(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getUserProcessInstanceTaskId(String processInstanceId, String userId, String userName) {
		Task task = getProcessInstanceTaskIdByUser(processInstanceId, userId, userName);
		if (task == null) {
			return null;
		} else {
			return task.getId();
		}
	}

	/*
	 * (非 Javadoc) <p>Title: getStartFlowInfo</p> <p>Description: 获得流程首个节点配置</p>
	 * 
	 * @param productId 产品id
	 * 
	 * @param processDefinitionKey 流程定义key
	 * 
	 * @return
	 * 
	 * @see com.company.platform.base.service.activiti.IProcessManagerService#
	 * getStartFlowInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public FlowInfo getStartFlowInfo(String productId, String processDefinitionKey) {
		FlowInfo first = new FlowInfo();
		// activiti获得流程定义，取流程定义最新版本
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
		// 获得流程定义id
		String wfId = pd.getId();
		Map<String, Object> flowInfo = taskSetManagerService.getFirstFlowInfo(productId, wfId);
		if (flowInfo != null) {
			first.setTaskName((String) flowInfo.get("taskName"));
			first.setTaskKey((String) flowInfo.get("taskKey"));

			List<Map<String, Object>> functions = taskSetManagerService.getWsFunctions((String) flowInfo.get("id"));

			if (functions != null && !functions.isEmpty()) {
				for (Map<String, Object> function : functions) {
					FunctionInfo functionInfo = new FunctionInfo();

					functionInfo.setFunctionName((String) function.get("functionName"));
					functionInfo.setBtnText((String) function.get("btnText"));
					functionInfo.setBtnStyle((String) function.get("btnStyle"));
					functionInfo.setChoose((Boolean) function.get("choose"));
					functionInfo.setNoticeFlag((Boolean) function.get("noticeFlag"));
					functionInfo.setNoticeTemp((String) function.get("noticeTemp"));
					functionInfo.setNextTaskKey((String) function.get("nextTaskKey"));
					functionInfo.setExpression((String) function.get("expression"));
					functionInfo.setVariableJson((String) function.get("variableJson"));

					first.getFunctions().add(functionInfo);
				}
			}

		}
		return first;
	}

	/*
	 * (非 Javadoc) <p>Title: getFlowInfoByTaskKey</p> <p>Description: </p>
	 * 
	 * @param productId
	 * 
	 * @param wfId
	 * 
	 * @param nextTaskKey
	 * 
	 * @return
	 * 
	 * @see com.company.platform.base.service.activiti.IProcessManagerService#
	 * getFlowInfoByTaskKey(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public FlowInfo getFlowInfoByTaskKey(String productId, String wfId, String nextTaskKey) {
		FlowInfo flow = new FlowInfo();
		ActTaskset flowInfo = taskSetManagerService.getTheActTaskset(productId, wfId, nextTaskKey);
		if (flowInfo != null) {
			flow.setTaskName(flowInfo.getTaskName());
			flow.setTaskKey(flowInfo.getTaskkey());
			List<Map<String, Object>> functions = taskSetManagerService.getWsFunctions(flowInfo.getId());
			if (functions != null && !functions.isEmpty()) {
				for (Map<String, Object> function : functions) {
					FunctionInfo functionInfo = new FunctionInfo();
					functionInfo.setFunctionName((String) function.get("functionName"));
					functionInfo.setBtnText((String) function.get("btnText"));
					functionInfo.setBtnStyle((String) function.get("btnStyle"));
					functionInfo.setChoose((Boolean) function.get("choose"));
					functionInfo.setNoticeFlag((Boolean) function.get("noticeFlag"));
					functionInfo.setNoticeTemp((String) function.get("noticeTemp"));
					functionInfo.setNextTaskKey((String) function.get("nextTaskKey"));
					functionInfo.setExpression((String) function.get("expression"));
					functionInfo.setVariableJson((String) function.get("variableJson"));
					flow.getFunctions().add(functionInfo);
				}
			}
		}
		return flow;
	}

	@Override
	public String getVariableByNextTaskKey(String productId, String wfId, String taskKey) {
		return taskSetManagerMapper.getVariableByNextTaskKey(productId, wfId, taskKey);
	}
}
