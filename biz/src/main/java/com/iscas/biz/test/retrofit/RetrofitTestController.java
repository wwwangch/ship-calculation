//package com.iscas.biz.test.retrofit;
//
//import com.iscas.templet.common.ResponseEntity;
//import com.rabbitmq.client.GetResponse;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import okhttp3.ResponseBody;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import retrofit2.Call;
//import retrofit2.Response;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/8/8 20:58
// * @since jdk1.8
// */
//@RestController
//@RequestMapping("/test/retrofit")
//public class RetrofitTestController {
//    @Autowired
//    private RetrofitApi retrofitApi;
//    @GetMapping("t1")
//    public ResponseEntity t1() {
//        return retrofitApi.t1("quanwen");
//    }
//
//    @GetMapping("t2")
//    public ResponseEntity t2() {
//        return retrofitApi.t2("quanwen");
//    }
//
//    @GetMapping("t3")
//    public ResponseEntity t3() {
//        Map<String, Object> map = new HashMap<String, Object>(){{
//            put("name", "quanwen");
//        }};
//        return retrofitApi.t3(map);
//    }
//
//    @GetMapping("t4")
//    public ResponseEntity t4() {
//        Map<String, Object> map = new HashMap<String, Object>(){{
//            put("name", "quanwen");
//        }};
//        return retrofitApi.t4(map);
//    }
//
//    @GetMapping("t5")
//    public ResponseEntity t5() {
//        Map<String, Object> map = new HashMap<String, Object>(){{
//            put("name", "quanwen");
//        }};
//        return retrofitApi.t5(map);
//    }
//
//    @GetMapping("t6")
//    public ResponseEntity t6() {
//        Map<String, Object> map = new HashMap<String, Object>(){{
//            put("name", "quanwen");
//        }};
//        return retrofitApi.t6(map);
//    }
//
//    @GetMapping("t7")
//    public ResponseEntity t7() {
//        return retrofitApi.t7("quanwen");
//    }
//
//    @GetMapping("t8")
//    public ResponseEntity t8() {
//        return retrofitApi.t8("quanwen");
//    }
//
//    @GetMapping("t9")
//    public ResponseEntity t9() {
//        return retrofitApi.t9();
//    }
//
//    @GetMapping("t10")
//    public ResponseEntity t10() {
//        File file = new File("D:/test-sp/aaa.html");
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html"), file);
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file1", file.getName(), requestBody);
//        return retrofitApi.t10(filePart);
//    }
//
//    @GetMapping("t11")
//    public ResponseEntity t11() {
//        File file1 = new File("D:/test-sp/aaa.html");
//        File file2 = new File("D:/test-sp/post_download_file.html");
//        RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/html"), file1);
//        MultipartBody.Part filePart1 = MultipartBody.Part.createFormData("file1", file1.getName(), requestBody1);
//        RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/html"), file2);
//        MultipartBody.Part filePart2 = MultipartBody.Part.createFormData("file2", file2.getName(), requestBody2);
//        return retrofitApi.t11(Arrays.asList(filePart1, filePart2));
//    }
//
////    @GetMapping("t11_2")
////    public ResponseEntity t11_2() {
////        File file1 = new File("D:/test-sp/aaa.html");
////        File file2 = new File("D:/test-sp/post_download_file.html");
////        RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/html"), file1);
////        MultipartBody.Part filePart1 = MultipartBody.Part.createFormData("file1", file1.getName(), requestBody1);
////        RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/html"), file2);
////        MultipartBody.Part filePart2 = MultipartBody.Part.createFormData("file2", file2.getName(), requestBody2);
////
////        return retrofitApi.t11_2(new HashMap<String, MultipartBody.Part>(){{
////            put("file1", filePart1);
////            put("file2", filePart2);
////        }
////        });
////    }
//
//    @GetMapping("t12")
//    public ResponseEntity t12() throws IOException {
//        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
//        Call<ResponseBody> responseBodyCall = retrofitApi.t12();
//        Response<ResponseBody> res = responseBodyCall.execute();
//        ResponseBody body = res.body();
//        return responseEntity;
//    }
//
//}
