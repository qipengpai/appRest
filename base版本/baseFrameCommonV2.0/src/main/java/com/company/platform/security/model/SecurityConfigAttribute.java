/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    Security.java
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
package com.company.platform.security.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;

/**
 * @ClassName: Security
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月1日 下午5:27:08
 * 
 */
@SuppressWarnings("serial")
public class SecurityConfigAttribute implements ConfigAttribute {

	private final HttpServletRequest httpServletRequest;

	public SecurityConfigAttribute(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	@Override
	public String getAttribute() {
		return null;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}
}
