package com.zkbr.platform.controller.activiti.listion;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.base.service.sys.ISysUserService;
import com.company.platform.base.util.ServiceBeanTool;

/** 
* @ClassName: MultPreprocessListioner 
* @Description: TODO(设置此流程任务领用人activiti Listioner) 
* @author luyuchi
* @date 2018年6月13日 上午10:31:14 
*  
*/
public class MultPreprocessListioner implements TaskListener {

	/**
	 * 对象序列化ID，类结构更改请重新生成该值
	 */
	private static final long serialVersionUID = 8968338432969379154L;

	@Override
	public void notify(DelegateTask delegateTask) {
		//组成当前节点处理人在流程变量中的key
		String assigneekey = delegateTask.getTaskDefinitionKey() + "Assignee";
		//获得流程待办人员，退回，或补件等退回操作不为null
		String assignee = (String) delegateTask.getVariable(assigneekey);
		Boolean noticeFlag = (Boolean) delegateTask.getVariable("noticeFlag_");
		if(assignee == null) {//首次执行，设置待拾取人员
			//初始化领用人分组
			List<String> candidate = (List<String>) delegateTask.getVariable("candidate");//在流程提交时，设置到流程变量的
			if(candidate != null && !candidate.isEmpty()) {
				if(candidate.get(0).startsWith("u")) {//多用户的领用组
					delegateTask.addCandidateUsers(candidate);//设置多个人中领用
					Set<String> recipients = new HashSet<String>();
					if(noticeFlag != null && noticeFlag) {//noticeFlag=true发送消息
						for(String candidateUser : candidate) {
							String[] userInfo = candidateUser.split(":");//candidateUser=userId:userName
							recipients.add(userInfo[1]);
						}
						
						delegateTask.setVariable("recipients", recipients);//设置消息接收人
						delegateTask.setVariable("theTaskName", delegateTask.getName());
					}
				} else {
					delegateTask.addCandidateGroups(candidate);//设置领用组内人员领用
					if(noticeFlag != null && noticeFlag) {//noticeFlag=true发送消息
						if(candidate.get(0).startsWith("r")) {//角色的领用组
							StringBuilder roleids = new StringBuilder();
							for(String candidateRole : candidate) {
								String[] roleInfo = candidateRole.split(":");
								roleids.append(roleInfo[1]);
								roleids.append(",");
							}
							String orgId = (String) delegateTask.getVariable("orgId");
							IOrganizationService organizationService = ServiceBeanTool.getBean(IOrganizationService.class);
							List<String> orgIds = organizationService.queryByVisibleOrgIds(orgId);
							ISysUserService sysUserService = ServiceBeanTool.getBean(ISysUserService.class);
							List<Map<String, Object>> users = sysUserService.queryUsersByOrgsAndRoles(orgIds, roleids.toString());
							Set<String> recipients = new HashSet<String>();
							for(Map<String, Object> user : users) {
								recipients.add((String) user.get("userId"));
							}
							delegateTask.setVariable("recipients", recipients);//设置消息接收人
							delegateTask.setVariable("theTaskName", delegateTask.getName());
						}
						if(candidate.get(0).startsWith("o")) {//机构的领用组
							StringBuilder organizationids = new StringBuilder();
							for(String candidateOrg : candidate) {
								String[] orgInfo = candidateOrg.split(":");
								organizationids.append(orgInfo[1]);
								organizationids.append(",");
							}
							IOrganizationService organizationService = ServiceBeanTool.getBean(IOrganizationService.class);
							List<Map<String, Object>> users = organizationService.queryStaffByOrgId(organizationids.toString());
							Set<String> recipients = new HashSet<String>();
							for(Map<String, Object> user : users) {
								recipients.add((String) user.get("id"));
							}
							delegateTask.setVariable("recipients", recipients);//设置消息接收人
							delegateTask.setVariable("theTaskName", delegateTask.getName());
						}
						if(candidate.get(0).startsWith("p")) {
							StringBuilder posterids = new StringBuilder();
							for(String candidateRole : candidate) {
								String[] posterInfo = candidateRole.split(":");
								posterids.append(posterInfo[1]);
								posterids.append(",");
							}
							String orgId = (String) delegateTask.getVariable("orgId");
							IOrganizationService organizationService = ServiceBeanTool.getBean(IOrganizationService.class);
							List<String> orgIds = organizationService.queryByVisibleOrgIds(orgId);
							ISysUserService sysUserService = ServiceBeanTool.getBean(ISysUserService.class);
							List<Map<String, Object>> users = sysUserService.queryUsersByOrgsAndPosts(orgIds, posterids.toString());
							Set<String> recipients = new HashSet<String>();
							for(Map<String, Object> user : users) {
								recipients.add((String) user.get("userId"));
							}
							delegateTask.setVariable("recipients", recipients);//设置消息接收人
							delegateTask.setVariable("theTaskName", delegateTask.getName());
						}
					}
				}
			}
		} else {
			if(noticeFlag != null && noticeFlag) {//noticeFlag=true发送消息
				if(assignee != null && !"".equals(assignee)) {
					Set<String> recipients = new HashSet<String>();
					String[] userInfo = assignee.split(":");
					recipients.add(userInfo[0]);
					delegateTask.setVariable("recipients", recipients);//设置消息接收人
					delegateTask.setVariable("theTaskName", delegateTask.getName());
				}
			}
			
			delegateTask.setAssignee(assignee);//设置当前节点处理人员
		}
	}

}
