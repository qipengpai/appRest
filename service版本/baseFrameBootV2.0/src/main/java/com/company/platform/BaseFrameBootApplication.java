package com.company.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: BaseFrameBootApplication
 * @Description: TODO(启动系统基类)
 * @author zhengjn
 * @date 2017年10月21日 下午1:03:59
 * 
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
@ServletComponentScan
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, SessionAutoConfiguration.class,
		SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class })
public class BaseFrameBootApplication {

	/** 
	* @Title: main 
	* @Description: TODO(启动工程的入口方法) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		SpringApplication.run(BaseFrameBootApplication.class, args);
	}

	/** 
	* @Title: health 
	* @Description: TODO(增加监控描述信息) 
	* @param @return    设定文件 
	* @return Health    返回类型 
	* @throws 
	*/
	public Health health() {
		return Health.up().withDetail("Copyright @北京中科博润科技股份有限公司", "").build();
	}
}
