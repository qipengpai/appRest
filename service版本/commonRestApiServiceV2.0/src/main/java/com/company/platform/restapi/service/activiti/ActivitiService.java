package com.company.platform.restapi.service.activiti;

import java.util.Map;

import com.company.platform.restapi.model.activiti.SubmitForApproval;

/**
 * 流程API相关调用
* @ClassName: ActivitiService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年7月12日 下午1:52:23 
*
 */
public interface ActivitiService {
	/**
	 * 查詢待領取列表
	* @Title: getPerLeisureTask 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId 用戶id
	* @param @param realName 用戶真实姓名
	* @param @param start 起始条数,
	* @param @param end 查询多少条
	* @param @return    设定文件 
	* @return Map<String,Object>  {count:"总数",list:"结果集"} 
	* @throws
	 */
	public Map<String, Object> getPerLeisureTask(String userId, String realName, Integer start, Integer end);

	/**
	 * 查询待办理列表
	* @Title: getPersonalTask 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId 用戶id
	* @param @param realName 用戶真实姓名
	* @param @param start 起始条数,
	* @param @param end 查询多少条
	* @param @return    设定文件 
	* @return Map<String,Object>  {count:"总数",list:"结果集"} 
	* @throws
	 */
	public Map<String, Object> getPersonalTask(String userId, String realName, Integer start, Integer end);

	/**
	 * 领取任务
	 * @Title: claim   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param taskId				节点ID
	 * @param: @param taskKey		                            节点定义key
	 * @param: @param userId				用户ID
	 * @param: @param realName				真实姓名
	 * @param: @return      
	 * @return: boolean true代表领取成功，false代表已经被领取了      
	 * @throws
	 */
	public boolean claim(String taskId, String taskKey, String userId, String realName);

	/**
	 * 
	* @Title: submittedForApprova 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param processInstanceId 流程实例id
	* @param taskId 任务id
	* @param variables 全局流程变量
	* @param localVariables 节点流程流程变量
	* @param comment 审批意见
	* @param audit 审核结果
	* @param auditUserId 审核人
	* @param auditUserName 审核人姓名
	* @param reason 退回拒绝原因
	* @param varJsonStr 按钮设置的流程变量
	* @param nextTaskKey 下一审核节点key值 
	* @param @return {code:"9999流程全部结束/9000拒绝/0000流程继续/9500/操作人与流程审批人不一致/9800/流程异常",message:"处理信息提示"} 
	* @return String    返回类型 
	* @throws
	 */
	public Map<String, String> submittedForApproval(SubmitForApproval submit);
}
