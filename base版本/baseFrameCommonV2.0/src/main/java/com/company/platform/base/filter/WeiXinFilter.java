package com.company.platform.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.company.platform.base.http.WeiXinHttpServletRequestWrapper;
import com.company.platform.security.model.SecurityUser;
import com.company.platform.security.service.WeixinCustomUserDetailsService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

/**
 * 微信url拦截 登陆处理
* @ClassName: WeiXinFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 下午3:40:59 
*
 */
public class WeiXinFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WeixinCustomUserDetailsService weixinCustomUserDetailsService;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		/*WeiXinHttpServletRequestWrapper requestWrapper = new WeiXinHttpServletRequestWrapper((HttpServletRequest) request);
		this.logger.info("baseAction鍒濆鍖栨祴璇曞弬鏁癆dmin鐢ㄦ埛:userId=25e53d4b-4377-40e4-a119-480ef67eb09d orgId=1 openId=123456789");
		requestWrapper.setParameter("userId", "b6b9dd62-6e34-4196-8e16-1a35d0673e56");
		requestWrapper.setParameter("orgId", "1");
		requestWrapper.setParameter("openId", "123456789");
		SecurityUser user = weixinCustomUserDetailsService.loadUserByOpenId("123456789");
		weixinCustomUserDetailsService.setUserDetails("123456789", user);
		chain.doFilter(requestWrapper, response);*/
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//判断是否为ajax请求 占不处理
		boolean isAjaxRequest = false;
	    if(!StringUtils.isBlank(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest"))isAjaxRequest = true;
		//过滤登陆请求
	    StringBuffer url = req.getRequestURL();
	    this.logger.info("请求路径:"+url);
		if(url.indexOf("/login")!=-1||url.indexOf("/getWechatOpenId")!=-1||url.indexOf("/userAuthorization")!=-1||url.indexOf("/loading")!=-1 ||url.indexOf("/getStatusByOpenId")!=-1||url.indexOf("/showImage")!=-1){
			chain.doFilter(request, res);
			return;
		}
		String openId = req.getParameter("openId");
		try {
		if(openId!=null){
			//已授权
			SecurityUser user= (SecurityUser) weixinCustomUserDetailsService.getUserDetails(openId);
			//session过期重新授权
			if(user==null){
				user=(SecurityUser)weixinCustomUserDetailsService.loadUserByOpenId(openId);
				if(user==null){
					 this.logger.error("未绑定系统用户！~");
					 res.sendRedirect("/wechat/login");
				}else{
					weixinCustomUserDetailsService.setUserDetails(openId, user);
					WeiXinHttpServletRequestWrapper requestWrapper = new WeiXinHttpServletRequestWrapper((HttpServletRequest) request);
					requestWrapper.setParameter("userId", user.getId());
					requestWrapper.setParameter("orgId", user.getSecurityOrg().get(0).getId());
					requestWrapper.setParameter("openId",user.getOpenId() );
					//测试处理
					chain.doFilter(requestWrapper, response);
				}
			}else{
				WeiXinHttpServletRequestWrapper requestWrapper = new WeiXinHttpServletRequestWrapper((HttpServletRequest) request);
				requestWrapper.setParameter("userId", user.getId());
				requestWrapper.setParameter("orgId", user.getSecurityOrg().get(0).getId());
				requestWrapper.setParameter("openId",user.getOpenId() );
				//测试处理
				chain.doFilter(requestWrapper, response);
			}
		}else{
			 this.logger.error("非法请求！~");
			 res.sendRedirect("/wechat/login");
		}
		} catch (Exception e) {
			 this.logger.error("系统异常！~");
			e.printStackTrace();
			 res.sendRedirect("/wechat/login");
		}
	}

	@Override
	public void destroy() {
	
	}

}
