package com.company.platform.restapi.dao.custom;

import com.company.platform.restapi.model.custom.CustomerHouseInfo;

/** 
* @ClassName: CustomerHouseInfoMapper 
* @Description: TODO(插入客户房屋信息) 
* @author yangxu 
* @date 2018年1月30日 上午8:00:11 
*  
*/
public interface CustomerHouseInfoMapper {
	/**
	 * 
	* @Title: insertCustomerHouseInfo 
	* @Description: TODO(插入信息) 
	* @param @param info (客户证件类型、证件号等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void insertCustomerHouseInfo(CustomerHouseInfo info);
}
