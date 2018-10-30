/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.service.imp
 *
 *    Filename:    CommonCodeServiceImp.java
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
package com.company.platform.openRestApi.service.custom.imp;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.util.RandomUtil;
import com.company.platform.openRestApi.service.custom.ICommonCodeService;

/**
 * @ClassName: CommonCodeServiceImp
 * @Description: TODO(用于检验相同sesion内验证码是否合法)
 * @author zhengjn
 * @date 2017年10月20日 上午9:57:16
 */
@Service
public class CommonCodeServiceImp implements ICommonCodeService {

	/*
	 * (非 Javadoc) <p>Title: checkCode</p> <p>Description: </p>
	 * @param session
	 * @param code
	 * @return
	 * @see com.company.platform.openRestApi.service.ICommonCodeService#checkCode(javax.servlet.http.HttpSession,
	 * java.lang.String)
	 */
	@Override
	public String checkCode(final HttpSession session, String code, final String key, long time) {

		// TODO Auto-generated method stub
		String cko = (String) session.getAttribute(key); // 验证码对象
		Date now = new Date();
		Long codeTime = Long.valueOf(session.getAttribute(key + "codeTime") + "");
		if ((now.getTime() - codeTime) / 1000 / 60 > 2) {
			// 验证码有效时长为2分钟
			// "验证码失效"；
			return ResponseConstants.FIELD_CODEINVALID_OUTTIME_ERROR.getRetCode();
		}
		if (StringUtils.isEmpty(cko) || !code.equals(cko)) {
			// "验证码失效"；
			return ResponseConstants.FIELD_CODEINVALID_ERROR.getRetCode();
		}

		return ResponseConstants.FIELD_CODECHECK_SUCCESS.getRetCode();
	}

	@Override
	public String getCode(final HttpSession session, final String key, long time) {
		// TODO Auto-generated method stub
		if (session.getAttribute(key) != null) {
			session.removeAttribute(key);
		}
		String code = RandomUtil.getRandomMix(4);
		session.setAttribute(key, code);
		session.setAttribute(key + "codeTime", new Date().getTime());
		Timer timer = new Timer();
		TimerTask task = new TimerTask() { // 创建一个新的计时器任务,两个钟后清除code

			@Override
			public void run() {
				session.removeAttribute(key);
			}
		};
		timer.schedule(task, time);
		return code;
	}

	/* (非 Javadoc) 
	* <p>Title: getCode</p> 
	* <p>Description: </p> 
	* @param session
	* @param key
	* @param keyMessage
	* @param time
	* @return 
	* @see com.company.platform.openRestApi.service.ICommonCodeService#getCode(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, long) 
	*/
	@Override
	public String getCode(final HttpSession session, final String key, String keyMessage, long time) {
		// TODO Auto-generated method stub
		if (session.getAttribute(key) != null) {
			session.removeAttribute(key);
		}
		String code = RandomUtil.getRandomMix(4);
		session.setAttribute(key, code);
		session.setAttribute(key + "codeTime", new Date().getTime());
		session.setAttribute(key + "message", keyMessage);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() { // 创建一个新的计时器任务,两分钟后清除code

			@Override
			public void run() {
				session.removeAttribute(key);
			}
		};
		timer.schedule(task, time);
		return code;
	}

	/* (非 Javadoc) 
	* <p>Title: checkSmsCode</p> 
	* <p>Description: </p> 
	* @param session
	* @param code
	* @param key
	* @param mobile
	* @param time
	* @return 
	* @see com.company.platform.openRestApi.service.ICommonCodeService#checkSmsCode(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, long) 
	*/
	@Override
	public String checkSmsCode(HttpSession session, String code, String key, String mobile, long time) {
		// TODO Auto-generated method stub
		String cko = (String) session.getAttribute(key); // 验证码对象
		String ckoMessage = (String) session.getAttribute(key + "message");
		Date now = new Date();
		Long codeTime = Long.valueOf(session.getAttribute(key + "codeTime") + "");
		if ((now.getTime() - codeTime) > time) {
			// 验证码有效时长为2分钟
			return ResponseConstants.FIELD_SMSCODEINVALID_OUTTIME_ERROR.getRetCode();
		}
		if (StringUtils.isEmpty(cko) || !(code.equals(cko) && mobile.equals(ckoMessage))) {
			// "验证码失效"；
			return ResponseConstants.FIELD_SMSCODEINVALID_ERROR.getRetCode();
		}

		return ResponseConstants.FIELD_SMSCODECHECK_SUCCESS.getRetCode();
	}

}
