/******************************************************************
 *
 *    Package:     com.company.platform.base.service.product
 *
 *    Filename:    ProductExistByIdOrCodeServiceImp.java
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
package com.company.platform.base.service.product.imp;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.product.ProductExistByIdOrCodeMapper;
import com.company.platform.base.service.product.IProductExistByIdOrCodeService;

/** 
* @ClassName: ProductExistByIdOrCodeServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月20日 下午1:44:03 
*  
*/
@Service
public class ProductExistByIdOrCodeServiceImp implements IProductExistByIdOrCodeService {

    @Autowired
    private ProductExistByIdOrCodeMapper productExistByIdOrCodeMapper;

    /*
     * (非 Javadoc) <p>Title: findByProductId</p> <p>Description: </p>
     * @param prdoctId
     * @return
     * @see com.company.platform.base.service.product.IProductExistByIdOrCodeService#findByProductId(java.lang.String)
     */
    @Override
    public HashMap<String, String> findByProductId(String prdoctId) {
        // TODO Auto-generated method stub
        return productExistByIdOrCodeMapper.findByProductId(prdoctId);
    }

    /*
     * (非 Javadoc) <p>Title: findByProductCode</p> <p>Description: </p>
     * @param prdoctCode
     * @return
     * @see com.company.platform.base.service.product.IProductExistByIdOrCodeService#findByProductCode(java.lang.String)
     */
    @Override
    public HashMap<String, String> findByProductCode(String prdoctCode) {
        // TODO Auto-generated method stub
        return productExistByIdOrCodeMapper.findByProductCode(prdoctCode);
    }

}
