package com.company.platform.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.company.platform.security.model.SecurityUser;

/**
 * 微信登陆用户信息获取
* @ClassName: WeixinCustomUserDetailsService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 下午7:20:31 
*
 */
public interface WeixinCustomUserDetailsService {
	/**
	 * 获取用信息详情信息
	* @Title: loadUserByUsername 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param optionId
	* @param @return    设定文件 
	* @return UserDetails    返回类型 
	* @throws
	 */
	public SecurityUser loadUserByOpenId(String openId);
	
	/**
	 * 获取用信息详情信息
	* @Title: loadUserByUsername 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param optionId
	* @param @return    设定文件 
	* @return UserDetails    返回类型 
	* @throws
	 */
	public SecurityUser loadUserByUsername(String username);
	
	/**
	 * 获取缓存user
	* @Title: getUserDetails 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param openId
	* @param @return    设定文件 
	* @return UserDetails    返回类型 
	* @throws
	 */
	public SecurityUser getUserDetails(String openId);
	
	/**
	 * 更新缓存User
	* @Title: setUserDetails 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param openId
	* @param @param userDetails
	* @param @return    设定文件 
	* @return 
	* @throws
	 */
	public  void setUserDetails(String openId,SecurityUser userDetails);
	
	/**
	 * 绑定用户openId
	* @Title: upUserOpenId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userName
	* @param @param openId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int upUserOpenId(String userName,String openId);
	
	/**
	 * 根据code  查询openId
	* @Title: getCodeOrOpenId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String  getCodeOrOpenId(String code);
	
	/**
	 * 缓存 code  openId关系
	* @Title: getCodeOrOpenId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public void  setCodeOrOpenId(String code,String openId);

	/**
	 * 根据userId  查询roleType
	* @Title: roleTypeByUserId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String roleTypeByUserId(String userId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 清除用户缓存 清除数据
	 * @Date 9:29 2018/9/28
	 * @Param [openId]
	 * @return java.lang.Boolean
	 **/
    Boolean delUserDetails(String openId);
}
