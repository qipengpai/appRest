/******************************************************************
 *
 *    Package:     com.company.platform.security.service
 *
 *    Filename:    CustomAccessDeniedHandler.java
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.util.GzippedOutputStreamWrapper;

/**
 * @ClassName: CustomAccessDeniedHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月2日 下午3:54:32
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    /*
     * (非 Javadoc) <p>Title: handle</p> <p>Description: </p>
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax. servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        if (logger.isInfoEnabled()) {
            logger.info("自定义权限异常响应报文");
        }
        response.setCharacterEncoding("UTF-8");
        BaseHttpParamsResp baseHttpParamsResp = null;
        if (accessDeniedException.getMessage().equalsIgnoreCase("annoymous User not allowed")) {
            baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.SESSION_TIMEOUT.getRetCode(),
                    ResponseConstants.SESSION_TIMEOUT.getRetMsg());
        } else if (accessDeniedException.getMessage().equalsIgnoreCase("No Access Allowed")) {
            baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.NO_ACCESS_ALLOWED.getRetCode(),
                    ResponseConstants.NO_ACCESS_ALLOWED.getRetMsg());
        }
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
