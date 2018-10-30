package com.company.platform.restapi.model.loan.deal;

import java.util.List;

import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationBaseAndLrInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerContactinfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerShareholderInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerJobDetailInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;

/** 
* @ClassName: LoanRepaymentTrialResp 
* @Description: TODO(还款试算返回参数) 
* @author wangxue 
* @date 2018年7月13日 下午4:55:12 
*  
*/
@SuppressWarnings("all")
public class LoanRepaymentTrialResp extends BaseHttpParamsResp {

	/** 
	* @Fields totalAmount : TODO(还款总额) 
	*/
	private String totalAmount;

	/** 
	* @Fields totalInterest : TODO(利息总额) 
	*/
	private String totalInterest;

	/** 
	* @Fields isFirstInterestCharge : TODO(是否首息收取 0：否 1：是) 
	*/
	private String isFirstInterestCharge;

	/** 
	* @Fields isFirstPrincipalInterestCharge : TODO(是否首期收取 0：否 1：是) 
	*/
	private String isFirstPrincipalInterestCharge;

	/** 
	* @Fields repaymentList : TODO(还款计划列表) 
	*/
	private List<LoanRepaymentInfo> repaymentList;

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(String totalInterest) {
		this.totalInterest = totalInterest;
	}

	public String getIsFirstInterestCharge() {
		return isFirstInterestCharge;
	}

	public void setIsFirstInterestCharge(String isFirstInterestCharge) {
		this.isFirstInterestCharge = isFirstInterestCharge;
	}

	public String getIsFirstPrincipalInterestCharge() {
		return isFirstPrincipalInterestCharge;
	}

	public void setIsFirstPrincipalInterestCharge(String isFirstPrincipalInterestCharge) {
		this.isFirstPrincipalInterestCharge = isFirstPrincipalInterestCharge;
	}

	public List<LoanRepaymentInfo> getRepaymentList() {
		return repaymentList;
	}

	public void setRepaymentList(List<LoanRepaymentInfo> repaymentList) {
		this.repaymentList = repaymentList;
	}
}
