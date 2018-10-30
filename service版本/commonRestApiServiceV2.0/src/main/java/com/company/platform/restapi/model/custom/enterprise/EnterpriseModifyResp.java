package com.company.platform.restapi.model.custom.enterprise;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.restapi.model.custom.personal.LocationOrContactBase;

/**
 * 
* @ClassName: CustomerModifyResp 
* @Description: TODO(返回贷款申请人信息) 
* @author 王雪 
* @date 2018年3月12日 上午11:16:54 
*
 */
@SuppressWarnings("all")
public class EnterpriseModifyResp extends BaseModel {

	/** 
	* @Fields updateTime : TODO(更新时间) 
	*/
	private String updateTime;
	/**
	 * @Fields locationList : TODO(客户地址基本信息) 
	 */
	private List<LocationOrContactBase> locationList;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<LocationOrContactBase> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<LocationOrContactBase> locationList) {
		this.locationList = locationList;
	}

}
