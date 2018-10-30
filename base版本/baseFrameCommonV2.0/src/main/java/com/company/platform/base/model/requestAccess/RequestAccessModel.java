/******************************************************************
 *
 *    Package:     com.company.platform.model.requestAccess
 *
 *    Filename:    RequestAccessModel.java
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
package com.company.platform.base.model.requestAccess;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: RequestAccessModel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年9月25日 上午10:00:18 
*  
*/
@SuppressWarnings("serial")
public class RequestAccessModel extends BaseModel {
	//逻辑主键
	String id;
	// 模块名称，注解中获取
    String modelName;
    //类型RequestAccessConstants
    String modelType;
    // 模块描述，注解中获取
    String desc;
    // 请求uri
    String requestUrl;
    //请求类型 安卓 ios 等
    String requestType;
    // 获取目标类名
    String className;
    // 方法名
    String methodName;
    // 操作人账号
    String userAccount ;
    // 操作人真实姓名
    String userRealName;
    //操作时间
    String operTime;
    // 执行时间ms
    long exeTime;
    //ip
    String requestIp;
    //sessionId
    String sessionId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public long getExeTime() {
		return exeTime;
	}
	public void setExeTime(long exeTime) {
		this.exeTime = exeTime;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
