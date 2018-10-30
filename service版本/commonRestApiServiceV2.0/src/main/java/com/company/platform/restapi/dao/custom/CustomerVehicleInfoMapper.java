package com.company.platform.restapi.dao.custom;

import com.company.platform.restapi.model.custom.CustomerVehicleInfo;

/** 
* @ClassName: CustomerVehicleInfoMapper 
* @Description: TODO(个人客户车辆信息) 
* @author yangxu 
* @date 2018年1月30日 上午8:08:40 
*  
*/
public interface CustomerVehicleInfoMapper {
	/**
	 * 
	* @Title: insertCustomerVehicleInfo 
	* @Description: TODO(插入信息) 
	* @param @param info (客户id,车牌号等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void insertCustomerVehicleInfo(CustomerVehicleInfo info);
}
