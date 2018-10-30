/******************************************************************
 *
 *    Package:     com.company.platform.base.service.requestLimit.imp
 *
 *    Filename:    RequestLimitByTimeServiceImp.java
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
package com.company.platform.base.service.requestLimit.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.company.platform.base.model.requestLimit.RequestLimitByTimeModel;
import com.company.platform.base.service.requestLimit.IRequestLimitByTimeService;

/**
 * @ClassName: RequestLimitByTimeServiceImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月18日 下午3:33:44
 */
@Service
public class RequestLimitByTimeServiceImp implements IRequestLimitByTimeService {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(RequestLimitByTimeServiceImp.class);
    
    @Override
    @CachePut(value = "requesTimesLimit", key = "#key")
    public RequestLimitByTimeModel putRequesTimesByKey(RequestLimitByTimeModel requestLimitByTimeModel , String key) {
        // TODO Auto-generated method stub
        if(logger.isInfoEnabled()){
            logger.info("增加请求次数缓存");
        }
        return requestLimitByTimeModel;
    }

    @Override
    @Cacheable(value = "requesTimesLimit" , key = "#key")
    public RequestLimitByTimeModel getRequesTimesByKey(String key) {
        // TODO Auto-generated method stub
        if(logger.isInfoEnabled()){
            logger.info("通过key获取请求次数缓存");
        }
        RequestLimitByTimeModel requestLimitByTimeModel = new RequestLimitByTimeModel();
        requestLimitByTimeModel.setKey(key);
        requestLimitByTimeModel.setTimes(0);
        return requestLimitByTimeModel;
    }

    @Override
    @CacheEvict(value = "requesTimesLimit", key = "#key")
    public void delRequesTimesByKey(String key) {
        // TODO Auto-generated method stub
        if(logger.isInfoEnabled()){
            logger.info("通过key清楚请求次数缓存");
        }
    }
}
