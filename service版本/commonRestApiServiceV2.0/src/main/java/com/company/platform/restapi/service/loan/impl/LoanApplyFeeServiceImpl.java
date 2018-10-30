package com.company.platform.restapi.service.loan.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.model.repayment.ReqRepaymentModel;
import com.company.platform.base.model.repayment.RespRepaymentModel;
import com.company.platform.base.util.RepaymentUtil;
import com.company.platform.restapi.dao.loan.FeeMapper;
import com.company.platform.restapi.dao.loan.ProductApplyInfoMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.dao.loan.SysDictionaryMapper;
import com.company.platform.restapi.model.loan.FeeCondition;
import com.company.platform.restapi.model.loan.LoanProductApplyFeeInfo;
import com.company.platform.restapi.model.loan.ProductApplyInfoResp;
import com.company.platform.restapi.model.loan.fee.LoanApplyFeeInfo;
import com.company.platform.restapi.model.loan.fee.LoanProductApplyFeeReq;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;
import com.company.platform.restapi.service.loan.ILoanApplyFeeService;

/**
 * @ClassName: LoanApplyFeeServiceImpl
 * @Description: TODO(创建费用信息)
 * @author yangxu
 * @date 2018年1月30日 下午4:17:38
 * 
 */
@Service
public class LoanApplyFeeServiceImpl implements ILoanApplyFeeService {

	@Autowired
	private ProductApplyInfoMapper productApplyInfoMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private FeeMapper feeMapper;
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	
	/*
	 * (非 Javadoc) <p>Title: getLoanApplyFeeInfo</p> <p>Description: </p>
	 * 
	 * @param loanProductApplyInfoReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.IProductApplyInfoService#
	 * addLoanApplyFeeInfo(com.company.platform.restapi.model.loan.
	 * LoanProductApplyInfoReq)
	 */
	@Override
	public List<LoanProductApplyFeeInfo> getLoanApplyFeeInfo(LoanProductApplyFeeReq loanProductApplyFeeReq)
			throws BusinessException {

		String productApplyId = loanProductApplyFeeReq.getProductApplyId();
		String amount = loanProductApplyFeeReq.getAmount();
		String interestRate = loanProductApplyFeeReq.getInterestRate();
		String termCount = loanProductApplyFeeReq.getTermCount();
		String termUnit = loanProductApplyFeeReq.getTermUnit();
		String repayTermCount = loanProductApplyFeeReq.getRepayTermCount();
		String repayTermUnit = loanProductApplyFeeReq.getRepayTermUnit();
		String repayType = loanProductApplyFeeReq.getRepayType();

		if ("2".equals(termUnit) && repayTermUnit != null && !"2".equals(repayTermUnit)) {// 借贷期限选择"月"，还款间隔不支持选择"日"
			throw new BusinessException(ResponseConstants.TERM_UNIT_NOT_PASS.getRetCode(),
					ResponseConstants.TERM_UNIT_NOT_PASS.getRetMsg());
		} else if ("3".equals(repayType)) {
			// 还款方式 == 3（等额本息），需借贷期限单位 、还款间隔单位均为2（月）
			if (!"2".equals(termUnit) || !"2".equals(repayTermUnit)) {
				throw new BusinessException(ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetCode(),
						ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetMsg());
			}
		}

		ProductApplyInfoResp loanProductApply = productApplyInfoMapper.selectLoanProductApplyById(productApplyId);

		List<Map<String, Object>> fees = productInfoMapper
				.selectLoanProductFeeConditionsByLoanProductId(loanProductApply.getLoanProductId());

		List<LoanProductApplyFeeInfo> LoanProductApplyFeeInfo = createLoanProductApplyFees(fees, new BigDecimal(amount),
				new BigDecimal(interestRate), Integer.parseInt(termCount), termUnit,
				StringUtils.isNotBlank(repayTermCount) ? Integer.parseInt(repayTermCount) : null, repayTermUnit,
				repayType);

		return LoanProductApplyFeeInfo;
	}

	/*
	 * (非 Javadoc) <p>Title: getLoanFeeInfo</p> <p>Description: </p>
	 * 
	 * @param loanProductApplyFeeReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanApplyFeeService#
	 * getLoanFeeInfo(com.company.platform.restapi.model.loan.fee.
	 * LoanProductApplyFeeReq)
	 */
	@Override
	public List<LoanApplyFeeInfo> getLoanFeeInfo(LoanProductApplyFeeReq loanProductApplyFeeReq)
			throws BusinessException {

		String productApplyId = loanProductApplyFeeReq.getProductApplyId();
		String amount = loanProductApplyFeeReq.getAmount();
		String interestRate = loanProductApplyFeeReq.getInterestRate();
		String termCount = loanProductApplyFeeReq.getTermCount();
		String termUnit = loanProductApplyFeeReq.getTermUnit();
		String repayTermCount = loanProductApplyFeeReq.getRepayTermCount();
		String repayTermUnit = loanProductApplyFeeReq.getRepayTermUnit();
		String repayType = loanProductApplyFeeReq.getRepayType();

		if ("2".equals(termUnit) && repayTermUnit != null && !"2".equals(repayTermUnit)) {// 借贷期限选择"月"，还款间隔不支持选择"日"
			throw new BusinessException(ResponseConstants.TERM_UNIT_NOT_PASS.getRetCode(),
					ResponseConstants.TERM_UNIT_NOT_PASS.getRetMsg());
		} else if ("3".equals(repayType)) {
			// 还款方式 == 3（等额本息），需借贷期限单位 、还款间隔单位均为2（月）
			if (!"2".equals(termUnit) || !"2".equals(repayTermUnit)) {
				throw new BusinessException(ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetCode(),
						ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetMsg());
			}
		}

		ProductApplyInfoResp loanProductApply = productApplyInfoMapper.selectLoanProductApplyById(productApplyId);

		List<Map<String, Object>> fees = productInfoMapper
				.selectLoanProductFeeConditionsByLoanProductId(loanProductApply.getLoanProductId());

		List<LoanProductApplyFeeInfo> loanProductApplyFeeInfo = createLoanProductApplyFees(fees, new BigDecimal(amount),
				new BigDecimal(interestRate), Integer.parseInt(termCount), termUnit,
				StringUtils.isNotBlank(repayTermCount) ? Integer.parseInt(repayTermCount) : null, repayTermUnit,
				repayType);
		List<LoanApplyFeeInfo> list = new ArrayList<LoanApplyFeeInfo>();
		if (loanProductApplyFeeInfo != null && loanProductApplyFeeInfo.size() > 0) {
			for (LoanProductApplyFeeInfo fee : loanProductApplyFeeInfo) {
				LoanApplyFeeInfo loanApplyFeeInfo = new LoanApplyFeeInfo();
				BeanUtils.copyProperties(fee, loanApplyFeeInfo);
				list.add(loanApplyFeeInfo);
			}
		}
		return list;
	}

	@Override
	public void saveLoanApplyFeeInfo(LoanProductApplyEntity apply) throws BusinessException {
		// 费用所需基本信息均存在时，保存费用信息
		if (apply.getRepayType() != null && apply.getApplyAmount() != null && apply.getInterestRate() != null
				&& apply.getTermCount() != null && apply.getTermUnit() != null) {
			if (!"1".equals(apply.getRepayType()) && apply.getRepayTermCount() != null
					&& apply.getRepayTermUnit() != null) {
				// 还款方式非一次性还款付息时，还款间隔必填，才可生成费用信息
				List<Map<String, Object>> fees = productInfoMapper
						.selectLoanProductFeeConditionsByLoanProductId(apply.getLoanProductId());
				List<LoanProductApplyFeeInfo> applyFees = createLoanProductApplyFees(fees,
						new BigDecimal(apply.getApplyAmount()), new BigDecimal(apply.getInterestRate()),
						Integer.parseInt(apply.getTermCount()),
						apply.getTermUnit(), StringUtils.isNotBlank(apply.getRepayTermCount())
								? Integer.parseInt(apply.getRepayTermCount()) : null,
						apply.getRepayTermUnit(), apply.getRepayType());
				if (applyFees != null && applyFees.size() > 0) {
					BigDecimal oneHundred = new BigDecimal(100);
					for (LoanProductApplyFeeInfo f : applyFees) {
						f.setId(UUID.randomUUID().toString());
						f.setLoanProductApplyId(apply.getId());
						if (f.getFeeRate() != null) {
							f.setFeeRate((new BigDecimal(f.getFeeRate()).divide(oneHundred)).toString());
						}
						f.setFeeReduction("0");
						f.setActualFeeAmount(f.getFeeAmount());
						f.setApplyType("0");
					}
					if (applyFees != null && applyFees.size() > 0) {
						feeMapper.deleteFeeInfo(apply.getId());
						for (LoanProductApplyFeeInfo fee : applyFees) {
							if ("".equals(fee.getPeriodNum())) {
								fee.setPeriodNum(null);
							}
							feeMapper.addFeeInfo(fee);
						}
					}
				}
			}
		}

	}

	public List<LoanProductApplyFeeInfo> createLoanProductApplyFees(List<Map<String, Object>> fees, BigDecimal amount,
			BigDecimal interestRate, Integer termCount, String termUnit, Integer repayTermCount, String repayTermUnit,
			String repayType) throws BusinessException {
		List<LoanProductApplyFeeInfo> loanProductApplyFees = new LinkedList<LoanProductApplyFeeInfo>();
		if (fees != null && fees.size() > 0) {
			ReqRepaymentModel reqRepaymentModel = new ReqRepaymentModel();
			reqRepaymentModel.setAmount(amount);
			reqRepaymentModel.setInterestRate(interestRate);
			reqRepaymentModel.setTermCount(termCount);
			reqRepaymentModel.setTermUnit(termUnit);
			if (!"1".equals(repayType)) {/* 如果还款方式非一次性还本付息，需要设置还款间隔 */
				reqRepaymentModel.setRepayTermCount(repayTermCount);
				reqRepaymentModel.setRepayTermUnit(repayTermUnit);
			}
			reqRepaymentModel.setRepayType(repayType);
			reqRepaymentModel.setDaysOneMonth(getConfigData("DAYS_ONE_MONTH"));
			reqRepaymentModel.setDaysOneYear(getConfigData("DAYS_ONE_YEAR"));
			reqRepaymentModel.setRound(getRound(getConfigData("ROUND")));
			List<RespRepaymentModel> respRepaymentModels = RepaymentUtil.createRepayment(reqRepaymentModel);

			BigDecimal totalInterest = BigDecimal.ZERO; // 总应还利息
			if (respRepaymentModels != null && respRepaymentModels.size() > 0) {
				totalInterest = respRepaymentModels.get(0).getTotalInterest();
			}

			for (Map<String, Object> fee : fees) {
				String feeId = (String) fee.get("feeId");
				String name = (String) fee.get("name");// 费用描述
				String feeType = (String) fee.get("feeType");// 费用类型 1:手续费 2:滞纳金
																// 3:违约金 4:管理费
																// 5:担保费 6:服务费
																// 7:风险金
				String chargeType = (String) fee.get("chargeType");// 收取类型
																	// 1：放款时收取
																	// 2：按贷款周期收取
																	// 3：首次还款日收取
																	// 4:逾期收取
																	// 5:违约收取

				int feeMode = (Integer) fee.get("feeMode");// 费用计算方式 0：自定义费用
															// 1：按总比例收取
				BigDecimal feeAmount = (BigDecimal) fee.get("feeAmount");// 费用金额（feeMode值为0时有效）
				String calculationBasic = (String) fee.get("calculationBasic");// 比例计算基础
																				// 1:贷款金额
																				// 2:剩余本金
																				// 3:剩余本息
																				// 4:当期应还本息
																				// 5:总应还本息
																				// 6:剩余最低还款额（账单式按日计息生效）
				if ("6".equals(calculationBasic)) {
					continue;
				}
				BigDecimal feeRate = (BigDecimal) fee.get("feeRate");// 收取比例（feeMode值为1时有效）
				BigDecimal minFee = null;// 最低费用
				BigDecimal maxFee = null;// 最高费用
				FeeCondition feeCondition = feeMapper.selectFeeConditionByFeeIdAndTerm(feeId,
						reqRepaymentModel.getTermCount().toString(), reqRepaymentModel.getTermUnit());
				if (feeCondition != null && feeCondition.getIsOn().equals("1")) {
					feeMode = Integer.parseInt(feeCondition.getCalculationType());
					feeAmount = feeCondition.getFeeAmount() == null ? BigDecimal.ZERO
							: new BigDecimal(feeCondition.getFeeAmount());
					if (feeCondition.getCalculationBasics() != null) {
						calculationBasic = feeCondition.getCalculationBasics();
					}
					if (feeCondition.getFeeRate() != null) {
						feeRate = new BigDecimal(feeCondition.getFeeRate());
					}
					if (feeCondition.getMinFee() != null) {
						minFee = new BigDecimal(feeCondition.getMinFee());
					}
					if (feeCondition.getMaxFee() != null) {
						maxFee = new BigDecimal(feeCondition.getMaxFee());
					}

				}
				if ("1".equals(chargeType) || "3".equals(chargeType) || "6".equals(chargeType)) {
					LoanProductApplyFeeInfo loanProductApplyFee = new LoanProductApplyFeeInfo();
					loanProductApplyFee.setName(name);
					loanProductApplyFee.setChargeType(chargeType);
					loanProductApplyFee.setFeeType(feeType);
					BigDecimal FeeAmountTemp = BigDecimal.ZERO;
					if (feeMode == 0) {
						loanProductApplyFee.setFeeAmount(feeAmount.toString());
					} else {
						loanProductApplyFee.setCalculationBasic(calculationBasic);
						loanProductApplyFee.setFeeRate(feeRate.multiply(new BigDecimal(100)).setScale(2).toString());
						if ("1".equals(calculationBasic)) {
							if (!"5".equals(chargeType)) {
								FeeAmountTemp = reqRepaymentModel.getAmount().multiply(feeRate).setScale(2,
										reqRepaymentModel.getRound());
								loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
							}
						} else if ("2".equals(calculationBasic)) {
							if (!"5".equals(chargeType)) {
								FeeAmountTemp = reqRepaymentModel.getAmount().multiply(feeRate).setScale(2,
										reqRepaymentModel.getRound());
								loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
							}
						} else if ("3".equals(calculationBasic) || "5".equals(calculationBasic)) {
							if (!"5".equals(chargeType)) {
								FeeAmountTemp = reqRepaymentModel.getAmount().add(totalInterest).multiply(feeRate)
										.setScale(2, reqRepaymentModel.getRound());
								loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
							}
						} else {
							if ("3".equals(chargeType)) {
								RespRepaymentModel firstRespRepaymentModel = respRepaymentModels.get(0);
								FeeAmountTemp = firstRespRepaymentModel.getPrincipal()
										.add(firstRespRepaymentModel.getInterest()).multiply(feeRate)
										.setScale(2, reqRepaymentModel.getRound());
								loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
							}
						}
					}
					BigDecimal feeAmountGlobal = loanProductApplyFee.getFeeAmount() == null ? BigDecimal.ZERO
							: new BigDecimal(loanProductApplyFee.getFeeAmount());
					if (minFee != null && feeAmountGlobal.compareTo(minFee) < 0) {
						/* 收取费用小于最低费用，设置收取费用为最低费用 */
						loanProductApplyFee.setFeeAmount(minFee.toString());
					}
					if (maxFee != null && feeAmountGlobal.compareTo(maxFee) > 0) {
						/* 收取费用大于最高费用，设置收取费用为最高费用 */
						loanProductApplyFee.setFeeAmount(maxFee.toString());
					}
					loanProductApplyFee.setPeriodNum("1".equals(chargeType) || "5".equals(chargeType) || "6".equals(chargeType) ? "" : "1");
					loanProductApplyFee.setStatus("0");
					loanProductApplyFee.setFeeConditionId(feeCondition == null ? null : feeCondition.getId());
					loanProductApplyFee.setLoanProductFeeId(feeId);
					loanProductApplyFees.add(loanProductApplyFee);
				} else if ("2".equals(chargeType) || "4".equals(chargeType) || "5".equals(chargeType)) {
					if (respRepaymentModels != null && respRepaymentModels.size() > 0) {
						for (RespRepaymentModel respRepaymentModel : respRepaymentModels) {
							LoanProductApplyFeeInfo loanProductApplyFee = new LoanProductApplyFeeInfo();
							loanProductApplyFee.setName(name);
							loanProductApplyFee.setChargeType(chargeType);
							loanProductApplyFee.setFeeType(feeType);
							BigDecimal FeeAmountTemp = BigDecimal.ZERO;
							if (feeMode == 0) {
								loanProductApplyFee.setFeeAmount(feeAmount.toString());
							} else {
								loanProductApplyFee.setCalculationBasic(calculationBasic);
								loanProductApplyFee
										.setFeeRate(feeRate.multiply(new BigDecimal(100)).setScale(2).toString());
								if ("1".equals(calculationBasic)) {
									FeeAmountTemp = reqRepaymentModel.getAmount().multiply(feeRate).setScale(2,
											reqRepaymentModel.getRound());
									loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
								} else if ("2".equals(calculationBasic)) {
									FeeAmountTemp = respRepaymentModel.getPrincipal()
											.add(respRepaymentModel.getSurplusPrincipal()).multiply(feeRate)
											.setScale(2, reqRepaymentModel.getRound());
									loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
								} else if ("3".equals(calculationBasic)) {
									FeeAmountTemp = respRepaymentModel.getPrincipal()
											.add(respRepaymentModel.getSurplusPrincipal())
											.add(respRepaymentModel.getInterest())
											.add(respRepaymentModel.getSurplusInterest()).multiply(feeRate)
											.setScale(2, reqRepaymentModel.getRound());
									loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
								} else if ("4".equals(calculationBasic)) {
									FeeAmountTemp = respRepaymentModel.getPrincipal()
											.add(respRepaymentModel.getInterest()).multiply(feeRate)
											.setScale(2, reqRepaymentModel.getRound());
									loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
								} else {
									FeeAmountTemp = respRepaymentModel.getTotalPrincipal()
											.add(respRepaymentModel.getTotalInterest()).multiply(feeRate)
											.setScale(2, reqRepaymentModel.getRound());
									loanProductApplyFee.setFeeAmount(FeeAmountTemp.toString());
								}
							}
							BigDecimal feeAmountGlobal = loanProductApplyFee.getFeeAmount() == null ? BigDecimal.ZERO
									: new BigDecimal(loanProductApplyFee.getFeeAmount());
							if (minFee != null && feeAmountGlobal.compareTo(minFee) < 0) {
								/* 收取费用小于最低费用，设置收取费用为最低费用 */
								loanProductApplyFee.setFeeAmount(minFee.toString());
							}
							if (maxFee != null && feeAmountGlobal.compareTo(maxFee) > 0) {
								/* 收取费用大于最高费用，设置收取费用为最高费用 */
								loanProductApplyFee.setFeeAmount(maxFee.toString());
							}
							loanProductApplyFee.setPeriodNum(respRepaymentModel.getPeriodNum().toString());
							loanProductApplyFee.setStatus("0");
							loanProductApplyFee.setFeeConditionId(feeCondition == null ? null : feeCondition.getId());
							loanProductApplyFee.setLoanProductFeeId(feeId);
							loanProductApplyFees.add(loanProductApplyFee);
						}
					}
				}
			}
		}
		return loanProductApplyFees;
	}

	/**
	 * @param code
	 *            配置项code
	 * @return 配置项值
	 */
	public String getConfigData(String code) {
		Map<String, String> globalConfig = new TreeMap<String, String>();
		List<Map<String, Object>> globalDatas = sysDictionaryMapper.getGlobalData();
		for (Map<String, Object> data : globalDatas) {
			globalConfig.put((String) data.get("name"), (String) data.get("value"));
		}
		return globalConfig.get(code);
	}

	/**
	 * 根据全局配置获取舍入方式
	 * 
	 * @param round
	 * @return
	 */
	public int getRound(String round) {
		if ("ROUND_CEILING".equals(round)) {
			return BigDecimal.ROUND_CEILING;
		} else if ("ROUND_DOWN".equals(round)) {
			return BigDecimal.ROUND_DOWN;
		} else if ("ROUND_FLOOR".equals(round)) {
			return BigDecimal.ROUND_FLOOR;
		} else if ("ROUND_HALF_DOWN".equals(round)) {
			return BigDecimal.ROUND_HALF_DOWN;
		} else if ("ROUND_HALF_EVEN".equals(round)) {
			return BigDecimal.ROUND_HALF_EVEN;
		} else if ("ROUND_HALF_UP".equals(round)) {
			return BigDecimal.ROUND_HALF_UP;
		} else if ("ROUND_UNNECESSARY".equals(round)) {
			return BigDecimal.ROUND_UNNECESSARY;
		} else if ("ROUND_UP".equals(round)) {
			return BigDecimal.ROUND_UP;
		} else {
			return BigDecimal.ROUND_HALF_EVEN;
		}
	}

	/*
	 * (非 Javadoc) <p>Title: dealLoanApplyFeeInfo</p> <p>Description: </p>
	 * 
	 * @param applyBefore
	 * 
	 * @param loanProductApplyInfo
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanApplyFeeService#
	 * dealLoanApplyFeeInfo(com.company.platform.restapi.model.loan.offToOnline.
	 * LoanProductApplyEntity,
	 * com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo)
	 */
	@Override
	public void dealLoanApplyFeeInfo(LoanProductApplyEntity applyBefore, LoanProductApplyInfo loanProductApplyInfo)
			throws BusinessException {
		// 借贷申请的基本信息改变，重新生成费用
		LoanProductApplyEntity apply = new LoanProductApplyEntity();
		BeanUtils.copyProperties(loanProductApplyInfo, apply);
		apply.setLoanProductId(applyBefore.getLoanProductId());
		if (applyBefore.getRepayType() == null || applyBefore.getApplyAmount() == null
				|| applyBefore.getInterestRate() == null || applyBefore.getTermCount() == null
				|| applyBefore.getTermUnit() == null) {
			// 借贷申请费用信息保存
			this.saveLoanApplyFeeInfo(apply);
		} else if (!applyBefore.getApplyAmount().equals(loanProductApplyInfo.getApplyAmount())
				|| (new BigDecimal(applyBefore.getInterestRate()))
						.compareTo(new BigDecimal(loanProductApplyInfo.getInterestRate())) != 0
				|| !applyBefore.getRepayType().equals(loanProductApplyInfo.getRepayType())
				|| !applyBefore.getTermCount().equals(loanProductApplyInfo.getTermCount())
				|| !applyBefore.getTermUnit().equals(loanProductApplyInfo.getTermUnit())) {
			// 借贷申请费用信息保存
			this.saveLoanApplyFeeInfo(apply);
		} else if (applyBefore.getRepayTermCount() != null && loanProductApplyInfo.getRepayTermCount() != null) {
			if (!applyBefore.getRepayTermCount().equals(loanProductApplyInfo.getRepayTermCount())
					|| !applyBefore.getRepayTermUnit().equals(loanProductApplyInfo.getRepayTermUnit())) {
				// 借贷申请费用信息保存
				this.saveLoanApplyFeeInfo(apply);
			}
		}

	}
}
