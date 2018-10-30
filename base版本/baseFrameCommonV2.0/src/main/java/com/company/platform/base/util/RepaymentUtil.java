package com.company.platform.base.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.company.platform.base.model.repayment.ReqRepaymentModel;
import com.company.platform.base.model.repayment.RespRepaymentModel;

/**
 * 还款计划
 * @Title RepaymentUtil.java
 * @Description TODO
 * @author lxy
 * @date 2016-8-9
 */
public class RepaymentUtil {
	/**
	 * 舍入方式
	 */
	private static Integer ROUND;
	/**
	 * 一年天数
	 */
	private static BigDecimal DAYS_ONE_YEAR;
	/**
	 * 一个月天数
	 */
	private static BigDecimal DAYS_ONE_MONTH;
	/**
	 * 一季天数
	 */
	private static BigDecimal DAYS_ONE_SEASON;
	/**
	 * 一年12个月
	 */
	private static final BigDecimal MONTHS_ONE_YEAR = new BigDecimal(12);
	/**
	 * 一季3个月
	 */
	private static final BigDecimal MONTHS_ONE_SEASON = new BigDecimal(3);
	/**
	 * 一次性还本付息
	 */
	private static final String ONE_TIME_REPAYMENT = "1";
	/**
	 * 按期还息到期还本
	 */
	private static final String THE_FIRST_INTEREST_RATE = "2";
	/**
	 * 等额本息
	 */
	private static final String EQUAL_INSTALLMENTS_OF_PRINCIPAL_AND_INTEREST = "3";
	/**
	 * 等本等息
	 */
	private static final String SUCH_AS_INTEREST_RATES = "4";
	/**
	 * 借款期限单位：日
	 */
	private static final String TERM_UNIT_DAY = "1";
	/**
	 * 借款期限单位：月
	 */
	private static final String TERM_UNIT_MONTH = "2";
	/**
	 * 还款间隔期限单位：日
	 */
	private static final String REPAY_TERM_UNIT_DAY = "1";
	/**
	 * 还款间隔期限单位：月
	 */
	private static final String REPAY_TERM_UNIT_MONTH = "2";
	/**
	 * 还款间隔期限单位：季
	 */
	private static final String REPAY_TERM_UNIT_SEASON = "3";
	/**
	 * 还款间隔期限单位：年
	 */
	private static final String REPAY_TERM_UNIT_YEAR = "4";
	
	public static List<RespRepaymentModel> createRepayment(ReqRepaymentModel reqRepaymentModel) {
		ROUND = reqRepaymentModel.getRound();
		DAYS_ONE_YEAR = new BigDecimal(reqRepaymentModel.getDaysOneYear());
		DAYS_ONE_MONTH = new BigDecimal(reqRepaymentModel.getDaysOneMonth());
		DAYS_ONE_SEASON = DAYS_ONE_MONTH.multiply(new BigDecimal(3));
		
		String repayType = reqRepaymentModel.getRepayType();
		if(ONE_TIME_REPAYMENT.equals(repayType)) {
			return oneTimeRepayment(reqRepaymentModel);
		} else if(THE_FIRST_INTEREST_RATE.equals(repayType)) {
			return theFirstInterestRate(reqRepaymentModel);
		} else if(EQUAL_INSTALLMENTS_OF_PRINCIPAL_AND_INTEREST.equals(repayType)) {
			return equalInstallmentsOfPrincipalAndInterest(reqRepaymentModel);
		} else if(SUCH_AS_INTEREST_RATES.equals(repayType)) {
			return suchAsInterestRates(reqRepaymentModel);
		}
		return null;
	}
	
	/**
	 * 一次性还本付息
	 * @param reqRepaymentModel
	 * @return
	 */
	private static List<RespRepaymentModel> oneTimeRepayment(ReqRepaymentModel reqRepaymentModel) {
		/*借贷金额*/
		BigDecimal amount = reqRepaymentModel.getAmount();
		/*年化利率*/
		BigDecimal interestRate = reqRepaymentModel.getInterestRate();
		/*借贷期限*/
		BigDecimal termCount = new BigDecimal(reqRepaymentModel.getTermCount());
		/*借贷期限单位*/
		String termUnit = reqRepaymentModel.getTermUnit();
		/*还款间隔期限*/
		//BigDecimal repayTermCount = new BigDecimal(reqRepaymentModel.getRepayTermCount());
		/*还款间隔期限单位*/
		//String repayTermUnit = reqRepaymentModel.getRepayTermUnit();
		
		Date baseTime = reqRepaymentModel.getBaseTime();//基准时间（主动还当期、展期会用到的当期约定还款时间）
		Calendar baseCalendar = Calendar.getInstance();
		if(baseTime!=null){//基准时间date转为Calendar
			baseCalendar.setTime(reqRepaymentModel.getBaseTime());
		}
		
		
		if(TERM_UNIT_DAY.equals(termUnit)) {/*借款期限单位为日*/
			/*if(!REPAY_TERM_UNIT_DAY.equals(repayTermUnit) || termCount.compareTo(repayTermCount) != 0) {
				return null;
			}*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*日利率*/
			BigDecimal day_interestRate = interestRate.divide(DAYS_ONE_YEAR, 18, ROUND);
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
			
			/*总利息*/
			BigDecimal totalInterest = amount.multiply(termCount).multiply(day_interestRate).setScale(2, ROUND);
			
			RespRepaymentModel repay = new RespRepaymentModel();
			repay.setPeriodNum(1);
			repay.setTotalPeriodNum(1);
			repay.setPrincipal(amount);
			repay.setInterest(totalInterest);
			
			if(baseTime!=null){
				baseCalendar.add(Calendar.DAY_OF_MONTH, termCount.intValue());
				repay.setRepayTime(baseCalendar.getTime());
			}else{
				nextRepayDate.add(Calendar.DAY_OF_MONTH, termCount.intValue());
				repay.setRepayTime(nextRepayDate.getTime());
			}
			
			repay.setSurplusInterest(BigDecimal.ZERO);
			repay.setSurplusPrincipal(BigDecimal.ZERO);
			repay.setTotalPrincipal(amount);
			repay.setTotalInterest(totalInterest);
			repaymentList.add(repay);
			
			return repaymentList;
		} else if(TERM_UNIT_MONTH.equals(termUnit)) {/*借款期限单位为月*/
			/*if(!REPAY_TERM_UNIT_MONTH.equals(repayTermUnit) || termCount.compareTo(repayTermCount) != 0) {
				return null;
			}*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*月利率*/
			BigDecimal month_interestRate = interestRate.divide(MONTHS_ONE_YEAR, 18, ROUND);
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
			
			/*总利息*/
			BigDecimal totalInterest = amount.multiply(termCount).multiply(month_interestRate).setScale(2, ROUND);
			
			RespRepaymentModel repay = new RespRepaymentModel();
			repay.setPeriodNum(1);
			repay.setTotalPeriodNum(1);
			repay.setPrincipal(amount);
			repay.setInterest(totalInterest);
			
			if(baseTime!=null){
				baseCalendar.add(Calendar.MONTH, termCount.intValue());
				repay.setRepayTime(baseCalendar.getTime());
			}else{
				nextRepayDate.add(Calendar.MONTH, termCount.intValue());
				repay.setRepayTime(nextRepayDate.getTime());
			}

			repay.setSurplusPrincipal(BigDecimal.ZERO);
			repay.setSurplusInterest(BigDecimal.ZERO);
			repay.setTotalPrincipal(amount);
			repay.setTotalInterest(totalInterest);
			repaymentList.add(repay);
			
			return repaymentList;
		}
		
		return null;
	}
	/**
	 * 先息后本
	 * @param reqRepaymentModel
	 * @return
	 */
	private static List<RespRepaymentModel> theFirstInterestRate(ReqRepaymentModel reqRepaymentModel) {
		/*借贷金额*/
		BigDecimal amount = reqRepaymentModel.getAmount();
		/*年化利率*/
		BigDecimal interestRate = reqRepaymentModel.getInterestRate();
		/*借贷期限*/
		BigDecimal termCount = new BigDecimal(reqRepaymentModel.getTermCount());
		/*借贷期限单位*/
		String termUnit = reqRepaymentModel.getTermUnit();
		/*还款间隔期限*/
		BigDecimal repayTermCount = new BigDecimal(reqRepaymentModel.getRepayTermCount());
		/*还款间隔期限单位*/
		String repayTermUnit = reqRepaymentModel.getRepayTermUnit();
		
		Date baseTime = reqRepaymentModel.getBaseTime();//基准时间（主动还当期、展期会用到的当期约定还款时间）
		Calendar baseCalendar = Calendar.getInstance();
		if(baseTime!=null){//基准时间date转为Calendar
			baseCalendar.setTime(reqRepaymentModel.getBaseTime());
		}
		
		if(TERM_UNIT_DAY.equals(termUnit)) {/*借款期限单位为日*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*日利率*/
			BigDecimal day_interestRate = interestRate.divide(DAYS_ONE_YEAR, 18, ROUND);
			
			/*总期数*/
			Integer totalPeriodNum = 0;
			/*最后一期间隔天数*/
			Integer lastPeriodNumDays = 0;
			
			if(REPAY_TERM_UNIT_MONTH.equals(repayTermUnit)) {
				/*还款期限单位为月，换算成天数*/
				repayTermCount = repayTermCount.multiply(DAYS_ONE_MONTH);
			} else if(REPAY_TERM_UNIT_SEASON.equals(repayTermUnit)) {
				/*还款期限单位为季，换算成天数*/
				repayTermCount = repayTermCount.multiply(DAYS_ONE_SEASON);
			} else if(REPAY_TERM_UNIT_YEAR.equals(repayTermUnit)) {
				/*还款期限单位为年，换算成天数*/
				repayTermCount = repayTermCount.multiply(DAYS_ONE_YEAR);
			}
			
			lastPeriodNumDays = termCount.remainder(repayTermCount).intValue();
			
			/*
			 * 1.总期数=借款周期/还款周期
			 * 2.如果：借款周期%还款周期>0，则：总期数+1 
			 */
			totalPeriodNum = termCount.divideToIntegralValue(repayTermCount).intValue();
			totalPeriodNum = lastPeriodNumDays > 0 ? ++totalPeriodNum : totalPeriodNum;
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
			
			/*总利息*/
			BigDecimal totalInterest = amount.multiply(termCount).multiply(day_interestRate).setScale(2, ROUND);
			
			if(totalPeriodNum == 1) {/*总还款期数只有一期*/
				RespRepaymentModel repay = new RespRepaymentModel();
				repay.setPeriodNum(totalPeriodNum);
				repay.setTotalPeriodNum(totalPeriodNum);
				repay.setPrincipal(amount);
				repay.setInterest(totalInterest);
				
				if(baseTime!=null){
					baseCalendar.add(Calendar.DAY_OF_MONTH, termCount.intValue());
					repay.setRepayTime(baseCalendar.getTime());
				}else{
					nextRepayDate.add(Calendar.DAY_OF_MONTH, termCount.intValue());
					repay.setRepayTime(nextRepayDate.getTime());
				}

				repay.setSurplusPrincipal(BigDecimal.ZERO);
				repay.setSurplusInterest(BigDecimal.ZERO);
				repay.setTotalPrincipal(amount);
				repay.setTotalInterest(totalInterest);
				repaymentList.add(repay);
			} else {/*总还款期数多于一期*/
				/*前N期应还利息总和*/
				BigDecimal interest_total = BigDecimal.ZERO;
				for(int periodNum = 1; periodNum <= totalPeriodNum; periodNum++) {
					RespRepaymentModel repay = new RespRepaymentModel();
					repay.setTotalPeriodNum(totalPeriodNum);
					repay.setPeriodNum(periodNum);
					if(periodNum == totalPeriodNum) {/*最后一期*/
						/*如果：最后一期还款间隔>0，赋值：还款周期间隔=最后一期还款间隔*/
						if(lastPeriodNumDays > 0) {
							repayTermCount = new BigDecimal(lastPeriodNumDays);
						}
						repay.setPrincipal(amount);
						/*（平账处理）应还利息=借贷总利息-前N期应还利息总和*/
						repay.setInterest(totalInterest.subtract(interest_total));
						repay.setSurplusPrincipal(BigDecimal.ZERO);
						repay.setSurplusInterest(BigDecimal.ZERO);
					} else {
						repay.setPrincipal(BigDecimal.ZERO);
						repay.setInterest(amount.multiply(repayTermCount).multiply(day_interestRate).setScale(2, ROUND));
						interest_total = interest_total.add(repay.getInterest());
						repay.setSurplusPrincipal(amount);
						repay.setSurplusInterest(totalInterest.subtract(interest_total));
					}
					
					if(baseTime!=null){
						baseCalendar.add(Calendar.DAY_OF_MONTH, repayTermCount.intValue());
						repay.setRepayTime(baseCalendar.getTime());
					}else{
						nextRepayDate.add(Calendar.DAY_OF_MONTH, repayTermCount.intValue());
						repay.setRepayTime(nextRepayDate.getTime());
					}

					repay.setTotalPrincipal(amount);
					repay.setTotalInterest(totalInterest);
					repaymentList.add(repay);
				}
			}
			return repaymentList;
		} else if(TERM_UNIT_MONTH.equals(termUnit)) {/*借款期限单位为月*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*月利率*/
			BigDecimal month_interestRate = interestRate.divide(MONTHS_ONE_YEAR, 18, ROUND);
			/*总期数*/
			Integer totalPeriodNum = 0;
			/*最后一期间隔月数*/
			Integer lastPeriodNumMonths = 0;
			
			if(REPAY_TERM_UNIT_SEASON.equals(repayTermUnit)) {
				/*还款期限单位为季，换算成月数*/
				repayTermCount = repayTermCount.multiply(MONTHS_ONE_SEASON);
			} else if(REPAY_TERM_UNIT_YEAR.equals(repayTermUnit)) {
				/*还款期限单位为年，换算成月数*/
				repayTermCount = repayTermCount.multiply(MONTHS_ONE_YEAR);
			} else if(REPAY_TERM_UNIT_DAY.equals(repayTermUnit)) {
				return null;
			}
			
			lastPeriodNumMonths = termCount.remainder(repayTermCount).intValue();
			/*
			 * 1.总期数=借款周期/还款周期
			 * 2.如果：借款周期%还款周期>0，则：总期数+1 
			 */
			totalPeriodNum = termCount.divideToIntegralValue(repayTermCount).intValue();
			totalPeriodNum = lastPeriodNumMonths > 0 ? ++totalPeriodNum : totalPeriodNum;
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
//			Date releaseDate = nextRepayDate.getTime();
			
			/*总利息*/
			BigDecimal totalInterest = amount.multiply(termCount).multiply(month_interestRate).setScale(2, ROUND);
			
			if(totalPeriodNum == 1) {/*总还款期数只有一期*/
				RespRepaymentModel repay = new RespRepaymentModel();
				repay.setPeriodNum(totalPeriodNum);
				repay.setTotalPeriodNum(totalPeriodNum);
				repay.setPrincipal(amount);
				repay.setInterest(totalInterest);
				
				if(baseTime!=null){
					baseCalendar.add(Calendar.MONTH, termCount.intValue());
					repay.setRepayTime(baseCalendar.getTime());
				}else{
					nextRepayDate.add(Calendar.MONTH, termCount.intValue());
					repay.setRepayTime(nextRepayDate.getTime());
				}
				
				repay.setSurplusPrincipal(BigDecimal.ZERO);
				repay.setSurplusInterest(BigDecimal.ZERO);
				repay.setTotalPrincipal(amount);
				repay.setTotalInterest(totalInterest);
				repaymentList.add(repay);
			} else {/*总还款期数多于一期*/
				/*前N期应还利息总和*/
				BigDecimal interest_total = BigDecimal.ZERO;
				for(int periodNum = 1; periodNum <= totalPeriodNum; periodNum++) {
					RespRepaymentModel repay = new RespRepaymentModel();
					repay.setTotalPeriodNum(totalPeriodNum);
					repay.setPeriodNum(periodNum);
					if(periodNum == totalPeriodNum) {/*最后一期*/
						/*如果：最后一期还款间隔>0，赋值：还款周期间隔=最后一期还款间隔*/
						if(lastPeriodNumMonths > 0) {
							repayTermCount = new BigDecimal(lastPeriodNumMonths);
						}
						repay.setPrincipal(amount);
						/*（平账处理）应还利息=借贷总利息-前N期应还利息总和*/
						repay.setInterest(totalInterest.subtract(interest_total));
						repay.setSurplusPrincipal(BigDecimal.ZERO);
						repay.setSurplusInterest(BigDecimal.ZERO);
					} else {
						repay.setPrincipal(BigDecimal.ZERO);
						repay.setInterest(amount.multiply(repayTermCount).multiply(month_interestRate).setScale(2, ROUND));
						interest_total = interest_total.add(repay.getInterest());
						repay.setSurplusPrincipal(amount);
						repay.setSurplusInterest(totalInterest.subtract(interest_total));
					}
//					nextRepayDate.setTime(releaseDate);
					
					if(baseTime!=null){
						baseCalendar.setTime(baseTime);
						baseCalendar.add(Calendar.MONTH, repayTermCount.intValue());
						repay.setRepayTime(baseCalendar.getTime());
					}else{
						nextRepayDate.add(Calendar.MONTH, repayTermCount.intValue());
						repay.setRepayTime(nextRepayDate.getTime());
					}

					repay.setTotalPrincipal(amount);
					repay.setTotalInterest(totalInterest);
					repaymentList.add(repay);
				}
			}
			return repaymentList;
		}
		return null;
	}
	/**
	 * 等额本息
	 * @param reqRepaymentModel
	 * @return
	 */
	public static List<RespRepaymentModel> equalInstallmentsOfPrincipalAndInterest(ReqRepaymentModel reqRepaymentModel) {
		/*借贷金额*/
		BigDecimal amount = reqRepaymentModel.getAmount();
		/*年化利率*/
		BigDecimal interestRate = reqRepaymentModel.getInterestRate();
		/*借贷期限*/
		BigDecimal termCount = new BigDecimal(reqRepaymentModel.getTermCount());
		/*借贷期限单位*/
		String termUnit = reqRepaymentModel.getTermUnit();
		/*还款间隔期限*/
		BigDecimal repayTermCount = new BigDecimal(reqRepaymentModel.getRepayTermCount());
		/*还款间隔期限单位*/
		String repayTermUnit = reqRepaymentModel.getRepayTermUnit();
		
		Date baseTime = reqRepaymentModel.getBaseTime();//基准时间（主动还当期、展期会用到的当期约定还款时间）
		Calendar baseCalendar = Calendar.getInstance();
		if(baseTime!=null){//基准时间date转为Calendar
			baseCalendar.setTime(reqRepaymentModel.getBaseTime());
		}
		
		if(TERM_UNIT_MONTH.equals(termUnit)) {/*借款期限单位为月*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*月利率*/
			BigDecimal month_interestRate = interestRate.divide(MONTHS_ONE_YEAR, 18, ROUND);
			/*(1+月利率)^借款期限*/
			BigDecimal pow = BigDecimal.ONE.add(month_interestRate).pow(termCount.intValue());
			/*每月还款金额*/
			BigDecimal month_repayAmount = amount.multiply(month_interestRate).multiply(pow)
					.divide(pow.subtract(BigDecimal.ONE), 18, ROUND);
			/*总期数*/
			Integer totalPeriodNum = 0;
			/*最后一期间隔月数*/
			Integer lastPeriodNumMonths = 0;
			
			if(REPAY_TERM_UNIT_SEASON.equals(repayTermUnit)) {
				/*还款期限单位为季，换算成月数*/
				repayTermCount = repayTermCount.multiply(MONTHS_ONE_SEASON);
			} else if(REPAY_TERM_UNIT_YEAR.equals(repayTermUnit)) {
				/*还款期限单位为年，换算成月数*/
				repayTermCount = repayTermCount.multiply(MONTHS_ONE_YEAR);
			} else if(REPAY_TERM_UNIT_DAY.equals(repayTermUnit)) {
				return null;
			}
			
			lastPeriodNumMonths = termCount.remainder(repayTermCount).intValue();
			/*
			 * 1.总期数=借款周期/还款周期
			 * 2.如果：借款周期%还款周期>0，则：总期数+1 
			 */
			totalPeriodNum = termCount.divideToIntegralValue(repayTermCount).intValue();
			totalPeriodNum = lastPeriodNumMonths > 0 ? ++totalPeriodNum : totalPeriodNum;
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
//			Date releaseDate = nextRepayDate.getTime();
			
			/*总利息*/
			BigDecimal totalInterest = month_repayAmount.multiply(termCount).subtract(amount).setScale(2, ROUND);
			
			if(totalPeriodNum == 1) {/*总还款期数只有一期*/
				RespRepaymentModel repay = new RespRepaymentModel();
				repay.setPeriodNum(totalPeriodNum);
				repay.setTotalPeriodNum(totalPeriodNum);
				repay.setPrincipal(amount);
				repay.setInterest(totalInterest);
				
				if(baseTime!=null){
					baseCalendar.add(Calendar.MONTH, termCount.intValue());
					repay.setRepayTime(baseCalendar.getTime());
				}else{
					nextRepayDate.add(Calendar.MONTH, termCount.intValue());
					repay.setRepayTime(nextRepayDate.getTime());
				}

				repay.setSurplusPrincipal(BigDecimal.ZERO);
				repay.setSurplusInterest(BigDecimal.ZERO);
				repay.setTotalPrincipal(amount);
				repay.setTotalInterest(totalInterest);
				repaymentList.add(repay);
			} else {/*总还款期数多于一期*/
				/*前N期应还本金总和*/
				BigDecimal principal_total = BigDecimal.ZERO;
				/*前N期应还利息总和*/
				BigDecimal interest_total = BigDecimal.ZERO;
				/*剩余应还本金*/
				BigDecimal surplus_amount = amount;
				for(int periodNum = 1; periodNum <= totalPeriodNum; periodNum++) {
					RespRepaymentModel repay = new RespRepaymentModel();
					repay.setTotalPeriodNum(totalPeriodNum);
					repay.setPeriodNum(periodNum);
					if(periodNum == totalPeriodNum) {/*最后一期*/
						/*如果：最后一期还款间隔>0，赋值：还款周期间隔=最后一期还款间隔*/
						if(lastPeriodNumMonths > 0) {
							repayTermCount = new BigDecimal(lastPeriodNumMonths);
						}
						/*（平账处理）应还本金=借贷金额-前N期应还本金总和*/
						repay.setPrincipal(amount.subtract(principal_total));
						/*（平账处理）应还利息=借贷总利息-前N期应还利息总和*/
						repay.setInterest(totalInterest.subtract(interest_total));
						repay.setSurplusPrincipal(BigDecimal.ZERO);
						repay.setSurplusInterest(BigDecimal.ZERO);
					} else {
						if(repayTermCount.compareTo(BigDecimal.ONE) == 0) {/*还款间隔为1个月*/
							repay.setInterest(surplus_amount.multiply(month_interestRate).setScale(2, ROUND));
							repay.setPrincipal(month_repayAmount.subtract(repay.getInterest()).setScale(2, ROUND));
							surplus_amount = surplus_amount.subtract(repay.getPrincipal());
						} else {/*还款间隔大于一个月*/
							/*本期应还本金总额*/
							BigDecimal principal_total_period = BigDecimal.ZERO;
							/*本期应还利息总额*/
							BigDecimal interest_total_period = BigDecimal.ZERO;
							for(int i = 0, size = repayTermCount.intValue(); i < size; i++) {
								/*当月利息*/
								BigDecimal interest_month = surplus_amount.multiply(month_interestRate).setScale(2, ROUND);
								/*当月本金*/
								BigDecimal principal_month = month_repayAmount.subtract(interest_month);
								
								interest_total_period = interest_total_period.add(interest_month);
								principal_total_period = principal_total_period.add(principal_month);
								surplus_amount = surplus_amount.subtract(principal_month);
							}
							repay.setPrincipal(principal_total_period.setScale(2, ROUND));
							repay.setInterest(interest_total_period.setScale(2, ROUND));
						}						
						principal_total = principal_total.add(repay.getPrincipal());
						interest_total = interest_total.add(repay.getInterest());
						repay.setSurplusPrincipal(amount.subtract(principal_total));
						repay.setSurplusInterest(totalInterest.subtract(interest_total));
					}
//					nextRepayDate.setTime(releaseDate);
					
					if(baseTime!=null){
						baseCalendar.setTime(baseTime);
						baseCalendar.add(Calendar.MONTH, repayTermCount.intValue());
						repay.setRepayTime(baseCalendar.getTime());
					}else{
						nextRepayDate.add(Calendar.MONTH, repayTermCount.intValue());
						repay.setRepayTime(nextRepayDate.getTime());
					}

					repay.setTotalPrincipal(amount);
					repay.setTotalInterest(totalInterest);
					repaymentList.add(repay);
				}
			}
			return repaymentList;
		}
		return null;
	}
	/**
	 * 等本等息
	 * @param reqRepaymentModel
	 * @return
	 */
	private static List<RespRepaymentModel> suchAsInterestRates(ReqRepaymentModel reqRepaymentModel) {

		/*借贷金额*/
		BigDecimal amount = reqRepaymentModel.getAmount();
		/*年化利率*/
		BigDecimal interestRate = reqRepaymentModel.getInterestRate();
		/*借贷期限*/
		BigDecimal termCount = new BigDecimal(reqRepaymentModel.getTermCount());
		/*借贷期限单位*/
		String termUnit = reqRepaymentModel.getTermUnit();
		/*还款间隔期限*/
		BigDecimal repayTermCount = new BigDecimal(reqRepaymentModel.getRepayTermCount());
		/*还款间隔期限单位*/
		String repayTermUnit = reqRepaymentModel.getRepayTermUnit();
		
		Date baseTime = reqRepaymentModel.getBaseTime();//基准时间（主动还当期、展期会用到的当期约定还款时间）
		Calendar baseCalendar = Calendar.getInstance();
		if(baseTime!=null){//基准时间date转为Calendar
			baseCalendar.setTime(reqRepaymentModel.getBaseTime());
		}
		
		if(TERM_UNIT_DAY.equals(termUnit)) {/*借款期限单位为日*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*日利率*/
			BigDecimal day_interestRate = interestRate.divide(DAYS_ONE_YEAR, 18, ROUND);
			
			/*总期数*/
			Integer totalPeriodNum = 0;
			/*最后一期间隔天数*/
			Integer lastPeriodNumDays = 0;
			
			if(REPAY_TERM_UNIT_MONTH.equals(repayTermUnit)) {
				/*还款期限单位为月，换算成天数*/
				repayTermCount = repayTermCount.multiply(DAYS_ONE_MONTH);
			} else if(REPAY_TERM_UNIT_SEASON.equals(repayTermUnit)) {
				/*还款期限单位为季，换算成天数*/
				repayTermCount = repayTermCount.multiply(DAYS_ONE_SEASON);
			} else if(REPAY_TERM_UNIT_YEAR.equals(repayTermUnit)) {
				/*还款期限单位为年，换算成天数*/
				repayTermCount = repayTermCount.multiply(DAYS_ONE_YEAR);
			}
			
			lastPeriodNumDays = termCount.remainder(repayTermCount).intValue();
			
			/*
			 * 1.总期数=借款周期/还款周期
			 * 2.如果：借款周期%还款周期>0，则：总期数+1 
			 */
			totalPeriodNum = termCount.divideToIntegralValue(repayTermCount).intValue();
			totalPeriodNum = lastPeriodNumDays > 0 ? ++totalPeriodNum : totalPeriodNum;
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
			
			/*总利息*/
			BigDecimal totalInterest = amount.multiply(termCount).multiply(day_interestRate).setScale(2, ROUND);
			
			if(totalPeriodNum == 1) {/*总还款期数只有一期*/
				RespRepaymentModel repay = new RespRepaymentModel();
				repay.setPeriodNum(totalPeriodNum);
				repay.setTotalPeriodNum(totalPeriodNum);
				repay.setPrincipal(amount);
				repay.setInterest(totalInterest);
				
				if(baseTime!=null){
					baseCalendar.add(Calendar.DAY_OF_MONTH, termCount.intValue());
					repay.setRepayTime(baseCalendar.getTime());
				}else{
					nextRepayDate.add(Calendar.DAY_OF_MONTH, termCount.intValue());
					repay.setRepayTime(nextRepayDate.getTime());
				}

				repay.setSurplusInterest(BigDecimal.ZERO);
				repay.setSurplusPrincipal(BigDecimal.ZERO);
				repay.setTotalPrincipal(amount);
				repay.setTotalInterest(totalInterest);
				repaymentList.add(repay);
			} else {/*总还款期数多于一期*/
				/*每一期应还本金，不包括最后一期*/
				BigDecimal principal_period = amount.divide(termCount, 18, ROUND).multiply(repayTermCount).setScale(2, ROUND);
				/*前N期应还本金总和*/
				BigDecimal principal_total = BigDecimal.ZERO;
				/*前N期应还利息总和*/
				BigDecimal interest_total = BigDecimal.ZERO;
				for(int periodNum = 1; periodNum <= totalPeriodNum; periodNum++) {
					RespRepaymentModel repay = new RespRepaymentModel();
					repay.setTotalPeriodNum(totalPeriodNum);
					repay.setPeriodNum(periodNum);
					if(periodNum == totalPeriodNum) {/*最后一期*/
						/*如果：最后一期还款间隔>0，赋值：还款周期间隔=最后一期还款间隔*/
						if(lastPeriodNumDays > 0) {
							repayTermCount = new BigDecimal(lastPeriodNumDays);
						}
						/*（平账处理）应还本金=借贷总金额-前N期应还本金总和*/
						repay.setPrincipal(amount.subtract(principal_total));
						/*（平账处理）应还利息=借贷总利息-前N期应还利息总和*/
						repay.setInterest(totalInterest.subtract(interest_total));
						repay.setSurplusPrincipal(BigDecimal.ZERO);
						repay.setSurplusInterest(BigDecimal.ZERO);
					} else {
						repay.setPrincipal(principal_period);
						principal_total = principal_total.add(repay.getPrincipal());
						repay.setInterest(amount.multiply(repayTermCount).multiply(day_interestRate).setScale(2, ROUND));
						interest_total = interest_total.add(repay.getInterest());
						repay.setSurplusPrincipal(amount.subtract(principal_total));
						repay.setSurplusInterest(totalInterest.subtract(interest_total));
					}
					
					if(baseTime!=null){
						baseCalendar.add(Calendar.DAY_OF_MONTH, repayTermCount.intValue());
						repay.setRepayTime(baseCalendar.getTime());
					}else{
						nextRepayDate.add(Calendar.DAY_OF_MONTH, repayTermCount.intValue());
						repay.setRepayTime(nextRepayDate.getTime());
					}

					repay.setTotalPrincipal(amount);
					repay.setTotalInterest(totalInterest);
					repaymentList.add(repay);
				}
			}
			return repaymentList;
		} else if(TERM_UNIT_MONTH.equals(termUnit)) {/*借款期限单位为月*/
			List<RespRepaymentModel> repaymentList = new LinkedList<RespRepaymentModel>();
			/*月利率*/
			BigDecimal month_interestRate = interestRate.divide(MONTHS_ONE_YEAR, 18, ROUND);
			/*总期数*/
			Integer totalPeriodNum = 0;
			/*最后一期间隔月数*/
			Integer lastPeriodNumMonths = 0;
			
			if(REPAY_TERM_UNIT_SEASON.equals(repayTermUnit)) {
				/*还款期限单位为季，换算成月数*/
				repayTermCount = repayTermCount.multiply(MONTHS_ONE_SEASON);
			} else if(REPAY_TERM_UNIT_YEAR.equals(repayTermUnit)) {
				/*还款期限单位为年，换算成月数*/
				repayTermCount = repayTermCount.multiply(MONTHS_ONE_YEAR);
			} else if(REPAY_TERM_UNIT_DAY.equals(repayTermUnit)) {
				return null;
			}
			
			lastPeriodNumMonths = termCount.remainder(repayTermCount).intValue();
			/*
			 * 1.总期数=借款周期/还款周期
			 * 2.如果：借款周期%还款周期>0，则：总期数+1 
			 */
			totalPeriodNum = termCount.divideToIntegralValue(repayTermCount).intValue();
			totalPeriodNum = lastPeriodNumMonths > 0 ? ++totalPeriodNum : totalPeriodNum;
			
			Calendar nextRepayDate = Calendar.getInstance();
			nextRepayDate.set(Calendar.HOUR_OF_DAY, 23);
			nextRepayDate.set(Calendar.MINUTE, 59);
			nextRepayDate.set(Calendar.SECOND, 59);
			nextRepayDate.set(Calendar.MILLISECOND, 0);
//			Date releaseDate = nextRepayDate.getTime();
			
			/*总利息*/
			BigDecimal totalInterest = amount.multiply(termCount).multiply(month_interestRate).setScale(2, ROUND);
			
			if(totalPeriodNum == 1) {/*总还款期数只有一期*/
				RespRepaymentModel repay = new RespRepaymentModel();
				repay.setPeriodNum(totalPeriodNum);
				repay.setTotalPeriodNum(totalPeriodNum);
				repay.setPrincipal(amount);
				repay.setInterest(totalInterest);
				
				if(baseTime!=null){
					baseCalendar.add(Calendar.MONTH, termCount.intValue());
					repay.setRepayTime(baseCalendar.getTime());
				}else{
					nextRepayDate.add(Calendar.MONTH, termCount.intValue());
					repay.setRepayTime(nextRepayDate.getTime());
				}

				repay.setSurplusPrincipal(BigDecimal.ZERO);
				repay.setSurplusInterest(BigDecimal.ZERO);
				repay.setTotalPrincipal(amount);
				repay.setTotalInterest(totalInterest);
				repaymentList.add(repay);
			} else {/*总还款期数多于一期*/
				/*每一期应还本金，不包括最后一期*/
				BigDecimal principal_period = amount.divide(termCount, 18, ROUND).multiply(repayTermCount).setScale(2, ROUND);
				/*前N期应还本金总和*/
				BigDecimal principal_total = BigDecimal.ZERO;
				/*前N期应还利息总和*/
				BigDecimal interest_total = BigDecimal.ZERO;
				for(int periodNum = 1; periodNum <= totalPeriodNum; periodNum++) {
					RespRepaymentModel repay = new RespRepaymentModel();
					repay.setTotalPeriodNum(totalPeriodNum);
					repay.setPeriodNum(periodNum);
					if(periodNum == totalPeriodNum) {/*最后一期*/
						/*如果：最后一期还款间隔>0，赋值：还款周期间隔=最后一期还款间隔*/
						if(lastPeriodNumMonths > 0) {
							repayTermCount = new BigDecimal(lastPeriodNumMonths);
						}
						/*（平账处理）应还本金=借贷总金额-前N期应还本金总和*/
						repay.setPrincipal(amount.subtract(principal_total));
						/*（平账处理）应还利息=借贷总利息-前N期应还利息总和*/
						repay.setInterest(totalInterest.subtract(interest_total));
						repay.setSurplusPrincipal(BigDecimal.ZERO);
						repay.setSurplusInterest(BigDecimal.ZERO);
					} else {
						repay.setPrincipal(principal_period);
						principal_total = principal_total.add(repay.getPrincipal());
						repay.setInterest(amount.multiply(repayTermCount).multiply(month_interestRate).setScale(2, ROUND));
						interest_total = interest_total.add(repay.getInterest());
						repay.setSurplusPrincipal(amount.subtract(principal_total));
						repay.setSurplusInterest(totalInterest.subtract(interest_total));
					}
//					nextRepayDate.setTime(releaseDate);
					
					if(baseTime!=null){
						baseCalendar.setTime(baseTime);
						baseCalendar.add(Calendar.MONTH, repayTermCount.intValue());
						repay.setRepayTime(baseCalendar.getTime());
					}else{
						nextRepayDate.add(Calendar.MONTH, repayTermCount.intValue());
						repay.setRepayTime(nextRepayDate.getTime());
					}

					repay.setTotalPrincipal(amount);
					repay.setTotalInterest(totalInterest);
					repaymentList.add(repay);
				}
			}
			return repaymentList;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		ReqRepaymentModel reqRepaymentModel = new ReqRepaymentModel();
		reqRepaymentModel.setAmount(new BigDecimal(20000));
		reqRepaymentModel.setTermCount(3);
		reqRepaymentModel.setTermUnit(TERM_UNIT_MONTH);
		reqRepaymentModel.setRepayType(SUCH_AS_INTEREST_RATES);
		reqRepaymentModel.setInterestRate(new BigDecimal(0.07));
		reqRepaymentModel.setRepayTermCount(1);
		reqRepaymentModel.setRepayTermUnit(REPAY_TERM_UNIT_MONTH);
		reqRepaymentModel.setDaysOneYear("365");
		reqRepaymentModel.setDaysOneMonth("30");
		reqRepaymentModel.setRound(BigDecimal.ROUND_HALF_EVEN);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			reqRepaymentModel.setBaseTime(sdf.parse("2018-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<RespRepaymentModel> respRepaymentModels = createRepayment(reqRepaymentModel);
		if(respRepaymentModels != null) {
			for(RespRepaymentModel respRepaymentModel : respRepaymentModels) {
				System.out.println("期数：" + respRepaymentModel.getPeriodNum() + "    总期数：" + respRepaymentModel.getTotalPeriodNum() 
						+ "   应还总额：" + respRepaymentModel.getPrincipal().add(respRepaymentModel.getInterest())
						+ "   应还本金：" + respRepaymentModel.getPrincipal() + "    应还利息：" + respRepaymentModel.getInterest() 
						+ "   剩余应还本金：" + respRepaymentModel.getSurplusPrincipal() + "   剩余应还利息：" + respRepaymentModel.getSurplusInterest()
						+ "   还款时间：" + DateUtil.dateTimeFormat(respRepaymentModel.getRepayTime()) + "\n");
			}
		}
	}
}
