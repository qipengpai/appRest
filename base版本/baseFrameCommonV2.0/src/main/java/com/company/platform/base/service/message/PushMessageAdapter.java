/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    PushMessageAdapter.java
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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.platform.base.service.message.imp.XingeMessageProvider;

/** 
* @ClassName: PushMessageAdapter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月4日 上午9:34:51 
*  
*/
@Service
public class PushMessageAdapter {

    @Resource
    private XingeMessageProvider xingeMessageProvider;

    /** 
    * @Title: pushPersonVcode 
    * @Description: TODO(app个人消息推送) 
    * @param @param para   userName用户账号 messageDetail推送消息内容 
    * @return void    返回类型 
    * @throws 
    */
    public void pushPersonMessage(Map<String, Object> para) {
        // TODO Auto-generated method stub
        xingeMessageProvider.produce().send(para);
    }

    /** 
    * @Title: pushAllMessage 
    * @Description: TODO(app群推) 
    * @param @param para     messageDetail推送消息内容  
    * @return void    返回类型 
    * @throws 
    */
    public void pushAllMessage(Map<String, Object> para) {
        // TODO Auto-generated method stub
        xingeMessageProvider.produce().sendAll(para);
    }
}
