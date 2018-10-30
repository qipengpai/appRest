package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: OrgDeptInfo
 * @Description: TODO(产品对应当前用户组织机构关联信息)
 * @author liang
 * @date 2018年1月25日 下午1:52:41
 * 
 */
@SuppressWarnings("all")
public class OrgDeptInfo extends BaseModel{

	/**
	 * @Fields productId : TODO(产品id)
	 */
	private String productId;
	
	/**
	 * @Fields orgId : TODO(组织机构id)
	 */
	private String orgId;

	/**
	 * @Fields orgMinInterestRate : TODO(机构借贷利率区间（下限）)
	 */
	private String orgMinInterestRate;

	/**
	 * @Fields orgMaxInterestRate : TODO(机构借贷利率区间（上限）)
	 */
	private String orgMaxInterestRate;

	/**
	 * @Fields interestRates : TODO(固定借贷利率)
	 */
	private String interestRates;

	/**
	 * @Fields defaultInterestRate : TODO(默认借贷利率)
	 */
	private String defaultInterestRate;

	/**
	 * @Fields orgMinPenaltyRate : TODO(机构罚息利率区间（下限）)
	 */
	private String orgMinPenaltyRate;

	/**
	 * @Fields orgMaxPenaltyRate : TODO(机构罚息利率区间（上限）)
	 */
	private String orgMaxPenaltyRate;

	/**
	 * @Fields penaltyRates : TODO(固定罚息利率)
	 */
	private String penaltyRates;

	/**
	 * @Fields defaultPenaltyRate : TODO(默认罚息利率)
	 */
	private String defaultPenaltyRate;

	/**
	 * @Fields singleMinAmount : TODO(单笔最小金额)
	 */
	private String singleMinAmount;

	/**
	 * @Fields singleMaxAmount : TODO(单笔最大金额)
	 */
	private String singleMaxAmount;

	/**
	 * @Fields singleAmounts : TODO(固定单笔金额)
	 */
	private String singleAmounts;

	/**
	 * @Fields defaultSingleAmount : TODO(默认借贷金额)
	 */
	private String defaultSingleAmount;

	/**
	 * @Fields orgDeptInfoUpdateTime : TODO(产品组织机构关联更新时间，时间格式：yyyy-MM-dd
	 *         hh:mm:ss)
	 */
	private String orgDeptInfoUpdateTime;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrgMinInterestRate() {
		return orgMinInterestRate;
	}

	public void setOrgMinInterestRate(String orgMinInterestRate) {
		this.orgMinInterestRate = orgMinInterestRate;
	}

	public String getOrgMaxInterestRate() {
		return orgMaxInterestRate;
	}

	public void setOrgMaxInterestRate(String orgMaxInterestRate) {
		this.orgMaxInterestRate = orgMaxInterestRate;
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

	public String getOrgMinPenaltyRate() {
		return orgMinPenaltyRate;
	}

	public void setOrgMinPenaltyRate(String orgMinPenaltyRate) {
		this.orgMinPenaltyRate = orgMinPenaltyRate;
	}

	public String getOrgMaxPenaltyRate() {
		return orgMaxPenaltyRate;
	}

	public void setOrgMaxPenaltyRate(String orgMaxPenaltyRate) {
		this.orgMaxPenaltyRate = orgMaxPenaltyRate;
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

	public String getDefaultSingleAmount() {
		return defaultSingleAmount;
	}

	public void setDefaultSingleAmount(String defaultSingleAmount) {
		this.defaultSingleAmount = defaultSingleAmount;
	}

	public String getOrgDeptInfoUpdateTime() {
		return orgDeptInfoUpdateTime;
	}

	public void setOrgDeptInfoUpdateTime(String orgDeptInfoUpdateTime) {
		this.orgDeptInfoUpdateTime = orgDeptInfoUpdateTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
