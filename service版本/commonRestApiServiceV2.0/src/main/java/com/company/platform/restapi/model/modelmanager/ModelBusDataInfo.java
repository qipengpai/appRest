package com.company.platform.restapi.model.modelmanager;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: ModelBusDataInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lifeng 
* @date 2018年1月31日 上午11:00:16 
*  
*/
@SuppressWarnings("all")
public class ModelBusDataInfo extends BaseModel{

	/** 
	* @Fields titleId : TODO(数据id) 
	*/ 
	private String id;
	
	/** 
	* @Fields name : TODO(数据类型) 
	*/ 
	private String inputType;
	
	
	/** 
	* @Fields modelId : TODO(输入项代码) 
	*/ 
	private String busCode;
	
	/** 
	* @Fields busVal : TODO(输入值) 
	*/ 
	private String busVal;
	
	/** 
	* @Fields updateTime : TODO(更新时间) 
	*/ 
	private String updateTime;
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getBusVal() {
		return busVal;
	}

	public void setBusVal(String busVal) {
		this.busVal = busVal;
	}

}
