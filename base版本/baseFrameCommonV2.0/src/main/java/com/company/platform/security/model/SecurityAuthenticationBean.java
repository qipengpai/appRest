/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityAuthenticationBean.java
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
package com.company.platform.security.model;

/** 
* @ClassName: SecurityAuthenticationBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月4日 上午9:17:24 
*  
*/
public class SecurityAuthenticationBean {

    /** 
    * @Fields username : TODO(用户名) 
    */
    private String username;

    /** 
    * @Fields password : TODO(密码) 
    */
    private String password;

    /** 
     * @Fields username : TODO(终端设备标识  1 安卓   2  IOS) 
     */
    private String appType;

    /** 
    * @Fields password : TODO(移动端设备token) 
    */
    private String token;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
