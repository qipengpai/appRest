package com.company.platform.restapi.service.index;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.company.platform.restapi.model.basicdata.ProductInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;

/**
 * 首页初始化 产品信息、会员信息
* @ClassName: IndeController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 下午3:46:36 
*
 */

public interface IndexService {
	
	/**
	 * 获取产品列表
	* @Title: getProductList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<ProductInfo>    返回类型 
	* @throws
	 */
	public List<ProductInfo> getIndexProductList(String orgId) throws Exception;
	
	/**
	 * 获取会员信息
	* @Title: getCsustomerPublicInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param idCard
	* @param @return    设定文件 
	* @return CustomerPublicInfo    返回类型 
	* @throws
	 */
	public CustomerPublicInfo getCsustomerPublicInfo(String idCard);
	
	/**
	 * 获取机构信息 
	* @Title: getOrgIdByUserId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws
	 */
	public Map<String, String> getOrgIdByUserId(String userId);

}
