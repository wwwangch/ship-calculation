package com.iscas.base.biz.service.openauth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;

import java.io.OutputStream;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/22 13:31
 * @since jdk1.8
 */
public class OpenAuthApi extends DefaultApi20 {
    private final String accessTokenUri;
    private final String authorizationBaseUri;
    private final String revokeTokenUri;

    public OpenAuthApi(String accessTokenUri, String authorizationBaseUri, String revokeTokenUri) {
        this.accessTokenUri = accessTokenUri;
        this.authorizationBaseUri = authorizationBaseUri;
        this.revokeTokenUri = revokeTokenUri;
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenJsonExtractor.instance();
    }

    /**
     * 添加appId跟appKey采用在http的请求body中添加
     *
     * @return ClientAuthentication
     */
    @Override
    public ClientAuthentication getClientAuthentication() {
        return RequestBodyAuthenticationScheme.instance();
    }

    /**
     * 授权的token在http请求的body中传递
     *
     * @return BearerSignature
     */
    @Override
    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public String getRevokeTokenEndpoint() {
        return revokeTokenUri;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return accessTokenUri;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return authorizationBaseUri;
    }


    @Override
    public OAuth20Service createService(String apiKey, String apiSecret, String callback, String defaultScope,
                                        String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
                                        HttpClient httpClient) {
        return new CustomAuthService(this, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent, httpClientConfig, httpClient);
    }

}
