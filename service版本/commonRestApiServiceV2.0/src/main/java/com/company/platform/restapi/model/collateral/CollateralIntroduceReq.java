package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/** 
* @ClassName: CollateralIntroduceReq 
* @Description: TODO(引入押品请求参数) 
* @author 王雪 
* @date 2018年5月25日 上午11:29:10 
*  
*/
@SuppressWarnings("serial")
public class CollateralIntroduceReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(押品id) 
	*/
	@NotEmpty(message = "押品id不能为空")
	private String id;

	/** 
	* @Fields applyId : TODO(借贷申请id) 
	*/
	@NotEmpty(message = "借贷申请id不能为空")
	private String applyId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

}
