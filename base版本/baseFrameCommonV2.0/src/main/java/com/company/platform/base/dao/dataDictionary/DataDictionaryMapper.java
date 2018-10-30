/******************************************************************
 *
 *    Package:     com.company.platform.base.dao.dataDictionary
 *
 *    Filename:    DataDictionaryMapper.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.base.dao.dataDictionary;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DataDictionaryMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月11日 下午1:22:24
 * 
 */
public interface DataDictionaryMapper {

	/** 
	* @Title: getGlobalConfigInfo 
	* @Description: TODO(通过全局配置的name查询value) 
	* @param @param name
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getGlobalConfigInfo(String name);
	
	/** 
	* @Title: getSystemData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getSystemData();
	
	/** 
	* @Title: getGlobalData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getGlobalData(String code);
}
