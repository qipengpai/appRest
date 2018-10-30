package com.company.platform.security.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.loginAssist.ILoginAssistService;
import com.company.platform.base.util.DateUtil;
import com.company.platform.base.util.GzippedOutputStreamWrapper;
import com.company.platform.security.model.SecurityUser;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	@Autowired
	ILoginAssistService loginAssistServiceImp;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (logger.isInfoEnabled()) {
			logger.info("成功登陆日志");
		}
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {// 获取登陆账号非空
			Map<String, Object> info = new HashMap<String, Object>();
			info.put("id", UUID.randomUUID().toString());
			info.put("userId", user.getId());
			info.put("enterpriseId", "");
			info.put("loginTime", DateUtil.getCurrentTime());
			info.put("loginIp", request.getRemoteAddr());
			info.put("logoutTime", DateUtil.getCurrentTime());
			loginAssistServiceImp.addLoginRecord(info);
		}
		response.setCharacterEncoding("UTF-8");
		BaseHttpParamsResp baseHttpParamsResp = new BaseHttpParamsResp(ResponseConstants.REQUEST_SUCCESS.getRetCode(),
				ResponseConstants.AUTHENTICATION_SUCCESS.getRetCode(),
				ResponseConstants.AUTHENTICATION_SUCCESS.getRetMsg(), request.getSession().getId());
		if (baseHttpParamsResp != null) {
			baseHttpParamsResp.createSign();
			String requestUrl = request.getRequestURI();// 获取请求路径
			if (requestUrl.contains(GLOBALCONFIG.GZIP_URI1) || requestUrl.contains(GLOBALCONFIG.GZIP_URI2)
					|| requestUrl.contains(GLOBALCONFIG.GZIP_URI3)) {// 若指定路径，进行gzip压缩响应
				GzippedOutputStreamWrapper.sendMessage(response, JSONObject.toJSON(baseHttpParamsResp).toString());
			} else {
				String contentType = "application/json;charset=UTF-8";
				response.setContentType(contentType);
				PrintWriter out = response.getWriter();
				out.print(JSONObject.toJSON(baseHttpParamsResp).toString());
				out.flush();
				out.close();
			}
		}
	}
}
