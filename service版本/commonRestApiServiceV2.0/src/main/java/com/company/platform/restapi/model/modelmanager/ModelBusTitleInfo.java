package com.company.platform.restapi.model.modelmanager;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: modelBusTitleInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lifeng 
* @date 2018年1月31日 上午11:00:16 
*  
*/
@SuppressWarnings("all")
public class ModelBusTitleInfo extends BaseModel{

	/** 
	* @Fields titleId : TODO(模型分类id) 
	*/ 
	private String titleId;
	
	/** 
	* @Fields name : TODO(业务模型标题名称) 
	*/ 
	private String name;
	
	
	/** 
	* @Fields modelId : TODO(业务模型ID) 
	*/ 
	private String modelId;

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
}
