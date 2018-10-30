/******************************************************************
 *
 *    Package:     com.company.platform.security.service
 *
 *    Filename:    ISecurityService.java
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
package com.company.platform.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: ISecurityService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月5日 下午7:26:15 
*  
*/
public interface ISecurityService {

	/** 
	* @Title: findByUserName 
	* @Description: TODO(通过登陆账号查询客户完整信息) 
	* @param @param username
	* @param @return    设定文件 
	* @return HashMap<String,String>    返回类型 
	* @throws 
	*/
	HashMap<String , String > findByUserName(String username);
	
	/** 
	* @Title: findRoleByuserid 
	* @Description: TODO(通过用户id查询角色) 
	* @param @param id
	* @param @return    设定文件 
	* @return ArrayList<HashMap<String,String>>    返回类型 
	* @throws 
	*/
	ArrayList<HashMap<String,String>> findRoleByUserId(String id);
	
	/** 
	* @Title: findResourceByAll 
	* @Description: TODO(依据角色id检索出所有角色资源信息) 
	* @param @return    设定文件 
	* @return ArrayList<HashMap<String,String>>    返回类型 
	* @throws 
	*/
	ArrayList<HashMap<String,String>> findResourceByRoleId(String roleId);
	
	/** 
	* @Title: findOrgByuserId 
	* @Description: TODO(通过用户id查询机构) 
	* @param @param id
	* @param @return    设定文件 
	* @return ArrayList<HashMap<String,String>>    返回类型 
	* @throws 
	*/
	ArrayList<HashMap<String,String>> findOrgByUserId(String id);
	
	/** 
	* @Title: findPosterByUserId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return ArrayList<HashMap<String,String>>    返回类型 
	* @throws 
	*/
	ArrayList<HashMap<String,String>> findPosterByUserId(String id);
	
	/**
	 * 根据openId获取 用户信息
	* @Title: getUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param openId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	Map<String,Object> getUser(String openId);
}
