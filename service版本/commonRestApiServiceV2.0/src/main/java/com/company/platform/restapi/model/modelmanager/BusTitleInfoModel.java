package com.company.platform.restapi.model.modelmanager;

/** 
* @ClassName: BusTitleInfoModel 
* @Description: TODO(业务模型标题 App应用) 
* @author luyuchi
* @date 2018年1月24日 下午6:12:43 
*  
*/
public class BusTitleInfoModel {

	/** 
	* @Fields id : TODO(主键) 
	*/ 
	private String id;
	
	/** 
	* @Fields name : TODO(业务模型标题名称) 
	*/ 
	private String name;
	
	/** 
	* @Fields sort : TODO(业务模型标题顺序) 
	*/ 
	private String sort;
	
	/** 
	* @Fields modelId : TODO(业务模型ID) 
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
