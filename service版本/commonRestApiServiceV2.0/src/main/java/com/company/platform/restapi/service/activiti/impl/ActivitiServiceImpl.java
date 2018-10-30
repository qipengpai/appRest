package com.company.platform.restapi.service.activiti.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.dao.sys.OrgMapper;
import com.company.platform.base.dao.sys.PosterMapper;
import com.company.platform.base.dao.sys.RoleMapper;
import com.company.platform.base.dao.sys.SysUserMapper;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;
import com.company.platform.base.util.RegulationUtil;
import com.company.platform.restapi.dao.activiti.ActivitiMapper;
import com.company.platform.restapi.dao.loan.ProductApplyInfoMapper;
import com.company.platform.restapi.model.activiti.SubmitForApproval;
import com.company.platform.restapi.model.activiti.WaitHandleTask;
import com.company.platform.restapi.model.activiti.WaitReceiveTask;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.service.activiti.ActivitiService;

/**
 * 流程API相关调用
* @ClassName: ActivitiServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年7月12日 下午1:52:23 
*
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

	@Autowired
	private ActivitiMapper activitiMapper;
	@Autowired
	private IDataDictionaryService dataDictionaryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ProductApplyInfoMapper productApplyInfoMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private OrgMapper orgMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PosterMapper posterMapper;

	@Override
	public Map<String, Object> getPerLeisureTask(String userId, String realName, Integer start, Integer end) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 返回结果
		List<WaitReceiveTask> resultlist = new ArrayList<WaitReceiveTask>();
		// 机构、角色 、岗位 赛选查询条件
		List<String> candidateGroups = new ArrayList<String>();
		// 个人
		candidateGroups.add("u:" + userId + ":" + realName);
		// 机构
		Map<String, Object> org = activitiMapper.queryOrg(userId);
		if (org != null) {
			candidateGroups.add("o:" + org.get("orgId") + ":" + org.get("orgName"));
		}
		// 角色
		List<Map<String, Object>> roles = activitiMapper.queryRoles(userId);
		for (Map<String, Object> role : roles) {
			candidateGroups.add("r:" + role.get("id") + ":" + role.get("roleName"));
		}
		// 岗位
		List<Map<String, Object>> posters = activitiMapper.queryPoster(userId);
		for (Map<String, Object> poster : posters) {
			candidateGroups.add("p:" + poster.get("id") + ":" + poster.get("posterName"));
		}
		// 获得待领取任务
		List<WaitReceiveTask> list = activitiMapper.getPerLeisureTask(candidateGroups);
		if (list != null && list.size() > 0) {
			// 借贷类型名称
			Map<String, Map<String, Object>> map = dataDictionaryService.getDataMap();
			Map<String, Object> workflowBusTypeMap = map.get("workflowBusType");
			// 筛选符合要求记录
			for (WaitReceiveTask task : list) {
				// 进件人属性
				List<Map<String, Object>> childRoles = activitiMapper.queryRoles(task.getRegisterId());
				boolean chuldFlag = false;
				if (childRoles != null && childRoles.size() > 0) {
					for (Map<String, Object> role : childRoles) {
						if (role.get("type") != null && "00".equals(role.get("type").toString())) {// 角色类型为客户经理
							chuldFlag = true;
							break;
						}
					}
				}
				// 借贷申请
				if ("loan".equals(task.getProcessType())) {
					task.setProcessTypeName(
							(String) ((Map<String, Object>) workflowBusTypeMap.get("item")).get("loan"));
					// 项目经理过滤
					if (chuldFlag == true && !userId.equals(task.getRegisterId())) {
						continue;
					}
					resultlist.add(task);
				}
			}
		}
		int size = resultlist != null ? resultlist.size() : 0;
		int endIndex = start + (end == null ? 0 : end);
		returnMap.put("count", size);
		if (size > 0) {
			returnMap.put("list", resultlist.subList(start, (end == null || size < endIndex) ? size : endIndex));
		} else {
			returnMap.put("list", new ArrayList<WaitReceiveTask>());
		}

		return returnMap;
	}

	@Override
	public Map<String, Object> getPersonalTask(String userId, String realName, Integer start, Integer end) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("user", userId + ":" + realName);
		where.put("userId", userId);
		where.put("start", start);
		where.put("end", end);
		// 获待办理任务
		List<WaitHandleTask> list = activitiMapper.getPersonalTask(where);
		int count = activitiMapper.getPersonalTaskCount(where.get("user").toString());
		Map<String, Map<String, Object>> map = dataDictionaryService.getDataMap();
		Map<String, Object> workflowBusTypeMap = map.get("workflowBusType");
		if (count > 0) {
			for (WaitHandleTask task : list) {
				task.setProcessTypeName((String) ((Map<String, Object>) workflowBusTypeMap.get("item")).get("loan"));
			}
			returnMap.put("count", count);
			returnMap.put("list", list);
		} else {
			returnMap.put("count", count);
			returnMap.put("list", new ArrayList<WaitHandleTask>());
		}
		return returnMap;
	}

	@Transactional
	@Override
	public boolean claim(String taskId, String taskKey, String userId, String realName) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 判断任务是否已经被签收
		if (task != null && task.getAssignee() == null) {
			synchronized (this) {
				taskService.claim(taskId, userId + ":" + realName);
				taskService.setVariable(taskId, taskKey + "Assignee", userId + ":" + realName);
			}
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public Map<String, String> submittedForApproval(SubmitForApproval submit) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			// 获得产品申请信息
			FullLoanProductApplyInfo loanProductApply = productApplyInfoMapper
					.getLoanProductApplyById(submit.getLoanProductApplyId());
			// 获取流程实例信息
			ProcessInstance pd = runtimeService.createProcessInstanceQuery()
					.processInstanceId(submit.getProcessInstanceId()).singleResult();
			// 获得当前节点审批信息
			Task task = taskService.createTaskQuery().taskId(submit.getTaskId()).singleResult();
			if (task == null) {
				// 借贷申请已进入下一节点
				throw new BusinessException(ResponseConstants.LOAN_APPLY_PROCESS_TASK_ERROR.getRetCode(),
						ResponseConstants.LOAN_APPLY_PROCESS_TASK_ERROR.getRetMsg());
			}
			if (!task.getAssignee().equals(submit.getAuditUserId() + ":" + submit.getAuditUserName())) {
				resultMap.put("code", "9500");
				resultMap.put("message", "非法审批人");
				return resultMap;
			}
			// 流程变量过滤
			if (submit.getVariables() != null && !submit.getVariables().isEmpty()) {
				Set<String> variablesSet = submit.getVariables().keySet();
				Iterator<String> it = variablesSet.iterator();
				while (it.hasNext()) {
					String key = it.next();
					Object v = submit.getVariables().get(key);
					if (v instanceof String) {
						String var = (String) v;
						if (var.startsWith("#") && var.endsWith("#")) {
							var = var.replaceAll("#", "");
							Map<String, Object> param = new HashMap<String, Object>();
							param.put("loanProductApply", loanProductApply);
							Object r = RegulationUtil.invokeMethod(var, param);
							if (r instanceof BigDecimal) {
								BigDecimal b = (BigDecimal) r;
								submit.getVariables().put(key, b.doubleValue());
							} else {
								submit.getVariables().put(key, r);
							}

						}
					}
				}
			}
			// 合同签订状态
			Integer ownContract = Integer.valueOf(loanProductApply.getOwnContract()==null?"0":loanProductApply.getOwnContract());
			if (new Integer(1).equals(ownContract)) {
				submit.getVariables().put("hasContract", "y");
			} else {
				submit.getVariables().put("hasContract", "n");
			}
			String method = "N";
			if (StringUtils.isNoneEmpty(submit.getNextTaskKey())) {
				method = "Y";
			}
			Map<String, String> message = new HashMap<String, String>();
			// 同意 设置下一节点审批人
			if ("Y".equals(method) && StringUtils.isNotBlank(submit.getNextTaskKey())) {
				// 获取下一节点配置信息
				ActTaskset nextActTaskset = activitiMapper.getTheActTaskset(loanProductApply.getLoanProductId(),
						pd.getProcessDefinitionId(), submit.getNextTaskKey());
				message.put("taskName", nextActTaskset.getTaskName());
				if (!StringUtils.isBlank(submit.getNextUserId()) && !StringUtils.isBlank(submit.getNextUserName())) {// 设置下一节点处理人
					// 下一节点指定审批人
					submit.getVariables().put("assigneeId", submit.getNextUserId());
					submit.getVariables().put("assigneeName", submit.getNextUserName());
					runtimeService.removeVariable(task.getExecutionId(), submit.getAudit() + "Assignee");
				} else {
					// 设置下一节点候选人，候选机构，候选角色
					List<String> candidate = new ArrayList<String>();
					if (!StringUtils.isBlank(nextActTaskset.getUserids())) {
						// 配置用户返回
						String[] userIds = nextActTaskset.getUserids() != null ? nextActTaskset.getUserids().split(",")
								: new String[0];
						List<Map<String, Object>> users = sysUserMapper.queryStaffDetailByids(userIds);
						for (Map<String, Object> user : users) {
							String uId = (String) user.get("userId");
							String uName = (String) user.get("userName");
							candidate.add("u:" + uId + ":" + uName);
						}
					}
					if (!StringUtils.isBlank(nextActTaskset.getOrganizationids())) {
						// 配置组织返回
						String[] orgIds = nextActTaskset.getOrganizationids() != null
								? nextActTaskset.getOrganizationids().split(",") : new String[0];
						List<Map<String, Object>> orgs = orgMapper.queryOrganizationByOrgIds(orgIds);
						for (Map<String, Object> org : orgs) {
							String orgId = (String) org.get("id");
							String orgName = (String) org.get("orgName");
							candidate.add("o:" + orgId + ":" + orgName);
						}
					}
					if (!StringUtils.isBlank(nextActTaskset.getRoleids())) {
						// 配置角色返回
						String[] roleIds = nextActTaskset.getRoleids() != null ? nextActTaskset.getRoleids().split(",")
								: new String[0];
						List<Map<String, Object>> roles = roleMapper.queryRoleDetailByIds(roleIds);
						for (Map<String, Object> role : roles) {
							String roleId = (String) role.get("id");
							String roleName = (String) role.get("roleName");
							candidate.add("r:" + roleId + ":" + roleName);
						}
					}
					if (!StringUtils.isBlank(nextActTaskset.getPosterids())) {
						// 配置岗位返回
						String[] posterIds = nextActTaskset.getPosterids() != null
								? nextActTaskset.getPosterids().split(",") : new String[0];
						List<Map<String, Object>> posters = posterMapper.queryPosterByIds(posterIds);
						for (Map<String, Object> poster : posters) {
							String posterId = (String) poster.get("id");
							String posterName = (String) poster.get("posterName");
							candidate.add("p:" + posterId + ":" + posterName);
						}
					}
					submit.getVariables().put("candidate", candidate);
				}
			}
			// 机构信息
			submit.getVariables().put("orgId", loanProductApply.getOrgId());
			// 审批意见
			Authentication.setAuthenticatedUserId(submit.getAuditUserId() + ":" + submit.getAuditUserName());// 设定创建人
			// 添加批注信息
			if (!StringUtils.isEmpty(submit.getComment())) {
				Comment co = taskService.addComment(submit.getTaskId(), submit.getProcessInstanceId(),
						submit.getAudit(), submit.getComment());// comment为批注内容
				// 处理退回拒绝原因
				if (co != null && submit.getReason() != null && !submit.getReason().equals("")) {
					// 退回拒绝原因
					String commentId = co.getId();
					String type = co.getType();
					activitiMapper.addHandleReason(commentId, type, submit.getReason(), submit.getProcessInstanceId());
				}
			}
			// 设置流程变量
			taskService.setVariables(submit.getTaskId(), submit.getVariables());
			// 启动流程
			taskService.complete(submit.getTaskId());
			pd = runtimeService.createProcessInstanceQuery().processInstanceId(submit.getProcessInstanceId())
					.singleResult();
			if (pd == null) {// 流程结束
				HistoricProcessInstance pdHistroty = historyService.createHistoricProcessInstanceQuery()
						.processInstanceId(submit.getProcessInstanceId()).singleResult();
				if (pdHistroty.getEndActivityId().equals("end4")) {
					// 结束全部流程
					resultMap.put("code", "9999");
					resultMap.put("message", "流程结束");
				} else {// 拒绝
					resultMap.put("code", "9000");
					resultMap.put("message", "流程拒绝");
				}
			} else {// 流程继续执行
				resultMap.put("code", "0000");
				resultMap.put("message", "流程正常处理");
			}

		} catch (Exception e) {
			resultMap.put("code", "9800");
			resultMap.put("message", "流程处理异常");
			e.printStackTrace();
		}
		return resultMap;
	}
}
