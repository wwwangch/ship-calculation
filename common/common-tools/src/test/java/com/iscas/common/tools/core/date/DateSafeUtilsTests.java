package com.iscas.common.tools.core.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;


/**
 * 线程安全的时间转化工具类测试
 * @author zhuquanwen
 * @date: 2018/7/12
 **/
public class DateSafeUtilsTests {
    private static final String Pattern = "yyyy-MM-dd HH:mm:ss";
    /**
     * <p>时间类型转为字符串<p/>
     * @version 1.0
     * @since jdk1.8
     * @date 2018/7/16
     * @return void
     */
    @Test
    public void format(){
        System.out.println("-------DateSafeUtils#format(Date date, String pattern) begin---------");
        Date date = new Date();
        String str = DateSafeUtils.format(date, Pattern);
        Assertions.assertNotNull(str);
        System.out.println("-------DateSafeUtils#format(Date date, String pattern) end---------");
    }
    /**
     * <p>字符串类型转为时间<p/>
     * @version 1.0
     * @since jdk1.8
     * @date 2018/7/16
     * @return void
     */
    @Test
    public void parse() throws ParseException {
        System.out.println("-------DateSafeUtils#parse(String dateStr, String pattern) begin---------");
        String dateStr = "2018-12-12 23:12:45";
        Date date = DateSafeUtils.parse(dateStr, Pattern);
        Assertions.assertNotNull(date);
        System.out.println("-------DateSafeUtils#parse(String dateStr, String pattern) end---------");
    }
}
