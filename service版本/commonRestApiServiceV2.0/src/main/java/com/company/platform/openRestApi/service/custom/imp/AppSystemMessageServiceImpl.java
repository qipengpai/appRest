/******************************************************************
 *
 *    Package:     com.company.platform.restapi.service.customer.imp
 *
 *    Filename:    AppCustomMessageServiceImpl.java
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
package com.company.platform.openRestApi.service.custom.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.openRestApi.dao.custom.AppSystemMessageMapper;
import com.company.platform.openRestApi.service.custom.IAppSystemMessageService;
import com.github.pagehelper.PageHelper;

/** 
* @ClassName: AppCustomMessageServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月27日 上午10:35:06 
*  
*/
@SuppressWarnings("all")
@Service
public class AppSystemMessageServiceImpl implements IAppSystemMessageService {

    @Autowired
    private AppSystemMessageMapper appSystemMessageMapper;

    /*
     * (非 Javadoc) <p>Title: getCustomMessage</p> <p>Description: </p>
     * @param baseHttpParamsPageAppReq
     * @return
     * @see
     * com.company.platform.restapi.service.customer.IAppCustomMessageService#getCustomMessage(com.company.platform.base
     * .model.base.BaseHttpParamsPageAppReq)
     */
    @Override
    public BaseHttpParamsPageResp getSystemMessage(BaseHttpParamsPageAppReq baseHttpParamsPageAppReq) {
        // TODO Auto-generated method stub
        PageHelper.startPage(baseHttpParamsPageAppReq.getPageNum(), baseHttpParamsPageAppReq.getPageSize());
        BaseHttpParamsPageResp baseHttpParamsPageResp = new BaseHttpParamsPageResp(
                appSystemMessageMapper.getSystemMessage(), true);
        return baseHttpParamsPageResp;
    }

}
