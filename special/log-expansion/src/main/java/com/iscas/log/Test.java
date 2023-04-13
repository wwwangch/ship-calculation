package com.iscas.log;

//import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.spi.JoranException;
import com.iscas.logback.classic.param.LogParam;
import com.iscas.logback.classic.param.LogParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/1 21:02
 * @since jdk1.8
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        aaa("lalalalalla", "wergweg");
    }
    public static void aaa(String str, String aaa){
        LogParams logParams = new LogParams();
        List<LogParam> logParamList = new ArrayList<>();
        LogParam logParam1 = new LogParam();
        logParam1.setType(String.class);
        logParam1.setDesc("应用名");

        LogParam logParam2 = new LogParam();
        logParam2.setType(String.class);
        logParam2.setDesc("模块名");
        try {
            InitConfig.loadFromResource();
            IOException exception = new IOException("测试异常");
//            logger.info("模块x", "这是一个日志测试");
//            logger.info("模块y", "这是一个日志测试，{}","第二个");
//            logger.info("模块z", "这是一个日志测试", exception);

//            logger.debug("模块x2", "这是一个日志测试");
//            logger.debug("模块y2", "这是一个日志测试，{}","第二个");
//            logger.debug("模块z2", "这是一个日志测试", exception);

//            logger.warn("模块x3", "这是一个日志测试");
//            logger.warn("模块y3", "这是一个日志测试，{}","第二个");
//            logger.warn("模块z3", "这是一个日志测试", exception);

            logger.error("模块x3", "这是一个日志测试");
            logger.error("模块y3", "这是一个日志测试，{}","第二个");
            logger.error("模块z3", "这是一个日志测试", exception);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JoranException e) {
//            e.printStackTrace();
        }
    }
}
