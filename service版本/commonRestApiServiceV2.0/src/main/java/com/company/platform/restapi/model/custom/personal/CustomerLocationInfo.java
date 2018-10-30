package com.company.platform.restapi.model.custom.personal;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CustomerContactInfo 
* @Description: TODO(客户地址信息) 
* @author 王雪 
* @date 2018年1月25日 上午11:43:16 
*  
*/
@SuppressWarnings("all")
public class CustomerLocationInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields customerId : TODO(客户id) 
	*/
	private String customerId;

	/** 
	* @Fields etype : TODO(地址类型) 
	*/
	@NotEmpty(message = "地址类型不能为空")
	private String etype;

	/** 
	* @Fields provinceCode : TODO(省编码) 
	*/
	private String provinceCode;

	/** 
	* @Fields provinceName : TODO(省名称) 
	*/
	private String provinceName;

	/** 
	* @Fields cityCode : TODO(市编码) 
	*/
	private String cityCode;

	/** 
	* @Fields cityName : TODO(市名称) 
	*/
	private String cityName;

	/** 
	* @Fields countyCode : TODO(区编码) 
	*/
	private String countyCode;

	/** 
	* @Fields countyName : TODO(区名称) 
	*/
	private String countyName;

	/** 
	* @Fields address : TODO(详细地址) 
	*/
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
