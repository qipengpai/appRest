package com.company.platform.base.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PosterMapper {

	List<Map<String, Object>> queryPosterByIds(@Param("posterStrs") String[] posterStrs);

}
