package com.company.platform.restapi.model.modelmanager;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: ImgDocModelInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lifeng 
* @date 2018年1月31日 上午11:00:16 
*  
*/
@SuppressWarnings("all")
public class ImgDocModelInfo extends BaseModel{

	/** 
	* @Fields imgDocId : TODO(数据id) 
	*/ 
	private String imgDocId;
	
	/** 
	* @Fields id : TODO(数据类型) 
	*/ 
	private String id;
	
	/** 
	* @Fields name : TODO(输入项代码) 
	*/ 
	private String name;
	
	/** 
	* @Fields expl : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private String expl;
	/** 
	* @Fields sort : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private String sort;
	
	public String getImgDocId() {
		return imgDocId;
	}
	public void setImgDocId(String imgDocId) {
		this.imgDocId = imgDocId;
	}
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
	public String getExpl() {
		return expl;
	}
	public void setExpl(String expl) {
		this.expl = expl;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}


}
