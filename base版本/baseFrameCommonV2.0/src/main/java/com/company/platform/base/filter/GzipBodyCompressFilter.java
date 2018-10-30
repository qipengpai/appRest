/******************************************************************
 *
 *    Package:     com.company.platform.base.filter
 *
 *    Filename:    GzipBodyDecompressFilter.java
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
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.platform.base.util.GzippedOutputStreamWrapper;

/**
 * @ClassName: GzipBodyDecompressFilter
 * @Description: TODO(gzip压缩过滤器)
 * @author zhengjn
 * @date 2017年10月8日 下午2:23:54
 * 
 */
public class GzipBodyCompressFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        boolean requestTypeSupported = RequestMethod.POST.name().equals(req.getMethod());
        if (requestTypeSupported) {
            GzippedOutputStreamWrapper responseWrapper = new GzippedOutputStreamWrapper((HttpServletResponse) response);
            chain.doFilter(request, responseWrapper);
            String responseContent = new String(responseWrapper.getDataStream());
            if (!StringUtils.isEmpty(responseContent)) {
                GzippedOutputStreamWrapper.sendMessage(res, responseContent);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
