package com.company.platform.restapi.model.loan;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanReqFormModel
 * @Description: TODO(借贷费用From)
 * @author yangxu
 * @date 2018年2月6日 下午5:06:36
 * 
 */
@SuppressWarnings("all")
public class LoanReqFormModel extends BaseModel {

	/**
	 * @Fields loanProductApplyFees : TODO(借贷申请费用参数)
	 */
	private List<LoanProductApplyFeeParam> loanProductApplyFees;

	/**
	 * @Fields guarantorInfos : TODO(担保人借贷申请信息)
	 */
	private List<LoanProductApplyGuarantorInfo> guarantorInfos;

	/**
	 * @Fields loanCollaterals : TODO(抵押信息)
	 */
	private List<LoanCollateralRel> loanCollaterals;

	public List<LoanProductApplyFeeParam> getLoanProductApplyFees() {
		return loanProductApplyFees;
	}

	public void setLoanProductApplyFees(List<LoanProductApplyFeeParam> loanProductApplyFees) {
		this.loanProductApplyFees = loanProductApplyFees;
	}

	public List<LoanProductApplyGuarantorInfo> getGuarantorInfos() {
		return guarantorInfos;
	}

	public void setGuarantorInfos(List<LoanProductApplyGuarantorInfo> guarantorInfos) {
		this.guarantorInfos = guarantorInfos;
	}

	public List<LoanCollateralRel> getLoanCollaterals() {
		return loanCollaterals;
	}

	public void setLoanCollaterals(List<LoanCollateralRel> loanCollaterals) {
		this.loanCollaterals = loanCollaterals;
	}
}