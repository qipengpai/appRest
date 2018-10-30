package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CollateralTypeReq 
* @Description: TODO(押品模板元素请求参数) 
* @author 王雪 
* @date 2018年5月23日 下午2:21:47 
*  
*/
@SuppressWarnings("serial")
public class CollateralTypeReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(押品类型) 
	*/
	@NotEmpty(message = "押品类型不能为空")
	@DictionaryStandardOrNot(dictionaryType="collateralType")
	private String collateralType;

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

}
