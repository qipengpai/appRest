/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.model.message
 *
 *    Filename:    PushMessageSingleModel.java
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
package com.company.platform.openRestApi.model.message;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: PushMessageSingleModel 
* @Description: TODO() 
* @author zhengjn 
* @date 2017年11月6日 上午11:17:55 
*  
*/
@SuppressWarnings("serial")
public class PushMessageSingleModel extends BaseHttpParamsAppReq {

    /**
     * @Fields mobile : TODO(手机号)
     */
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$", message = "手机格式错误")
    private String userName;

    /** 
    * @Fields messageDetail : TODO(待发送消息内容) 
    */
    @NotEmpty(message = "消息内容不能为空")
    private String messageDetail;

    /** 
    * @Fields title : TODO(用一句话描述这个变量表示什么) 
    */
    @NotEmpty(message = "消息标题不能为空")
    private String title;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
