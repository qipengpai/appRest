package com.company.platform.restapi.model.loan;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.decimalValidated.DecimalFormat;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * @ClassName: OneSetpApplyInfoReq
 * @Description: TODO(第一步借贷申请输入信息)
 * @author yangxu
 * @date 2018年1月28日 下午12:59:08
 * 
 */
@SuppressWarnings("all")
public class OneSetpApplyInfoReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	private String loanProductId;

	/**
	 * @Fields code : TODO(贷款申请码)
	 */
	private String code;

	/**
	 * @Fields customerType : TODO(客户类型)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "customerType")
	private String customerType;

	/**
	 * @Fields credentialType : TODO(证件类型)
	 */
	private String credentialType;

	/**
	 * @Fields credentialNo : TODO(证件号码)
	 */
	//@Length(max = 32, message = "证件号码最多32个字符")
	private String credentialNo;

	/**
	 * @Fields name : TODO(客户姓名)
	 */
	//@Length(max = 20, message = "客户名称最多20个字符")
	private String name;
	
	/**
	 * @Fields mobilePhone : TODO(客户手机号)
	 */
	//@Length(max = 11, message = "客户手机号最多11个字符")
	private String mobilePhone;

	/**
	 * @Fields applyAmount : TODO(申请金额)
	 */
	//@DecimalFormat(format = "14,2", var = "申请金额")
	private String applyAmount;

	/**
	 * @Fields termCount : TODO(借贷期限)
	 */
	//@NotEmpty(message = "借贷期限不能为空")
	private String termCount;

	/**
	 * @Fields termUnit : TODO(借贷期限单位)
	 */
	//@DictionaryStandardOrNot(dictionaryType = "termUnit")
	//@NotEmpty(message = "借贷期限单位不能为空")
	private String termUnit;

	/** 
	* @Fields repayTermCount : TODO(还款间隔) 
	*/
	//@Length(max = 5, message = "还款间隔期限最多五位整数")
	//private String repayTermCount;

	/** 
	* @Fields repayTermUnit : TODO(还款间隔单位) 
	*/
	//@DictionaryStandardOrNot(dictionaryType = "repayTermUnit")
	//private String repayTermUnit;

	/**
	 * @Fields orgId : TODO(客户机构id)
	 */
	//@NotEmpty(message = "组织机构id不能为空")
	private String orgId;

	/** 
	* @Fields applyTime : TODO(申请时间) 
	*/
	private String applyTime;

	/** 
	* @Fields imgModelId : TODO(影像模型id) 
	*/
	private String imgModelId;

	/** 
	* @Fields downPaymentsAmount : TODO(首付金额) 
	*/
	//@DecimalFormat(format = "14,2", var = "首付金额")
	//private String downPaymentsAmount;

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getTermCount() {
		return termCount;
	}

	public void setTermCount(String termCount) {
		this.termCount = termCount;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

//	public String getRepayTermCount() {
//		return repayTermCount;
//	}
//
//	public void setRepayTermCount(String repayTermCount) {
//		this.repayTermCount = repayTermCount;
//	}

//	public String getRepayTermUnit() {
//		return repayTermUnit;
//	}
//
//	public void setRepayTermUnit(String repayTermUnit) {
//		this.repayTermUnit = repayTermUnit;
//	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
	}

//	public String getDownPaymentsAmount() {
//		return downPaymentsAmount;
//	}
//
//	public void setDownPaymentsAmount(String downPaymentsAmount) {
//		this.downPaymentsAmount = downPaymentsAmount;
//	}

}
