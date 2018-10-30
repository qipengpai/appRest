/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.controller.custom
 *
 *    Filename:    SystemMessageController.java
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
package com.company.platform.openRestApi.controller.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.openRestApi.service.custom.IAppSystemMessageService;

/** 
* @ClassName: SystemMessageController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月27日 下午2:11:28 
*  
*/
@RequestMapping("/openCommonApi")
@RestController
public class SystemMessageController extends BaseController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(SystemMessageController.class);

    @Autowired
    private IAppSystemMessageService appSystemMessageServiceImpl;

    /** 
    * @Title: getCustomMessage 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param baseHttpParamsPageAppReq
    * @param @return
    * @param @throws Exception    设定文件 
    * @return BaseHttpParamsPageResp    返回类型 
    * @throws 
    */
    @RequestMapping(path = "/getSystemMessage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName = "获取系统消息", modelType = RequestAccessConstants.SEARCH, desc = "系统消息")
    public BaseHttpParamsPageResp getCustomMessage(
            @RequestBody @Validated BaseHttpParamsPageAppReq baseHttpParamsPageAppReq) throws Exception {

        BaseHttpParamsPageResp baseHttpParamsPageResp = appSystemMessageServiceImpl
                .getSystemMessage(baseHttpParamsPageAppReq);
        baseHttpParamsPageResp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
        baseHttpParamsPageResp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
        baseHttpParamsPageResp.setResponseMessage("获取系统消息成功");
        baseHttpParamsPageResp.createSign();// 生成验签
        if (logger.isInfoEnabled()) {
            logger.info("获取系统消息成功");
        }

        return baseHttpParamsPageResp;
    }
}
