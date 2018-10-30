/******************************************************************
 *
 *    Package:     com.company.platform.restapi.controller.custom
 *
 *    Filename:    CustomController.java
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
package com.company.platform.restapi.controller.custom;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.util.DateUtil;
import com.company.platform.restapi.model.custom.CustomerInfoReq;
import com.company.platform.restapi.model.custom.enterprise.CorpCustomerInfoReq;
import com.company.platform.restapi.model.custom.enterprise.CorporationBaseAndLrInfo;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseCustomerReq;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseModifyResp;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.custom.personal.CustomerModifyResp;
import com.company.platform.restapi.model.custom.personal.LocationOrContactBase;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerReq;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.restapi.validated.custom.CustomerOrgValidate;

/** 
* @ClassName: CustomController 
* @Description: TODO(个人 、企业客户操作集合) 
* @author wangxue 
* @date 2018年1月18日 上午9:35:11 
*  
*/
@RestController
@RequestMapping("appApi")
public class CustomController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(CustomController.class);

	@Resource
	private ICustomerInfoService customerInfoService;

	@Autowired
	private CustomerOrgValidate customerOrgValidate;

	/** 
	* @Title: getCustomInfo 
	* @Description: TODO(获取贷款申请人信息) 
	* @param @param customerInfoReq 个人客户传入参数
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getCustomInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取贷款申请人信息", modelType = RequestAccessConstants.SEARCH, desc = "通过证件类型和证件号获取个人客户信息")
	public BaseHttpParamsResp getCustomInfo(@RequestBody @Validated CustomerInfoReq customerInfoReq) throws Exception {
		logger.info("获取贷款申请人信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 验证数据是否同步（组织机构）
		customerOrgValidate.customerOrgValidate(customerInfoReq.getCustomerOrgId());
		// 获取个人客户信息
		CustomerInfoResp cus = customerInfoService.getCustomerInfo(customerInfoReq);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), cus);
		baseHttpParamsResp.createSign();
		logger.info("获取贷款申请人信息结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: getEnterpriseInfo 
	* @Description: TODO(获取贷款企业信息) 
	* @param @param customerInfoReq 企业客户传入参数
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getEnterpriseInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取贷款企业信息", modelType = RequestAccessConstants.SEARCH, desc = "通过证件类型和证件号获取企业客户信息")
	public BaseHttpParamsResp getEnterpriseInfo(@RequestBody @Validated CorpCustomerInfoReq customerInfoReq)
			throws Exception {
		logger.info("获取贷款企业信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		// 验证数据是否同步（组织机构）
		customerOrgValidate.customerOrgValidate(customerInfoReq.getCustomerOrgId());
		// 获取企业客户信息
		EnterpriseInfoResp cus = customerInfoService.getEnterpriseInfo(customerInfoReq);
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), cus);
		baseHttpParamsResp.createSign();
		logger.info("获取贷款企业信息结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: saveCustormInfo 
	* @Description: TODO(保存贷款申请人信息) 
	* @param @param personalCustomerReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/saveCustormInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "保存贷款申请人信息", modelType = RequestAccessConstants.UPDATE, desc = "保存个人客户信息")
	public BaseHttpParamsResp saveCustormInfo(@RequestBody @Validated PersonalCustomerReq personalCustomerReq)
			throws Exception {
		logger.info("保存贷款申请人信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		CustomerModifyResp resp = new CustomerModifyResp();
		// 验证数据是否同步（组织机构）
		customerOrgValidate.customerOrgValidate(personalCustomerReq.getCustomerOrgId());
		// 客户信息非空校验
		personalCustomerReq = customerOrgValidate.persionCustomerValidate(personalCustomerReq, "online");
		// 修改客户更新时间
		String updateTime = DateUtil.dateTimeFormat(new Date());
		customerInfoService.modifyCustomerPublicUpdateTimeById(updateTime, personalCustomerReq.getId());
		resp.setUpdateTime(updateTime);
		// 保存客户个人基本信息
		PersonalCustomerBaseInfo personal = personalCustomerReq.getPersonalCustomerBaseInfo();
		personal.setId(personalCustomerReq.getId());
		customerInfoService.savePersonalCustomerInfo(personal, "online");
		// 保存客户地址信息（registry：户籍work：工作live：居住）
		List<LocationOrContactBase> locationList = customerInfoService.saveCustomerLocationInfo(
				personalCustomerReq.getCustomerLocationInfo(), personalCustomerReq.getId(), "online");
		resp.setLocationList(locationList);
		// 保存客户联系信息（pMobile：个人手机号wPhone：办公电话）
		List<LocationOrContactBase> contactList = customerInfoService.saveCustomerContactInfo(
				personalCustomerReq.getCustomerContactInfo(), personalCustomerReq.getId(), "online");
		resp.setContactList(contactList);
		// 保存客户工作信息
		customerInfoService.saveCustomerJobDetailInfo(personalCustomerReq.getCustomerJobDetailInfo(),
				personalCustomerReq.getId(), "online");
		// 保存客户联系人信息
		customerInfoService.saveCustomerRelationshipInfo(personalCustomerReq.getCustomerRelationshipInfo(),
				personalCustomerReq.getId(), "online");
		// 保存客户配偶信息
		customerInfoService.saveCustomerMateInfo(personalCustomerReq.getCustomerMateInfo(),
				personalCustomerReq.getId(), "online", personal.getMartialStatus());
		// 保存资产信息
		customerInfoService.saveCustomerAssetInfo(personalCustomerReq.getCustomerAssetInfo(),
				personalCustomerReq.getId(), "online");
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		logger.info("保存贷款申请人信息结束");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: saveEnterpriseInfo 
	* @Description: TODO(保存贷款企业信息) 
	* @param @param enterpriseCustomerReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/saveEnterpriseInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "保存贷款企业信息", modelType = RequestAccessConstants.UPDATE, desc = "保存企业客户信息")
	public BaseHttpParamsResp saveEnterpriseInfo(@RequestBody @Validated EnterpriseCustomerReq enterpriseCustomerReq)
			throws Exception {
		logger.info("保存贷款企业信息开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		EnterpriseModifyResp resp = new EnterpriseModifyResp();
		// 验证数据是否同步（组织机构）
		customerOrgValidate.customerOrgValidate(enterpriseCustomerReq.getCustomerOrgId());
		// 客户信息非空校验
		customerOrgValidate.enterpriseCustomerValidate(enterpriseCustomerReq, "online");
		// 修改企业客户基本信息
		String updateTime = DateUtil.dateTimeFormat(new Date());
		customerInfoService.modifyCustomerPublicUpdateTimeById(updateTime, enterpriseCustomerReq.getId());
		resp.setUpdateTime(updateTime);
		// 保存客户地址信息（business:经营/办公地址register：注册地址）
		List<LocationOrContactBase> locationList = customerInfoService.saveCustomerLocationInfo(
				enterpriseCustomerReq.getCustomerLocationInfo(), enterpriseCustomerReq.getId(), "online");
		resp.setLocationList(locationList);
		// 保存企业客户联系人信息
		customerInfoService.saveCorporationCustomerContactinfo(
				enterpriseCustomerReq.getCorporationCustomerContactinfo(), enterpriseCustomerReq.getId(), "online");
		// 保存客户股东信息
		customerInfoService.saveCorporationCustomerShareholderInfo(
				enterpriseCustomerReq.getCorporationCustomerShareholderInfo(), enterpriseCustomerReq.getId(), "online");
		// 保存企业基本信息和法人信息
		CorporationBaseAndLrInfo base = enterpriseCustomerReq.getCorporationBaseAndLrInfo();
		base.setId(enterpriseCustomerReq.getId());
		customerInfoService.saveCorporationBaseAndLrInfo(base, "online");
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		logger.info("保存贷款企业信息结束");
		return baseHttpParamsResp;
	}
}
