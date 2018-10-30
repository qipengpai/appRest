package com.company.platform.base.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.base.model.sys.Organization;

public interface OrgMapper {

	List<Map<String, Object>> queryOrganizationByOrgIds(@Param("orgStrs") String[] orgStrs);

	List<Organization> queryAllOrganization();

	Organization selectByPrimaryKey(@Param("id") String id);
	
	List<Map<String, Object>> queryStaffByOrgId(@Param("orgStrs") String[] orgStrs);



}
