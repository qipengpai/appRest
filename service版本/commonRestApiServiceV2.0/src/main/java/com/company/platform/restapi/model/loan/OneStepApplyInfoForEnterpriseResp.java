package com.company.platform.restapi.model.loan;

import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;

/** 
* @ClassName: OneStepApplyInfoForEnterpriseResp 
* @Description: TODO(第一步申请，企业客户返回信息) 
* @author luyuchi
* @date 2018年3月16日 下午3:02:30 
*  
*/
public class OneStepApplyInfoForEnterpriseResp extends EnterpriseInfoResp {

	/** 
	* @Fields serialVersionUID : TODO(对象序列化版本) 
	*/ 
	private static final long serialVersionUID = -3874859241673942073L;
	
	/** 
	* @Fields loanProductApplyId : TODO(借贷申请id) 
	*/ 
	private String loanProductApplyId;
	
	/** 
	* @Fields updateTimeProductApply : TODO(借贷申请更新时间) 
	*/
	private String updateTimeProductApply;
	
	/** 
	* @Fields updateTimeCustomer : TODO(客户信息更新时间) 
	*/ 
	private String updateTimeCustomer;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getUpdateTimeProductApply() {
		return updateTimeProductApply;
	}

	public void setUpdateTimeProductApply(String updateTimeProductApply) {
		this.updateTimeProductApply = updateTimeProductApply;
	}

	public String getUpdateTimeCustomer() {
		return updateTimeCustomer;
	}

	public void setUpdateTimeCustomer(String updateTimeCustomer) {
		this.updateTimeCustomer = updateTimeCustomer;
	}

}
