package com.iscas.biz.config.log;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/21 9:26
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public enum LogType {
    /**
     * 未知
     * */
    UNKONW("未知", 0),
    TEST("实验", 1),
    TEMPLATE("模板", 2),
    USER("用户", 3),
    AUTH("权限", 4),
    SYSTEM("系统", 5);
    private final String value;
    private final int intVal;
    LogType(String value, int intVal) {

        this.value = value;
        this.intVal = intVal;
    }

    public int getIntVal() {
        return intVal;
    }

    public String getValue() {
        return value;
    }
}
