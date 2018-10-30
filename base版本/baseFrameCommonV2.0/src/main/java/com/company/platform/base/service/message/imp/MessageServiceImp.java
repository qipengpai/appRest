/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message.imp
 *
 *    Filename:    MessageServiceImp.java
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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.platform.base.dao.message.MessageMapper;
import com.company.platform.base.service.message.IMeaageService;

/** 
* @ClassName: MessageServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月9日 上午10:15:12 
*  
*/
@Service
public class MessageServiceImp implements IMeaageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    @Transactional
    public void addPushPersonMessage(Map<String, Object> info) {
        // TODO Auto-generated method stub
        messageMapper.addPushPersonMessage(info);
    }
}
