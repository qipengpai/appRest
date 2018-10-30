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
package com.company.platform.base.validated.decimalValidated;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/** 
* @ClassName: DecimalFormat 
* @Description: TODO(Decimal数据格式校验) 
* @author wangxue 
* @date 2018年7月27日 上午11:10:48 
*  
*/
@Target({ ANNOTATION_TYPE, METHOD, FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DecimalFormatImpl.class)
public @interface DecimalFormat {

	String format() default "";

	String var() default "";

	String message() default "数据格式错误";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
