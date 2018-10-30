package com.company.platform.restapi.controller.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;
import com.company.platform.restapi.model.basicdata.ProductInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.loan.code.LoanCodeReq;
import com.company.platform.restapi.model.loan.code.LoanCodeResp;
import com.company.platform.restapi.service.index.IndexService;
import com.company.platform.restapi.service.loan.ILoanCodeService;
import com.company.platform.restapi.validated.loan.LoanProductValidate;

/**
 * 首页初始化 产品信息、会员信息
* @ClassName: IndeController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 下午3:46:36 
*
 */
@RestController
@RequestMapping("/wechat")
public class IndexController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IndexService indexService;
	@Autowired
	private IDataDictionaryService dataDictionaryService;
	@Autowired
	private LoanProductValidate loanProductValidate;
	@Resource
	private ILoanCodeService loanCodeService;

	/**
	 * 获取产列表信息
	* @Title: uploadLoanMaterial 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/getProductList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
	public Map<String,Object> getProductList(String orgId) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Map<String, Object>> allData = dataDictionaryService.getDataMap();
		result.put("status", 200);
		//产品
		result.put("productList",indexService.getIndexProductList(orgId));
		//身份证
		result.put("credentialType",allData.get("credentialType").get("item"));
		//人员类型
		result.put("customerType",allData.get("customerType").get("item"));
		//termUnit 借贷期限
		result.put("termUnit",allData.get("termUnit").get("item"));
		return result;
	}
	
	/**
	 * 获取客户数据
	* @Title: getCsustomerPublicInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param idCard
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/getCsustomerPublicInfo",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
	public Map<String,Object> getCsustomerPublicInfo(String idCard) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 200);
		result.put("member", indexService.getCsustomerPublicInfo(idCard));
		return result;
	}

	/**
	 * 获取借贷编码
	* @Title: getLoanCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param loanCodeReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/getProductCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getLoanCode(String userId,String productId) throws Exception {
		this.logger.info("获取贷款申请编码开始");
		Map<String,Object> result  = new HashMap<String,Object>();
		// 验证产品的存在性与生效性，获取借贷编码类型
		String loanCodeType = loanProductValidate.loanProductValidate(productId);
		// 获取当前用户所在的组织机构信息（机构id和机构编码）
		Map<String, String> org = indexService.getOrgIdByUserId(userId);
//		// 验证用户权限
//		loanProductValidate.loanProductOrgRelValidate(productId, org.get("orgId").toString());
		// 获取贷款申请编码
		String code = loanCodeService.getSysCode(loanCodeType + "_LOAN",
				!org.containsKey("orgCode") || org.get("orgCode") == null ? "" : org.get("orgCode").toString());
		result.put("status", "200");
		result.put("code", code);
		this.logger.info("获取贷款申请编码结束");
		return result;
	}

}
