package com.company.platform.restapi.model.collateral;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.decimalValidated.DecimalFormat;
import com.company.platform.base.validated.dictionaryValidated.DictionaryStandardOrNot;

/** 
* @ClassName: CollateralDetailReq 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王雪 
* @date 2018年5月24日 上午11:42:15 
*  
*/
@SuppressWarnings("serial")
public class CollateralDetailReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields id : TODO(押品id) 
	*/
	private String id;

	/** 
	* @Fields collateralNo : TODO(押品编号) 
	*/
	@NotEmpty(message = "押品编号不能为空")
	private String collateralNo;

	/** 
	* @Fields collateralName : TODO(押品名称) 
	*/
	@Length(max = 64, message = "押品名称最多64位字符")
	@NotEmpty(message = "押品名称不能为空")
	private String collateralName;

	/** 
	* @Fields collateralType : TODO(押品类型) 
	*/
	@NotEmpty(message = "押品类型不能为空")
	@DictionaryStandardOrNot(dictionaryType = "collateralType")
	private String collateralType;

	/** 
	* @Fields collateralDesc : TODO(规格型号) 
	*/
	@Length(max = 128, message = "规格型号最多128位字符")
	private String collateralDesc;

	/** 
	* @Fields customerId : TODO(权属人id) 
	*/
	@NotEmpty(message = "权属人不能为空")
	private String customerId;

	/** 
	* @Fields warrantType : TODO(权属证件类型) 
	*/
	@NotEmpty(message = "权属证件类型不能为空")
	@DictionaryStandardOrNot(dictionaryType = "warrantType")
	private String warrantType;

	/** 
	* @Fields warrantNo : TODO(权属证编号) 
	*/
	@Length(max = 32, message = "权属证编号最多32位字符")
	@NotEmpty(message = "权属证编号不能为空")
	private String warrantNo;

	/** 
	* @Fields warrantName : TODO(权属证名称) 
	*/
	@Length(max = 64, message = "权属证名称最多64位字符")
	@NotEmpty(message = "权属证名称不能为空")
	private String warrantName;

	/** 
	* @Fields esValue : TODO(人工评估价值) 
	*/
	@DecimalFormat(format = "14,2", var = "人工评估价值")
	@NotEmpty(message = "人工评估价值不能为空")
	private String esValue;

	/** 
	* @Fields collateralStatus : TODO(押品状态) 
	*/
	@DictionaryStandardOrNot(dictionaryType = "collateralStatus")
	@NotEmpty(message = "押品状态不能为空")
	private String collateralStatus;

	/** 
	* @Fields esValueSys : TODO(系统评估价值) 
	*/
	@DecimalFormat(format = "14,2", var = "系统评估价值")
	private String esValueSys;

	/** 
	* @Fields templateValue : TODO(押品模板实际值列表) 
	*/
	private List<TemplateValue> templateValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollateralNo() {
		return collateralNo;
	}

	public void setCollateralNo(String collateralNo) {
		this.collateralNo = collateralNo;
	}

	public String getCollateralName() {
		return collateralName;
	}

	public void setCollateralName(String collateralName) {
		this.collateralName = collateralName;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getCollateralDesc() {
		return collateralDesc;
	}

	public void setCollateralDesc(String collateralDesc) {
		this.collateralDesc = collateralDesc;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getWarrantType() {
		return warrantType;
	}

	public void setWarrantType(String warrantType) {
		this.warrantType = warrantType;
	}

	public String getWarrantNo() {
		return warrantNo;
	}

	public void setWarrantNo(String warrantNo) {
		this.warrantNo = warrantNo;
	}

	public String getWarrantName() {
		return warrantName;
	}

	public void setWarrantName(String warrantName) {
		this.warrantName = warrantName;
	}

	public String getEsValue() {
		return esValue;
	}

	public void setEsValue(String esValue) {
		this.esValue = esValue;
	}

	public String getCollateralStatus() {
		return collateralStatus;
	}

	public void setCollateralStatus(String collateralStatus) {
		this.collateralStatus = collateralStatus;
	}

	public String getEsValueSys() {
		return esValueSys;
	}

	public void setEsValueSys(String esValueSys) {
		this.esValueSys = esValueSys;
	}

	public List<TemplateValue> getTemplateValue() {
		return templateValue;
	}

	public void setTemplateValue(List<TemplateValue> templateValue) {
		this.templateValue = templateValue;
	}

}
