package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ConfigInfo
 * @Description: TODO(全局变量信息)
 * @author liang
 * @date 2018年1月25日 上午11:25:14
 * 
 */
@SuppressWarnings("all")
public class ConfigInfo extends BaseModel{

	/**
	 * @Fields name : TODO(代码)
	 */
	private String name;

	/**
	 * @Fields displayName : TODO(名称)
	 */
	private String displayName;

	/**
	 * @Fields value : TODO(对应值)
	 */
	private String value;

	/**
	 * @Fields configInfoUpdateTime : TODO(全局变量更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String configInfoUpdateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConfigInfoUpdateTime() {
		return configInfoUpdateTime;
	}

	public void setConfigInfoUpdateTime(String configInfoUpdateTime) {
		this.configInfoUpdateTime = configInfoUpdateTime;
	}

}
