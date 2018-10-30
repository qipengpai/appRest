package com.company.platform.restapi.model.custom.personal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CustomerAssetInfo 
* @Description: TODO(客户资产信息) 
* @author 王雪
* @date 2018年6月1日 上午9:23:25 
*  
*/
@SuppressWarnings("all")
public class CustomerAssetInfo extends BaseModel {

	/** 
	* @Fields id : TODO(客户id) 
	*/
	private String id;

	/** 
	* @Fields hasHouse : TODO(是否有房：0：无，1：有) 
	*/
	@NotEmpty(message = "是否有房不能为空")
	private String hasHouse;

	/** 
	* @Fields currentHouseType : TODO(现住房性质) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "houseStatus")
	private String currentHouseType;

	/** 
	* @Fields currentHouseArea : TODO(现住房面积(㎡)) 
	*/
	private String currentHouseArea;

	/** 
	* @Fields hasVehicle : TODO(是否有车：0：无，1：有) 
	*/
	@NotEmpty(message = "是否有车不能为空")
	private String hasVehicle;

	/** 
	* @Fields vehicleNo : TODO(车辆数目) 
	*/
	private String vehicleNo;

	/** 
	* @Fields remark : TODO(备注) 
	*/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHasHouse() {
		return hasHouse;
	}

	public void setHasHouse(String hasHouse) {
		this.hasHouse = hasHouse;
	}

	public String getCurrentHouseType() {
		return currentHouseType;
	}

	public void setCurrentHouseType(String currentHouseType) {
		this.currentHouseType = currentHouseType;
	}

	public String getCurrentHouseArea() {
		return currentHouseArea;
	}

	public void setCurrentHouseArea(String currentHouseArea) {
		this.currentHouseArea = currentHouseArea;
	}

	public String getHasVehicle() {
		return hasVehicle;
	}

	public void setHasVehicle(String hasVehicle) {
		this.hasVehicle = hasVehicle;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
