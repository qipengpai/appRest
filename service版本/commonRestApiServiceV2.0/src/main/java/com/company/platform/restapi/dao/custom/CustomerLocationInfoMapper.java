package com.company.platform.restapi.dao.custom;

import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;

/** 
* @ClassName: CustomerLocationInfoMapper 
* @Description: TODO(本地数据信息) 
* @author yangxu 
* @date 2018年1月30日 上午11:29:55 
*  
*/
public interface CustomerLocationInfoMapper {
	/**
	 * 
	* @Title: deleteByCustomerId 
	* @Description: TODO(清除本地数据信息) 
	* @param @param info (客户id)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void deleteByCustomerId(String customerId);

	/**
	 * 
	* @Title: selectByCustomerId 
	* @Description: TODO(查询本地数据信息-户籍地址) 
	* @param @param info (客户id)
	* @param @return    设定文件 
	* @return String    返回类型
	* @throws
	 */
	String selectByCustomerId(String customerId);

	CustomerLocationInfo queryByCustomerIdAndType(String customerId, String registry);
}
