/******************************************************************
 *
 *    Package:     com.company.platform.base.dao.loginAssist
 *
 *    Filename:    LoginAssistMapper.java
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
package com.company.platform.base.dao.loginAssist;

import java.util.Map;

/** 
* @ClassName: LoginAssistMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月11日 下午1:48:58 
*  
*/
public interface LoginAssistMapper {

    /** 
    * @Title: addLoginRecord 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param info    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    void addLoginRecord(Map<String,Object> info);
}
