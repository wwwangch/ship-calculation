package com.iscas.common.minio.service;


import com.iscas.common.minio.exception.MinioServiceException;
import com.iscas.common.minio.tools.MinioClientUtils;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import okhttp3.Response;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author zhuquanwen
 * @date 2021/6/4 17:03
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "unchecked"})
public class MinioService {
    private MinioClient minioClient = null;

    public void init(String endpoint, String accessKey, String secretKey) {
        minioClient = MinioClientUtils.getClient(endpoint, accessKey, secretKey);
    }

    /**
     * 判断某个存储桶在不在
     *
     * @param bucketName 存储桶名称
     * @return boolean
     * @throws MinioServiceException 异常
     * @date 2021/6/4
     * @since jdk1.8
     */
    public boolean bucketExists(String bucketName) throws MinioServiceException {
        check();
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            return (boolean) throwMinioException(e);
        }
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     * @throws MinioServiceException 异常
     * @date 2021/6/4
     * @since jdk1.8
     */
    public void makeBucket(String bucketName) throws MinioServiceException {
        check();
        boolean exists = bucketExists(bucketName);
        if (exists) {
            throw new MinioServiceException(String.format("存储桶:%s已存在", bucketName));
        }
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 列出所有存储桶
     *
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public List<Bucket> listBuckets() throws MinioServiceException {
        check();
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            return (List<Bucket>) throwMinioException(e);
        }
    }

    /**
     * 删除存储桶
     *
     * @param bucketName 存储桶名称
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void removeBucket(String bucketName) throws MinioServiceException {
        check();
        try {
            boolean exists = bucketExists(bucketName);
            if (!exists) {
                throw new MinioServiceException(String.format("存储桶:%s不存在", bucketName));
            }
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 获取存储桶中的对象
     *
     * @param bucketName 存储桶名称
     * @param prefix     前缀
     * @param recursive  是否递归
     * @return java.lang.Iterable<io.minio.Result < io.minio.messages.Item>>
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive) throws MinioServiceException {
        check();
        boolean exists = bucketExists(bucketName);
        if (!exists) {
            throw new MinioServiceException(String.format("存储桶:%s不存在", bucketName));
        }
        return minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName)
                .prefix(prefix).recursive(recursive).build());
    }

    /**
     * 获取存储策略
     *
     * @param bucketName 存储桶名称
     * @return String
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public String getBucketPolicy(String bucketName) throws MinioServiceException {
        check();
        boolean exists = bucketExists(bucketName);
        if (!exists) {
            throw new MinioServiceException(String.format("存储桶:%s不存在", bucketName));
        }
        try {
            return minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            return (String) throwMinioException(e);
        }
    }

    /**
     * 设置存储策略
     *
     * @param bucketName 存储桶名称
     * @param policyJson 策略JSON
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void setBucketPolicy(String bucketName, String policyJson) throws MinioServiceException {
        check();
        try {
            boolean exists = bucketExists(bucketName);
            if (!exists) {
                throw new MinioServiceException(String.format("存储桶:%s不存在", bucketName));
            }
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(policyJson).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 查看对象状态
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return io.minio.StatObjectResponse
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    @SuppressWarnings("UnusedReturnValue")
    public StatObjectResponse statObject(String bucketName, String objectName) throws MinioServiceException {
        check();
        try {
            return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            throw new MinioServiceException(String.format("查询对象:%s的状态出错", objectName));
        }
    }

    /**
     * 以流的形式下载一个对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param offset     从下标开始下载，可以传0或null
     * @return InputStream 输入流
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public InputStream getObject(String bucketName, String objectName, Long offset) throws MinioServiceException {
        check();
        try {
            statObject(bucketName, objectName);
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName)
                    .offset(offset).build());
        } catch (Exception e) {
            return (InputStream) throwMinioException(e);
        }
    }

    /**
     * 下载一个对象，用输出流输出
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param os         输出
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void getObject(String bucketName, String objectName, OutputStream os) throws MinioServiceException {
        check();
        try {
            statObject(bucketName, objectName);
            try (GetObjectResponse is = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName)
                    .offset(0L).build())) {
                transferIO(is, os);
            }
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 下载一个对象，下载到本地文件
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param file       本地文件
     * @throws MinioServiceException 异常
     * @throws IOException           IO异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void getObject(String bucketName, String objectName, File file) throws MinioServiceException, IOException {
        try (OutputStream os = new FileOutputStream(file)) {
            getObject(bucketName, objectName, os);
        }
    }

    /**
     * 下载一个对象，下载到本地路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param filePath   本地路径
     * @throws MinioServiceException 异常
     * @throws IOException           IO异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void getObject(String bucketName, String objectName, String filePath) throws MinioServiceException, IOException {
        getObject(bucketName, objectName, new File(filePath));
    }

    /**
     * 以流的形式下载一个对象，断点续传
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param offset     从下标开始下载，可以传0或null
     * @param len        当次下载的长度
     * @return InputStream 输入流
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public InputStream getObject(String bucketName, String objectName, Long offset, Long len) throws MinioServiceException {
        check();
        try {
            statObject(bucketName, objectName);
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName)
                    .offset(offset).length(len).build());
        } catch (Exception e) {
            return (InputStream) throwMinioException(e);
        }
    }

    /**
     * 创建路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void mkdirs(String bucketName, String objectName) throws MinioServiceException {
        putObject(bucketName, objectName, new ByteArrayInputStream(new byte[0]), 0L, -1L, null);
    }


    /**
     * 上传
     *
     * @param bucketName  存储桶名称
     * @param objectName  对象名称
     * @param stream      输入流
     * @param size        输入流大小，如果不知道设置为-1，由partSize控制大上传块大小
     * @param partSize    如果知道流的大小，设置为-1
     * @param contentType 获取对象时的content-type，如image/jpeg，application/octet-stream;charset=utf-8
     *                    在用minio管理界面看的时候有用，比如为图片格式，可以预览
     *                    如果传null，默认为application/octet-stream;charset=utf-8
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void putObject(String bucketName, String objectName, InputStream stream, Long size, Long partSize, String contentType) throws MinioServiceException {
        check();
        try {
            boolean exists = bucketExists(bucketName);
            if (!exists) {
                throw new MinioServiceException(String.format("存储桶：%s不存在", bucketName));
            }
            minioClient.putObject(PutObjectArgs.builder()
                    .contentType(contentType == null ? "application/octet-stream;charset=utf-8" : contentType).bucket(bucketName)
                    .object(objectName).stream(stream, size, partSize).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 上传本地文件
     *
     * @param bucketName  存储桶名称
     * @param objectName  对象名称
     * @param file        文件
     * @param contentType 获取对象时的content-type，如image/jpeg，application/octet-stream;charset=utf-8
     *                    在用minio管理界面看的时候有用，比如为图片格式，可以预览
     *                    如果传null，默认为application/octet-stream;charset=utf-8
     * @throws MinioServiceException 异常
     * @throws IOException           IO异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void putObject(String bucketName, String objectName, File file, String contentType) throws IOException, MinioServiceException {
        try (InputStream is = new FileInputStream(file)) {
            putObject(bucketName, objectName, is, (long) is.available(), -1L, contentType);
        }
    }

    /**
     * 上传本地文件
     *
     * @param bucketName  存储桶名称
     * @param objectName  对象名称
     * @param filePath    文件路径
     * @param contentType 获取对象时的content-type，如image/jpeg，application/octet-stream;charset=utf-8
     *                    在用minio管理界面看的时候有用，比如为图片格式，可以预览
     *                    如果传null，默认为application/octet-stream;charset=utf-8
     * @throws MinioServiceException 异常
     * @throws IOException           IO异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void putObject(String bucketName, String objectName, String filePath, String contentType) throws IOException, MinioServiceException {
        putObject(bucketName, objectName, new File(filePath), contentType);
    }

    /**
     * 拷贝
     *
     * @param bucketName       存储桶名
     * @param objectName       对象名
     * @param sourceBucketName 原始存储桶名
     * @param sourceObjectName 原始对象名
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void copyObject(String bucketName, String objectName, String sourceBucketName,
                           String sourceObjectName) throws MinioServiceException {
        try {
            minioClient.copyObject(CopyObjectArgs.builder().bucket(bucketName).object(objectName)
                    .source(CopySource.builder().bucket(sourceBucketName).object(sourceObjectName).build()).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    public void composeObject(String bucketName, String objectName, List<ComposeSource> sourceObjects) throws MinioServiceException {
        try {
            minioClient.composeObject(ComposeObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .sources(sourceObjects)
                    .build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 删除对象
     *
     * @param bucketName 存储桶名
     * @param objectName 对象名
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void removeObject(String bucketName, String objectName) throws MinioServiceException {
        try {
            boolean exists = bucketExists(bucketName);
            if (!exists) {
                throw new MinioServiceException(String.format("存储桶：%s不存在", bucketName));
            }
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 删除多个对象
     *
     * @param bucketName  存储桶名
     * @param objectNames 对象名
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public void removeObject(String bucketName, Collection<String> objectNames) throws MinioServiceException {
        try {
            boolean exists = bucketExists(bucketName);
            if (!exists) {
                throw new MinioServiceException(String.format("存储桶：%s不存在", bucketName));
            }
            List<DeleteObject> list = new ArrayList<>();
            Optional.ofNullable(objectNames).ifPresent(names -> names.forEach(name -> {
                        DeleteObject deleteObject = new DeleteObject(name);
                        list.add(deleteObject);
                    }
            ));
            minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(list).build());
        } catch (Exception e) {
            throwMinioException(e);
        }
    }

    /**
     * 生成临时URL
     *
     * @param bucketName 存储桶名
     * @param objectName 对象名
     * @param method     http请求方式
     * @param expires    过期时间（秒）
     * @return String url
     * @throws MinioServiceException 异常
     * @date 2021/6/5
     * @since jdk1.8
     */
    public String getPresignedObjectUrl(String bucketName, String objectName, Method method, Integer expires) throws MinioServiceException {
        try {
            boolean exists = bucketExists(bucketName);
            if (!exists) {
                throw new MinioServiceException(String.format("存储桶：%s不存在", bucketName));
            }
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName)
                    .object(objectName).expiry(expires).method(method).build());

        } catch (Exception e) {
            return (String) throwMinioException(e);
        }
    }

    /**
     * 判断文件或文件夹是否存在
     *
     * @param bucketName 桶名称
     * @param objectName 文件名
     * @return boolean
     * @date 2022/6/23
     * @since jdk11
     */
    public boolean checkExist(String bucketName, String objectName) throws MinioServiceException {
        return checkFileIsExist(bucketName, objectName) || checkFolderIsExist(bucketName, objectName);
    }

    /**
     * 判断文件是否存在
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
     * @return true存在, 反之
     */
    public boolean checkFileIsExist(String bucketName, String objectName) throws MinioServiceException {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucketName).object(objectName).build()
            );
        } catch (ErrorResponseException e) {
            Response response = e.response();
            if (response.code() == 404) {
                return false;
            }
            throwMinioException(e);
        } catch (Exception e) {
            throwMinioException(e);
        }
        return true;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 桶名称
     * @param folderName 文件夹名称
     * @return true存在, 反之
     */
    public boolean checkFolderIsExist(String bucketName, String folderName) throws MinioServiceException {
        try {
            if (folderName.endsWith("/")) {
                folderName = folderName.substring(0, folderName.length() - 1);
            }
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs
                            .builder()
                            .bucket(bucketName)
                            .prefix(folderName)
                            .recursive(false)
                            .build());
            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && (folderName + "/").equals(item.objectName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            throwMinioException(e);
        }
        return false;
    }


    /**
     * 获取Minio对象
     *
     * @return io.minio.MinioClient
     * @date 2021/6/5
     * @since jdk1.8
     */
    public MinioClient getClient() {
        return minioClient;
    }

    private void check() {
        if (minioClient == null) {
            throw new RuntimeException("minioClient未初始化，需调用init");
        }
    }

    private void transferIO(InputStream is, OutputStream os) throws IOException {
        int len = 8192;
        byte[] buff = new byte[len];
        while ((len = is.read(buff)) > 0) {
            os.write(buff, 0, len);
        }
        os.flush();
    }

    public Object throwMinioException(Exception e) throws MinioServiceException {
        if (e instanceof ErrorResponseException) {
            throw new MinioServiceException("执行请求失败", e);
        } else if (e instanceof InsufficientDataException) {
            throw new MinioServiceException("非法的存储桶名称", e);
        } else if (e instanceof InternalException) {
            throw new MinioServiceException("minio服务内部异常", e);
        } else if (e instanceof InvalidKeyException) {
            throw new MinioServiceException("无效密钥", e);
        } else if (e instanceof InvalidResponseException) {
            throw new MinioServiceException("响应异常", e);
        } else if (e instanceof IOException) {
            throw new MinioServiceException("连接异常", e);
        } else if (e instanceof NoSuchAlgorithmException) {
            throw new MinioServiceException("加密算法异常", e);
        } else if (e instanceof ServerException) {
            throw new MinioServiceException("minio服务内部异常", e);
        } else if (e instanceof XmlParserException) {
            throw new MinioServiceException("解析返回的XML异常", e);
        } else if (e instanceof BucketPolicyTooLargeException) {
            throw new MinioServiceException("存储桶策略过长", e);
        } else {
            throw new MinioServiceException(e);
        }
    }


}
