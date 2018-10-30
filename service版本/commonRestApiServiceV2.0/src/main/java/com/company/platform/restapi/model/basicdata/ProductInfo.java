package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: ProductInfo 
* @Description: TODO(产品基本信息) 
* @author liang
* @date 2018年1月25日 下午1:53:02 
*  
*/
/**
 * @ClassName: ProductInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author liang
 * @date 2018年1月25日 下午2:08:07
 * 
 */
@SuppressWarnings("all")
public class ProductInfo extends BaseModel {

	/**
	 * @Fields id : TODO(产品id)
	 */
	private String id;

	/**
	 * @Fields name : TODO(产品名称)
	 */
	private String name;

	/**
	 * @Fields productType : TODO(产品类型：对应字典表productType)
	 */
	private String productType;

	/**
	 * @Fields amountLimitType : TODO(申请金额限制类型:0：区间 1：固定值)
	 */
	private String amountLimitType;

	/**
	 * @Fields singleMinAmount : TODO(单笔最小金额)
	 */
	private String singleMinAmount;

	/**
	 * @Fields singleMaxAmount : TODO(单笔最大金额)
	 */
	private String singleMaxAmount;

	/**
	 * @Fields singleAmounts : TODO(单笔固定金额限制:多个用,限制)
	 */
	private String singleAmounts;

	/**
	 * @Fields singleDefaultAmount : TODO(单笔默认金额)
	 */
	private String singleDefaultAmount;

	/**
	 * @Fields interestRateLimitType : TODO(年化利率限制类型:0：区间 1：固定值)
	 */
	private String interestRateLimitType;

	/**
	 * @Fields minInterestRate : TODO(借贷利率区间（下限）)
	 */
	private String minInterestRate;

	/**
	 * @Fields maxInterestRate : TODO(借贷利率区间（上限）)
	 */
	private String maxInterestRate;

	/**
	 * @Fields interestRates : TODO(固定年化利率限制:多个用,限制)
	 */
	private String interestRates;

	/**
	 * @Fields defaultInterestRate : TODO(默认年化利率)
	 */
	private String defaultInterestRate;

	/**
	 * @Fields termUnit : TODO(借贷期限单位：对应字典表termUnit)
	 */
	private String termUnit;

	/**
	 * @Fields repayType : TODO(还款方式:对应字典表repayType)
	 */
	private String repayType;

	/**
	 * @Fields daysLimitType : TODO(单笔天数限制类型：0：区间 1：固定值)
	 */
	private String daysLimitType;

	/**
	 * @Fields singleMinDays : TODO(单笔最小天数)
	 */
	private String singleMinDays;

	/**
	 * @Fields singleMaxDays : TODO(单笔最大天数)
	 */
	private String singleMaxDays;

	/**
	 * @Fields singleDays : TODO(单笔固定天数限制:多个用,限制)
	 */
	private String singleDays;

	/**
	 * @Fields singleDefaultDays : TODO(单笔默认天数)
	 */
	private String singleDefaultDays;

	/**
	 * @Fields monthsLimitType : TODO(单笔月数限制类型:0：区间 1：固定值)
	 */
	private String monthsLimitType;

	/**
	 * @Fields singleMinMonths : TODO(单笔最小月数)
	 */
	private String singleMinMonths;

	/**
	 * @Fields singleMaxMonths : TODO(单笔最大月数)
	 */
	private String singleMaxMonths;

	/**
	 * @Fields singleMonths : TODO(单笔固定月数限制：多个用,限制)
	 */
	private String singleMonths;

	/**
	 * @Fields singleDefaultMonths : TODO(单笔默认月数)
	 */
	private String singleDefaultMonths;

	/**
	 * @Fields repayTermUnit : TODO(还款期限单位：对应字典表repayTermUnit)
	 */
	private String repayTermUnit;

	/**
	 * @Fields defaultRepayTermCount : TODO(默认还款间隔)
	 */
	private String defaultRepayTermCount;

	/**
	 * @Fields defaultRepayTermUnit : TODO(默认还款间隔单位:对应字典表repayTermUnit)
	 */
	private String defaultRepayTermUnit;

	/**
	 * @Fields repayDate : TODO(还款期:单位：日)
	 */
	private String repayDate;

	/**
	 * @Fields penaltyRateLimitType : TODO(罚息利率限制类型：0：区间 1：固定值)
	 */
	private String penaltyRateLimitType;

	/**
	 * @Fields minPenaltyRate : TODO(罚息利率区间（下限）)
	 */
	private String minPenaltyRate;

	/**
	 * @Fields maxPenaltyRate : TODO(罚息利率区间（上限）)
	 */
	private String maxPenaltyRate;

	/**
	 * @Fields penaltyRates : TODO(固定罚息利率限制)
	 */
	private String penaltyRates;

	/**
	 * @Fields defaultPenaltyRate : TODO(默认罚息利率)
	 */
	private String defaultPenaltyRate;

	/**
	 * @Fields penaltyCalculationBasic : TODO(罚息计算基础:字典表calculationBasic)
	 */
	private String penaltyCalculationBasic;

	/**
	 * @Fields guaranteeType : TODO(担保类型：对应字典表guaranteeType)
	 */
	private String guaranteeType;

	/**
	 * @Fields isDailyInterestRate : TODO(是否按日计息:0：否 1：是)
	 */
	private String isDailyInterestRate;

	/**
	 * @Fields productInfoUpdateTime : TODO(产品信息更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String productInfoUpdateTime;

	/**
	 * @Fields busModelId : TODO(产品对应业务模型id)
	 */
	private String busModelId;

	/**
	 * @Fields imgModelId : TODO(产品对应影像模型id)
	 */
	private String imgModelId;

	/**
	 * @Fields downPaymentsLimitType : TODO(首付限制类型 0：区间 1：固定值)
	 */
	private String downPaymentsLimitType;

	/**
	 * @Fields minDownPayments : TODO(首付区间（下限）)
	 */
	private String minDownPayments;

	/**
	 * @Fields maxDownPayments : TODO(首付区间（上限）)
	 */
	private String maxDownPayments;

	/**
	 * @Fields downPayments : TODO(固定首付限制（多个用,限制）)
	 */
	private String downPayments;

	/**
	 * @Fields defaultDownPayments : TODO(默认首付金额)
	 */
	private String defaultDownPayments;

	/**
	 * @Fields downPaymentsEnable : TODO(首付是否启用（0：未启用；1：启用）s)
	 */
	private String downPaymentsEnable;

	/**
	 * @Fields status : TODO(产品状态 0：待生效 1：生效 -1：删除)
	 */
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAmountLimitType() {
		return amountLimitType;
	}

	public void setAmountLimitType(String amountLimitType) {
		this.amountLimitType = amountLimitType;
	}

	public String getSingleMinAmount() {
		return singleMinAmount;
	}

	public void setSingleMinAmount(String singleMinAmount) {
		this.singleMinAmount = singleMinAmount;
	}

	public String getSingleMaxAmount() {
		return singleMaxAmount;
	}

	public void setSingleMaxAmount(String singleMaxAmount) {
		this.singleMaxAmount = singleMaxAmount;
	}

	public String getSingleAmounts() {
		return singleAmounts;
	}

	public void setSingleAmounts(String singleAmounts) {
		this.singleAmounts = singleAmounts;
	}

	public String getSingleDefaultAmount() {
		return singleDefaultAmount;
	}

	public void setSingleDefaultAmount(String singleDefaultAmount) {
		this.singleDefaultAmount = singleDefaultAmount;
	}

	public String getInterestRateLimitType() {
		return interestRateLimitType;
	}

	public void setInterestRateLimitType(String interestRateLimitType) {
		this.interestRateLimitType = interestRateLimitType;
	}

	public String getMinInterestRate() {
		return minInterestRate;
	}

	public void setMinInterestRate(String minInterestRate) {
		this.minInterestRate = minInterestRate;
	}

	public String getMaxInterestRate() {
		return maxInterestRate;
	}

	public void setMaxInterestRate(String maxInterestRate) {
		this.maxInterestRate = maxInterestRate;
	}

	public String getInterestRates() {
		return interestRates;
	}

	public void setInterestRates(String interestRates) {
		this.interestRates = interestRates;
	}

	public String getDefaultInterestRate() {
		return defaultInterestRate;
	}

	public void setDefaultInterestRate(String defaultInterestRate) {
		this.defaultInterestRate = defaultInterestRate;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getDaysLimitType() {
		return daysLimitType;
	}

	public void setDaysLimitType(String daysLimitType) {
		this.daysLimitType = daysLimitType;
	}

	public String getSingleMinDays() {
		return singleMinDays;
	}

	public void setSingleMinDays(String singleMinDays) {
		this.singleMinDays = singleMinDays;
	}

	public String getSingleMaxDays() {
		return singleMaxDays;
	}

	public void setSingleMaxDays(String singleMaxDays) {
		this.singleMaxDays = singleMaxDays;
	}

	public String getSingleDays() {
		return singleDays;
	}

	public void setSingleDays(String singleDays) {
		this.singleDays = singleDays;
	}

	public String getSingleDefaultDays() {
		return singleDefaultDays;
	}

	public void setSingleDefaultDays(String singleDefaultDays) {
		this.singleDefaultDays = singleDefaultDays;
	}

	public String getMonthsLimitType() {
		return monthsLimitType;
	}

	public void setMonthsLimitType(String monthsLimitType) {
		this.monthsLimitType = monthsLimitType;
	}

	public String getSingleMinMonths() {
		return singleMinMonths;
	}

	public void setSingleMinMonths(String singleMinMonths) {
		this.singleMinMonths = singleMinMonths;
	}

	public String getSingleMaxMonths() {
		return singleMaxMonths;
	}

	public void setSingleMaxMonths(String singleMaxMonths) {
		this.singleMaxMonths = singleMaxMonths;
	}

	public String getSingleMonths() {
		return singleMonths;
	}

	public void setSingleMonths(String singleMonths) {
		this.singleMonths = singleMonths;
	}

	public String getSingleDefaultMonths() {
		return singleDefaultMonths;
	}

	public void setSingleDefaultMonths(String singleDefaultMonths) {
		this.singleDefaultMonths = singleDefaultMonths;
	}

	public String getRepayTermUnit() {
		return repayTermUnit;
	}

	public void setRepayTermUnit(String repayTermUnit) {
		this.repayTermUnit = repayTermUnit;
	}

	public String getDefaultRepayTermCount() {
		return defaultRepayTermCount;
	}

	public void setDefaultRepayTermCount(String defaultRepayTermCount) {
		this.defaultRepayTermCount = defaultRepayTermCount;
	}

	public String getDefaultRepayTermUnit() {
		return defaultRepayTermUnit;
	}

	public void setDefaultRepayTermUnit(String defaultRepayTermUnit) {
		this.defaultRepayTermUnit = defaultRepayTermUnit;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getPenaltyRateLimitType() {
		return penaltyRateLimitType;
	}

	public void setPenaltyRateLimitType(String penaltyRateLimitType) {
		this.penaltyRateLimitType = penaltyRateLimitType;
	}

	public String getMinPenaltyRate() {
		return minPenaltyRate;
	}

	public void setMinPenaltyRate(String minPenaltyRate) {
		this.minPenaltyRate = minPenaltyRate;
	}

	public String getMaxPenaltyRate() {
		return maxPenaltyRate;
	}

	public void setMaxPenaltyRate(String maxPenaltyRate) {
		this.maxPenaltyRate = maxPenaltyRate;
	}

	public String getPenaltyRates() {
		return penaltyRates;
	}

	public void setPenaltyRates(String penaltyRates) {
		this.penaltyRates = penaltyRates;
	}

	public String getDefaultPenaltyRate() {
		return defaultPenaltyRate;
	}

	public void setDefaultPenaltyRate(String defaultPenaltyRate) {
		this.defaultPenaltyRate = defaultPenaltyRate;
	}

	public String getPenaltyCalculationBasic() {
		return penaltyCalculationBasic;
	}

	public void setPenaltyCalculationBasic(String penaltyCalculationBasic) {
		this.penaltyCalculationBasic = penaltyCalculationBasic;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getIsDailyInterestRate() {
		return isDailyInterestRate;
	}

	public void setIsDailyInterestRate(String isDailyInterestRate) {
		this.isDailyInterestRate = isDailyInterestRate;
	}

	public String getProductInfoUpdateTime() {
		return productInfoUpdateTime;
	}

	public void setProductInfoUpdateTime(String productInfoUpdateTime) {
		this.productInfoUpdateTime = productInfoUpdateTime;
	}

	public String getBusModelId() {
		return busModelId;
	}

	public void setBusModelId(String busModelId) {
		this.busModelId = busModelId;
	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
	}

	public String getDownPaymentsLimitType() {
		return downPaymentsLimitType;
	}

	public void setDownPaymentsLimitType(String downPaymentsLimitType) {
		this.downPaymentsLimitType = downPaymentsLimitType;
	}

	public String getMinDownPayments() {
		return minDownPayments;
	}

	public void setMinDownPayments(String minDownPayments) {
		this.minDownPayments = minDownPayments;
	}

	public String getMaxDownPayments() {
		return maxDownPayments;
	}

	public void setMaxDownPayments(String maxDownPayments) {
		this.maxDownPayments = maxDownPayments;
	}

	public String getDownPayments() {
		return downPayments;
	}

	public void setDownPayments(String downPayments) {
		this.downPayments = downPayments;
	}

	public String getDefaultDownPayments() {
		return defaultDownPayments;
	}

	public void setDefaultDownPayments(String defaultDownPayments) {
		this.defaultDownPayments = defaultDownPayments;
	}

	public String getDownPaymentsEnable() {
		return downPaymentsEnable;
	}

	public void setDownPaymentsEnable(String downPaymentsEnable) {
		this.downPaymentsEnable = downPaymentsEnable;
	}

}
