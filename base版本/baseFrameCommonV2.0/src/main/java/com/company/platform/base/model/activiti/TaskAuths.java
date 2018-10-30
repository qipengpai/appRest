package com.company.platform.base.model.activiti;


/**
 * 流程权限设置.
 *
 * @version V1.0
 * @ClassName:  TaskAuths
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @author: ou
 * @date:   2016年8月4日 下午5:11:18
 */
public class TaskAuths {

	/**模块.*/
	private String modole;
	/**权限.*/
	private String permissions;
	
	/**
	 * 获取 模块.
	 *
	 * @return the 模块
	 */
	public String getModole() {
		return modole;
	}
	
	/**
	 * 设置 模块.
	 *
	 * @param modole the new 模块
	 */
	public void setModole(String modole) {
		this.modole = modole;
	}
	
	/**
	 * 获取 权限.
	 *
	 * @return the 权限
	 */
	public String getPermissions() {
		return permissions;
	}
	
	/**
	 * 设置 权限.
	 *
	 * @param permissions the new 权限
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}
