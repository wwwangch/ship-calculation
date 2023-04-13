package com.iscas.common.k8s.tools.util;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/15 16:45
 * @since jdk1.8
 */
public class YamlUtils {
    private YamlUtils() {}

    /**
     * 将JSON格式化为Yaml
     * */
    public static String formatToYaml(String json) {
        Yaml yaml = new Yaml();
        //将 JSON 字符串转成 Map
        Map<String,Object> map = yaml.load(json);
        //转换成 YAML 字符串
        return "---\n" + yaml.dumpAsMap(map);
    }
}
