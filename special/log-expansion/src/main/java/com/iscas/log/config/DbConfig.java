package com.iscas.log.config;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/2/28 21:35
 * @since jdk1.8
 */
public class DbConfig {

    /**
     * 数据库类型
     * */
    private DBTypeEnum dbType;
    private String col1;
    private String col2;

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public static enum DBTypeEnum {
        MYSQL, ES, MONGO, DATASONG;
    }
}
