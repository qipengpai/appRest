package com.zkbr.platform.controller.activiti.listion;

import java.util.HashSet;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/** 
* @ClassName: SingleHandleListioner 
* @Description: TODO(设置此流程任务处理人activiti Listioner) 
* @author luyuchi
* @date 2018年6月13日 上午10:15:03 
*  
*/
public class SingleHandleListioner implements TaskListener {

	/**
	 * 对象序列化ID，类结构更改请重新生成该值
	 */
	private static final long serialVersionUID = -6735337054347169643L;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		//组成当前节点处理人在流程变量中的key
		String assigneekey = delegateTask.getTaskDefinitionKey() + "Assignee";
		//获得流程待办人员，退回，或补件等退回操作不为null
		String assignee = (String) delegateTask.getVariable(assigneekey);
		if(assignee == null) {
			String userId = (String) delegateTask.getVariable("assigneeId");
			String userName = (String) delegateTask.getVariable("assigneeName");
			assignee = userId + ":" + userName;//流程处理人id=userId:userRealName
			delegateTask.setVariable(assigneekey, assignee);
		}
		Boolean noticeFlag = (Boolean) delegateTask.getVariable("noticeFlag_");
		if(noticeFlag != null && noticeFlag) {//noticeFlag=true发送消息
			Set<String> recipients = new HashSet<String>();
			String[] userInfo = assignee.split(":");//流程处理人id=userId:userRealName
			recipients.add(userInfo[0]);
			delegateTask.setVariable("recipients", recipients);
			delegateTask.setVariable("theTaskName", delegateTask.getName());
		}
		
		delegateTask.setAssignee(assignee);//设置当前节点处理人员
		
	}

}
