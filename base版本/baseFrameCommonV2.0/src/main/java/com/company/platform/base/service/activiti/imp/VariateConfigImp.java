package com.company.platform.base.service.activiti.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.activiti.VariateConfigMapper;
import com.company.platform.base.service.activiti.IVariateConfig;

/** 
* @ClassName: VariateConfigImp 
* @Description: TODO(流程变量信息操作) 
* @author luyuchi
* @date 2018年3月21日 下午1:00:12 
*  
*/
@Service
public class VariateConfigImp implements IVariateConfig {
	
	@Autowired
	private VariateConfigMapper variateConfigMapper;

	@Override
	public Map<String, Object> getFlowVariateByWfKey(String processDefinitionKey) {
		Map<String, Object> flowVariate = new HashMap<String, Object>();
		List<Map<String, Object>> vars = variateConfigMapper.getFlowVariateByWfKey(processDefinitionKey);
		for(Map<String, Object> v : vars) {
			String constantKey = (String) v.get("constantKey");
			String constantValue = (String) v.get("constantValue");
			String dataType = (String) v.get("dataType");
			if("integer".equals(dataType)) {
				Integer value = Integer.valueOf(constantValue);
				flowVariate.put(constantKey, value);
			} else if("double".equals(dataType) || "decimal".equals(dataType)) {
				Double value = Double.valueOf(constantValue);
				flowVariate.put(constantKey, value);
			} else {
				flowVariate.put(constantKey, constantValue);
			}
		}
		return flowVariate;
	}

}
