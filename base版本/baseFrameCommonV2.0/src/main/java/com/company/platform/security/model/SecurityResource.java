/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityResource.java
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
package com.company.platform.security.model;

import java.io.Serializable;

/**
 * @ClassName: SecurityResource
 * @Description: TODO(系统资源)
 * @author zhengjn
 * @date 2017年9月28日 下午3:15:53
 * 
 */
@SuppressWarnings("serial")
public class SecurityResource implements Serializable{
	private String id;// '主键'
	
	private String resourceCode; // '资源编码'
	
	private String resourceName; // '资源名称'
	
	private String url;// '资源链接'；
	
	private String pid; // '上级节点id（自连接使用）
	
	private String method;//rest 方法，GET POST All等
	
	private String categoryCode;//'资源分类  0 服务  1 菜单  '
	
	private String rSort;// '排序'
	
	private String rLevel;//'层级 '
	
	private String isValid;//'是否可用 0 可用 1 不可用'
	
	private String resourceIcon;//'菜单图标，存储css'
	
	private String  isLeaf;//'是否为叶子节点 0 是 1 否',

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getrSort() {
		return rSort;
	}

	public String getrLevel() {
		return rLevel;
	}

	public String getIsValid() {
		return isValid;
	}

	public String getResourceIcon() {
		return resourceIcon;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setrSort(String rSort) {
		this.rSort = rSort;
	}

	public void setrLevel(String rLevel) {
		this.rLevel = rLevel;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public void setResourceIcon(String resourceIcon) {
		this.resourceIcon = resourceIcon;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
}
