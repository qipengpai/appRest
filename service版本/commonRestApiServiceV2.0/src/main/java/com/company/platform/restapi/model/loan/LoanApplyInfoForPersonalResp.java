package com.company.platform.restapi.model.loan;

import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;


/** 
* @ClassName: LoanApplyInfoForPersonalResp 
* @Description: TODO(个人客户返回详情信息) 
* @author luyuchi
* @date 2018年3月23日 上午10:36:12 
*  
*/
public class LoanApplyInfoForPersonalResp extends CustomerInfoResp {

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
	* @Fields address : TODO(户籍地址) 
	*/ 
	private String address;
	
	/** 
	* @Fields orgName : TODO(进件渠道) 
	*/ 
	private String orgName;
	
	/** 
	* @Fields productModelData : TODO(模型校验规则) 
	*/ 
	private String productModelData;
	
	/**
	 * @Fields singleMinAmount : TODO(单笔最小金额)
	 */
	private String singleMinAmount;
	
	/**
	 * @Fields singleMaxAmount : TODO(单笔最大金额)
	 */
	private String singleMaxAmount;
	
	/**
	 * @Fields singleMonths : TODO(单笔最小月数)
	 */
	private String singleMonths;
	
	/**
	 * @Fields repayType : TODO(还款方式)
	 */
	private String repayType;
	

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProductModelData() {
		return productModelData;
	}

	public void setProductModelData(String productModelData) {
		this.productModelData = productModelData;
	}

	public String getSingleMinAmount() {
		return singleMinAmount;
	}

	public void setSingleMinAmount(String singleMinAmount) {
		this.singleMinAmount = singleMinAmount;
	}

	public String getSingleMaxAmount() {
		return singleMaxAmount;
	}

	public void setSingleMaxAmount(String singleMaxAmount) {
		this.singleMaxAmount = singleMaxAmount;
	}

	public String getSingleMonths() {
		return singleMonths;
	}

	public void setSingleMonths(String singleMonths) {
		this.singleMonths = singleMonths;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}



}
