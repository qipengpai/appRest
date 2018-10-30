package com.company.platform.base.dao.activiti;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface VariateConfigMapper {

	List<Map<String, Object>> getFlowVariateByWfKey(@Param("wfKey") String wfKey);

}
