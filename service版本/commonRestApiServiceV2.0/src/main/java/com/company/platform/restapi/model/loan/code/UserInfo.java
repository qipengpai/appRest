package com.company.platform.restapi.model.loan.code;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: UserInfo 
* @Description: TODO(封装流程待办人信息) 
* @author luyuchi
* @date 2018年4月27日 下午5:11:37 
*  
*/
public class UserInfo extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = -688849056075996108L;

	/** 
	* @Fields userId : TODO(用户id) 
	*/
	private String userId;

	/** 
	* @Fields userName : TODO(用户名) 
	*/
	private String userName;

	/** 
	* @Fields realName : TODO(真实姓名) 
	*/
	private String realName;

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

}
