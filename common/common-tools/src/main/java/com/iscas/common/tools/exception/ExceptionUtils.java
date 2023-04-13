package com.iscas.common.tools.exception;


import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理工具函数
 *
 * @author LJian
 * @date 2017/4/14
 */
public class ExceptionUtils {
    /**
     * 获取异常信息
     *
     * @param e 异常
     * @return java.lang.String
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static String getExceptionInfo(Throwable e) {
        try {
            String info = e.getMessage();
            if (StringUtils.isEmpty(info)) {
                @Cleanup StringWriter sw = new StringWriter();
                @Cleanup PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                info = sw.toString();

            }
            return info.length() > 300 ? info.substring(0, 300) + "..." : info;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return "analyze Exception error";
    }

    /**
     * add by zqw 20210813
     */
    public static String getExceptionStackTrace(Throwable e, int maxSize) {
        try {

            @Cleanup StringWriter sw = new StringWriter();
            @Cleanup PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            return stackTrace.length() > maxSize ? stackTrace.substring(0, maxSize) + "..." : stackTrace;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return "analyze Exception error";
    }
}
