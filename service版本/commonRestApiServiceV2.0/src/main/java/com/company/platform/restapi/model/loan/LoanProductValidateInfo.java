package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: LoanProductValidateInfo 
* @Description: TODO(借贷产品验证需要参数，产品状态和借贷编码类型) 
* @author 王雪 
* @date 2018年1月30日 上午9:36:59 
*  
*/
@SuppressWarnings("all")
public class LoanProductValidateInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;
	/** 
	* @Fields loanCodeType : TODO(借贷编码类型) 
	*/
	private String loanCodeType;

	/** 
	* @Fields status : TODO(产品状态 0：待生效 1：生效 -1：删除) 
	*/
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanCodeType() {
		return loanCodeType;
	}

	public void setLoanCodeType(String loanCodeType) {
		this.loanCodeType = loanCodeType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
