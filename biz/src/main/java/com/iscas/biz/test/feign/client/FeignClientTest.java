package com.iscas.biz.test.feign.client;


import cn.hutool.core.io.IoUtil;
import com.iscas.biz.test.feign.interceptor.FeignRequestInterceptor;
import com.iscas.biz.test.feign.remote.RemoteFeignController;
import com.iscas.templet.common.ResponseEntity;
import feign.Feign;
import feign.Request;
import feign.Response;
import feign.Retryer;
import feign.form.FormData;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class FeignClientTest {
    public static void main(String[] args) throws IOException {
        FeignApi feignApi = Feign.builder()
                .requestInterceptor(new FeignRequestInterceptor())
                .encoder(new FormEncoder(new JacksonEncoder()))
                .decoder(new JacksonDecoder())
//                .decoder(new StringDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(FeignApi.class, "http://localhost:7901/demo");
        ResponseEntity res1 = feignApi.remoteT1("zhansan");
        System.out.println(res1);
        ResponseEntity res2 = feignApi.remoteT2("zhansan");
        System.out.println(res2);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        ResponseEntity res3 = feignApi.remoteT3(map);
        System.out.println(res3);
        ResponseEntity res4 = feignApi.remoteT4(map);
        System.out.println(res4);
        ResponseEntity res5 = feignApi.remoteT5(map);
        System.out.println(res5);
        RemoteFeignController.RetrofitTestModel model = new RemoteFeignController.RetrofitTestModel();
        model.setName("zhangsan");
        ResponseEntity res6 = feignApi.remoteT6(model);
        System.out.println(res6);
        ResponseEntity res7 = feignApi.remoteT7("张三");
        System.out.println(res7);
        ResponseEntity res10 = feignApi.remoteT10(new File("D:/h2.mv.db"));
        System.out.println(res10);
        FormData formData = FormData.builder()
                .data(Files.readAllBytes(new File("D:/h2.mv.db").toPath()))
                .contentType("image/png")
                .fileName("h2.mv.db")
                .build();
        ResponseEntity res10_2 = feignApi.remoteT10_2(formData);
        System.out.println(res10_2);

        ResponseEntity res11 = feignApi.remoteT11(new HashMap<String, File>(){{
            put("file1", new File("D:/aaa.txt"));
            put("file2", new File("D:/h2.mv.db"));
        }});
        System.out.println(res11);

        Response response = feignApi.remoteT12();
        System.out.println(response.body());
    }


}