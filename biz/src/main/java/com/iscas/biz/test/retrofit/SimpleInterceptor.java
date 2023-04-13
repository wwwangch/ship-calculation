//package com.iscas.biz.test.retrofit;
//
//import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
//import okhttp3.Headers;
//import okhttp3.HttpUrl;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/8/9 11:20
// * @since jdk1.8
// */
//@Component
//public class SimpleInterceptor extends BasePathMatchInterceptor {
//    @Override
//    protected Response doIntercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        HttpUrl url = request.url();
//        url = url.newBuilder().addQueryParameter("time", String.valueOf(System.currentTimeMillis()))
//                .build();
//        Headers headers = request.headers();
//        headers = headers.newBuilder().add("add-header", "lalala").build();
//        request = request.newBuilder().headers(headers)
//                .url(url).build();
//        return chain.proceed(request);
//    }
//}
