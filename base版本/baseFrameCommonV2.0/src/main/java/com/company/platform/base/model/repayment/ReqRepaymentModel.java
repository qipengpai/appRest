package com.company.platform.base.model.repayment;

import java.math.BigDecimal;
import java.util.Date;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: ReqRepaymentModel
 * @Description: TODO(借贷信息模型)
 * @author yangxu
 * @date 2018年1月30日 下午7:26:46
 * 
 */
@SuppressWarnings("all")
public class ReqRepaymentModel extends BaseHttpParamsAppReq {

	/**
	 * 借贷金额
	 */
	private BigDecimal amount;
	/**
	 * 借贷期限
	 */
	private Integer termCount;
	/**
	 * 借贷期限单位
	 */
	private String termUnit;
	/**
	 * 还款方式
	 */
	private String repayType;
	/**
	 * 年化利率
	 */
	private BigDecimal interestRate;
	/**
	 * 还款间隔期限
	 */
	private Integer repayTermCount;
	/**
	 * 还款间隔期限单位
	 */
	private String repayTermUnit;
	/**
	 * 一年的天数
	 */
	private String daysOneYear;
	/**
	 * 一个月的天数
	 */
	private String daysOneMonth;
	/**
	 * 舍入方式
	 */
	private int round;
	/**
	 * 基准时间（主动还当期、展期会用到的当期约定还款时间）
	 */
	private Date baseTime;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTermCount() {
		return termCount;
	}

	public void setTermCount(Integer termCount) {
		this.termCount = termCount;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Integer getRepayTermCount() {
		return repayTermCount;
	}

	public void setRepayTermCount(Integer repayTermCount) {
		this.repayTermCount = repayTermCount;
	}

	public String getRepayTermUnit() {
		return repayTermUnit;
	}

	public void setRepayTermUnit(String repayTermUnit) {
		this.repayTermUnit = repayTermUnit;
	}

	public String getDaysOneYear() {
		return daysOneYear;
	}

	public void setDaysOneYear(String daysOneYear) {
		this.daysOneYear = daysOneYear;
	}

	public String getDaysOneMonth() {
		return daysOneMonth;
	}

	public void setDaysOneMonth(String daysOneMonth) {
		this.daysOneMonth = daysOneMonth;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Date getBaseTime() {
		return baseTime;
	}

	public void setBaseTime(Date baseTime) {
		this.baseTime = baseTime;
	}

}
