/******************************************************************
 *
 *    Package:     com.company.platform.open.controller
 *
 *    Filename:    BannerController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018
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
package com.company.platform.open.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.platform.base.controller.BaseController;
import com.company.platform.base.util.DownLoadFile;
import com.company.platform.base.util.OrderID;

/** 
* @ClassName: BannerController 
* @Description: TODO(获取banner图片) 
* @author zhengjn 
* @date 2018年1月17日 下午2:10:17 
*  
*/
@Controller
@RequestMapping("/open")
public class BannerController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(BannerController.class);

	@Value("${file.root_dir}")
	private String root_dir;

	/**
	 * @throws IOException  
	* @Title: loadBanner 
	* @Description: TODO(加载图片开始) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "loadPicture")
	public void loadBanner(ModelMap model, String picturePath) throws IOException {
		logger.info("下载banner图片开始");
		DownLoadFile.readFile(root_dir + picturePath, response, OrderID.getPK() + ".png");
		logger.info("下载banner图片结束");
	}
}
