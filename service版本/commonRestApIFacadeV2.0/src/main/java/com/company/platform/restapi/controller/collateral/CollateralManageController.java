package com.company.platform.restapi.controller.collateral;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

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
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.base.util.DateUtil;
import com.company.platform.restapi.model.collateral.CollateralCodeAndCustomerResp;
import com.company.platform.restapi.model.collateral.CollateralCommonReq;
import com.company.platform.restapi.model.collateral.CollateralDetailReq;
import com.company.platform.restapi.model.collateral.CollateralDetailResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.collateral.CollateralTemplateResp;
import com.company.platform.restapi.model.collateral.CollateralTypeReq;
import com.company.platform.restapi.model.collateral.CollateralWarrantInfo;
import com.company.platform.restapi.model.collateral.CustomerWarrant;
import com.company.platform.restapi.model.collateral.TemplateElement;
import com.company.platform.restapi.service.collateral.ICollateralManageService;
import com.company.platform.restapi.service.loan.ILoanCodeService;
import com.company.platform.restapi.validated.collateral.CollateralInfoValidate;
import com.company.platform.security.model.SecurityRole;
import com.company.platform.security.model.SecurityUser;
import com.github.pagehelper.PageHelper;

/** 
* @ClassName: CollateralManageController 
* @Description: TODO(押品管理) 
* @author 王雪 
* @date 2018年5月22日 上午9:10:05 
*  
*/

@RestController
@RequestMapping("appApi")
public class CollateralManageController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(CollateralManageController.class);

	@Autowired
	private ICollateralManageService collateralManageService;

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private CollateralInfoValidate collateralInfoValidate;

	@Resource
	private ILoanCodeService loanCodeService;

	/** 
	* @Title: getCollateralList 
	* @Description: TODO(获取押品信息列表) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsPageResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getCollateralList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取押品信息列表", modelType = RequestAccessConstants.SEARCH, desc = "通过登录人ID获得登录人或其所在组织机构登记的押品信息")
	public BaseHttpParamsPageResp getCollateralList(@RequestBody @Validated BaseHttpParamsPageAppReq req)
			throws Exception {
		logger.info("开始获取押品信息列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获取角色类型
		List<SecurityRole> roleList = user.getSecurityRole();
		boolean flag = false;
		if (roleList != null && roleList.size() > 0) {
			for (SecurityRole role : roleList) {
				if ("00".equals(role.getRoleType())) {// 角色类型为客户经理
					flag = true;
					break;
				}
			}
		}
		List<String> orgList = null;
		String userId = null;
		if (flag) {// 当前登录用户的角色类型为客户经理
			// 须符合条件：进件人id = 登录人id
			userId = user.getId();
		} else {
			// 获取关联组织机构
			orgList = organizationService.queryByVisibleOrgIds(user.getSecurityOrg().get(0).getId());
		}
		// 获得当前用户押品信息列表响应信息
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<CollateralInfo> list = collateralManageService.getCollateralList(orgList, userId);
		BaseHttpParamsPageResp resp = new BaseHttpParamsPageResp(list, true);
		resp.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
		resp.setResponseMessage(ResponseConstants.REQUEST_SUCCESS.getRetMsg());
		resp.createSign();
		logger.info("开始获取押品信息列表");
		return resp;
	}

	/** 
	* @Title: deleteCollateralInfo 
	* @Description: TODO(删除押品信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/deleteCollateralInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "删除押品信息", modelType = RequestAccessConstants.DELETE, desc = "根据押品id删除押品信息")
	public BaseHttpParamsResp deleteCollateralInfo(@RequestBody @Validated CollateralCommonReq req) throws Exception {
		logger.info("开始删除押品信息");
		// 押品状态校验
		collateralInfoValidate.collateralStatusValidate(req.getId());
		// 删除押品信息
		collateralManageService.deleteCollateralInfoById(req.getId());
		logger.info("结束删除押品信息");
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg());
		baseHttpParamsResp.createSign();
		return baseHttpParamsResp;
	}

	/** 
	* @Title: getCollateralCodeAndCustomer 
	* @Description: TODO(获取押品编号和权属人列表信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getCollateralCodeAndCustomer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取押品编号和权属人列表信息", modelType = RequestAccessConstants.SEARCH, desc = "根据登录者获取押品编号和权属人列表信息")
	public BaseHttpParamsResp getCollateralCodeAndCustomer(@RequestBody @Validated BaseHttpParamsAppReq req)
			throws Exception {
		logger.info("开始获取押品编号和权属人列表信息");
		// 获取押品编号
		String collateralNo = loanCodeService.getSysCode("COLLATERAL_TYPE", null);
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获取关联组织机构
		List<String> orgList = organizationService.queryByVisibleOrgIds(user.getSecurityOrg().get(0).getId());
		// 获取权属人列表信息
		List<CustomerWarrant> customerList = collateralManageService.getCustomerWarrantList(orgList);
		CollateralCodeAndCustomerResp resp = new CollateralCodeAndCustomerResp();
		resp.setCollateralNo(collateralNo);
		resp.setCustomerWarrant(customerList);
		logger.info("结束获取押品编号和权属人列表信息");
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		return baseHttpParamsResp;
	}

	/** 
	* @Title: getCollateralTemplateElement 
	* @Description: TODO(获取押品模板元素信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getCollateralTemplateElement", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取押品模板元素信息", modelType = RequestAccessConstants.SEARCH, desc = "根据押品类型获取押品模板元素信息")
	public BaseHttpParamsResp getCollateralTemplateElement(@RequestBody @Validated CollateralTypeReq req)
			throws Exception {
		logger.info("开始获取押品模板元素信息");
		String id = "collateral_" + req.getCollateralType();
		// 根据模板id获取标题
		String title = collateralManageService.getTitleByTemplateId(id);
		// 通过押品类型获取押品模板元素信息
		List<TemplateElement> list = collateralManageService.getCollateralTemplateElementById(id);
		CollateralTemplateResp resp = new CollateralTemplateResp();
		resp.setTitle(title);
		resp.setTemplateElement(list);
		logger.info("结束获取押品模板元素信息");
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		return baseHttpParamsResp;
	}

	/** 
	* @Title: getCollateralInfo 
	* @Description: TODO(获取押品详细信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getCollateralInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取押品详细信息", modelType = RequestAccessConstants.SEARCH, desc = "根据押品id获取押品详细信息")
	public BaseHttpParamsResp getCollateralInfo(@RequestBody @Validated CollateralCommonReq req) throws Exception {
		logger.info("开始获取押品详细信息");
		// 获取押品基础信息
		CollateralDetailResp resp = collateralManageService.getCollateralInfoById(req.getId());
		// 押品模板id
		String id = "collateral_" + resp.getCollateralType();
		// 根据模板id获取标题
		resp.setTitle(collateralManageService.getTitleByTemplateId(id));
		// 通过押品类型获取押品模板元素信息
		resp.setTemplateElement(collateralManageService.getCollateralTemplateElementById(id));
		// 通过押品id获取押品模板实际值列表
		resp.setTemplateValue(collateralManageService.getTemplateValueByCollateralId(req.getId()));
		logger.info("获取权属人列表");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获取关联组织机构
		List<String> orgList = organizationService.queryByVisibleOrgIds(user.getSecurityOrg().get(0).getId());
		// 获取权属人列表信息
		List<CustomerWarrant> customerList = collateralManageService.getCustomerWarrantList(orgList);
		// 获取权属人列表
		resp.setCustomerWarrant(customerList);
		logger.info("结束获取押品详细信息");
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), resp);
		baseHttpParamsResp.createSign();
		return baseHttpParamsResp;
	}

	/** 
	* @Title: saveCollateralInfo 
	* @Description: TODO(保存押品详细信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/saveCollateralInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "保存押品详细信息", modelType = RequestAccessConstants.INSERT, desc = "保存押品详细信息")
	public BaseHttpParamsResp saveCollateralInfo(@RequestBody @Validated CollateralDetailReq req) throws Exception {
		logger.info("开始保存押品详细信息");
		CollateralWarrantInfo info = new CollateralWarrantInfo();
		BeanUtils.copyProperties(req, info);
		// 数据校验
		collateralInfoValidate.collateralInfoValidate(info);
		logger.info("保存押品权证信息");
		// 保存押品基本信息
		String collateralId = info.getId();
		if (StringUtils.isEmpty(collateralId)) {// 新建
			// 完善数据
			collateralId = UUID.randomUUID().toString();
			info.setId(collateralId);
			// 获得当app登录用户
			SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			info.setRegisterStaffId(user.getId());
			info.setRegisterOrgId(user.getSecurityOrg().get(0).getId());
			info.setRegisterTime(DateUtil.dateTimeFormat(new Date()));
			info.setIsUsed("0");
			info.setDeleteStatus("0");
			// 新建押品权证信息
			collateralManageService.insertCollateralWarrantInfo(info);
		} else {// 更新
			// 校验押品id
			collateralInfoValidate.collateralStatusValidate(collateralId);
			// 更新押品权证信息
			collateralManageService.updateCollateralWarrantInfo(info);
		}
		// 保存押品模板实际值
		collateralManageService.saveTemplateValue(collateralId, req.getTemplateValue());
		logger.info("结束保存押品详细信息");
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg());
		baseHttpParamsResp.createSign();
		return baseHttpParamsResp;
	}
}
