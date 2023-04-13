//package com.iscas.biz.test.retrofit;
//
//import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
//import com.github.lianjiatech.retrofit.spring.boot.interceptor.Intercept;
//import com.iscas.templet.common.ResponseEntity;
//import okhttp3.MultipartBody;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.http.*;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 参考 https://blog.csdn.net/why_still_confused/article/details
// *
// *  /108041657
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/8/8 18:25
// * @since jdk1.8
// */
//@RetrofitClient(baseUrl = "http://localhost:7901/demo")
//@Intercept(handler = SimpleInterceptor.class, include = {"/demo/test/**"}) //添加拦截器
//@Sign(accessKeyId = "xxxx", accessKeySecret = "yyyyy", exclude = {"/demo/test/xxxx"})
//public interface RetrofitApi {
//
//    /**url中参数 ?name=xxx*/
//    @GET("test/retrofit/remote/t1")
//    ResponseEntity t1(@Query("name") String name);
//
//    /**路径中传参 /{}*/
//    @DELETE("test/retrofit/remote/t2/{name}")
//    ResponseEntity t2(@Path("name") String name);
//
//    /**使用请求体传送json*/
//    @POST("test/retrofit/remote/t3")
//    ResponseEntity t3(@Body Map<String, Object> params);
//
//    /**put请求使用请求体传送json*/
//    @PUT("test/retrofit/remote/t4")
//    ResponseEntity t4(@Body Map<String, Object> params);
//
//    /**多个url参数使用map传递*/
//    @POST("test/retrofit/remote/t5")
//    ResponseEntity t5(@QueryMap Map<String, Object> params);
//
//    /**form表单传送数据，多个参数使用map传递*/
//    @FormUrlEncoded
//    @POST("test/retrofit/remote/t6")
//    ResponseEntity t6(@FieldMap Map<String, Object> model);
//
//    /**form表单传送数据，多个参数一个一个传递*/
//    @FormUrlEncoded
//    @POST("test/retrofit/remote/t7")
//    ResponseEntity t7(@Field("name") String name);
//
//    /**header传送数据，多个参数一个一个传递*/
//    @POST("test/retrofit/remote/t8")
//    ResponseEntity t8(@Header("name") String name);
//
//    /**header传送数据，多个参数一起传递*/
//    @POST("test/retrofit/remote/t9")
//    @Headers({"name:quanwen", "token:wgwegwe"})
//    ResponseEntity t9();
//
//    /**单个文件上传*/
//    @POST("test/retrofit/remote/t10")
//    @Multipart
//    ResponseEntity t10(@Part MultipartBody.Part filePart);
//
//    /**多个文件上传*/
//    @POST("test/retrofit/remote/t11")
//    @Multipart
//    ResponseEntity t11(@Part List<MultipartBody.Part> parts);
//
////    /**多个文件上传*/
////    @POST("test/retrofit/remote/t11")
////    @Multipart
////    ResponseEntity t11_2(@PartMap Map<String, MultipartBody.Part> parts);
//
//    /**文件下载*/
//    @POST("test/retrofit/remote/t12")
//    @Streaming
//    Call<ResponseBody> t12();
//
//}
