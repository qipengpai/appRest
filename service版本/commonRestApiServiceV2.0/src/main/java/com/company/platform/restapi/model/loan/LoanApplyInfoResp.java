package com.company.platform.restapi.model.loan;

import java.util.List;

import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.custom.ContactsInfo;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.EntCustomerInfo;
import com.company.platform.restapi.model.custom.SelContactsInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;
import com.company.platform.restapi.model.modelmanager.BusColumnInfoModel;
import com.company.platform.restapi.model.modelmanager.BusInfoModel;
import com.company.platform.restapi.model.modelmanager.ImgDocModelDataInfo;
import com.company.platform.restapi.model.modelmanager.ImgDocModelInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusDataInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusTitleInfo;

/** 
* @ClassName: CustomerInfoResp 
* @Description: TODO(获取暂存信息详情返回对象) 
* @author lifeng 
* @date 2018年1月29日 下午6:31:43 
*  
*/
@SuppressWarnings("all")
public class LoanApplyInfoResp extends BaseHttpParamsResp {

	/** 
	* @Fields customerInfo : TODO(客户基本信息) 
	*/
	private CustomerPublicInfo customerPublicInfo;

	/** 
	* @Fields loanProductApplyInfo : TODO(进件申请信息) 
	*/
	private LoanProductApplyInfo loanProductApplyInfo;

	/** 
	* @Fields customerType : TODO(客户类型) 
	*/
	private String customerType;
	
	/** 
	* @Fields address : TODO(客户户籍地址) 
	*/
	private String address;
		
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
	* @Fields repayType : TODO(还款方式) 
	*/
	private String repayType;

	/** 
	* @Fields collateralInfo : TODO(押品信息) 
	*/
//	private List<CollateralInfo> collateralInfo;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
//	private List<GuarantorInfo> guarantorInfo;

//	public List<CollateralInfo> getCollateralInfo() {
//		return collateralInfo;
//	}
//
//	public void setCollateralInfo(List<CollateralInfo> collateralInfo) {
//		this.collateralInfo = collateralInfo;
//	}
	
	/**
	 * @Fields modelData : TODO(业务模型数据字典及校验规则)
	 */
	private String productModelData;

	/**
	 * @Fields modelData : TODO(进件渠道)
	 */
	private String orgName;

	/**
	 * @Fields singleMinAmount : TODO(单笔最小金额)
	 */
	private String singleMinAmount;
	
	/**
	 * @Fields singleMaxAmount : TODO(单笔最大金额)
	 */
	private String singleMaxAmount;
	
	/**
	 * @Fields singleMonths : TODO(单笔最小月数)
	 */
	private String singleMonths;
	
	
	public CustomerPublicInfo getCustomerPublicInfo() {
		return customerPublicInfo;
	}

	public void setCustomerPublicInfo(CustomerPublicInfo customerPublicInfo) {
		this.customerPublicInfo = customerPublicInfo;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getProductModelData() {
		return productModelData;
	}

	public void setProductModelData(String productModelData) {
		this.productModelData = productModelData;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSingleMinAmount() {
		return singleMinAmount;
	}

	public void setSingleMinAmount(String singleMinAmount) {
		this.singleMinAmount = singleMinAmount;
	}

	public String getSingleMaxAmount() {
		return singleMaxAmount;
	}

	public void setSingleMaxAmount(String singleMaxAmount) {
		this.singleMaxAmount = singleMaxAmount;
	}

	public String getSingleMonths() {
		return singleMonths;
	}

	public void setSingleMonths(String singleMonths) {
		this.singleMonths = singleMonths;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}


//	public List<GuarantorInfo> getGuarantorInfo() {
//		return guarantorInfo;
//	}
//
//	public void setGuarantorInfo(List<GuarantorInfo> guarantorInfo) {
//		this.guarantorInfo = guarantorInfo;
//	}

}
