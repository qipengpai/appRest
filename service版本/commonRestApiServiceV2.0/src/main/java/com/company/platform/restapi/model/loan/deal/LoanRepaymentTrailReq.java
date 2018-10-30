package com.company.platform.restapi.model.loan.deal;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: LoanRepaymentTrailReq 
* @Description: TODO(还款试算请求参数) 
* @author wangxue 
* @date 2018年7月24日 上午10:17:40 
*  
*/
@SuppressWarnings("all")
public class LoanRepaymentTrailReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields productApplyId : TODO(借贷申请id)
	 */
	@NotEmpty(message = "借贷申请id不能为空")
	private String productApplyId;
	/**
	 * @Fields amount : TODO(批复金额)
	 */
	@NotEmpty(message = "批复金额不能为空")
	private String amount;
	/**
	 * @Fields termCount : TODO(批准期限)
	 */
	@NotEmpty(message = "批准期限不能为空")
	private String termCount;
	/**
	 * @Fields termUnit : TODO(批准期限单位)
	 */
	@DictionaryStandardOrNot(dictionaryType = "termUnit")
	@NotEmpty(message = "批准期限单位不能为空")
	private String termUnit;
	/**
	 * @Fields repayTermCount : TODO(还款间隔期限)
	 */
	@Length(max = 5, message = "还款间隔期限最多五位整数")
	private String repayTermCount;
	/**
	 * @Fields repayTermUnit : TODO(还款间隔期限单位)
	 */
	@NotEmpty(message = "还款间隔期限单位不能为空")
	private String repayTermUnit;
	/**
	 * @Fields repayType : TODO(还款方式)
	 */
	@DictionaryStandardOrNot(dictionaryType = "repayType")
	@NotEmpty(message = "还款方式不能为空")
	private String repayType;
	/**
	 * @Fields interestRate : TODO(年化利率)
	 */
	@NotEmpty(message = "年化利率不能为空")
	private String interestRate;

	public String getProductApplyId() {
		return productApplyId;
	}

	public void setProductApplyId(String productApplyId) {
		this.productApplyId = productApplyId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTermCount() {
		return termCount;
	}

	public void setTermCount(String termCount) {
		this.termCount = termCount;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getRepayTermCount() {
		return repayTermCount;
	}

	public void setRepayTermCount(String repayTermCount) {
		this.repayTermCount = repayTermCount;
	}

	public String getRepayTermUnit() {
		return repayTermUnit;
	}

	public void setRepayTermUnit(String repayTermUnit) {
		this.repayTermUnit = repayTermUnit;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
}
