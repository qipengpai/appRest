package com.company.platform.restapi.model.loan;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * 
 * @ClassName: ProductApplyInfoReq
 * @Description: TODO(借贷申请信息结果集)
 * @author 杨旭
 * @date 2018年1月25日 下午2:11:21
 *
 */
@SuppressWarnings("all")
public class ProductApplyInfoReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields loanProductApplyId : TODO(借贷申请id)
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
