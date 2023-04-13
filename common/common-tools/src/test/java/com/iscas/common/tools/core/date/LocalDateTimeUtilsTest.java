package com.iscas.common.tools.core.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/13 16:54
 */
class LocalDateTimeUtilsTest {

    /**
     * 测试字符串转为LocalDateTime
     * */
    @Test
    void parseLocalDateTime() {
        String dateTimeStr = "2021-10-11 12:11:12";
        LocalDateTime localDateTime = LocalDateTimeUtils.parseLocalDateTime(dateTimeStr);
        System.out.println(localDateTime);
        Assertions.assertNotNull(localDateTime);
    }

    /**
     * 测试字符串转为LocalDateTime，自定义格式化的串
     * */
    @Test
    void testParseLocalDateTime() {
        String dateTimeStr = "2021-10-11 12";
        LocalDateTime localDateTime = LocalDateTimeUtils.parseLocalDateTime(dateTimeStr, "yyyy-MM-dd HH");
        System.out.println(localDateTime);
        Assertions.assertNotNull(localDateTime);
    }

    /**
     * 测试字符串转为LocalDateTime
     * */
    @Test
    void testParseLocalDateTime1() {
        String dateTimeStr = "2021-10-11 12";
        LocalDateTime localDateTime = LocalDateTimeUtils.parseLocalDateTime(dateTimeStr, "yyyy-MM-dd HH", Locale.getDefault());
        System.out.println(localDateTime);
        Assertions.assertNotNull(localDateTime);

        LocalDateTime localDateTime2 = LocalDateTimeUtils.parseLocalDateTime(dateTimeStr, "yyyy-MM-dd HH", Locale.US);
        System.out.println(localDateTime2);
        Assertions.assertNotNull(localDateTime2);

    }

    /**
     * 测试字符串转为LocalDate
     * */
    @Test
    void parseLocalDate() {
        String dateStr = "2021-10-11";
        LocalDate localDate = LocalDateTimeUtils.parseLocalDate(dateStr);
        System.out.println(localDate);
        Assertions.assertNotNull(localDate);
    }

    /**
     * 测试字符串转为LocalDate，自定义格式化的串
     * */
    @Test
    void testParseLocalDate() {
        String dateStr = "2021-10.11";
        LocalDate localDate = LocalDateTimeUtils.parseLocalDate(dateStr, "yyyy-MM.dd");
        System.out.println(localDate);
        Assertions.assertNotNull(localDate);
    }

    /**
     * 测试字符串转为LocalDate
     * */
    @Test
    void testParseLocalDate1() {
        String dateStr = "2021-10.11";
        LocalDate localDate = LocalDateTimeUtils.parseLocalDate(dateStr, "yyyy-MM.dd", Locale.getDefault());
        System.out.println(localDate);
        Assertions.assertNotNull(localDate);

        LocalDate localDate2 = LocalDateTimeUtils.parseLocalDate(dateStr, "yyyy-MM.dd", Locale.US);
        System.out.println(localDate2);
        Assertions.assertNotNull(localDate2);

    }

    @Test
    void acquireMinutesBetween() {
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2012, 12, 12), LocalTime.of(12, 14));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2012, 12, 12), LocalTime.of(12, 18));
        long time = LocalDateTimeUtils.acquireMinutesBetween(start, end);
        Assertions.assertEquals(time, 4);
    }

    @Test
    void acquireMillisBetween() {
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2012, 12, 12), LocalTime.of(12, 14));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2012, 12, 12), LocalTime.of(12, 18));
        long time = LocalDateTimeUtils.acquireMillisBetween(start, end);
        Assertions.assertEquals(time, 240000L);
    }

    /**
     * 测试时间毫秒数转为LocalDateTime
     * */
    @Test
    void testParseLocalDateTime2() {
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)) * 1000L;
        LocalDateTime localDateTime = LocalDateTimeUtils.parseLocalDateTime(l);
        System.out.println(localDateTime);
        Assertions.assertNotNull(localDateTime);
    }

    /**
     * 测试时间毫秒数转为LocalDateTime
     * */
    @Test
    void testParseLocalDateTime3() {
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)) * 1000L;
        LocalDateTime localDateTime = LocalDateTimeUtils.parseLocalDateTime(l, ZoneOffset.ofHours(3));
        System.out.println(localDateTime);
        Assertions.assertNotNull(localDateTime);
    }

    /**
     * 测试时间毫秒数转为LocalDate
     * */
    @Test
    void testParseLocalDate2() {
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)) * 1000L;
        LocalDate localDate = LocalDateTimeUtils.parseLocalDate(l);
        System.out.println(localDate);
        Assertions.assertNotNull(localDate);
    }

    /**
     * 测试时间毫秒数转为LocalDate
     * */
    @Test
    void testParseLocalDate3() {
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)) * 1000L;
        LocalDate localDate = LocalDateTimeUtils.parseLocalDate(l, ZoneOffset.ofHours(3));
        System.out.println(localDate);
        Assertions.assertNotNull(localDate);
    }

    @Test
    void format1() {
        LocalDateTime now = LocalDateTime.now();
        String format = LocalDateTimeUtils.format(now);
        System.out.println(format);
    }

    @Test
    void format2() {
        LocalDateTime now = LocalDateTime.now();
        String format = LocalDateTimeUtils.format(now, "yyyy-MM-dd HH:mm");
        System.out.println(format);
    }

    @Test
    void format3() {
        LocalDateTime now = LocalDateTime.now();
        String format = LocalDateTimeUtils.format(now, "yyyy-MM-dd HH:mm", Locale.getDefault());
        System.out.println(format);
    }

    @Test
    void format4() {
        LocalDate now = LocalDate.now();
        String format = LocalDateTimeUtils.format(now);
        System.out.println(format);
    }

    @Test
    void format5() {
        LocalDate now = LocalDate.now();
        String format = LocalDateTimeUtils.format(now, "yyyy-MM-dd");
        System.out.println(format);
    }

    @Test
    void format6() {
        LocalDate now = LocalDate.now();
        String format = LocalDateTimeUtils.format(now, "yyyy-MM-dd", Locale.getDefault());
        System.out.println(format);
    }

    @Test
    void toEpochMilli() {
        System.out.println(LocalDateTimeUtils.toEpochMilli(LocalDateTime.now()));
    }

    @Test
    void testToEpochMilli() {
        System.out.println(LocalDateTimeUtils.toEpochMilli(LocalDate.now()));
    }

    @Test
    void dateToLocalDateTime() {
        System.out.println(LocalDateTimeUtils.dateToLocalDateTime(new Date()));
    }

    @Test
    void dateToLocalDateTime2() {
        System.out.println(LocalDateTimeUtils.dateToLocalDateTime(new Date(), ZoneId.of("GMT")));
    }

    @Test
    void dateToLocalDate() {
        System.out.println(LocalDateTimeUtils.dateToLocalDate(new Date()));
    }

    @Test
    void dateToLocalDate2() {
        System.out.println(LocalDateTimeUtils.dateToLocalDate(new Date(), ZoneId.of("GMT")));
    }

    @Test
    void localDateTimeToDate() {
        System.out.println(LocalDateTimeUtils.localDateTimeToDate(LocalDateTime.now()));
    }

    @Test
    void localDateTimeToDate2() {
        System.out.println(LocalDateTimeUtils.localDateTimeToDate(LocalDateTime.now(), ZoneId.of("GMT")));
    }


    @Test
    void localDateToDate() {
        System.out.println(LocalDateTimeUtils.localDateToDate(LocalDate.now()));
    }

    @Test
    void localDateToDate2() {
        System.out.println(LocalDateTimeUtils.localDateToDate(LocalDate.now(), ZoneId.of("GMT")));
    }




}