package com.company.platform.restapi.model.loan.onlineTemp;

import com.company.platform.base.model.base.BaseModel;

/**
 * 
* @ClassName: LoanOnlineResp 
* @Description: TODO(返回借贷申请更新时间) 
* @author 王雪 
* @date 2018年3月15日 上午9:39:43 
*
 */
@SuppressWarnings("all")
public class LoanOnlineResp extends BaseModel {

	/** 
	* @Fields code : TODO(借贷申请更新时间) 
	*/
	private String updateTime;

	/** 
	* @Fields busModelId : TODO(业务模型id) 
	*/
	private String busModelId;

	/** 
	* @Fields imgModelId : TODO(影像模型id) 
	*/
	private String imgModelId;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getBusModelId() {
		return busModelId;
	}

	public void setBusModelId(String busModelId) {
		this.busModelId = busModelId;
	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
	}

}
