/******************************************************************
 *
 *    Package:     com.company.platform.base.config.activiti
 *
 *    Filename:    ActivitiConfig.java
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
package com.company.platform.base.config.activiti;

import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @ClassName: ActivitiConfig
 * @Description: TODO(工作流引擎配置类)
 * @author zhengjn
 * @date 2018年1月8日 下午4:48:04
 * 
 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration(
			@Qualifier("masterDataSource") DataSource dataSource,
			@Qualifier("masterTransactionManager") PlatformTransactionManager transactionManager,
			SpringAsyncExecutor springAsyncExecutor) throws IOException {
		return this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
	}
}
