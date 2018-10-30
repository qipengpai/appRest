/******************************************************************
 *
 *    Package:     com.company.platform.base.validated
 *
 *    Filename:    ListNotHasNullValidatorImpl.java
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

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;
/** 
* @ClassName: ListNotHasNullValidatorImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月14日 下午6:22:12 
*  
*/
@Service
@SuppressWarnings("all")
public class ListNotHasNullValidatorImpl implements ConstraintValidator<ListNotHasNull, List>{

	private int value;
	
	@Override
	public void initialize(ListNotHasNull constraintAnnotation) {
		// TODO Auto-generated method stub
		//传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(List list, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(null == list){
			return false;
		}
		for (Object object : list) {
            if (object == null) {
                //如果List集合中含有Null元素，校验失败
                return false;
            }
        }
        return true;
	}

}
