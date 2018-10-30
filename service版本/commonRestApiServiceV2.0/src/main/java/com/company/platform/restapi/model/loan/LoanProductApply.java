package com.company.platform.restapi.model.loan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
* @ClassName: LoanProductApply 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dongjian 
* @date 2018年5月24日 上午10:11:35 
*  
*/
public class LoanProductApply implements Serializable {

	private static final long serialVersionUID = 5019284567269772633L;
	
	private String id;
	/**
	 * 产品id
	 */
	private String loanProductId;
	/**
	 * 申请借款客户id
	 */
	private String customerId;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 借贷金额
	 */
	private BigDecimal applyAmount;
	/**
	 * 批复金额
	 */
	private BigDecimal replyAmount;
	/**
	 * 借贷期限
	 */
	private Integer termCount;
	/**
	 * 借贷期限单位
	 */
	private String termUnit;
	/**
	 * 批准期限
	 */
	private Integer authTermCount;
	/**
	 * 批准期限单位
	 */
	private String authTermUnit;
	/**
	 * 还款间隔期限
	 */
	private Integer repayTermCount;
	/**
	 * 还款间隔期限单位
	 */
	private String repayTermUnit;
	/**
	 * 还款方式
	 */
	private String repayType;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 年化利率
	 */
	private BigDecimal interestRate;
	/**
	 * 罚息利率
	 */
	private BigDecimal penaltyRate;
    /**
     * 罚息计算基础，字典表calculationBasic
     */
    private String penaltyCalculationBasic;
	/**
	 * 担保方式
	 */
	private String guaranteeType;
	/**
	 * 借款用途
	 */
	private String useType;
	/**
	 * 进件类型
	 */
	private String intoPieceType;
	/**
	 * 产品分类，产品安银贷专有属性 0：保单贷 1：房贷
	 */
	private Integer productType;
	/**
	 * 是否自带合同 0：否 1：是
	 */
	private Integer ownContract = 0;
	/**
	 * 状态 0：待发起 1：审核中 2：审核成功 3：审核拒绝 4:违规申请
	 */
	private int status;
	/**
	 * 还款状态  0：未签订合同 1：已签定合同 2：还款中 3：已还完
	 */
	private int actionStatus;
	
	/**
	 * 客户经理id
	 */
	private String accountManagerId;
	/**
	 * 登记人员
	 */
	private String registerId;
	/**
	 * 登记日期
	 */
	private Date registerTime;
	/**
	 * 进件编号
	 */
	private String intoPieceNo;
	/**
	 * 放款账户名
	 */
	private String loanAccount;
	/**
	 * 放款账号/卡号
	 */
	private String loanCardNo;
	/**
	 * 放款开户银行
	 */
	private String loanBank;
	/**
	 * 放款开户机构
	 */
	private String loanOrg;
	/**
	 * 还款账户名
	 */
	private String repaymentAccount;
	/**
	 * 还款账号/卡号
	 */
	private String repaymentCardNo;
	/**
	 * 还款开户银行
	 */
	private String repaymentBank;
	/**
	 * 还款开户机构
	 */
	private String repaymentOrg;
	/**
	 * 提交时间
	 */
	private Date commitTime;
	/**
	 * 补充信息
	 */
	private String supplementMsg;
	/**
	 * 流程id
	 */
	private String procdefId;
	/**
	 * 申请客户机构id
	 */
	private String orgId;
	/**
	 * 放款类型（对应字典repaymentType）
	 */
	private String releaseType;
	/**
	 * 放款时间
	 */
	private Date releaseDate;
	/**
	 * （受全局变量TASK_TYPE_EFFECTIVE控制是否生效）任务类型 0：默认 1：设置批复金额和批准期限 2：合同管理 3：合同管理和费率调整 4：已签订合同 5：放款设和通过
	 */
	private Integer taskType;
	/**
	 * 是否为商户进件 0：否 1：是
	 */
	private Integer merchantState;
	/**
	 * 商户id
	 */
	private String merchantId;
	/**
	 * 0：商户按比例收款 1：商户按固定金额收款
	 */
	private Integer merchantChargeType;
	/**
	 * 商户收取比例
	 */
	private BigDecimal merchantChargeRate;
	/**
	 * 商户收取固定金额
	 */
	private BigDecimal merchantChargeAmount;
	/**
	 * 批复商户收取比例
	 */
	private BigDecimal replyMerchantChargeRate;
	/**
	 * 批复商户收取固定金额
	 */
	private BigDecimal replyMerchantChargeAmount;
	
	/**
	 * 放款收取类型
	 */
	private Integer firstPeriodCharge;
	/**
     * 是否合同期外罚息 0：否 1：是
     */
    private Integer isOutOfTermPenalty;
    /**
     * 合同期外罚息计算基础，字典表calculationBasic
     */
    private String outOfTermPenaltyCalculationBasic;
	/**
	 * 当前期还款计划id
	 */
	private String currentPeriodRepayId;
	
	
	/**
	 * get[id]
	 */
	public String getId() {
		return id;
	}
	/**
	 * set[id]
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * get[产品id]
	 */
	public String getLoanProductId() {
		return loanProductId;
	}
	/**
	 * set[产品id]
	 */
	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}
	/**
	 * get[申请借款客户id]
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * set[申请借款客户id]
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * get[编码]
	 */
	public String getCode() {
		return code;
	}
	/**
	 * set[编码]
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * get[借贷金额]
	 */
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	/**
	 * set[借贷金额]
	 */
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	/**
	 * get[批复金额]
	 */
	public BigDecimal getReplyAmount() {
		return replyAmount;
	}
	/**
	 * set[批复金额]
	 */
	public void setReplyAmount(BigDecimal replyAmount) {
		this.replyAmount = replyAmount;
	}
	/**
	 * get[借贷期限]
	 */
	public Integer getTermCount() {
		return termCount;
	}
	/**
	 * set[借贷期限]
	 */
	public void setTermCount(Integer termCount) {
		this.termCount = termCount;
	}
	/**
	 * get[借贷期限单位]
	 */
	public String getTermUnit() {
		return termUnit;
	}
	/**
	 * set[借贷期限单位]
	 */
	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}
	/**
	 * get[批准期限]
	 */
	public Integer getAuthTermCount() {
		return authTermCount;
	}
	/**
	 * set[批准期限]
	 */
	public void setAuthTermCount(Integer authTermCount) {
		this.authTermCount = authTermCount;
	}
	/**
	 * get[批准期限单位]
	 */
	public String getAuthTermUnit() {
		return authTermUnit;
	}
	/**
	 * set[批准期限单位]
	 */
	public void setAuthTermUnit(String authTermUnit) {
		this.authTermUnit = authTermUnit;
	}
	/**
	 * get[还款间隔期限]
	 */
	public Integer getRepayTermCount() {
		return repayTermCount;
	}
	/**
	 * set[还款间隔期限]
	 */
	public void setRepayTermCount(Integer repayTermCount) {
		this.repayTermCount = repayTermCount;
	}
	/**
	 * get[还款方式]
	 */
	public String getRepayType() {
		return repayType;
	}
	/**
	 * set[还款方式]
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	/**
	 * get[申请时间]
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	/**
	 * set[申请时间]
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * get[还款间隔期限单位]
	 */
	public String getRepayTermUnit() {
		return repayTermUnit;
	}
	/**
	 * set[还款间隔期限单位]
	 */
	public void setRepayTermUnit(String repayTermUnit) {
		this.repayTermUnit = repayTermUnit;
	}
	/**
	 * get[年化利率]
	 */
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	/**
	 * set[年化利率]
	 */
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * get[罚息利率]
	 */
	public BigDecimal getPenaltyRate() {
		return penaltyRate;
	}
	/**
	 * set[罚息利率]
	 */
	public void setPenaltyRate(BigDecimal penaltyRate) {
		this.penaltyRate = penaltyRate;
	}
	/**
	 * get[担保方式]
	 */
	public String getGuaranteeType() {
		return guaranteeType;
	}
	/**
	 * set[担保方式]
	 */
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	/**
	 * get[借款用途]
	 */
	public String getUseType() {
		return useType;
	}
	/**
	 * set[借款用途]
	 */
	public void setUseType(String useType) {
		this.useType = useType;
	}
	/**
	 * get[进件类型]
	 */
	public String getIntoPieceType() {
		return intoPieceType;
	}
	/**
	 * set[进件类型]
	 */
	public void setIntoPieceType(String intoPieceType) {
		this.intoPieceType = intoPieceType;
	}
	/**
	 * get[产品分类，产品安银贷专有属性0：保单贷1：房贷]
	 */
	public Integer getProductType() {
		return productType;
	}
	/**
	 * set[产品分类，产品安银贷专有属性0：保单贷1：房贷]
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	/**
	 * get[是否自带合同0：否1：是]
	 */
	public Integer getOwnContract() {
		return ownContract;
	}
	/**
	 * set[是否自带合同0：否1：是]
	 */
	public void setOwnContract(Integer ownContract) {
		this.ownContract = ownContract;
	}
	/**
	 * get[状态0：待发起 1：审核中 2：审核成功 3：审核拒绝 4:违规申请]
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * set[状态0：待发起 1：审核中 2：审核成功 3：审核拒绝 4:违规申请]
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * get[客户经理id]
	 */
	public String getAccountManagerId() {
		return accountManagerId;
	}
	/**
	 * set[客户经理id]
	 */
	public void setAccountManagerId(String accountManagerId) {
		this.accountManagerId = accountManagerId;
	}
	/**
	 * get[登记人员]
	 */
	public String getRegisterId() {
		return registerId;
	}
	/**
	 * set[登记人员]
	 */
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	/**
	 * get[登记日期]
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * set[登记日期]
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * get[进件编号]
	 */
	public String getIntoPieceNo() {
		return intoPieceNo;
	}
	/**
	 * set[进件编号]
	 */
	public void setIntoPieceNo(String intoPieceNo) {
		this.intoPieceNo = intoPieceNo;
	}
	/**
	 * get[放款账户名]
	 */
	public String getLoanAccount() {
		return loanAccount;
	}
	/**
	 * set[放款账户名]
	 */
	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}
	/**
	 * get[放款账号卡号]
	 */
	public String getLoanCardNo() {
		return loanCardNo;
	}
	/**
	 * set[放款账号卡号]
	 */
	public void setLoanCardNo(String loanCardNo) {
		this.loanCardNo = loanCardNo;
	}
	/**
	 * get[放款开户银行]
	 */
	public String getLoanBank() {
		return loanBank;
	}
	/**
	 * set[放款开户银行]
	 */
	public void setLoanBank(String loanBank) {
		this.loanBank = loanBank;
	}
	/**
	 * get[放款开户机构]
	 */
	public String getLoanOrg() {
		return loanOrg;
	}
	/**
	 * set[放款开户机构]
	 */
	public void setLoanOrg(String loanOrg) {
		this.loanOrg = loanOrg;
	}
	/**
	 * get[还款账户名]
	 */
	public String getRepaymentAccount() {
		return repaymentAccount;
	}
	/**
	 * set[还款账户名]
	 */
	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}
	/**
	 * get[还款账号卡号]
	 */
	public String getRepaymentCardNo() {
		return repaymentCardNo;
	}
	/**
	 * set[还款账号卡号]
	 */
	public void setRepaymentCardNo(String repaymentCardNo) {
		this.repaymentCardNo = repaymentCardNo;
	}
	/**
	 * get[还款开户银行]
	 */
	public String getRepaymentBank() {
		return repaymentBank;
	}
	/**
	 * set[还款开户银行]
	 */
	public void setRepaymentBank(String repaymentBank) {
		this.repaymentBank = repaymentBank;
	}
	/**
	 * get[还款开户机构]
	 */
	public String getRepaymentOrg() {
		return repaymentOrg;
	}
	/**
	 * set[还款开户机构]
	 */
	public void setRepaymentOrg(String repaymentOrg) {
		this.repaymentOrg = repaymentOrg;
	}
	/**
	 * get[提交时间]
	 */
	public Date getCommitTime() {
		return commitTime;
	}
	/**
	 * set[提交时间]
	 */
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	/**
	 * get[补充信息]
	 */
	public String getSupplementMsg() {
		return supplementMsg;
	}
	/**
	 * set[补充信息]
	 */
	public void setSupplementMsg(String supplementMsg) {
		this.supplementMsg = supplementMsg;
	}
	/**
	 * get[流程id]
	 */
	public String getProcdefId() {
		return procdefId;
	}
	/**
	 * set[流程id]
	 */
	public void setProcdefId(String procdefId) {
		this.procdefId = procdefId;
	}
	
	/** 
	 * get[还款状态]
	 * @return actionStatus
	 */
	public int getActionStatus() {
		return actionStatus;
	}
	/** 
	 * set[还款状态]
	 * @param actionStatus
	 */
	public void setActionStatus(int actionStatus) {
		this.actionStatus = actionStatus;
	}
	
	/**
	 * 获取还款状态名称
	 * @Title: getActionStatus   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param actionStatus
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getActionStatus(int actionStatus) {
		String name = "";
		if(0 == actionStatus) {
			name = "未签订合同";//状态
		} else if (1 == actionStatus) {
			name = "已签定合同 ";//状态
		}else if (2 == actionStatus) {
			name = "还款中";//状态
		}else if (3 == actionStatus) {
			name = "已还完";//状态
		}
		return name;
	}
	
	/**
	 * 获取申请状态名称
	 * @Title: getApplyStatus   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getApplyStatus(int state) {
		String name = "";
		if(0 == state) {
			name = "待发起";//状态
		} else if (1 == state) {
			name = "审核中";//状态
		}else if (2 == state) {
			name = "审核成功";//状态
		}else if (3 == state) {
			name = "审核拒绝";//状态
		}else if(4 == state) {
			name = "违规申请";
		}
		return name;
	}
	/**
	 * get[申请客户机构id]
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * set[申请客户机构id]
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * get[放款类型（对应字典repaymentType）]
	 */
	public String getReleaseType() {
		return releaseType;
	}
	/**
	 * set[放款类型（对应字典repaymentType）]
	 */
	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}
	/**
	 * get[放款时间]
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	/**
	 * set[放款时间]
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * get[（受全局变量TASK_TYPE_EFFECTIVE控制是否生效）任务类型0：默认1：设置批复金额和批准期限2：合同管理3：合同管理和费率调整4：已签订合同5：放款设和通过]
	 */
	public Integer getTaskType() {
		return taskType;
	}
	/**
	 * set[（受全局变量TASK_TYPE_EFFECTIVE控制是否生效）任务类型0：默认1：设置批复金额和批准期限2：合同管理3：合同管理和费率调整4：已签订合同5：放款设和通过]
	 */
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	/**
	 * get[是否为商户进件0：否1：是]
	 */
	public Integer getMerchantState() {
		return merchantState;
	}
	/**
	 * set[是否为商户进件0：否1：是]
	 */
	public void setMerchantState(Integer merchantState) {
		this.merchantState = merchantState;
	}
	/**
	 * get[商户id]
	 */
	public String getMerchantId() {
		return merchantId;
	}
	/**
	 * set[商户id]
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * get[0：商户按比例收款1：商户按固定金额收款]
	 */
	public Integer getMerchantChargeType() {
		return merchantChargeType;
	}
	/**
	 * set[0：商户按比例收款1：商户按固定金额收款]
	 */
	public void setMerchantChargeType(Integer merchantChargeType) {
		this.merchantChargeType = merchantChargeType;
	}
	/**
	 * get[商户收取比例]
	 */
	public BigDecimal getMerchantChargeRate() {
		return merchantChargeRate;
	}
	/**
	 * set[商户收取比例]
	 */
	public void setMerchantChargeRate(BigDecimal merchantChargeRate) {
		this.merchantChargeRate = merchantChargeRate;
	}
	/**
	 * get[商户收取固定金额]
	 */
	public BigDecimal getMerchantChargeAmount() {
		return merchantChargeAmount;
	}
	/**
	 * set[商户收取固定金额]
	 */
	public void setMerchantChargeAmount(BigDecimal merchantChargeAmount) {
		this.merchantChargeAmount = merchantChargeAmount;
	}
	/**
	 * get[批复商户收取比例]
	 */
	public BigDecimal getReplyMerchantChargeRate() {
		return replyMerchantChargeRate;
	}
	/**
	 * set[批复商户收取比例]
	 */
	public void setReplyMerchantChargeRate(BigDecimal replyMerchantChargeRate) {
		this.replyMerchantChargeRate = replyMerchantChargeRate;
	}
	/**
	 * get[批复商户收取固定金额]
	 */
	public BigDecimal getReplyMerchantChargeAmount() {
		return replyMerchantChargeAmount;
	}
	/**
	 * set[批复商户收取固定金额]
	 */
	public void setReplyMerchantChargeAmount(BigDecimal replyMerchantChargeAmount) {
		this.replyMerchantChargeAmount = replyMerchantChargeAmount;
	}
	public Integer getFirstPeriodCharge() {
		return firstPeriodCharge;
	}
	public void setFirstPeriodCharge(Integer firstPeriodCharge) {
		this.firstPeriodCharge = firstPeriodCharge;
	}
	public String getCurrentPeriodRepayId() {
		return currentPeriodRepayId;
	}
	public void setCurrentPeriodRepayId(String currentPeriodRepayId) {
		this.currentPeriodRepayId = currentPeriodRepayId;
	}
	public String getPenaltyCalculationBasic() {
		return penaltyCalculationBasic;
	}
	public void setPenaltyCalculationBasic(String penaltyCalculationBasic) {
		this.penaltyCalculationBasic = penaltyCalculationBasic;
	}
	public Integer getIsOutOfTermPenalty() {
		return isOutOfTermPenalty;
	}
	public void setIsOutOfTermPenalty(Integer isOutOfTermPenalty) {
		this.isOutOfTermPenalty = isOutOfTermPenalty;
	}
	public String getOutOfTermPenaltyCalculationBasic() {
		return outOfTermPenaltyCalculationBasic;
	}
	public void setOutOfTermPenaltyCalculationBasic(String outOfTermPenaltyCalculationBasic) {
		this.outOfTermPenaltyCalculationBasic = outOfTermPenaltyCalculationBasic;
	}
	
}
