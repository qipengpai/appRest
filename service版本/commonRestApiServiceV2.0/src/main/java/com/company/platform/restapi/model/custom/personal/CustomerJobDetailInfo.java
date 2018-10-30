package com.company.platform.restapi.model.custom.personal;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/**
 * 
* @ClassName: CustomerJobDetailInfo 
* @Description: TODO(客户工作信息) 
* @author 王雪 
* @date 2018年1月25日 下午1:50:21 
*
 */
@SuppressWarnings("all")
public class CustomerJobDetailInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields currentWorkInfo : TODO(单位名称) 
	*/
	private String currentWorkInfo;

	/** 
	* @Fields currentWorkType : TODO(单位性质) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "companyType")
	private String currentWorkType;

	/** 
	* @Fields currentWorkAge : TODO(工龄) 
	*/
	private String currentWorkAge;

	/** 
	* @Fields totalWorkAge : TODO(总工龄) 
	*/
	private String totalWorkAge;

	/** 
	* @Fields currentPosition : TODO(职位名称) 
	*/
	private String currentPosition;

	/** 
	* @Fields monthIncome : TODO(月均收入) 
	*/
	private String monthIncome;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrentWorkInfo() {
		return currentWorkInfo;
	}

	public void setCurrentWorkInfo(String currentWorkInfo) {
		this.currentWorkInfo = currentWorkInfo;
	}

	public String getCurrentWorkType() {
		return currentWorkType;
	}

	public void setCurrentWorkType(String currentWorkType) {
		this.currentWorkType = currentWorkType;
	}

	public String getCurrentWorkAge() {
		return currentWorkAge;
	}

	public void setCurrentWorkAge(String currentWorkAge) {
		this.currentWorkAge = currentWorkAge;
	}

	public String getTotalWorkAge() {
		return totalWorkAge;
	}

	public void setTotalWorkAge(String totalWorkAge) {
		this.totalWorkAge = totalWorkAge;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

}
