/******************************************************************
 *
 *    Package:     com.company.platform.open.controller
 *
 *    Filename:    MonitorOpenContoller.java
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
package com.company.platform.open.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.util.DateUtil;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: MonitorOpenContoller 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月21日 下午8:03:42 
*  
*/
@RestController
@RequestMapping("/open")
public class MonitorOpenController extends BaseController {

	@Autowired
	private SessionRegistry sessionRegistry;

	/** 
	* @Title: getCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param baseHttpParamsAppReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/queryOnlineUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public BaseHttpParamsResp getCode(@RequestBody @Validated BaseHttpParamsAppReq baseHttpParamsAppReq)
			throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<Object> loginUserList = sessionRegistry.getAllPrincipals();// 获取登陆的SecurityUser对象
		for (Object u : loginUserList) {
			List<SessionInformation> temp = new ArrayList<SessionInformation>();
			temp = sessionRegistry.getAllSessions(u, true);
			for (SessionInformation sessionInformation : temp) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("expired", sessionInformation.isExpired());
				map.put("lastRequest", DateUtil.dateTimeFormat(sessionInformation.getLastRequest()));
				map.put("sessionId", sessionInformation.getSessionId());
				map.put("userName", ((SecurityUser) u).getUsername());
				data.add(map);
			}
		}
		BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("获取在线用户信息成功", data);
		baseHttpParamsResp.createSign();// 生成验签
		return baseHttpParamsResp;
	}
}
