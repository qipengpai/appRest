package com.company.platform.restapi.model.loan;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;

public class LoanApplyInfo extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(对象序列化) 
	*/
	private static final long serialVersionUID = -1834572425306367713L;

	/** 
	* @Fields loanProductApplyInfo : TODO(进件申请信息) 
	*/
	private LoanProductApplyInfo loanProductApplyInfo;

	/** 
	* @Fields customerType : TODO(客户类型) 
	*/
	private String customerType;

	/**
	 * @Fields modelData : TODO(业务模型数据)
	 */
	private String modelData;

	/** 
	* @Fields imgModelId : TODO(影像模型id) 
	*/
	private String imgModelId;

	/** 
	* @Fields updateTimeProductApply : TODO(借贷申请更新时间) 
	*/
	private String updateTimeProductApply;

	/** 
	* @Fields updateTimeCustomer : TODO(申请人更新时间) 
	*/
	private String updateTimeCustomer;

	/** 
	* @Fields collateralInfo : TODO(押品信息) 
	*/
	private List<CollateralInfo> collateralInfo;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
	private List<GuarantorInfo> guarantorInfo;

	public LoanProductApplyInfo getLoanProductApplyInfo() {
		return loanProductApplyInfo;
	}

	public void setLoanProductApplyInfo(LoanProductApplyInfo loanProductApplyInfo) {
		this.loanProductApplyInfo = loanProductApplyInfo;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
	}

	public String getUpdateTimeProductApply() {
		return updateTimeProductApply;
	}

	public void setUpdateTimeProductApply(String updateTimeProductApply) {
		this.updateTimeProductApply = updateTimeProductApply;
	}

	public String getUpdateTimeCustomer() {
		return updateTimeCustomer;
	}

	public void setUpdateTimeCustomer(String updateTimeCustomer) {
		this.updateTimeCustomer = updateTimeCustomer;
	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<CollateralInfo> getCollateralInfo() {
		return collateralInfo;
	}

	public void setCollateralInfo(List<CollateralInfo> collateralInfo) {
		this.collateralInfo = collateralInfo;
	}

	public List<GuarantorInfo> getGuarantorInfo() {
		return guarantorInfo;
	}

	public void setGuarantorInfo(List<GuarantorInfo> guarantorInfo) {
		this.guarantorInfo = guarantorInfo;
	}

}
