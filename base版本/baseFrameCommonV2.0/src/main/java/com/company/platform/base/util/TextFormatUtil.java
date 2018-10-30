package com.company.platform.base.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TextFormatUtil {
	
	public static final String NUMERAL = ",##0.00";

	public static final String NUMERAL_CAPITAL = "¤,##0.00";
	
	public static final String NUMERAL_PERCENT = "#0.00%";
	
	public static final String DATE = "yyyy-MM-dd";
	
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	//接口传值使用
	public static final String YYYYMMDD = "yyyyMMdd";
	
	/**
	 * 数字格式转换
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param num 被转换数据对象，类型包括 Long、Integer、Short、Byte、AtomicInteger、AtomicLong、BigInteger、BigDecimal、BigInteger、Number、Double、Float
	 * @param format 转换文本格式
	 * @param rm 小数舍入方式
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String numberFormat(Object num, String format, RoundingMode rm) {
		if(num != null) {
			DecimalFormat df = new DecimalFormat(format, DecimalFormatSymbols.getInstance(Locale.CHINA));
			df.setRoundingMode(rm);
			return df.format(num);
		} else {
			return "";
		}
	}
	
	/**
	 * 转换成三位一段格式，例如21,100.00
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换数据对象，类型包括 Long、Integer、Short、Byte、AtomicInteger、AtomicLong、BigInteger、BigDecimal、BigInteger、Number、Double、Float
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String numberFormat(Object d) {
		return numberFormat(d, NUMERAL, RoundingMode.HALF_UP);
	}
	
	/**
	 * 转换成金融货币格式，例如￥21,100.00
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换数据对象，类型包括 Long、Integer、Short、Byte、AtomicInteger、AtomicLong、BigInteger、BigDecimal、BigInteger、Number、Double、Float
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String getCAPITALText(Object d) {
		return numberFormat(d, NUMERAL_CAPITAL, RoundingMode.HALF_UP);
	}
	
	/**
	 * 转换成百分比格式，例如0.23，转换后成23.00%
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换数据对象，类型包括 Long、Integer、Short、Byte、AtomicInteger、AtomicLong、BigInteger、BigDecimal、BigInteger、Number、Double、Float
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String getPERCENTText(Object d) {
		return numberFormat(d, NUMERAL_PERCENT, RoundingMode.HALF_UP);
	}
	
	/**
	 * 转换时间格式
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换时间对象
	 * @param format 时间格式
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String dateFormat(Date d, String format) {
		if(d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			return sdf.format(d);
		} else {
			return "";
		}
	}
	
	/**
	 * 转换时间格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换时间对象
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String timeFormatText(Date d) {
		return dateFormat(d, DATE_TIME);
	}
	
	/**
	 * 转换时间格式为 yyyy-MM-dd
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换时间对象
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String dateFormatText(Date d) {
		return dateFormat(d, DATE);
	}
	
	/**
	 * 转换时间格式为 yyyyMMdd
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param d 被转换时间对象
	 * 
	 * @return 转换后文本
	 * 
	 * */
	public static String ymdFormatText(Date d) {
		return dateFormat(d, YYYYMMDD);
	}
	
	/**
	 * 银行卡显示格式
	 * 
	 * @serialData 2015-7-15
	 * 
	 * @param bankCardStr 原始卡号
	 *  
	 * @return 转换后文本
	 * 
	 * */
	public static String bankCardText(String bankCardStr) {
		if(bankCardStr != null) {
			StringBuffer bcs = new StringBuffer(bankCardStr);
			if(bankCardStr.length() == 16) {
				bcs.insert(12, " ");
				bcs.insert(8, " ");
				bcs.insert(4, " ");
			} else if(bankCardStr.length() == 19) {
				bcs.insert(6, " ");
			}
			return bcs.toString();
		} else {
			return "";
		}
	}
	
	/**
	 * 格式化yyyyMMdd字符串为yyyy-MM-dd
	 * 
	 * @serialData 2015-7-21
	 * 
	 * @param yyyyMMdd 要转换时间文本
	 * @param separator 分隔符
	 *  
	 * @return 转换后文本
	 * 
	 * */
	public static String dateTextformat(String yyyyMMdd, String separator) {
		if (yyyyMMdd != null && yyyyMMdd.length() == YYYYMMDD.length()) {
			if (separator == null) {
				separator = "-";
			}
			String pattern = "{0}" + separator + "{1}" + separator + "{2}";
			return MessageFormat.format(pattern, yyyyMMdd.substring(0, 4),
					yyyyMMdd.substring(4, 6), yyyyMMdd.substring(6, 8));
		} else {
			return yyyyMMdd;
		}
	}
}

