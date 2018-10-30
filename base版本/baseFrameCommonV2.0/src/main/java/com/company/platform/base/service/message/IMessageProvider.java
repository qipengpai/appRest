/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    SMSTemplateAbstract.java
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
package com.company.platform.base.service.message;

/** 
* @ClassName: IMessageProvider 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月20日 下午4:07:16 
*  
*/
public interface IMessageProvider {

    ISender produce();

    public ISender produce(Class<? extends ISender> clazz)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
