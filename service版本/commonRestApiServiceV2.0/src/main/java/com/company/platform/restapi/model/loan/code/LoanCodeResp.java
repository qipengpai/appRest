package com.company.platform.restapi.model.loan.code;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: LoanCodeResp 
* @Description: TODO(返回借贷申请编码) 
* @author 王雪 
* @date 2018年1月30日 下午5:25:48 
*  
*/
@SuppressWarnings("all")
public class LoanCodeResp extends BaseModel {
	
	/** 
	* @Fields code : TODO(借贷申请编码) 
	*/ 
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
