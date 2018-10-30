package com.company.platform.base.service.sys;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: IPosterService 
* @Description: TODO(岗位信息操作) 
* @author luyuchi
* @date 2018年3月21日 下午1:04:47 
*  
*/
public interface IPosterService {

	/** 
	* @Title: queryPosterByIds 
	* @Description: TODO(通过岗位id集合获得多个岗位信息) 
	* @param @param posterids
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryPosterByIds(String posterids);

}
