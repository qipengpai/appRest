/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityPoster.java
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
package com.company.platform.security.model;

import java.io.Serializable;

/**
 * @ClassName: SecurityPoster
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2018年1月8日 下午1:08:35
 * 
 */
@SuppressWarnings("serial")
public class SecurityPoster implements Serializable {

	/** 
	* @Fields id : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private String id;
	/** 
	* @Fields posterCode : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private String posterCode;
	/** 
	* @Fields posterName : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private String posterName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosterCode() {
		return posterCode;
	}
	public void setPosterCode(String posterCode) {
		this.posterCode = posterCode;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	
}
