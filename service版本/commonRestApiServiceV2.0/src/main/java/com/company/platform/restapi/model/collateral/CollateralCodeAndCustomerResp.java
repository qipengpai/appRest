package com.company.platform.restapi.model.collateral;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CollateralCodeAndCustomerResp 
* @Description: TODO(押品编号和权属人列表信息) 
* @author 王雪 
* @date 2018年5月23日 下午1:50:28 
*  
*/
@SuppressWarnings("all")
public class CollateralCodeAndCustomerResp extends BaseModel {

	/** 
	* @Fields collateralNo : TODO(押品编号) 
	*/
	private String collateralNo;

	/** 
	* @Fields customerWarrant : TODO(权属人列表) 
	*/
	private List<CustomerWarrant> customerWarrant;

	public String getCollateralNo() {
		return collateralNo;
	}

	public void setCollateralNo(String collateralNo) {
		this.collateralNo = collateralNo;
	}

	public List<CustomerWarrant> getCustomerWarrant() {
		return customerWarrant;
	}

	public void setCustomerWarrant(List<CustomerWarrant> customerWarrant) {
		this.customerWarrant = customerWarrant;
	}
}
