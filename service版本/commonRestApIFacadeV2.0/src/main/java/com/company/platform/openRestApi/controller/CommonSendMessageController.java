/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller
 *
 *    Filename:    CommonSendMessageController.java
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
package com.company.platform.openRestApi.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestLimit.RequestLimitByTimeAnnotation;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.service.message.MessageAdapter;
import com.company.platform.base.service.message.PushMessageAdapter;
import com.company.platform.openRestApi.model.message.PushMessageAllModel;
import com.company.platform.openRestApi.model.message.PushMessageSingleModel;
import com.company.platform.openRestApi.model.message.SendMessageAllModel;
import com.company.platform.openRestApi.model.message.SendMessageModel;

/** 
* @ClassName: CommonSendMessageController 
* @Description: TODO(app 消息发送通用，消息推送  群发 短信通知) 
* @author zhengjn 
* @date 2017年11月6日 上午10:44:18 
*  
*/
@RestController
@RequestMapping("/openApi")
public class CommonSendMessageController extends BaseController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(CommonSendMessageController.class);

    @Resource
    private PushMessageAdapter pushMessageAdapter;

    @Resource
    private MessageAdapter messageAdapter;

    /** 
    * @Title: pushMeaageAll 
    * @Description: TODO(app群发) 
    * @param @param baseHttpParamsAppReq
    * @param @return
    * @param @throws Exception    设定文件 
    * @return BaseHttpParamsResp    返回类型 
    * @throws 
    */
    @RequestLimitByTimeAnnotation(count = 5, time = 60000)
    @RequestMapping(path = "/pushMeaageAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void pushMeaageAll(@RequestBody @Validated PushMessageAllModel pushMessageAllModel) throws Exception {
        logger.info("app群发开始，消息内容:" + pushMessageAllModel.getMessageDetail());
        HashMap<String, Object> messageBody = new HashMap<String, Object>();
        messageBody.put("title", pushMessageAllModel.getTitle());
        messageBody.put("messageDetail", pushMessageAllModel.getMessageDetail());
        pushMessageAdapter.pushAllMessage(messageBody);
        logger.info("app群发结束");
    }

    /** 
    * @Title: pushMeaageSingle 
    * @Description: TODO(app个推) 
    * @param @param pushMessageSingleModel
    * @param @return
    * @param @throws Exception    设定文件 
    * @return BaseHttpParamsResp    返回类型 
    * @throws 
    */
    @RequestLimitByTimeAnnotation(count = 5, time = 60000)
    @RequestMapping(path = "/pushMeaageSingle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void pushMeaageSingle(@RequestBody @Validated PushMessageSingleModel pushMessageSingleModel)
            throws Exception {
        logger.info("app个推开始,接收人账号" + pushMessageSingleModel.getUserName() + "消息内容:"
                + pushMessageSingleModel.getMessageDetail());
        HashMap<String, Object> messageBody = new HashMap<String, Object>();
        messageBody.put("userName", pushMessageSingleModel.getUserName());
        messageBody.put("messageDetail", pushMessageSingleModel.getMessageDetail());
        messageBody.put("title", pushMessageSingleModel.getTitle());
        pushMessageAdapter.pushPersonMessage(messageBody);
        logger.info("app个推结束");
    }

    /** 
    * @Title: sendMeaageByBuinessType 
    * @Description: TODO(按照业务类型发送短信通知) 
    * @param @param pushMessageSingleModel
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    @RequestLimitByTimeAnnotation(count = 5, time = 60000)
    @RequestMapping(path = "/sendMeaageByBuinessType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void sendMeaageByBuinessType(@RequestBody @Validated SendMessageModel sendMessageModel) throws Exception {
        logger.info("短信通知开始");
        HashMap<String, Object> para = sendMessageModel.getPara();
        switch (sendMessageModel.getBusinessType()) {
        case "10"://验证码短信
            messageAdapter.sendVcode(para);
            break;
        case "11"://提交审核短信提醒
            messageAdapter.sendAuditMessage(para);
            break;
        case "12"://审核通过短信通知
            messageAdapter.sendAuditPassMessage(para);
            break;
        case "13"://审核拒绝短信通知
            messageAdapter.sendAuditRefuseMessage(para);
            break;
        case "14"://放款短信通知
            messageAdapter.sendLoanMessage(para);
            break;
        case "15"://还款提醒
            messageAdapter.sendRepaymentWarnMessage(para);
            break;
        case "16"://已还款通知
            messageAdapter.sendRepaymentMessage(para);
            break;
        case "17"://逾期催收
            messageAdapter.sendOverdueMessage(para);
            break;
        default:
            break;
        }
        logger.info("短信通知结束");
    }

    /** 
    * @Title: sendAllMeaageByBuinessType 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param sendMessageAllModel
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    @RequestLimitByTimeAnnotation(count = 5, time = 60000)
    @RequestMapping(path = "/sendAllMeaageByBuinessType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void sendAllMeaageByBuinessType(@RequestBody @Validated SendMessageAllModel sendMessageAllModel)
            throws Exception {
        logger.info("短信、app个推通知开始");
        HashMap<String, Object> para = sendMessageAllModel.getPara();
        HashMap<String, Object> messageBody = new HashMap<String, Object>();
        messageBody.put("userName", sendMessageAllModel.getUserName());
        messageBody.put("messageDetail", sendMessageAllModel.getMessageDetail());
        switch (sendMessageAllModel.getBusinessType()) {
        case "10"://验证码短信
            messageAdapter.sendVcode(para);
            break;
        case "11"://提交审核短信提醒
            messageAdapter.sendAuditMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        case "12"://审核通过短信通知
            messageAdapter.sendAuditPassMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        case "13"://审核拒绝短信通知
            messageAdapter.sendAuditRefuseMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        case "14"://放款短信通知
            messageAdapter.sendLoanMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        case "15"://还款提醒
            messageAdapter.sendRepaymentWarnMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        case "16"://已还款通知
            messageAdapter.sendRepaymentMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        case "17"://逾期催收
            messageAdapter.sendOverdueMessage(para);
            pushMessageAdapter.pushPersonMessage(messageBody);
            break;
        default:
            break;
        }
        logger.info("短信、app个推通知结束");
    }

}
