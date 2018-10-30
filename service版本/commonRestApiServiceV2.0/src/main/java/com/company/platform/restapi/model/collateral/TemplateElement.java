package com.company.platform.restapi.model.collateral;

import java.util.ArrayList;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: TemplateElement 
* @Description: TODO(押品表单模板关联元素) 
* @author 王雪 
* @date 2018年5月23日 下午2:24:29 
*  
*/
@SuppressWarnings("all")
public class TemplateElement extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields displayName : TODO(标签名) 
	*/
	private String displayName;

	/** 
	* @Fields elementType : TODO(元素类型) 
	*/
	private String elementType;

	/** 
	* @Fields validateDesc : TODO(校验规则) 
	*/
	private String validateDesc;
	/** 
	* @Fields eSort : TODO(排序) 
	*/
	private String eSort;

	/** 
	* @Fields selectOption : TODO(值域JSON) 
	*/
	private String selectOption;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getValidateDesc() {
		return validateDesc;
	}

	public void setValidateDesc(String validateDesc) {
		this.validateDesc = validateDesc;
	}

	public String geteSort() {
		return eSort;
	}

	public void seteSort(String eSort) {
		this.eSort = eSort;
	}

	public String getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}

}
