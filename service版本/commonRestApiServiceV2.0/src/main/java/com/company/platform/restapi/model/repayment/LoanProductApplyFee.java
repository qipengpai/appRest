package com.company.platform.restapi.model.repayment;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
* @ClassName: LoanProductApplyFee 
* @Description: TODO(借贷申请费用) 
* @author dongjian 
* @date 2018年5月23日 下午2:32:10 
*  
*/
public class LoanProductApplyFee implements Serializable {

	private static final long serialVersionUID = 7700104457457148968L;
	
	private String id;
	/**
	 * 费用描述
	 */
	private String name;
	/**
	 * 费用类型
	 */
	private String feeType;
	/**
	 * 收取类型
	 */
	private String chargeType;
	/**
	 * 费用金额
	 */
	private BigDecimal feeAmount;
	/**
	 * 比例计算基础
	 */
	private String calculationBasic;
	/**
	 * 收取比例
	 */
	private BigDecimal feeRate;
	/**
	 * 费用减免
	 */
	private BigDecimal feeReduction;
	/**
	 * 实际费用金额
	 */
	private BigDecimal actualFeeAmount;
	/**
	 * 当前期数
	 */
	private int periodNum;
	/**
	 * 付费标志 0：未付费 1：已付费
	 */
	private int status;
	/**
	 * 产品费用id
	 */
	private String loanProductFeeId;
	/**
	 * 收费条件id
	 */
	private String feeConditionId;
	/**
	 * 借贷申请id
	 */
	private String loanProductApplyId;
	/**
	 * 展期申请id
	 */
	private String extensionApplyId;
	/**
	 * 0：借贷申请 1：展期申请
	 */
	private Integer applyType;
	
	/**
	 * 费用减免符号：in + ; out -
	 */
	private String feeReductionState;
	
	public String getFeeReductionState() {
        return feeReductionState;
    }
    public void setFeeReductionState(String feeReductionState) {
        this.feeReductionState = feeReductionState;
    }
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
	 * get[费用描述]
	 */
	public String getName() {
		return name;
	}
	/**
	 * set[费用描述]
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get[费用类型]
	 */
	public String getFeeType() {
		return feeType;
	}
	/**
	 * set[费用类型]
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * get[收取类型]
	 */
	public String getChargeType() {
		return chargeType;
	}
	/**
	 * set[收取类型]
	 */
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	/**
	 * get[费用金额]
	 */
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	/**
	 * set[费用金额]
	 */
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	/**
	 * get[比例计算基础]
	 */
	public String getCalculationBasic() {
		return calculationBasic;
	}
	/**
	 * set[比例计算基础]
	 */
	public void setCalculationBasic(String calculationBasic) {
		this.calculationBasic = calculationBasic;
	}
	/**
	 * get[收取比例]
	 */
	public BigDecimal getFeeRate() {
		return feeRate;
	}
	/**
	 * set[收取比例]
	 */
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	/**
	 * get[费用减免]
	 */
	public BigDecimal getFeeReduction() {
		return feeReduction;
	}
	/**
	 * set[费用减免]
	 */
	public void setFeeReduction(BigDecimal feeReduction) {
		this.feeReduction = feeReduction;
	}
	/**
	 * get[实际费用金额]
	 */
	public BigDecimal getActualFeeAmount() {
		return actualFeeAmount;
	}
	/**
	 * set[实际费用金额]
	 */
	public void setActualFeeAmount(BigDecimal actualFeeAmount) {
		this.actualFeeAmount = actualFeeAmount;
	}
	/**
	 * get[当前期数]
	 */
	public int getPeriodNum() {
		return periodNum;
	}
	/**
	 * set[当前期数]
	 */
	public void setPeriodNum(int periodNum) {
		this.periodNum = periodNum;
	}
	/**
	 * get[付费标志0：未付费1：已付费]
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * set[付费标志0：未付费1：已付费]
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * get[产品费用id]
	 */
	public String getLoanProductFeeId() {
		return loanProductFeeId;
	}
	/**
	 * set[产品费用id]
	 */
	public void setLoanProductFeeId(String loanProductFeeId) {
		this.loanProductFeeId = loanProductFeeId;
	}
	/**
	 * get[收费条件id]
	 */
	public String getFeeConditionId() {
		return feeConditionId;
	}
	/**
	 * set[收费条件id]
	 */
	public void setFeeConditionId(String feeConditionId) {
		this.feeConditionId = feeConditionId;
	}
	/**
	 * get[借贷申请id]
	 */
	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}
	/**
	 * set[借贷申请id]
	 */
	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}
	/**
	 * get[展期申请id]
	 */
	public String getExtensionApplyId() {
		return extensionApplyId;
	}
	/**
	 * set[展期申请id]
	 */
	public void setExtensionApplyId(String extensionApplyId) {
		this.extensionApplyId = extensionApplyId;
	}
	/**
	 * get[0：借贷申请1：展期申请]
	 */
	public Integer getApplyType() {
		return applyType;
	}
	/**
	 * set[0：借贷申请1：展期申请]
	 */
	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}
}
