/******************************************************************
 *
 *    Package:     com.company.platform.base.validated.signTimestampValidated
 *
 *    Filename:    SignTimestampStandardOrNot.java
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

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/** 
* @ClassName: SignTimestampStandardOrNot 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月15日 下午4:00:27 
*  
*/
@Target({ ANNOTATION_TYPE, METHOD, ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SignTimestampStandardOrNotValidatorImpl.class)
public @interface SignTimestampStandardOrNot {
	
	String message() default "验签时间戳不合规";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 定义List，为了让Bean的一个属性上可以添加多套规则
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		SignTimestampStandardOrNot[] value();
	}
}
