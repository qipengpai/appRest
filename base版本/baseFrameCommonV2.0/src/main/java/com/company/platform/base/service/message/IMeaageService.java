/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    IMeaageService.java
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
package com.company.platform.base.service.message;

import java.util.Map;

/** 
* @ClassName: IMeaageService 
* @Description: TODO(消息持久化service层) 
* @author zhengjn 
* @date 2017年11月9日 上午10:13:21 
*  
*/
public interface IMeaageService {

    /** 
     * @Title: addPersonMessage 
     * @Description: TODO(持久化个推消息) 
     * @param @param info    设定文件 
     * @return void    返回类型 
     * @throws 
     */
    void addPushPersonMessage(Map<String, Object> info);
}
