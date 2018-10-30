package com.company.platform.restapi.dao.custom;

import com.company.platform.restapi.model.custom.CustomerJobInfo;


/** 
* @ClassName: customerJobDetailMapper 
* @Description: TODO(插入用户工作信息) 
* @author yangxu 
* @date 2018年1月30日 上午7:43:03 
*  
*/
public interface CustomerJobDetailMapper {

	/**
	 * 
	* @Title: insertCustomerJobInfo 
	* @Description: TODO(插入信息) 
	* @param @param info (客户证件类型、证件号等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void insertCustomerJobInfo(CustomerJobInfo info);
}
