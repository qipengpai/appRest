package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductModelV
 * @Description: TODO(产品模型版本化)
 * @author yangxu
 * @date 2018年2月6日 下午5:02:14
 * 
 */
@SuppressWarnings("all")
public class LoanProductModelV extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	private String loanProductId;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
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
}
