/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.loan
 *
 *    Filename:    LoanApplyController.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.activiti.WeiXinActivitiService;
import com.company.platform.base.util.DateUtil;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.loan.LoanTempMapper;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.loan.deal.LoanProductApplyDealReq;
import com.company.platform.restapi.model.loan.deal.LoanProductApplyStorage;
import com.company.platform.restapi.model.loan.deal.LoanRepaymentTrailReq;
import com.company.platform.restapi.model.loan.fee.LoanApplyFeeInfo;
import com.company.platform.restapi.model.loan.fee.LoanProductApplyFeeReq;
import com.company.platform.restapi.model.loan.handled.ImageDataInfo;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.model.loan.onlineTemp.LoanOnlineResp;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfoReq;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.restapi.service.loan.ILoanApplyCompleteInfoService;
import com.company.platform.restapi.service.loan.ILoanApplyFeeService;
import com.company.platform.restapi.service.loan.ILoanCodeService;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.restapi.service.loan.IProductApplyInfoService;
import com.company.platform.restapi.service.modelmanager.IImgAndDocModelService;
import com.company.platform.restapi.validated.custom.CustomerOrgValidate;
import com.company.platform.restapi.validated.loan.LoanProductValidate;

/**
 * @ClassName: LoanApplyController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2018年1月18日 上午9:47:49
 * 
 */
@RestController
@RequestMapping("wechat")
public class LoanApplyController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanApplyController.class);

	@Autowired
	private LoanProductValidate loanProductValidate;
	@Autowired
	private CustomerOrgValidate customerOrgValidate;

	@Resource
	private ILoanCodeService loanCodeService;
	@Resource
	private IProductApplyInfoService productApplyInfoService;
	@Resource
	private ILoanApplyFeeService loanApplyFeeService;
	@Resource
	private ICustomerInfoService customerInfoService;

	@Resource
	private ILoanApplyCompleteInfoService loanApplyCompleteInfoService;
	@Resource
	private ILoanProductApplyService loanProductApplyService;
	
	@Resource
	private WeiXinActivitiService weiXinActivitiService;
	
	@Autowired
	private LoanTempMapper loanTempMapper;
	@Autowired
	private IImgAndDocModelService imgAndDocModelService;
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	/** 
	* @Title: onlineTempLoanApply 
	* @Description: TODO(在线暂存) 
	* @param @param loanProductApplyInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/onlineTempLoanApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "在线暂存", modelType = RequestAccessConstants.INSERT, desc = "保存在线暂存借贷信息")
	public BaseHttpParamsResp onlineTempLoanApply(String  appinfo) throws Exception {
		
		logger.info("在线暂存开始");
		LoanProductApplyInfoReq loanProductApplyInfoReq = JSONObject.toJavaObject(JSONObject.parseObject(appinfo), LoanProductApplyInfoReq.class); 
		BaseHttpParamsResp baseHttpParamsResp = null;
		LoanOnlineResp resp = new LoanOnlineResp();
		// 获取借贷申请信息
		LoanProductApplyInfo loanProductApplyInfo = loanProductApplyInfoReq.getLoanProductApplyInfo();
		//获取申请人信息
		String customerId = loanProductApplyInfoReq.getCustomerId();
		String mobilePhone = loanProductApplyInfoReq.getMobilePhone();
		String address = loanProductApplyInfoReq.getAddress();
		
		// 完善借贷信息
		String updateTime = DateUtil.dateTimeFormat(new Date());
		loanProductApplyInfo.setUpdateTime(updateTime);
		resp.setUpdateTime(updateTime);
		
		loanProductApplyInfo.setRepayTermCount("1");
		loanProductApplyInfo.setRepayTermUnit("2");
		
		// 校验借贷申请信息
		LoanProductApplyEntity applyBefore = loanProductValidate.loanProductApplyOnlineValidate(loanProductApplyInfo);
		// 校验担保人数据格式
		//loanProductValidate.loanGuarantorInfoValidate(loanProductApplyInfoReq.getGuarantorInfo(), "online");
		// 获取将要保存的模型信息
		String jsonData = loanProductApplyInfoReq.getModelData();
		// 获取模型记录
		Map<String, Object> recordMap = loanProductApplyService.selectBusModelRecord(loanProductApplyInfo.getId());
		if (StringUtils.isNotEmpty(jsonData)) {
			boolean flag = loanProductValidate.modelDataValidate(jsonData, applyBefore.getLoanProductId(),
					loanProductApplyInfo.getId(), "online");
			if (flag) {// 关联业务模型
				// 保存业务模型信息
				JSONObject modelDataJson = JSONObject.parseObject(jsonData);
				if (recordMap != null && recordMap.size() > 0) {// 有记录，更新
					loanProductApplyService.modifyModelData(modelDataJson, recordMap.get("id").toString());
				} else {// 新建
					String recordId = loanProductApplyService.saveModelData(loanProductApplyInfo.getId(), modelDataJson,
							"在线暂存，保存业务模型数据");
					if (StringUtils.isNotBlank(recordId)) {
						String modelId = modelDataJson.getString("id");
						resp.setBusModelId(modelId);
						loanProductApplyService.saveLoanApplyModelS(loanProductApplyInfo.getId(), modelId, "BUM",
								recordId);
					}
				}
			}
		} else {// 清空模型中所有数据为null
			if (recordMap != null && recordMap.size() > 0) {
				loanProductApplyService.emptyModelData(recordMap.get("id").toString());
			}
		}
//		if (loanProductApplyInfo.getRepayType() != null && "1".equals(loanProductApplyInfo.getRepayType())) {
//			// 还款方式=1（一次性还本付息），还款间隔及其单位为null
//			loanProductApplyInfo.setRepayTermCount(null);
//			loanProductApplyInfo.setRepayTermUnit(null);
//		}
		// 费用信息处理
		loanApplyFeeService.dealLoanApplyFeeInfo(applyBefore, loanProductApplyInfo);

		// 保存担保人信息
//		productApplyInfoService.saveLoanGuarantorInfo(loanProductApplyInfoReq.getGuarantorInfo(),
//				loanProductApplyInfo.getId());
		// 保存借贷申请信息
		//loanProductApplyInfo.setDownPaymentsAmount(applyBefore.getDownPaymentsAmount());// 确保首付不启用时数据正确性
		productApplyInfoService.modifyLoanProductApply(loanProductApplyInfo);
		//更新客户手机号及户籍地址
		customerInfoService.modifyCustomerPublicInfo(customerId, mobilePhone);
		
		String historyAddress = customerInfoMapper.getHistoryAddress(customerId);
		if(historyAddress != null && !historyAddress.equals("")) {
			customerInfoService.updateCustomerLocationInfoAddress(customerId, address);
		} else {
			customerInfoService.insertCustomerLocationInfo(customerId, address);	
		}
		resp.setImgModelId(loanProductApplyInfoReq.getImgModelId());

		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		//baseHttpParamsResp.createSign();
		logger.info("在线暂存结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: getloanApplyFee 
	* @Description: TODO(获取贷款申请费用) 
	* @param @param loanProductApplyInfoReq
	* @param @param request
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getloanApplyFee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取贷款申请费用", modelType = RequestAccessConstants.SEARCH, desc = "获取贷款申请费用借贷信息")
	public BaseHttpParamsResp getloanApplyFee(@RequestBody @Validated LoanProductApplyFeeReq req) throws Exception {
		logger.info("获取贷款申请费用开始");
		loanProductValidate.loanApplyFeeValidate(req, "storage");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取贷款申请费用信息
		List<LoanApplyFeeInfo> LoanProductApplyFeeInfo = loanApplyFeeService.getLoanFeeInfo(req);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), LoanProductApplyFeeInfo);
		baseHttpParamsResp.createSign();
		logger.info("获取贷款申请费用结束");
		return baseHttpParamsResp;
	}

	@RequestMapping(path = "/getloanApplyAuditFee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取审核时贷款申请费用", modelType = RequestAccessConstants.SEARCH, desc = "获取贷款申请费用借贷信息")
	public BaseHttpParamsResp getloanApplyAuditFee(@RequestBody @Validated LoanRepaymentTrailReq req) throws Exception {
		logger.info("获取贷款申请费用开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取审核时贷款申请费用信息
		LoanProductApplyFeeReq feeReq = new LoanProductApplyFeeReq();
		BeanUtils.copyProperties(req, feeReq);
		loanProductValidate.loanApplyFeeValidate(feeReq, "auditing");
		List<LoanApplyFeeInfo> LoanProductApplyFeeInfo = loanApplyFeeService.getLoanFeeInfo(feeReq);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), LoanProductApplyFeeInfo);
		baseHttpParamsResp.createSign();
		logger.info("获取审核时贷款申请费用结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: offlineTempLoanApply 
	* @Description: TODO(离线转在线) 
	* @param @param offlineTempLoanApplyReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
//	@Transactional
//	@RequestMapping(path = "/offlineTempLoanApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
//	@ResponseBody
//	@RequestAccessAnnotation(modelName = "离线转在线", modelType = RequestAccessConstants.INSERT, desc = "保存借贷申请及客户信息")
//	public BaseHttpParamsResp offlineTempLoanApply(
//			@RequestBody @Validated OfflineTempLoanApplyReq offlineTempLoanApplyReq) throws Exception {
//		logger.info("离线转在线开始");
//		BaseHttpParamsResp baseHttpParamsResp = null;
//		/* 校验数据 */
//		// 校验借贷申请信息
//		LoanProductInfo product = loanProductValidate.loanProductApplyValidate(offlineTempLoanApplyReq);
//		// 校验担保人数据格式
//		loanProductValidate.loanGuarantorInfoValidate(offlineTempLoanApplyReq.getGuarantorInfo(), "offline");
//
//		LoanProductApplyEntity apply = offlineTempLoanApplyReq.getLoanProductApplyEntity();
//		/* 产品权限校验 */
//		String productId = apply.getLoanProductId();
//		// 校验产品的存在性与生效性，获取借贷编码类型
//		String loanCodeType = loanProductValidate.loanProductValidate(productId);
//		// 获取当前用户所在的组织机构信息（机构id和机构编码）
//		Map<String, String> org = loanCodeService.getOrgInfoByUserId();
//		// 校验用户权限
//		loanProductValidate.loanProductOrgRelValidate(productId, org.get("orgId").toString());
//		/* 客户权限校验 */
//		// 验证数据是否同步（组织机构）
//		customerOrgValidate.customerOrgValidate(offlineTempLoanApplyReq.getCustomerOrgId());
//		// 校验用户基本信息
//		Map<String, String> map = customerOrgValidate.customerInfoValidate(offlineTempLoanApplyReq.getCredentialType(),
//				offlineTempLoanApplyReq.getCredentialNo(), offlineTempLoanApplyReq.getName());
//		String customerType = offlineTempLoanApplyReq.getCustomerType();
//		if ("0".equals(customerType)) {// 个人客户
//			PersonalCustomerReq personal = new PersonalCustomerReq();
//			personal.setId(map.get("customerId"));
//			personal.setCustomerOrgId(offlineTempLoanApplyReq.getCustomerOrgId());
//			personal.setPersonalCustomerBaseInfo(offlineTempLoanApplyReq.getPersonalCustomerBaseInfo());
//			personal.setCustomerContactInfo(offlineTempLoanApplyReq.getCustomerContactInfo());
//			personal.setCustomerLocationInfo(offlineTempLoanApplyReq.getCustomerLocationInfo());
//			personal.setCustomerJobDetailInfo(offlineTempLoanApplyReq.getCustomerJobDetailInfo());
//			personal.setCustomerMateInfo(offlineTempLoanApplyReq.getCustomerMateInfo());
//			personal.setCustomerRelationshipInfo(offlineTempLoanApplyReq.getCustomerRelationshipInfo());
//			personal = customerOrgValidate.persionCustomerValidate(personal, "offline");
//			offlineTempLoanApplyReq.setCustomerMateInfo(personal.getCustomerMateInfo());
//		} else if ("1".equals(customerType)) {// 企业客户
//			EnterpriseCustomerReq enterprise = new EnterpriseCustomerReq();
//			enterprise.setId(map.get("customerId"));
//			enterprise.setCustomerOrgId(offlineTempLoanApplyReq.getCustomerOrgId());
//			enterprise.setCorporationBaseAndLrInfo(offlineTempLoanApplyReq.getCorporationBaseAndLrInfo());
//			enterprise.setCorporationCustomerContactinfo(offlineTempLoanApplyReq.getCorporationCustomerContactinfo());
//			customerOrgValidate.enterpriseCustomerValidate(enterprise, "offline");
//		}
//
//		// 业务处理
//		// 保存客户信息
//		String customerId = "";
//		String date = DateUtil.dateTimeFormat(new Date());
//		List<CustomerContactInfo> contactList = offlineTempLoanApplyReq.getCustomerContactInfo();
//		List<CustomerLocationInfo> locationList = offlineTempLoanApplyReq.getCustomerLocationInfo();
//		if (null == map.get("customerId") || "".equals(map.get("customerId"))) {// 新客户
//			// 新建客户信息
//			customerId = UUID.randomUUID().toString();
//			CustomerPublicInfo customerPublic = new CustomerPublicInfo();
//			customerPublic.setId(customerId);
//			customerPublic.seteType(customerType);
//			customerPublic.setCustomerNo(customerInfoService.getCustomerNo("CUSTOMER_TYPE"));
//			customerPublic.setOrgId(offlineTempLoanApplyReq.getCustomerOrgId());
//			customerPublic.setCredentialType(offlineTempLoanApplyReq.getCredentialType());
//			customerPublic.setCredentialNo(offlineTempLoanApplyReq.getCredentialNo());
//			customerPublic.setCustomerName(offlineTempLoanApplyReq.getName());
//			customerPublic.setCreateTime(date);
//			customerPublic.setUpdateTime(date);
//			// 创建客户基础数据
//			customerInfoService.insertCustomerBaseInfo(customerPublic);
//		} else {// 老客户
//			customerId = map.get("customerId");
//			if ("0".equals(customerType)) {
//				// 修改客户信息更新时间
//				if (contactList != null || locationList != null
//						|| offlineTempLoanApplyReq.getCustomerJobDetailInfo() != null
//						|| offlineTempLoanApplyReq.getCustomerRelationshipInfo() != null
//						|| offlineTempLoanApplyReq.getPersonalCustomerBaseInfo() != null) {
//					customerInfoService.modifyCustomerPublicUpdateTimeById(date, customerId);
//				}
//				// 查询个人客户联系信息
//				if (contactList != null && contactList.size() > 0) {
//					// 完善数据id
//					contactList = customerInfoService.getCustomerContactInfo(contactList, customerId);
//				}
//			} else {
//				// 修改企业客户基本信息
//				if (offlineTempLoanApplyReq.getCorporationBaseAndLrInfo() != null || locationList != null
//						|| offlineTempLoanApplyReq.getCorporationCustomerShareholderInfo() != null
//						|| offlineTempLoanApplyReq.getCorporationCustomerContactinfo() != null) {
//					// 修改客户信息更新时间
//					customerInfoService.modifyCustomerPublicUpdateTimeById(date, customerId);
//				}
//			}
//			// 查询客户（个人或企业）地址信息id
//			if (locationList != null && locationList.size() > 0) {
//				// 完善数据id
//				customerInfoService.getCustomerLocationInfo(locationList, customerId);
//			}
//		}
//		if ("0".equals(customerType)) {// 个人客户
//			// 保存客户个人基本信息
//			PersonalCustomerBaseInfo personal = offlineTempLoanApplyReq.getPersonalCustomerBaseInfo();
//			if (personal != null) {
//				personal.setId(customerId);
//				customerInfoService.savePersonalCustomerInfo(personal, "offline");
//			}
//			// 保存客户地址信息（register：户籍work：工作live：居住）
//			customerInfoService.saveCustomerLocationInfo(locationList, customerId, "offline");
//			// 保存客户联系信息（pMobile：个人手机号wPhone：办公电话）
//			customerInfoService.saveCustomerContactInfo(contactList, customerId, "offline");
//			// 保存客户工作信息
//			customerInfoService.saveCustomerJobDetailInfo(offlineTempLoanApplyReq.getCustomerJobDetailInfo(),
//					customerId, "offline");
//			// 保存客户联系人信息
//			customerInfoService.saveCustomerRelationshipInfo(offlineTempLoanApplyReq.getCustomerRelationshipInfo(),
//					customerId, "offline");
//			// 保存客户配偶信息
//			customerInfoService.saveCustomerMateInfo(offlineTempLoanApplyReq.getCustomerMateInfo(), customerId,
//					"offline", personal != null ? personal.getMartialStatus() : null);
//			// 保存客户资产信息
//			customerInfoService.saveCustomerAssetInfo(offlineTempLoanApplyReq.getCustomerAssetInfo(), customerId,
//					"offline");
//		} else if ("1".equals(customerType)) {// 企业客户
//			// 保存客户地址信息（business:经营地址register：注册地址）
//			customerInfoService.saveCustomerLocationInfo(locationList, customerId, "offline");
//			// 保存企业客户联系人信息
//			customerInfoService.saveCorporationCustomerContactinfo(
//					offlineTempLoanApplyReq.getCorporationCustomerContactinfo(), customerId, "offline");
//			// 保存客户股东信息
//			customerInfoService.saveCorporationCustomerShareholderInfo(
//					offlineTempLoanApplyReq.getCorporationCustomerShareholderInfo(), customerId, "offline");
//			// 保存企业基本信息和法人信息
//			CorporationBaseAndLrInfo base = offlineTempLoanApplyReq.getCorporationBaseAndLrInfo();
//			if (base != null) {
//				base.setId(customerId);
//				customerInfoService.saveCorporationBaseAndLrInfo(base, "offline");
//			}
//		}
//
//		// 获取贷款申请编码
//		String loanCode = loanCodeService.getSysCode(loanCodeType + "_LOAN",
//				!org.containsKey("orgCode") || org.get("orgCode") == null ? "" : org.get("orgCode").toString());
//
//		String loanProductApplyId = UUID.randomUUID().toString();
//		apply.setId(loanProductApplyId);
//		apply.setCode(loanCode);
//		apply.setCustomerId(customerId);
//		apply.setUpdateTime(date);
//		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		apply.setRegisterId(user.getId());
//		apply.setAccountManagerId(user.getId());
//		apply.setOrgId(offlineTempLoanApplyReq.getCustomerOrgId());
//		if (apply.getRepayType() != null && "1".equals(apply.getRepayType())) {
//			// 还款方式=1（一次性还本付息），还款间隔及其单位为null
//			apply.setRepayTermCount(null);
//			apply.setRepayTermUnit(null);
//		}
//
//		if ("0".equals(product.getDownPaymentsEnable())) {
//			apply.setDownPaymentsAmount(null);
//		}
//		// 新建贷款申请信息
//		loanProductApplyService.insertOffLineLoanProductApply(apply);
//
//		// 获取将要保存的模型信息
//		String jsonData = offlineTempLoanApplyReq.getModelData();
//		String busModelId = null;
//		if (StringUtils.isNotEmpty(jsonData)) {
//			boolean flag = loanProductValidate.modelDataValidate(jsonData, apply.getLoanProductId(), loanProductApplyId,
//					"offline");
//			if (flag) {// 关联业务模型
//				JSONObject modelDataJson = JSONObject.parseObject(jsonData);
//				// 保存业务模型信息
//				String recordId = loanProductApplyService.saveModelData(loanProductApplyId, modelDataJson,
//						"离线转在线，保存业务模型数据");
//
//				if (StringUtils.isNotBlank(recordId)) {
//					busModelId = modelDataJson.getString("id");
//					loanProductApplyService.saveLoanApplyModelS(loanProductApplyId, busModelId, "BUM", recordId);
//				}
//			}
//		}
//		// 影像模型处理
//		String imgModelId = offlineTempLoanApplyReq.getImgModelId();
//		if (StringUtils.isNotEmpty(imgModelId)) {
//			String recordId = loanProductApplyService.saveImgModelRecord(loanProductApplyId, imgModelId,
//					"离线转在线，创建影像模型记录");
//			if (StringUtils.isNotBlank(recordId)) {
//				loanProductApplyService.saveLoanApplyImgModelS(loanProductApplyId, imgModelId, "IMM", recordId);
//			}
//		}
//
//		// 借贷申请费用信息保存
//		loanApplyFeeService.saveLoanApplyFeeInfo(apply);
//
//		if (product.getGuaranteeType() != null && product.getGuaranteeType().contains("3")) {// 担保方式包含保证人
//			// 保存担保人信息
//			productApplyInfoService.saveLoanGuarantorInfo(offlineTempLoanApplyReq.getGuarantorInfo(),
//					loanProductApplyId);
//		}
//
//		// 返回客户信息
//		OfflineResp resp = customerInfoService.getCustomerAllInfo(customerId, customerType);
//		resp.setCode(loanCode);
//		resp.setUpdateTime(date);
//		resp.setLoanProductApplyId(loanProductApplyId);
//		resp.setBusModelId(busModelId);
//		resp.setImgModelId(imgModelId);
//		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
//				ResponseConstants.SUCCESS.getRetMsg(), resp);
//		baseHttpParamsResp.createSign();
//		logger.info("离线转在线结束");
//		return baseHttpParamsResp;
//	}

	/** 
	* @Title: loanApplyComplete 
	* @Description: TODO(第二步申请提交) 
	* @param @param loanProductApplyCompleteInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
//	@Transactional
//	@RequestMapping(path = "/loanApplyComplete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
//	@ResponseBody
//	@RequestAccessAnnotation(modelName = "贷款申请第二步提交", modelType = RequestAccessConstants.INSERT, desc = "将已经填写好的贷款申请信息进行提交")
//	public BaseHttpParamsResp loanApplyComplete(LoanProductApplyCompleteInfoReq req, String userId, String orgId )
//			throws Exception {
//		logger.info("贷款申请第二步提交开始");
//		BaseHttpParamsResp baseHttpParamsResp = null;
//		String customerId = req.getLoanProductApplyInfo().getCustomerId();
//
//		if (StringUtils.isNotBlank(customerId)) {// 保存客户信息
//			String updateTime = DateUtil.dateTimeFormat(new Date());
//			// 更新客户修改时间
//			customerInfoService.modifyCustomerPublicUpdateTimeById(updateTime, customerId);
//			// 保存客户信息
//			PersonalCustomerBaseInfo personal = req.getPersonalCustomerBaseInfo();
//			// 获得当app登录用户
//			SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if (personal != null) {
//				// 个人客户信息校验
//				PersonalCustomerReq personalReq = new PersonalCustomerReq();
//				personalReq.setId(customerId);
//				personalReq.setCustomerOrgId(orgId);
//				personalReq.setPersonalCustomerBaseInfo(req.getPersonalCustomerBaseInfo());
//				personalReq.setCustomerContactInfo(req.getCustomerContactInfo());
//				personalReq.setCustomerLocationInfo(req.getCustomerLocationInfo());
//				personalReq.setCustomerJobDetailInfo(req.getCustomerJobDetailInfo());
//				personalReq.setCustomerMateInfo(req.getCustomerMateInfo());
//				personalReq.setCustomerRelationshipInfo(req.getCustomerRelationshipInfo());
//				personalReq = customerOrgValidate.persionCustomerValidate(personalReq, "online");
//				// 个人客户信息保存
//				personal.setId(customerId);
//				customerInfoService.savePersonalCustomerInfo(personal, "online");
//				// 保存客户地址信息（register：户籍work：工作live：居住）
//				customerInfoService.saveCustomerLocationInfo(req.getCustomerLocationInfo(), customerId, "online");
//				// 保存客户联系信息（pMobile：个人手机号wPhone：办公电话）
//				customerInfoService.saveCustomerContactInfo(req.getCustomerContactInfo(), customerId, "online");
//				// 保存客户工作信息
//				customerInfoService.saveCustomerJobDetailInfo(req.getCustomerJobDetailInfo(), customerId, "online");
//				// 保存客户联系人信息
//				customerInfoService.saveCustomerRelationshipInfo(req.getCustomerRelationshipInfo(), customerId,
//						"online");
//				// 保存客户配偶信息
//				customerInfoService.saveCustomerMateInfo(personalReq.getCustomerMateInfo(), customerId, "online",
//						personal.getMartialStatus());
//				// 保存资产信息
//				customerInfoService.saveCustomerAssetInfo(personalReq.getCustomerAssetInfo(), customerId, "online");
//			} else {
//				// 企业客户信息校验
//				EnterpriseCustomerReq enterprise = new EnterpriseCustomerReq();
//				enterprise.setId(customerId);
//				// 设置用户所在机构，取第一个
//				enterprise.setCustomerOrgId(user.getSecurityOrg().get(0).getId());
//				enterprise.setCorporationBaseAndLrInfo(req.getCorporationBaseAndLrInfo());
//				enterprise.setCorporationCustomerContactinfo(req.getCorporationCustomerContactinfo());
//				customerOrgValidate.enterpriseCustomerValidate(enterprise, "online");
//
//				// 保存客户地址信息（business:经营地址register：注册地址）
//				customerInfoService.saveCustomerLocationInfo(req.getCustomerLocationInfo(), customerId, "online");
//				// 保存企业客户联系人信息
//				customerInfoService.saveCorporationCustomerContactinfo(req.getCorporationCustomerContactinfo(),
//						customerId, "online");
//				// 保存客户股东信息
//				customerInfoService.saveCorporationCustomerShareholderInfo(req.getCorporationCustomerShareholderInfo(),
//						customerId, "online");
//				// 保存企业基本信息和法人信息
//				CorporationBaseAndLrInfo base = req.getCorporationBaseAndLrInfo();
//				customerInfoService.saveCorporationBaseAndLrInfo(base, "online");
//			}
//		}
//		LoanProductApplyStorage dealReq = new LoanProductApplyStorage();
//		BeanUtils.copyProperties(req, dealReq);
//		this.dealLoanApplyInfo(dealReq);
//		
////		String audit = "";
////		String realname = "";
////		String nextTaskKey = "";
////		Map<String, String> codeMap = weiXinActivitiService.startActiviti(req.getLoanProductApplyInfo().getId(), nextTaskKey, audit, userId, realname);
//		
//		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
//				ResponseConstants.SUCCESS.getRetMsg());
//		baseHttpParamsResp.createSign();
//		logger.info("贷款申请第二步提交结束");
//		return baseHttpParamsResp;
//	}
	
	/** 
	* @Title: loanApplyComplete 
	* @Description: TODO(第二步申请提交) 
	* @param @param LoanProductApplyInfoReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/loanApplyComplete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "贷款申请第二步提交", modelType = RequestAccessConstants.INSERT, desc = "将已经填写好的贷款申请信息进行提交")
	public BaseHttpParamsResp loanApplyComplete(String appinfo, String userId )
			throws Exception {
		logger.info("贷款申请第二步提交开始");
		LoanProductApplyInfoReq loanProductApplyInfoReq = JSONObject.toJavaObject(JSONObject.parseObject(appinfo), LoanProductApplyInfoReq.class); 
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 获取借贷申请信息
		LoanProductApplyInfo loanProductApplyInfo = loanProductApplyInfoReq.getLoanProductApplyInfo();
		//图片校验
		Map<String,String>  checkImage=uploadFileCheck(loanProductApplyInfo.getId());
		if(!checkImage.get("code").equals("0000")) {
			baseHttpParamsResp = BaseHttpParamsResp.requestError(checkImage.get("message"));
			return baseHttpParamsResp;
		}
		
		LoanOnlineResp resp = new LoanOnlineResp();
		
		//获取申请人信息
		String customerId = loanProductApplyInfoReq.getCustomerId();
		String mobilePhone = loanProductApplyInfoReq.getMobilePhone();
		String address = loanProductApplyInfoReq.getAddress();
		
		// 完善借贷信息
		String updateTime = DateUtil.dateTimeFormat(new Date());
		loanProductApplyInfo.setUpdateTime(updateTime);
		resp.setUpdateTime(updateTime);
		
		loanProductApplyInfo.setRepayTermCount("1");
		loanProductApplyInfo.setRepayTermUnit("2");
		
		// 校验借贷申请信息
		LoanProductApplyEntity applyBefore = loanProductValidate.loanProductApplyOnlineValidate(loanProductApplyInfo);
		// 获取将要保存的模型信息
		String jsonData = loanProductApplyInfoReq.getModelData();
		// 获取模型记录
		Map<String, Object> recordMap = loanProductApplyService.selectBusModelRecord(loanProductApplyInfo.getId());
		if (StringUtils.isNotEmpty(jsonData)) {
			boolean flag = loanProductValidate.modelDataValidate(jsonData, applyBefore.getLoanProductId(),
					loanProductApplyInfo.getId(), "online");
			if (flag) {// 关联业务模型
				// 保存业务模型信息
				JSONObject modelDataJson = JSONObject.parseObject(jsonData);
				if (recordMap != null && recordMap.size() > 0) {// 有记录，更新
					loanProductApplyService.modifyModelData(modelDataJson, recordMap.get("id").toString());
				} else {// 新建
					String recordId = loanProductApplyService.saveModelData(loanProductApplyInfo.getId(), modelDataJson,
							"在线暂存，保存业务模型数据");
					if (StringUtils.isNotBlank(recordId)) {
						String modelId = modelDataJson.getString("id");
						resp.setBusModelId(modelId);
						loanProductApplyService.saveLoanApplyModelS(loanProductApplyInfo.getId(), modelId, "BUM",
								recordId);
					}
				}
			}
		} else {// 清空模型中所有数据为null
			if (recordMap != null && recordMap.size() > 0) {
				loanProductApplyService.emptyModelData(recordMap.get("id").toString());
			}
		}

		// 费用信息处理
		loanApplyFeeService.dealLoanApplyFeeInfo(applyBefore, loanProductApplyInfo);

		productApplyInfoService.modifyLoanProductApply(loanProductApplyInfo);
		//更新客户手机号及户籍地址
		customerInfoService.modifyCustomerPublicInfo(customerId, mobilePhone);
		
		String historyAddress = customerInfoMapper.getHistoryAddress(customerId);
		if(historyAddress != null && !historyAddress.equals("")) {
			customerInfoService.updateCustomerLocationInfoAddress(customerId, address);
		} else {
			customerInfoService.insertCustomerLocationInfo(customerId, address);	
		}
		
		resp.setImgModelId(loanProductApplyInfoReq.getImgModelId());
				
		String audit = "submit";
		
		//启动流程
		String realname = loanTempMapper.getOrgName(loanProductApplyInfo.getId());
		String nextTaskKey = "tasktwo";
		Map<String, String> codeMap = weiXinActivitiService.startActiviti(loanProductApplyInfo.getId(), nextTaskKey, audit, userId, realname);
		if(codeMap.get("retCode").equals("9999")) {
			baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.PROCESS_AUDIT_ERROR.getRetCode(), 
					ResponseConstants.PROCESS_AUDIT_ERROR.getRetMsg());
		}else {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
					ResponseConstants.SUCCESS.getRetMsg());
			logger.info("贷款申请第二步提交结束");			
		}
		return baseHttpParamsResp;
	}
	
	

	/** 
	* @Title: saveLoanApplyAudit 
	* @Description: TODO(审核时保存借贷申请信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/saveLoanApplyAudit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "审核时保存借贷申请信息", modelType = RequestAccessConstants.INSERT, desc = "审核时保存借贷申请信息")
	public BaseHttpParamsResp saveLoanApplyAudit(@RequestBody @Validated LoanProductApplyDealReq req) throws Exception {
		logger.info("审核时保存借贷申请信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		req.setAuths("1");
		// 完善借贷信息
		String updateTime = DateUtil.dateTimeFormat(new Date());
		req.getLoanProductApplyInfo().setUpdateTime(updateTime);
		LoanProductApplyStorage dealReq = new LoanProductApplyStorage();
		BeanUtils.copyProperties(req, dealReq);
		this.dealLoanApplyInfo(dealReq);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), updateTime);
		baseHttpParamsResp.createSign();
		logger.info("审核时保存借贷申请信息结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: dealLoanApplyInfo 
	* @Description: TODO(保存借贷申请信息) 
	* @param @param req
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void dealLoanApplyInfo(LoanProductApplyStorage req) throws BusinessException {
		// 验证传入数据是否正确（借贷信息）,更新借贷信息
		req.setLoanProductApplyInfo(loanApplyCompleteInfoService.applyCompleteInfoReqValidate(req, request));
		// 校验担保人数据格式
		loanProductValidate.loanGuarantorInfoValidate(req.getGuarantorInfo(), "online");

		// 提交贷款申请信息(校验提交数据通过，才能保存（提交流程）申请信息)
		loanApplyCompleteInfoService.insertapplyCompleteInfo(req, request);
	}
	
    /**
     * 图片数量核验
    * @Title: uploadFileCheck 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param loanProductApplyId
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Map<String,String>    返回类型 
    * @throws
     */
    private Map<String,String> uploadFileCheck(String loanProductApplyId)throws Exception {
			Map<String,String> result = new HashMap<String,String>();
			//通过借贷申请id获取当前模型分类信息
			List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
			String dataId = imgAndDocModelService.getMaterialDataIdPro(imgAndDocTitle.get(0).getImgDocId().toString(), loanProductApplyId);
			for (ImgAndDocTitle imgAndDocTitle2 : imgAndDocTitle) {
				//获得文件可上传数量
				int imageClassNum =  imgAndDocModelService.imageClassNum(imgAndDocTitle2.getId().toString(),dataId);
				//获取文件最少数量
				int imageFloorNum = imgAndDocTitle2.getFloor();
				//获取现有针对于影像分类上传的文件的数量
				// 影像资料数据信息
				List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
				int imageDataNum = 0;
				for (ImageDataInfo imageDataInfo : imageInfoList) {
					if(imageDataInfo.getClassId().equals(imgAndDocTitle2.getId().toString())){
						imageDataNum ++;
					}
				}
				if(imageFloorNum > imageDataNum){
					result.put("code", "9999");
					result.put("message", "影像资料最少上传"+imageFloorNum+"张！");
					break;
				}else{
					result.put("code", "0000");
					result.put("message", "核验通过");
				}
			}
			return result;
			}

}
