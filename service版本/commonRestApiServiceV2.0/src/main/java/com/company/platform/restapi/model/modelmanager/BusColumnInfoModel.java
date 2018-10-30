package com.company.platform.restapi.model.modelmanager;

/**
 * @ClassName: BusColumnInfoModel
 * @Description: TODO(业务模型字段类 App应用)
 * @author yclu
 * @date 2018年1月24日 上午11:50:59
 */
public class BusColumnInfoModel {

	/** 
	* @Fields id : TODO(主键) 
	*/ 
	private String id;
	
	/** 
	* @Fields code : TODO(字段编码) 
	*/ 
	private String code;
	
	/** 
	* @Fields name : TODO(字段名称) 
	*/ 
	private String name;
	
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
	* @Fields dataType : TODO(字段数据类型) 
	*/ 
	private String dataType;
	
	/** 
	* @Fields length : TODO(输入长度限制) 
	*/ 
	private String length;
	
	/** 
	* @Fields validation : TODO(数据校验正则) 
	*/ 
	private String validation;
	
	/** 
	* @Fields validationMsg : TODO(校验提示) 
	*/ 
	private String validationMsg;
	
	/** 
	* @Fields sort : TODO(字段顺序) 
	*/ 
	private String sort;
	
	/** 
	* @Fields titleId : TODO(模型标题ID) 
	*/ 
	private String titleId;

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

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getValidationMsg() {
		return validationMsg;
	}

	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
	
}
