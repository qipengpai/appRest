package com.company.platform.restapi.model.loan.deal;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

//import com.alibaba.fastjson.annotation.JSONField;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.restapi.model.custom.enterprise.CorporationBaseAndLrInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerContactinfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerShareholderInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerJobDetailInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;

/**
 * @ClassName: LoanProductApplyCompleteInfoReq
 * @Description: TODO(提交的借贷申请信息结果集)
 * @author yangxu
 * @date 2018年2月1日 下午2:38:00
 * 
 */
@SuppressWarnings("all")
public class LoanProductApplyDealReq extends BaseHttpParamsAppReq {

	/**
	 * @Fields LoanProductApplyInfo : TODO(借贷申请信息)
	 */
	@Valid
	private FullLoanProductApplyInfo loanProductApplyInfo;

	/**
	 * @Fields modelData : TODO(模型数据 json字符串)
	 */
	private String modelData;

	/** 
	* @Fields auths : TODO(权限，现用于区分暂存0/null、审核1) 
	*/
	private String auths;

	/** 
	* @Fields taskKey : TODO(节点key) 
	*/
	@NotEmpty(message = "节点key不能为空")
	private String taskKey;

	/** 
	* @Fields processInstanceId : TODO(流程实例id) 
	*/
	@NotEmpty(message = "流程实例id不能为空")
	private String processInstanceId;

	/** 
	* @Fields processDefinitionKey : TODO(流程类型key) 
	*/
	@NotEmpty(message = "流程类型key不能为空")
	private String processDefinitionKey;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
	@Valid
	private List<GuarantorInfo> guarantorInfo;

	public FullLoanProductApplyInfo getLoanProductApplyInfo() {
		return loanProductApplyInfo;
	}

	public void setLoanProductApplyInfo(FullLoanProductApplyInfo loanProductApplyInfo) {
		this.loanProductApplyInfo = loanProductApplyInfo;
	}

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
	}

	public String getAuths() {
		return auths;
	}

	public void setAuths(String auths) {
		this.auths = auths;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public List<GuarantorInfo> getGuarantorInfo() {
		return guarantorInfo;
	}

	public void setGuarantorInfo(List<GuarantorInfo> guarantorInfo) {
		this.guarantorInfo = guarantorInfo;
	}

}
