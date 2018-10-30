package com.company.platform.restapi.model.loan.onlineTemp;

/**
 * 
* @ClassName: LoanProductApplyInfoReq 
* @Description: TODO(在线暂存数据) 
* @author 王雪 
* @date 2018年3月9日 上午9:31:39 
*
 */

//@SuppressWarnings("serial")
public class LoanProductApplyInfoReq {

	/**
	 * @Fields loanProductApplyInfo : TODO(借贷申请信息)
	 */
	//@Valid
	private LoanProductApplyInfo loanProductApplyInfo;

	/**
	 * @Fields modelData : TODO(业务模型数据)
	 */
	private String modelData;

	/** 
	* @Fields imgModelId : TODO(影像模型id) 
	*/
	private String imgModelId;

	/** 
	* @Fields guarantorInfo : TODO(担保人信息) 
	*/
	//@Valid
	//private List<GuarantorInfo> guarantorInfo;
	
	/**
	 * @Fields customerId : TODO(申请人id)
	 */
	//@Valid
	private String customerId;
	
	/**
	 * @Fields mobilePhone : TODO(申请人手机号)
	 */
	//@Valid
	private String mobilePhone;	
	
	/**
	 * @Fields address : TODO(申请人户籍地址)
	 */
	//@Valid
	private String address;	
	

	public LoanProductApplyInfo getLoanProductApplyInfo() {
		return loanProductApplyInfo;
	}

	public void setLoanProductApplyInfo(LoanProductApplyInfo loanProductApplyInfo) {
		this.loanProductApplyInfo = loanProductApplyInfo;
	}

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
	}

	public String getImgModelId() {
		return imgModelId;
	}

	public void setImgModelId(String imgModelId) {
		this.imgModelId = imgModelId;
	}

//	public List<GuarantorInfo> getGuarantorInfo() {
//		return guarantorInfo;
//	}
//
//	public void setGuarantorInfo(List<GuarantorInfo> guarantorInfo) {
//		this.guarantorInfo = guarantorInfo;
//	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
