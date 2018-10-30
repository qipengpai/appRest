/**
 * 
 */
package com.company.platform.base.config.dataSource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/** 
* @ClassName: MasterDataSourceConfig 
* @Description: TODO(数据库配置，duird连接池，mysql数据库，PageHelper分页插件，) 
* @author zhengjn 
* @date 2017年9月15日 下午1:55:04 
*  
*/
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

	//日志 
	private final Logger logger = LoggerFactory.getLogger(MasterDataSourceConfig.class);
	// 精确到 master 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.company.platform.**.dao.**";
	
    @Value("${master.datasource.url}")
	private String url;

	@Value("${master.datasource.driverClassName}")
	private String driverClassName;

	@Value("${master.datasource.initialSize}")
	private int initialSize;

	@Value("${master.datasource.minIdle}")
	private int minIdle;

	@Value("${master.datasource.maxActive}")
	private int maxActive;

	@Value("${master.datasource.maxWait}")
	private long maxWait;

	@Value("${master.datasource.username}")
	private String username;

	@Value("${master.datasource.password}")
	private String password;

	@Value("${master.datasource.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${master.datasource.filters}")
	private String filters;

	@Value("${master.datasource.connectionProperties}")
	private String connectionProperties;

	@Value("${master.datasource.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;

	@Value("${master.datasource.validationQuery}")
	private String validationQuery;

	@Value("${master.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${master.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${master.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${master.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${master.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Bean(name = "masterDataSource")
	@Primary
	public DataSource masterDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		try {
			dataSource.setUrl(url);
			dataSource.setDriverClassName(driverClassName);
			dataSource.setInitialSize(initialSize);
			dataSource.setMinIdle(minIdle);
			dataSource.setMaxActive(maxActive);
			dataSource.setMaxWait(maxWait);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			dataSource.setFilters(filters);
			dataSource.setConnectionProperties(connectionProperties);
			dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			dataSource.setValidationQuery(validationQuery);
			dataSource.setTestWhileIdle(testWhileIdle);
			dataSource.setTestOnBorrow(testOnBorrow);
			dataSource.setTestOnReturn(testOnReturn);
			dataSource.setPoolPreparedStatements(poolPreparedStatements);
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return dataSource;
	}

	@Bean(name = "masterTransactionManager")
	@Primary
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(masterDataSource());
	}

	@Bean(name = "masterSqlSessionFactory")
	@Primary
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(masterDataSource);
		sessionFactory.setTypeAliasesPackage(MasterDataSourceConfig.PACKAGE);
		return sessionFactory.getObject();
	}
}
