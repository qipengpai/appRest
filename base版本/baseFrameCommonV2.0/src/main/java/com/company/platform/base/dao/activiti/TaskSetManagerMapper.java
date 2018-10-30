package com.company.platform.base.dao.activiti;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.base.model.activiti.ActTaskset;
import com.company.platform.base.model.activiti.FunctionInfo;

public interface TaskSetManagerMapper {

	Map<String, Object> getFirstFlowInfo(@Param("productId") String productId, @Param("wfId") String wfId);

	ActTaskset getTheActTaskset(@Param("productId") String productId, @Param("wfId") String wfId,
			@Param("taskKey") String taskKey);

	List<Map<String, Object>> getWsFunctions(@Param("taskSetId") String taskSetId);

	List<ActTaskset> selectByProductId(@Param("productId") String productId, @Param("processId") String processId);

	List<Map<String, Object>> getHandleReasons(@Param("processInstanceId") String processInstanceId);

	List<Map<String, Object>> getAUComments(@Param("processInstanceId") String processInstanceId);

	FunctionInfo findFunctionInfoByFunctionName(@Param("productId") String productId, @Param("wfId") String wfId,
			@Param("taskKey") String taskKey, @Param("functionName") String functionName);

	String getVariableByNextTaskKey(@Param("productId") String productId, @Param("wfId") String wfId,
			@Param("taskKey") String taskKey);
	
	/**
	 * 获取审批按钮
	* @Title: getButton 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param productId
	* @param @param wfId
	* @param @param taskKey
	* @param @return    设定文件 
	* @return List<FunctionInfo>    返回类型 
	* @throws
	 */
	List<FunctionInfo> getButton(@Param("productId") String productId, @Param("wfId") String wfId,@Param("taskKey") String taskKey);

}
