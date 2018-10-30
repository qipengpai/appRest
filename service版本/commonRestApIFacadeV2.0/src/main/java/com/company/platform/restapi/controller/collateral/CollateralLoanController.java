package com.company.platform.restapi.controller.collateral;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.sys.IOrganizationService;
import com.company.platform.restapi.model.collateral.CollateralCommonReq;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.collateral.CollateralIntroduceReq;
import com.company.platform.restapi.model.collateral.CollateralLoanApplyReq;
import com.company.platform.restapi.service.collateral.ICollateralLoanService;
import com.company.platform.restapi.validated.collateral.CollateralInfoValidate;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: CollateralManageController 
* @Description: TODO(押品管理) 
* @author 王雪 
* @date 2018年5月22日 上午9:10:05 
*  
*/

@RestController
@RequestMapping("appApi")
public class CollateralLoanController extends BaseController {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(CollateralLoanController.class);

	@Autowired
	private ICollateralLoanService collateralLoanService;

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private CollateralInfoValidate collateralInfoValidate;

	/** 
	* @Title: getCollateralListByApplyId 
	* @Description: TODO(获取借贷申请时可引入的押品列表信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@RequestMapping(path = "/getCollateralListByApplyId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "获取借贷申请时可引入的押品列表信息", modelType = RequestAccessConstants.SEARCH, desc = "根据借贷申请id获取借贷申请时可引入的押品列表信息")
	public BaseHttpParamsResp getCollateralListByApplyId(@RequestBody @Validated CollateralLoanApplyReq req)
			throws Exception {
		logger.info("开始获取借贷申请时可引入的押品列表信息");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获取关联组织机构
		List<String> orgList = organizationService.queryByVisibleOrgIds(user.getSecurityOrg().get(0).getId());
		// 获取借贷申请时可引入的押品列表信息
		List<CollateralInfo> list = collateralLoanService.getCollateralListByApplyId(req.getId(), orgList);
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), list);
		baseHttpParamsResp.createSign();
		logger.info("结束获取借贷申请时可引入的押品列表信息");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: introduceCollateralInfo 
	* @Description: TODO(引入押品信息) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/introduceCollateralInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "引入押品信息", modelType = RequestAccessConstants.INSERT, desc = "为借贷申请引入押品信息")
	public BaseHttpParamsResp introduceCollateralInfo(@RequestBody @Validated CollateralIntroduceReq req)
			throws Exception {
		logger.info("开始引入押品信息");
		// 校验押品基本状态
		Map<String, Object> map = collateralInfoValidate.collateralWarrantValidate(req.getId());
		Map<String, Object> relMap = collateralInfoValidate.loanCollateralRelValidate(req.getId());
		if (relMap != null) {
			if ("0".equals(relMap.get("warrantStatus").toString())) {
				logger.info("押品已引入");
				throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_INTRODUCED.getRetCode(),
						ResponseConstants.COLLATERAL_WARRANT_INTRODUCED.getRetMsg());
			}
		}
		logger.info("校验借贷申请存在性、押品所属人");
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 获取关联组织机构
		List<String> orgList = organizationService.queryByVisibleOrgIds(user.getSecurityOrg().get(0).getId());
		// 获取借贷申请时可引入的押品列表信息
		List<CollateralInfo> list = collateralLoanService.getCollateralListByApplyId(req.getApplyId(), orgList);
		boolean flag = false;
		if (list != null && list.size() > 0) {
			for (CollateralInfo info : list) {
				if (req.getId().equals(info.getId())) {
					flag = true;
				}
			}
		}
		if (!flag) {// 当前押品权属人错误
			throw new BusinessException(ResponseConstants.WARRANT_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.WARRANT_CUSTOMER_ERROR.getRetMsg());
		}
		// 引入押品
		collateralLoanService.insertLoanCollateralRel(req.getApplyId(), req.getId(), map.get("esValue").toString());
		// 获取当前押品信息
		CollateralInfo info = collateralLoanService.getLoanCollateralInfo(req.getId());
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg(), info);
		baseHttpParamsResp.createSign();
		logger.info("结束引入押品信息");
		return baseHttpParamsResp;
	}

	/** 
	* @Title: cancelIntroduceCollateral 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param req
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@Transactional
	@RequestMapping(path = "/cancelIntroduceCollateral", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "取消引入押品信息", modelType = RequestAccessConstants.UPDATE, desc = "为借贷申请取消引入押品信息")
	public BaseHttpParamsResp cancelIntroduceCollateral(@RequestBody @Validated CollateralCommonReq req)
			throws Exception {
		logger.info("开始取消引入押品信息");
		// 校验押品关联状态(已入库/已取消引入)
		Map<String, Object> relMap = collateralInfoValidate.collateralRelValidate(req.getId());
		// 取消引入
		collateralLoanService.deleteLoanCollateralRel(req.getId(), relMap.get("loanApplyId").toString());
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.SUCCESS.getRetCode(),
				ResponseConstants.SUCCESS.getRetMsg());
		baseHttpParamsResp.createSign();
		logger.info("结束取消引入押品信息");
		return baseHttpParamsResp;
	}
}
