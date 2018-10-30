/******************************************************************
 *
 *    Package:     com.company.platform.base.config.threadPool
 *
 *    Filename:    TaskExecutorConfig.java
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
package com.company.platform.base.config.threadPool;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @ClassName: TaskExecutorConfig
 * @Description: TODO(多线程配置类)
 * @author zhengjn
 * @date 2017年10月8日 上午9:08:15
 * 
 */
@Configuration
public class TaskExecutorConfig implements AsyncConfigurer {
	
	/** 
	* @Fields corePoolSize : TODO(维护最小线程池内线程数) 
	*/ 
	@Value("${threads.pool.corePoolSize}")
	private int corePoolSize;
	
	/** 
	* @Fields maxPoolSize : TODO(线程池内最大线程数) 
	*/ 
	@Value("${threads.pool.maxPoolSize}")
	private int maxPoolSize;
	
	/** 
	* @Fields queueCapacity : TODO(队列线程数) 
	*/ 
	@Value("${threads.pool.queueCapacity}")
	private int queueCapacity;

	// 日志
	private final Logger logger = LoggerFactory.getLogger(TaskExecutorConfig.class);

	@Bean
	public Executor getAsyncExecutor() {
		// TODO Auto-generated method stub
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(corePoolSize);
		taskExecutor.setMaxPoolSize(maxPoolSize);
		taskExecutor.setQueueCapacity(queueCapacity);
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
				// TODO Auto-generated method stub
				if (logger.isErrorEnabled()) {
					logger.error("多线程异步执行异常：" + arg0.getMessage() + ";执行方法：" + arg1.getName());
				}
			}
		};
	}

}
