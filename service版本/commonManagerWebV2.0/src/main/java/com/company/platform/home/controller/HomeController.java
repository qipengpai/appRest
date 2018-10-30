/******************************************************************
 *
 *    Package:     com.company.platform.demo.controller
 *
 *    Filename:    Demo1Controller.java
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
package com.company.platform.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @ClassName: Demo1Controller 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月2日 下午5:29:34 
*  
*/
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping(path = "/index")
	public String index(ModelMap map) {
		return "include/index";
	}
}
