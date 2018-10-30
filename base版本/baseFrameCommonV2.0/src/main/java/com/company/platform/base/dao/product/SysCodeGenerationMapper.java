package com.company.platform.base.dao.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysCodeGenerationMapper {

	@Select("select * from sys_code_generation where type=#{type}")
	List<Map<String, String>> selectByType(String type);
	
	@Update("update sys_code_generation set name=#{name} where type=#{type} and code=#{code}")
	int updateVal(@Param("type") String type, @Param("code") String code, @Param("name") String name);
	
	@Select("select count(*) from sys_code_generation where type=#{type}")
	int selectLoanByType(String type);
	
	@Insert("insert into sys_code_generation(code,name,type) values(#{code},#{name},#{type})")
	int save(@Param("code") String code, @Param("name") String name, @Param("type") String type);
	
	@Select("select name from sys_code_generation where type=#{type} and code=#{code}")
	String selectVal(@Param("type") String type, @Param("code") String code);
}
