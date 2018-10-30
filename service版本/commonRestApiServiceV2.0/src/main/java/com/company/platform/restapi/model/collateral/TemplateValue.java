package com.company.platform.restapi.model.collateral;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: TemplateValue 
* @Description: TODO(押品模板实际值参数) 
* @author 王雪 
* @date 2018年5月23日 下午5:05:47 
*  
*/
@SuppressWarnings("all")
public class TemplateValue extends BaseModel {

	/** 
	* @Fields key : TODO(模板元素id) 
	*/
	@NotEmpty(message = "模板元素id不能为空")
	private String key;

	/** 
	* @Fields value : TODO(模板实际值) 
	*/
	@NotEmpty(message = "模板实际值不能为空")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
