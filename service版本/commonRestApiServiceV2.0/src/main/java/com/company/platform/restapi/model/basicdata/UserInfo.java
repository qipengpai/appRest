package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: UserInfo
 * @Description: TODO(用户信息)
 * @author liang
 * @date 2018年1月25日 下午5:36:59
 * 
 */
@SuppressWarnings("all")
public class UserInfo extends BaseModel{

	/**
	 * @Fields userId : TODO(用户id)
	 */
	private String userId;

	/**
	 * @Fields userName : TODO(用户名)
	 */
	private String userName;

	/**
	 * @Fields realName : TODO(用户姓名)
	 */
	private String realName;

	/**
	 * @Fields userInfoUpdateTime : TODO(用户更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String userInfoUpdateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserInfoUpdateTime() {
		return userInfoUpdateTime;
	}

	public void setUserInfoUpdateTime(String userInfoUpdateTime) {
		this.userInfoUpdateTime = userInfoUpdateTime;
	}

}
