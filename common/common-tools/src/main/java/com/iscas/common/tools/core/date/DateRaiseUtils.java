package com.iscas.common.tools.core.date;



import com.iscas.common.tools.constant.MonthEnum;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>date操作增强类</p>
 * @author zhuquanwen
 * @version 1.0
 * @since jdk1.8
 */

@SuppressWarnings("unused")
public class DateRaiseUtils {

    private DateRaiseUtils(){}


    /**
     * 将格林威治时间格式的字符串转为时间毫秒数
     * 时间字符串例如：Wed, 25 Aug 2021 02:46:52 GMT
     * */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static long convertGMTToMs(String gmtStr) {
        ZonedDateTime zdt = ZonedDateTime.parse(gmtStr, DateTimeFormatter.RFC_1123_DATE_TIME);
        return zdt.toInstant().toEpochMilli();
    }


    /**
     * 获取当前日期里的年份
     * @since jdk1.8
     * @param date 日期
     * @return int 年份
     * @see DateSafeUtils
     */
    public static int getYear(Date date){
        assert date != null;
        String  x = DateSafeUtils.format(date, "yyyy");
        return Integer.parseInt(x);
    }

    /**
     * 获取当前日期里的月份
     * @since jdk1.8
     * @param date 日期
     * @return int 月份
     * @see DateSafeUtils
     */
    public static int getMonth(Date date){
        assert date != null;
        String month = DateSafeUtils.format(date, "MM");
        return Integer.parseInt(month);
    }

    /**
     * 获取当前日期里的天
     * @since jdk1.8
     * @param date 日期
     * @return int 天
     * @see DateSafeUtils
     */
    public static int getDay(Date date){
        assert date != null;
        String x = DateSafeUtils.format(date, "dd");
        return Integer.parseInt(x);
    }

    /**
     * 获取当前日期里的小时
     * @since jdk1.8
     * @param date 日期
     * @return int 小时
     * @see DateSafeUtils
     */
    public static int getHour(Date date){
        assert date != null;
        String x = DateSafeUtils.format(date, "HH");
        return Integer.parseInt(x);
    }
    /**
     * 获取当前日期里的分钟
     * @since jdk1.8
     * @param date 日期
     * @return int 分钟
     * @see DateSafeUtils
     */
    public static int getMinute(Date date){
        assert date != null;
        String x = DateSafeUtils.format(date, "HH");
        return Integer.parseInt(x);
    }

    /**
     * 获取当前日期里的秒数
     * @since jdk1.8
     * @param date 日期
     * @return int 秒数
     * @see DateSafeUtils
     */
    public static int getSecond(Date date){
        assert date != null;
        String x = DateSafeUtils.format(date, "ss");
        return Integer.parseInt(x);
    }

    /**
     * 判断当前月份是否是季度末
     * @since jdk1.8
     * @param date 时间
     * @return boolean
     * @see #getMonth(Date)
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static boolean isSeason(Date date){
        assert date != null;
        boolean sign = false;
        int month = getMonth(date);
        if (month == MonthEnum.MAR.getValue()) {
            sign = true;
        }
        if (month == MonthEnum.JULY.getValue()) {
            sign = true;
        }
        if (month == MonthEnum.SEPT.getValue()) {
            sign = true;
        }
        if (month == MonthEnum.DEC.getValue()) {
            sign = true;
        }
        return sign;
    }
    /**
     * 计算从现在开始偏移毫秒数后的时间,支持负数
     * @since jdk1.8
     * @param offset 偏移的时候毫秒数
     * @return java.util.Date
     * @see #afterOffsetDate(Date, long)
     */
    public static Date afterOffsetDate(long offset){
        return afterOffsetDate(new Date(), offset);
    }

    /**
     * 计算从某个时间偏移毫秒数后的时间,支持负数
     * @since jdk1.8
     * @param offset 偏移的时候毫秒数
     * @param date 日期时间
     * @return java.util.Date
     */
    public static Date afterOffsetDate(Date date, long offset){
        assert date != null;
        long time = date.getTime();
        time = time + offset;
        return new Date(time);
    }

    /**
     * 时间偏移一定毫秒数
     * {@link #afterOffsetDate(Date, long)}
     * */
    @Deprecated
    public static Date timeOffset(Date time, long offset) {
        time = new Date(time.getTime() + offset);
        return time;
    }

    public static LocalDateTime convert2LocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant1 = date.toInstant();
        return instant1.atZone(zoneId).toLocalDateTime();
    }
}
