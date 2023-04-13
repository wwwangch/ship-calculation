package com.iscas.common.prometheus.tools.service;

import com.iscas.common.prometheus.tools.CustomHttpClient;
import com.iscas.common.prometheus.tools.exception.PrometheusException;
import com.iscas.common.prometheus.tools.model.Annotation;
import com.iscas.common.prometheus.tools.model.ConnectionCfg;
import com.iscas.common.prometheus.tools.model.Label;
import com.iscas.common.prometheus.tools.model.alert.Alert;
import com.iscas.common.prometheus.tools.model.alert.AlertInfo;
import com.iscas.common.prometheus.tools.model.query.Metric;
import com.iscas.common.prometheus.tools.model.query.QueryResponse;
import com.iscas.common.prometheus.tools.model.query.Result;
import com.iscas.common.prometheus.tools.model.rule.Group;
import com.iscas.common.prometheus.tools.model.rule.Rule;
import com.iscas.common.prometheus.tools.model.target.TargetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:09
 */
public class PrometheusServiceTest {
    private PrometheusService prometheusService;

    @BeforeEach
    public void before() {
        prometheusService = new PrometheusService(new ConnectionCfg(), new CustomHttpClient(new CustomHttpClient.HttpClientProps()));
    }

    /**
     * 测试重新加载prometheus
     */
    @Test
    public void reload() throws PrometheusException {
        prometheusService.reload();
    }

    /**
     * 测试查询指标
     */
    @Test
    public void query1() throws PrometheusException {
        QueryResponse<? extends Result> query = prometheusService.query("station_system_memory_used_bytes[1m]");
        System.out.println(query);
    }

    /**
     * 测试查询指标
     */
    @Test
    public void query2() throws PrometheusException {
        QueryResponse<? extends Result> query = prometheusService.query("station_system_memory_used_bytes");
        System.out.println(query);
    }

    /**
     * 测试查询指标
     */
    @Test
    public void query3() throws PrometheusException {
        QueryResponse<? extends Result> query = prometheusService.queryRange("station_system_memory_used_bytes", null, null, 10);
        System.out.println(query);
    }

    /**
     * 查询标签
     */
    @Test
    public void series() throws PrometheusException {
        List<List<Metric>> series = prometheusService.series("station_system_disk_read_speed{host_address=\"192.168.100.22\"}");
        System.out.println(series);
    }

    /**
     * 查询标签KEY
     */
    @Test
    public void getLabels() throws PrometheusException {
        List<String> labels = prometheusService.getLabels();
        System.out.println(labels);
    }

    /**
     * 查询标签值
     */
    @Test
    public void getLabelValue() throws PrometheusException {
        List<String> values = prometheusService.getLabelValue("host_address");
        System.out.println(values);
    }

    /**
     * 查询目标
     */
    @Test
    public void getTarget() throws PrometheusException {
        TargetResponse target = prometheusService.getTarget();
        System.out.println(target);
    }

    /**
     * 获取告警规则
     */
    @Test
    public void getRule() throws PrometheusException {
        List<Group> rule = prometheusService.getRule();
        System.out.println(rule);
    }

    /**
     * 获取活跃的告警
     */
    @Test
    public void getAlerts() throws PrometheusException {
        List<Alert> alerts = prometheusService.getActiveAlert();
        System.out.println(alerts);
    }

    /**
     * 获取告警信息
     */
    @Test
    public void getAlertInfo() throws PrometheusException {
        List<AlertInfo> alerts = prometheusService.getAlertInfo();
        System.out.println(alerts);
    }

//    /**
//     * 新增告警信息
//     */
//    @Test
//    public void createAlert() throws PrometheusException {
//        List<Group> groups = new ArrayList<>();
//        Group group = new Group();
//        group.setName("station-exporter-cpu-alert-test");
//
//        Rule rule = new Rule();
//        rule.setName("station-exporter-cpu-high-alert-test");
//        rule.setQuery("(station_system_cpu_used_C{collector_name=\"station_system_cpu_used\"} / ignoring(collector_name) station_system_cpu_total_C) * 100 > 30");
//        rule.setDuration(10);
//        rule.setLabels(new ArrayList<>(){{
//            add(new Label("severity", "warning"));
//        }});
//        rule.setType("alerting");
//        rule.setAnnotations(new ArrayList<>(){{
//            add(new Annotation("description", "战位: {{ $labels.host_address }},CPU利用率1分钟内大于60%,当前值：{{ $value }}%"));
//            add(new Annotation("instance", "{{ $labels.instance }}"));
//            add(new Annotation("stationName", "{{ $labels.station_name }}"));
//            add(new Annotation("summary", "战位: {{ $labels.host_address }},CPU利用率10秒内大于30%"));
//            add(new Annotation("value", "{{ $value }}"));
//        }});
//        group.setRules(List.of(rule));
//
//        groups.add(group);
//        prometheusService.createRule(groups);
//    }

}
