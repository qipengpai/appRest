package com.company.platform.openRestApi.model.custom;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.util.cipher.Decrypt;

/**
 * 
* @ClassName: UserResetPwdMessage 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月27日 下午1:41:42 
*
 */
@SuppressWarnings("serial")
public class UserResetPwdMessage  extends BaseHttpParamsAppReq{
	/**
     * @Fields mobile : TODO(手机号)
     */
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$", message = "手机格式错误")
    private String mobile;
    
    @Decrypt
    @NotEmpty(message = "密码不能为空")
    private String password;
    
    @Decrypt
    @NotEmpty(message = "确认密码不能为空")
    private String confirmPassword;

	/** 
	* @return mobile 
	*/
	public String getMobile() {
		return mobile;
	}

	/** 
	* @return password 
	*/
	public String getPassword() {
		return password;
	}

	/** 
	* @return confirmPassword 
	*/
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/** 
	* @param mobile 要设置的 mobile 
	*/
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 
	* @param password 要设置的 password 
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	* @param confirmPassword 要设置的 confirmPassword 
	*/
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
