package com.company.platform.restapi.model.repayment;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;


/** 
* @ClassName: GetRepayInfoListReq 
* @Description: TODO() 
* @author dongjian 
* @date 2018年5月23日 上午11:22:24 
*  
*/
@SuppressWarnings("serial")
public class GetRepayInfoListReq extends BaseHttpParamsPageAppReq {

    /**
     * @Fields condition : TODO(查询条件)
     */
    @NotEmpty(message = "查询条件不能为空")
    private String condition;
    
    @NotEmpty(message = "借贷申请id不能为空")
    private String loanApplyId;
    
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

}
