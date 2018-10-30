/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message.imp
 *
 *    Filename:    XingePushSender.java
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

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.loginAssist.LoginAssistMapper;
import com.company.platform.base.service.message.IMeaageService;
import com.company.platform.base.service.message.ISender;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

/** 
* @ClassName: XingePushSender 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年11月3日 下午4:07:06 
*  
*/
@Service
public class XingePushSender implements ISender {

	@Autowired
	LoginAssistMapper loginAssistMapper;

	@Resource
	IMeaageService messageServiceImp;

	// 日志
	private final Logger logger = LoggerFactory.getLogger(XingePushSender.class);

	private static final String ACCESS_ID_IOS = "2200270258";
	//private static final String ACCESS_KEY_IOS = "IU58374BKMRD";
	private static final String SECRET_KEY_IOS = "11097147be13e1d6654718091418b387";

	private static final String ACCESS_ID_ANDROID = "2100273323";
	// private static final String ACCESS_KEY_ANDROID = "A62NX1J6SW2J";
	private static final String SECRET_KEY_ANDROID = "6d58f5f88cfa5006e4802c07c7658ca7";

	/*
	 * (非 Javadoc) <p>Title: send</p> <p>Description: </p>
	 * @param para
	 * @see com.company.platform.base.service.message.ISender#send(java.util.Map)
	 */
	@Override
	@Async
	public void send(Map<String, Object> para) {
		// TODO Auto-generated method stub
	}

	/*
	 * (非 Javadoc) <p>Title: sendAll</p> <p>Description: </p>
	 * @param para
	 * @see com.company.platform.base.service.message.ISender#sendAll(java.util.Map)
	 */
	@Override
	@Async
	public void sendAll(Map<String, Object> para) {
		String messageDetail = (String) para.get("messageDetail");
		String title = para.get("title") == null ? "中科博润" : (String) para.get("title");
		if (StringUtils.isEmpty(messageDetail)) {
			logger.error("信鸽消息推送，推送内容为空，拒绝推送");
			return;
		}
		// TODO Auto-generated method stub

		XingeApp appPush = new XingeApp(Long.parseLong(ACCESS_ID_ANDROID), SECRET_KEY_ANDROID);
		Message mess = new Message();
		mess.setTitle(title);
		mess.setContent(messageDetail);
		mess.setType(Message.TYPE_NOTIFICATION);
		mess.setMultiPkg(1);
		mess.setStyle(new Style(0, 1, 1, 0, 0));
		JSONObject ret = new JSONObject();
		ret = appPush.pushAllDevice(0, mess);
		logger.info("信鸽广播推送结果报文(安卓)：" + ret.toString());

		XingeApp appPushIOS = new XingeApp(Long.parseLong(ACCESS_ID_IOS), SECRET_KEY_IOS);
		MessageIOS messIOS = new MessageIOS();
		messIOS.setExpireTime(86400);
		messIOS.setAlert(messageDetail);
		messIOS.setBadge(1);
		messIOS.setSound("beep.wav");
		TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
		messIOS.addAcceptTime(acceptTime);
		JSONObject retIOS = new JSONObject();
		retIOS = appPushIOS.pushAllDevice(0, messIOS, XingeApp.IOSENV_DEV);
		logger.info("信鸽广播推送结果报文(IOS)：" + retIOS.toString());
	}
}
