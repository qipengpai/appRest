package com.company.platform.restapi.service.network;

/** 
* @ClassName: IProductUpdateInfoService 
* @Description: TODO(判断产品更新时间) 
* @author liang
* @date 2018年4月27日 下午9:25:53 
*  
*/
public interface IProductUpdateInfoService {

	/** 
	* @Title: getProductUpdateTime 
	* @Description: TODO(产品是否需要进行更新) 
	* @param @param updateTime
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @throws 
	*/
	Boolean getProductUpdateTime(String updateTime);
	
}
