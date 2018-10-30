/******************************************************************
 *
 *    Package:     com.company.platform.base.config.security
 *
 *    Filename:    WebSecurityConfig.java
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
package com.company.platform.base.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

import com.company.platform.base.util.MD5Util;
import com.company.platform.security.service.CustomAccessDeniedHandler;
import com.company.platform.security.service.CustomAuthenticationEntryPoint;
import com.company.platform.security.service.CustomAuthenticationFailureHandler;
import com.company.platform.security.service.CustomAuthenticationFilter;
import com.company.platform.security.service.CustomAuthenticationSuccessHandler;
import com.company.platform.security.service.CustomFilterSecurityInterceptor;
import com.company.platform.security.service.CustomUserDetailsService;

/**
 * @ClassName: WebSecurityConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年9月28日 上午10:45:26
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	SessionRegistry sessionRegistry;

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/", "/bootstrap/**", "/console/**", "/css/**", "/img/**", "/js/**", "/security/**",
						"/openApi/**", "/openCommonApi/**", "/favicon.ico", "/open/**", "/metrics/**", "/heapdump/**",
						"/trace/**", "/info/**", "/health/**", "/env/**", "/dump/**", "/auditevents/**",
						"/autoconfig/**", "/loggers/**", "/mappings/**", "/configprops/**", "/beans/**","/wechat/**")
				.permitAll()// 访问：/// /home// 无需登录认证权限
				.anyRequest().authenticated() // 其他所有资源都需要认证，登陆后访问
				.and().formLogin().loginPage("/")// 指定登录页是
				.permitAll().and()
				// 认证不通过后的处理
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())
				.authenticationEntryPoint(customAuthenticationEntryPoint()).and().logout().logoutSuccessUrl("/") // 退出登录后的默认网址是
				.permitAll().invalidateHttpSession(true).clearAuthentication(true).and().httpBasic();

		http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String) rawPassword));
			}
		});
	}

	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());//
		// 登录成功后可使用loginSuccessHandler()存储用户信息，可选。
		filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler());// 登陆失败后任务处理
		// 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
		filter.setFilterProcessesUrl("/security/login");
		filter.setAuthenticationManager(authenticationManagerBean());
		List<SessionAuthenticationStrategy> sessionList = new ArrayList<SessionAuthenticationStrategy>();
		sessionList.add(getConcurrentSessionControlAuthenticationStrategy());
		sessionList.add(getSessionFixationProtectionStrategy());
		sessionList.add(getRegisterSessionAuthenticationStrategy());
		filter.setSessionAuthenticationStrategy(new CompositeSessionAuthenticationStrategy(sessionList));
		return filter;
	}

	@Bean
	public ConcurrentSessionControlAuthenticationStrategy getConcurrentSessionControlAuthenticationStrategy() {
		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				sessionRegistry);
		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
		concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(false);
		return concurrentSessionControlAuthenticationStrategy;
	}

	@Bean
	public SessionFixationProtectionStrategy getSessionFixationProtectionStrategy() {
		SessionFixationProtectionStrategy sessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
		return sessionFixationProtectionStrategy;
	}

	@Bean
	public RegisterSessionAuthenticationStrategy getRegisterSessionAuthenticationStrategy() {
		RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new RegisterSessionAuthenticationStrategy(
				sessionRegistry);
		return registerSessionAuthenticationStrategy;
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint("/");
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public CustomFilterSecurityInterceptor customFilterSecurityInterceptor() {
		return new CustomFilterSecurityInterceptor();
	}

	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
		CustomAuthenticationFailureHandler customAuthenticationFailureHandler = new CustomAuthenticationFailureHandler();
		return customAuthenticationFailureHandler;
	}

	@Bean
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
		return customAuthenticationSuccessHandler;
	}
}
