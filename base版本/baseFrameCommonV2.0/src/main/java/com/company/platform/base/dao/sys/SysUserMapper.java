package com.company.platform.base.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

	List<Map<String, Object>> queryStaffDetailByids(@Param("userStrs") String[] userStrs);

	List<Map<String, Object>> queryUsersByOrgsAndRoles(@Param("orgIds") List<String> orgIds, @Param("roleStrs") String[] roleStrs);

	List<Map<String, Object>> queryUsersByOrgsAndPosts(@Param("orgIds") List<String> orgIds, @Param("posterStrs") String[] posterStrs);

	Map<String, Object> getUserInfoById(@Param("id") String id);
	
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
	public int upUserOpenId(@Param("userName")String userName,@Param("openId")String openId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 清除openId
	 * @Date 14:15 2018/9/28
	 * @Param [openId]
	 * @return int
	 **/
    int setUserInfoByOpenId(@Param("openId")String openId);
}
