package com.company.platform.restapi.model.network;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: ProductUpdateInfoReq 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 李昂
* @date 2018年04月27日 下午21:02:16 
*  
*/
@SuppressWarnings("serial")
public class ProductUpdateInfoReq extends BaseHttpParamsAppReq {

    /** 
    * @Fields updateTime : TODO(产品更新时间) 
    */ 
	@NotEmpty(message = "产品更新时间不能为空")
    private String updateTime;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
