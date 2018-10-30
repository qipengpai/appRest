/******************************************************************
 *
 *    Package:     com.company.platform.base.filter
 *
 *    Filename:    GzipBodyDecompressFilter.java
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
package com.company.platform.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;

import com.company.platform.base.util.GzippedInputStreamWrapper;

/**
 * @ClassName: GzipBodyDecompressFilter
 * @Description: TODO(gizp解压过滤器)
 * @author zhengjn
 * @date 2017年10月8日 下午4:16:09
 * 
 */
public class GzipBodyDecompressFilter implements Filter {

	/*
	 * (非 Javadoc) <p>Title: init</p> <p>Description: </p>
	 * 
	 * @param filterConfig
	 * 
	 * @throws ServletException
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/*
	 * (非 Javadoc) <p>Title: doFilter</p> <p>Description: </p>
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param chain
	 * 
	 * @throws IOException
	 * 
	 * @throws ServletException
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		boolean requestTypeSupported = RequestMethod.POST.name().equals(
				request.getMethod());
		String uri = request.getRequestURI();
		if (requestTypeSupported && uri.indexOf("uploadLoanMaterial") == -1) {
			chain.doFilter(new GzippedInputStreamWrapper(
					(HttpServletRequest) request), response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: destroy</p> <p>Description: </p>
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
