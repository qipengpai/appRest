package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CollateralInfo 
* @Description: TODO(押品信息) 
* @author 王雪 
* @date 2018年5月22日 上午10:09:23 
*  
*/
@SuppressWarnings("all")
public class CollateralInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields collateralNo : TODO(押品编号) 
	*/
	private String collateralNo;

	/** 
	* @Fields collateralName : TODO(押品名称) 
	*/
	private String collateralName;

	/** 
	* @Fields collateralType : TODO(押品类型) 
	*/
	private String collateralType;
	/** 
	* @Fields warrantType : TODO(权属证件类型) 
	*/
	private String warrantType;

	/** 
	* @Fields customerName : TODO(权属人名称) 
	*/
	private String customerName;

	/** 
	* @Fields registerUserName : TODO(登记人) 
	*/
	private String registerUserName;

	/** 
	* @Fields registerOrgName : TODO(登记机构) 
	*/
	private String registerOrgName;

	/** 
	* @Fields registerTime : TODO(登记日期) 
	*/
	private String registerTime;

	/** 
	* @Fields esValue : TODO(评估价值) 
	*/
	private String esValue;

	/** 
	* @Fields collateralStatus : TODO(状态) 
	*/
	private String collateralStatus;

	/** 
	* @Fields esValueSys : TODO(系统评估价值) 
	*/
	private String esValueSys;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollateralNo() {
		return collateralNo;
	}

	public void setCollateralNo(String collateralNo) {
		this.collateralNo = collateralNo;
	}

	public String getCollateralName() {
		return collateralName;
	}

	public void setCollateralName(String collateralName) {
		this.collateralName = collateralName;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getWarrantType() {
		return warrantType;
	}

	public void setWarrantType(String warrantType) {
		this.warrantType = warrantType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRegisterUserName() {
		return registerUserName;
	}

	public void setRegisterUserName(String registerUserName) {
		this.registerUserName = registerUserName;
	}

	public String getRegisterOrgName() {
		return registerOrgName;
	}

	public void setRegisterOrgName(String registerOrgName) {
		this.registerOrgName = registerOrgName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getEsValue() {
		return esValue;
	}

	public void setEsValue(String esValue) {
		this.esValue = esValue;
	}

	public String getCollateralStatus() {
		return collateralStatus;
	}

	public void setCollateralStatus(String collateralStatus) {
		this.collateralStatus = collateralStatus;
	}

	public String getEsValueSys() {
		return esValueSys;
	}

	public void setEsValueSys(String esValueSys) {
		this.esValueSys = esValueSys;
	}

}
