package com.company.platform.restapi.service.loan.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.dao.collateral.CollateralLoanMapper;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.guarantor.GuarantorInfoMapper;
import com.company.platform.restapi.dao.loan.LoanHandledMapper;
import com.company.platform.restapi.dao.loan.LoanTempMapper;
import com.company.platform.restapi.dao.modelmanager.BusinessModelMapper;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.loan.LoanProductApplySyno;
import com.company.platform.restapi.model.loan.handled.ImageInfo;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoReq;
import com.company.platform.restapi.model.loan.handled.LoanHandledInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusDataInfo;
import com.company.platform.restapi.service.loan.ILoanHandledService;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: LoanHandledServiceImpl
 * @Description: TODO(已办任务操作)
 * @author luyuchi
 * @date 2018年1月26日 下午1:52:38
 * 
 */
@Service
public class LoanHandledServiceImpl implements ILoanHandledService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanTempServiceImpl.class);

	@Autowired
	private LoanTempMapper loanTempMapper;

	@Autowired
	private LoanHandledMapper loanHandledMapper;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Autowired
	private BusinessModelMapper businessModelMapper;

	@Autowired
	private CollateralLoanMapper collateralLoanMapper;
	
	@Autowired
	private GuarantorInfoMapper guarantorInfoMapper;

	@Value("${image.app_ip}")
	private String appIp;

	@Override
	public BaseHttpParamsPageResp queryLoanHandledInfoByPage(BaseHttpParamsPageAppReq baseHttpParamsPageAppReq,
			String userId) {
		PageHelper.startPage(baseHttpParamsPageAppReq.getPageNum(), baseHttpParamsPageAppReq.getPageSize());
		// 获得已办任务列表，用于列表显示
		List<LoanProductApplySyno> loanProductApplySynos = loanHandledMapper.getLoanHandledInfoByUserIdNew(userId);
		BaseHttpParamsPageResp baseHttpParamsPageResp = new BaseHttpParamsPageResp(loanProductApplySynos, true);
		return baseHttpParamsPageResp;
	}

	@Transactional
	@Override
	public LoanHandledInfoResp searchHandleInfoById(LoanHandledInfoReq req) throws BusinessException {
		LoanHandledInfoResp resp = new LoanHandledInfoResp();
		String loanProductApplyId = req.getLoanProductApplyId();
		if (loanProductApplyId != null) {
			LoanProductApplyInfo loanProductApplyInfo = loanTempMapper.getLoanApplyinfo(loanProductApplyId);
			if (loanProductApplyInfo == null) {
				logger.error("借贷申请不存在");
				throw new BusinessException(ResponseConstants.NOT_EXIT_LOAN_APPLY_ERROR.getRetCode(),
						ResponseConstants.NOT_EXIT_LOAN_APPLY_ERROR.getRetMsg());
			} else {
				// 返回借贷申请信息
				resp.setLoanProductApplyInfo(loanProductApplyInfo);
				// 返回业务模型信息
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
					resp.setModelData(JSON.toJSONString(modelDataMap));
				}
				// 返回影像模型信息
				Map<String, Object> imgRecord = loanTempMapper.getImgRecordByBusId(loanProductApplyId);
				if (imgRecord != null) {
					List<ImageInfo> imageList = loanTempMapper.selectImageInfoByBusinessId(loanProductApplyId);
					Map<String, Object> modelDataMap = new HashMap<String, Object>();
					modelDataMap.put("modelId", imgRecord.get("modelId"));
					if (imageList != null && imageList.size() > 0) {
						List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
						for (ImageInfo image : imageList) {
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("classId", image.getClassId());
							String url = appIp + "/image/showImage?filePath=" + image.getFilePath() + "&fileName="
									+ image.getFileName();
							item.put("url", url);
							item.put("id", image.getId());
							data.add(item);
						}
						modelDataMap.put("data", data);
					}
					resp.setImageInfo(JSON.toJSONString(modelDataMap));
				}
				String customerId = loanProductApplyInfo.getCustomerId();
				// 获取返回客户数据
				// 客户基本信息
				CustomerPublicInfo cus = customerInfoMapper.getCustomerPublicInfoById(customerId);
				resp.setCustomerPublicInfo(cus);
				// 返回客户地址信息
				resp.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
				if ("0".equals(cus.geteType())) {// 个人客户
					// 返回客户联系信息
					resp.setCustomerContactInfo(customerInfoMapper.getCustomerContactInfo(customerId));
					// 返回客户工作信息
					resp.setCustomerJobDetailInfo(customerInfoMapper.getCustomerJobDetailInfo(customerId));
					// 返回客户联系人信息
					resp.setCustomerRelationshipInfo(customerInfoMapper.getCustomerRelationshipInfo(customerId));
					// 返回客户个人基本信息
					resp.setPersonalCustomerBaseInfo(customerInfoMapper.getPersonalCustomerInfo(customerId));
					// 返回客户配偶信息
					resp.setCustomerMateInfo(customerInfoMapper.getCustomerMateInfo(customerId));
					// 返回客户资产信息
					resp.setCustomerAssetInfo(customerInfoMapper.getCustomerAssetInfo(customerId));
				} else {
					// 返回企业客户联系人信息
					resp.setCorporationCustomerContactinfo(
							customerInfoMapper.getCorporationCustomerContactinfo(customerId));
					// 返回企业客户股东信息
					resp.setCorporationCustomerShareholderInfo(
							customerInfoMapper.getCorporationCustomerShareholderInfo(customerId));
					// 返回企业客户法人信息
					resp.setCorporationBaseAndLrInfo(customerInfoMapper.getCorporationBaseAndLrInfo(customerId));
				}
				// 返回引入的押品列表信息
				resp.setCollateralInfo(collateralLoanMapper.showCollateralListByApplyId(loanProductApplyId));
				// 返回担保人信息
				resp.setGuarantorInfo(guarantorInfoMapper.showGuarantorInByApplyId(loanProductApplyId));
			}
		}
		return resp;
	}

}
