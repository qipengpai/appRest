package com.company.platform.restapi.model.custom.personal;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: LocationOrContactBase 
* @Description: TODO(客户地址/联系方式类别信息) 
* @author 王雪 
* @date 2018年3月12日 上午11:55:42 
*  
*/
@SuppressWarnings("all")
public class LocationOrContactBase extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields etype : TODO(地址/联系方式类型) 
	*/
	private String etype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}
}
