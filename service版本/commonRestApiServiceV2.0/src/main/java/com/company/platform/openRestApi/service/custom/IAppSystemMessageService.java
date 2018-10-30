/******************************************************************
 *
 *    Package:     com.company.platform.restapi.service.customer
 *
 *    Filename:    IAppCustomMessageService.java
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
package com.company.platform.openRestApi.service.custom;

import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;

/** 
* @ClassName: IAppSystemMessageService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月27日 下午2:27:11 
*  
*/
public interface IAppSystemMessageService {

    public BaseHttpParamsPageResp getSystemMessage(BaseHttpParamsPageAppReq baseHttpParamsPageAppReq);
}
