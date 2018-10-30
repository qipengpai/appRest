package com.company.platform.restapi.model.setup;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: QuitMobileLogin 
* @Description: TODO(退出app) 
* @author zhengjn 
* @date 2017年11月2日 下午4:24:03 
*  
*/
@SuppressWarnings("serial")
public class QuitMobileLogin extends BaseHttpParamsAppReq {

    /**
    * @Fields sessionId : TODO(sessionId)
    */
    //@NotEmpty(message = "sessionId")
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
