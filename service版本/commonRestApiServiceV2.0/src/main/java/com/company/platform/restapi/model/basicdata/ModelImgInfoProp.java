package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ModelImgInfoProp
 * @Description: TODO(获取所有影像模型信息)
 * @author liang
 * @date 2018年1月25日 下午1:52:28
 * 
 */
@SuppressWarnings("all")
public class ModelImgInfoProp extends BaseModel{

	/**
	 * @Fields titleId : TODO(模型分类id)
	 */
	private String titleId;

	/**
	 * @Fields titleName : TODO(模型分类名称)
	 */
	private String titleName;

	/**
	 * @Fields titleSort : TODO(模型分类排序)
	 */
	private String titleSort;

	/**
	 * @Fields titleExpl : TODO(模型分类描述)
	 */
	private String titleExpl;

	/**
	 * @Fields modelId : TODO(模型id)
	 */
	private String modelId;
	
	/**
	 * @Fields floor : TODO(文件上传数下限)
	 */
	private String floor;
	
	/** 
	* @Fields size : TODO(最大文件上传数) 
	*/
	private String size;

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

	public String getTitleSort() {
		return titleSort;
	}

	public void setTitleSort(String titleSort) {
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

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
