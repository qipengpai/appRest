/******************************************************************
 *
 *    Package:     com.company.platform.base.util.cipher
 *
 *    Filename:    Decrypt.java
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
package com.company.platform.base.util.cipher;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @ClassName: Decrypt 
* @Description: TODO(用于标识解密) 
* @author zhengjn 
* @date 2017年10月23日 下午6:03:46 
*  
*/
@Target(ElementType.FIELD) //字段注解  
@Retention(RetentionPolicy.RUNTIME) //在运行期保留注解信息  
@Documented     //在生成javac时显示该注解的信息  
@Inherited      //标明Ancrypt注解可以被使用它的子类继承
public @interface Decrypt {
	
	String type() default "DES"; 
	/**
	 * 定义List，为了让Bean的一个属性上可以添加多套规则
	 */
	@Target({ FIELD })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		Decrypt[] value();
	}
}
