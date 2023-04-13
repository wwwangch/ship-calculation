package com.iscas.common.harbor.tools.client;//package com.iscas.cmi.service;


import okhttp3.*;
import okio.BufferedSink;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuquanwen
 * @Description:
 * @Date: 2018/3/19 15:49
 * @Modified:
 **/
public class OkHttpCustomClient {
    private volatile OkHttpClient client;
    private OkHttpProps okHttpConfig;


    //    private final  Gson gson = new Gson();
    //    private static File file = new File("F:/general");
//    private static final long cacheSize = 1024 * 1024 * 20;// 缓存文件最大限制大小20M
//    private static String cacheDirectory = "F:/okHttpCache";
//    private static Cache cache = new Cache(new File(cacheDirectory), cacheSize);  //
    public OkHttpCustomClient(OkHttpProps okHttpConfig) {
        this.okHttpConfig = okHttpConfig;
        if (client == null) {
            synchronized (OkHttpCustomClient.class) {
                if (client == null) {
                    client = new OkHttpClient().newBuilder()
                            .readTimeout(okHttpConfig.getReadTimeout(), TimeUnit.MILLISECONDS)
                            .writeTimeout(okHttpConfig.getWriteTimeout(), TimeUnit.MILLISECONDS)
                            .connectTimeout(okHttpConfig.getConnectTimeout(), TimeUnit.MILLISECONDS)
                            .connectionPool(new ConnectionPool(okHttpConfig.getMaxIdleConnection(), okHttpConfig.getKeepAliveDuration(), TimeUnit.MINUTES))
                            .retryOnConnectionFailure(true)
//                            .cache(cache)
                            .build();
                }
            }
        }
    }


    public static class UploadInfo<T extends Object> {
        /**
         * 文件、文件路径、InputStream、字节数组
         * */
        private T data;

        /**
         * 文件名
         * */
        private String fileName;

        /**
         * 文件上传的表单key
         * */
        private String formKey;

        public UploadInfo() {}

        public UploadInfo(T data, String fileName, String formKey) {
            this.data = data;
            this.fileName = fileName;
            this.formKey = formKey;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFormKey() {
            return formKey;
        }

        public void setFormKey(String formKey) {
            this.formKey = formKey;
        }
    }


    /**
     * @description: 获取删除的通用Call
     * @date: 2018/3/19 9:55
     * @param: url 访问URL
     * @param: headerMap header键值对
     * @return: okhttp3.Call
     */
    private Call baseDeleteCall(String url, Map<String, String> headerMap) {
        Request.Builder requestBuilder = requestBuilderAddHeader(headerMap, url);
        requestBuilder.delete();
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 获取通用的GET请求Call
     * @date: 2018/3/19 9:56
     * @param: url 访问URL
     * @param: headerMap header键值对
     * @return: okhttp3.Call
     */
    private Call baseGetCall(String url, Map<String, String> headerMap) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = builder.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 获取POST发送请求参数的call
     * @date: 2018/3/19 9:57
     * @param: url 访问URL
     * @param: headerMap header键值对
     * @param: mapParams 请求参数键值对
     * @return: okhttp3.Call
     */
    private Call basePostCall1(String url, Map<String, String> headerMap, Map<String, String> mapParams) {
        FormBody.Builder builder = new FormBody.Builder();
        if (mapParams != null) {
            for (String key : mapParams.keySet()) {
                builder.add(key, mapParams.get(key));
            }
        }
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.post(builder.build());
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 获取post请求发送json串的call
     * @date: 2018/3/19 9:58
     * @param: url 请求URL
     * @param: headerMap header键值对
     * @param: jsonParams json请求串
     * @return: okhttp3.Call
     */
    private Call basePostCall2(String url, Map<String, String> headerMap, String jsonParams) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.post(body);
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 获取file上传请求的call
     * @date: 2018/3/19 10:00
     * @param: url 请求URL
     * @param: uploadInfos  文件的信息， {@link UploadInfo}
     * @param: fileMap 多个文件的Map,key为String类型(文件路径)或者byte[]类型(文件字节数组)，value为文件名称
     * @param: params 长传文件时附带请求参数键值对
     * @return: okhttp3.Call
     */
    private Call baseFileCall(String url, Map<String, String> headerMap, List<UploadInfo> uploadInfos,
                              Map<String, String> params) {
        //创建文件参数
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (uploadInfos != null) {
            for (UploadInfo uploadInfo: uploadInfos) {
                Object data = uploadInfo.getData();
                String fileName = uploadInfo.getFileName();
                String formKey = uploadInfo.getFormKey();
                //判断类型
                if (data instanceof String) {
                    //文件的路径
                    MediaType mediaType = MediaType.parse(judgeType((String) data));
                    builder.addFormDataPart(formKey, fileName,
                            RequestBody.create(mediaType, new File((String) data)));
                } else if (data instanceof File) {
                    //文件类型
                    MediaType mediaType = MediaType.parse("application/octet-stream");
                    builder.addFormDataPart(formKey, fileName,
                            RequestBody.create(mediaType, (File) data));
                } else if (data instanceof byte[]) {
                    //文件字节流
                    MediaType mediaType = MediaType.parse("application/octet-stream");
                    builder.addFormDataPart(formKey, fileName,
                            RequestBody.create(mediaType, (byte[]) data));
                } else if (data instanceof InputStream || InputStream.class.isAssignableFrom(((Object) data).getClass())) {
                    //InputStream 的实现类
                    InputStream is = (InputStream) data;
                    RequestBody requestBody = new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            return MediaType.parse("application/octet-stream");
                        }

                        @Override
                        public void writeTo(BufferedSink bufferedSink) throws IOException {
                            OutputStream os = bufferedSink.outputStream();
                            byte[] buff = new byte[4096];
                            int len = -1;
                            while ((len= is.read(buff)) > 0) {
                                os.write(buff, 0, len);
                            }
                        }
                    };
                    builder.addFormDataPart(formKey, fileName, requestBody);

                } else {
                    throw new IllegalArgumentException("the key of fileMap must be String、File、InputStream or byte[]!");
                }
            }
        }
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        //发出请求参数
        Request.Builder builder1 = new Request.Builder();
        builder1.url(url);
        builder1.post(builder.build());
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder1.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = builder1.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 获取PUT发送请求参数的call
     * @date: 2018/3/19 9:57
     * @param: url 访问URL
     * @param: headerMap header键值对
     * @param: mapParams 请求参数键值对
     * @return: okhttp3.Call
     */
    private Call basePutCall1(String url, Map<String, String> headerMap, Map<String, String> mapParams) {
        FormBody.Builder builder = new FormBody.Builder();
        if (mapParams != null) {
            for (String key : mapParams.keySet()) {
                builder.add(key, mapParams.get(key));
            }
        }
        Request.Builder requestBuilder = requestBuilderAddHeader(headerMap, url);
        requestBuilder.put(builder.build());
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 获取put请求发送json串的call
     * @date: 2018/3/19 9:58
     * @param: url 请求URL
     * @param: headerMap header键值对
     * @param: jsonParams json请求串
     * @return: okhttp3.Call
     */
    private Call basePutCall2(String url, Map<String, String> headerMap, String jsonParams) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request.Builder requestBuilder = requestBuilderAddHeader(headerMap, url);
        requestBuilder.put(body);
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        return call;
    }

    /**
     * @description: 转化JSON为对象
     * @date: 2018/3/19 10:05
     * @param: response JSON字符串
     * @param: fromJsonHandler JSON字符串处理接口
     * @return: java.lang.Object
     */
    public Object fromJson(String result, FromJsonHandler fromJsonHandler) {
        return fromJsonHandler.fromJson(result);
    }

    //以下JSON转化依赖gson，暂时先注释掉
//    /**
//     *@description: 转化JSON为对象
//     *@date: 2018/3/19 10:06
//     *@param: response JSON字符串
//     *@param: tClass 返回对象类型
//     *@return: T 返回对象T
//     */
//    public  <T> T fromJson(String response, Class<T> tClass){
//        return gson.fromJson(response, tClass);
//    }
//
//    /**
//     *@description: 转化JSON为对象
//     *@date: 2018/3/19 10:07
//     *@param: response 返回对象类型
//     *@param: type Typetoken定义的typerefrence
//     *@return: T 返回对象T
//     */
//    public  <T> T fromJson(String response, TypeToken<T> type){
//        return gson.fromJson(response, type.getType());
//    }


    /**
     * @Author: zhuquanwen
     * @description: 同步GET请求
     * @date: 2018/3/19 10:17
     * @param: url 访问URL
     * @param: headerMap header键值对
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doGet(String url, Map<String, String> headerMap) throws IOException {
        Call call = baseGetCall(url, headerMap);
        return call.execute().body().string();
    }

    /**
     * @author: zhuquanwen
     * @description: 同步GET请求
     * @date: 2018/3/19 10:40
     * @param: url
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doGet(String url) throws IOException {
        return doGet(url, (Map<String, String>) null);
    }


    /**
     * @author: zhuquanwen
     * @description: 异步GET请求
     * @date: 2018/3/19 10:41
     * @param: url 请求URL
     * @param: headerMap header键值对
     * @param: callback okhttp异步回调
     * @exception: IOException IO异常
     * @return: void
     */
    public void doGetAsyn(String url, Map<String, String> headerMap, Callback callback) throws IOException {
        Call call = baseGetCall(url, headerMap);
        call.enqueue(callback);
        ;
    }

    /**
     * @author: zhuquanwen
     * @description: 异步GET请求
     * @date: 2018/3/19 10:41
     * @param: url 请求URL
     * @param: callback okhttp异步回调
     * @exception: IOException IO异常
     * @return: void
     */
    public void doGetAsyn(String url, Callback callback) throws IOException {
        doGetAsyn(url, (Map<String, String>) null, callback);
    }

    /**
     * @param url       请求URL
     * @param headerMap header键值对
     * @param mapParams 请求参数键值对
     * @author: zhuquanwen
     * @description: 同步POST请求
     * @date: 2018/3/19 10:45
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPost(String url, Map<String, String> headerMap, Map<String, String> mapParams) throws IOException {
        Call call = basePostCall1(url, headerMap, mapParams);
        return call.execute().body().string();
    }

    /**
     * @param url       请求URL
     * @param mapParams 请求参数键值对
     * @author: zhuquanwen
     * @description: 同步POST请求
     * @date: 2018/3/19 10:45
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPost(String url, Map<String, String> mapParams) throws IOException {
        return doPost(url, (Map<String, String>) null, mapParams);
    }

    /**
     * @param url        请求URL
     * @param headerMap  header键值对
     * @param jsonParams 请求JSON串
     * @author: zhuquanwen
     * @description: 同步POST请求
     * @date: 2018/3/19 10:45
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPost(String url, Map<String, String> headerMap, String jsonParams) throws IOException {
        Call call = basePostCall2(url, headerMap, jsonParams);
        return call.execute().body().string();
    }

    /**
     * @param url        请求URL
     * @param jsonParams 请求JSON串
     * @author: zhuquanwen
     * @description: 同步POST请求
     * @date: 2018/3/19 10:45
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPost(String url, String jsonParams) throws IOException {
        return doPost(url, (Map<String, String>) null, jsonParams);
    }

    /**
     * @param url       请求URL
     * @param headerMap header键值对
     * @param mapParams 请求键值对
     * @param callback  okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步POST请求
     * @date: 2018/3/19 10:47
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPostAsyn(String url, Map<String, String> headerMap, Map<String, String> mapParams,
                           Callback callback) throws IOException {
        Call call = basePostCall1(url, headerMap, mapParams);
        call.enqueue(callback);
    }

    /**
     * @param url       请求URL
     * @param mapParams 请求键值对
     * @param callback  okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步POST请求
     * @date: 2018/3/19 10:47
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPostAsyn(String url, Map<String, String> mapParams, Callback callback) throws IOException {
        doPostAsyn(url, (Map<String, String>) null, mapParams, callback);
    }

    /**
     * @param url        请求URL
     * @param headerMap  header键值对
     * @param jsonParams 请求JSON串
     * @param callback   okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步POST请求
     * @date: 2018/3/19 10:47
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPostAsyn(String url, Map<String, String> headerMap, String jsonParams, Callback callback) throws IOException {
        Call call = basePostCall2(url, headerMap, jsonParams);
        call.enqueue(callback);
    }

    /**
     * @param url        请求URL
     * @param jsonParams 请求JSON串
     * @param callback   okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步POST请求
     * @date: 2018/3/19 10:47
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPostAsyn(String url, String jsonParams, Callback callback) throws IOException {
        doPostAsyn(url, (Map<String, String>) null, jsonParams, callback);
    }

    /**
     * @param url       URL请求
     * @param headerMap header请求键值对
     * @param uploadInfos  文件的信息， {@link UploadInfo}
     * @param params    文件上传附带参数键值对
     * @author: zhuquanwen
     * @description: 同步文件上传
     * @date: 2018/3/19 10:49
     * @exception: IOException IO异常
     * @exception: IllegalArgumentException 参数异常
     * @return: java.lang.String
     */
    public String doFile(String url, Map<String, String> headerMap, List<UploadInfo> uploadInfos, Map<String, String> params)
            throws IOException, IllegalArgumentException {
        Call call = baseFileCall(url, headerMap, uploadInfos, params);
        return call.execute().body().string();

    }

    /**
     * @param url     URL请求
     * @param uploadInfos {@link UploadInfo}
     * @param params  文件上传附带参数键值对
     * @author: zhuquanwen
     * @description: 同步文件上传
     * @date: 2018/3/19 10:49
     * @exception: IOException IO异常
     * @exception: IllegalArgumentException 参数异常
     * @return: java.lang.String
     */
    public String doFile(String url, List<UploadInfo> uploadInfos, Map<String, String> params) throws IOException {
        return doFile(url, (Map<String, String>) null, uploadInfos, params);
    }

    /**
     * @param url       URL请求
     * @param headerMap header请求键值对
     * @param uploadInfos {@link UploadInfo}
     * @param params    文件上传附带参数键值对
     * @param callback  okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步文件上传
     * @date: 2018/3/19 10:49
     * @exception: IOException IO异常
     * @exception: IllegalArgumentException 参数异常
     * @return: void
     */
    public void doFileAsyn(String url, Map<String, String> headerMap, List<UploadInfo> uploadInfos,
                           Map<String, String> params, Callback callback) throws IOException, IllegalArgumentException {
        Call call = baseFileCall(url, headerMap, uploadInfos, params);
        call.enqueue(callback);
    }

    /**
     * @param url      URL请求
     * @param uploadInfos {@link UploadInfo}
     * @param params   文件上传附带参数键值对
     * @param callback okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步文件上传
     * @date: 2018/3/19 10:49
     * @exception: IOException IO异常
     * @exception: IllegalArgumentException 参数异常
     * @return: void
     */
    public void doFileAsyn(String url, List<UploadInfo> uploadInfos, Map<String, String> params
            , Callback callback) throws IOException {
        doFileAsyn(url, (Map<String, String>) null, uploadInfos, params, callback);
    }

    /**
     * @param url      URL请求
     * @param fileDir  下载文件夹路径
     * @param fileName 下载文件名称
     * @author: zhuquanwen
     * @description: 同步文件下载
     * @date: 2018/3/19 10:53
     * @exception: InterruptedException 线程打断异常
     * @return: boolean
     */
    public boolean downFile(String url, final String fileDir, final String fileName) throws InterruptedException {
        return downFile(url, null, fileDir, fileName);
    }

    /**
     * @param url       URL请求
     * @param headerMap header键值对
     * @param fileDir   下载文件夹路径
     * @param fileName  下载文件名称
     * @author: zhuquanwen
     * @description: 同步文件下载
     * @date: 2018/3/19 10:53
     * @exception: InterruptedException 线程打断异常
     * @return: boolean
     */
    public boolean downFile(String url, Map<String, String> headerMap,
                            final String fileDir, final String fileName) throws InterruptedException {
        Boolean[] result = new Boolean[1];
        result[0] = null;
        Request.Builder requestBuilder = requestBuilderAddHeader(headerMap, url);
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result[0] = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] buf = new byte[2048];
                int len = 0;
                File file = new File(fileDir, fileName);
                try (
                        InputStream is = response.body().byteStream();
                        FileOutputStream fos = new FileOutputStream(file);
                ) {
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    result[0] = true;
                }
            }
        });
        while (true) {
            if (result[0] != null) {
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(50);
            }
        }
        return result[0];
    }

    /**
     * @author: zhuquanwen
     * @description: 同步文件下载
     * @date: 2018/3/19 10:55
     * @param: url 请求URL
     * @exception: InterruptedException 线程打断异常
     * @return: java.io.InputStream
     */
    public InputStream downFile(String url) throws InterruptedException {
        return downFile(url, null);
    }

    /**
     * @param url       请求URL
     * @param headerMap header 键值对
     * @author: zhuquanwen
     * @description: 同步文件下载
     * @date: 2018/3/19 10:55
     * @exception: InterruptedException 线程打断异常
     * @return: java.io.InputStream
     */
    public InputStream downFile(String url, Map<String, String> headerMap) throws InterruptedException {
        Boolean[] flag = new Boolean[1];
        flag[0] = null;
        InputStream[] iss = new InputStream[1];
        iss[0] = null;
        Request.Builder requestBuilder = requestBuilderAddHeader(headerMap, url);
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                flag[0] = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                is = response.body().byteStream();
                iss[0] = is;
                flag[0] = true;
            }
        });
        while (true) {
            if (flag[0] != null) {
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(50);
            }
        }
        return iss[0];
    }


    /**
     * @param url       请求URL
     * @param headerMap header 键值对
     * @param callback  okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步文件下载
     * @date: 2018/3/19 10:56
     * @return: void
     */
    public void downFileAsyn(String url, Map<String, String> headerMap, Callback callback) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    /**
     * @param url      请求URL
     * @param callback okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步文件下载
     * @date: 2018/3/19 10:56
     * @return: void
     */
    public void downFileAsyn(String url, Callback callback) {
        downFileAsyn(url, null, callback);
    }

    /**
     * @param url       请求URL
     * @param headerMap header键值对
     * @param mapParams 请求参数键值对
     * @author: zhuquanwen
     * @description: 同步PUT请求
     * @date: 2018/3/19 10:57
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPut(String url, Map<String, String> headerMap, Map<String, String> mapParams) throws IOException {
        Call call = basePutCall1(url, headerMap, mapParams);
        return call.execute().body().string();
    }

    /**
     * @param url       请求URL
     * @param mapParams 请求参数键值对
     * @author: zhuquanwen
     * @description: 同步PUT请求
     * @date: 2018/3/19 10:57
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPut(String url, Map<String, String> mapParams) throws IOException {
        return doPut(url, (Map<String, String>) null, mapParams);
    }

    /**
     * @param url        请求URL
     * @param headerMap  header键值对
     * @param jsonParams 请求json字符串
     * @author: zhuquanwen
     * @description: 同步PUT请求
     * @date: 2018/3/19 10:57
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPut(String url, Map<String, String> headerMap, String jsonParams) throws IOException {
        Call call = basePutCall2(url, headerMap, jsonParams);
        return call.execute().body().string();
    }

    /**
     * @param url        请求URL
     * @param jsonParams 请求json字符串
     * @author: zhuquanwen
     * @description: 同步PUT请求
     * @date: 2018/3/19 10:57
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doPut(String url, String jsonParams) throws IOException {
        return doPut(url, (Map<String, String>) null, jsonParams);
    }

    /**
     * @param url       请求URL
     * @param headerMap header键值对
     * @param mapParams 请求参数键值对
     * @param callback  okhttp异步请求回调
     * @author: zhuquanwen
     * @description: 异步put请求
     * @date: 2018/3/19 10:59
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPutAsyn(String url, Map<String, String> headerMap, Map<String, String> mapParams, Callback callback) throws IOException {
        Call call = basePutCall1(url, headerMap, mapParams);
        call.enqueue(callback);
    }

    /**
     * @param url       请求URL
     * @param mapParams 请求参数键值对
     * @param callback  okhttp异步请求回调
     * @author: zhuquanwen
     * @description: 异步put请求
     * @date: 2018/3/19 10:59
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPutAsyn(String url, Map<String, String> mapParams, Callback callback) throws IOException {
        doPutAsyn(url, (Map<String, String>) null, mapParams, callback);
    }

    /**
     * @param url        请求URL
     * @param headerMap  header键值对
     * @param jsonParams 请求json字符串
     * @param callback   okhttp异步请求回调
     * @author: zhuquanwen
     * @description: 异步put请求
     * @date: 2018/3/19 10:59
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPutAsyn(String url, Map<String, String> headerMap, String jsonParams, Callback callback) throws IOException {
        Call call = basePutCall2(url, headerMap, jsonParams);
        call.enqueue(callback);
    }

    /**
     * @param url        请求URL
     * @param jsonParams 请求JSON字符串
     * @param callback   okhttp异步请求回调
     * @author: zhuquanwen
     * @description: 异步put请求
     * @date: 2018/3/19 10:59
     * @exception: IOException IO异常
     * @return: void
     */
    public void doPutAsyn(String url, String jsonParams, Callback callback) throws IOException {
        doPutAsyn(url, (Map<String, String>) null, jsonParams, callback);
    }


    /**
     * @param url       请求URL
     * @param headerMap header 请求键值对
     * @author: zhuquanwen
     * @description: 同步delete请求
     * @date: 2018/3/19 11:01
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doDelete(String url, Map<String, String> headerMap) throws IOException {

        Call call = baseDeleteCall(url, headerMap);
        return call.execute().body().string();
    }

    /**
     * @param url 请求URL
     * @author: zhuquanwen
     * @description: 同步delete请求
     * @date: 2018/3/19 11:01
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doDelete(String url) throws IOException {
        return doDelete(url, (Map<String, String>) null);
    }

    /**
     * @param url       请求URL
     * @param headerMap header键值对
     * @param callback  okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步DELETE请求
     * @date: 2018/3/19 11:03
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doDeleteAsyn(String url, Map<String, String> headerMap, Callback callback) throws IOException {

        Call call = baseDeleteCall(url, headerMap);
        return call.execute().body().string();
    }

    /**
     * @param url      请求URL
     * @param callback okhttp异步回调
     * @author: zhuquanwen
     * @description: 异步DELETE请求
     * @date: 2018/3/19 11:03
     * @exception: IOException IO异常
     * @return: java.lang.String
     */
    public String doDeleteAsyn(String url, Callback callback) throws IOException {
        return doDeleteAsyn(url, (Map<String, String>) null, callback);
    }


    private Request.Builder requestBuilderAddHeader(Map<String, String> headerMap, String url) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return requestBuilder;
    }

    private String judgeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    @FunctionalInterface
    public interface FromJsonHandler {
        Object fromJson(String json);
    }
}
