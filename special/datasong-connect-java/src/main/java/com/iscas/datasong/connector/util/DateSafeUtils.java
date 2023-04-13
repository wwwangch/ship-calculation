package com.iscas.datasong.connector.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * <p>线程安全的时间转化工具类</p>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 **/
@SuppressWarnings({"unused", "JavadocDeclaration"})
public class DateSafeUtils {
    private DateSafeUtils() {
    }

    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 锁对象
     */
    private static final Object LOCK_OBJ = new Object();
    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static final Map<String, ThreadLocal<SimpleDateFormat>> SDF_MAP = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern PATTERN
     * @return simpleDataFormat
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = SDF_MAP.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (LOCK_OBJ) {
                tl = SDF_MAP.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                    SDF_MAP.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }

    private static SimpleDateFormat getSdf(final String pattern, final TimeZone timeZone) {
        String key = pattern + "_" + timeZone.getRawOffset();
         ThreadLocal<SimpleDateFormat> tl = SDF_MAP.get(key);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (LOCK_OBJ) {
                tl = SDF_MAP.get(key);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                    SDF_MAP.put(key, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     *
     * @param date    {@link Date} 时间
     * @param pattern 格式化串
     * @return String 时间字符串格式
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     *
     * @param date     {@link Date} 时间
     * @param pattern  格式化串
     * @param timeZone 时区
     * @return String 时间字符串格式
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
        return getSdf(pattern, timeZone).format(date);
    }

    /**
     * 使用默认的格式化方式yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date) {
        return format(date, PATTERN);
    }

    /**
     * 使用默认的格式化方式yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, TimeZone timeZone) {
        return format(date, PATTERN, timeZone);
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     *
     * @param dateStr 字符串
     * @param pattern 时间字符串格式
     * @return Date {@link Date} 时间
     * @throws ParseException 时间转换错误
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     *
     * @param dateStr  字符串
     * @param pattern  时间字符串格式
     * @param timeZone 时区
     * @return Date {@link Date} 时间
     * @throws ParseException 时间转换错误
     */
    public static Date parse(String dateStr, String pattern, TimeZone timeZone) throws ParseException {
        return getSdf(pattern, timeZone).parse(dateStr);
    }

    /**
     * 使用默认的格式化方式yyyy-MM-dd HH:mm:ss
     */
    public static Date parse(String dateStr) throws ParseException {
        return parse(dateStr, PATTERN);
    }

    /**
     * 使用默认的格式化方式yyyy-MM-dd HH:mm:ss
     */
    public static Date parse(String dateStr, TimeZone timeZone) throws ParseException {
        return parse(dateStr, PATTERN, timeZone);
    }

    /**
     * 任意时间字符串转换成时间，无需指定解析模板
     */
    public static Date parseStringToDate(String dateStr) throws ParseException {
        String parse = dateStr.replaceFirst("\\d{4}(\\D?)", "yyyy$1");
        parse = parse.replaceFirst("^\\d{2}(\\D?)", "yy$1");
        parse = parse.replaceFirst("(\\D?)\\d{1,2}(\\D?)", "$1MM$2");
        parse = parse.replaceFirst("(\\D?)\\d{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )\\d{1,2}(\\D?)", "$1HH$2");
        parse = parse.replaceFirst("(\\D?)\\d{1,2}(\\D?)", "$1mm$2");
        parse = parse.replaceFirst("(\\D?)\\d{1,2}(\\D?)", "$1ss$2");
        return DateSafeUtils.parse(dateStr, parse);
    }

}
