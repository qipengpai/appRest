package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: ProductApplyInfoReqp
 * @Description: TODO(借贷申请信息输出参数)
 * @author yangxu
 * @date 2018年1月25日 下午3:53:04
 * 
 */
@SuppressWarnings("all")
public class ProductApplyInfoResp extends BaseHttpParamsAppReq {

	/**
	 * @Fields loanProductApplyId : TODO(借贷申请Id)
	 */
	private String loanProductApplyId;

	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	private String loanProductId;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}
}
