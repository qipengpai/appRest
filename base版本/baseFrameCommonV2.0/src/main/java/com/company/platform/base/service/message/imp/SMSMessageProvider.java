/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    SMSMessageProvider.java
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
package com.company.platform.base.service.message.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.service.message.IMessageProvider;
import com.company.platform.base.service.message.ISender;

/** 
* @ClassName: SMSMessageProvider 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月20日 下午4:14:25 
*  
*/
@Service
public class SMSMessageProvider implements IMessageProvider {

    @Autowired
    ISender weimiSMSSender;

    /*
     * (非 Javadoc) <p>Title: produce</p> <p>Description: </p>
     * @return
     * @see com.company.platform.base.service.message.IMessageProvider#produce()
     */
    @Override
    public ISender produce() {
        // TODO Auto-generated method stub
        return weimiSMSSender;
    }

    /** 
    * @Title: produce 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param clazz
    * @param @return
    * @param @throws InstantiationException
    * @param @throws IllegalAccessException
    * @param @throws ClassNotFoundException    设定文件 
    * @return ISender    返回类型 
    * @throws 
    */
    public ISender produce(Class<? extends ISender> clazz)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ISender) Class.forName(clazz.getName()).newInstance();
    }

}
