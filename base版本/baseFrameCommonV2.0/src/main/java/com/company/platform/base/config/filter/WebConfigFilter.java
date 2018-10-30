/******************************************************************
 *
 *    Package:     com.company.platform.base.config.filter
 *
 *    Filename:    WebConfigFilter.java
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
package com.company.platform.base.config.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.filter.GzipBodyCompressFilter;
import com.company.platform.base.filter.GzipBodyDecompressFilter;
import com.company.platform.base.filter.SimpleCORSFilter;
import com.company.platform.base.filter.WeiXinFilter;

/**
 * @ClassName: WebConfigFilter
 * @Description: TODO(过滤器配置类)
 * @author zhengjn
 * @date 2017年10月4日 下午12:26:06
 * 
 */
@Configuration
public class WebConfigFilter {
	/** 
	* @Title: simpleCORSFilter 
	* @Description: TODO(跨域过滤器配置) 
	* @param @return    设定文件 
	* @return FilterRegistrationBean    返回类型 
	* @throws 
	*/
	@Bean
	public FilterRegistrationBean simpleCORSFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		SimpleCORSFilter simpleCORSFilter = new SimpleCORSFilter();
		registrationBean.setFilter(simpleCORSFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/security/*");
		urlPatterns.add("/open/*");
		urlPatterns.add("/openApi/*");
		urlPatterns.add("/wechat/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	/** 
	* @Title: gzipBodyCompressFilter 
	* @Description: TODO(gzip压缩配置类) 
	* @param @return    设定文件 
	* @return FilterRegistrationBean    返回类型 
	* @throws 
	*/
	@Bean
	public FilterRegistrationBean gzipBodyCompressFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		GzipBodyCompressFilter gzipBodyCompressFilter = new GzipBodyCompressFilter();
		registrationBean.setFilter(gzipBodyCompressFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add(GLOBALCONFIG.GZIP_URI_FILTER1);
		urlPatterns.add(GLOBALCONFIG.GZIP_URI_FILTER2);
		urlPatterns.add(GLOBALCONFIG.GZIP_URI_FILTER3);
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	/** 
	* @Title: gzipBodyDecompressFilter 
	* @Description: TODO(gzip解压缩) 
	* @param @return    设定文件 
	* @return FilterRegistrationBean    返回类型 
	* @throws 
	*/
	@Bean
	public FilterRegistrationBean gzipBodyDecompressFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		GzipBodyDecompressFilter gzipBodyDecompressFilter = new GzipBodyDecompressFilter();
		registrationBean.setFilter(gzipBodyDecompressFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add(GLOBALCONFIG.GZIP_URI_FILTER1);
		urlPatterns.add(GLOBALCONFIG.GZIP_URI_FILTER2);
		urlPatterns.add(GLOBALCONFIG.GZIP_URI_FILTER3);
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean weixinFilter() {
		System.out.println("------微信小程序登陆拦截-------");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(myWeiXinFilter());
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/wechat/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	
	 @Bean(name = "myWeiXinFilter")
     public WeiXinFilter myWeiXinFilter() {
       return new WeiXinFilter();
}
}
