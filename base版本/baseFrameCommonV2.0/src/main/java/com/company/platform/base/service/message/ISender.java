/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    ISender.java
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
* @ClassName: ISender 
* @Description: TODO(基类) 
* @author zhengjn 
* @date 2017年10月20日 下午4:10:32 
*  
*/
public interface ISender {

    /** 
    * @Title: send 
    * @Description: TODO(发送消息) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws 
    */
    void send(Map<String, Object> para);
    
    /** 
    * @Title: sendAll 
    * @Description: TODO(群发) 
    * @param @param para    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    void sendAll(Map<String, Object> para);
}
