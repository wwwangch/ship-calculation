package com.iscas.common.tools.core.io.zip;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * gzip工具测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/12 15:00
 * @since jdk1.8
 */
public class GzipUtilsTests {
    @Test
    public void test1() throws IOException {
        System.out.println("--------Gzip压缩 begin---------");
        String str = "中国为各位各位个我我国围观围观围观威尔和人品【黄坤鹏【wgwegweg<>          " +
                "\nwegwegwe\nwgweeeeeeeeeeeee                       wegwe              \n\r                ";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] compress = GzipUtils.compress(str);
        String str2 = GzipUtils.uncompress(compress);
        System.out.println("原来大小:" + bytes.length + ";压缩后大小:" + compress.length);
        System.out.println(str);
        System.out.println(str2);

        System.out.println("===============================下面直接压缩bytes==============================");
        byte[] bytes2 = str.getBytes(StandardCharsets.UTF_8);
        byte[] compress2 = GzipUtils.compressFromBytes(bytes2);
        byte[] result2 = GzipUtils.uncompressToBytes(compress2);
        System.out.println("原来大小:" + bytes2.length + ";压缩后大小:" + compress2.length);
        System.out.println(bytes2);
        System.out.println(result2);
        System.out.println("--------Gzip压缩 end---------");
    }

    @Test
    public void test3() throws IOException {
        System.out.println("--------Gzip压缩 begin---------");
        Map<Object, Object> map = MapUtil.builder().put("name", "zhangsan")
                .put("age", 22)
                .put("address","china beijing")
                .put("email","zqw@sina.cn")
                .put("realName", "xiaozhang")
                .put("degree","doctor")
                .build();
        String str = JSONUtil.toJsonStr(map);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] compress = GzipUtils.compress(str);
        System.out.println(bytes.length);
        System.out.println(compress.length);
        System.out.println("--------Gzip压缩 end---------");

    }
}
