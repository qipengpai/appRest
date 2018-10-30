package com.company.platform.restapi.model.loan.handled;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;

/**
 * @ClassName: LoanSubmittedReq
 * @Description: TODO(客户贷款列表查询条件)
 * @author wangxeu
 * @date 2018年6月21日 下午2:37:15
 * 
 */
@SuppressWarnings("serial")
public class LoanSubmittedReq extends BaseHttpParamsPageAppReq {

	/**
	 * @Fields customerName : TODO(客户姓名)
	 */
	private String customerName;

	/**
	 * @Fields credentialNo : TODO(证件号码)
	 */
	private String credentialNo;

	/**
	 * @Fields applyTimeStart : TODO(借贷申请开始时间)
	 */
	@DateTimeFormat(iso = ISO.DATE)
	private String applyTimeStart;

	/**
	 * @Fields applyTimeEnd : TODO(借贷申请结束时间)
	 */
	@DateTimeFormat(iso = ISO.DATE)
	private String applyTimeEnd;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getApplyTimeStart() {
		return applyTimeStart;
	}

	public void setApplyTimeStart(String applyTimeStart) {
		this.applyTimeStart = applyTimeStart;
	}

	public String getApplyTimeEnd() {
		return applyTimeEnd;
	}

	public void setApplyTimeEnd(String applyTimeEnd) {
		this.applyTimeEnd = applyTimeEnd;
	}
}
