package com.company.platform.restapi.model.collateral;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CollateralDetailResp 
* @Description: TODO(押品详细信息输出参数) 
* @author 王雪 
* @date 2018年5月23日 下午5:07:25 
*  
*/
@SuppressWarnings("all")
public class CollateralDetailResp extends BaseModel {

	/** 
	* @Fields id : TODO(主键id) 
	*/
	private String id;

	/** 
	* @Fields collateralNo : TODO(押品编号) 
	*/
	private String collateralNo;

	/** 
	* @Fields collateralName : TODO(押品名称) 
	*/
	private String collateralName;

	/** 
	* @Fields collateralType : TODO(押品类型) 
	*/
	private String collateralType;

	/** 
	* @Fields collateralDesc : TODO(规格型号) 
	*/
	private String collateralDesc;

	/** 
	* @Fields customerId : TODO(权属人Id) 
	*/
	private String customerId;

	/** 
	* @Fields customerDesc : TODO(权属人显示信息，格式：权属人姓名（证件编号）) 
	*/
	private String customerDesc;

	/** 
	* @Fields warrantType : TODO(权属证件类型) 
	*/
	private String warrantType;

	/** 
	* @Fields warrantNo : TODO(权属证编号) 
	*/
	private String warrantNo;

	/** 
	* @Fields warrantName : TODO(权属证名称) 
	*/
	private String warrantName;

	/** 
	* @Fields esValue : TODO(评估价值，单位：元) 
	*/
	private String esValue;

	/** 
	* @Fields collateralStatus : TODO(押品状态) 
	*/
	private String collateralStatus;

	/** 
	* @Fields esValueSys : TODO(系统评估价值，单位：元) 
	*/
	private String esValueSys;

	/** 
	* @Fields title : TODO(标题) 
	*/
	private String title;

	/** 
	* @Fields customerWarrant : TODO(押品模型元素列表) 
	*/
	private List<TemplateElement> templateElement;

	/** 
	* @Fields templateValue : TODO(押品模板实际值列表) 
	*/
	private List<TemplateValue> templateValue;

	/** 
	* @Fields customerWarrant : TODO(权属人列表) 
	*/
	private List<CustomerWarrant> customerWarrant;

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

	public String getCustomerDesc() {
		return customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TemplateElement> getTemplateElement() {
		return templateElement;
	}

	public void setTemplateElement(List<TemplateElement> templateElement) {
		this.templateElement = templateElement;
	}

	public List<TemplateValue> getTemplateValue() {
		return templateValue;
	}

	public void setTemplateValue(List<TemplateValue> templateValue) {
		this.templateValue = templateValue;
	}

	public List<CustomerWarrant> getCustomerWarrant() {
		return customerWarrant;
	}

	public void setCustomerWarrant(List<CustomerWarrant> customerWarrant) {
		this.customerWarrant = customerWarrant;
	}

}
