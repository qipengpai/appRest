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
* @ClassName: HelpOpenController 
* @Description: TODO(帮助页面集合) 
* @author zhengjn 
* @date 2017年11月23日 上午10:07:09 
*  
*/
@Controller
@RequestMapping("/open")
public class HelpOpenController extends BaseController {

    /** 
     * @Title: registrationProtocolIndex 
     * @Description: TODO(还款帮助页面) 
     * @param @param map
     * @param @return    设定文件 
     * @return String    返回类型 
     * @throws 
     */
    @RequestMapping(path = "/loanHelp")
    public String loanHelpIndex(ModelMap map) {
        return "loan/loanHelp";
    }
}
