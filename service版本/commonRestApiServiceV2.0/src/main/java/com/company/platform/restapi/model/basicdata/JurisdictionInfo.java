package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: JurisdictionInfo
 * @Description: TODO(权限信息)
 * @author liang
 * @date 2018年1月25日 上午11:52:30
 * 
 */
@SuppressWarnings("all")
public class JurisdictionInfo extends BaseModel{

	/**
	 * @Fields productId : TODO(产品id)
	 */
	private String productId;

	/**
	 * @Fields authIds : TODO(权限IDS:此信息为json)
	 */
	private String authIds;

	/**
	 * @Fields taskKey : TODO(节点Key)
	 */
	private String taskKey;

	/**
	 * @Fields taskSort : TODO(节点排序)
	 */
	private String taskSort;

	/**
	 * @Fields jurisdictionInfoUpdateTime : TODO(权限信息更新时间，时间格式：yyyy-MM-dd
	 *         hh:mm:ss)
	 */
	private String jurisdictionInfoUpdateTime;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAuthIds() {
		return authIds;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getTaskSort() {
		return taskSort;
	}

	public void setTaskSort(String taskSort) {
		this.taskSort = taskSort;
	}

	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}

	public String getJurisdictionInfoUpdateTime() {
		return jurisdictionInfoUpdateTime;
	}

	public void setJurisdictionInfoUpdateTime(String jurisdictionInfoUpdateTime) {
		this.jurisdictionInfoUpdateTime = jurisdictionInfoUpdateTime;
	}

}
