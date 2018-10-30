package com.company.platform.restapi.model.modelmanager;

/** 
* @ClassName: ImgInfoModel 
* @Description: TODO(影像模型分类信息 App应用) 
* @author luyuchi
* @date 2018年1月24日 下午5:56:52 
*  
*/
public class ImgInfoModel {

	/** 
	* @Fields productId : TODO(产品ID) 
	*/ 
	private String productId;
	
	/** 
	* @Fields titleId : TODO(影像分类ID) 
	*/ 
	private String titleId;
	
	/** 
	* @Fields titleName : TODO(模型分类名称) 
	*/ 
	private String titleName;
	
	/** 
	* @Fields titleSort : TODO(模型分类顺序) 
	*/ 
	private Integer titleSort;
	
	/** 
	* @Fields titleExpl : TODO(模型分类描述) 
	*/ 
	private String titleExpl;
	
	/** 
	* @Fields modelId : TODO(影像模型ID) 
	*/ 
	private String modelId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public Integer getTitleSort() {
		return titleSort;
	}

	public void setTitleSort(Integer titleSort) {
		this.titleSort = titleSort;
	}

	public String getTitleExpl() {
		return titleExpl;
	}

	public void setTitleExpl(String titleExpl) {
		this.titleExpl = titleExpl;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
}
