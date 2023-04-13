package com.iscas.common.harbor.tools.client;

import lombok.Data;

/**
 * 自动配置属性
 **/

@Data
public class OkHttpProps {
    private int readTimeout = 10000; //读取超时时间毫秒
    private int writeTimeout = 10000 ; //写数据超时时间毫秒
    private int connectTimeout = 2000; //连接超时时间毫秒
    private int maxIdleConnection = 15 ; //最大空闲数目
    private long keepAliveDuration = 5 ; //keep alive保持时间 分钟


}
