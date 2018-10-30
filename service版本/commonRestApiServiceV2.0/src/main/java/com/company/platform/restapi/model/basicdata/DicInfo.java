package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: DicInfo
 * @Description: TODO(数据字典子节点信息)
 * @author liang
 * @date 2018年1月25日 上午11:29:29
 * 
 */
@SuppressWarnings("all")
public class DicInfo extends BaseModel{

	/**
	 * @Fields code : TODO(字典字段代码)
	 */
	private String code;

	/**
	 * @Fields name : TODO(字典字段名称)
	 */
	private String name;

	/**
	 * @Fields rangeCode : TODO(字典代码)
	 */
	private String rangeCode;

	/**
	 * @Fields dicInfoUpdateTime : TODO(字典字段更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String dicInfoUpdateTime;

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

	public String getRangeCode() {
		return rangeCode;
	}

	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	public String getDicInfoUpdateTime() {
		return dicInfoUpdateTime;
	}

	public void setDicInfoUpdateTime(String dicInfoUpdateTime) {
		this.dicInfoUpdateTime = dicInfoUpdateTime;
	}

}
