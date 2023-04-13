package com.iscas.common.tools.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 14:40
 * @since jdk1.8
 */
public class BloomFilterTest {
    @Test
    public void test1(){
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b.put("121");
        b.put("122");
        b.put("123");
        Assert.assertFalse(b.mightContain("12321"));
        BloomFilter<String> b1 = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b1.put("aba");
        b1.put("abb");
        b1.put("abc");
        b1.putAll(b);
        Assert.assertTrue(b1.mightContain("123"));
    }
}
