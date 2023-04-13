package com.iscas.common.prometheus.tools.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.iscas.common.prometheus.tools.CustomHttpClient;
import com.iscas.common.prometheus.tools.constant.ApiPaths;
import com.iscas.common.prometheus.tools.constant.Constants;
import com.iscas.common.prometheus.tools.exception.PrometheusException;
import com.iscas.common.prometheus.tools.model.Annotation;
import com.iscas.common.prometheus.tools.model.ConnectionCfg;
import com.iscas.common.prometheus.tools.model.Label;
import com.iscas.common.prometheus.tools.model.alert.Alert;
import com.iscas.common.prometheus.tools.model.alert.AlertInfo;
import com.iscas.common.prometheus.tools.model.alert.AlertInfoStatus;
import com.iscas.common.prometheus.tools.model.query.*;
import com.iscas.common.prometheus.tools.model.rule.Group;
import com.iscas.common.prometheus.tools.model.rule.Rule;
import com.iscas.common.prometheus.tools.model.target.Target;
import com.iscas.common.prometheus.tools.model.target.TargetResponse;
import com.iscas.common.prometheus.tools.util.JsonUtils;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:06
 */
@RequiredArgsConstructor
public class PrometheusService implements ApiPaths, Constants {
    private final ConnectionCfg connectionCfg;

    private final CustomHttpClient httpClient;

    /**
     * 热加载prometheus
     *
     * @date 2023/3/30
     */
    public void reload() throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + RELOAD;
        HttpResponse<String> response;
        try {
            response = httpClient.doPostResponse(url, null, "{}", connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus热加载出错:" + response.body());
        }
    }

    /**
     * 查询指标
     *
     * @param promSql promSql
     * @return com.iscas.common.prometheus.tools.model.query.QueryResponse<T>
     * @date 2023/3/30
     */
    public QueryResponse<? extends Result> query(String promSql) throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + GET_QUERY + "?query=" + URLEncoder.encode(promSql, StandardCharsets.UTF_8);
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        // 解析结果
        return analysisResult(resBody);

    }

    /**
     * 查询指标
     *
     * @param promSql promSql
     * @param start   start
     * @param end     end
     * @param step    step
     * @return com.iscas.common.prometheus.tools.model.query.QueryResponse<T>
     * @date 2023/3/30
     */
    public QueryResponse<? extends Result> queryRange(String promSql, Integer start, Integer end, Integer step) throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + QUERY_RANGE + "?query=" + URLEncoder.encode(promSql, StandardCharsets.UTF_8);
        if (Objects.nonNull(start)) {
            url += "&start=" + start;
        }
        if (Objects.nonNull(end)) {
            url += "&end=" + end;
        }
        if (Objects.nonNull(step)) {
            url += "&step=" + step;
        }
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        // 解析结果
        return analysisResult(resBody);
    }

    /**
     * 查询标签
     *
     * @param promSql promSql
     * @return 标签
     * @date 2023/3/30
     */
    public List<List<Metric>> series(String promSql) throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + SERIES + "?match[]=" + URLEncoder.encode(promSql, StandardCharsets.UTF_8);
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询标签出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        List<Map<String, String>> dataList = (List<Map<String, String>>) resultMap.get(DATA);
        if (Objects.nonNull(dataList)) {
            return dataList.stream().map(data -> {
                // 生成metric对象数组
                List<Metric> metric = new ArrayList<>();
                // 解析metric
                data.forEach((k, v) -> metric.add(new Metric(k, v)));
                return metric;
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 查询标签的key
     *
     * @return 标签值
     * @date 2023/3/30
     */
    public List<String> getLabels() throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + GET_LABEL;
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询标签出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        return (List<String>) resultMap.get(DATA);
    }

    /**
     * 查询特定标签的所有值
     *
     * @param key 标签key
     * @return 标签值
     * @date 2023/3/30
     */
    public List<String> getLabelValue(String key) throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + String.format(GET_LABEL_VALUES, key);
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询标签出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        return (List<String>) resultMap.get(DATA);
    }


    /**
     * 获取当前监听的目标端
     *
     * @return TargetResponse
     * @date 2023/3/31
     */
    public TargetResponse getTarget() throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + GET_TARGET;
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询目标出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        Map<String, List<Map>> data = (Map<String, List<Map>>) resultMap.get(DATA);
        return analysisTargetResult(data);
    }

    /**
     * 根据状态获取当前监听的目标端
     *
     * @return TargetResponse
     * @date 2023/3/31
     */
    public TargetResponse getTargetByState(String state) throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + GET_TARGET + "?state=" + state;
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询目标出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        Map<String, List<Map>> data = (Map<String, List<Map>>) resultMap.get(DATA);
        return analysisTargetResult(data);
    }

    /**
     * 获取告警规则
     *
     * @return TargetResponse
     * @date 2023/3/31
     */
    public List<Group> getRule() throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + GET_RULE;
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询告警规则出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        Map<String, List<Map>> data = (Map<String, List<Map>>) resultMap.get(DATA);
        return analysisRules(data);
    }

    /**
     * 获取活动的告警
     *
     * @return java.util.List<com.iscas.common.prometheus.tools.model.alert.Alert>
     * @date 2023/4/6
     */
    public List<Alert> getActiveAlert() throws PrometheusException {
        String url = connectionCfg.getPrometheusUrl() + GET_ACTIVE_ALERT;
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询活动告警出错:" + resBody);
        }
        // 解析结果
        Map resultMap = JsonUtils.fromJson(resBody, Map.class);
        if (!Objects.equals(resultMap.get(STATUS), SUCCESS)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }
        Map<String, List<Map<String, Object>>> data = (Map<String, List<Map<String, Object>>>) resultMap.get(DATA);
        if (Objects.nonNull(data)) {
            List<Map<String, Object>> alertMaps = data.get(ALERTS);
            return analysisAlert(alertMaps);
        }
        return null;
    }

    /**
     * 获取告警信息，从alertManager获取
     *
     * @return java.util.List<com.iscas.common.prometheus.tools.model.alert.AlertInfo>
     * @date 2023/4/6
     */
    public List<AlertInfo> getAlertInfo() throws PrometheusException {
        String url = connectionCfg.getAlertmanagerUrl() + GET_ALERT_INFO;
        HttpResponse<String> response;
        try {
            response = httpClient.doGetResponse(url, null, connectionCfg.getTimeout(), String.class);
        } catch (IOException | InterruptedException e) {
            throw new PrometheusException("调用prometheus rest api 出错", e);
        }
        String resBody = response.body();
        if (!Objects.equals(response.statusCode(), 200)) {
            throw new PrometheusException("prometheus查询告警信息出错:" + resBody);
        }
        // 解析结果
        List<Map<String, Object>> results = JsonUtils.fromJson(resBody, new TypeReference<List<Map<String, Object>>>(){});
        if (Objects.nonNull(results)) {
            return analysisAlertInfo(results);
        }
        return null;
    }

//    /**
//     * 新增告警规则
//     *
//     * @date 2023/4/6
//     */
//    public void createRule(List<Group> groups) throws PrometheusException {
//        String url = connectionCfg.getPrometheusUrl() + UPDATE_RULE;
//        String groupEditorJsonStr = handleGroupsEditorToJsonStr(groups);
//        HttpResponse<String> response;
//        try {
//            response = httpClient.doPostResponse(url, null, groupEditorJsonStr, connectionCfg.getTimeout(), String.class);
//        } catch (IOException | InterruptedException e) {
//            throw new PrometheusException("调用prometheus rest api 出错", e);
//        }
//        String resBody = response.body();
//        if (!Objects.equals(response.statusCode(), 200)) {
//            throw new PrometheusException("prometheus新增告警规则出错:" + resBody);
//        }
//    }

//    /**
//     * 将告警规则实体转为json字符串
//     * */
//    private String handleGroupsEditorToJsonStr(List<Group> groups) throws PrometheusException {
//        Map<String, Object> resultMap = new HashMap<>();
//        List<Map<String, Object>> groupMaps = new ArrayList<>();
//
//        for (Group group : groups) {
//            Map<String, Object> groupMap = new HashMap<>();
//            String name = group.getName();
//            groupMap.put(NAME, name);
//            List<Map<String, Object>> ruleList = new ArrayList<>();
//
//            List<Rule> rules = group.getRules();
//            if (Objects.nonNull(rules)) {
//                for (Rule rule : rules) {
//                    Map<String, Object> ruleMap = new HashMap<>();
//
//                    // 根据不同的类型，设置alert或record参数
//                    String type = rule.getType();
//                    if (Objects.equals(ALERTING, type)) {
//                        ruleMap.put(ALERT, rule.getName());
//                    } else if (Objects.equals(RECORDING, type)) {
//                        ruleMap.put(RECORD, rule.getName());
//                    } else {
//                        throw new PrometheusException("不支持的告警类型:" + type);
//                    }
//
//                    // expr
//                    ruleMap.put(EXPR, rule.getQuery());
//
//                    // for
//                    Integer duration = rule.getDuration();
//                    if (Objects.nonNull(duration)) {
//                        resultMap.put(FOR, duration + "s");
//                    }
//
//                    // labels
//                    resultMap.put(LABELS, convertLabel(rule.getLabels()));
//
//                    // annotations
//                    resultMap.put(ANNOTATIONS, convertAnnotation(rule.getAnnotations()));
//
//                    ruleList.add(ruleMap);
//                }
//            }
//
//            groupMap.put(RULES, ruleList);
//        }
//
//        resultMap.put(GROUPS, groupMaps);
//        return JsonUtils.toJson(resultMap);
//    }

    private Map<String, String> convertLabel(List<Label> labels) {
        if (Objects.nonNull(labels)) {
            Map<String, String> result = new HashMap<>();
            for (Label label : labels) {
                result.put(label.getKey(), label.getValue());
            }
            return result;
        }
        return null;
    }

    private Map<String, String> convertAnnotation(List<Annotation> annotations) {
        if (Objects.nonNull(annotations)) {
            Map<String, String> result = new HashMap<>();
            for (Annotation annotation : annotations) {
                result.put(annotation.getKey(), annotation.getValue());
            }
            return result;
        }
        return null;
    }

    /**
     * 解析告警信息
     * */
    private List<AlertInfo> analysisAlertInfo(List<Map<String, Object>> results) {
        return results.stream().map(map -> {
            AlertInfo alertInfo = new AlertInfo();
            // 解析annotations
            alertInfo.setAnnotations(createAnnotation((Map<String, String>) map.get(ANNOTATIONS)));

            // 解析endsAt
            String endsAt = (String) map.get(ENDS_AT);
            if (endsAt != null && !"".equals(endsAt)) {
                endsAt = formatTimeStr3(endsAt);
                LocalDateTime time = LocalDateTime.parse(endsAt, DateTimeFormatter.ofPattern(TIME_PATTERN3));
                alertInfo.setEndsAt(time);
            }

            // 解析fingerprint
            alertInfo.setFingerprint((String) map.get(FINGERPRINT));

            // 解析receivers
            alertInfo.setReceivers((List<Map<String, Object>>) map.get(RECEIVERS));

            // 解析startsAt
            String startsAt = (String) map.get(STARTS_AT);
            if (startsAt != null && !"".equals(startsAt)) {
                startsAt = formatTimeStr3(startsAt);
                LocalDateTime time = LocalDateTime.parse(startsAt, DateTimeFormatter.ofPattern(TIME_PATTERN3));
                alertInfo.setStartsAt(time);
            }

            // 解析status
            alertInfo.setStatus(analysisAlertInfoStatus((Map<String, Object>) map.get(STATUS)));

            // 解析updatedAt
            String updatedAt = (String) map.get(UPDATED_AT);
            if (updatedAt != null && !"".equals(updatedAt)) {
                updatedAt = formatTimeStr4(updatedAt);
                LocalDateTime time = LocalDateTime.parse(updatedAt, DateTimeFormatter.ofPattern(TIME_PATTERN4));
                alertInfo.setUpdatedAt(time);
            }

            // 解析generatorURL
            alertInfo.setGeneratorURL((String) map.get(GENERATOR_URL));

            // 解析labels
            alertInfo.setLabels(createLabel((Map<String, String>) map.get(LABELS)));
            return alertInfo;
        }).toList();
    }

    private AlertInfoStatus analysisAlertInfoStatus(Map<String, Object> statusMap) {
        AlertInfoStatus status = new AlertInfoStatus();
        // 解析inhibitedBy
        status.setInhibitedBy((List<String>) statusMap.get(INHIBITED_BY));

        // 解析silencedBy
        status.setSilencedBy((List<String>) statusMap.get(SILENCE_BY));

        // 解析state
        status.setState((String) statusMap.get(STATE));
        return status;
    }

    /**
     * 解析告警规则
     */
    private List<Group> analysisRules(Map<String, List<Map>> data) {
        List<Map> groupsMaps = data.get(GROUPS);
        return groupsMaps.stream().map(groupMap -> {
            Group group = new Group();
            // 解析name
            group.setName((String) groupMap.get(NAME));

            // 解析file
            group.setFile((String) groupMap.get(FILE));

            // 解析interval
            group.setInterval((Integer) groupMap.get(INTERVAL));

            // 解析limit
            group.setLimit((Integer) groupMap.get(LIMIT));

            // 解析evaluationTime
            group.setEvaluationTime(Double.parseDouble(groupMap.get(EVALUATION_TIME).toString()));

            // 解析lastEvaluation
            String lastEvaluation = (String) groupMap.get(LAST_EVALUATION);
            if (lastEvaluation != null && !"".equals(lastEvaluation)) {
                lastEvaluation = formatTimeStr(lastEvaluation);
                LocalDateTime time = LocalDateTime.parse(lastEvaluation, DateTimeFormatter.ofPattern(TIME_PATTERN));
                group.setLastEvaluation(time);
            }
            // 解析alerts
            group.setRules(doAnalysisRule((List<Map<String, Object>>) groupMap.get(RULES)));

            return group;
        }).toList();
    }

    private List<Rule> doAnalysisRule(List<Map<String, Object>> ruleList) {
        if (ruleList != null) {
            return ruleList.stream().map(r -> {
                Rule rule = new Rule();
                // 解析state
                rule.setState((String) r.get(STATE));

                // 解析name
                rule.setName((String) r.get(NAME));

                // 解析query
                rule.setQuery((String) r.get(QUERY));

                // 解析duration
                rule.setDuration((Integer) r.get(DURATION));

                // 解析labels
                rule.setLabels(createLabel((Map<String, String>) r.get(LABELS)));

                // 解析annotations
                rule.setAnnotations(createAnnotation((Map<String, String>) r.get(ANNOTATIONS)));

                // 解析health
                rule.setHealth((String) r.get(HEALTH));

                // 解析evaluationTime
                rule.setEvaluationTime(Double.parseDouble(r.get(EVALUATION_TIME).toString()));

                // 解析lastEvaluation
                String lastEvaluation = (String) r.get(LAST_EVALUATION);
                if (lastEvaluation != null && !"".equals(lastEvaluation)) {
                    lastEvaluation = formatTimeStr(lastEvaluation);
                    LocalDateTime time = LocalDateTime.parse(lastEvaluation, DateTimeFormatter.ofPattern(TIME_PATTERN));
                    rule.setLastEvaluation(time);
                }

                // 解析type
                rule.setType((String) r.get(TYPE));

                // 解析alerts
                rule.setAlerts(analysisAlert((List<Map<String, Object>>) r.get(ALERTS)));

                return rule;
            }).toList();
        }
        return null;
    }

    private List<Alert> analysisAlert(List<Map<String, Object>> alertList) {
        if (alertList != null) {
            return alertList.stream().map(a -> {
                Alert alert = new Alert();
                // 解析labels
                alert.setLabels(createLabel((Map<String, String>) a.get(LABELS)));

                // 解析annotations
                alert.setAnnotations(createAnnotation((Map<String, String>) a.get(ANNOTATIONS)));

                // 解析state
                alert.setState((String) a.get(STATE));

                // 解析activeAt
                String activeAt = (String) a.get(ACTIVE_AT);
                if (activeAt != null && !"".equals(activeAt)) {
                    activeAt = formatTimeStr2(activeAt);
                    LocalDateTime time = LocalDateTime.parse(activeAt, DateTimeFormatter.ofPattern(TIME_PATTERN2));
                    alert.setActiveAt(time);
                }

                // 解析value
                alert.setValue((String) a.get(VALUE));

                return alert;
            }).toList();
        }
        return null;
    }


    private static String formatTimeStr(String lastEvaluation) {
        String s = "";
        if (lastEvaluation.contains(".") && lastEvaluation.contains("+")) {
            s = lastEvaluation.substring(lastEvaluation.indexOf("."), lastEvaluation.indexOf("+"));
        } else if (lastEvaluation.contains(".") && lastEvaluation.contains("-")) {
            s = lastEvaluation.substring(lastEvaluation.indexOf("."), lastEvaluation.indexOf("-"));
        }
        String replaced = s;
        for (int i = 0; i < 8 - s.length(); i++) {
            replaced += "0";
        }
        lastEvaluation = lastEvaluation.replace(s, replaced);
        return lastEvaluation;
    }

    private static String formatTimeStr2(String lastEvaluation) {
        String s = "";
        if (lastEvaluation.contains(".") && lastEvaluation.contains("Z")) {
            s = lastEvaluation.substring(lastEvaluation.indexOf("."), lastEvaluation.indexOf("Z"));
        }
        String replaced = s;
        for (int i = 0; i < 10 - s.length(); i++) {
            replaced += "0";
        }
        lastEvaluation = lastEvaluation.replace(s, replaced);
        return lastEvaluation;
    }

    private static String formatTimeStr3(String timeStr) {
        String s = "";
        if (timeStr.contains(".") && timeStr.contains("Z")) {
            s = timeStr.substring(timeStr.indexOf("."), timeStr.indexOf("Z"));
        }
        String replaced = s;
        for (int i = 0; i < 4 - s.length(); i++) {
            replaced += "0";
        }
        timeStr = timeStr.replace(s, replaced);
        return timeStr;
    }

    private static String formatTimeStr4(String str) {
        String s = "";
        if (str.contains(".") && str.contains("+")) {
            s = str.substring(str.indexOf("."), str.indexOf("+"));
        } else if (str.contains(".") && str.contains("-")) {
            s = str.substring(str.indexOf("."), str.indexOf("-"));
        }
        String replaced = s;
        for (int i = 0; i < 4 - s.length(); i++) {
            replaced += "0";
        }
        str = str.replace(s, replaced);
        return str;
    }

    /**
     * 解析target
     * 示例结构：
     * {
     * "status": "success",
     * "data": {
     * "activeTargets": [
     * {
     * "discoveredLabels": {
     * "__address__": "localhost:9090",
     * "__metrics_path__": "/metrics",
     * "__scheme__": "http",
     * "__scrape_interval__": "15s",
     * "__scrape_timeout__": "10s",
     * "job": "prometheus"
     * },
     * "labels": {
     * "instance": "localhost:9090",
     * "job": "prometheus"
     * },
     * "scrapePool": "prometheus",
     * "scrapeUrl": "http://localhost:9090/metrics",
     * "globalUrl": "http://WIN-20230109NPV:9090/metrics",
     * "lastError": "",
     * "lastScrape": "2023-03-31T10:44:00.8477987+08:00",
     * "lastScrapeDuration": 0.0018373,
     * "health": "up",
     * "scrapeInterval": "15s",
     * "scrapeTimeout": "10s"
     * },
     * {
     * "discoveredLabels": {
     * "__address__": "localhost:9091",
     * "__metrics_path__": "/metrics",
     * "__scheme__": "http",
     * "__scrape_interval__": "15s",
     * "__scrape_timeout__": "10s",
     * "instance": "pushgateway",
     * "job": "pushgateway"
     * },
     * "labels": {
     * "instance": "pushgateway",
     * "job": "pushgateway"
     * },
     * "scrapePool": "pushgateway",
     * "scrapeUrl": "http://localhost:9091/metrics",
     * "globalUrl": "http://WIN-20230109NPV:9091/metrics",
     * "lastError": "",
     * "lastScrape": "2023-03-31T10:43:54.6823292+08:00",
     * "lastScrapeDuration": 0.0019047,
     * "health": "up",
     * "scrapeInterval": "15s",
     * "scrapeTimeout": "10s"
     * }
     * ],
     * "droppedTargets": []
     * }
     * }
     */
    private TargetResponse analysisTargetResult(Map<String, List<Map>> data) {
        TargetResponse targetResponse = new TargetResponse();
        List<Map> activeTargets = data.get(ACTIVE_TARGETS);
        List<Map> droppedTargets = data.get(DROPPED_TARGETS);
        targetResponse.setActiveTargets(analysisTarget(activeTargets));
        targetResponse.setDroppedTargets(analysisTarget(droppedTargets));
        return targetResponse;
    }

    private List<Target> analysisTarget(List<Map> targetMapList) {
        if (targetMapList != null && targetMapList.size() > 0) {
            return targetMapList.stream().map(targetMap -> {
                Target target = new Target();
                // 获取并解析discoveredLabels属性
                Map<String, String> discoveredLabels = (Map<String, String>) targetMap.get(DISCOVERED_LABELS);
                target.setDiscoveredLabels(createLabel(discoveredLabels));

                // 获取并解析labels属性
                Map<String, String> labels = (Map<String, String>) targetMap.get(LABELS);
                target.setLabels(createLabel(labels));

                // 获取并解析scrapePool属性
                target.setScrapePool((String) targetMap.get(SCRAP_POOL));

                // 获取并解析scrapeUrl属性
                target.setScrapeUrl((String) targetMap.get(SCRAP_URL));

                // 获取并解析globalUrl属性
                target.setGlobalUrl((String) targetMap.get(GLOBAL_URL));

                // 获取并解析lastError属性
                target.setLastError((String) targetMap.get(LAST_ERROR));

                // 获取并解析lastScrape属性
                String lastScrap = (String) targetMap.get(LAST_SCRAP);
                if (lastScrap != null && !"".equals(lastScrap)) {
                    lastScrap = formatTimeStr(lastScrap);
                    LocalDateTime lastScrapTime = null;
                    try {
                        lastScrapTime = LocalDateTime.parse(lastScrap, DateTimeFormatter.ofPattern(TIME_PATTERN));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    target.setLastScrape(lastScrapTime);
                }

                // 获取并解析lastScrapeDuration属性
                target.setLastScrapeDuration((Double) targetMap.get(LAST_SCRAP_DURATION));

                // 获取并解析health属性
                target.setHealth((String) targetMap.get(HEALTH));

                // 获取并解析scrapeInterval属性
                target.setScrapeInterval((String) targetMap.get(SCRAP_INTERVAL));

                // 获取并解析scrapeTimeout属性
                target.setScrapeTimeout((String) targetMap.get(SCRAP_TIMEOUT));
                return target;
            }).toList();
        }
        return null;
    }

    /**
     * 解析query结果
     * <p>
     * vector类型示例：
     * {
     * "status": "success",
     * "data": {
     * "resultType": "vector",
     * "result": [
     * {
     * "metric": {
     * "__name__": "station_system_memory_used_bytes",
     * "collector_name": "station_system_memory_used",
     * "host_address": "192.168.100.22",
     * "host_name": "WIN-20230109NPV",
     * "instance": "192.168.100.22:14501",
     * "job": "station-system-job",
     * "station_name": "测试"
     * },
     * "value": [
     * 1680170238.504,
     * "14563708928"
     * ]
     * }
     * ]
     * }
     * }
     * <p>
     * matrix类型示例：
     * {
     * "status": "success",
     * "data": {
     * "resultType": "matrix",
     * "result": [
     * {
     * "metric": {
     * "__name__": "station_system_memory_used_bytes",
     * "collector_name": "station_system_memory_used",
     * "host_address": "192.168.100.22",
     * "host_name": "WIN-20230109NPV",
     * "instance": "192.168.100.22:14501",
     * "job": "station-system-job",
     * "station_name": "测试"
     * },
     * "values": [
     * [
     * 1680167633.789,
     * "18169200640"
     * ],
     * [
     * 1680167648.783,
     * "17891569664"
     * ],
     * [
     * 1680167663.779,
     * "17805451264"
     * ],
     * [
     * 1680167678.781,
     * "17842130944"
     * ]
     * ]
     * }
     * ]
     * }
     * }
     */
    private QueryResponse<? extends Result> analysisResult(String resBody) throws PrometheusException {
        Map map = JsonUtils.fromJson(resBody, Map.class);
        String status = (String) map.get(STATUS);
        // 如果状态不是success，抛出异常
        if (!Objects.equals(SUCCESS, status)) {
            throw new PrometheusException("prometheus查询指标出错:" + resBody);
        }

        Map data = (Map) map.get(DATA);
        String resultTypeStr = (String) data.get(RESULT_TYPE);
        Object resultData = data.get(RESULT);
        if (Objects.equals(VECTOR, resultTypeStr)) {
            // vector类型
            QueryResponse<VectorResult> queryResponse = new QueryResponse<>();
            queryResponse.setResultType(QueryResultType.VECTOR);
            List<Map> resultMap = (List<Map>) resultData;
            List<VectorResult> vectorResults = resultMap.stream().map(rm -> {
                // 创建VectorResult实体
                VectorResult result = new VectorResult();

                // 获取metric
                List<Metric> metric = getMetrics(rm);
                result.setMetric(metric);

                // 获取value
                List valueList = (List) rm.get(VALUE);
                // 生成value对象数组
                Value value = new Value((Double) valueList.get(0), (String) valueList.get(1));
                result.setValue(value);
                return result;
            }).toList();
            queryResponse.setResult(vectorResults);
            return queryResponse;
        } else if (Objects.equals(MATRIX, resultTypeStr)) {
            // matrix类型
            QueryResponse<MatrixResult> queryResponse = new QueryResponse<>();
            queryResponse.setResultType(QueryResultType.MATRIX);

            List<Map> resultMap = (List<Map>) resultData;
            List<MatrixResult> matrixResults = resultMap.stream().map(rm -> {
                // 创建MatrixResult实体
                MatrixResult result = new MatrixResult();

                // 获取metric
                List<Metric> metric = getMetrics(rm);
                result.setMetric(metric);

                // 获取values
                List<List> valueList = (List<List>) rm.get(VALUES);
                // 生成value对象数组
                List<Value> values = valueList.stream().map(list -> new Value((Double) list.get(0), (String) list.get(1))).toList();
                result.setValues(values);
                return result;
            }).toList();
            queryResponse.setResult(matrixResults);
            return queryResponse;

        } else {
            // 其他类型
            QueryResponse<OtherResult> queryResponse = new QueryResponse<>();
            queryResponse.setResultType(QueryResultType.OTHER);
            // 创建OtherResult实体
            OtherResult result = new OtherResult();
            // 将result的字符串值设置在other中
            result.setOther(resultData);
            queryResponse.setResult(List.of(result));
            return queryResponse;
        }

    }

    /**
     * 查询标签
     */
    private static List<Metric> getMetrics(Map rm) {
        // 获取metric
        Map<String, String> metricMap = (Map<String, String>) rm.get(METRIC);
        // 生成metric对象数组
        List<Metric> metric = new ArrayList<>();
        // 解析metric
        metricMap.forEach((k, v) -> metric.add(new Metric(k, v)));
        return metric;
    }

    /**
     * 生成label
     */
    private static List<Label> createLabel(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            // 生成label对象数组
            List<Label> labels = new ArrayList<>();
            // 解析label
            map.forEach((k, v) -> labels.add(new Label(k, v)));
            return labels;
        }
        return null;
    }

    /**
     * 生成annotation
     */
    private static List<Annotation> createAnnotation(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            // 生成label对象数组
            List<Annotation> annotations = new ArrayList<>();
            // 解析label
            map.forEach((k, v) -> annotations.add(new Annotation(k, v)));
            return annotations;
        }
        return null;
    }


}
