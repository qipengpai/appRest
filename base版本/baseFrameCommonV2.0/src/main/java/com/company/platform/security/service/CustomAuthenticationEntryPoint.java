/******************************************************************
 *
 *    Package:     com.company.platform.security.service
 *
 *    Filename:    CustomAuthenticationEntryPoint.java
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
package com.company.platform.security.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.util.GzippedOutputStreamWrapper;

/**
 * @ClassName: CustomAuthenticationEntryPoint
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月2日 下午4:00:54
 * 
 */
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public CustomAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        BaseHttpParamsResp baseHttpParamsResp = null;
        baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.SESSION_TIMEOUT.getRetCode(),
                ResponseConstants.SESSION_TIMEOUT.getRetMsg());
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
