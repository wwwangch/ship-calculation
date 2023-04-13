package com.iscas.common.tools.core.collection;

import cn.hutool.core.map.MapUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Map扩展工具测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/23 9:42
 * @since jdk1.8
 */
public class MapRaiseUtilsTests {

    @Test
    public void test1() {
        System.out.println("------- MapRaiseUtils.convertToHump begin---------");
        Map<Object, Object> map = MapUtil.builder().put("a_param", 1)
                .put("b_param", 2).build();
        map = MapRaiseUtils.convertToHump(map);
        System.out.println(map);
        System.out.println("------- MapRaiseUtils.convertToHump end---------");
    }
}
