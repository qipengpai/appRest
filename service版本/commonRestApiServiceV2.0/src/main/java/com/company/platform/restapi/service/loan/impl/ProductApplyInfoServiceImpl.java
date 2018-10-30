package com.company.platform.restapi.service.loan.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.dao.guarantor.GuarantorInfoMapper;
import com.company.platform.restapi.dao.loan.ProductApplyInfoMapper;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.ProductApplyInfoReq;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;
import com.company.platform.restapi.service.loan.IProductApplyInfoService;

/**
 * @ClassName: ProductApplyInfoServiceImp
 * @Description: TODO(借贷申请信息)
 * @author yangxu
 * @date 2018年1月25日 下午4:02:21
 * 
 */
@Service
public class ProductApplyInfoServiceImpl implements IProductApplyInfoService {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(ProductApplyInfoServiceImpl.class);

	@Autowired
	private ProductApplyInfoMapper productApplyInfoMapper;

	@Autowired
	private GuarantorInfoMapper guarantorInfoMapper;

	/*
	 * (非 Javadoc) <p>Title: delTempInfo</p> <p>Description: </p>
	 * 
	 * @param productApplyInfoReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.IProductApplyInfoService#
	 * delTempInfo(com.company.platform.restapi.model.loan. ProductApplyInfoReq)
	 */
	@Override
	public boolean delTempInfo(ProductApplyInfoReq productApplyInfoReq) throws BusinessException {
		String id = productApplyInfoReq.getLoanProductApplyId();
		if (StringUtils.isNotBlank(id)) {
			int i = productApplyInfoMapper.delProductApplyInfo(id);
			return i > 0;
		} else {
			return false;
		}
	}

	@Override
	public void modifyLoanProductApply(LoanProductApplyInfo loanProductApplyInfo) throws BusinessException {
		logger.info("修改借贷申请信息");
		if ("" .equals( loanProductApplyInfo.getMonthlyPayments())) {
			loanProductApplyInfo.setMonthlyPayments(null);
		}else{
			loanProductApplyInfo.setMonthlyPayments(loanProductApplyInfo.getMonthlyPayments().toString().replace(",", ""));
		}
		productApplyInfoMapper.modifyLoanProductApply(loanProductApplyInfo);
	}

	@Override
	public void saveLoanGuarantorInfo(List<GuarantorInfo> list, String applyId) {
		logger.info("保存担保人信息");
		// 查询当前申请的担保人id
		String ids = guarantorInfoMapper.selectGuarantorInfoIds(applyId);
		// 删除担保人
		if (StringUtils.isNotEmpty(ids)) {
			guarantorInfoMapper.deleteGuarantorInfo(ids);
		}

		// 删除担保人关联信息
		guarantorInfoMapper.deleteLoanProductApplyGuarantor(applyId);
		if (list != null && list.size() > 0) {
			for (GuarantorInfo guarantor : list) {
				if ("".equals(guarantor.getIncome())) {
					guarantor.setIncome(null);
				}
				guarantor.setId(UUID.randomUUID().toString());
				guarantorInfoMapper.insertGuarantorInfo(guarantor);
				guarantorInfoMapper.insertLoanProductApplyGuarantor(guarantor, applyId);
			}
		}

	}
}
