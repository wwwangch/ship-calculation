package com.iscas.base.biz.autoconfigure.auth;

import com.iscas.base.biz.util.JWTUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * token配置表
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/17 8:31
 * @since jdk1.8
 */
@Data
@ConfigurationProperties(prefix = "token")
@Component
public class TokenProps {

    /**token过期时间(分钟)*/
    @SuppressWarnings("UnusedAssignment")
    private Duration expire = Duration.ofMinutes(1440);

    /** token保存在cookie的时间(毫秒)*/
    @SuppressWarnings("UnusedAssignment")
    private int cookieExpire = -1;

    /**是否将token存入cookie*/
    private boolean cookieStore;

    /**
     * token生成方式
     * */
    private JWTUtils.AlgorithmType creatorMode;

//    @ConstructorBinding
//    public TokenProps(@DurationUnit(ChronoUnit.MINUTES) @DefaultValue("1440m") Duration expire,
//                      @DefaultValue("-1") int cookieExpire, @DefaultValue("true") boolean cookieStore,
//                      @DefaultValue("hmac256") String creatorMode) {
//        this.expire = expire;
//        this.cookieExpire = cookieExpire;
//        this.cookieStore = cookieStore;
//        this.creatorMode = JWTUtils.AlgorithmType.getEnum(creatorMode);
//    }

}