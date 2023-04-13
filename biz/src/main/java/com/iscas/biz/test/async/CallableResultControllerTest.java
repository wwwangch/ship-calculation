package com.iscas.biz.test.async;

import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 返回Callable 异步返回数据
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 20:41
 * @since jdk1.8
 */
@RestController
@RequestMapping("/callable/test")
public class CallableResultControllerTest extends BaseController {

    @GetMapping
    public Callable<ResponseEntity> callable() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return () -> new ResponseEntity<>().setValue("This is callale test");
    }
}
