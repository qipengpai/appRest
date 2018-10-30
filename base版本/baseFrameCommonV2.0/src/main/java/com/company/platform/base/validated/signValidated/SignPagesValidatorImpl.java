/******************************************************************
 *
 *    Package:     com.company.platform.base.validated.signValidated
 *
 *    Filename:    SignValidatorImpl.java
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
package com.company.platform.base.validated.signValidated;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.util.SHAHelper;

/**
 * @ClassName: SignValidatorImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月15日 上午10:43:42
 * 
 */
public class SignPagesValidatorImpl implements ConstraintValidator<SignPages, BaseHttpParamsPageAppReq> {

    @Override
    public void initialize(SignPages constraintAnnotation) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(BaseHttpParamsPageAppReq value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        if (null == value) {
            return false;
        }
        if (StringUtils.isEmpty(value.getNonce())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("随机数不能为空").addPropertyNode("nonce").addConstraintViolation();
            return false;
        }
        if (StringUtils.isEmpty(value.getTimestamp())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("时间戳不能为空").addPropertyNode("timestamp")
                    .addConstraintViolation();
            return false;
        }
        if (StringUtils.isEmpty(value.getSign())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("验签不能为空").addPropertyNode("sign").addConstraintViolation();
            return false;
        }
        String timestamp_request = value.getTimestamp();
        String nonce_request = value.getNonce();
        String sign_request = value.getSign();
        value.setSign(null);
        String sign_response = SHAHelper
                .SHA1(GLOBALCONFIG.APPKEY + timestamp_request + nonce_request + JSON.toJSONString(value));
        if (!sign_request.equals(sign_response)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("验签未通过").addPropertyNode("sign").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
