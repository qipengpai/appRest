package com.company.platform.security.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.company.platform.security.model.SecurityGrantedAuthority;

@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		String url, method;
		if ("anonymousUser".equals(authentication.getPrincipal())
				|| matchers("/", request)
                || matchers("/bootstrap/**", request)
                || matchers("/console/**", request)
                || matchers("/css/**", request)
                || matchers("/img/**", request)
                || matchers("/js/**", request)
                || matchers("/security/**", request)
                || matchers("/openApi/**", request)
                || matchers("/openCommonApi/**", request)
                || matchers("/favicon.ico", request)
                || matchers("/open/**", request)
                || matchers("/metrics/**", request)
                || matchers("/heapdump/**", request)
                || matchers("/trace/**", request)
                || matchers("/info/**", request)
                || matchers("/health/**", request)
                || matchers("/env/**", request)
                || matchers("/dump/**", request)
                || matchers("/auditevents/**", request)
                || matchers("/autoconfig/**", request)
                || matchers("/loggers/**", request)
                || matchers("/mappings/**", request)
                || matchers("/configprops/**", request)
                || matchers("/beans/**", request)
                || matchers("/wechat/**", request)
                ){
			return;
		} else {
			if(null == SecurityContextHolder.getContext().getAuthentication()) {
				throw new AccessDeniedException("annoymous User not allowed");
			}
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (ga instanceof SecurityGrantedAuthority) {
					SecurityGrantedAuthority securityGrantedAuthority = (SecurityGrantedAuthority) ga;
					url = securityGrantedAuthority.getPermissionUrl();
					method = securityGrantedAuthority.getMethod();
					if (matchers(url, request)) {
						if (method.equals(request.getMethod()) || "ALL".equals(method)) {
							return;
						}
					}
				}
			}
		}
		throw new AccessDeniedException("No Access Allowed");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	private boolean matchers(String url, HttpServletRequest request) {
		AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
		if (matcher.matches(request)) {
			return true;
		}
		return false;
	}
}
