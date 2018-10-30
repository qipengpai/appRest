/******************************************************************
 *
 *    Package:     com.company.platform.base.validated.productValidated
 *
 *    Filename:    ProductExistOrNotValidatorImpl.java
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

import java.util.HashMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.platform.base.service.product.IProductExistByIdOrCodeService;

/** 
* @ClassName: ProductExistOrNotValidatorImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月20日 下午1:33:49 
*  
*/
public class ProductExistOrNotValidatorImpl implements ConstraintValidator<ProductExistOrNot, String> {

    @Autowired
    private IProductExistByIdOrCodeService productExistByIdOrCodeServiceImp;

    /**
     * @Fields flag : TODO(0 校验id，默认值0； 1 校验code)
     */
    private int flag;

    @Override
    public void initialize(ProductExistOrNot arg0) {
        // TODO Auto-generated method stub
        flag = arg0.flag();
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        // TODO Auto-generated method stub
        if (!"java.lang.String".equals(arg0.getClass().getName())) {// 账号只能用在字符串类型
            arg1.disableDefaultConstraintViolation();
            arg1.buildConstraintViolationWithTemplate("被校验字段只能为String类型，校验失败").addConstraintViolation();
            return false;
        }
        if (StringUtils.isEmpty(arg0)) {
            arg1.disableDefaultConstraintViolation();
            arg1.buildConstraintViolationWithTemplate("被校验字段不能为空，校验失败").addConstraintViolation();
            return false;
        }

        if (flag == 0) {
            HashMap<String, String> mapinfo = productExistByIdOrCodeServiceImp.findByProductId(arg0);
            if (mapinfo == null) {
                arg1.disableDefaultConstraintViolation();
                arg1.buildConstraintViolationWithTemplate("产品不存在").addConstraintViolation();
                return false;
            } else {
                return true;
            }
        } else if (flag == 1) {
            HashMap<String, String> mapinfo = productExistByIdOrCodeServiceImp.findByProductCode(arg0);
            if (mapinfo == null) {
                arg1.disableDefaultConstraintViolation();
                arg1.buildConstraintViolationWithTemplate("产品不存在").addConstraintViolation();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
