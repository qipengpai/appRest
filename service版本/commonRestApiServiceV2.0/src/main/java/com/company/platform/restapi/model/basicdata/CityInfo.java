package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * 
 * @ClassName: CityInfo
 * @Description: TODO(行政区划信息)
 * @author liang
 * @date 2018年1月25日 上午11:17:54
 *
 */
@SuppressWarnings("all")
public class CityInfo extends BaseModel{

	/**
	 * @Fields provinceCode : TODO(省代码)
	 */
	private String provinceCode;

	/**
	 * @Fields provinceName : TODO(省名称)
	 */
	private String provinceName;

	/**
	 * @Fields cityCode : TODO(市代码)
	 */
	private String cityCode;

	/**
	 * @Fields cityName : TODO(市名称)
	 */
	private String cityName;

	/**
	 * @Fields countyCode : TODO(区/县代码)
	 */
	private String countyCode;

	/**
	 * @Fields countyName : TODO(区/县名称)
	 */
	private String countyName;

	/**
	 * @Fields cityInfoUpdateTime : TODO(行政区划更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String cityInfoUpdateTime;

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

	public String getCityInfoUpdateTime() {
		return cityInfoUpdateTime;
	}

	public void setCityInfoUpdateTime(String cityInfoUpdateTime) {
		this.cityInfoUpdateTime = cityInfoUpdateTime;
	}

}
