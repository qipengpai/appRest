package com.company.platform.restapi.service.collateral.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.dao.collateral.CollateralLoanMapper;
import com.company.platform.restapi.dao.loan.ProductApplyInfoMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.service.collateral.ICollateralLoanService;

/** 
* @ClassName: CollateralLoanServiceImpl 
* @Description: TODO(贷款押品servise实现类) 
* @author 王雪 
* @date 2018年5月24日 下午5:44:54 
*  
*/
@Service
public class CollateralLoanServiceImpl implements ICollateralLoanService {

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	CollateralLoanMapper collateralLoanMapper;

	@Autowired
	ProductApplyInfoMapper productApplyInfoMapper;

	/*
	 * (非 Javadoc) <p>Title: getCollateralListByApplyId</p> <p>Description: </p>
	 * 
	 * @param applyId
	 * 
	 * @param orgList
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralLoanService#
	 * getCollateralListByApplyId(java.lang.String, java.util.List)
	 */
	@Override
	public List<CollateralInfo> getCollateralListByApplyId(String applyId, List<String> orgList)
			throws BusinessException {
		LoanProductApplyEntity apply = productInfoMapper.getLoanProductApplyById(applyId);
		if (apply == null) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_APPLY_NOT_EXIST.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_APPLY_NOT_EXIST.getRetMsg());
		}
		List<CollateralInfo> list = new ArrayList<CollateralInfo>();
		if (orgList != null && orgList.size() > 0) {
			// 获取借贷申请时可引入的押品列表信息
			list = collateralLoanMapper.getCollateralListByApplyId(apply.getCustomerId(), orgList);
		}
		return list;
	}

	/*
	 * (非 Javadoc) <p>Title: insertLoanCollateralRel</p> <p>Description: </p>
	 * 
	 * @param applyId
	 * 
	 * @param id
	 * 
	 * @param esValue
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralLoanService#
	 * insertLoanCollateralRel(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public int insertLoanCollateralRel(String applyId, String id, String esValue) {
		// 获取排序编号
		Short nextEOrder = collateralLoanMapper.selectLoanApplyNextEOrder(applyId);
		if (nextEOrder == null || nextEOrder == 0 || nextEOrder > 99) {
			nextEOrder = 1;
		}
		// 引入押品
		collateralLoanMapper.insertLoanCollateralRel(applyId, id, esValue, nextEOrder.toString());
		// 更新借贷申请时间
		return productApplyInfoMapper.updateLoanProductApplyTimeById(applyId);
	}

	/*
	 * (非 Javadoc) <p>Title: getLoanCollateralInfo</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralLoanService#
	 * getLoanCollateralInfo(java.lang.String)
	 */
	@Override
	public CollateralInfo getLoanCollateralInfo(String id) {
		return collateralLoanMapper.getLoanCollateralInfo(id);
	}

	/*
	 * (非 Javadoc) <p>Title: deleteLoanCollateralRel</p> <p>Description: </p>
	 * 
	 * @param collateralId
	 * 
	 * @param loanApplyId
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralLoanService#
	 * deleteLoanCollateralRel(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteLoanCollateralRel(String collateralId, String loanApplyId) {
		int i = collateralLoanMapper.deleteLoanCollateralRel(collateralId);
		if (i > 0) {// 取消引入成功，更新借贷申请时间
			productApplyInfoMapper.updateLoanProductApplyTimeById(loanApplyId);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: getEstimateTotalValue</p> <p>Description: </p>
	 * 
	 * @param loanApplyId
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralLoanService#
	 * getEstimateTotalValue(java.lang.String)
	 */
	@Override
	public BigDecimal getEstimateTotalValue(String loanApplyId) {
		return collateralLoanMapper.getEstimateTotalValue(loanApplyId);
	}
}
