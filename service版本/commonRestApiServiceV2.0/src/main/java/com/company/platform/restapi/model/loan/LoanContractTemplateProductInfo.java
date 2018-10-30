package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: LoanContractTemplateProductInfo
 * @Description: TODO(合同模板)
 * @author yangxu
 * @date 2018年2月6日 下午5:04:25
 * 
 */
@SuppressWarnings("all")
public class LoanContractTemplateProductInfo extends BaseModel {

	/**
	 * @Fields ctp_id : TODO(用一句话描述这个变量表示什么)
	 */
	private String ctp_id;
	/**
	 * @Fields ctp_create_time : TODO(创建时间)
	 */
	private String ctp_create_time;
	/**
	 * @Fields ctp_update_time : TODO(更新时间)
	 */
	private String ctp_update_time;
	/**
	 * @Fields ctp_contract_template_id : TODO(模板id)
	 */
	private String ctp_contract_template_id;
	/**
	 * @Fields ctp_contract_template_product_id : TODO(产品Id)
	 */
	private String ctp_contract_template_product_id;
	/**
	 * @Fields ctp_template_text : TODO(模板文本)
	 */
	private String ctp_template_text;
	/**
	 * @Fields ctp_model_name : TODO(模板名称)
	 */
	private String ctp_model_name;
	/**
	 * @Fields ctp_is_delete : TODO(是否被删除)
	 */
	private String ctp_is_delete;
	/**
	 * @Fields ctp_temp_variable : TODO(模板变量)
	 */
	private String ctp_temp_variable;
	/**
	 * @Fields ctp_contract_template_name : TODO(合同名称)
	 */
	private String ctp_contract_template_name;

	public String getCtp_id() {
		return ctp_id;
	}

	public void setCtp_id(String ctp_id) {
		this.ctp_id = ctp_id;
	}

	public String getCtp_create_time() {
		return ctp_create_time;
	}

	public void setCtp_create_time(String ctp_create_time) {
		this.ctp_create_time = ctp_create_time;
	}

	public String getCtp_update_time() {
		return ctp_update_time;
	}

	public void setCtp_update_time(String ctp_update_time) {
		this.ctp_update_time = ctp_update_time;
	}

	public String getCtp_contract_template_id() {
		return ctp_contract_template_id;
	}

	public void setCtp_contract_template_id(String ctp_contract_template_id) {
		this.ctp_contract_template_id = ctp_contract_template_id;
	}

	public String getCtp_contract_template_product_id() {
		return ctp_contract_template_product_id;
	}

	public void setCtp_contract_template_product_id(String ctp_contract_template_product_id) {
		this.ctp_contract_template_product_id = ctp_contract_template_product_id;
	}

	public String getCtp_template_text() {
		return ctp_template_text;
	}

	public void setCtp_template_text(String ctp_template_text) {
		this.ctp_template_text = ctp_template_text;
	}

	public String getCtp_model_name() {
		return ctp_model_name;
	}

	public void setCtp_model_name(String ctp_model_name) {
		this.ctp_model_name = ctp_model_name;
	}

	public String getCtp_is_delete() {
		return ctp_is_delete;
	}

	public void setCtp_is_delete(String ctp_is_delete) {
		this.ctp_is_delete = ctp_is_delete;
	}

	public String getCtp_temp_variable() {
		return ctp_temp_variable;
	}

	public void setCtp_temp_variable(String ctp_temp_variable) {
		this.ctp_temp_variable = ctp_temp_variable;
	}

	public String getCtp_contract_template_name() {
		return ctp_contract_template_name;
	}

	public void setCtp_contract_template_name(String ctp_contract_template_name) {
		this.ctp_contract_template_name = ctp_contract_template_name;
	}
	
}
