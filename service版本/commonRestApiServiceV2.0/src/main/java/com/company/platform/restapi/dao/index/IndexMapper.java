package com.company.platform.restapi.dao.index;

import java.util.List;

import com.company.platform.restapi.model.basicdata.ProductInfo;

/**
 * 初始录入页面基本元素准备
* @ClassName: IndexMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月13日 上午11:49:38 
*
 */
public interface IndexMapper {
	/**
	 * 获取机构可见产品
	* @Title: getIndexProductList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    设定文件 
	* @return List<ProductInfo>    返回类型 
	* @throws
	 */
	public List<ProductInfo> getIndexProductList(String orgId);
}
