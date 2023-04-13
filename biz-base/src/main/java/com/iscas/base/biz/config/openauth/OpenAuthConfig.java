package com.iscas.base.biz.config.openauth;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.iscas.base.biz.service.openauth.OpenAuthApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/22 13:26
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class OpenAuthConfig {

    @Value("${security.oauth2.open.auth.client.clientId:}")
    private String clientId;
    @Value("${security.oauth2.open.auth.client.clientSecret:}")
    private String clientSecret;
    @Value("${security.oauth2.open.auth.client.accessTokenUri:}")
    private String accessTokenUri;
    @Value("${security.oauth2.open.auth.client.userAuthorizationUri:}")
    private String userAuthorizationUri;

    @Value("${security.oauth2.open.auth.client.revokeTokenUri:}")
    private String revokeTokenUri;

    @Value("${open.auth.oAuth2.callbackUrl:}")
    private String callbackUrl;

    /**
     * 为防止CSRF跨站攻击，每次请求STATE的值应该不同，可以放入Session！
     * 由于都是localhost测试，所以session无法保持，用一个固定值。
     */
    public static final String STATE = "secret-rensanning";
    private static final String SCOPE = "GOODS+ORDER";


    @Bean
    OpenAuthApi openAuthApi(){
        return new OpenAuthApi(accessTokenUri, userAuthorizationUri, revokeTokenUri);
    }

    @Bean
    OAuth20Service oAuth20Service(){
        return new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope(SCOPE)
                .callback(callbackUrl)
                .build(openAuthApi());
    }
}
