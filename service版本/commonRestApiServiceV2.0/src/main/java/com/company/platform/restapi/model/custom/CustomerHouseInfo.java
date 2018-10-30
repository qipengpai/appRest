package com.company.platform.restapi.model.custom;

import java.math.BigDecimal;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: CustomerHouseInfo
 * @Description: TODO(客户房产信息)
 * @author yangxu
 * @date 2018年1月29日 下午7:09:40
 * 
 */
@SuppressWarnings("all")
public class CustomerHouseInfo extends BaseModel {
	/**
	 * @Fields Id : TODO(客户id)
	 */
	private String Id;
	/**
	 * @Fields hasHouse : TODO(是否有住房)
	 */
	private String hasHouse;
	/**
	 * @Fields ownHouseFlag : TODO(是否拥有住房)
	 */
	private String ownHouseFlag;
	/**
	 * @Fields ownHouseArea : TODO(拥有住房地址)
	 */
	private String ownHouseArea;
	/**
	 * @Fields currentHouseType : TODO(住房类型)
	 */
	private String currentHouseType;
	/**
	 * @Fields currentHouseArea : TODO(当前住房地址)
	 */
	private String currentHouseArea;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getHasHouse() {
		return hasHouse;
	}

	public void setHasHouse(String hasHouse) {
		this.hasHouse = hasHouse;
	}

	public String getOwnHouseFlag() {
		return ownHouseFlag;
	}

	public void setOwnHouseFlag(String ownHouseFlag) {
		this.ownHouseFlag = ownHouseFlag;
	}

	public String getOwnHouseArea() {
		return ownHouseArea;
	}

	public void setOwnHouseArea(String ownHouseArea) {
		this.ownHouseArea = ownHouseArea;
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
}
