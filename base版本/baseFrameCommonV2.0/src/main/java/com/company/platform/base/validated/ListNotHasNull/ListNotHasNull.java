/******************************************************************
 *
 *    Package:     com.company.platform.base.validated
 *
 *    Filename:    ListNotHasNull.java
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
package com.company.platform.base.validated.ListNotHasNull;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName: ListNotHasNull
 * @Description: TODO(注解List内对象不能为空)
 * @author zhengjn
 * @date 2017年10月14日 下午6:19:24
 * 
 */
@Target({ ANNOTATION_TYPE, METHOD, ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ListNotHasNullValidatorImpl.class)
public @interface ListNotHasNull {
	/**
	 * 添加value属性，可以作为校验时的条件,若不需要，可去掉此处定义
	 */
	int value() default 0;

	String message() default "List集合中不能含有null元素";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 定义List，为了让Bean的一个属性上可以添加多套规则
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		ListNotHasNull[] value();
	}
}
