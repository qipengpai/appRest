package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ModelBusInfo
 * @Description: TODO(模型唯一标识)
 * @author liang
 * @date 2018年1月25日 上午11:58:52
 * 
 */
@SuppressWarnings("all")
public class ModelBusCodeAndVersion extends BaseModel{

	/**
	 * @Fields code : TODO(模型code)
	 */
	private String code;

	/**
	 * @Fields version : TODO(模型version)
	 */
	private String version;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
