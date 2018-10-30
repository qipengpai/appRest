/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.model.message
 *
 *    Filename:    SendMessageAllModel.java
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

import java.util.HashMap;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: SendMessageAllModel 
* @Description: TODO(app推送和短信通知) 
* @author zhengjn 
* @date 2017年11月6日 下午1:35:52 
*  
*/
@SuppressWarnings("serial")
public class SendMessageAllModel extends BaseHttpParamsAppReq {

    /** 
     * @Fields BusinessType : TODO(业务类型) 
     */
    @NotEmpty(message = "业务类型不能为空")
    private String BusinessType;

    /** 
    * @Fields userName : TODO(手机号) 
    */
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$", message = "手机格式错误")
    private String userName;

    /** 
    * @Fields messageDetail : TODO(app推送内容) 
    */
    @NotEmpty(message = "手机号码不能为空")
    private String messageDetail;

    /** 
    * @Fields para : TODO(消息变量参数) 
    */
    @NotNull(message = "消息变量参数不能为 空")
    private HashMap<String, Object> para;

    
    public String getBusinessType() {
        return BusinessType;
    }

    
    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    
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

    
    public HashMap<String, Object> getPara() {
        return para;
    }

    
    public void setPara(HashMap<String, Object> para) {
        this.para = para;
    }
    
}
