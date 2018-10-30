package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: OrgProductRelation
 * @Description: TODO(产品机构关联信息)
 * @author yangxu
 * @date 2018年1月29日 上午10:26:29
 * 
 */
@SuppressWarnings("all")
public class OrgProductRelation extends BaseModel {
	/**
	 * @Fields id : TODO(Id)
	 */
	String id;
	/**
	 * @Fields orgId : TODO(机构id)
	 */
	String orgId;
	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	String loanProductId;
	/**
	 * @Fields orgMinInterestRate : TODO(机构借贷利率区间（下限）)
	 */
	String orgMinInterestRate;
	/**
	 * @Fields orgMaxInterestRate : TODO(机构借贷利率区间（上限）)
	 */
	String orgMaxInterestRate;
	/**
	 * @Fields interestRates : TODO(固定借贷利率（多个用,分隔）)
	 */
	String interestRates;
	/**
	 * @Fields defaultInterestRate : TODO(默认借贷利率)
	 */
	String defaultInterestRate;
	/**
	 * @Fields orgMinPenaltyRate : TODO(机构罚息利率区间（下限）)
	 */
	String orgMinPenaltyRate;
	/**
	 * @Fields orgMaxPenaltyRate : TODO(机构罚息利率区间（上限）)
	 */
	String orgMaxPenaltyRate;
	/**
	 * @Fields penaltyRates : TODO(固定罚息利率（多个用,分隔）)
	 */
	String penaltyRates;
	/**
	 * @Fields defaultPenaltyRate : TODO(默认罚息利率)
	 */
	String defaultPenaltyRate;
	/**
	 * @Fields singleMinAmount : TODO(单笔最小金额)
	 */
	String singleMinAmount;
	/**
	 * @Fields singleMaxAmount : TODO(单笔最大金额)
	 */
	String singleMaxAmount;
	/**
	 * @Fields singleAmounts : TODO(固定单笔金额（多个用,分隔）)
	 */
	String singleAmounts;
	/**
	 * @Fields defaultSingleAmount : TODO(默认借贷金额)
	 */
	String defaultSingleAmount;
	/**
	 * @Fields totalAmount : TODO(可借贷总金额)
	 */
	String totalAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
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

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
