package com.company.platform.image.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.platform.base.controller.BaseController;

/**
 * @ClassName: MaterialController
 * @Description: TODO(图片预览，下载等操作)
 * @author luyuchi
 * @date 2018年2月1日 上午11:44:04
 * 
 */
@Controller
@RequestMapping("/wechat")
public class MaterialController extends BaseController {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(MaterialController.class);

	@Value("${file.image_dir}")
	private String basePath;

	@Value("${file.root_dir}")
	private String rootPath;

	/**
	 * @Title: showImage @Description: TODO(展示圖片) @param @param
	 * filePath @param @param fileName 设定文件 @return void 返回类型 @throws
	 */
	@RequestMapping(path = "/showImage")
	public void showImage(String filePath, String fileName,HttpServletResponse response) {
		if (StringUtils.isNotBlank(filePath)) {

			String fullPath = rootPath + basePath + filePath;

			// 获取文件的长度
			// long fileLength = new File(fullPath).length();

			FileInputStream fis = null;
			OutputStream os = null;
			try {
				//String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition","attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
				response.setHeader("Connection", "keep-alive");
				fis = new FileInputStream(fullPath);
				os = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[fis.available()];
				while ((count = fis.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(fis != null) fis.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			logger.debug("文件路径未传！");
		}
	}
}
