package com.company.platform.base.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String DATE = "yyyy-MM-dd";

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 自定义转让格式
     * 
     * @param d
     *            日期
     * @param format
     *            格式
     * @return
     */
    public static String format(Date d, String format) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(d);
        } else {
            return "";
        }
    }

    /**
     * 转换时间格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @serialData 2016-4-7
     * @param d
     *            被转换时间对象
     * @return 转换后文本
     */
    public static String dateTimeFormat(Date d) {
        return format(d, DATE_TIME);
    }

    /**
     * 转换时间格式为 yyyy-MM-dd
     * 
     * @serialData 2016-4-7
     * @param d
     *            被转换时间对象
     * @return 转换后文本
     */
    public static String dateFormat(Date d) {
        return format(d, DATE);
    }

    public static Date parseDate(String time, String format) {
        try {
            if (time == null || "".equals(time)) {
                return new Date();
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format == null || "".equals(format) ? DATE_TIME : format);
            return sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得当前的日期毫秒
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前的时间戳
     */
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(getCurrentTimeMillis());
    }

    /**
     * 获取当前日期，格式yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return format(new Date(), DateUtil.DATE);
    }

    /**
     * 传入日期
     * 
     * @param date
     * @return
     */
    public static String getCurrentDate(Date date) {
        return format(date, DateUtil.DATE);
    }
    
    /**
     *  当前时间 ，格式yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurrentTime() {
        return format(new Date(), DateUtil.DATE_TIME);
    }
}
