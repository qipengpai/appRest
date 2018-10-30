package com.company.platform.restapi.model.loan;

import java.util.List;

import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;


/** 
* @ClassName: LoanAuditingResp 
* @Description: TODO(个人客户返回详情信息) 
* @author luyuchi
* @date 2018年3月23日 上午10:36:12 
*  
*/
public class LoanAuditingResp extends CustomerInfoResp {

	/** 
	* @Fields serialVersionUID : TODO(对象序列化版本号) 
	*/ 
	private static final long serialVersionUID = 1L;

	/** 
	* @Fields loanApplyInfoForPersonalResp : TODO(个人申请信息) 
	*/ 
	private LoanApplyInfoForPersonalResp loanApplyInfoForPersonalResp;
	
	/** 
	* @Fields buttonInfo : TODO(按钮信息) 
	*/ 
	private List<FunctionInfo> buttonInfo;

	public LoanApplyInfoForPersonalResp getLoanApplyInfoForPersonalResp() {
		return loanApplyInfoForPersonalResp;
	}

	public void setLoanApplyInfoForPersonalResp(LoanApplyInfoForPersonalResp loanApplyInfoForPersonalResp) {
		this.loanApplyInfoForPersonalResp = loanApplyInfoForPersonalResp;
	}

	public List<FunctionInfo> getButtonInfo() {
		return buttonInfo;
	}

	public void setButtonInfo(List<FunctionInfo> buttonInfo) {
		this.buttonInfo = buttonInfo;
	}

}
