package com.company.platform.restapi.controller.loan;

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

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.model.repayment.GetRepayInfoListReq;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.restapi.service.repayment.ILoanRepaymentService;

/** 
* @ClassName: LoanRepaymentController 
* @Description: TODO(还款计划) 
* @author dongjian 
* @date 2018年5月23日 下午4:12:37 
*  
*/
@RestController
@RequestMapping("appApi")
public class LoanRepaymentController extends BaseController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(LoanRepaymentController.class);
    
    @Resource
	private ILoanRepaymentService repaymentService;
    
    @Resource
    private ILoanProductApplyService loanProductApplyService;
    
	
    @RequestMapping(path = "/getRepayInfoList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @RequestAccessAnnotation(modelName="获取还款信息",modelType=RequestAccessConstants.SEARCH,desc="获取还款信息")
    public BaseHttpParamsPageResp getRepayInfoList(
            @RequestBody @Validated GetRepayInfoListReq getRepayInfoListReq)
                    throws Exception {
		BaseHttpParamsPageResp baseHttpParamsPageResp = new BaseHttpParamsPageResp();
		
		baseHttpParamsPageResp = repaymentService.queryRepaymentInfo(getRepayInfoListReq);
		
        baseHttpParamsPageResp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
        baseHttpParamsPageResp.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
        baseHttpParamsPageResp.setResponseMessage("获取还款信息成功");
        baseHttpParamsPageResp.createSign();// 生成验签
        if (logger.isInfoEnabled()) {
            logger.info("获取还款信息成功");
        }

        return baseHttpParamsPageResp;
    }
    
}
