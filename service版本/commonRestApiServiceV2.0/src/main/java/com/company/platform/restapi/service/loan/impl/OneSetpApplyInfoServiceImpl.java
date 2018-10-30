package com.company.platform.restapi.service.loan.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;
import com.company.platform.base.util.DataDictionaryUtil;
import com.company.platform.base.util.SysCodeGenerationUtil;
import com.company.platform.restapi.dao.custom.CustomerAlterRecordMapper;
import com.company.platform.restapi.dao.custom.CustomerHouseInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerJobDetailMapper;
import com.company.platform.restapi.dao.custom.CustomerVehicleInfoMapper;
import com.company.platform.restapi.dao.custom.SelfEmployeeInfoMapper;
import com.company.platform.restapi.dao.loan.OneSetpApplyInfoMapper;
import com.company.platform.restapi.dao.loan.OrgProductCreditMapper;
import com.company.platform.restapi.dao.loan.OrgProductRelationMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.model.basicdata.ThirdResult;
import com.company.platform.restapi.model.custom.ContactsInfo;
import com.company.platform.restapi.model.custom.CorporationCustomerForm;
import com.company.platform.restapi.model.custom.CustomerAlterRecord;
import com.company.platform.restapi.model.custom.CustomerHouseInfo;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.CustomerJobInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.CustomerVehicleInfo;
import com.company.platform.restapi.model.custom.EntCustomerInfo;
import com.company.platform.restapi.model.custom.PersonalCustomerInfo;
import com.company.platform.restapi.model.custom.SelfEmployeeInfo;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.LoanProductInfo;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoReq;
import com.company.platform.restapi.model.loan.OneSetpApplyInfoResp;
import com.company.platform.restapi.model.loan.OrgProductCredit;
import com.company.platform.restapi.model.loan.OrgProductRelation;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.service.identityauth.IdentityAuthService;
import com.company.platform.restapi.service.loan.ICorporationCustomerService;
import com.company.platform.restapi.service.loan.IOneSetpApplyInfoService;
import com.company.platform.security.model.SecurityUser;

/**
 * @ClassName: OneSetpApplyInfoServiceImp
 * @Description: TODO(贷款申请第一步提交)
 * @author yangxu
 * @date 2018年1月28日 下午1:11:53
 * 
 */
@Service
public class OneSetpApplyInfoServiceImpl implements IOneSetpApplyInfoService {
	// 日志
	private final Logger logger = LoggerFactory.getLogger(OneSetpApplyInfoServiceImpl.class);

	@Autowired
	OneSetpApplyInfoMapper oneSetpApplyInfoMapper;

	@Autowired
	CustomerInfoMapper customerInfoMapper;

	@Autowired
	OrgProductRelationMapper orgProductRelationMapper;

	@Autowired
	OrgProductCreditMapper orgProductCreditMapper;

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	CustomerJobDetailMapper customerJobDetailMapper;

	@Autowired
	CustomerHouseInfoMapper customerHouseInfoMapper;

	@Autowired
	SelfEmployeeInfoMapper selfEmployeeInfoMapper;

	@Autowired
	CustomerVehicleInfoMapper customerVehicleInfoMapper;

	@Autowired
	CustomerAlterRecordMapper customerAlterRecordMapper;

	@Resource
	private ICorporationCustomerService corporationCustomerService;

	@Autowired
	private IDataDictionaryService dataDictionaryService;
	
	@Autowired
	private IdentityAuthService identityAuthService;
	
	@Resource
	DataDictionaryUtil dictUtil;

	/*
	 * (非 Javadoc) <p>Title: oneSetpApplyInfoReqValidate</p> <p>Description:
	 * </p>
	 * 
	 * @param oneSetpApplyInfoReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.IOneSetpApplyInfoResp#
	 * oneSetpApplyInfoReqValidate(com.company.platform.restapi.model.loan.
	 * OneSetpApplyInfoReq)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OneSetpApplyInfoReq oneSetpApplyInfoReqValidate(OneSetpApplyInfoReq oneSetpApplyInfoReq)
			throws BusinessException {
		// 校验证件类型字典值
		Map<String, Map<String, Object>> map = dataDictionaryService.getDataMap();
		if ("0".equals(oneSetpApplyInfoReq.getCustomerType())) {// 个人客户
			if (!((Map<String, String>) map.get("credentialType").get("item"))
					.containsKey(oneSetpApplyInfoReq.getCredentialType())) {
				throw new BusinessException(ResponseConstants.CREDENTIAL_TYPE_ERROR.getRetCode(),
						ResponseConstants.LOAN_PRODUCT_APPLY_IS_EMPTY.getRetMsg());
			}
			String ID_NUMBER = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
			String MOBLIE_PHONE = "0?(13|14|15|18)[0-9]{9}";
			if ("0".equals(oneSetpApplyInfoReq.getCredentialType())) {// 校验身份证号
				if (!Pattern.matches(ID_NUMBER, oneSetpApplyInfoReq.getCredentialNo())) {
					throw new BusinessException(ResponseConstants.CREDENTIAL_NO_ERROR.getRetCode(),
							ResponseConstants.CREDENTIAL_NO_ERROR.getRetMsg());
				}else{
					// 申请人姓名身份证号码鉴权标记 0 校验 1不校验
					String IDENTITY_AUTH = dictUtil.getConfigData("IDENTITY_AUTH") == null ? "1"
							: dictUtil.getConfigData("IDENTITY_AUTH");
					if("0".equals(IDENTITY_AUTH)){
						// 调用大数据接口认证
						Map<String, Object> parameter = new HashMap<String, Object>();
						parameter.put("customerName", oneSetpApplyInfoReq.getName());
						parameter.put("credentialNo", oneSetpApplyInfoReq.getCredentialNo());
						try {
							ThirdResult thirdResult = identityAuthService.checkNameAndCard(parameter);
							if (thirdResult.isStatus() == false) {
								throw new BusinessException(ResponseConstants.REALNAMEAUTHFAIL.getRetCode(),
										ResponseConstants.REALNAMEAUTHFAIL.getRetMsg());
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(ResponseConstants.REALNAMEAUTHFAIL.getRetCode(),
									ResponseConstants.REALNAMEAUTHFAIL.getRetMsg());
						}
					}
				}
			}
			
			if(!Pattern.matches(MOBLIE_PHONE, oneSetpApplyInfoReq.getMobilePhone())) {
				throw new BusinessException(ResponseConstants.PHONE_NO_ERROR.getRetCode(),
						ResponseConstants.PHONE_NO_ERROR.getRetMsg());
			}
			
		} else if ("1".equals(oneSetpApplyInfoReq.getCustomerType())) {// 企业客户
			if (!((Map<String, String>) map.get("corCredentialType").get("item"))
					.containsKey(oneSetpApplyInfoReq.getCredentialType())) {
				throw new BusinessException(ResponseConstants.CREDENTIAL_TYPE_ERROR.getRetCode(),
						ResponseConstants.LOAN_PRODUCT_APPLY_IS_EMPTY.getRetMsg());
			}
		}
		Map<String, String> info = new HashMap<String, String>();
		info.put("credentialType", oneSetpApplyInfoReq.getCredentialType());
		info.put("credentialNo", oneSetpApplyInfoReq.getCredentialNo());

		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfo(info);

		String loanProductId = oneSetpApplyInfoReq.getLoanProductId();
		String orgId = oneSetpApplyInfoReq.getOrgId();

		LoanProductInfo product = productInfoMapper.queryByPrimaryId(loanProductId);
		OrgProductRelation relation = orgProductRelationMapper.selectByOrgIdAndLoanProductId(orgId, loanProductId);

		BigDecimal surplusAmount = null;// 还可申请借贷额度
		if (StringUtils.isNotBlank(oneSetpApplyInfoReq
				.getLoanProductId())) {/* 如果已选择产品，计算机构（操作人所在机构）还可申请此产品的借贷额度 */
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
			if (customerPublicInfo.getCustomerName() != null
					&& !oneSetpApplyInfoReq.getName().equals(customerPublicInfo.getCustomerName())) {
				logger.error("客户名称错误");
				throw new BusinessException(ResponseConstants.CUSTOMER_NAME_ERROR.getRetCode(),
						ResponseConstants.CUSTOMER_NAME_ERROR.getRetMsg());
			}
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
				&& surplusAmount.compareTo(new BigDecimal(oneSetpApplyInfoReq.getApplyAmount())) < 0) {
			logger.error("客户额度不足");
			throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetCode(),
					ResponseConstants.SURPLUS_AMOUNT_ERROR.getRetMsg());
		}

		BigDecimal applyAmount = new BigDecimal(oneSetpApplyInfoReq.getApplyAmount());
		int amountLimitType = Integer.parseInt(product.getAmountLimitType());
		if (amountLimitType == 0) {/* 校验申请金额和批复金额（区间） */
			BigDecimal minAmount = new BigDecimal(product.getSingleMinAmount());
			if (relation.getSingleMinAmount() != null) {
				minAmount = new BigDecimal(relation.getSingleMinAmount());
			}

			BigDecimal maxAmount = new BigDecimal(product.getSingleMaxAmount());
			if (relation.getSingleMaxAmount() != null) {
				maxAmount = new BigDecimal(relation.getSingleMaxAmount());
			}

			if (applyAmount != null && (applyAmount.compareTo(minAmount) < 0 || applyAmount.compareTo(maxAmount) > 0)) {
				logger.error("申请金额不在范围内");
				throw new BusinessException(ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetCode(),
						ResponseConstants.SURPLUS_AMOUNT_RULE_ERROR.getRetMsg());
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
		}

		if (StringUtils.isNotBlank(oneSetpApplyInfoReq.getTermUnit())
				&& oneSetpApplyInfoReq.getTermCount() != null) {/* 校验借贷期限 */
			int termCount = Integer.parseInt(oneSetpApplyInfoReq.getTermCount());
			if ("1".equals(oneSetpApplyInfoReq.getTermUnit())) {/* 单位：日 */
				int daysLimitType = Integer.parseInt(product.getDaysLimitType());
				if (daysLimitType == 0) {/* 区间校验 */
					int minDays = Integer.parseInt(product.getSingleMinDays());
					int maxDays = Integer.parseInt(product.getSingleMaxDays());
					if (termCount < minDays || termCount > maxDays) {
						logger.error("借贷期限不在范围内");
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
						logger.error("借贷期限不在范围内");
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
						logger.error("借贷期限不在范围内");
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
						logger.error("借贷期限单位不在范围内");
						throw new BusinessException(ResponseConstants.TERMCOUNT_RULE_ERROR.getRetCode(),
								ResponseConstants.TERMCOUNT_RULE_ERROR.getRetMsg());
					}
				}
			}
			// 校验首付金额
//			if ("1".equals(product.getDownPaymentsEnable())) {// 启用
//				if (StringUtils.isEmpty(oneSetpApplyInfoReq.getDownPaymentsAmount())) {
//					logger.error("首付金额不能为空");
//					throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_IS_EMPTY.getRetCode(),
//							ResponseConstants.DOWN_RAYMENT_AMOUNT_IS_EMPTY.getRetMsg());
//				}
//				BigDecimal downPaymentsAmount = new BigDecimal(oneSetpApplyInfoReq.getDownPaymentsAmount());
//				// 首付金额范围检验
//				if ("0".equals(product.getDownPaymentsLimitType())) {// 区间值
//					BigDecimal minDownPayments = new BigDecimal(product.getMinDownPayments());
//					BigDecimal maxDownPayments = new BigDecimal(product.getMaxDownPayments());
//					if (downPaymentsAmount.compareTo(minDownPayments) < 0 || downPaymentsAmount.compareTo(maxDownPayments) > 0) {
//						logger.error("首付金额不在允许范围内");
//						throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetCode(),
//								ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
//					}
//				} else {// 固定值
//					String[] downPayments = product.getDownPayments().split(",");
//					boolean isError = true;
//					for (String payment : downPayments) {
//						BigDecimal r = new BigDecimal(payment);
//						if (downPaymentsAmount.compareTo(r) == 0) {
//							isError = false;
//							break;
//						}
//					}
//					if (isError) {
//						logger.error("首付金额不在允许范围内");
//						throw new BusinessException(ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetCode(),
//								ResponseConstants.DOWN_RAYMENT_AMOUNT_RULE_ERROR.getRetMsg());
//					}
//				}
//			} else {
//				oneSetpApplyInfoReq.setDownPaymentsAmount(null);
//			}
		}
		return oneSetpApplyInfoReq;
	}

	/*
	 * (非 Javadoc) <p>Title: getOneSetpApplyInfo</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return OneSetpApplyInfoResp
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.IOneSetpApplyInfoResp#
	 * getOneSetpApplyInfo(com.company.platform.restapi.model.loan.
	 * OneSetpApplyInfoReq)
	 */
	@Override
	public OneSetpApplyInfoResp getOneSetpApplyInfo(String id) throws BusinessException {
		// 得到输出参数的信息
		OneSetpApplyInfoResp applyInfo = new OneSetpApplyInfoResp();
		CustomerInfo customerInfo = customerInfoMapper.getCustomerinfo(id);
		EntCustomerInfo entCustomerInfo = customerInfoMapper.getEntCustomerInfo(id);
		List<ContactsInfo> contactsInfo = customerInfoMapper.getContactsInfo(id);

		applyInfo.setLoanProductApplyId(id);
		if (customerInfo != null) {
			applyInfo.setCustomerInfo(customerInfo);
		}

		if (entCustomerInfo != null) {
			applyInfo.setEntCustomerInfo(entCustomerInfo);
		}

		if (contactsInfo != null && contactsInfo.size() > 0) {
			applyInfo.setContactsInfo(contactsInfo);
		}

		return applyInfo;
	}

	/*
	 * (非 Javadoc) <p>Title: insertProductApplyInfo</p> <p>Description: </p>
	 * 
	 * @param oneSetpApplyInfoReq
	 * 
	 * @return String
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.loan.IOneSetpApplyInfoResp#
	 * insertProductApplyInfo(com.company.platform.restapi.model.loan.
	 * OneSetpApplyInfoReq)
	 */
	@Override
	public FullLoanProductApplyInfo insertProductApplyInfo(OneSetpApplyInfoReq oneSetpApplyInfoReq, SecurityUser user,
			HttpServletRequest request) throws BusinessException {

		String id = UUID.randomUUID().toString();
		String loanProductId = oneSetpApplyInfoReq.getLoanProductId();
		String code = oneSetpApplyInfoReq.getCode();
		String applyAmount = oneSetpApplyInfoReq.getApplyAmount();
		String termCount = oneSetpApplyInfoReq.getTermCount();
		String termUnit = oneSetpApplyInfoReq.getTermUnit();
		String orgId = oneSetpApplyInfoReq.getOrgId();
		String customerId = UUID.randomUUID().toString();
		FullLoanProductApplyInfo LoanProductApplyInfo = new FullLoanProductApplyInfo();

		LoanProductApplyInfo.setId(id);
		LoanProductApplyInfo.setLoanProductId(loanProductId);
		LoanProductApplyInfo.setCode(code);
		LoanProductApplyInfo.setApplyAmount(applyAmount);
		LoanProductApplyInfo.setTermCount(termCount);
		LoanProductApplyInfo.setTermUnit(termUnit);
		LoanProductApplyInfo.setRepayTermCount("1");
		LoanProductApplyInfo.setRepayTermUnit("2");
		LoanProductApplyInfo.setOrgId(orgId);
//		LoanProductApplyInfo.setDownPaymentsAmount(oneSetpApplyInfoReq.getDownPaymentsAmount());
		LoanProductInfo productInfo = productInfoMapper.queryByPrimaryId(loanProductId);
		LoanProductApplyInfo.setInterestRate(productInfo.getDefaultInterestRate());
		LoanProductApplyInfo.setPenaltyRate(productInfo.getDefaultPenaltyRate());
		

		LoanProductApplyInfo.setStatus("0");
		LoanProductApplyInfo.setActionStatus("0");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		LoanProductApplyInfo.setRegisterTime(dateString);
		if (StringUtils.isBlank(oneSetpApplyInfoReq.getApplyTime())) {
			LoanProductApplyInfo.setApplyTime(dateString);
		} else {
			LoanProductApplyInfo.setApplyTime(oneSetpApplyInfoReq.getApplyTime());
		}
		LoanProductApplyInfo.setRegisterId(user.getId());
		LoanProductApplyInfo.setAccountManagerId(user.getId());
		LoanProductApplyInfo.setUpdateTime(dateString);

		String customerType = oneSetpApplyInfoReq.getCustomerType();
		String credentialType = oneSetpApplyInfoReq.getCredentialType();
		String credentialNo = oneSetpApplyInfoReq.getCredentialNo();
		String name = oneSetpApplyInfoReq.getName();
		String mobilePhone = oneSetpApplyInfoReq.getMobilePhone();
		
		Map<String, String> info = new HashMap<String, String>();
		info.put("credentialType", credentialType);
		info.put("credentialNo", credentialNo);
		
		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfo(info);
		CustomerPublicInfo customerPublic = null;
		PersonalCustomerInfo personalCustomerInfo = null;
		CorporationCustomerForm corporationCustomer = null;

		if (customerPublicInfo == null || customerPublicInfo.getId() == null) {
			if ("0".equals(customerType)) {// 个人
				customerPublic = new CustomerPublicInfo();
				customerPublic.setId(customerId);
				customerPublic
						.setCustomerNo(SysCodeGenerationUtil.getInstance().getCode("CUSTOMER_TYPE", null, request));
				customerPublic.setOrgId(orgId);
				customerPublic.setCredentialType(credentialType);
				customerPublic.setCredentialNo(credentialNo);
				customerPublic.setCustomerName(name);
				customerPublic.setMobilePhone(mobilePhone);
				customerPublic.seteType("0");
				customerPublic.setWorkType("0");
				customerPublic.seteStatus("0");
				customerPublic.setDeleteStatus("0");
				customerPublic.setIsBlackList("0");

				customerPublic.setCreateTime(dateString);
				customerPublic.setUpdateTime(dateString);
				customerPublic.setCreateUserId(user.getId());
				customerPublic.setIsRestricted("0");
				customerPublic.setAuthority("1");
				customerPublic.setUsedCredit("0");
				personalCustomerInfo = new PersonalCustomerInfo();
				personalCustomerInfo.setId(customerId);
				personalCustomerInfo.setCustomerName(name);
				personalCustomerInfo.setCredentialType(credentialType);
				personalCustomerInfo.setCredentialNo(credentialNo);
				String gender = "1";
				if ("0".equals(credentialType)) {
					if (credentialNo.length() == 15) {
						gender = credentialNo.substring(14, 15);
						if ("x".equalsIgnoreCase(gender) || Integer.parseInt(gender) % 2 == 0) {
							gender = "2";
						} else {
							gender = "1";
						}
					} else {
						gender = credentialNo.substring(16, 17);
						if (Integer.parseInt(gender) % 2 == 0) {
							gender = "2";
						} else {
							gender = "1";
						}
					}
				}
				personalCustomerInfo.setGender(gender);
				if ("0".equals(credentialType)) {
					String birthdayStr = credentialNo.substring(6, 10) + credentialNo.substring(10, 12)
							+ credentialNo.substring(12, 14);
					personalCustomerInfo.setBirthday(birthdayStr);
				}
			} else {// 企业
				corporationCustomer = new CorporationCustomerForm();
				corporationCustomer.setCustomerId(customerId);// 客户ID
				corporationCustomer
						.setCustomerNo(SysCodeGenerationUtil.getInstance().getCode("CUSTOMER_TYPE", null, request));// 客户编号
				corporationCustomer.setCustomerName(name);// 客户名称
				corporationCustomer.setCredentialType(credentialType);// 证件类型
				corporationCustomer.setCredentialNo(credentialNo);// 证件号码
				corporationCustomer.setOrgId(orgId);// 组织ID
				corporationCustomer.setCustomerStatus("0");// 客户状态 默认为生效
				corporationCustomer.setIfBlack("0");// 是否黑名单 默认非黑名单
				corporationCustomer.setIfRestrict("0");// 是否受限 默认非受限
				corporationCustomer.setStaffCount("0");// 企业规模
			}
		} else {
			LoanProductApplyInfo.setCustomerId(customerPublicInfo.getId());
			if (customerPublicInfo.geteStatus() != null && "1".equals(customerPublicInfo.geteStatus())) {
				logger.error("未生效客户");
				throw new BusinessException(ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getDeleteStatus() != null
					&& "true".equals(customerPublicInfo.getDeleteStatus())) {
				logger.error("注销客户");
				throw new BusinessException(ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsBlackList() != null
					&& ("true".equals(customerPublicInfo.getIsBlackList())
							|| "1".equals(customerPublicInfo.getIsBlackList()))) {
				logger.error("黑名单客户");
				throw new BusinessException(ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsRestricted() != null
					&& ("true".equals(customerPublicInfo.getIsRestricted())
							|| "1".equals(customerPublicInfo.getIsRestricted()))) {
				logger.error("受限客户");
				throw new BusinessException(ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsRisk() != null
					&& ("true".equals(customerPublicInfo.getIsRisk()) || "1".equals(customerPublicInfo.getIsRisk()))) {
				logger.error("风险客户");
				throw new BusinessException(ResponseConstants.RISK_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.RISK_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getOrgId() != null && !orgId.equals(customerPublicInfo.getOrgId())) {
				logger.error("请前往pc端进行客户迁移");
				throw new BusinessException(ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetMsg());
			}
		}

		/* 借贷申请第一步 */
		if (customerPublic != null) {
			LoanProductApplyInfo.setCustomerId(customerPublic.getId());

			oneSetpApplyInfoMapper.insertCustomerInfo(customerPublic);
			if (personalCustomerInfo != null) {
				oneSetpApplyInfoMapper.insertPersonalCustomerInfo(personalCustomerInfo);
			}
			CustomerJobInfo customerJobDetail = new CustomerJobInfo();
			customerJobDetail.setId(customerPublic.getId());
			customerJobDetail.setMonthIncome("0");
			customerJobDetail.seteType("0");
			customerJobDetail.setCurrentWorkInfo("");
			customerJobDetail.setCurrentDepInfo("");
			customerJobDetail.setCurrentWorkType("");
			customerJobDetail.setCurrentPosition("");
			customerJobDetail.setTotalWorkAge("0");
			customerJobDetail.setIndustryType("");
			customerJobDetailMapper.insertCustomerJobInfo(customerJobDetail);
			CustomerHouseInfo customerHouseInfo = new CustomerHouseInfo();
			customerHouseInfo.setId(customerPublic.getId());
			customerHouseInfo.setHasHouse("0");
			customerHouseInfo.setOwnHouseFlag("1");
			customerHouseInfo.setOwnHouseArea("0");
			customerHouseInfo.setCurrentHouseType("");
			customerHouseInfo.setCurrentHouseArea("0");
			customerHouseInfoMapper.insertCustomerHouseInfo(customerHouseInfo);
			SelfEmployeeInfo selfEmployeeInfo = new SelfEmployeeInfo();
			selfEmployeeInfo.setId(customerPublic.getId());
			selfEmployeeInfo.seteType("");
			selfEmployeeInfo.setMonthIncome("0");
			selfEmployeeInfo.setEnterpriseName("");
			selfEmployeeInfo.setTurnover("0");
			selfEmployeeInfo.setMainBusiness("");
			selfEmployeeInfo.setShareRadio("0");
			selfEmployeeInfoMapper.insertSelfEmployeeInfo(selfEmployeeInfo);
			CustomerVehicleInfo customerVehicleInfo = new CustomerVehicleInfo();
			customerVehicleInfo.setId(customerPublic.getId());
			customerVehicleInfo.setHasVehicle("0");
			customerVehicleInfo.setVehicleNo("0");
			customerVehicleInfoMapper.insertCustomerVehicleInfo(customerVehicleInfo);
		} else if (corporationCustomer != null) { // 增加企业客户信息
			LoanProductApplyInfo.setCustomerId(corporationCustomer.getCustomerId());
			Boolean flag = corporationCustomerService.merge(corporationCustomer, user.getId());
			if (!flag) {
				CustomerAlterRecord record = new CustomerAlterRecord();
				record.setId(UUID.randomUUID().toString());
				record.seteType("0");
				record.setCustomerId(corporationCustomer.getCustomerId());
				record.setAlterTime(dateString);
				record.seteDesc("");
				record.setOperaterId(user.getId());
				customerAlterRecordMapper.insertCustomerAlterRecord(record);
			}
		}

		oneSetpApplyInfoMapper.insertProductApplyInfo(LoanProductApplyInfo);

		return LoanProductApplyInfo;
	}
}
