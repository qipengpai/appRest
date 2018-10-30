package com.company.platform.restapi.model.loan;

/** 
* @ClassName: LoanProductApplyModelV 
* @Description: TODO(业务数据与模型数据关联模型) 
* @author luyuchi
* @date 2018年1月30日 上午9:01:22 
*  
*/
public class LoanProductApplyModelV {

	/** 
	* @Fields id : TODO(主键) 
	*/ 
	private String id;
	/** 
	* @Fields loanProductApplyId : TODO(借贷申请id) 
	*/ 
	private String loanProductApplyId;
	/** 
	* @Fields modelType : TODO(模型type) 
	*/ 
	private String modelType;
	/** 
	* @Fields modelCode : TODO(模型code) 
	*/ 
	private String modelCode;

	/** 
	* @Fields modelVersion : TODO(模型版本) 
	*/ 
	private String modelVersion;

	/** 
	* @Fields modelInstanceId : TODO(数据实例id) 
	*/ 
	private String modelInstanceId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}
	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelVersion() {
		return modelVersion;
	}
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}
	public String getModelInstanceId() {
		return modelInstanceId;
	}
	public void setModelInstanceId(String modelInstanceId) {
		this.modelInstanceId = modelInstanceId;
	}
}
