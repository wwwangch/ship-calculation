package com.iscas.common.tools.hutool.bloomfilter;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 布隆过滤器测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 14:24
 * @since jdk1.8
 */
public class BitMapBloomFilterTest {
    @Test
    public void filterTest() {
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("123");
        filter.add("abc");
        filter.add("ddd");


        Assertions.assertTrue(filter.contains("abc"));
        Assertions.assertTrue(filter.contains("ddd"));
        Assertions.assertTrue(filter.contains("123"));
    }
}
