package com.company.platform.openRestApi.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * 
* @ClassName: SmsCodeCheckRequest 
* @Description: TODO(校验手机验证码请求) 
* @author zhengjn 
* @date 2017年10月23日 上午10:50:06 
*
 */
@SuppressWarnings("serial")
public class SmsCodeCheckRequest extends BaseHttpParamsAppReq {

	/**
     * @Fields code : TODO(验证码)
     */
    @NotEmpty(message = "短信验证码不能为空")
    private String smscode;
    /**
     * @Fields mobile : TODO(手机号)
     */
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$", message = "手机格式错误")
    private String mobile;
    
    
    
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
    
    
}
