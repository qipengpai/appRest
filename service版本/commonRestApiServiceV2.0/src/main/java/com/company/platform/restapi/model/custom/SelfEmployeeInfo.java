package com.company.platform.restapi.model.custom;

import java.math.BigDecimal;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: SelfEmployeeInfo
 * @Description: TODO(个人客户自经营实体信息)
 * @author yangxu
 * @date 2018年1月29日 下午7:19:58
 * 
 */
@SuppressWarnings("all")
public class SelfEmployeeInfo extends BaseModel {
	/**
	 * @Fields Id : TODO(客户id)
	 */
	private String Id;
	/**
	 * @Fields eType : TODO(客户类型)
	 */
	private String eType;
	/**
	 * @Fields monthIncome : TODO(月收入)
	 */
	private String monthIncome;
	/**
	 * @Fields enterpriseName : TODO(企业名称)
	 */
	private String enterpriseName;
	/**
	 * @Fields turnover : TODO(营业额)
	 */
	private String turnover;
	/**
	 * @Fields mainBusiness : TODO(主营业务)
	 */
	private String mainBusiness;
	/**
	 * @Fields shareRadio : TODO(共享讯息)
	 */
	private String shareRadio;

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

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getShareRadio() {
		return shareRadio;
	}

	public void setShareRadio(String shareRadio) {
		this.shareRadio = shareRadio;
	}
}
