package com.company.platform.restapi.service.loan.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.service.activiti.ITaskSetManagerService;
import com.company.platform.base.util.DataDictionaryUtil;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.loan.OrgProductCreditMapper;
import com.company.platform.restapi.dao.loan.OrgProductRelationMapper;
import com.company.platform.restapi.dao.loan.ProductApplyInfoMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.dao.loan.UserDeptRelMapper;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.OrgProductCredit;
import com.company.platform.restapi.model.loan.OrgProductRelation;
import com.company.platform.restapi.model.loan.deal.LoanProductApplyStorage;
import com.company.platform.restapi.service.loan.ILoanApplyCompleteInfoService;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.security.model.SecurityUser;

/**
 * @ClassName: LoanApplyCompleteInfoServiceImpl
 * @Description: TODO(借贷信息最终处理)
 * @author yangxu
 * @date 2018年2月1日 下午2:57:21
 * 
 */
@Service
public class LoanApplyCompleteInfoServiceImpl implements ILoanApplyCompleteInfoService {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanApplyCompleteInfoServiceImpl.class);

	@Resource
	DataDictionaryUtil dictUtil;

	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Autowired
	private ProductApplyInfoMapper productApplyInfoMapper;

	@Autowired
	private OrgProductRelationMapper orgProductRelationMapper;

	@Autowired
	private UserDeptRelMapper userDeptRelMapper;

	@Resource
	private ILoanProductApplyService loanProductApplyService;

	@Resource
	private CustomerInfoMapper customerInfoMapper;

	@Resource
	private OrgProductCreditMapper orgProductCreditMapper;

	@Resource
	private ITaskSetManagerService taskSetManagerService;

	/*
	 * (非 Javadoc) <p>Title: applyCompleteInfoReqValidate</p> <p>Description:
	 * </p>
	 * 
	 * @param loanProductApplyCompleteInfoReq
	 * 
	 * @throws BusinessException
	 * 
	 * @see
	 * com.company.platform.restapi.service.loan.ILoanApplyCompleteInfoService#
	 * applyCompleteInfoReqValidate(com.company.platform.restapi.model.loan.
	 * LoanProductApplyCompleteInfoReq)
	 */
	@Override
	public FullLoanProductApplyInfo applyCompleteInfoReqValidate(
			LoanProductApplyStorage req, HttpServletRequest request)
			throws BusinessException {
		String modelData = req.getModelData();
		JSONObject modelDataJson = StringUtils.isNotBlank(modelData) ? JSONObject.parseObject(modelData) : null;
		FullLoanProductApplyInfo loanProductApply = req.getLoanProductApplyInfo();
		if ("2".equals(loanProductApply.getTermUnit()) && loanProductApply.getRepayTermUnit() != null
				&& !"2".equals(loanProductApply.getRepayTermUnit())) {// 借贷期限选择"月"，还款间隔不支持选择"日"
			throw new BusinessException(ResponseConstants.TERM_UNIT_NOT_PASS.getRetCode(),
					ResponseConstants.TERM_UNIT_NOT_PASS.getRetMsg());
		} else if ("3".equals(loanProductApply.getRepayType())) {
			// 还款方式 == 3（等额本息），需借贷期限单位、还款间隔单位均为2（月）
			if (!"2".equals(loanProductApply.getTermUnit()) || !"2".equals(loanProductApply.getRepayTermUnit())) {
				throw new BusinessException(ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetCode(),
						ResponseConstants.REPAY_TYPE_TERM_UNIT_ERROR.getRetMsg());
			}
		}
		if (StringUtils.isNotEmpty(req.getAuths())) {
			if ("1".equals(req.getAuths())) {// 审核中
				if (StringUtils.isEmpty(loanProductApply.getReplyAmount())) {
					throw new BusinessException(ResponseConstants.REPLY_AMOUNT_NOT_EMPTY.getRetCode(),
							ResponseConstants.REPLY_AMOUNT_NOT_EMPTY.getRetMsg());
				} else if (StringUtils.isEmpty(loanProductApply.getAuthTermCount())) {
					throw new BusinessException(ResponseConstants.AUTH_TERM_COUNT_NOT_EMPTY.getRetCode(),
							ResponseConstants.AUTH_TERM_COUNT_NOT_EMPTY.getRetMsg());
				} else if (StringUtils.isEmpty(loanProductApply.getAuthTermUnit())) {
					throw new BusinessException(ResponseConstants.AUTH_TERM_TERM_NOT_EMPTY.getRetCode(),
							ResponseConstants.AUTH_TERM_TERM_NOT_EMPTY.getRetMsg());
				}
				if ("2".equals(loanProductApply.getAuthTermUnit()) && loanProductApply.getRepayTermUnit() != null
						&& !"2".equals(loanProductApply.getRepayTermUnit())) {// 批准期限选择"月"，还款间隔不支持选择"日"
					throw new BusinessException(ResponseConstants.REPLY_TERM_UNIT_NOT_PASS.getRetCode(),
							ResponseConstants.REPLY_TERM_UNIT_NOT_PASS.getRetMsg());
				} else if ("3".equals(loanProductApply.getRepayType())) {
					// 还款方式 == 3（等额本息），需借贷期限单位、还款间隔单位均为2（月）
					if (!"2".equals(loanProductApply.getAuthTermUnit())
							|| !"2".equals(loanProductApply.getRepayTermUnit())) {
						throw new BusinessException(ResponseConstants.REPAY_TERM_UNIT_NOT_PASS.getRetCode(),
								ResponseConstants.REPAY_TERM_UNIT_NOT_PASS.getRetMsg());
					}
				}
			} else if (!"0".equals(req.getAuths())) {
				throw new BusinessException(ResponseConstants.PROCESS_AUDIT_ERROR.getRetCode(),
						ResponseConstants.PROCESS_AUDIT_ERROR.getRetMsg());
			}
		}
		boolean auditFlag = true;
		if (StringUtils.isBlank(req.getAuths())
				|| "0".equals(req.getAuths())) {
			auditFlag = false;
		}
		return checkApply(loanProductApply, auditFlag, modelDataJson);
	}

	/*
	 * (非 Javadoc) <p>Title: insertapplyCompleteInfo</p> <p>Description: </p>
	 * 
	 * @param loanProductApplyCompleteInfoReq
	 * 
	 * @param request
	 * 
	 * @throws BusinessException
	 * 
	 * @see
	 * com.company.platform.restapi.service.loan.ILoanApplyCompleteInfoService#
	 * insertapplyCompleteInfo(com.company.platform.restapi.model.loan.
	 * LoanProductApplyCompleteInfoReq, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void insertapplyCompleteInfo(LoanProductApplyStorage req, HttpServletRequest request)
			throws BusinessException {
		try {
			String modelData = req.getModelData();// 获得业务模型数据
			FullLoanProductApplyInfo loanProductApply = req.getLoanProductApplyInfo();// 获得完整借贷申请数据
			String audit = req.getAuths();// 流程节点操作权限，第一步流程启动值为null
			SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = user.getId();
			if (loanProductApply.getRepayType() != null && "1".equals(loanProductApply.getRepayType())) {
				// 还款方式=1（一次性还本付息），还款间隔及其单位为null
				loanProductApply.setRepayTermCount(null);
				loanProductApply.setRepayTermUnit(null);
			}

			if (StringUtils.isBlank(audit)
					|| "0".equals(audit)) {/* 权限为空，则为启动流程之前保存或提交借贷信息 */
				loanProductApplyService.saveOrUpdateLoanProductApply(loanProductApply, BigDecimal.ZERO, "2", userId,
						req.getNextTaskKey(), req.getAuditUserId(), null, req.getModelData(), req.getGuarantorInfo());
			} else {/* 权限不为空，则为启动流程之后，保存借贷信息 */
				// 获取流程权限信息
				Map<String, Object> map = taskSetManagerService.getProcessAuths(loanProductApply.getLoanProductId(),
						req.getProcessDefinitionKey(), req.getProcessInstanceId(), req.getTaskKey());
				Object auths = map.get("auths");
				Map<String, String> authsMap = new HashMap<String, String>();
				if (auths != null) {
					JSONArray authArray = (JSONArray) auths;
					Object[] authObjs = authArray.toArray();
					for (Object authObj : authObjs) {
						JSONObject auth = (JSONObject) authObj;
						authsMap.put(auth.getString("modole"), auth.getString("permissions"));
					}
				} else {
					Map<String, String> taskPermissionsModule = dictUtil.getDictDataByType("taskPermissionsModule");
					Iterator<String> keys = taskPermissionsModule.keySet().iterator();
					while (keys.hasNext()) {
						authsMap.put(keys.next(), "non");
					}
				}
				loanProductApplyService.updateLoanProductApply(loanProductApply, modelData, authsMap, BigDecimal.ZERO,
						req.getGuarantorInfo());
			}
			logger.info("贷款申请成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("贷款申请失败");
			throw new BusinessException(ResponseConstants.LOAN_APPLY_ERROR.getRetCode(),
					ResponseConstants.LOAN_APPLY_ERROR.getRetMsg());
		}
	}

	public FullLoanProductApplyInfo checkApply(FullLoanProductApplyInfo apply, boolean auditFlag, JSONObject modalData)
			throws BusinessException {
		if (StringUtils.isNotBlank(apply.getId())) {/* 编辑借贷 */
			FullLoanProductApplyInfo applyTemp = productApplyInfoMapper.getLoanProductApplyById(apply.getId());
			if (!auditFlag && !"0".equals(applyTemp.getStatus())) {
				logger.error("该借贷申请已经提交！");
				throw new BusinessException(ResponseConstants.SURPLUS_REPETITION_ERROR.getRetCode(),
						ResponseConstants.SURPLUS_REPETITION_ERROR.getRetMsg());
			}
			apply.setLoanProductId(applyTemp.getLoanProductId());
			apply.setOrgId(applyTemp.getOrgId());
			apply.setStatus(applyTemp.getStatus());
		} else {/* 新增借贷 */
			SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = user.getId();
			List<String> orgId = userDeptRelMapper.getQueryOrgIdByUserId(userId);
			if (orgId.get(0) != null) {
				apply.setOrgId(orgId.get(0));
				apply.setStatus("0");
			}
		}

		LoanProductInfo product = productInfoMapper.queryByPrimaryId(apply.getLoanProductId());
		OrgProductRelation relation = orgProductRelationMapper.selectByOrgIdAndLoanProductId(apply.getOrgId(),
				apply.getLoanProductId());

		// 客户额度、组织机构额度校验
		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfoById(apply.getCustomerId());

		BigDecimal surplusAmount = null;// 还可申请借贷额度
		if (StringUtils.isNotBlank(apply.getLoanProductId())) {
			/* 如果已选择产品，计算机构（操作人所在机构）还可申请此产品的借贷额度 */
			if (relation != null && relation.getTotalAmount() != null) {
				BigDecimal totalAmount = new BigDecimal(relation.getTotalAmount());// 机构可用额度
				if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
					logger.error("组织机构额度不足");
					throw new BusinessException(ResponseConstants.ORG_SURPLUS_AMOUNT_ERROR.getRetCode(),
							ResponseConstants.ORG_SURPLUS_AMOUNT_ERROR.getRetMsg());
				}
				BigDecimal totalUsedCredit = BigDecimal.ZERO;// 已使用额度
				OrgProductCredit orgProductCredit = orgProductCreditMapper
						.selectByOrgIdAndLoanProductId(apply.getOrgId(), apply.getLoanProductId());
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
			throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetCode(),
					ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetMsg());
		}

		BigDecimal applyAmount = new BigDecimal(apply.getApplyAmount());
		if (product.getAmountLimitType().equals("0")) {/* 校验申请金额和批复金额（区间） */
			BigDecimal minAmount = relation.getSingleMinAmount() == null ? new BigDecimal(product.getSingleMinAmount())
					: new BigDecimal(relation.getSingleMinAmount());
			BigDecimal maxAmount = relation.getSingleMaxAmount() == null ? new BigDecimal(product.getSingleMaxAmount())
					: new BigDecimal(relation.getSingleMaxAmount());
			if (applyAmount != null && (applyAmount.compareTo(minAmount) < 0 || applyAmount.compareTo(maxAmount) > 0)) {
				logger.error("申请金额不在范围内");
				throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
			}
			if (auditFlag && "1".equals(apply.getStatus())) {
				BigDecimal replyAmount = new BigDecimal(apply.getReplyAmount());
				if (replyAmount.compareTo(minAmount) < 0 || replyAmount.compareTo(maxAmount) > 0) {
					logger.error("批复金额不在范围内");
					throw new BusinessException(ResponseConstants.REPLY_AMOUNT_NOT_RANGE.getRetCode(),
							ResponseConstants.REPLY_AMOUNT_NOT_RANGE.getRetMsg());
				}
				// 批复金额 <= 申请金额
				if (applyAmount.compareTo(replyAmount) < 0) {
					logger.error("批复金额不能大于申请金额");
					throw new BusinessException(ResponseConstants.APPLY_REPLY_AMOUNT_COMPARE_ERROR.getRetCode(),
							ResponseConstants.APPLY_REPLY_AMOUNT_COMPARE_ERROR.getRetMsg());
				}
			}

		} else {/* 校验申请金额和批复金额（固定值） */
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
				logger.error("申请金额不在范围内");
				throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
			}
			if (auditFlag && "1".equals(apply.getStatus())) {
				BigDecimal replyAmount = new BigDecimal(apply.getReplyAmount());
				boolean replyIsError = apply.getReplyAmount() == null ? false : true;
				StringBuffer buf1 = new StringBuffer();
				int idx1 = 0;
				int last1 = amounts.length - 1;
				for (String amount : amounts) {
					BigDecimal a = new BigDecimal(amount);
					if (replyIsError && replyAmount.compareTo(a) == 0) {
						replyIsError = false;
					}
					buf1.append(a.toString());
					if (idx1++ < last1) {
						buf1.append(",");
					}
				}
				if (replyIsError) {
					logger.error("批复金额不在范围内");
					throw new BusinessException(ResponseConstants.REPLY_AMOUNT_NOT_RANGE.getRetCode(),
							ResponseConstants.REPLY_AMOUNT_NOT_RANGE.getRetMsg());
				}
				// 批复金额 <= 申请金额
				if (applyAmount.compareTo(replyAmount) < 0) {
					logger.error("批复金额不能大于申请金额");
					throw new BusinessException(ResponseConstants.APPLY_REPLY_AMOUNT_COMPARE_ERROR.getRetCode(),
							ResponseConstants.APPLY_REPLY_AMOUNT_COMPARE_ERROR.getRetMsg());
				}
			}
		}

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
		if ("1".equals(product.getDownPaymentsEnable())) {// 启用
			if (StringUtils.isEmpty(apply.getDownPaymentsAmount())) {
				logger.error("首付金额不能为空");
				throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_IS_EMPTY.getRetCode(),
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
					throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetCode(),
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
					throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetCode(),
							ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
				}
			}
		} else {
			apply.setDownPaymentsAmount(null);
		}

		BigDecimal penaltyRate = new BigDecimal(apply.getPenaltyRate());
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

		if (StringUtils.isNotBlank(apply.getTermUnit()) && apply.getTermCount() != null) {/* 校验借贷期限 */
			int termCount = Integer.parseInt(apply.getTermCount());
			if ("1".equals(apply.getTermUnit())) {/* 单位：日 */
				if (product.getDaysLimitType().equals("0")) {/* 区间校验 */
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
				if (product.getMonthsLimitType().equals("0")) {/* 区间校验 */
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
		if (auditFlag && "1".equals(apply.getStatus()) && StringUtils.isNotBlank(apply.getAuthTermUnit())
				&& apply.getAuthTermCount() != null) {/* 校验批准期限 */
			int termCount = Integer.parseInt(apply.getAuthTermCount());
			if ("1".equals(apply.getAuthTermUnit())) {/* 单位：日 */
				if (product.getDaysLimitType().equals("0")) {/* 区间校验 */
					int minDays = Integer.parseInt(product.getSingleMinDays());
					int maxDays = Integer.parseInt(product.getSingleMaxDays());
					if (termCount < minDays || termCount > maxDays) {
						logger.error("批准期限不在范围内");
						throw new BusinessException(ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetCode(),
								ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetMsg());
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
						logger.error("批准期限不在范围内");
						throw new BusinessException(ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetCode(),
								ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetMsg());
					}
				}
			} else {/* 单位：月 */
				if (product.getMonthsLimitType().equals("0")) {/* 区间校验 */
					int minMonths = Integer.parseInt(product.getSingleMinMonths());
					int maxMonths = Integer.parseInt(product.getSingleMaxMonths());
					if (termCount < minMonths || termCount > maxMonths) {
						logger.error("批准期限不在范围内");
						throw new BusinessException(ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetCode(),
								ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetMsg());
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
						logger.error("批准期限不在范围内");
						throw new BusinessException(ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetCode(),
								ResponseConstants.AUTH_TERM_COUNT_NOT_RANGE.getRetMsg());
					}
				}
			}
		}
		return apply;
	}

	public String getPERCENTText(Object d) {
		return numberFormat(d, "#0.00%", RoundingMode.HALF_UP);
	}

	public String numberFormat(Object num, String format, RoundingMode rm) {
		if (num != null) {
			DecimalFormat df = new DecimalFormat(format, DecimalFormatSymbols.getInstance(Locale.CHINA));
			df.setRoundingMode(rm);
			return df.format(num);
		} else {
			return "";
		}
	}
}