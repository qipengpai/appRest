package com.company.platform.openRestApi.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: SmsCodeRequest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月20日 上午10:39:52
 */
@SuppressWarnings("serial")
public class SmsCodeRequest extends BaseHttpParamsAppReq {

    /**
     * @Fields code : TODO(验证码)
     */
    @NotEmpty(message = "验证码不能为空")
    private String code;
    /**
     * @Fields mobile : TODO(手机号)
     */
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$", message = "手机格式错误")
    private String mobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
