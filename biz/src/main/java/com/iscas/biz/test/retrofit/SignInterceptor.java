//package com.iscas.biz.test.retrofit;
//
//import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * 模拟的一个签名的拦截器
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/8/9 13:21
// * @since jdk1.8
// */
//@Component
//public class SignInterceptor extends BasePathMatchInterceptor {
//
//    private String accessKeyId;
//
//    private String accessKeySecret;
//
//    public void setAccessKeyId(String accessKeyId) {
//        this.accessKeyId = accessKeyId;
//    }
//
//    public void setAccessKeySecret(String accessKeySecret) {
//        this.accessKeySecret = accessKeySecret;
//    }
//
//    @Override
//    public Response doIntercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Request newReq = request.newBuilder()
//                .addHeader("accessKeyId", accessKeyId)
//                .addHeader("accessKeySecret", accessKeySecret)
//                .build();
//        return chain.proceed(newReq);
//    }
//}
//
