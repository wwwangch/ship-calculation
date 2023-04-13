package com.iscas.common.tools.core.date;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

/**
 *<p>日期增强工具类测试</p>
 * @author zhuquanwen
 * @date 2018/7/16
 * @modified
 **/
public class DateRaiseUtilsTests {

    /**
     * 获取年份
     * */
    @Test
    public void getYear(){
        System.out.println("------- DateRaiseUtils#getYear(date) begin---------");
        Date date = new Date();
        Integer x = DateRaiseUtils.getYear(date);
        System.out.println(x);
        Assertions.assertNotNull(x);
        System.out.println("------- DateRaiseUtils#getYear(date) end---------");
    }

    /**
     * 获取月份
     * */
    @Test
    public void getMonth(){
        System.out.println("------- DateRaiseUtils#getMonth(date) begin---------");
        Date date = new Date();
        Integer month = DateRaiseUtils.getMonth(date);
        System.out.println(month);
        Assertions.assertNotNull(month);
        System.out.println("------- DateRaiseUtils#getMonth(date) end---------");
    }

    /**
     * 获取天
     * */
    @Test
    public void getDay(){
        System.out.println("------- DateRaiseUtils#getDay(date) begin---------");
        Date date = new Date();
        Integer x = DateRaiseUtils.getDay(date);
        System.out.println(x);
        Assertions.assertNotNull(x);
        System.out.println("------- DateRaiseUtils#getDay(date) end---------");
    }
    /**
     * 获取小时
     * */
    @Test
    public void getHour(){
        System.out.println("------- DateRaiseUtils#getHour(date) begin---------");
        Date date = new Date();
        Integer x = DateRaiseUtils.getHour(date);
        System.out.println(x);
        Assertions.assertNotNull(x);
        System.out.println("------- DateRaiseUtils#getHour(date) end---------");
    }
    /**
     * 获取分钟
     * */
    @Test
    public void getMinute(){
        System.out.println("-------DateRaiseUtils#getMinute(date) begin---------");
        Date date = new Date();
        Integer x = DateRaiseUtils.getMinute(date);
        System.out.println(x);
        Assertions.assertNotNull(x);
        System.out.println("-------DateRaiseUtils#getMinute(date) end---------");
    }
    /*
     * 获取秒
     * */
    @Test
    public void getSecond(){
        System.out.println("-------DateRaiseUtils#getSecond(date) begin---------");
        Date date = new Date();
        Integer x = DateRaiseUtils.getSecond(date);
        System.out.println(x);
        Assertions.assertNotNull(x);
        System.out.println("-------DateRaiseUtils#getSecond(date) end---------");
    }

    /**
     * 判断是否这个月份为季度末
     * */
    @Test
    public void isSeason(){
        System.out.println("-------DateRaiseUtils#isSeason(date) begin---------");
        Date date = new Date();
        Boolean flag = DateRaiseUtils.isSeason(date);
        System.out.println(flag);
        System.out.println("-------DateRaiseUtils#isSeason(date) end---------");
    }
    /**
     * 从现在开始算偏移一定毫秒数后的时间
     * */
    @Test
    public void afterOffsetDate(){
        System.out.println("-------DateRaiseUtils#afterOffsetDate(long offset) begin---------");
        long offset = -1 * 24 * 3600 *1000;
        Date date = DateRaiseUtils.afterOffsetDate(offset);
        Assertions.assertNotNull(date);
        System.out.println("-------DateRaiseUtils#afterOffsetDate(long offset) end---------");
    }
    /**
     * 从现在开始算偏移一定毫秒数后的时间
     * */
    @Test
    public void afterOffsetDate2() throws ParseException {
        System.out.println("-------DateRaiseUtils#afterOffsetDate(Date date, long offset) begin---------");
        long offset = 1 * 24 * 3600 *1000;
        Date date = DateSafeUtils.parse("2018-11-11 12:12:12","yyyy-MM-dd HH:mm:ss");
        Date datex = DateRaiseUtils.afterOffsetDate(date, offset);
        Assertions.assertNotNull(datex);
        System.out.println("-------DateRaiseUtils#afterOffsetDate(Date date, long offset) end---------");
    }


    @Test
    public void beforeOffset() {
        System.out.println("-------DateRaiseUtils将时间偏移之前一段时间 begin---------");
        long time = -(30L*24*3600*1000);
        System.out.println(time);
        Date date = DateRaiseUtils.afterOffsetDate(time);
        System.out.println(DateSafeUtils.format(date, DateSafeUtils.PATTERN));
        System.out.println("-------DateRaiseUtils将时间偏移之前一段时间 end---------");
    }
}
