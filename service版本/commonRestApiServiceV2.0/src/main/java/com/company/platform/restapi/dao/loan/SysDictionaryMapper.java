package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: SysDictionaryMapper 
* @Description: TODO(系统字典) 
* @author yangxu 
* @date 2018年1月31日 上午8:35:44 
*  
*/
public interface SysDictionaryMapper {
	/** 
	* @Title: getGlobalData 
	* @Description: TODO(获取全局数据) 
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getGlobalData();

}
