package com.company.platform.restapi.model.loan;

import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;


/** 
* @ClassName: LoanApplyInfoForEnterpriseResp 
* @Description: TODO(企业客户返回暂存详情信息) 
* @author luyuchi
* @date 2018年3月23日 上午10:35:24 
*  
*/
public class LoanApplyInfoForEnterpriseResp extends EnterpriseInfoResp {

	/** 
	* @Fields serialVersionUID : TODO(对象序列化版本号) 
	*/ 
	private static final long serialVersionUID = 381836230504418724L;

	/** 
	* @Fields loanProductApplyInfo : TODO(进件申请信息) 
	*/ 
	private LoanProductApplyInfo loanProductApplyInfo;
	
	/** 
	* @Fields customerType : TODO(客户类型) 
	*/ 
	private String customerType;
	
	/**
	 * @Fields modelData : TODO(业务模型数据)
	 */
	private String modelData;

	/** 
	* @Fields updateTimeProductApply : TODO(借贷申请更新时间) 
	*/ 
	private String updateTimeProductApply;
	
	/** 
	* @Fields updateTimeCustomer : TODO(申请人更新时间) 
	*/ 
	private String updateTimeCustomer;

	public LoanProductApplyInfo getLoanProductApplyInfo() {
		return loanProductApplyInfo;
	}

	public void setLoanProductApplyInfo(LoanProductApplyInfo loanProductApplyInfo) {
		this.loanProductApplyInfo = loanProductApplyInfo;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
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
