/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.model.custom
 *
 *    Filename:    AppSystemMessageModel.java
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
package com.company.platform.openRestApi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: AppSystemMessageModel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月27日 下午2:21:52 
*  
*/
@SuppressWarnings("serial")
public class AppSystemMessageModel extends BaseModel {

    /** 
    * @Fields id : TODO(用一句话描述这个变量表示什么) 
    */
    private String id;
    /** 
    * @Fields messageType : TODO(消息类型标识  0:活动消息   1：公告消息) 
    */
    private String messageType;
    /** 
    * @Fields messageTitle : TODO(消息标题) 
    */
    private String messageTitle;
    /** 
    * @Fields eventUrl : TODO(活动url) 
    */
    private String eventUrl;
    /** 
    * @Fields messageContent : TODO(消息内容) 
    */
    private String messageContent;
    /** 
    * @Fields sendDate : TODO(发送时间) 
    */
    private String sendDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}
