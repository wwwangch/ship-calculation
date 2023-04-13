package com.iscas.common.minio.tools;

import io.minio.MinioClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/4 16:49
 * @since jdk1.8
 */
public class MinioClientUtilsTest {
    private MinioClient minioClient;
    @BeforeEach
    public void before() {
        minioClient = MinioClientUtils.getClient("http://172.16.100.160:30900",
                 "admin", "iscas123");
    }

    /**
     * 测试获取minio-client
     * */
    @Test
    public void testGetClient() {
        System.out.println(minioClient);
        Assertions.assertNotNull(minioClient);
    }

}