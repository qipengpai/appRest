package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: CustomerVehicleInfo
 * @Description: TODO(个人客户车辆信息)
 * @author yangxu
 * @date 2018年1月29日 下午7:28:40
 * 
 */
@SuppressWarnings("all")
public class CustomerVehicleInfo extends BaseModel {
	/**
	 * @Fields Id : TODO(客户Id)
	 */
	private String Id;
	/**
	 * @Fields hasVehicle : TODO(有无车辆)
	 */
	private String hasVehicle;
	/**
	 * @Fields vehicleNo : TODO(车牌号码)
	 */
	private String vehicleNo;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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

}
