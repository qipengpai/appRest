package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: ModelBusColumnInfoProp
 * @Description: TODO(模型字段信息)
 * @author liang
 * @date 2018年1月25日 下午5:34:21
 * 
 */
@SuppressWarnings("all")
public class ModelBusColumnInfoProp extends BaseModel{

	/**
	 * @Fields id : TODO(模型字段id)
	 */
	private String id;

	/**
	 * @Fields name : TODO(模型字段名称)
	 */
	private String name;

	/**
	 * @Fields code : TODO(模型字段编码)
	 */
	private String code;

	/**
	 * @Fields inputType : TODO(输入控件类型)
	 */
	private String inputType;

	/**
	 * @Fields necessity : TODO(是否必填)
	 */
	private String necessity;

	/**
	 * @Fields defValue : TODO(默认值)
	 */
	private String defValue;

	/**
	 * @Fields dataType : TODO(数据类型)
	 */
	private String dataType;

	/**
	 * @Fields length : TODO(输入长度限制)
	 */
	private String length;

	/**
	 * @Fields sort : TODO(模型字段排序)
	 */
	private String sort;

	/**
	 * @Fields titleId : TODO(模型分类id)
	 */
	private String titleId;

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getNecessity() {
		return necessity;
	}

	public void setNecessity(String necessity) {
		this.necessity = necessity;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
