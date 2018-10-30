package com.company.platform.base.service.activiti;

import java.util.Map;

/** 
* @ClassName: IVariateConfig 
* @Description: TODO(流程变量配置操作) 
* @author luyuchi
* @date 2018年3月21日 下午12:58:00 
*  
*/
public interface IVariateConfig {

	/** 
	* @Title: getFlowVariateByWfKey 
	* @Description: TODO(获得某节点流程变量配置信息) 
	* @param @param processDefinitionKey
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getFlowVariateByWfKey(String processDefinitionKey);

}
