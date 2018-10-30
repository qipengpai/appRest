/******************************************************************
 *
 *    Package:     com.company.platform.restapi.dao.customer
 *
 *    Filename:    AppCustomMessageMapper.java
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
package com.company.platform.openRestApi.dao.custom;

import java.util.List;

import com.company.platform.openRestApi.model.custom.AppSystemMessageModel;

/** 
* @ClassName: AppCustomMessageMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月27日 上午10:19:40 
*  
*/
public interface AppSystemMessageMapper {

    /** 
    * @Title: getCustomMessage 
    * @Description: TODO(获取系统信息) 
    * @param @param userId
    * @param @return    设定文件 
    * @return List<AppCustomMessageModel>    返回类型 
    * @throws 
    */
    public List<AppSystemMessageModel> getSystemMessage();
}
