/******************************************************************
 *
 *    Package:     com.company.platform.base.service.loginAssist.imp
 *
 *    Filename:    LoginAssistServiceImp.java
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
package com.company.platform.base.service.loginAssist.imp;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.platform.base.dao.loginAssist.LoginAssistMapper;
import com.company.platform.base.service.loginAssist.ILoginAssistService;
import com.company.platform.base.util.IPInfoUtil;
import com.company.platform.base.util.IPInfoUtil.GeographyService;

/**
 * @ClassName: LoginAssistServiceImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月11日 下午1:43:28
 */
@Service
public class LoginAssistServiceImp implements ILoginAssistService {
    
    // 日志
    private final Logger logger = LoggerFactory.getLogger(LoginAssistServiceImp.class);

    @Autowired
    LoginAssistMapper loginAssistMapper;

    @Transactional
    @Async
    public void addLoginRecord(Map<String, Object> info) {
        info.put("loginAddress", IPInfoUtil.getAddressByIP((String)info.get("loginIp"), GeographyService.SinaServiuce));
        if(logger.isInfoEnabled()){
            logger.info("插入用户登陆成功日志");
        }
        loginAssistMapper.addLoginRecord(info);
    }
}
