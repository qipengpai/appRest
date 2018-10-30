package com.company.platform.restapi.model.loan.code;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * 
 * @ClassName: ProductApplyInfoReq
 * @Description: TODO(借贷申请信息结果集)
 * @author 杨旭
 * @date 2018年1月25日 下午2:11:21
 *
 */
@SuppressWarnings("all")
public class LoanCodeReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields productId : TODO(产品id) 
	*/
	//@NotEmpty(message = "产品id不能为空")
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
