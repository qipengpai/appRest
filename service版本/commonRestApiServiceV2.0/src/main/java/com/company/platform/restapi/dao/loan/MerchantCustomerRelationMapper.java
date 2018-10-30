package com.company.platform.restapi.dao.loan;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.MerchantCustomerRelation;

/** 
* @ClassName: MerchantCustomerRelationMapper 
* @Description: TODO(获取和插入商户信息) 
* @author yangxu 
* @date 2018年2月2日 下午2:17:18 
*  
*/
public interface MerchantCustomerRelationMapper {
	
	/** 
	* @Title: getRelationByCidAndMid 
	* @Description: TODO(获取商户信息) 
	* @param @param CustomerId
	* @param @param mid
	* @param @return    设定文件 
	* @return List<MerchantCustomerRelation>    返回类型 
	* @throws 
	*/
	List<MerchantCustomerRelation> getRelationByCidAndMid(@Param("customerId") String customerId, @Param("mid")String mid);
	
	/** 
	* @Title: getRelationByCidAndMorgid 
	* @Description: TODO(获取商户信息) 
	* @param @param CustomerId
	* @param @param mid
	* @param @return    设定文件 
	* @return List<MerchantCustomerRelation>    返回类型 
	* @throws 
	*/
	List<MerchantCustomerRelation> getRelationByCidAndMorgid(@Param("customerId") String customerId, @Param("orgId") String orgId);
	
	/** 
	* @Title: insertInfo 
	* @Description: TODO(插入商户信息) 
	* @param @param merchantCustomerRelation    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void insertInfo(MerchantCustomerRelation merchantCustomerRelation);
}
