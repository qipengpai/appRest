/******************************************************************
 *
 *    Package:     com.company.platform.restapi.model.setup
 *
 *    Filename:    AppBannerInfoReq.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018
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
package com.company.platform.restapi.model.setup;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: AppBannerInfoReq 
* @Description: TODO(获取banner请求体) 
* @author zhengjn 
* @date 2018年1月17日 下午2:04:58 
*  
*/
@SuppressWarnings("serial")
public class AppBannerInfoReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields appName : TODO(app名称) 
	*/ 
	@NotEmpty(message = "app名称不能为空")
	private String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
