/******************************************************************
 *
 *    Package:     com.company.platform.open.controller
 *
 *    Filename:    HelpOpenController.java
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
package com.company.platform.open.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.platform.base.controller.BaseController;

/** 
* @ClassName: LoadOpenController 
* @Description: TODO(Android二维码识别页面) 
* @author wangxue
* @date 2018年6月7日 上午9:44:15 
*  
*/
@Controller
@RequestMapping("/open")
public class LoadOpenController extends BaseController {

	/** 
	* @Title: loanLoadIndex 
	* @Description: TODO(Android二维码识别页面) 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/appDownLoad")
	public String loanLoadIndex(ModelMap map) {
		return "loan/loanLoad";
	}
}
