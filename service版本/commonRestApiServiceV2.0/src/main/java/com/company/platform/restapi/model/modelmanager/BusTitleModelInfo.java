package com.company.platform.restapi.model.modelmanager;

/** 
* @ClassName: BusTitleInfoModel 
* @Description: TODO(业务模型标题 App应用) 
* @author yangxu
* @date 2018年9月13日 下午6:12:43 
*  
*/
public class BusTitleModelInfo {

	/** 
	* @Fields id : TODO(主键) 
	*/ 
	private String id;
	
	/** 
	* @Fields code : TODO(标题编码) 
	*/ 
	private String code;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
