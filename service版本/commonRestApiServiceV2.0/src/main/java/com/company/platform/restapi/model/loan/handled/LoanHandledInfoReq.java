package com.company.platform.restapi.model.loan.handled;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: LoanHandledInfoReq
 * @Description: TODO(获取暂存信息详情请求参数)
 * @author lifeng
 * @date 2018年1月29日 下午5:42:31
 * 
 */
@SuppressWarnings("serial")
public class LoanHandledInfoReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields loanProductApplyId : TODO(借贷申请id不能为空)
	 */
	@NotEmpty(message = "借贷申请id不能为空")
	private String loanProductApplyId;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

}
