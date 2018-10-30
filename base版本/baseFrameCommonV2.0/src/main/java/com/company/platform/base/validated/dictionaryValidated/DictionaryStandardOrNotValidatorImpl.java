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
package com.company.platform.base.validated.dictionaryValidated;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.service.dataDictionary.IDataDictionaryService;

/** 
* @ClassName: DictionaryStandardOrNotValidatorImpl 
* @Description: TODO(字典校验实现类) 
* @author 王雪 
* @date 2018年2月8日 下午4:47:59 
*  
*/
@Service
@SuppressWarnings("all")
public class DictionaryStandardOrNotValidatorImpl implements ConstraintValidator<DictionaryStandardOrNot, String> {

	@Autowired
	private IDataDictionaryService dataDictionaryService;
	/** 
	* @Fields type : TODO(字典类型值) 
	*/
	private String type;

	@Override
	public void initialize(DictionaryStandardOrNot constraintAnnotation) {
		// TODO Auto-generated method stub
		type = constraintAnnotation.dictionaryType();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(value)) {// 无需校验
			return true;
		} else {
			if (!"java.lang.String".equals(value.getClass().getName())) {// 字典类型只能是字符串类型
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("被校验字段只能为String类型，校验失败").addConstraintViolation();
				return false;
			}
			Map<String, Map<String, Object>> map = dataDictionaryService.getDataMap();
			if (!map.containsKey(type)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("字典类型错误，校验失败").addConstraintViolation();
				return false;
			} else if (!((Map<String, String>) map.get(type).get("item")).containsKey(value)) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("字典数值错误，校验失败").addConstraintViolation();
				return false;
			} else {
				return true;
			}
		}

	}

}
