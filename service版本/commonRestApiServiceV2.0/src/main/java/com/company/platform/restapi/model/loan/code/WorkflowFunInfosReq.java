package com.company.platform.restapi.model.loan.code;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: WorkflowFunInfosReq 
* @Description: TODO(获得流程配置信息请求参数) 
* @author luyuchi
* @date 2018年4月27日 下午4:27:50 
*  
*/
public class WorkflowFunInfosReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields serialVersionUID : TODO(序列化) 
	*/
	private static final long serialVersionUID = -6271742061524461335L;

	/** 
	* @Fields loanProductId : TODO(产品id) 
	*/
	@NotEmpty(message = "产品id不能为空")
	private String loanProductId;

	/** 
	* @Fields loanApplyId : TODO(借贷申请id) 
	*/
	@NotEmpty(message = "借贷申请id不能为空")
	private String loanApplyId;

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
