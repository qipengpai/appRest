package com.company.platform.restapi.model.loan;

import java.io.Serializable;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanProductModel
 * @Description: TODO(产品与规则模型关联model)
 * @author yangxu
 * @date 2018年2月6日 下午5:02:39
 * 
 */
@SuppressWarnings("all")
public class LoanProductModel extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields loanProductId : TODO(产品id)
	 */
	private String loanProductId;

	/**
	 * @Fields modelId : TODO(规则模型id)
	 */
	private String modelId;

	/**
	 * @Fields modelType : TODO(规则模型类型)
	 */
	private String modelType;

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

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
}
