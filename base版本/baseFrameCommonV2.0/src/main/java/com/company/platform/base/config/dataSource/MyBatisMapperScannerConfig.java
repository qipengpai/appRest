package com.company.platform.base.config.dataSource;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MyBatisMapperScannerConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年9月15日 下午5:06:15
 * 
 */
@Configuration
// 因为这个对象的扫描，需要在MyBatisConfig的后面注入，所以加上下面的注解
@AutoConfigureAfter(MasterDataSourceConfig.class)
public class MyBatisMapperScannerConfig {
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		// 获取之前注入的beanName为sqlSessionFactory的对象
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
		// 指定xml配置文件的路径
		mapperScannerConfigurer.setBasePackage("com.company.platform.**.dao.**");
		return mapperScannerConfigurer;
	}
}
