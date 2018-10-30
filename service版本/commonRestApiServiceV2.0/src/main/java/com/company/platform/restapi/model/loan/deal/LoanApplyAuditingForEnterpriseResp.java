package com.company.platform.restapi.model.loan.deal;

import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;

/** 
* @ClassName: LoanApplyAuditingForEnterpriseResp 
* @Description: TODO(借贷申请) 
* @author wangxue 
* @date 2018年7月21日 下午3:40:23 
*  
*/
public class LoanApplyAuditingForEnterpriseResp extends EnterpriseInfoResp {

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

	/** 
	* @Fields htmlUrl : TODO(html5页面url地址) 
	*/
	private String htmlUrl;

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

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

}
