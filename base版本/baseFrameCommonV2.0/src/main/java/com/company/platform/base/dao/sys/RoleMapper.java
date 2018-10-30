package com.company.platform.base.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

	List<Map<String, Object>> queryRoleDetailByIds(@Param("roleStrs") String[] roleStrs);

}
