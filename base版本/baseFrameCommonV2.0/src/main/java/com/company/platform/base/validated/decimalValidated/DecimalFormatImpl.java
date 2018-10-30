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
package com.company.platform.base.validated.decimalValidated;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.service.dataDictionary.IDataDictionaryService;
import com.company.platform.base.util.MatchUtil;

/**
 * @ClassName: DecimalFormatImpl
 * @Description: TODO(Decimal数据格式校验实现类)
 * @author wangxue
 * @date 2018年7月27日 上午11:11:59
 * 
 */
@Service
@SuppressWarnings("all")
public class DecimalFormatImpl implements ConstraintValidator<DecimalFormat, String> {

	/**
	 * @Fields format : TODO(数据格式，eg：14,2)
	 */
	private String format;

	/**
	 * @Fields var : TODO(变量名称)
	 */
	private String var;

	@Override
	public void initialize(DecimalFormat constraintAnnotation) {
		format = constraintAnnotation.format();
		var = constraintAnnotation.var();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {// 无需校验
			return true;
		} else {
			if (!"java.lang.String".equals(value.getClass().getName())) {// 字典类型只能是字符串类型
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("被校验字段只能为String类型，校验失败").addConstraintViolation();
				return false;
			}
			String[] length = format.split(",");
			int max = Integer.valueOf(length[0]) - Integer.valueOf(length[1]);
			// 校验浮点数，整数1-(length[0] - length[1])位，小数0-length[1]位（可以用来校验金额）
			String regex = "^\\d{1," + max + "}(\\.\\d{1," + length[1] + "}){0,1}$";
			if ("4,2".equals(format)) {// 利率数据转化
				value = String
						.valueOf(new BigDecimal(value).multiply(new BigDecimal(100)).setScale(value.length() - 4));
			}
			if (MatchUtil.match(value, regex)) {
				return true;
			} else {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(var + "整数部分最大" + max + "位,小数部分最大" + length[1] + "位\n")
						.addConstraintViolation();
				return false;
			}
		}
	}
}
