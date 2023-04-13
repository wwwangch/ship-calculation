package com.iscas.biz.annotation.api;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**

 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/29 9:08
 * @since jdk1.8
 */
@RestController
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiV1RestController {
}
