package com.iscas.biz.config.log;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/20 18:13
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaEnumConstantsMustHaveComment", "unused"})
public enum OperateType {
    add("添加", 0),
    delete("删除", 1),
    select("查询", 2),
    update("修改", 3),
    stop("停止", 4),
    restart("重启", 5),
    check("检查", 6),
    download("下载", 7),
    upload("上传", 8),
    login("登录", 9),
    logout("退出", 10),
    other("其他", 11);
    private final String value;
    private final int intVal;

    OperateType(String value, int intVal) {
        this.value = value;
        this.intVal = intVal;
    }

    public static String getNameByIntVal(int intVal) {
        for (OperateType operateType : values()) {
            if (operateType.intVal == intVal) {
                return operateType.name();
            }
        }
        return other.name();
    }

    public String getValue() {
        return value;
    }

    public int getIntVal() {
        return intVal;
    }
}
