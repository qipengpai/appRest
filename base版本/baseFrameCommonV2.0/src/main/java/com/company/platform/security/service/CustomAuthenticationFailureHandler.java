package com.company.platform.security.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.loginAssist.ILoginAssistService;
import com.company.platform.base.util.DataDictionaryUtil;
import com.company.platform.base.util.GzippedOutputStreamWrapper;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	ILoginAssistService loginAssistServiceImp;

	@Autowired
	ISecurityService securityServiceImpl;

	@Autowired
	DataDictionaryUtil dataDictionaryUtil;

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		BaseHttpParamsResp baseHttpParamsResp = null;
		String userName;// 登陆系统账号
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
				|| request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			userName = (String) request.getAttribute("username");
		} else {
			userName = request.getParameter("username");
		}

		if (!StringUtils.isEmpty(userName)) {// 正常接收登陆账号
			HashMap<String, String> sysUserInfoMap = securityServiceImpl.findByUserName(userName);// 登陆账号查询用户信息
			String isAccountNonLocked;
			if (sysUserInfoMap != null) {// 通过账号查询到用户信息，说明账号输入正确
				isAccountNonLocked = String.valueOf(sysUserInfoMap.get("isValid"));// 是可用，1可用
																					// 0
																					// 不可用
				if (!StringUtils.isEmpty(isAccountNonLocked) && "0".equals(isAccountNonLocked)) {// 账号被锁
					baseHttpParamsResp = BaseHttpParamsResp.requestError(
							ResponseConstants.AUTHENTICATION_ACCOUNT_LOCKED_FAIL.getRetCode(),
							ResponseConstants.AUTHENTICATION_ACCOUNT_LOCKED_FAIL.getRetMsg());
					this.showInfo(baseHttpParamsResp, response, request);
				} else {
					baseHttpParamsResp = BaseHttpParamsResp.requestError(
							ResponseConstants.AUTHENTICATION_PASSWORD_FAIL.getRetCode(),
							ResponseConstants.AUTHENTICATION_PASSWORD_FAIL.getRetMsg());
					this.showInfo(baseHttpParamsResp, response, request);
				}
			} else {// 通过账号查询到用户信息，未查到，说明账号输入错误
				baseHttpParamsResp = BaseHttpParamsResp.requestError(
						ResponseConstants.AUTHENTICATION_NOTFIND_ACCOUNT_FAIL.getRetCode(),
						ResponseConstants.AUTHENTICATION_NOTFIND_ACCOUNT_FAIL.getRetMsg());
				this.showInfo(baseHttpParamsResp, response, request);
			}

		} else {// 通用授权异常报文
			baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.AUTHENTICATION_FAIL.getRetCode(),
					ResponseConstants.AUTHENTICATION_FAIL.getRetMsg());
			this.showInfo(baseHttpParamsResp, response, request);
		}
	}

	/**
	 * @Title: showInfo @Description: TODO(响应报文) @param @param
	 *         baseHttpParamsResp @param @param response @param @throws
	 *         IOException 设定文件 @return void 返回类型 @throws
	 */
	private void showInfo(BaseHttpParamsResp baseHttpParamsResp, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (baseHttpParamsResp != null) {
			baseHttpParamsResp.createSign();
			String requestUrl = request.getRequestURI();// 获取请求路径
			if (requestUrl.contains(GLOBALCONFIG.GZIP_URI1) || requestUrl.contains(GLOBALCONFIG.GZIP_URI2)
					|| requestUrl.contains(GLOBALCONFIG.GZIP_URI3)) {// 若指定路径，进行gzip压缩响应
				GzippedOutputStreamWrapper.sendMessage(response, JSONObject.toJSON(baseHttpParamsResp).toString());
			} else {
				String contentType = "application/json;charset=UTF-8";
				response.setContentType(contentType);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(JSONObject.toJSON(baseHttpParamsResp).toString());
				out.flush();
				out.close();
			}
		}
	}
}
