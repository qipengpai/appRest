/******************************************************************
 *
 *    Package:     com.company.platform.demo.controller
 *
 *    Filename:    DemoController.java
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
package com.company.platform.demo.controller;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.util.RandomUtil;


/** 
* @ClassName: DemoController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月19日 上午10:19:58 
*  
*/
@RequestMapping("/demo")
@RestController
public class DemoController extends BaseController {

    @RequestMapping(path = "/none", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public BaseHttpParamsResp getCode(@RequestBody @Validated BaseHttpParamsAppReq baseHttpParamsAppReq)
            throws Exception {
        String simpleCaptcha = "simpleCaptcha";
        if (session.getAttribute("simpleCaptcha") != null) {
            session.removeAttribute("simpleCaptcha");
        }
        String code = RandomUtil.getRandomMix(4);
        BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("获取验证码成功", code);
        baseHttpParamsResp.createSign();// 生成验签
        session.setAttribute(simpleCaptcha, code);
        session.setAttribute("codeTime", new Date().getTime());
        return baseHttpParamsResp;
    }
    
}
