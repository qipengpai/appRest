/******************************************************************
 *
 *    Package:     com.company.platform.base.util
 *
 *    Filename:    ss.java
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
package com.company.platform.base.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: GzippedInputStreamWrapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月8日 下午2:44:18 
*  
*/
public class GzippedOutputStreamWrapper extends HttpServletResponseWrapper {

    // 日志
    private final static Logger logger = LoggerFactory.getLogger(GzippedOutputStreamWrapper.class);

    ByteArrayOutputStream output;
    FilterServletOutputStream filterOutput;
    HttpServletResponse httpServletResponse;

    public GzippedOutputStreamWrapper(HttpServletResponse response) {
        super(response);
        this.httpServletResponse= response;
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new FilterServletOutputStream(output);
        }
        return filterOutput;
    }

    public byte[] getDataStream() {
        return output.toByteArray();
    }

    public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	/** 
     * @Title: sendMessage 
     * @Description: TODO(gzip压缩响应) 
     * @param @param response
     * @param @param msg
     * @param @throws IOException    设定文件 
     * @return void    返回类型 
     * @throws 
     */
    public static void sendMessage(HttpServletResponse response, String msg) throws IOException {
        GZIPOutputStream gOut = null;
        try {
            gOut = new GZIPOutputStream(response.getOutputStream());
            gOut.write(msg.getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error("gzip压缩失败");
        } finally {
            try {
                if (gOut != null) {
                    gOut.flush();
                    gOut.finish();
                    gOut.close();
                }
            } catch (Exception e) {
                logger.error("gzip压缩失败");
            }
        }
    }
}
