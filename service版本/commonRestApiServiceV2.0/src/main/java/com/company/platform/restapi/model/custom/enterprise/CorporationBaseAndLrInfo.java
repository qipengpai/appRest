package com.company.platform.restapi.model.custom.enterprise;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CorporationLrInfo 
* @Description: TODO(企业客户法人信息) 
* @author 王雪 
* @date 2018年1月29日 下午4:22:29 
*  
*/
@SuppressWarnings("all")
public class CorporationBaseAndLrInfo extends BaseModel {

	/** 
	* @Fields id : TODO(客户id) 
	*/
	private String id;

	/** 
	* @Fields lrName : TODO(法人代表姓名) 
	*/
	private String lrName;

	/** 
	* @Fields lrSex : TODO(法人代表性别) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "gender")
	private String lrSex;

	/** 
	* @Fields lrCertType : TODO(法人代表证件类型) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "credentialType")
	private String lrCertType;

	/** 
	* @Fields lrCertNo : TODO(法人代表证件号码) 
	*/
	private String lrCertNo;

	/** 
	* @Fields lrMobile : TODO(法人代表联系方式) 
	*/
	private String lrMobile;

	/** 
	* @Fields lrAge : TODO(法人代表年龄) 
	*/
	private String lrAge;

	/** 
	* @Fields corporationName : TODO(企业客户全称) 
	*/
	private String corporationName;

	/** 
	* @Fields taxId : TODO(税务登记号) 
	*/
	private String taxId;

	/** 
	* @Fields registerCapital : TODO(注册资本(万元)) 
	*/
	private String registerCapital;

	/** 
	* @Fields registerTime : TODO(注册时间) 
	*/
	@DateTimeFormat(iso = ISO.DATE)
	private String registerTime;

	/** 
	* @Fields staffCount : TODO(职员人数) 
	*/
	private String staffCount;

	/** 
	* @Fields industryType : TODO(所属行业) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "industryType")
	private String industryType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLrName() {
		return lrName;
	}

	public void setLrName(String lrName) {
		this.lrName = lrName;
	}

	public String getLrSex() {
		return lrSex;
	}

	public void setLrSex(String lrSex) {
		this.lrSex = lrSex;
	}

	public String getLrCertType() {
		return lrCertType;
	}

	public void setLrCertType(String lrCertType) {
		this.lrCertType = lrCertType;
	}

	public String getLrCertNo() {
		return lrCertNo;
	}

	public void setLrCertNo(String lrCertNo) {
		this.lrCertNo = lrCertNo;
	}

	public String getLrMobile() {
		return lrMobile;
	}

	public void setLrMobile(String lrMobile) {
		this.lrMobile = lrMobile;
	}

	public String getLrAge() {
		return lrAge;
	}

	public void setLrAge(String lrAge) {
		this.lrAge = lrAge;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(String staffCount) {
		this.staffCount = staffCount;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

}
