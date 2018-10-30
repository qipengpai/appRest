/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.loan
 *
 *    Filename:    LoanHandledController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018
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
package com.company.platform.restapi.controller.loan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoReq;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoResp;
import com.company.platform.restapi.service.loan.ILoanHandledService;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: LoanHandledController 
* @Description: TODO(已办相关功能集合) 
* @author zhengjn 
* @date 2018年1月18日 上午9:49:50 
*  
*/
@RestController
@RequestMapping("appApi")
public class LoanHandledController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanHandledController.class);

	@Autowired
	private ILoanHandledService loanHandledService;

	/**  
	* @Title: searchHandleInfo
	* @Description: TODO(查询已办信息列表) 
	* @param req 接收app端传入参数 
	* @return BaseHttpParamsResp 返回给app端响应
	* @throws Exception
	*/
	@RequestMapping(path = "/searchHandleInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "查询已办信息列表", modelType = RequestAccessConstants.SEARCH, desc = "通过登录人ID获得登录人待办列表")
	public BaseHttpParamsPageResp searchHandleInfo(@RequestBody @Validated BaseHttpParamsPageAppReq req)
			throws Exception {
		logger.debug("开始获得已办任务列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获得当前用户借贷已办任务列表响应信息
		BaseHttpParamsPageResp resp = loanHandledService.queryLoanHandledInfoByPage(req,
				user.getId() + ":" + user.getRealName());
		resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
		resp.setResponseMessage(ResponseConstants.REQUEST_SUCCESS.getRetMsg());
		resp.createSign();
		return resp;
	}

	/** 
	* @Title: searchHandleInfoById 
	* @Description: TODO(获取已办详情) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/searchHandleInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取已办详情", modelType = RequestAccessConstants.SEARCH, desc = "通过借贷申请id获得暂存信息详情")
	public BaseHttpParamsResp searchHandleInfoById(@RequestBody @Validated LoanHandledInfoReq req) throws Exception {

		logger.debug("开始获取已办详情");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取个人客户信息
		LoanHandledInfoResp loanResp = loanHandledService.searchHandleInfoById(req);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), loanResp);
		baseHttpParamsResp.createSign();

		logger.debug("结束获取已办详情");
		return baseHttpParamsResp;
	}
}
