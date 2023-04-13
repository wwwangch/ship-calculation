package com.iscas.common.prometheus.tools.constant;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:12
 */
public interface ApiPaths {
    /**热加载prometheus*/
    String RELOAD = "-/reload";

    /**
     * 查询prometheus的指标
     * */
    String GET_QUERY = "api/v1/query";

    /**
     * 查询prometheus的指标
     * */
    String QUERY_RANGE = "api/v1/query_range";

    /**
     * 查询标签
     * */
    String SERIES = "api/v1/series";

    /**
     * 查询标签key
     * */
    String GET_LABEL = "api/v1/labels";

    /**
     * 按照标签查询标签值
     * */
    String GET_LABEL_VALUES = "api/v1/label/%s/values";

    /**
     * 获取目标端
     * */
    String GET_TARGET = "api/v1/targets";

    /**
     * 获取告警规则
     * */
    String GET_RULE = "api/v1/rules";

    /**
     * 获取活动的告警
     * */
    String GET_ACTIVE_ALERT = "api/v1/alerts";

    /**
     * 获取告警信息
     * */
    String GET_ALERT_INFO = "api/v2/alerts";

    /**
     * 修改告警规则
     * */
    String UPDATE_RULE = "api/v1/rules";

}
