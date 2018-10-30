package com.company.platform.restapi.service.loan;

import com.company.platform.restapi.model.custom.CorporationCustomerForm;

/** 
* @ClassName: ICorporationCustomerService 
* @Description: TODO(增加企业用户信息) 
* @author yangxu 
* @date 2018年1月30日 上午8:35:57 
*  
*/
public interface ICorporationCustomerService {
	/** 
	* @Title: merge 
	* @Description: TODO(用于增加企业用户信息) 
	* @param @param corporationCustomer
	* @param @param userId
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @throws 
	*/
	Boolean merge( CorporationCustomerForm corporationCustomer, String userId);
}
