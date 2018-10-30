package com.company.platform.base.model.repayment;

import java.math.BigDecimal;
import java.util.Date;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: RespRepaymentModel
 * @Description: TODO(还款计划响应model)
 * @author yangxu
 * @date 2018年1月30日 下午7:28:54
 * 
 */
@SuppressWarnings("all")
public class RespRepaymentModel extends BaseHttpParamsAppReq {

	/**
	 * 当前期数：从1开始
	 */
	private Integer periodNum;
	/**
	 * 总期数
	 */
	private Integer totalPeriodNum;
	/**
	 * 约定还款时间
	 */
	private Date repayTime;
	/**
	 * 应还本金
	 */
	private BigDecimal principal;
	/**
	 * 应还利息
	 */
	private BigDecimal interest;
	/**
	 * 本期还完，剩余应还本金
	 */
	private BigDecimal surplusPrincipal;
	/**
	 * 本期还完，剩余应还利息
	 */
	private BigDecimal surplusInterest;
	/**
	 * 总应还本金
	 */
	private BigDecimal totalPrincipal;
	/**
	 * 总应还利息
	 */
	private BigDecimal totalInterest;

	public Integer getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}

	public Integer getTotalPeriodNum() {
		return totalPeriodNum;
	}

	public void setTotalPeriodNum(Integer totalPeriodNum) {
		this.totalPeriodNum = totalPeriodNum;
	}

	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getSurplusPrincipal() {
		return surplusPrincipal;
	}

	public void setSurplusPrincipal(BigDecimal surplusPrincipal) {
		this.surplusPrincipal = surplusPrincipal;
	}

	public BigDecimal getSurplusInterest() {
		return surplusInterest;
	}

	public void setSurplusInterest(BigDecimal surplusInterest) {
		this.surplusInterest = surplusInterest;
	}

	public BigDecimal getTotalPrincipal() {
		return totalPrincipal;
	}

	public void setTotalPrincipal(BigDecimal totalPrincipal) {
		this.totalPrincipal = totalPrincipal;
	}

	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}
}
