/******************************************************************
 *
 *    Package:     com.company.platform.base.aop.requestAccess
 *
 *    Filename:    RequestAccessAspect.java
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
package com.company.platform.base.aop.requestAccess;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.platform.base.service.requestAccess.IRequestAccessService;
import com.company.platform.base.util.DateUtil;
import com.company.platform.security.model.SecurityUser;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @ClassName: RequestAccessAspect
 * @Description: TODO(增加请求访问记录AOP)
 * @author zhengjn
 * @date 2017年9月26日 上午9:25:08
 * 
 */
@Component
@Aspect
public class RequestAccessAspect {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(RequestAccessAspect.class);

	/** 
	* @Fields start : TODO(执行开始时间) 
	*/
	private long start;

	/** 
	* @Fields end : TODO(执行结束时间) 
	*/
	private long end;

	@Autowired
	private IRequestAccessService requestAccessService;

	public RequestAccessAspect() {
		if (logger.isInfoEnabled()) {
			logger.info("实例RequestAccessAspect");
		}
	}

	@Pointcut("@annotation(com.company.platform.base.aop.requestAccess.RequestAccessAnnotation)")
	public void controllerAspect() {
	}

	/** 
	* @Title: before 
	* @Description: TODO(事前监听) 
	* @param @param jp
	* @param @param requestAccessAnnotation    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Before(value = "controllerAspect()&&@annotation(requestAccessAnnotation)")
	public void before(JoinPoint jp, RequestAccessAnnotation requestAccessAnnotation) {
		start = System.currentTimeMillis();
	}

	/** 
	* @Title: after 
	* @Description: TODO(事后监听) 
	* @param @param jp
	* @param @param requestAccessAnnotation    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@After(value = "controllerAspect()&&@annotation(requestAccessAnnotation)")
	public void after(JoinPoint jp, RequestAccessAnnotation requestAccessAnnotation) {
		end = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		String operatingSystem = userAgent.getOperatingSystem().getName();
		String appVersion = "";
		if (request.getHeader("appVersion") != null) {
			appVersion = request.getHeader("appVersion").toString();
		}
		String uuid = UUID.randomUUID().toString();// ID主键
		String modelName = requestAccessAnnotation.modelName();// 模块名称，注解中获取
		String modelType = requestAccessAnnotation.modelType().name();
		String desc = requestAccessAnnotation.desc();// 模块描述，注解中获取
		String requestUrl = request.getRequestURI();// uri
		String requestType = operatingSystem;
		String className = jp.getTarget().getClass().toString();// 获取目标类名
		String signature = jp.getSignature().toString();
		String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));// 方法名
		//SecurityUser
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAccount = "";// 操作人账号
		String userRealName = "";// 操作人账号
		if ("java.lang.String".equals(user.getClass().getName())) {
			if (!StringUtils.isEmpty((String) user)) {
				userAccount = (String) user;
				userRealName = "游客";
			}
		} else if (user != null) {
			userAccount = ((SecurityUser) user).getUsername();
			userRealName = ((SecurityUser) user).getRealName();
		}
		String operTime = DateUtil.getCurrentTime();
		long exeTime = (end - start);// 执行时间ms
		String requestIp = request.getRemoteAddr();// 获取请求IP地址
		String sessionId = request.getSession().getId();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", uuid);
		paraMap.put("modelName", modelName);
		paraMap.put("modelType", modelType);
		paraMap.put("logDesc", desc);
		paraMap.put("requestUrl", requestUrl);
		paraMap.put("requestType", requestType + "&" + appVersion);
		paraMap.put("className", className);
		paraMap.put("methodName", methodName);
		paraMap.put("userName", userAccount);
		paraMap.put("userRealName", userRealName);
		paraMap.put("operTime", operTime);
		paraMap.put("exeTime", exeTime);
		paraMap.put("requestIp", requestIp);
		paraMap.put("sessionId", sessionId);
		requestAccessService.addRequestAccessInfo(paraMap);
	}
}
