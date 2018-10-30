/******************************************************************
 *
 *    Package:     com.company.platform.base.validated.signTimestampValidated
 *
 *    Filename:    SignTimestampStandardOrNotValidatorImpl.java
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
package com.company.platform.base.validated.signTimestampValidated;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SignTimestampStandardOrNotValidatorImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月15日 下午4:01:07
 * 
 */
public class SignTimestampStandardOrNotValidatorImpl
		implements ConstraintValidator<SignTimestampStandardOrNot, String> {

	@Override
	public void initialize(SignTimestampStandardOrNot constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (!"java.lang.String".equals(value.getClass().getName())) {// 账号只能用在字符串类型
			return false;
		}
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		if (!this.isNumeric(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("时间戳格式异常").addPropertyNode("timestamp")
					.addConstraintViolation();
			return false;
		}
		if (((System.currentTimeMillis() - Long.valueOf(value)) / 1000 / 60) > 5) {// 超过五分钟的时间戳无效
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("时间戳格式异常").addPropertyNode("timestamp")
					.addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @Title: isNumeric @Description: TODO(判断时间戳是否未存数字) @param @param
	 * str @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
