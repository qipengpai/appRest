/******************************************************************
 *
 *    Package:     com.company.platform.dao.requestAccess
 *
 *    Filename:    RequestAccessMapper.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.base.dao.requestAccess;

import java.util.Map;

/**
 * @ClassName: RequestAccessMapper
 * @Description: TODO(操作用户访问信息汇总mapper)
 * @author zhengjn
 * @date 2017年9月25日 下午1:46:33
 * 
 */
public interface RequestAccessMapper {

	/** 
	* @Title: addRequestAccessInfo 
	* @Description: TODO(添加用户访问信息) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void addRequestAccessInfo(Map<String, Object> info);

}
