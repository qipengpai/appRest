package com.company.platform.restapi.model.modelmanager;

/**
 * @ClassName: BusInfoModel
 * @Description: TODO(业务模型类 App应用)
 * @author yclu
 * @date 2018年1月24日 上午11:47:59
 */
public class BusInfoModel {

	/** 
	* @Fields id : TODO(业务模型ID) 
	*/ 
	private String id;
	
	/** 
	* @Fields productId : TODO(产品ID) 
	*/ 
	private String productId;
	
	/** 
	* @Fields modelName : TODO(业务模型名称) 
	*/ 
	private String modelName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
