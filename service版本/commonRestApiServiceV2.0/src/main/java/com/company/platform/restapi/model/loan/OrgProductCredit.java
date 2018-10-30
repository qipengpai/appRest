package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: OrgProductCredit
 * @Description: TODO(使用配额信息)
 * @author yangxu
 * @date 2018年1月29日 上午10:36:50
 * 
 */
@SuppressWarnings("all")
public class OrgProductCredit extends BaseModel {

	/**
	 * @Fields id : TODO(Id)
	 */
	String id;
	/**
	 * @Fields orgId : TODO(机构id)
	 */
	String orgId;
	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	String loanProductId;
	/**
	 * @Fields usedCredit : TODO(已使用配额)
	 */
	String usedCredit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getUsedCredit() {
		return usedCredit;
	}

	public void setUsedCredit(String usedCredit) {
		this.usedCredit = usedCredit;
	}
}
