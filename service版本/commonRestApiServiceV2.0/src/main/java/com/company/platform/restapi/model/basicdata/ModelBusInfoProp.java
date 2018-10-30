package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ModelBusInfoProp
 * @Description: TODO(所有业务模型信息)
 * @author liang
 * @date 2018年1月25日 上午11:58:52
 * 
 */
@SuppressWarnings("all")
public class ModelBusInfoProp extends BaseModel{

	/**
	 * @Fields id : TODO(模型id)
	 */
	private String id;

	/**
	 * @Fields modelName : TODO(模型名称)
	 */
	private String modelName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
