/******************************************************************
 *
 *    Package:     com.company.platform.security.service
 *
 *    Filename:    CustomAuthenticationFilter.java
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
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.platform.security.model.SecurityAuthenticationBean;

/**
 * @ClassName: CustomAuthenticationFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月4日 上午9:13:06
 * 
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (logger.isInfoEnabled()) {
            logger.info("自定义过滤器，用于接收json报文");
        }
        if (null == request.getContentType()) {
            return super.attemptAuthentication(request, response);
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            // use jackson to deserialize json
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                SecurityAuthenticationBean authenticationBean = mapper.readValue(is, SecurityAuthenticationBean.class);
                authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.getUsername(),
                        authenticationBean.getPassword());
                request.setAttribute("username", authenticationBean.getUsername());
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                setDetails(request, authRequest);
            }
            return this.getAuthenticationManager().authenticate(authRequest);
        }

        // transmit it to UsernamePasswordAuthenticationFilter
        else {
            return super.attemptAuthentication(request, response);
        }
    }
}
