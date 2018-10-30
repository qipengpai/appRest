/******************************************************************
 *
 *    Package:     com.company.platform.open.controller
 *
 *    Filename:    CacheOpenApiController.java
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

import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;

/** 
* @ClassName: CacheOpenApiController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月22日 上午8:58:31 
*  
*/
@RestController
@RequestMapping("/open")
public class CacheOpenApiController extends BaseController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(CacheOpenApiController.class);

    @Autowired
    private IDataDictionaryService dataDictionaryServiceImp;

    @RequestMapping(path = "/delCacheDataConfig")
    @ResponseBody
    public BaseHttpParamsResp getCode()
            throws Exception {
        logger.info("清除缓存开始");
        dataDictionaryServiceImp.delDataMap();
        dataDictionaryServiceImp.delGlobal_Config();
        logger.info("清除缓存结束");
        BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("清空缓存成功", "");
        baseHttpParamsResp.createSign();// 生成验签
        return baseHttpParamsResp;
    }
}
