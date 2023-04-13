package com.iscas.common.tools.hutool.cache;

import cn.hutool.cache.file.LFUFileCache;
import cn.hutool.cache.file.LRUFileCache;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 文件缓存测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 15:12
 * @since jdk1.8
 */
public class FileCacheTest {
    private static File file = null;
    @BeforeAll
    public static void createCacheFile() throws IOException {
        file = File.createTempFile("testCache", ".cache");
        file.deleteOnExit();
        @Cleanup PrintWriter writer = new PrintWriter(file);
        writer.println("this is cache test!!!");
    }

    /**
     * LFU规则的文件缓存
     * */
    @Test
    public void test() throws IOException {
        LFUFileCache lfuFileCache = new LFUFileCache(1000,500,2000);
        byte[] fileBytes = lfuFileCache.getFileBytes(file);
        System.out.println(fileBytes.length);
    }

    /**
     * LRU规则的文件缓存
     * */
    @Test
    public void test2(){
        LRUFileCache lruFileCache = new LRUFileCache(1000,500,2000);
        byte[] fileBytes = lruFileCache.getFileBytes(file);
        System.out.println(fileBytes.length);
    }
}
