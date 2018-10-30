/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.model
 *
 *    Filename:    CodeMessage.java
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
package com.company.platform.openRestApi.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: CodeMessage 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月15日 下午1:59:18 
*  
*/
@SuppressWarnings("serial")
public class CodeMessage extends BaseHttpParamsAppReq {

	/** 
	* @Fields code : TODO(验证码) 
	*/
	@NotEmpty(message = "验证码不能为空")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
