package com.iscas.ssh.server.constant;

/**
 *
 * 公用常量
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/27 14:05
 * @since jdk1.8
 */
public interface CommonConstants {
    /**
     * 随机生成uuid的key名
     */
    String USER_UUID_KEY = "user_uuid";
    /**
     * 发送指令：连接
     */
    String WEBSSH_OPERATE_CONNECT = "connect";
    /**
     * 发送指令：命令
     */
    String WEBSSH_OPERATE_COMMAND = "command";
}
