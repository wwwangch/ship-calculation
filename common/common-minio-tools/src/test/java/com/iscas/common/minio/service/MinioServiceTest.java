package com.iscas.common.minio.service;

import com.iscas.common.minio.exception.MinioServiceException;
import io.minio.Result;
import io.minio.StatObjectResponse;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/4 17:20
 * @since jdk1.8
 */
public class MinioServiceTest {
    private MinioService minioService;

    @BeforeEach
    public void before() {
        minioService = new MinioService();
        minioService.init("http://172.16.10.160:30900", "admin", "iscas123");
    }

    /**
     * 判断存储桶是否存在
     */
    @Test
    public void testExsitsBucket() throws MinioServiceException {
        boolean exists = minioService.bucketExists("aaa");
        Assertions.assertFalse(exists);
    }

    /**
     * 创建存储桶
     */
    @Test
    public void makeBucket() throws MinioServiceException {
        boolean exists = minioService.bucketExists("my-bucket");
        if (exists) {
            try {
                minioService.makeBucket("my-bucket");
            } catch (Exception e) {
                Assertions.assertTrue(e instanceof MinioServiceException);
            }
        } else {
            minioService.makeBucket("my-bucket");
        }
    }

    /**
     * 测试列出所有存储桶
     */
    @Test
    public void listBuckets() throws MinioServiceException {
        List<Bucket> buckets = minioService.listBuckets();
        Optional.ofNullable(buckets)
                .ifPresent(list -> list.forEach(System.out::println));
    }

    /**
     * 测试删除存储桶
     */
    @Test
    public void removeBucket() throws MinioServiceException {
        minioService.makeBucket("test-1001");
        minioService.removeBucket("test-1001");
    }

    /**
     * 测试查询存储桶中的对象
     */
    @Test
    public void listObjects() throws MinioServiceException {
        Iterable<Result<Item>> results = minioService.listObjects("test-abc", "gra", false);
        if (results != null) {
            results.forEach(result -> {
                Item item = null;
                try {
                    item = result.get();
                    System.out.println(item);
                } catch (ErrorResponseException e) {
                    e.printStackTrace();
                } catch (InsufficientDataException e) {
                    e.printStackTrace();
                } catch (InternalException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidResponseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (ServerException e) {
                    e.printStackTrace();
                } catch (XmlParserException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 获取存储策略
     * */
    @Test
    public void getBucketPolicy() throws MinioServiceException {
        String bucketPolicy = minioService.getBucketPolicy("test-readonly");
        System.out.println(bucketPolicy);
    }

    /**
     * 设置存储策略
     * */
    @Test
    public void setBucketPolicy() throws MinioServiceException {
        minioService.setBucketPolicy("test-readonly",
                "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\"],\"Resource\":[\"arn:aws:s3:::test-readonly\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::test-readonly\"],\"Condition\":{\"StringEquals\":{\"s3:prefix\":[\"aaa\"]}}},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::test-readonly/aaa*\",\"arn:aws:s3:::test-readonly/work*\"]}]}");
    }

    /**
     * 获取对象状态
     * */
    @Test
    public void stateObject() throws MinioServiceException {
        StatObjectResponse statObjectResponse = minioService.statObject("test-readonly", "aaa/hostsxx");
    }

    /**
     * 以流的形式下载一个对象
     * */
    @Test
    public void getObject() throws IOException, MinioServiceException {
        File file = File.createTempFile("host", ".txt");
        file.deleteOnExit();
        InputStream inputStream = minioService.getObject("test-readonly", "aaa/hosts", 0L);
        OutputStream outputStream = new FileOutputStream(file);
        int len = 1024;
        byte[] buff = new byte[len];
        while ((len = inputStream.read(buff)) > 0) {
            outputStream.write(buff, 0, len);
        }
        outputStream.close();
        file.delete();
    }

    /**
     * 以流形式下载一个对象，断点续传
     * */
    @Test
    public void getObject2() throws IOException, MinioServiceException {
        File file = File.createTempFile("host", ".txt");
        file.deleteOnExit();
        InputStream inputStream = minioService.getObject("test-readonly",
                "aaa/hosts", 0L, 10L);
        OutputStream outputStream = new FileOutputStream(file);
        int len = 1024;
        byte[] buff = new byte[len];
        while ((len = inputStream.read(buff)) > 0) {
            outputStream.write(buff, 0, len);
        }
        outputStream.close();
        file.delete();
    }

    /**
     * 以流形式下载一个对象
     * */
    @Test
    public void getObject3() throws IOException, MinioServiceException {
        File file = File.createTempFile("host", ".txt");
        file.deleteOnExit();
        minioService.getObject("test-readonly",
                "aaa/hosts", file);
        file.delete();
    }

    /**
     * 上传
     * */
    @Test
    public void putObject() throws MinioServiceException {
        String str = "test";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        minioService.putObject("test-readonly", "aaa/bbb/test.txt", bais, -1L, 1024*1024*10L,
                "application/octet-stream;charset=utf-8");
    }

    /**
     * 上传2
     * */
    @Test
    public void putObject2() throws MinioServiceException, FileNotFoundException {
        String str = "D:\\BaiduNetdiskDownload\\seata-samples-dubbo.zip";

        minioService.putObject("test-readonly", "aaa/bbb/seata-samples-dubbo.zip",
                new FileInputStream(str), -1L, 1024*1024*10L,
                "application/octet-stream;charset=utf-8");
    }

    /**
     * 上传3
     * */
    @Test
    public void putObject3() throws MinioServiceException, IOException {
        String str = "D:\\项目\\红盾测试\\系统流程.jpg";

        minioService.putObject("test-readonly", "aaa/bbb/系统流程2.jpg",
                str, "application/octet-stream;charset=utf-8");
    }

    /**
     * 拷贝
     * */
    @Test
    public void copyObject() throws MinioServiceException {
        minioService.copyObject("test-abc", "aaa/bbb/ccc/系统流程.jpg",
                "test-readonly", "aaa/bbb/系统流程2.jpg");
    }

    /**
     * 删除
     * */
    @Test
    public void removeObject() throws MinioServiceException {
        minioService.removeObject("test-abc", "aaa/bbb/ccc/系统流程.jpg");
    }

    /**
     * 删除多个
     * */
    @Test
    public void removeObjects() throws MinioServiceException {
        minioService.removeObject("test-abc", Arrays.asList("aaa/bbb/ccc/系统流程.jpg", "aaa/bbb/ccc/系统流程2.jpg"));
    }

    /**
     * 生成临时URL
     * */
    @Test
    public void getPresignedObjectUrl() throws MinioServiceException {
        String url1 = minioService.getPresignedObjectUrl("test1", "grafana-svc.yaml",
                Method.GET, 100);
        System.out.println(url1);

        String url2 = minioService.getPresignedObjectUrl("test1", "grafana-aaa.yaml",
                Method.POST, 100);
        System.out.println(url2);

        String url3 = minioService.getPresignedObjectUrl("test1", "ms.txt",
                Method.DELETE, 100);
        System.out.println(url3);
    }


}