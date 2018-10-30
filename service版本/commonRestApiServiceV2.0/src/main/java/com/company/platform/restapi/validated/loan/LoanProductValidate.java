package com.company.platform.restapi.validated.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;
import com.company.platform.base.util.MatchUtil;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.loan.LoanCodeMapper;
import com.company.platform.restapi.dao.loan.OrgProductCreditMapper;
import com.company.platform.restapi.dao.loan.OrgProductRelationMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.dao.modelmanager.BusinessModelMapper;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.LoanProductValidateInfo;
import com.company.platform.restapi.model.loan.OrgProductCredit;
import com.company.platform.restapi.model.loan.OrgProductRelation;
import com.company.platform.restapi.model.loan.fee.LoanProductApplyFeeReq;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.model.loan.offToOnline.OfflineTempLoanApplyReq;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;
import com.company.platform.restapi.model.modelmanager.BusInfoModel;

@Service
public class LoanProductValidate {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanProductValidate.class);

	@Autowired
	private LoanCodeMapper loanCodeMapper;

	@Autowired
	CustomerInfoMapper customerInfoMapper;

	@Autowired
	OrgProductRelationMapper orgProductRelationMapper;

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	OrgProductCreditMapper orgProductCreditMapper;

	@Autowired
	private BusinessModelMapper businessVersionModelMapper;

	@Autowired
	private BusinessModelMapper businessModelMapper;

	@Autowired
	private IDataDictionaryService dataDictionaryService;

	/** 
	* @Title: loanApplyFeeValidate 
	* @Description: TODO(借贷申请费用信息输入参数数据校验) 
	* @param @param req
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void loanApplyFeeValidate(LoanProductApplyFeeReq req, String type) throws BusinessException {
		if (!"1".equals(req.getRepayType())) {// ！一次性还本付息
			if (StringUtils.isEmpty(req.getRepayTermCount())) {
				throw new BusinessException(ResponseConstants.REPAY_TERMCOUNT_IS_EMPTY.getRetCode(),
						ResponseConstants.REPAY_TERMCOUNT_IS_EMPTY.getRetMsg());
			} else if (StringUtils.isEmpty(req.getRepayTermUnit())) {
				throw new BusinessException(ResponseConstants.REPAY_TERMUNIT_IS_EMPTY.getRetCode(),
						ResponseConstants.REPAY_TERMUNIT_IS_EMPTY.getRetMsg());
			}
		}
		if ("2".equals(req.getTermUnit()) && req.getRepayTermUnit() != null && !"2".equals(req.getRepayTermUnit())) {
			if ("storage".equals(type)) {// 借贷期限选择"月"，还款间隔不支持选择"日"
				throw new BusinessException(ResponseConstants.TERM_UNIT_NOT_PASS.getRetCode(),
						ResponseConstants.TERM_UNIT_NOT_PASS.getRetMsg());
			} else {// 批准期限选择"月"，还款间隔不支持选择"日"
				throw new BusinessException(ResponseConstants.REPLY_TERM_UNIT_NOT_PASS.getRetCode(),
						ResponseConstants.REPLY_TERM_UNIT_NOT_PASS.getRetMsg());
			}
		} else if ("3".equals(req.getRepayType())) {
			// 还款方式 == 3（等额本息），需批准期限单位 、还款间隔单位均为2（月）
			if (!"2".equals(req.getTermUnit()) || !"2".equals(req.getRepayTermUnit())) {
				if ("storage".equals(type)) {
					throw new BusinessException(ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetCode(),
							ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetMsg());
				} else {
					throw new BusinessException(ResponseConstants.REPAY_TERM_UNIT_NOT_PASS.getRetCode(),
							ResponseConstants.REPAY_TERM_UNIT_NOT_PASS.getRetMsg());
				}
			}
		}
	}

	/** 
	* @Title: loanProductValidate 
	* @Description: TODO(验证借贷产品的存在性和有效性，返回借贷编码类型) 
	* @param @param productId 借贷产品id
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String loanProductValidate(String productId) throws BusinessException {
		// 根据借贷产品id查询借贷产品详细（产品状态 0：待生效 1：生效 -1：删除，借贷编码类型）
		LoanProductValidateInfo validate = loanCodeMapper.getLoanProductValidateById(productId);
		if (validate == null || "".equals(validate.getId())) {// 产品不存在
			logger.error("产品不存在");
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetMsg());
		} else if (validate.getStatus() != 1) {// 产品失效
			logger.error("产品失效");
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_INVALID.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_INVALID.getRetMsg());
		}
		return validate.getLoanCodeType();
	}

	/** 
	* @Title: loanProductOrgRelValidate 
	* @Description: TODO(验证用户权限) 
	* @param @param productId
	* @param @param orgId
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void loanProductOrgRelValidate(String productId, String orgId) throws BusinessException {
		// 获取组织机构和产品的关联信息
		Map<String, String> info = new HashMap<String, String>();
		info.put("productId", productId);
		info.put("orgId", orgId);
		Map<String, Object> map = loanCodeMapper.getLoanProductOrgRel(info);
		if (map == null || "".equals(map.get("id").toString())) {
			logger.error("用户无权限");
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_ORG_NOT_REL.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_ORG_NOT_REL.getRetMsg());
		}
	}

	/** 
	* @Title: loanProductApplyValidate 
	* @Description: TODO(借贷申请信息校验) 
	* @param @param apply
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public LoanProductInfo loanProductApplyValidate(OfflineTempLoanApplyReq offlineTempLoanApplyReq)
			throws BusinessException {
		logger.info("借贷申请信息校验");
		if (null == offlineTempLoanApplyReq.getLoanProductApplyEntity()) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_APPLY_IS_EMPTY.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_APPLY_IS_EMPTY.getRetMsg());
		}

		// 校验证件类型字典值
		Map<String, Map<String, Object>> map = dataDictionaryService.getDataMap();
		if ("0".equals(offlineTempLoanApplyReq.getCustomerType())) {// 个人客户
			if (!((Map<String, String>) map.get("credentialType").get("item"))
					.containsKey(offlineTempLoanApplyReq.getCredentialType())) {
				throw new BusinessException(ResponseConstants.CREDENTIAL_TYPE_ERROR.getRetCode(),
						ResponseConstants.CREDENTIAL_TYPE_ERROR.getRetMsg());
			}
		} else if ("1".equals(offlineTempLoanApplyReq.getCustomerType())) {// 企业客户
			if (!((Map<String, String>) map.get("corCredentialType").get("item"))
					.containsKey(offlineTempLoanApplyReq.getCredentialType())) {
				throw new BusinessException(ResponseConstants.CREDENTIAL_TYPE_ERROR.getRetCode(),
						ResponseConstants.CREDENTIAL_TYPE_ERROR.getRetMsg());
			}
		}

		LoanProductApplyEntity apply = offlineTempLoanApplyReq.getLoanProductApplyEntity();

		if ("2".equals(apply.getTermUnit()) && apply.getRepayTermUnit() != null
				&& !"2".equals(apply.getRepayTermUnit())) {// 借贷期限选择"月"，还款间隔不支持选择"日"
			throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
					ResponseConstants.TERM_UNIT_NOT_PASS.getRetMsg());
		} else if ("3".equals(apply.getRepayType())) {
			// 还款方式 == 3（等额本息），需借贷期限单位、还款间隔单位均为2（月）
			if (!"2".equals(apply.getTermUnit()) || !"2".equals(apply.getRepayTermUnit())) {
				throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
						ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetMsg());
			}
		}

		String loanProductId = apply.getLoanProductId();
		String orgId = offlineTempLoanApplyReq.getCustomerOrgId();

		LoanProductInfo product = productInfoMapper.queryByPrimaryId(loanProductId);
		if (product == null) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetMsg());
		}
		if (!product.getProductInfoUpdateTime().equals(apply.getUpdateTime())) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_NOT_SYN.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_NOT_SYN.getRetMsg());
		}

		OrgProductRelation relation = orgProductRelationMapper.selectByOrgIdAndLoanProductId(orgId, loanProductId);
		if (relation == null) {
			throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetMsg());
		}

		Map<String, String> info = new HashMap<String, String>();
		info.put("credentialType", offlineTempLoanApplyReq.getCredentialType());
		info.put("credentialNo", offlineTempLoanApplyReq.getCredentialNo());

		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfo(info);

		BigDecimal surplusAmount = null;// 还可申请借贷额度
		if (StringUtils.isNotBlank(loanProductId)) {
			/* 如果已选择产品，计算机构（操作人所在机构）还可申请此产品的借贷额度 */
			if (relation != null && relation.getTotalAmount() != null) {
				BigDecimal totalAmount = new BigDecimal(relation.getTotalAmount());// 机构可用额度
				if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
					logger.error("组织机构额度不足");
					throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
							ResponseConstants.ORG_SURPLUS_AMOUNT_ERROR.getRetMsg());
				}
				BigDecimal totalUsedCredit = BigDecimal.ZERO;// 已使用额度
				OrgProductCredit orgProductCredit = orgProductCreditMapper.selectByOrgIdAndLoanProductId(orgId,
						loanProductId);
				if (orgProductCredit != null) {
					totalUsedCredit = new BigDecimal(orgProductCredit.getUsedCredit());
				}
				surplusAmount = totalAmount.subtract(totalUsedCredit);
			}
		}
		if (customerPublicInfo != null) {
			if ("1".equals(customerPublicInfo.getCreditStatus())) {
				// 客户可用额度
				BigDecimal availableCredit = BigDecimal.ZERO;
				if (customerPublicInfo.getAvailableCredit() != null) {
					availableCredit = new BigDecimal(customerPublicInfo.getAvailableCredit());
				}
				// 客户已用额度
				BigDecimal usedCredit = BigDecimal.ZERO;
				if (customerPublicInfo.getUsedCredit() != null) {
					usedCredit = new BigDecimal(customerPublicInfo.getUsedCredit());
				}
				BigDecimal a = availableCredit.subtract(usedCredit);// 客户还可以使用额度
				if (surplusAmount == null || a.compareTo(surplusAmount) < 0) {
					surplusAmount = a;
				}
			}
		}
		if (surplusAmount != null && surplusAmount.compareTo(new BigDecimal(apply.getApplyAmount())) < 0) {
			logger.error("客户可申请额度不足");
			throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
					ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetMsg());
		}

		BigDecimal applyAmount = new BigDecimal(apply.getApplyAmount());
		int amountLimitType = Integer.parseInt(product.getAmountLimitType());
		if (amountLimitType == 0) {/* 校验申请金额（区间） */
			BigDecimal minAmount = new BigDecimal(product.getSingleMinAmount());
			if (relation.getSingleMinAmount() != null) {
				minAmount = new BigDecimal(relation.getSingleMinAmount());
			}

			BigDecimal maxAmount = new BigDecimal(product.getSingleMaxAmount());
			if (relation.getSingleMaxAmount() != null) {
				maxAmount = new BigDecimal(relation.getSingleMaxAmount());
			}

			if (applyAmount != null && (applyAmount.compareTo(minAmount) < 0 || applyAmount.compareTo(maxAmount) > 0)) {
				logger.error("申请金额不在允许范围内");
				throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
			}
		} else {/* 校验申请金额（固定值） */
			String[] amounts = StringUtils.isBlank(relation.getSingleAmounts()) ? product.getSingleAmounts().split(",")
					: relation.getSingleAmounts().split(",");
			boolean applyIsError = applyAmount == null ? false : true;
			StringBuffer buf = new StringBuffer();
			int idx = 0;
			int last = amounts.length - 1;
			for (String amount : amounts) {
				BigDecimal a = new BigDecimal(amount);
				if (applyIsError && applyAmount.compareTo(a) == 0) {
					applyIsError = false;
				}
				buf.append(a.toString());
				if (idx++ < last) {
					buf.append(",");
				}
			}
			if (applyIsError) {
				logger.error("申请金额不在允许范围内");
				throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
			}
		}

		if (StringUtils.isNotBlank(apply.getTermUnit()) && apply.getTermCount() != null) {/* 校验借贷期限 */
			int termCount = Integer.parseInt(apply.getTermCount());
			if ("1".equals(apply.getTermUnit())) {/* 单位：日 */
				int daysLimitType = Integer.parseInt(product.getDaysLimitType());
				if (daysLimitType == 0) {/* 区间校验 */
					int minDays = Integer.parseInt(product.getSingleMinDays());
					int maxDays = Integer.parseInt(product.getSingleMaxDays());
					if (termCount < minDays || termCount > maxDays) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				} else {/* 固定值校验 */
					String[] days = product.getSingleDays().split(",");
					boolean isError = true;
					int idx = 0;
					int last = days.length - 1;
					StringBuffer buf = new StringBuffer();
					for (String day : days) {
						if (termCount == Integer.parseInt(day)) {
							isError = false;
							break;
						}
						buf.append(day);
						if (idx++ < last) {
							buf.append(",");
						}
					}
					if (isError) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				}
			} else {/* 单位：月 */
				int monthsLimitType = Integer.parseInt(product.getMonthsLimitType());
				if (monthsLimitType == 0) {/* 区间校验 */
					int minMonths = Integer.parseInt(product.getSingleMinMonths());
					int maxMonths = Integer.parseInt(product.getSingleMaxMonths());
					if (termCount < minMonths || termCount > maxMonths) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				} else {/* 固定值校验 */
					String[] months = product.getSingleMonths().split(",");
					boolean isError = true;
					int idx = 0;
					int last = months.length - 1;
					StringBuffer buf = new StringBuffer();
					for (String month : months) {
						if (termCount == Integer.parseInt(month)) {
							isError = false;
							break;
						}
						buf.append(month);
						if (idx++ < last) {
							buf.append(",");
						}
					}
					if (isError) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				}
			}
		}

		if (StringUtils.isNotEmpty(product.getRepayType()) && StringUtils.isNotEmpty(apply.getRepayType())) {
			int index = product.getRepayType().indexOf(apply.getRepayType());
			if (index < 0) {
				logger.error("还款方式不在允许范围内");
				throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
						ResponseConstants.REPAY_TYPE_RULE_ERROR.getRetMsg());
			}
		}

		if (StringUtils.isNotEmpty(product.getGuaranteeType()) && StringUtils.isNotEmpty(apply.getGuaranteeType())) {
			int index = product.getGuaranteeType().indexOf(apply.getGuaranteeType());
			if (index < 0) {
				logger.error("担保方式不在允许范围内");
				throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
						ResponseConstants.GUARANTEE_TYPE_RULE_ERROR.getRetMsg());
			}
		}

		if (StringUtils.isNotEmpty(apply.getInterestRate())) {
			BigDecimal interestRate = new BigDecimal(apply.getInterestRate());
			if (interestRate != null) {/* 校验年化利率 */
				if (product.getInterestRateLimitType().equals("0")) {/* 区间校验 */
					BigDecimal minInterestRate = relation.getOrgMinInterestRate() == null
							? new BigDecimal(product.getMinInterestRate())
							: new BigDecimal(relation.getOrgMinInterestRate());
					BigDecimal maxInterestRate = relation.getOrgMaxInterestRate() == null
							? new BigDecimal(product.getMaxInterestRate())
							: new BigDecimal(relation.getOrgMaxInterestRate());
					if (interestRate.compareTo(minInterestRate) < 0 || interestRate.compareTo(maxInterestRate) > 0) {
						logger.error("年化利率不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.INTEREST_RATE_RULE_ERROR.getRetMsg());
					}
				} else {/* 固定值校验 */
					String[] interestRates = StringUtils.isBlank(relation.getInterestRates())
							? product.getInterestRates().split(",") : relation.getInterestRates().split(",");
					boolean isError = true;
					int idx = 0;
					int last = interestRates.length - 1;
					StringBuffer buf = new StringBuffer();
					for (String rate : interestRates) {
						BigDecimal r = new BigDecimal(rate);
						if (interestRate.compareTo(r) == 0) {
							isError = false;
							break;
						}
						buf.append(getPERCENTText(r));
						if (idx++ < last) {
							buf.append(",");
						}
					}
					if (isError) {
						logger.error("年化利率不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.INTEREST_RATE_RULE_ERROR.getRetMsg());
					}
				}
			}
		}

		// 校验首付金额
		if ("1".equals(product.getDownPaymentsEnable())) {// 启用
			if (StringUtils.isEmpty(apply.getDownPaymentsAmount())) {
				logger.error("首付金额不能为空");
				throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
						ResponseConstants.DOWN_RAYMENT_AMOUNT_IS_EMPTY.getRetMsg());
			}
			// 首付金额范围检验
			BigDecimal downPaymentsAmount = new BigDecimal(apply.getDownPaymentsAmount());
			if ("0".equals(product.getDownPaymentsLimitType())) {// 区间值
				BigDecimal minDownPayments = new BigDecimal(product.getMinDownPayments());
				BigDecimal maxDownPayments = new BigDecimal(product.getMaxDownPayments());
				if (downPaymentsAmount.compareTo(minDownPayments) < 0
						|| downPaymentsAmount.compareTo(maxDownPayments) > 0) {
					logger.error("首付金额不在允许范围内");
					throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
							ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
				}
			} else {// 固定值
				String[] downPayments = product.getDownPayments().split(",");
				boolean isError = true;
				for (String payment : downPayments) {
					BigDecimal r = new BigDecimal(payment);
					if (downPaymentsAmount.compareTo(r) == 0) {
						isError = false;
						break;
					}
				}
				if (isError) {
					logger.error("首付金额不在允许范围内");
					throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
							ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
				}
			}
		}

		if (StringUtils.isNotEmpty(apply.getPenaltyRate())) {
			BigDecimal penaltyRate = new BigDecimal(apply.getPenaltyRate());
			if (penaltyRate != null) {/* 校验罚息利率 */
				if (product.getPenaltyRateLimitType().equals("0")) {/* 区间校验 */
					BigDecimal minPenaltyRate = relation.getOrgMinPenaltyRate() == null
							? new BigDecimal(product.getMinPenaltyRate())
							: new BigDecimal(relation.getOrgMinPenaltyRate());
					BigDecimal maxPenaltyRate = relation.getOrgMaxPenaltyRate() == null
							? new BigDecimal(product.getMaxPenaltyRate())
							: new BigDecimal(relation.getOrgMaxPenaltyRate());
					if (penaltyRate.compareTo(minPenaltyRate) < 0 || penaltyRate.compareTo(maxPenaltyRate) > 0) {
						logger.error("罚息利率不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.LOAN_PENALTY_RATE_ERROR.getRetMsg());
					}
				} else {/* 固定值校验 */
					String[] penaltyRates = StringUtils.isBlank(relation.getPenaltyRates())
							? product.getPenaltyRates().split(",") : relation.getPenaltyRates().split(",");
					boolean isError = true;
					int idx = 0;
					int last = penaltyRates.length - 1;
					StringBuffer buf = new StringBuffer();
					for (String rate : penaltyRates) {
						BigDecimal r = new BigDecimal(rate);
						if (penaltyRate.compareTo(r) == 0) {
							isError = false;
							break;
						}
						buf.append(getPERCENTText(r));
						if (idx++ < last) {
							buf.append(",");
						}
					}
					if (isError) {
						logger.error("罚息利率不在允许范围内");
						throw new BusinessException(ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
								ResponseConstants.LOAN_PENALTY_RATE_ERROR.getRetMsg());
					}
				}
			}
		}
		return product;
	}

	public LoanProductApplyEntity loanProductApplyOnlineValidate(LoanProductApplyInfo loanProductApplyInfo)
			throws BusinessException {
		if (null == loanProductApplyInfo) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_APPLY_IS_EMPTY.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_APPLY_IS_EMPTY.getRetMsg());
		}

		if ("2".equals(loanProductApplyInfo.getTermUnit()) && loanProductApplyInfo.getRepayTermUnit() != null
				&& !"2".equals(loanProductApplyInfo.getRepayTermUnit())) {// 借贷期限选择"月"，还款间隔不支持选择"日"
			throw new BusinessException(ResponseConstants.TERM_UNIT_NOT_PASS.getRetCode(),
					ResponseConstants.TERM_UNIT_NOT_PASS.getRetMsg());
		} else if ("3".equals(loanProductApplyInfo.getRepayType())) {
			// 还款方式 == 3（等额本息），需借贷期限单位、还款间隔单位均为2（月）
			if (!"2".equals(loanProductApplyInfo.getTermUnit())
					|| !"2".equals(loanProductApplyInfo.getRepayTermUnit())) {
				throw new BusinessException(ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetCode(),
						ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetMsg());
			}
		}

		LoanProductApplyEntity apply = productInfoMapper.getLoanProductApplyById(loanProductApplyInfo.getId());
		if (apply == null) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_APPLY_NOT_EXIST.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_APPLY_NOT_EXIST.getRetMsg());
		}
		String loanProductId = apply.getLoanProductId();
		String orgId = apply.getOrgId();

		LoanProductInfo product = productInfoMapper.queryByPrimaryId(loanProductId);
		if (product == null) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_NOT_EXIST.getRetMsg());
		}
		if (!"1".equals(product.getStatus())) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_STATUS_ERROR.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_STATUS_ERROR.getRetMsg());
		}

		OrgProductRelation relation = orgProductRelationMapper.selectByOrgIdAndLoanProductId(orgId, loanProductId);
		if (relation == null) {
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_ORG_NOT_RELATION.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_ORG_NOT_RELATION.getRetMsg());
		}

		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfoById(apply.getCustomerId());
		if (customerPublicInfo == null) {
			throw new BusinessException(ResponseConstants.CUSTOMER_PUBLIC_NOT_EXIST.getRetCode(),
					ResponseConstants.CUSTOMER_PUBLIC_NOT_EXIST.getRetMsg());
		}
		BigDecimal surplusAmount = null;// 还可申请借贷额度
		if (StringUtils.isNotBlank(loanProductId)) {
			/* 如果已选择产品，计算机构（操作人所在机构）还可申请此产品的借贷额度 */
			if (relation != null && relation.getTotalAmount() != null) {
				BigDecimal totalAmount = new BigDecimal(relation.getTotalAmount());// 机构可用额度
				if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
					logger.error("组织机构额度不足");
					throw new BusinessException(ResponseConstants.ORG_SURPLUS_AMOUNT_ERROR.getRetCode(),
							ResponseConstants.ORG_SURPLUS_AMOUNT_ERROR.getRetMsg());
				}
				BigDecimal totalUsedCredit = BigDecimal.ZERO;// 已使用额度
				OrgProductCredit orgProductCredit = orgProductCreditMapper.selectByOrgIdAndLoanProductId(orgId,
						loanProductId);
				if (orgProductCredit != null) {
					totalUsedCredit = new BigDecimal(orgProductCredit.getUsedCredit());
				}
				surplusAmount = totalAmount.subtract(totalUsedCredit);
			}
		}
		if (customerPublicInfo != null) {
			if ("1".equals(customerPublicInfo.getCreditStatus())) {
				// 客户可用额度
				BigDecimal availableCredit = BigDecimal.ZERO;
				if (customerPublicInfo.getAvailableCredit() != null) {
					availableCredit = new BigDecimal(customerPublicInfo.getAvailableCredit());
				}
				// 客户已用额度
				BigDecimal usedCredit = BigDecimal.ZERO;
				if (customerPublicInfo.getUsedCredit() != null) {
					usedCredit = new BigDecimal(customerPublicInfo.getUsedCredit());
				}
				BigDecimal a = availableCredit.subtract(usedCredit);// 客户还可以使用额度
				if (surplusAmount == null || a.compareTo(surplusAmount) < 0) {
					surplusAmount = a;
				}
			}
		}
		if (surplusAmount != null
				&& surplusAmount.compareTo(new BigDecimal(loanProductApplyInfo.getApplyAmount())) < 0) {
			logger.error("客户可申请额度不足");
			throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetCode(),
					ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetMsg());
		}

		BigDecimal applyAmount = new BigDecimal(loanProductApplyInfo.getApplyAmount());
		int amountLimitType = Integer.parseInt(product.getAmountLimitType());
		if (amountLimitType == 0) {/* 校验申请金额（区间） */
			BigDecimal minAmount = new BigDecimal(product.getSingleMinAmount());
			if (relation.getSingleMinAmount() != null) {
				minAmount = new BigDecimal(relation.getSingleMinAmount());
			}

			BigDecimal maxAmount = new BigDecimal(product.getSingleMaxAmount());
			if (relation.getSingleMaxAmount() != null) {
				maxAmount = new BigDecimal(relation.getSingleMaxAmount());
			}

			if (applyAmount != null && (applyAmount.compareTo(minAmount) < 0 || applyAmount.compareTo(maxAmount) > 0)) {
				logger.error("申请金额不在允许范围内");
				throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
			}
		} else {/* 校验申请金额（固定值） */
			String[] amounts = StringUtils.isBlank(relation.getSingleAmounts()) ? product.getSingleAmounts().split(",")
					: relation.getSingleAmounts().split(",");
			boolean applyIsError = applyAmount == null ? false : true;
			StringBuffer buf = new StringBuffer();
			int idx = 0;
			int last = amounts.length - 1;
			for (String amount : amounts) {
				BigDecimal a = new BigDecimal(amount);
				if (applyIsError && applyAmount.compareTo(a) == 0) {
					applyIsError = false;
				}
				buf.append(a.toString());
				if (idx++ < last) {
					buf.append(",");
				}
			}
			if (applyIsError) {
				logger.error("申请金额不在允许范围内");
				throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
			}
		}

		if (StringUtils.isNotBlank(loanProductApplyInfo.getTermUnit())
				&& loanProductApplyInfo.getTermCount() != null) {/* 校验借贷期限 */
			int termCount = Integer.parseInt(loanProductApplyInfo.getTermCount());
			if ("1".equals(loanProductApplyInfo.getTermUnit())) {/* 单位：日 */
				int daysLimitType = Integer.parseInt(product.getDaysLimitType());
				if (daysLimitType == 0) {/* 区间校验 */
					int minDays = Integer.parseInt(product.getSingleMinDays());
					int maxDays = Integer.parseInt(product.getSingleMaxDays());
					if (termCount < minDays || termCount > maxDays) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.TERMCOUNT_RULE_ERROR.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				} else {/* 固定值校验 */
					String[] days = product.getSingleDays().split(",");
					boolean isError = true;
					int idx = 0;
					int last = days.length - 1;
					StringBuffer buf = new StringBuffer();
					for (String day : days) {
						if (termCount == Integer.parseInt(day)) {
							isError = false;
							break;
						}
						buf.append(day);
						if (idx++ < last) {
							buf.append(",");
						}
					}
					if (isError) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.TERMCOUNT_RULE_ERROR.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				}
			} else {/* 单位：月 */
				int monthsLimitType = Integer.parseInt(product.getMonthsLimitType());
				if (monthsLimitType == 0) {/* 区间校验 */
					int minMonths = Integer.parseInt(product.getSingleMinMonths());
					int maxMonths = Integer.parseInt(product.getSingleMaxMonths());
					if (termCount < minMonths || termCount > maxMonths) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.TERMCOUNT_RULE_ERROR.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				} else {/* 固定值校验 */
					String[] months = product.getSingleMonths().split(",");
					boolean isError = true;
					int idx = 0;
					int last = months.length - 1;
					StringBuffer buf = new StringBuffer();
					for (String month : months) {
						if (termCount == Integer.parseInt(month)) {
							isError = false;
							break;
						}
						buf.append(month);
						if (idx++ < last) {
							buf.append(",");
						}
					}
					if (isError) {
						logger.error("借贷期限不在允许范围内");
						throw new BusinessException(ResponseConstants.TERMCOUNT_RULE_ERROR.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				}
			}
		}

		if (StringUtils.isNotEmpty(product.getRepayType())) {
			int index = product.getRepayType().indexOf(loanProductApplyInfo.getRepayType());
			if (index < 0) {
				logger.error("还款方式不在允许范围内");
				throw new BusinessException(ResponseConstants.REPAY_TYPE_RULE_ERROR.getRetCode(),
						ResponseConstants.REPAY_TYPE_RULE_ERROR.getRetMsg());
			}
		}

//		if (StringUtils.isNotEmpty(product.getGuaranteeType())) {
//			int index = product.getGuaranteeType().indexOf(loanProductApplyInfo.getGuaranteeType());
//			if (index < 0) {
//				logger.error("担保方式不在允许范围内");
//				throw new BusinessException(ResponseConstants.GUARANTEE_TYPE_RULE_ERROR.getRetCode(),
//						ResponseConstants.GUARANTEE_TYPE_RULE_ERROR.getRetMsg());
//			}
//		}

		LoanProductApplyEntity loanProductInfo = productInfoMapper.getLoanProductApplyById(loanProductApplyInfo.getId());
		if(loanProductInfo != null) {
			loanProductApplyInfo.setInterestRate(loanProductInfo.getInterestRate());
			loanProductApplyInfo.setPenaltyRate(loanProductInfo.getPenaltyRate());
		} else {
			loanProductApplyInfo.setInterestRate("");
			loanProductApplyInfo.setPenaltyRate("");
		}
		
		BigDecimal interestRate;
		if(loanProductApplyInfo.getInterestRate() == null) {
			interestRate = null;
		} else {
			interestRate = new BigDecimal(loanProductApplyInfo.getInterestRate());
		}
		
		if (interestRate != null) {/* 校验年化利率 */
			if (product.getInterestRateLimitType().equals("0")) {/* 区间校验 */
				BigDecimal minInterestRate = relation.getOrgMinInterestRate() == null
						? new BigDecimal(product.getMinInterestRate())
						: new BigDecimal(relation.getOrgMinInterestRate());
				BigDecimal maxInterestRate = relation.getOrgMaxInterestRate() == null
						? new BigDecimal(product.getMaxInterestRate())
						: new BigDecimal(relation.getOrgMaxInterestRate());
				if (interestRate.compareTo(minInterestRate) < 0 || interestRate.compareTo(maxInterestRate) > 0) {
					logger.error("年化利率不在允许范围内");
					throw new BusinessException(ResponseConstants.INTEREST_RATE_RULE_ERROR.getRetCode(),
							ResponseConstants.INTEREST_RATE_RULE_ERROR.getRetMsg());
				}
			} else {/* 固定值校验 */
				String[] interestRates = StringUtils.isBlank(relation.getInterestRates())
						? product.getInterestRates().split(",") : relation.getInterestRates().split(",");
				boolean isError = true;
				int idx = 0;
				int last = interestRates.length - 1;
				StringBuffer buf = new StringBuffer();
				for (String rate : interestRates) {
					BigDecimal r = new BigDecimal(rate);
					if (interestRate.compareTo(r) == 0) {
						isError = false;
						break;
					}
					buf.append(getPERCENTText(r));
					if (idx++ < last) {
						buf.append(",");
					}
				}
				if (isError) {
					logger.error("年化利率不在允许范围内");
					throw new BusinessException(ResponseConstants.INTEREST_RATE_RULE_ERROR.getRetCode(),
							ResponseConstants.INTEREST_RATE_RULE_ERROR.getRetMsg());
				}
			}
		}

		// 校验首付金额
//		if ("1".equals(product.getDownPaymentsEnable())) {// 启用
//			if (StringUtils.isEmpty(loanProductApplyInfo.getDownPaymentsAmount())) {
//				logger.error("首付金额不能为空");
//				throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_IS_EMPTY.getRetCode(),
//						ResponseConstants.DOWN_RAYMENT_AMOUNT_IS_EMPTY.getRetMsg());
//			}
//			// 首付金额范围检验
//			BigDecimal downPaymentsAmount = new BigDecimal(loanProductApplyInfo.getDownPaymentsAmount());
//			if ("0".equals(product.getDownPaymentsLimitType())) {// 区间值
//				BigDecimal minDownPayments = new BigDecimal(product.getMinDownPayments());
//				BigDecimal maxDownPayments = new BigDecimal(product.getMaxDownPayments());
//				if (downPaymentsAmount.compareTo(minDownPayments) < 0
//						|| downPaymentsAmount.compareTo(maxDownPayments) > 0) {
//					logger.error("首付金额不在允许范围内");
//					throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetCode(),
//							ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
//				}
//			} else {// 固定值
//				String[] downPayments = product.getDownPayments().split(",");
//				boolean isError = true;
//				for (String payment : downPayments) {
//					BigDecimal r = new BigDecimal(payment);
//					if (downPaymentsAmount.compareTo(r) == 0) {
//						isError = false;
//						break;
//					}
//				}
//				if (isError) {
//					logger.error("首付金额不在允许范围内");
//					throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetCode(),
//							ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
//				}
//			}
//		} else {
//			apply.setDownPaymentsAmount(null);
//		}

		BigDecimal penaltyRate;
		if(loanProductApplyInfo.getInterestRate() == null) {
			penaltyRate = null;
		} else {
			penaltyRate = new BigDecimal(loanProductApplyInfo.getPenaltyRate());
		}
		if (penaltyRate != null) {/* 校验罚息利率 */
			if (product.getPenaltyRateLimitType().equals("0")) {/* 区间校验 */
				BigDecimal minPenaltyRate = relation.getOrgMinPenaltyRate() == null
						? new BigDecimal(product.getMinPenaltyRate()) : new BigDecimal(relation.getOrgMinPenaltyRate());
				BigDecimal maxPenaltyRate = relation.getOrgMaxPenaltyRate() == null
						? new BigDecimal(product.getMaxPenaltyRate()) : new BigDecimal(relation.getOrgMaxPenaltyRate());
				if (penaltyRate.compareTo(minPenaltyRate) < 0 || penaltyRate.compareTo(maxPenaltyRate) > 0) {
					logger.error("罚息利率不在允许范围内");
					throw new BusinessException(ResponseConstants.LOAN_PENALTY_RATE_ERROR.getRetCode(),
							ResponseConstants.LOAN_PENALTY_RATE_ERROR.getRetMsg());
				}
			} else {/* 固定值校验 */
				String[] penaltyRates = StringUtils.isBlank(relation.getPenaltyRates())
						? product.getPenaltyRates().split(",") : relation.getPenaltyRates().split(",");
				boolean isError = true;
				int idx = 0;
				int last = penaltyRates.length - 1;
				StringBuffer buf = new StringBuffer();
				for (String rate : penaltyRates) {
					BigDecimal r = new BigDecimal(rate);
					if (penaltyRate.compareTo(r) == 0) {
						isError = false;
						break;
					}
					buf.append(getPERCENTText(r));
					if (idx++ < last) {
						buf.append(",");
					}
				}
				if (isError) {
					logger.error("罚息利率不在允许范围内");
					throw new BusinessException(ResponseConstants.LOAN_PENALTY_RATE_ERROR.getRetCode(),
							ResponseConstants.LOAN_PENALTY_RATE_ERROR.getRetMsg());
				}
			}
		}
		return apply;
	}

	/** 
	* @Title: modelDataValidate 
	* @Description: TODO(业务模型数据校验) 
	* @param @param jsonData
	* @param @param productId
	* @param @param loanApplyId
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean modelDataValidate(String jsonData, String productId, String loanApplyId, String type)
			throws BusinessException {
		boolean flag = true;
		if (StringUtils.isEmpty(jsonData)) {
			throw new BusinessException(ResponseConstants.BUSINESS_MODEL_DATA_EMPTY.getRetCode(),
					ResponseConstants.BUSINESS_MODEL_DATA_EMPTY.getRetMsg());
		}
		JSONObject modelDataJson = JSONObject.parseObject(jsonData);
		// 校验数据key值存在性
		if (!modelDataJson.containsKey("validate") || !modelDataJson.containsKey("id")
				|| !modelDataJson.containsKey("data")) {
			logger.error("业务模型数据格式错误");
			throw new BusinessException(ResponseConstants.BUSINESS_MODEL_DATA_ERROR.getRetCode(),
					ResponseConstants.BUSINESS_MODEL_DATA_ERROR.getRetMsg());
		}
		// 校验业务模型与产品关联的模型ID
		if (modelDataJson.get("id") == null || "".equals(modelDataJson.get("id").toString())) {
			throw new BusinessException(ResponseConstants.BUSINESS_MODEL_ID_EMPTY.getRetCode(),
					ResponseConstants.BUSINESS_MODEL_ID_EMPTY.getRetMsg());
		}
		if ("offline".equals(type)) {// 离线操作
			Map<String, Object> busMap = businessModelMapper.getBusModelById(modelDataJson.get("id").toString());
			if (busMap == null) {
				throw new BusinessException(ResponseConstants.BUSINESS_MODEL_NOT_EXIST.getRetCode(),
						ResponseConstants.BUSINESS_MODEL_NOT_EXIST.getRetMsg());
			}
		} else {// 在线操作
			// 获取产品关联的模型信息
			List<BusInfoModel> modelList = businessVersionModelMapper.getBusinessModelsByProductId(productId);
			if (modelList != null && modelList.size() > 0) {
				Map<String, Object> map = businessModelMapper.getBusRecordByBusId(loanApplyId);
				if (map != null) {// 当前借贷申请已绑定模型
					if (!map.get("busmodId").equals(modelDataJson.get("id").toString())) {
						throw new BusinessException(ResponseConstants.BUSINESS_MODEL_ID_ERROR.getRetCode(),
								ResponseConstants.BUSINESS_MODEL_ID_ERROR.getRetMsg());
					}
				} else {
					String modelId = modelList.get(0).getId();
					if (!modelId.equals(modelDataJson.get("id").toString())) {
						throw new BusinessException(ResponseConstants.BUSINESS_MODEL_ID_ERROR.getRetCode(),
								ResponseConstants.BUSINESS_MODEL_ID_ERROR.getRetMsg());
					}
				}
			} else {
				// 该产品未关联业务模型
				flag = false;
			}
		}
		return flag;
	}

	/** 
	* @Title: getPERCENTText 
	* @Description: TODO(获取百分比) 
	* @param @param d
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getPERCENTText(Object d) {
		return numberFormat(d, "#0.00%", RoundingMode.HALF_UP);
	}

	/** 
	* @Title: numberFormat 
	* @Description: TODO(数据格式化) 
	* @param @param num
	* @param @param format
	* @param @param rm
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String numberFormat(Object num, String format, RoundingMode rm) {
		if (num != null) {
			DecimalFormat df = new DecimalFormat(format, DecimalFormatSymbols.getInstance(Locale.CHINA));
			df.setRoundingMode(rm);
			return df.format(num);
		} else {
			return "";
		}
	}

	/** 
	* @Title: getLoanProductInfoById 
	* @Description: TODO(通过产品id获得产品信息) 
	* @param @param loanProductId
	* @param @return    设定文件 
	* @return LoanProductInfo    返回类型 
	* @throws 
	*/
	public LoanProductInfo getLoanProductInfoById(String loanProductId) {
		return productInfoMapper.queryByPrimaryId(loanProductId);
	}

	/** 
	* @Title: loanGuarantorInfoValidate 
	* @Description: TODO(借贷申请担保人信息校验) 
	* @param @param list
	* @param @param type
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void loanGuarantorInfoValidate(List<GuarantorInfo> list, String type) throws BusinessException {
		if (list != null && list.size() > 0) {
			// 校验浮点数，整数1-12位，小数0-2位（可以用来校验金额）
			String NUMBER_12_2 = "^\\d{1,12}(.\\d{1,2}){0,1}$";
			StringBuffer sb = new StringBuffer();
			for (GuarantorInfo guarantor : list) {
				if (StringUtils.isNotEmpty(guarantor.getIncome())) {
					logger.info("月均收入格式错误");
					if (!MatchUtil.match(guarantor.getIncome(), NUMBER_12_2)) {
						throw new BusinessException(ResponseConstants.INCOME_FORMAT_ERROR.getRetCode(),
								ResponseConstants.INCOME_FORMAT_ERROR.getRetMsg());
					}
				}
				// 校验担保人证件类型、证件号码不能重复（同一借贷申请）
				StringBuffer str = new StringBuffer(guarantor.getCredentialType()).append(",")
						.append(guarantor.getCredentialNo()).append(",");
				if (sb.toString().contains(str.toString())) {
					throw new BusinessException(
							type.equals("online") ? ResponseConstants.LOAN_APPLY_GUARANTOR_REPEAT.getRetCode()
									: ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
							ResponseConstants.LOAN_APPLY_GUARANTOR_REPEAT.getRetMsg());
				}
				sb.append(str);
			}
		}
	}
}
