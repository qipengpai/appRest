package com.company.platform.base.service.activiti;

import java.util.List;
import java.util.Map;

import com.company.platform.base.model.activiti.FunctionInfo;
/**
 * 微信开启流程/审批接口
* @ClassName: WeiXinActivitiService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月17日 下午5:07:23 
*
 */
public interface WeiXinActivitiService {


	/**
	 *  开启流程接口
	 * {retCode":"0000","retMsg":"处理成功"}
	* @Title: startActiviti 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param loanProductApplyId 产品申请id
	* @param @param nextTaskKey 下一节点 key
	* @param @param audit 权限
	* @param @param userId 用户id
	* @param @param realname 用户真实姓名
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String,String> startActiviti(String loanProductApplyId, String nextTaskKey,String audit,String userId,String realname);
	

	/**
	* 流程审批接口
	* {retCode":"0000","retMsg":"处理成功"}
	* @Title: approveActiviti 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param processInstanceId  流程ID
	* @param @param taskId  节点id
	* @param @param audit  编辑状态
	* @param @param comment 审批意见
	* @param @param auditUser 审批人
	* @param @param processType 流程图类型
	* @param @param functionName 节点名称
	* @param @param nextTaskKey 下一节点key
	* @param @param varJsonStr 流程变量
	* @param @param noticeFlag 消息发送标记
	* @param @param noticeTemp 消息发送标记
	* @param @param reason
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String,String> approveActiviti(String userId,String loanProductApplyId,String comment,String audit,String varJsonStr,String functionName);
	
	
	/**
	 * 获取审批按钮组
	* @Title: getButton 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param productId产品ID
	* @param procdefKey 产品流程id
	* @param @return    设定文件 
	* @return List<FunctionInfo>   返回类型 
	* @throws
	 */
	public List<FunctionInfo> getButton(String productId,String procdefKey);
	
	/**
	 * 
	* @Title: checkYyssys 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String,Object> checkYyssys(String customerName,String idCard,String phone);
}
