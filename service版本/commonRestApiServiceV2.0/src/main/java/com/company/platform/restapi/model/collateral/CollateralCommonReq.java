package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: CollateralCommonReq 
* @Description: TODO(押品基本请求参数) 
* @author 王雪 
* @date 2018年5月22日 下午5:50:25 
*  
*/
@SuppressWarnings("serial")
public class CollateralCommonReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(押品id) 
	*/
	@NotEmpty(message = "押品id不能为空")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
