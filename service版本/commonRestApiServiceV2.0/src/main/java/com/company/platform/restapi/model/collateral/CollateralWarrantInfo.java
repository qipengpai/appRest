package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CollateralWarrantInfo 
* @Description: TODO(押品权证信息表) 
* @author 王雪 
* @date 2018年5月24日 下午2:09:47 
*  
*/
@SuppressWarnings("all")
public class CollateralWarrantInfo extends BaseModel {

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
	* @Fields collateralDesc : TODO(规格型号) 
	*/
	private String collateralDesc;

	/** 
	* @Fields customerName : TODO(权属人id) 
	*/
	private String customerId;

	/** 
	* @Fields warrantType : TODO(权属证件类型) 
	*/
	private String warrantType;

	/** 
	* @Fields warrantNo : TODO(权属证编号) 
	*/
	private String warrantNo;

	/** 
	* @Fields warrantName : TODO(权属证名称) 
	*/
	private String warrantName;

	/** 
	* @Fields esValue : TODO(评估价值) 
	*/
	private String esValue;

	/** 
	* @Fields collateralStatus : TODO(押品状态) 
	*/
	private String collateralStatus;

	/** 
	* @Fields registerStaffId : TODO(登记人id) 
	*/
	private String registerStaffId;

	/** 
	* @Fields registerOrgId : TODO(登记机构id) 
	*/
	private String registerOrgId;

	/** 
	* @Fields registerTime : TODO(登记日期) 
	*/
	private String registerTime;

	/** 
	* @Fields isUsed : TODO(是否正在被使用) 
	*/
	private String isUsed;

	/** 
	* @Fields deleteStatus : TODO(删除状态) 
	*/
	private String deleteStatus;

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

	public String getCollateralDesc() {
		return collateralDesc;
	}

	public void setCollateralDesc(String collateralDesc) {
		this.collateralDesc = collateralDesc;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getWarrantType() {
		return warrantType;
	}

	public void setWarrantType(String warrantType) {
		this.warrantType = warrantType;
	}

	public String getWarrantNo() {
		return warrantNo;
	}

	public void setWarrantNo(String warrantNo) {
		this.warrantNo = warrantNo;
	}

	public String getWarrantName() {
		return warrantName;
	}

	public void setWarrantName(String warrantName) {
		this.warrantName = warrantName;
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

	public String getRegisterStaffId() {
		return registerStaffId;
	}

	public void setRegisterStaffId(String registerStaffId) {
		this.registerStaffId = registerStaffId;
	}

	public String getRegisterOrgId() {
		return registerOrgId;
	}

	public void setRegisterOrgId(String registerOrgId) {
		this.registerOrgId = registerOrgId;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getEsValueSys() {
		return esValueSys;
	}

	public void setEsValueSys(String esValueSys) {
		this.esValueSys = esValueSys;
	}

}
