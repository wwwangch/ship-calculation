package com.iscas.common.tools.hutool.core;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/9 10:43
 * @since jdk1.8
 */
public class DateTest {
    /**
     * 当前时间获取测试
     * */
    @Test
    public void test1(){
        String now = DateUtil.now();
        System.out.println(now); // yyyy-MM-dd HH:mm:ss

        Date date = DateUtil.date();
        System.out.println(date);// yyyy-MM-dd HH:mm:ss

        Date date2 = DateUtil.date(Calendar.getInstance());
        System.out.println(date2);// yyyy-MM-dd HH:mm:ss

        Date date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date3);// yyyy-MM-dd HH:mm:ss

        String today = DateUtil.today();
        System.out.println(today);// yyyy-MM-dd
    }

    /**
     * 线程安全的时间转化
     * */
    @Test
    public void test2(){
        String dateStr = "2018-05-04 12:12:11";
        Date date = DateUtil.date();
        String dateStr1 = DateUtil.formatDateTime(date);
        System.out.println(dateStr1);

        String dateStr2 = DateUtil.formatDate(date);
        System.out.println(dateStr2);

        String dateStr3 = DateUtil.formatTime(date);
        System.out.println(dateStr3);

        Date date2 = DateUtil.parse(dateStr);
        System.out.println(date2);

    }
}
