package com.iscas.biz.test.async;

import com.iscas.base.biz.service.common.DeferredResultService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 测试DeferredResult
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 21:02
 * @since jdk1.8
 */
@RestController
@RequestMapping("/deferred/result/test")
public class DeferredResultControllerTest extends BaseController {
    @Autowired
    private DeferredResultService deferredResultService;


    /**
     * 模拟一个请求，携带requestMark，并等待RocketMQ的结果
     * */
    @GetMapping("/{requestMark}")
    public DeferredResult<ResponseEntity> request1(@PathVariable String requestMark) {
        DeferredResult<ResponseEntity> deferredResult = new DeferredResult<>(10000L);
        deferredResultService.process(requestMark, deferredResult);
        //直接返回了，这时这个线程释放了，等待rocketmq推送结果，再返回数据给前端
        return deferredResult;
    }

    /**
     * 模拟rocketmq 推送数据
     * */
    @GetMapping("/push/{requestMark}")
    public ResponseEntity push(@PathVariable String requestMark) {
        deferredResultService.setResult(requestMark, new ResponseEntity().setMessage("假设这是rocketmq推送的数据"));
        return getResponse();
    }

}
