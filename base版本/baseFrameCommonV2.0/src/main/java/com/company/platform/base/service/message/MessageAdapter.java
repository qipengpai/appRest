/******************************************************************
 *
 *    Package:     com.company.platform.base.service.message
 *
 *    Filename:    MessageAdapter.java
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

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.platform.base.service.message.imp.SMSMessageProvider;

/**
 * @ClassName: MessageAdapter
 * @Description: TODO(借款人短信专用)
 * @author zhengjn
 * @date 2017年10月31日 上午8:43:25
 */
@Service
public class MessageAdapter {

	@Resource
	private SMSMessageProvider sMSMessageProvider;

	/** 
	* @Title: sendVcode 
	* @Description: TODO(发送短信验证码 BuinessType 10) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendVcode(Map<String, Object> para) {
		// TODO Auto-generated method stub
		para.put("cid", "nn5kdbycDmjnull");
		sMSMessageProvider.produce().send(para);
	}

	/** 
	* @Title: sendAuditMessage 
	* @Description: TODO(用于发送提交审核短信提醒 BuinessType 11) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendAuditMessage(Map<String, Object> para) {

	}

	/** 
	* @Title: sendAuditPassMessage 
	* @Description: TODO(用于发送审核通过短信通知 BuinessType 12) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendAuditPassMessage(Map<String, Object> para) {

	}

	/** 
	* @Title: sendAuditRefuseMessage 
	* @Description: TODO(用于发送审核拒绝短信通知 BuinessType 13) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendAuditRefuseMessage(Map<String, Object> para) {

	}
	
	/** 
	* @Title: sendLoanMessage 
	* @Description: TODO(用于发送放款短信通知 BuinessType 14) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendLoanMessage(Map<String, Object> para) {

	}
	
	
	/** 
	* @Title: sendRepaymentWarnMessage 
	* @Description: TODO(用于发送还款提醒短信 BuinessType 15) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendRepaymentWarnMessage(Map<String, Object> para) {

	}
	
	/** 
	* @Title: sendRepaymentMessage 
	* @Description: TODO(已还款通知 BuinessType 16) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendRepaymentMessage(Map<String, Object> para) {

	}
	
	/** 
	* @Title: sendRepaymentMessage 
	* @Description: TODO(逾期催收 BuinessType 17) 
	* @param @param para    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendOverdueMessage(Map<String, Object> para) {

	}
	
	/** 
	* @Title: sendMessage 
	* @Description: TODO(定制开发短信平台实现，通过反射实现，需实现ISender接口类) 
	* @param @param clazz
	* @param @param para
	* @param @throws InstantiationException
	* @param @throws IllegalAccessException
	* @param @throws ClassNotFoundException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendMessage(Class<? extends ISender> clazz, Map<String, Object> para)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		sMSMessageProvider.produce(clazz).send(para);
	}
}
