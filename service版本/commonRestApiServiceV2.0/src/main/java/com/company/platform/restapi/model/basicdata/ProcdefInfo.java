package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ProcdefInfo
 * @Description: TODO(产品流程信息)
 * @author liang
 * @date 2018年1月25日 下午1:52:55
 * 
 */
@SuppressWarnings("all")
public class ProcdefInfo extends BaseModel{

	/**
	 * @Fields procdefKey : TODO(流程定义key)
	 */
	private String procdefKey;

	/**
	 * @Fields productId : TODO(产品id)
	 */
	private String productId;

	/**
	 * @Fields procdefInfoUpdateTime : TODO(产品流程信息更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String procdefInfoUpdateTime;

	public String getProcdefKey() {
		return procdefKey;
	}

	public void setProcdefKey(String procdefKey) {
		this.procdefKey = procdefKey;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProcdefInfoUpdateTime() {
		return procdefInfoUpdateTime;
	}

	public void setProcdefInfoUpdateTime(String procdefInfoUpdateTime) {
		this.procdefInfoUpdateTime = procdefInfoUpdateTime;
	}

}
