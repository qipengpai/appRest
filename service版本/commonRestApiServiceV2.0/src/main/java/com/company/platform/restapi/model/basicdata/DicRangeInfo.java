package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: DicRangeInfo
 * @Description: TODO(数据字典父节点信息)
 * @author liang
 * @date 2018年1月25日 上午11:35:10
 * 
 */
@SuppressWarnings("all")
public class DicRangeInfo extends BaseModel{

	/**
	 * @Fields rangeCode : TODO(字典代码)
	 */
	private String rangeCode;

	/**
	 * @Fields rangeName : TODO(字典名称)
	 */
	private String rangeName;

	/**
	 * @Fields dicRangeInfoUpdateTime : TODO(字典信息更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String dicRangeInfoUpdateTime;

	public String getRangeCode() {
		return rangeCode;
	}

	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public String getDicRangeInfoUpdateTime() {
		return dicRangeInfoUpdateTime;
	}

	public void setDicRangeInfoUpdateTime(String dicRangeInfoUpdateTime) {
		this.dicRangeInfoUpdateTime = dicRangeInfoUpdateTime;
	}

}
