package com.company.platform.restapi.dao.custom;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.custom.CustomerPublicInfo;

/** 
* @ClassName: CustomerPublicInfoMapper 
* @Description: TODO(获取或更新客户公共基础信息) 
* @author yangxu 
* @date 2018年1月30日 上午10:43:39 
*  
*/
public interface CustomerPublicInfoMapper {
	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description: TODO(查询客户公共基础信息) 
	* @param @param CustomerId (客户id)
	* @param @return    设定文件 
	* @return CustomerPublicInfo    返回类型
	* @throws
	 */
	CustomerPublicInfo selectByPrimaryKey(String CustomerId);
	
	/**
	 * 
	* @Title: updateCustomerIdRisk 
	* @Description: TODO(插入信息) 
	* @param @param Id,eType.. (客户id,客户类型等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void updateCustomerIdRisk(@Param("Id") String Id, @Param("eType") String eType, @Param("credentialType") String credentialType, @Param("credentialNo") String credentialNo);
	
	/**
	 * 
	* @Title: updateByPrimaryKey 
	* @Description: TODO(插入信息) 
	* @param @param customerPublicInfo (客户id,客户类型等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void updateByPrimaryKey(CustomerPublicInfo customerPublicInfo);
	
	
	/**
	 * 
	* @Title: insertSelective 
	* @Description: TODO(插入信息) 
	* @param @param customerPublicInfo (客户id,客户类型等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void insertSelective(CustomerPublicInfo customerPublicInfo);
	
	/**
	 * 获取用户信息 根据身份证
	* @Title: selectByIdCard 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param idCard    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public CustomerPublicInfo selectByIdCard(@Param("idCard")String idCard);
}
