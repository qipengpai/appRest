package com.company.platform.restapi.model.loan;

import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;

/** 
* @ClassName: OneSetpApplyInfoForPersonalResp 
* @Description: TODO(第一步申请，个人客户返回信息) 
* @author luyuchi
* @date 2018年3月16日 下午3:03:40 
*  
*/
public class OneSetpApplyInfoForPersonalResp extends CustomerInfoResp {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -5126468993533737649L;
	
	/** 
	* @Fields loanProductApplyId : TODO(借贷申请id) 
	*/ 
	private String loanProductApplyId;
	
	/** 
	* @Fields updateTimeProductApply : TODO(借贷申请更新时间) 
	*/
//	private String updateTimeProductApply;
	
	/** 
	* @Fields updateTimeCustomer : TODO(客户信息更新时间) 
	*/ 
//	private String updateTimeCustomer;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

//	public String getUpdateTimeProductApply() {
//		return updateTimeProductApply;
//	}
//
//	public void setUpdateTimeProductApply(String updateTimeProductApply) {
//		this.updateTimeProductApply = updateTimeProductApply;
//	}
//
//	public String getUpdateTimeCustomer() {
//		return updateTimeCustomer;
//	}
//
//	public void setUpdateTimeCustomer(String updateTimeCustomer) {
//		this.updateTimeCustomer = updateTimeCustomer;
//	}

}
