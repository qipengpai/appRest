package com.company.platform.restapi.service.loan.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.dao.collateral.CollateralLoanMapper;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerLocationInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerPublicInfoMapper;
import com.company.platform.restapi.dao.guarantor.GuarantorInfoMapper;
import com.company.platform.restapi.dao.loan.LoanTempMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.dao.modelmanager.BusinessModelMapper;
import com.company.platform.restapi.model.basicdata.DicInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.loan.LoanApplyInfoForPersonal;
import com.company.platform.restapi.model.loan.LoanApplyInfoReq;
import com.company.platform.restapi.model.loan.LoanApplyInfoResp;
import com.company.platform.restapi.model.loan.LoanProductApplySyno;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;
import com.company.platform.restapi.model.modelmanager.BusColumnInfoModel;
import com.company.platform.restapi.model.modelmanager.BusTitleModelInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusDataInfo;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.restapi.service.loan.ILoanTempService;
import com.github.pagehelper.PageHelper;

/** 
* @ClassName: LoanTempServiceImpl 
* @Description: TODO(借贷任务暂存信息操作) 
* @author luyuchi
* @date 2018年1月25日 下午2:32:10 
*  
*/
@Service
public class LoanTempServiceImpl implements ILoanTempService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanTempServiceImpl.class);

	@Autowired
	private LoanTempMapper loanTempMapper;

	@Autowired
	private BusinessModelMapper businessModelMapper;

	@Autowired
	private CustomerPublicInfoMapper customerPublicInfoMapper;

	@Resource
	private ICustomerInfoService customerInfoService;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Autowired
	private CollateralLoanMapper collateralLoanMapper;

	@Autowired
	private GuarantorInfoMapper guarantorInfoMapper;

	@Autowired
	private CustomerLocationInfoMapper customerLocationInfoMapper;
	
	@Autowired
	private ProductInfoMapper productInfoMapper; 
	
	@Override
	public BaseHttpParamsPageResp queryLoanProductApplySynosByPage(BaseHttpParamsPageAppReq baseHttpParamsPageAppReq,
			String userId) throws BusinessException {
		if (baseHttpParamsPageAppReq.getPageNum() != null && baseHttpParamsPageAppReq.getPageNum().intValue() != 0) {
			PageHelper.startPage(baseHttpParamsPageAppReq.getPageNum(), baseHttpParamsPageAppReq.getPageSize());
		}
		// 获得暂存列表，用于列表显示
		List<LoanProductApplySyno> loanProductApplySynos = loanTempMapper.getLoanProductApplySynosByUserId(userId);
		Map<String, Object> customerTemp = new HashMap<String, Object>();

		for (LoanProductApplySyno loanProductApplySyno : loanProductApplySynos) {
			String loanProductApplyId = loanProductApplySyno.getLoanProductApplyId();
			LoanProductApplyInfo loanProductApplyInfo = loanTempMapper.getLoanApplyinfo(loanProductApplyId);
			if (loanProductApplyInfo == null) {
				logger.error("借贷申请不存在");
				continue;
			} else {
				String slUpdateTime = loanProductApplyInfo.getUpdateTime();
				CustomerPublicInfo customerPublicInfo = customerPublicInfoMapper
						.selectByPrimaryKey(loanProductApplyInfo.getCustomerId());
				if ("0".equals(customerPublicInfo.geteType())) {// 个人客户
					LoanApplyInfoForPersonal loanApplyInfo = new LoanApplyInfoForPersonal();

					loanApplyInfo.setUpdateTimeProductApply(slUpdateTime);
					Map<String, Object> busRecord = businessModelMapper.getBusRecordByBusId(loanProductApplyId);

					if (busRecord != null) {
						List<ModelBusDataInfo> busData = businessModelMapper.getModelBusDataInfo(loanProductApplyId);
						Map<String, Object> modelDataMap = new HashMap<String, Object>();
						modelDataMap.put("id", busRecord.get("busmodId"));
						if (!busData.isEmpty()) {
							List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
							for (ModelBusDataInfo d : busData) {
								Map<String, Object> item = new HashMap<String, Object>();
								item.put("code", d.getBusCode());
								item.put("val", d.getBusVal());
								data.add(item);
							}
							modelDataMap.put("data", data);
						}
						loanApplyInfo.setModelData(JSON.toJSONString(modelDataMap));
					}

					loanApplyInfo.setLoanProductApplyInfo(loanProductApplyInfo);

					loanApplyInfo.setCustomerType(customerPublicInfo.geteType());

					CustomerInfoResp cus = (CustomerInfoResp) customerTemp.get(loanProductApplyInfo.getCustomerId());

					if (cus == null) {
						CustomerInfoResp customer = new CustomerInfoResp();
						// 返回客户基本信息
						customer.setCustomerPublicInfo(customerPublicInfo);
						String customerId = customerPublicInfo.getId();
						// 返回客户联系信息
						customer.setCustomerContactInfo(customerInfoMapper.getCustomerContactInfo(customerId));
						// 返回客户地址信息
						customer.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
						// 返回客户工作信息
						customer.setCustomerJobDetailInfo(customerInfoMapper.getCustomerJobDetailInfo(customerId));
						// 返回客户联系人信息
						customer.setCustomerRelationshipInfo(
								customerInfoMapper.getCustomerRelationshipInfo(customerId));
						// 返回客户个人基本信息
						customer.setPersonalCustomerBaseInfo(customerInfoMapper.getPersonalCustomerInfo(customerId));
						// 返回客户配偶信息
						customer.setCustomerMateInfo(customerInfoMapper.getCustomerMateInfo(customerId));
						// 返回客户资产信息
						customer.setCustomerAssetInfo(customerInfoMapper.getCustomerAssetInfo(customerId));
						cus = customer;
						customerTemp.put(loanProductApplyInfo.getCustomerId(), cus);
					}

					CustomerPublicInfo cpi = cus.getCustomerPublicInfo();
					if (cpi != null) {
						loanApplyInfo.setUpdateTimeCustomer(cpi.getUpdateTime());
					}
					BeanUtils.copyProperties(cus, loanApplyInfo);
					Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanProductApplyInfo.getId());
					if (imgRecord != null) {
						loanApplyInfo.setImgModelId(imgRecord.get("modelId").toString());
					}
					loanApplyInfo
							.setCollateralInfo(collateralLoanMapper.showCollateralListByApplyId(loanProductApplyId));
					loanApplyInfo.setGuarantorInfo(guarantorInfoMapper.showGuarantorInByApplyId(loanProductApplyId));
					loanProductApplySyno.setApplyDetail(loanApplyInfo);
				}
//				} else {// 企业客户
//					LoanApplyInfoForEnterprise loanApplyInfo = new LoanApplyInfoForEnterprise();
//
//					loanApplyInfo.setUpdateTimeProductApply(slUpdateTime);
//					Map<String, Object> busRecord = businessModelMapper.getBusRecordByBusId(loanProductApplyId);
//
//					if (busRecord != null) {
//						List<ModelBusDataInfo> busData = businessModelMapper.getModelBusDataInfo(loanProductApplyId);
//						Map<String, Object> modelDataMap = new HashMap<String, Object>();
//						modelDataMap.put("id", busRecord.get("busmodId"));
//						if (!busData.isEmpty()) {
//							List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//							for (ModelBusDataInfo d : busData) {
//								Map<String, Object> item = new HashMap<String, Object>();
//								item.put("code", d.getBusCode());
//								item.put("val", d.getBusVal());
//								data.add(item);
//							}
//							modelDataMap.put("data", data);
//						}
//						loanApplyInfo.setModelData(JSON.toJSONString(modelDataMap));
//					}
//
//					loanApplyInfo.setLoanProductApplyInfo(loanProductApplyInfo);
//
//					loanApplyInfo.setCustomerType(customerPublicInfo.geteType());
//
//					EnterpriseInfoResp cus = (EnterpriseInfoResp) customerTemp
//							.get(loanProductApplyInfo.getCustomerId());
//
//					if (cus == null) {
//						EnterpriseInfoResp customer = new EnterpriseInfoResp();
//						// 返回客户基本信息
//						customer.setCustomerPublicInfo(customerPublicInfo);
//						String customerId = customerPublicInfo.getId();
//						// 返回客户地址信息
//						customer.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
//						// 返回企业客户联系人信息
//						customer.setCorporationCustomerContactinfo(
//								customerInfoMapper.getCorporationCustomerContactinfo(customerId));
//						// 返回企业客户股东信息
//						customer.setCorporationCustomerShareholderInfo(
//								customerInfoMapper.getCorporationCustomerShareholderInfo(customerId));
//						// 返回企业客户法人信息
//						customer.setCorporationBaseAndLrInfo(
//								customerInfoMapper.getCorporationBaseAndLrInfo(customerId));
//						cus = customer;
//						customerTemp.put(loanProductApplyInfo.getCustomerId(), cus);
//					}
//					CustomerPublicInfo cpi = cus.getCustomerPublicInfo();
//					if (cpi != null) {
//						loanApplyInfo.setUpdateTimeCustomer(cpi.getUpdateTime());
//					}
//					BeanUtils.copyProperties(cus, loanApplyInfo);
//
//					Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanProductApplyInfo.getId());
//					if (imgRecord != null) {
//						loanApplyInfo.setImgModelId(imgRecord.get("modelId").toString());
//					}
//					loanApplyInfo
//							.setCollateralInfo(collateralLoanMapper.showCollateralListByApplyId(loanProductApplyId));
//					loanApplyInfo.setGuarantorInfo(guarantorInfoMapper.showGuarantorInByApplyId(loanProductApplyId));
//					loanProductApplySyno.setApplyDetail(loanApplyInfo);
//				}
			}

		}

		BaseHttpParamsPageResp baseHttpParamsPageResp = new BaseHttpParamsPageResp(loanProductApplySynos,
				baseHttpParamsPageAppReq.getPageNum() != null && baseHttpParamsPageAppReq.getPageNum().intValue() != 0);

		baseHttpParamsPageResp.createSign();

		return baseHttpParamsPageResp;
	}

	/*
	 * (非 Javadoc) <p>Title: searchTempInfoById</p> <p>Description: </p>
	 * 
	 * @param req
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanTempService#
	 * searchTempInfoById(com.company.platform.restapi.model.loan.
	 * LoanApplyInfoReq)
	 */
	@Transactional
	@Override
	public LoanApplyInfoResp searchTempInfoById(LoanApplyInfoReq req, String type, String flag ) throws BusinessException {

		LoanApplyInfoResp resp = new LoanApplyInfoResp();
		String loanProductApplyId = req.getLoanProductApplyId();

		if (loanProductApplyId != null) {
			LoanProductApplyInfo loanProductApplyInfo = loanTempMapper.getLoanApplyinfo(loanProductApplyId);
			if(true) {
				//借贷申请信息编辑
				if(loanProductApplyInfo.getApplyAmount() == null) {
					loanProductApplyInfo.setApplyAmount("");
				}
				if(loanProductApplyInfo.getTermCount() == null) {
					loanProductApplyInfo.setTermCount("");
				}
				if(loanProductApplyInfo.getTermUnit() == null) {
					loanProductApplyInfo.setTermUnit("");
				}
				if(loanProductApplyInfo.getRepayType() == null) {
					loanProductApplyInfo.setRepayType("");
				}
				if(loanProductApplyInfo.getMonthlyPayments() == null) {
					loanProductApplyInfo.setMonthlyPayments("");
				}
				//还款方式
				Map<String, Object> repayTypeMap = new LinkedHashMap<String, Object>();
				List<Map<String,Object>> repayTypeList = productInfoMapper.getRepayType();
				repayTypeMap.put("repayTypeList", repayTypeList);
				resp.setRepayType(JSON.toJSONString(repayTypeMap));
				
				//编辑客户信息
				CustomerPublicInfo customerPublicInfo = customerPublicInfoMapper
						.selectByPrimaryKey(loanProductApplyInfo.getCustomerId());
				resp.setCustomerPublicInfo(customerPublicInfo);
				//获取户籍地址
				String address = customerLocationInfoMapper.selectByCustomerId(loanProductApplyInfo.getCustomerId());
				if(address != null && !address.equals("")) {
					resp.setAddress(address);	
				} else {
					resp.setAddress("");
				}
				
				//获取组织机构经理名称(进件渠道)
				String orgName = loanTempMapper.getOrgName(loanProductApplyId);
				if(orgName != null && !orgName.equals("")) {
					resp.setOrgName(orgName);	
				} else {
					resp.setOrgName("");
				}	
				
				LoanProductInfo productInfo = productInfoMapper.queryByPrimaryId(loanProductApplyInfo.getLoanProductId());
				//获取借贷金额范围
				resp.setSingleMinAmount(productInfo.getSingleMinAmount());
				resp.setSingleMaxAmount(productInfo.getSingleMaxAmount());
				
				//获取借贷期限范围
				resp.setSingleMonths(productInfo.getSingleMonths());
				
				try {
//					//String updateTimeProductApply = req.getUpdateTimeProductApply();
//					String slUpdateTime = loanProductApplyInfo.getUpdateTime();
//					if (StringUtils.isNotBlank(updateTimeProductApply) && StringUtils.isNotBlank(slUpdateTime)) {
//						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						Date aLUpdateTime = f.parse(updateTimeProductApply);
//						Date sLUpdateTime = f.parse(slUpdateTime);
//						if (aLUpdateTime.getTime() < sLUpdateTime.getTime()) {
//							resp.setUpdateTimeProductApply(slUpdateTime);
				Map<String, Object> busRecord = businessModelMapper.getBusRecordByBusId(loanProductApplyId);

				if (busRecord != null) {
					List<ModelBusDataInfo> busData = businessModelMapper
							.getModelBusDataInfo(loanProductApplyId);
					Map<String, Object> modelDataMap = new HashMap<String, Object>();
					modelDataMap.put("id", busRecord.get("busmodId"));
					if (!busData.isEmpty()) {
						List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
						for (ModelBusDataInfo d : busData) {
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("code", d.getBusCode());
							item.put("val", d.getBusVal());
							data.add(item);
						}
						modelDataMap.put("data", data);
					}
					resp.setModelData(JSON.toJSONString(modelDataMap));
				}
				
				//模型字典及规则
				Map<String, Object> productModelDataMap = new LinkedHashMap<String, Object>();
				Map<String, Object> productModelMap = businessModelMapper.getProductModelByProductId(loanProductApplyInfo.getLoanProductId(), "BUM");
				String productModelId =
							businessModelMapper.getProductModelV(productModelMap.get("modelCode").toString(), productModelMap.get("modelVersion").toString());
					if(productModelId != null) {
						//产品模型id
						productModelDataMap.put("productModelId", productModelId);
						List<Map<String, Object>> productTitleDataList = new ArrayList<Map<String, Object>>();
						List<BusTitleModelInfo> productModelTitles = businessModelMapper.getProductModelByModelId(productModelId);
						if(!productModelTitles.isEmpty()) {
							for(BusTitleModelInfo productModelTitle : productModelTitles) {
								Map<String, Object> productTitleDataMap = new LinkedHashMap<String, Object>();
								productTitleDataMap.put("id", productModelTitle.getId());
								productTitleDataMap.put("code", productModelTitle.getCode());
								productTitleDataMap.put("name", productModelTitle.getName());
								productTitleDataMap.put("sotr", productModelTitle.getSort());
								productTitleDataMap.put("modelId", productModelTitle.getModelId());
								List<Map<String, Object>> productModelColumnList = new ArrayList<Map<String, Object>>();
								List<BusColumnInfoModel> productModelColumns = 
										businessModelMapper.getProductModelBytitleId(productModelTitle.getId());
								if(!productModelColumns.isEmpty()) {
									for( BusColumnInfoModel productModelColumn : productModelColumns) {
										Map<String, Object> productModelColumnMap = new LinkedHashMap<String, Object>();
										productModelColumnMap.put("id", productModelColumn.getId());
										productModelColumnMap.put("code", productModelColumn.getCode());
										productModelColumnMap.put("name", productModelColumn.getName());
										productModelColumnMap.put("inputType", productModelColumn.getInputType());
										productModelColumnMap.put("necessity", productModelColumn.getNecessity());
										productModelColumnMap.put("defValue", productModelColumn.getDefValue());
										productModelColumnMap.put("dataType", productModelColumn.getDataType());
										if(productModelColumn.getLength() != null) {
											productModelColumnMap.put("length", productModelColumn.getLength());	
										} else {
											productModelColumnMap.put("length", "");
										}
										productModelColumnMap.put("validation", productModelColumn.getValidation());
										productModelColumnMap.put("validationMsg", productModelColumn.getValidationMsg());
										productModelColumnMap.put("sort", productModelColumn.getSort());
										productModelColumnMap.put("titleId", productModelColumn.getTitleId());
										List<Map<String, Object>> dataRangDateList = new ArrayList<Map<String, Object>>();
										List<DicInfo> dataRangLists = 
												businessModelMapper.getDataRangItemByRangeCode(productModelColumn.getDataType());
										for(DicInfo dataRangList : dataRangLists) {
											Map<String, Object> typeMap = new LinkedHashMap<String, Object>();
											typeMap.put("code", dataRangList.getCode());
											typeMap.put("name", dataRangList.getName());
											//productModelColumnMap.put("dataRangMap", typeMap);
											dataRangDateList.add(typeMap);
										}
										productModelColumnMap.put("dataRangDateList", dataRangDateList);
										productModelColumnList.add(productModelColumnMap);
									}
								}
								productTitleDataMap.put("productModelColumnList", productModelColumnList);
								productTitleDataList.add(productTitleDataMap);
							}
							productModelDataMap.put("productTitleDataList", productTitleDataList);
						}
						resp.setProductModelData(JSON.toJSONString(productModelDataMap));
					}
				
				// 组装返回数据
				resp.setLoanProductApplyInfo(loanProductApplyInfo);
//							resp.setCollateralInfo(collateralLoanMapper.showCollateralListByApplyId(loanProductApplyId));
//							resp.setGuarantorInfo(guarantorInfoMapper.showGuarantorInByApplyId(loanProductApplyId));
//						}
//					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				resp.setCustomerType(customerPublicInfo != null ? customerPublicInfo.geteType() : "");
				Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanProductApplyInfo.getId());
				if (imgRecord != null) {
					resp.setImgModelId(imgRecord.get("modelId").toString());
				}
			}
		}
		return resp;
	}

}
