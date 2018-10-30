package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: CollateralLoanApplyReq 
* @Description: TODO(借贷申请押品管理) 
* @author 王雪 
* @date 2018年5月24日 下午5:24:02 
*  
*/
@SuppressWarnings("serial")
public class CollateralLoanApplyReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(借贷申请id) 
	*/
	@NotEmpty(message = "借贷申请id不能为空")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
