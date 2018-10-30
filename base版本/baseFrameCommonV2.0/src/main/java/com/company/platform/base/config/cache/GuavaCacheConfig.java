/******************************************************************
 *
 *    Package:     com.company.platform.base.config.cache
 *
 *    Filename:    sss.java
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
package com.company.platform.base.config.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

/**
 * @ClassName: sss
 * @Description: TODO(Guava缓存配置类)
 * @author zhengjn
 * @date 2017年10月5日 下午7:56:06
 * 
 */
@Configuration
public class GuavaCacheConfig {
	@Bean
	public CacheManager cacheManager() {
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).maximumSize(50000));
		return cacheManager;
	}
}
