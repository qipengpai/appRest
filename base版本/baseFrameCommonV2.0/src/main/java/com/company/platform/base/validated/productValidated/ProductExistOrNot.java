/******************************************************************
 *
 *    Package:     com.company.platform.base.validated.productValidated
 *
 *    Filename:    ProductExistOrNot.java
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
package com.company.platform.base.validated.productValidated;

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
* @ClassName: ProductExistOrNot 
* @Description: TODO(判断产品id  或      code是否存在) 
* @author zhengjn 
* @date 2017年11月20日 下午1:32:02 
*  
*/
@Target({ANNOTATION_TYPE, METHOD, ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ProductExistOrNotValidatorImpl.class)
public @interface ProductExistOrNot {

    /** 
     * @Title: flag 
     * @Description: TODO(0 检验id是否存在，默认值；0；1 校验code是否存在) 
     * @param @return    设定文件 
     * @return int    返回类型 
     * @throws 
     */
    int flag() default 0;

    String message() default "产品唯一标识不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 定义List，为了让Bean的一个属性上可以添加多套规则
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ProductExistOrNot[] value();
    }
}
