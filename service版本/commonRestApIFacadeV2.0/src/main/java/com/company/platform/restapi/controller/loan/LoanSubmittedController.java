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
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoReq;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanSubmittedReq;
import com.company.platform.restapi.service.loan.ILoanSubmittedService;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: LoanSubmittedController 
* @Description: TODO(已提交客户贷款) 
* @author dongjian 
* @date 2018年5月17日 下午2:44:49 
*  
*/
@RestController
@RequestMapping("appApi")
public class LoanSubmittedController extends BaseController  {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanSubmittedController.class);

	@Autowired
	private ILoanSubmittedService loanSubmittedService;

	/**  
	* @Title: searchSubmitInfo
	* @Description: TODO(查询客户贷款列表) 
	* @param req 接收app端传入参数 
	* @return BaseHttpParamsResp 返回给app端响应
	* @throws Exception
	*/
	@RequestMapping(path = "/searchLoanSubmittedList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "查询客户贷款列表", modelType = RequestAccessConstants.SEARCH, desc = "通过登录人ID获得登录人已提交进件列表")
	public BaseHttpParamsPageResp searchLoanSubmittedList(@RequestBody @Validated LoanSubmittedReq req)
			throws Exception {
		logger.debug("开始获得客户贷款列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获得当前用户借贷已办任务列表响应信息
		BaseHttpParamsPageResp resp = loanSubmittedService.queryLoanSubmitInfoByPage(req,user.getId());
		resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
		resp.setResponseMessage(ResponseConstants.REQUEST_SUCCESS.getRetMsg());
		resp.createSign();
		return resp;
	}

	/** 
	* @Title: searchSubmitInfoById 
	* @Description: TODO(获取客户贷款详情) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/searchLoanSubmittedInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取客户贷款详情", modelType = RequestAccessConstants.SEARCH, desc = "通过借贷申请id获得客户贷款详情")
	public BaseHttpParamsResp searchLoanSubmittedInfo(@RequestBody @Validated LoanHandledInfoReq req) throws Exception {

		logger.debug("开始获取客户贷款详情");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取个人客户信息
		LoanHandledInfoResp loanResp = loanSubmittedService.searchSubmitInfoById(req);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), loanResp);
		baseHttpParamsResp.createSign();

		logger.debug("结束获取客户贷款详情");
		return baseHttpParamsResp;
	}
}
