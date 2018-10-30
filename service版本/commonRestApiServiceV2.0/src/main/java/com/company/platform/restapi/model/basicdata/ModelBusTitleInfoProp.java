package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ModelBusTitleInfoProp
 * @Description: TODO(获取所有业务模型分类信息)
 * @author liang
 * @date 2018年1月25日 下午1:51:45
 * 
 */
@SuppressWarnings("all")
public class ModelBusTitleInfoProp extends BaseModel{

	/**
	 * @Fields id : TODO(模型分类id)
	 */
	private String id;

	/**
	 * @Fields name : TODO(模型分类名称)
	 */
	private String name;

	/**
	 * @Fields sort : TODO(模型分类排序)
	 */
	private String sort;

	/**
	 * @Fields modelId : TODO(模型id)
	 */
	private String modelId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}
