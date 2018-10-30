package com.company.platform.restapi.model.loan;

import java.util.List;

import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.custom.ContactsInfo;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.EntCustomerInfo;

/**
 * @ClassName: OneSetpApplyInfoResp
 * @Description: TODO(第一步借贷申请输出信息)
 * @author yangxu
 * @date 2018年1月28日 下午1:02:42
 * 
 */
@SuppressWarnings("all")
public class OneSetpApplyInfoResp extends BaseHttpParamsResp {

	/**
	 * @Fields loanProductApplyId : TODO(借贷申请Id)
	 */
	private String loanProductApplyId;
	/**
	 * @Fields customerInfo : TODO(申请人信息(个人客户))
	 */
	private CustomerInfo customerInfo;
	/**
	 * @Fields entCustomerInfo : TODO(申请人信息(企业客户))
	 */
	private EntCustomerInfo entCustomerInfo;
	/**
	 * @Fields contactsInfo : TODO(客户联系人信息)
	 */
	private List<ContactsInfo> contactsInfo;

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public EntCustomerInfo getEntCustomerInfo() {
		return entCustomerInfo;
	}

	public void setEntCustomerInfo(EntCustomerInfo entCustomerInfo) {
		this.entCustomerInfo = entCustomerInfo;
	}

	public List<ContactsInfo> getContactsInfo() {
		return contactsInfo;
	}

	public void setContactsInfo(List<ContactsInfo> contactsInfo) {
		this.contactsInfo = contactsInfo;
	}
}
