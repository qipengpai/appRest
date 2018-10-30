/**
 * 
 */
package com.company.platform.restapi.controller;

import javax.annotation.Resource;

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
import com.company.platform.openRestApi.service.custom.ICommonCodeService;

/** 
* @ClassName: RestApiController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月8日 下午6:40:45 
*  
*/
@RestController
@RequestMapping("/appApi")
public class RestApiController extends BaseController {

    @Resource
    ICommonCodeService codeService;

    @RequestMapping(path = "/getCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public BaseHttpParamsResp getCode(@RequestBody @Validated BaseHttpParamsAppReq baseHttpParamsAppReq)
            throws Exception {

        String simpleCaptcha = "simpleCaptcha";
        String code = codeService.getCode(session, simpleCaptcha, 120000);
        BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("获取验证码成功", code);

        baseHttpParamsResp.createSign();// 生成验签
        return baseHttpParamsResp;
    }
}
