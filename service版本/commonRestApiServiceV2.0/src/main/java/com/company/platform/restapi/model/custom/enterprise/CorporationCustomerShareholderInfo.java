package com.company.platform.restapi.model.custom.enterprise;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CorporationCustomerShareholderInfo 
* @Description: TODO(企业客户股东信息) 
* @author 王雪 
* @date 2018年1月25日 下午5:49:22 
*  
*/
@SuppressWarnings("all")
public class CorporationCustomerShareholderInfo extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields customerId : TODO(企业客户ID) 
	*/
	private String customerId;

	/** 
	* @Fields capital : TODO(注册资本(万元)) 
	*/
	@NotEmpty(message = "注册资本不能为空")
	private String capital;

	/** 
	* @Fields capitalType : TODO(注资方式) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "capitalType")
	@NotEmpty(message = "注资方式不能为空")
	private String capitalType;

	/** 
	* @Fields isRepresent : TODO(是否为法人代表) 
	*/
	@NotEmpty(message = "是否为法人代表不能为空")
	private String isRepresent;

	/** 
	* @Fields shareHolderName : TODO(股东名称) 
	*/
	@NotEmpty(message = "股东名称不能为空")
	private String shareHolderName;

	/** 
	* @Fields sharePercent : TODO(股权比例) 
	*/
	@NotEmpty(message = "股权比例不能为空")
	private String sharePercent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}

	public String getIsRepresent() {
		return isRepresent;
	}

	public void setIsRepresent(String isRepresent) {
		this.isRepresent = isRepresent;
	}

	public String getShareHolderName() {
		return shareHolderName;
	}

	public void setShareHolderName(String shareHolderName) {
		this.shareHolderName = shareHolderName;
	}

	public String getSharePercent() {
		return sharePercent;
	}

	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}

}
