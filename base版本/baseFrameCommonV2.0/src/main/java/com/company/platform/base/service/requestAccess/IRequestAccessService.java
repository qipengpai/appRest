/******************************************************************
 *
 *    Package:     com.company.platform.service.requestAccess
 *
 *    Filename:    IRequestAccessService.java
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
package com.company.platform.base.service.requestAccess;

import java.util.Map;

/** 
* @ClassName: IRequestAccessService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年9月25日 下午1:42:24 
*  
*/
public interface IRequestAccessService {

	/** 
	* @Title: addRequestAccessInfo 
	* @Description: TODO(添加用户操作日志) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void addRequestAccessInfo(Map<String,Object> info);
}
