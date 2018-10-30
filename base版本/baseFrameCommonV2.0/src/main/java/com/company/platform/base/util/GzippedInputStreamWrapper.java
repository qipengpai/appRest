/******************************************************************
 *
 *    Package:     com.company.platform.base.util
 *
 *    Filename:    GzippedInputStreamWrapper.java
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

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: GzippedInputStreamWrapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月8日 下午3:56:14
 * 
 */
public class GzippedInputStreamWrapper extends HttpServletRequestWrapper {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(GzippedInputStreamWrapper.class);

	private HttpServletRequest request;

	public GzippedInputStreamWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		ServletInputStream stream = request.getInputStream();
		// 如果对内容进行了压缩，则解压
		try {
			final GZIPInputStream gzipInputStream = new GZIPInputStream(stream);

			ServletInputStream newStream = new ServletInputStream() {
				@Override
				public int read() throws IOException {
					return gzipInputStream.read();
				}

				@Override
				public boolean isFinished() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isReady() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setReadListener(ReadListener listener) {
					// TODO Auto-generated method stub

				}
			};
			return newStream;
		} catch (Exception e) {
			logger.error("ungzip content fail.", e);
		}
		return stream;
	}
}
