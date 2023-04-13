package com.iscas.biz.mp.enhancer.enums;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/23 14:09
 * @since jdk1.8
 */
public enum CustomSqlMethod {

    /**
     * truncate
     * */
    TRUNCATE("truncate", "truncate一张表", "<script>\nTRUNCATE TABLE %s \n</script>"),

    /**
     * 流式读取数据
     * */
    FETCH_BY_STREAM("fetchByStream", "流式读取数据", "<script>\nSELECT %s FROM %s %s %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;

    CustomSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
