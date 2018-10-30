package com.company.platform.restapi.model.custom;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: CustomerAlterRecord
 * @Description: TODO(客户信息修改记录)
 * @author yangxu
 * @date 2018年1月29日 下午7:56:35
 * 
 */
@SuppressWarnings("all")
public class CustomerAlterRecord extends BaseModel {
	/**
	 * @Fields id : TODO(主键id)
	 */
	private String Id;
	/**
	 * @Fields eType : TODO(客户类型)
	 */
	private String eType;
	/**
	 * @Fields customerId : TODO(客户id)
	 */
	private String customerId;
	/**
	 * @Fields alterTime : TODO(修改时间)
	 */
	private String alterTime;
	/**
	 * @Fields eDesc : TODO(描述)
	 */
	private String eDesc;
	/**
	 * @Fields operaterId : TODO(操作员id)
	 */
	private String operaterId;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}

	public String geteDesc() {
		return eDesc;
	}

	public void seteDesc(String eDesc) {
		this.eDesc = eDesc;
	}

	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}
}
