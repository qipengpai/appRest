package com.company.platform.restapi.model.custom.personal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: PersonalCustomerBaseInfo 
* @Description: TODO(个人客户信息) 
* @author 王雪 
* @date 2018年1月31日 下午1:58:32 
*  
*/
@SuppressWarnings("all")
public class PersonalCustomerBaseInfo extends BaseModel {
	/** 
	* @Fields id : TODO(id) 
	*/
	private String id;
	/** 
	* @Fields gender : TODO(性别) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "gender")
	private String gender;
	/** 
	* @Fields birthday : TODO(出生日期) 
	*/
	@DateTimeFormat(iso = ISO.DATE)
	private String birthday;
	/** 
	* @Fields hasChild : TODO(有无子女) 
	*/
	private String hasChild;
	/** 
	* @Fields education : TODO(最高学历) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "educationType")
	private String education;
	/** 
	* @Fields martialStatus : TODO(婚姻状况) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "martialStatus")
	private String martialStatus;
	/** 
	* @Fields occupationStatus : TODO(职业状态) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "occupationStatus")
	private String occupationStatus;

	/** 
	* @Fields hasDrivingLicense : TODO(有无驾照) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "hasDrivingLicense")
	private String hasDrivingLicense;

	/** 
	* @Fields remark : TODO(备注) 
	*/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getOccupationStatus() {
		return occupationStatus;
	}

	public void setOccupationStatus(String occupationStatus) {
		this.occupationStatus = occupationStatus;
	}

	public String getHasDrivingLicense() {
		return hasDrivingLicense;
	}

	public void setHasDrivingLicense(String hasDrivingLicense) {
		this.hasDrivingLicense = hasDrivingLicense;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
