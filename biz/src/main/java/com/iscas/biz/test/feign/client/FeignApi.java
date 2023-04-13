package com.iscas.biz.test.feign.client;


import com.iscas.biz.test.feign.remote.RemoteFeignController;
import com.iscas.templet.common.ResponseEntity;
import feign.*;
import feign.form.FormData;

import java.io.File;
import java.util.Map;

public interface FeignApi {
    /**url中参数 ?name=xxx*/
    @RequestLine("GET /test/feign/remote/t1?name={name}")
    ResponseEntity remoteT1(@Param("name") String username);

    /**url路径中参数*/
    @RequestLine("DELETE /test/feign/remote/t2/{name}")
    ResponseEntity remoteT2(@Param("name") String name);

    /**使用请求体传送json*/
    @RequestLine("POST /test/feign/remote/t3")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    ResponseEntity remoteT3(Map<String, Object> params);

    /**使用请求体传送json，使用PUT请求*/
    @RequestLine("PUT /test/feign/remote/t4")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    ResponseEntity remoteT4(Map<String, Object> params);

    /**多个url参数使用map传递*/
    @RequestLine("POST /test/feign/remote/t5")
    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json"})
    ResponseEntity remoteT5(@QueryMap Map<String, Object> params);

    /**form表单参数，以对象传递*/
    @RequestLine("POST /test/feign/remote/t6")
    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json"})
    ResponseEntity<RemoteFeignController.RetrofitTestModel> remoteT6(RemoteFeignController.RetrofitTestModel model);

    /**form表单传送数据，多个参数一个一个传递*/
    @RequestLine("POST /test/feign/remote/t7")
    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json"})
    ResponseEntity remoteT7(@Param("name") String name);

    /**单文件上传*/
    @RequestLine("POST /test/feign/remote/t10")
    @Headers({"Content-Type: multipart/form-data","Accept: application/json"})
    ResponseEntity remoteT10(@Param("file1") File file);

    /**单文件上传*/
    @RequestLine("POST /test/feign/remote/t10")
    @Headers({"Content-Type: multipart/form-data","Accept: application/json"})
    ResponseEntity remoteT10_2(@Param("file1") FormData data);

    /**多文件上传*/
    @RequestLine("POST /test/feign/remote/t11")
    @Headers({"Content-Type: multipart/form-data","Accept: application/json"})
    ResponseEntity remoteT11(Map<String, ?> data);


    /**文件下载*/
    @RequestLine("GET /test/feign/remote/t12")
    Response remoteT12();

}