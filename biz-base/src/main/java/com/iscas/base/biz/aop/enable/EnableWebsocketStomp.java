package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.stomp.WsImportSelector;
import com.iscas.base.biz.config.stomp.WsPushType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启websoket支持
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 14:24
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WsImportSelector.class)
public @interface EnableWebsocketStomp {
    WsPushType pushType() default WsPushType.SIMPLE;
}
