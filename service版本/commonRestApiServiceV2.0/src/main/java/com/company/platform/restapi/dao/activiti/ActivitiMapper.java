package com.company.platform.restapi.dao.activiti;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.restapi.model.activiti.WaitHandleTask;
import com.company.platform.restapi.model.activiti.WaitReceiveTask;
public interface ActivitiMapper {
	/**
	 * 查询机构信息 
	* @Title: queryOrg 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId 用户id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	Map<String,Object> queryOrg(@Param("userId")String userId);
	
	/**
	 * 获取岗位信息
	* @Title: queryPoster 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	List<Map<String,Object>> queryPoster(@Param("userId")String userId);
	
	/**
	 * 获得角色列表
	* @Title: queryRoles 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId 用户id
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	List<Map<String, Object>> queryRoles(@Param("userId")String userId);
	
    /**
     * 查询用户信息详情
    * @Title: queryStaffDetail 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param userId
    * @param @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws
     */
	Map<String, Object> queryStaffDetail(@Param("userId")String userId);
	
	/**
	 * 查詢待領取列表
	* @Title: getPerLeisureTask 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userGroup
	* @param @return    设定文件 
	* @return List<WaitReceiveTask>    返回类型 
	* @throws
	 */
	public List<WaitReceiveTask> getPerLeisureTask(@Param("userGroup")List<String> userGroup);
	
    /**
     * 查询代办任务列表
    * @Title: getPersonalTask 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param whereMap {user:"xxx:xxx",userId:"",start:"",end:""}
    * @throws
     */
	public List<WaitHandleTask> getPersonalTask(Map<String, Object> whereMap);

	/**
	 * 查询待办任务列表数量
	* @Title: getPersonalTaskCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getPersonalTaskCount( @Param("user")String user);
	
	/**
	 * 添加拒绝/退回原因
	* @Title: addHandleReason 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param commentId
	* @param @param type
	* @param @param reason
	* @param @param processInstanceId    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void addHandleReason(@Param("commentId") String commentId, @Param("type") String type, @Param("reason") String reason, @Param("processInstanceId") String processInstanceId);

	
    /**
     * 获取流程节点配置信息
    * @Title: getTheActTaskset 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param productId 产品id
    * @param @param wfId 流程key
    * @param @param taskKey 节点id
    * @param @return    设定文件 
    * @return ActTaskset    返回类型 
    * @throws
     */
	ActTaskset getTheActTaskset(@Param("productId") String productId, @Param("wfId") String wfId, @Param("taskKey") String taskKey);

}
