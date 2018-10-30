/******************************************************************
 *
 *    Package:     com.company.platform.base.config.httpClient
 *
 *    Filename:    HttpClientConfig.java
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
package com.company.platform.base.config.httpClient;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RestTemplateConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年9月27日 上午8:54:56
 * 
 */
@Configuration
public class RestTemplateConfig {

	// 代理地址
	@Value("${httpclient.config.proxyhost}")
	private String proxyhost;

	// 代理端口
	@Value("${httpclient.config.proxyPort}")
	private String proxyPort;

	// 连接超时或异常重试次数
	@Value("${httpclient.config.retryTime}")
	private int retryTime;

	// 长连接保持时间，单位为s
	@Value("${httpclient.config.keepAliveTime}")
	private long keepAliveTime;

	// 连接池最大连接数
	@Value("${httpclient.config.connMaxTotal}")
	private int connMaxTotal;

	@Value("${httpclient.config.maxPerRoute}")
	private int maxPerRoute;

	// 连接超时时间，单位ms
	@Value("${httpclient.config.connectTimeout}")
	private int connectTimeout;

	// 请求超时时间
	@Value("${httpclient.config.connectRequestTimeout}")
	private int connectRequestTimeout;

	// sock超时时间
	@Value("${httpclient.config.socketTimeout}")
	private int socketTimeout;

	// 连接存活时间，单位s
	@Value("${httpclient.config.timeToLive}")
	private long timeToLive;

	@Bean(name = "poolingHttpClientConnectionManager")
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(connMaxTotal);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
		return poolingHttpClientConnectionManager;
	}

	@Bean(name = "httpClientBuilder")
	public HttpClientBuilder httpClientBuilder() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager());
		return httpClientBuilder;
	}

	@Bean(name = "httpClient")
	public CloseableHttpClient httpClient() {

		return httpClientBuilder().build();

	}

	@Bean(name = "clientHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
		httpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeout);
		httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(connectRequestTimeout);
		return httpComponentsClientHttpRequestFactory;
		
	}
	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory());
		// 添加内容转换器
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}
}
