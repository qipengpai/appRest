/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.model.message
 *
 *    Filename:    PushMessageAllModel.java
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

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: PushMessageAllModel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月6日 上午11:07:54 
*  
*/
@SuppressWarnings("serial")
public class PushMessageAllModel extends BaseHttpParamsAppReq {

    /** 
     * @Fields title : TODO(用一句话描述这个变量表示什么) 
     */
    @NotEmpty(message = "消息标题不能为空")
    private String title;

    /** 
    * @Fields messageDetail : TODO(群发消息内容) 
    */
    @NotEmpty(message = "消息内容不能为空")
    private String messageDetail;

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
