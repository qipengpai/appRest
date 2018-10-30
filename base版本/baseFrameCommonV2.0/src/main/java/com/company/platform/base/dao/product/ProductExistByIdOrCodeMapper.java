/******************************************************************
 *
 *    Package:     com.company.platform.base.dao.product
 *
 *    Filename:    ProductExistByIdOrCodeMapper.java
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
package com.company.platform.base.dao.product;

import java.util.HashMap;

/** 
* @ClassName: ProductExistByIdOrCodeMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月20日 下午1:46:55 
*  
*/
public interface ProductExistByIdOrCodeMapper {

    /** 
     * @Title: findByProductId 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param prdoctId
     * @param @return    设定文件 
     * @return HashMap<String,String>    返回类型 
     * @throws 
     */
    HashMap<String, String> findByProductId(String prdoctId);

    /** 
    * @Title: findByProductCode 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param prdoctCode
    * @param @return    设定文件 
    * @return HashMap<String,String>    返回类型 
    * @throws 
    */
    HashMap<String, String> findByProductCode(String prdoctCode);
}
