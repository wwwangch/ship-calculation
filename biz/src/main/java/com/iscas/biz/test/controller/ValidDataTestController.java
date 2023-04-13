package com.iscas.biz.test.controller;

import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/3 15:53
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/valid")
@Validated
public class ValidDataTestController extends BaseController {

    @GetMapping("/t1")
    public ResponseEntity test1(@RequestParam @Max(value = 2, message = "参数错误") String str) {
        return getResponse().setValue(str);
    }

    @PostMapping("/t2")
    public ResponseEntity test2(@Valid @RequestBody TestBean testBean) {
        return getResponse().setValue(testBean);
    }

    @Data
    public static class TestBean {
        @Size(min = 2, max = 4, message = "name长度必须介于2-4之间")
        private String name;
    }

}
