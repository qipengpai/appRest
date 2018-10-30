package com.company.platform.restapi.model.custom.enterprise;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * 
* @ClassName: CustomerInfoReq 
* @Description: TODO(获取贷款申请人信息) 
* @author 王雪 
* @date 2018年1月23日 上午11:12:09 
*
 */
@SuppressWarnings("serial")
public class CorpCustomerInfoReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields credentialType : TODO(客户类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "corCredentialType")
	@NotEmpty(message = "客户类型不能为空")
	private String credentialType;

	/** 
	* @Fields credentialNo : TODO(证件号码) 
	*/
	@NotEmpty(message = "证件号码不能为空")
	private String credentialNo;

	/** 
	* @Fields customerOrgId : TODO(客户机构id) 
	*/
	@NotEmpty(message = "客户机构id不能为空")
	private String customerOrgId;

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

	public String getCustomerOrgId() {
		return customerOrgId;
	}

	public void setCustomerOrgId(String customerOrgId) {
		this.customerOrgId = customerOrgId;
	}

}
