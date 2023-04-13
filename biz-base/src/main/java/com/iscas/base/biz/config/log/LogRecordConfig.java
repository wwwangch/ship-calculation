package com.iscas.base.biz.config.log;

import org.springframework.context.annotation.Lazy;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/8/30 18:06
 * @since jdk1.8
 */
@Lazy(value = false)
public class LogRecordConfig {
    public static boolean flag = false;
    public LogRecordConfig(){
        flag = true;
    }
}
