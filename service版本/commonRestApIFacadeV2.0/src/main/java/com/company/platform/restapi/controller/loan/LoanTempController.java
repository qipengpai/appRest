/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.loan
 *
 *    Filename:    LoanTempController.java
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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.activiti.WeiXinActivitiService;
import com.company.platform.restapi.dao.loan.LoanProductApplyModelMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.dao.modelmanager.ImgAndDocModelMapper;
import com.company.platform.restapi.model.custom.CustomerInfoReq;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.LoanApplyInfoForPersonalResp;
import com.company.platform.restapi.model.loan.LoanApplyInfoReq;
import com.company.platform.restapi.model.loan.LoanApplyInfoResp;
import com.company.platform.restapi.model.loan.LoanAuditingResp;
import com.company.platform.restapi.model.loan.LoanProductApplyModelV;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.LoanProductModelV;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoForPersonalResp;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoReq;
import com.company.platform.restapi.model.loan.ProductApplyInfoReq;
import com.company.platform.restapi.model.modelmanager.ImgAndDocModel;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.restapi.service.loan.ILoanTempService;
import com.company.platform.restapi.service.loan.IOneSetpApplyInfoService;
import com.company.platform.restapi.service.loan.IProductApplyInfoService;
import com.company.platform.restapi.validated.loan.ProductApplyInfoOrgValidate;
import com.company.platform.security.model.SecurityUser;


/** 
* @ClassName: LoanTempController 
* @Description: TODO(借贷暂存功能集合) 
* @author zhengjn 
* @date 2018年1月18日 上午9:45:59 
*  
*/
@RestController
@RequestMapping("wechat")
public class LoanTempController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanTempController.class);

	@Autowired
	private ILoanTempService loanTempService;

	@Resource
	private IProductApplyInfoService productApplyInfoService;

	@Resource
	private IOneSetpApplyInfoService OneSetpApplyInfoService;

	@Autowired
	private ProductApplyInfoOrgValidate productApplyInfoOrgValidate;

	@Resource
	private ICustomerInfoService customerInfoService;

	@Resource
	private ILoanProductApplyService loanProductApplyService;
	
	@Resource
	private WeiXinActivitiService weiXinActivitiService;
	
	@Resource
	private ProductInfoMapper productInfoMapper;
	
	@Resource
	private LoanProductApplyModelMapper loanProductApplyModelMapper;
	
	@Resource
	private ImgAndDocModelMapper imgAndDocModelMapper;

	/**  
	* @Title: searchTempInfo
	* @Description: TODO(查询暂存信息列表) 
	* @param req 接收app端传入参数 
	* @return BaseHttpParamsResp 返回给app端响应
	* @throws Exception
	*/
	@RequestMapping(path = "/searchTempInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "查询暂存信息列表", modelType = RequestAccessConstants.SEARCH, desc = "通过登录人ID获得登录人暂存列表")
	public BaseHttpParamsPageResp searchTempInfo(@RequestBody @Validated BaseHttpParamsPageAppReq req)
			throws Exception {
		logger.debug("开始获得暂存任务列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获得当前用户借贷暂存任务列表响应信息
		BaseHttpParamsPageResp resp = loanTempService.queryLoanProductApplySynosByPage(req, user.getId());
		resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
		return resp;
	}

	/** 
	* @Title: delTempInfo 
	* @Description: TODO(删除暂存信息) 
	* @param @param productApplyInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/delTempInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "删除暂存信息", modelType = RequestAccessConstants.DELETE, desc = "通过借贷申请id删除用户的暂存信息")
	public BaseHttpParamsResp delTempInfo(@RequestBody @Validated ProductApplyInfoReq productApplyInfoReq)
			throws Exception {
		logger.info("删除暂存信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;

		// 删除暂存信息(借贷申请信息)
		boolean b = productApplyInfoService.delTempInfo(productApplyInfoReq);
		if (b) {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
					ResponseConstants.SUCCESS.getRetMsg());
		} else {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.FAIL.getRetCode(),
					ResponseConstants.FAIL.getRetMsg());
		}
		baseHttpParamsResp.createSign();
		logger.info("删除暂存信息结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: loanApplyOneSetp 
	* @Description: TODO(贷款申请第一步提交) 
	* @param @param productApplyInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/loanApplyOneSetp", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "贷款申请第一步提交", modelType = RequestAccessConstants.INSERT, desc = "提交贷款申请")
	public BaseHttpParamsResp loanApplyOneSetp(OneSetpApplyInfoReq oneSetpApplyInfoReq, String userId,HttpServletRequest request)
			throws Exception {
		SecurityUser user = new SecurityUser();
		user.setId(userId);
		logger.info("贷款申请第一步提交开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 验证数据是否同步（组织机构）
		//productApplyInfoOrgValidate.productApplyInfoOrgValidate(oneSetpApplyInfoReq.getOrgId());
		// 验证传入数据是否正确
		oneSetpApplyInfoReq = OneSetpApplyInfoService.oneSetpApplyInfoReqValidate(oneSetpApplyInfoReq);
		// 更新贷款申请信息
		FullLoanProductApplyInfo loanProductApplyInfo = OneSetpApplyInfoService
				.insertProductApplyInfo(oneSetpApplyInfoReq, user, request);

//		String customerType = oneSetpApplyInfoReq.getCustomerType();
		// 关联当前产品所选用影像模型版本
		LoanProductModelV modelV = imgAndDocModelMapper
				.selectLoanProductModelVByLoanProductIdAndModelType(oneSetpApplyInfoReq.getLoanProductId(), "IMM");
		if (modelV != null) {// 当前产品关联影像模型
			ImgAndDocModel imgAndDocModel = imgAndDocModelMapper.getImgAndDocModelByCodeAndVersion(modelV.getModelCode(),
					modelV.getModelVersion());
			if (StringUtils.isNotEmpty(imgAndDocModel.getId())) {// 当前影像模型存在模型
					String recordId = loanProductApplyService.saveImgModelRecord(loanProductApplyInfo.getId(), imgAndDocModel.getId(),
							"借贷申请第一步提交，创建影像模型记录");
					if (StringUtils.isNotBlank(recordId)) {
						loanProductApplyService.saveLoanApplyImgModelS(loanProductApplyInfo.getId(), imgAndDocModel.getId(), "IMM", recordId);
					}
			}
		}
		//获取当前产品对应的影像模型id
//		List<LoanProductApplyModelV> LoanProductApplyModelV= loanProductApplyModelMapper.selectByLoanProductApplyIdAndModelType(loanProductApplyInfo.getId(),"IMM");
/*//		// 影像模型处理
		String code = LoanProductApplyModelV.get(0).getModelCode();
		String version = LoanProductApplyModelV.get(0).getModelVersion();
		//通过code和version获取影像模型信息
		ImgAndDocModel imgAndDocModel = imgAndDocModelMapper.getImgAndDocModelByCodeAndVersion(code,version);
		String imgModelId = imgAndDocModel.getId();
		if (StringUtils.isNotEmpty(imgModelId)) {
			String recordId = loanProductApplyService.saveImgModelRecord(loanProductApplyInfo.getId(), imgModelId,
					"借贷申请第一步提交，创建影像模型记录");
			if (StringUtils.isNotBlank(recordId)) {
				loanProductApplyService.saveLoanApplyImgModelS(loanProductApplyInfo.getId(), imgModelId, "IMM", recordId);
			}
		}*/
//		if ("0".equals(customerType)) {// 个人
		CustomerInfoReq customerInfoReq = new CustomerInfoReq();
		customerInfoReq.setCredentialType(oneSetpApplyInfoReq.getCredentialType());
		customerInfoReq.setCredentialNo(oneSetpApplyInfoReq.getCredentialNo());
		customerInfoReq.setCustomerOrgId(oneSetpApplyInfoReq.getOrgId());
		customerInfoReq.setMobilePhone(oneSetpApplyInfoReq.getMobilePhone());
		CustomerInfoResp cus = customerInfoService.getCustomerInfo(customerInfoReq);
		OneSetpApplyInfoForPersonalResp ocus = new OneSetpApplyInfoForPersonalResp();
		ocus.setLoanProductApplyId(loanProductApplyInfo.getId());
//		ocus.setUpdateTimeProductApply(loanProductApplyInfo.getUpdateTime());
//		CustomerPublicInfo cpi = cus.getCustomerPublicInfo();
//		if (cpi != null) {
//			ocus.setUpdateTimeCustomer(cpi.getUpdateTime());
//		}

		BeanUtils.copyProperties(cus, ocus);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), ocus);
//		} else {// 企业
//			CorpCustomerInfoReq customerInfoReq = new CorpCustomerInfoReq();
//			customerInfoReq.setCredentialType(oneSetpApplyInfoReq.getCredentialType());
//			customerInfoReq.setCredentialNo(oneSetpApplyInfoReq.getCredentialNo());
//			customerInfoReq.setCustomerOrgId(oneSetpApplyInfoReq.getOrgId());
//			EnterpriseInfoResp cus = customerInfoService.getEnterpriseInfo(customerInfoReq);
//			OneStepApplyInfoForEnterpriseResp ocus = new OneStepApplyInfoForEnterpriseResp();
//			ocus.setLoanProductApplyId(loanProductApplyInfo.getId());
//			ocus.setUpdateTimeProductApply(loanProductApplyInfo.getUpdateTime());
//			CustomerPublicInfo cpi = cus.getCustomerPublicInfo();
//			if (cpi != null) {
//				ocus.setUpdateTimeCustomer(cpi.getUpdateTime());
//			}
//			BeanUtils.copyProperties(cus, ocus);
//			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
//					ResponseConstants.SUCCESS.getRetMsg(), ocus);
//		}
		//baseHttpParamsResp.createSign();
		logger.info("贷款申请第一步提交结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: searchTempInfoById 
	* @Description: TODO(获取暂存信息详情) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/searchTempInfoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取暂存信息详情", modelType = RequestAccessConstants.SEARCH, desc = "通过借贷申请id获得暂存信息详情")
	public BaseHttpParamsResp searchTempInfoById(LoanApplyInfoReq req) throws Exception {

		logger.debug("开始获取暂存信息详情");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取暂存信息
		LoanApplyInfoResp loanResp = loanTempService.searchTempInfoById(req, "storage","");

		String customerType = loanResp.getCustomerType();
		CustomerPublicInfo customerInfo = loanResp.getCustomerPublicInfo();
		
//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String aCusUpdateTime = req.getUpdateTimeCustomer();
//		Date acTime = f.parse(aCusUpdateTime);
//		String sCusUpdateTime = customerInfo.getUpdateTime();
//		Date scTime = f.parse(sCusUpdateTime);
//		if (acTime.getTime() < scTime.getTime()) {
//			loanResp.setUpdateTimeCustomer(sCusUpdateTime);
//		if ("0".equals(customerType)) {// 个人
		CustomerInfoReq customerInfoReq = new CustomerInfoReq();
		customerInfoReq.setCredentialType(customerInfo.getCredentialType());
		customerInfoReq.setCredentialNo(customerInfo.getCredentialNo());
		customerInfoReq.setCustomerOrgId(customerInfo.getOrgId());
		customerInfoReq.setMobilePhone(customerInfo.getMobilePhone());
		CustomerInfoResp cus = customerInfoService.getCustomerInfo(customerInfoReq);
		LoanApplyInfoForPersonalResp laip = new LoanApplyInfoForPersonalResp();
		BeanUtils.copyProperties(loanResp, laip);
		BeanUtils.copyProperties(cus, laip);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), laip);
//		} else {// 企业
//			CorpCustomerInfoReq customerInfoReq = new CorpCustomerInfoReq();
//			customerInfoReq.setCredentialType(customerInfo.getCredentialType());
//			customerInfoReq.setCredentialNo(customerInfo.getCredentialNo());
//			customerInfoReq.setCustomerOrgId(customerInfo.getOrgId());
//			EnterpriseInfoResp cus = customerInfoService.getEnterpriseInfo(customerInfoReq);
//			LoanApplyInfoForEnterpriseResp laie = new LoanApplyInfoForEnterpriseResp();
//			BeanUtils.copyProperties(loanResp, laie);
//			BeanUtils.copyProperties(cus, laie);
//			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
//					ResponseConstants.SUCCESS.getRetMsg(), laie);
//		}
//		} else {
//			loanResp.setCustomerPublicInfo(null);
//			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
//					ResponseConstants.SUCCESS.getRetMsg(), loanResp);
//		}
//		baseHttpParamsResp.createSign();

		logger.debug("结束获取暂存信息详情");
		return baseHttpParamsResp;
	}
	
	/** 
	* @Title: auditingInfoRevise 
	* @Description: TODO(获取人工复核修改信息详情) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/auditingInfoRevise", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取人工复核修改信息详情", modelType = RequestAccessConstants.SEARCH, desc = "通过借贷申请id获得人工复核修改信息详情")
	public BaseHttpParamsResp auditingInfoRevise(LoanApplyInfoReq req) throws Exception {

		logger.debug("开始获取人工复核修改信息详情");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取暂存信息
		LoanApplyInfoResp loanResp = loanTempService.searchTempInfoById(req, "storage", "auditing");

		String customerType = loanResp.getCustomerType();
		CustomerPublicInfo customerInfo = loanResp.getCustomerPublicInfo();
		
		CustomerInfoReq customerInfoReq = new CustomerInfoReq();
		customerInfoReq.setCredentialType(customerInfo.getCredentialType());
		customerInfoReq.setCredentialNo(customerInfo.getCredentialNo());
		customerInfoReq.setCustomerOrgId(customerInfo.getOrgId());
		customerInfoReq.setMobilePhone(customerInfo.getMobilePhone());
		CustomerInfoResp cus = customerInfoService.getCustomerInfo(customerInfoReq);
		LoanApplyInfoForPersonalResp laip = new LoanApplyInfoForPersonalResp();
		BeanUtils.copyProperties(loanResp, laip);
		BeanUtils.copyProperties(cus, laip);
		
		//获取按钮信息
		String loanProductId = loanResp.getLoanProductApplyInfo().getLoanProductId();
		LoanProductInfo productInfo = productInfoMapper.queryByPrimaryId(loanProductId);
		String procdefKey = productInfo.getProcdefKey();
		List<FunctionInfo> buttonInfo = weiXinActivitiService.getButton(loanProductId, procdefKey);
		
		LoanAuditingResp arp = new LoanAuditingResp();
		arp.setButtonInfo(buttonInfo);
		arp.setLoanApplyInfoForPersonalResp(laip);
	
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), arp);

		logger.debug("结束获取人工复核修改信息详情");
		return baseHttpParamsResp;
	}
}
