package com.company.platform.restapi.model.loan.deal;

import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;

/** 
* @ClassName: LoanApplyInfoForPersonalResp 
* @Description: TODO(个人客户返回详情信息) 
* @author luyuchi
* @date 2018年3月23日 上午10:36:12 
*  
*/
public class LoanApplyAuditingForPersonalResp extends CustomerInfoResp {

	/** 
	* @Fields serialVersionUID : TODO(对象序列化版本号) 
	*/
	private static final long serialVersionUID = 716669134052852568L;

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
