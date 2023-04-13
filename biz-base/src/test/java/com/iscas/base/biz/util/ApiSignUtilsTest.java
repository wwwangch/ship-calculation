package com.iscas.base.biz.util;

import com.iscas.common.web.tools.json.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/21 14:24
 * @since jdk1.8
 */
public class ApiSignUtilsTest {
    private static final String secretKey = "ISCAS123";

    @Test
    public void test1() {
        Map signature = ApiSignUtils.getSignature(Map.of("username", "zhangsan", "password", "123456"), secretKey);
        System.out.println(signature);
    }

    @Test
    public void test2() {
        Map map = new TreeMap();
        map.put("username", "zhangsan");
        map.put("password", "123");
        map.put("aaa", new TreeMap(){{
            put("a", "1");
            put("b", List.of(1, 2, 3));
        }});
        map.put("bbb", List.of("1", 2, Map.of(1,2, 3,4)));
        String s = JsonUtils.toJson(map);
        System.out.println(s);
        Map signature = ApiSignUtils.getSignature(map, secretKey);
        System.out.println(signature);
    }


}