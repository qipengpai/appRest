package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: MerchantApplyInfo
 * @Description: TODO(商户申请信息)
 * @author yangxu
 * @date 2018年2月2日 下午3:10:16
 * 
 */
@SuppressWarnings("all")
public class MerchantApplyInfo extends BaseModel {
	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;
	/**
	 * @Fields bussinessType : TODO(商户类型:0：借贷 1：授信)
	 */
	private String bussinessType;
	/**
	 * @Fields bussinessId : TODO(业务id)
	 */
	private String bussinessId;
	/**
	 * @Fields merchantId : TODO(商户id)
	 */
	private String merchantId;
	/**
	 * @Fields orgId : TODO(组织机构id)
	 */
	private String orgId;
	/**
	 * @Fields customerId : TODO(客户id)
	 */
	private String customerId;
	/**
	 * @Fields applyAmount : TODO(借贷申请金额)
	 */
	private String applyAmount;
	/**
	 * @Fields replyAmount : TODO(借贷批复金额)
	 */
	private String replyAmount;
	/**
	 * @Fields rechargeType : TODO(收款方式:0：按比例收款 1：按固定金额收款)
	 */
	private String rechargeType;
	/**
	 * @Fields applySettlementAmount : TODO(申请结算金额)
	 */
	private String applySettlementAmount;
	/**
	 * @Fields applyTime : TODO(申请时间)
	 */
	private String applyTime;
	/**
	 * @Fields replySettlementAmount : TODO(批复结算金额)
	 */
	private String replySettlementAmount;
	/**
	 * @Fields replyTime : TODO(批复时间)
	 */
	private String replyTime;
	/**
	 * @Fields settlementState : TODO(结算状态 0:未出账 1:已出账)
	 */
	private String settlementState;
	/**
	 * @Fields applyState : TODO(申请状态 0:审批中 1:通过 2:拒绝)
	 */
	private String applyState;
	/**
	 * @Fields releaseTime : TODO(放款时间)
	 */
	private String releaseTime;
	/**
	 * @Fields settleId : TODO(结算单号id)
	 */
	private String settleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public String getBussinessId() {
		return bussinessId;
	}

	public void setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(String replyAmount) {
		this.replyAmount = replyAmount;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getApplySettlementAmount() {
		return applySettlementAmount;
	}

	public void setApplySettlementAmount(String applySettlementAmount) {
		this.applySettlementAmount = applySettlementAmount;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getReplySettlementAmount() {
		return replySettlementAmount;
	}

	public void setReplySettlementAmount(String replySettlementAmount) {
		this.replySettlementAmount = replySettlementAmount;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getSettlementState() {
		return settlementState;
	}

	public void setSettlementState(String settlementState) {
		this.settlementState = settlementState;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
}
