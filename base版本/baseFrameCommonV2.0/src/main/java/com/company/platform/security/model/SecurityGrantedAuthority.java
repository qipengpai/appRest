/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityGrantedAuthority.java
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

import org.springframework.security.core.GrantedAuthority;

/** 
* @ClassName: SecurityGrantedAuthority 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月1日 下午4:56:23 
*  
*/
@SuppressWarnings("serial")
public class SecurityGrantedAuthority implements GrantedAuthority {
	
	private String url;
    private String method;

    public String getPermissionUrl() {
        return url;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.url = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public SecurityGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method;
    }

}
