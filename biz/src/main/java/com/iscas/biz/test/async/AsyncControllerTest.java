package com.iscas.biz.test.async;

import com.iscas.templet.common.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 测试异步回调
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/30 20:57
 * @since jdk1.8
 */
@RestController
@RequestMapping("/async/test")
public class AsyncControllerTest {

    @Async("asyncExecutor")
    public Future<String> testAsync1() {
        // do something ...
        return new AsyncResult<>("lalalala");
    }

    @Async("asyncExecutor")
    public Future<String> testAsync2() {
        // do something ...
        return new AsyncResult<>("yayayaya");
    }

    @GetMapping
    public ResponseEntity test() throws ExecutionException, InterruptedException {
        Future<String> stringFuture1 = testAsync1();
        Future<String> stringFuture2 = testAsync2();
        System.out.println(stringFuture1.get());
        System.out.println(stringFuture2.get());
        return new com.iscas.templet.common.ResponseEntity<>();
    }
}
