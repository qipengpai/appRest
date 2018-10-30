package com.company.platform.restapi.dao.custom;

import com.company.platform.restapi.model.custom.CorporationInfo;

/** 
* @ClassName: CorporationInfoMapper 
* @Description: TODO(企业信息) 
* @author yangxu 
* @date 2018年1月30日 上午11:39:05 
*  
*/
public interface CorporationInfoMapper {
	
	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description: TODO(插入信息) 
	* @param @param info (客户id)
	* @param @return    设定文件 
	* @return CorporationInfo    返回类型
	* @throws
	 */
	CorporationInfo selectByPrimaryKey(String customerId);
	
	/**
	 * 
	* @Title: updateByPrimaryKey 
	* @Description: TODO(更新信息) 
	* @param @param info (客户证件类型、证件号等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void updateByPrimaryKey(CorporationInfo corporationInfo);
	
	/**
	 * 
	* @Title: insertSelective 
	* @Description: TODO(插入信息) 
	* @param @param info (客户证件类型、证件号等)
	* @param @return    设定文件 
	* @return void    返回类型
	* @throws
	 */
	void insertSelective(CorporationInfo corporationInfo);
}
