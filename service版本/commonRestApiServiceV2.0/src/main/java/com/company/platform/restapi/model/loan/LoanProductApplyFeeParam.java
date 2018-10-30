package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: LoanProductApplyFeeParam
 * @Description: TODO(借贷费用参数)
 * @author yangxu
 * @date 2018年2月6日 下午5:04:57
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyFeeParam extends BaseHttpParamsAppReq {

	/**
	 * @Fields jsonData : TODO(模型数据)
	 */
	private String jsonData;

	/**
	 * @Fields feeReduction : TODO(减免费用)
	 */
	private String feeReduction;

	/**
	 * @Fields feeReductionState : TODO(减免费用状态)
	 */
	private String feeReductionState;

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getFeeReduction() {
		return feeReduction;
	}

	public void setFeeReduction(String feeReduction) {
		this.feeReduction = feeReduction;
	}

	public String getFeeReductionState() {
		return feeReductionState;
	}

	public void setFeeReductionState(String feeReductionState) {
		this.feeReductionState = feeReductionState;
	}
}
