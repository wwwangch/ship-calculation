///**
// * Licensed to Apereo under one or more contributor license
// * agreements. See the NOTICE file distributed with this work
// * for additional information regarding copyright ownership.
// * Apereo licenses this file to you under the Apache License,
// * Version 2.0 (the "License"); you may not use this file
// * except in compliance with the License.  You may obtain a
// * copy of the License at the following location:
// *
// *   <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
// *
// * Unless required by applicable law or agreed to in writing,
// * software distributed under the License is distributed on an
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// * KIND, either express or implied.  See the License for the
// * specific language governing permissions and limitations
// * under the License.
// */
//package com.iscas.biz.config.cas;
//
//import org.jasig.cas.client.Protocol;
//import org.jasig.cas.client.authentication.*;
//import org.jasig.cas.client.configuration.ConfigurationKeys;
//import org.jasig.cas.client.util.AbstractCasFilter;
//import org.jasig.cas.client.util.CommonUtils;
//import org.jasig.cas.client.util.ReflectUtils;
//import org.jasig.cas.client.validation.Assertion;
//
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 将{@link AuthenticationFilter}类的内容拷贝过来，
// * 做一些改动，因为使用了rest的接口，不进行重定向
// */
//@SuppressWarnings("unused")
//public class CustomCasAuthenticationFilter extends AbstractCasFilter {
//    private boolean isRedirectAfterValidation;
//
//    /**
//     * The URL to the CAS Server login.
//     */
//    private String casServerLoginUrl;
//
//    /**
//     * Whether to send the renew request or not.
//     */
//    private boolean renew = false;
//
//    /**
//     * Whether to send the gateway request or not.
//     */
//    private boolean gateway = false;
//
//    /**
//     * The method used by the CAS server to send the user back to the application.
//     */
//    private String method;
//
//    private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();
//
//    private AuthenticationRedirectStrategy authenticationRedirectStrategy = new DefaultAuthenticationRedirectStrategy();
//
//    private UrlPatternMatcherStrategy ignoreUrlPatternMatcherStrategyClass = null;
//
//    private static final Map<String, Class<? extends UrlPatternMatcherStrategy>> PATTERN_MATCHER_TYPES =
//            new HashMap<>();
//
//    static {
//        PATTERN_MATCHER_TYPES.put("CONTAINS", ContainsPatternUrlPatternMatcherStrategy.class);
//        PATTERN_MATCHER_TYPES.put("REGEX", RegexUrlPatternMatcherStrategy.class);
//        PATTERN_MATCHER_TYPES.put("FULL_REGEX", EntireRegionRegexUrlPatternMatcherStrategy.class);
//        PATTERN_MATCHER_TYPES.put("EXACT", ExactUrlPatternMatcherStrategy.class);
//    }
//
//    public CustomCasAuthenticationFilter(boolean isRedirectAfterValidation) {
//        this();
//        this.isRedirectAfterValidation = isRedirectAfterValidation;
//    }
//
//    public CustomCasAuthenticationFilter() {
//        this(Protocol.CAS2);
//    }
//
//    protected CustomCasAuthenticationFilter(final Protocol protocol) {
//        super(protocol);
//    }
//
//    @Override
//    protected void initInternal(final FilterConfig filterConfig) throws ServletException {
//        if (!isIgnoreInitConfiguration()) {
//            super.initInternal(filterConfig);
//
//            final String loginUrl = getString(ConfigurationKeys.CAS_SERVER_LOGIN_URL);
//            if (loginUrl != null) {
//                setCasServerLoginUrl(loginUrl);
//            } else {
//                setCasServerUrlPrefix(getString(ConfigurationKeys.CAS_SERVER_URL_PREFIX));
//            }
//
//            setRenew(getBoolean(ConfigurationKeys.RENEW));
//            setGateway(getBoolean(ConfigurationKeys.GATEWAY));
//            setMethod(getString(ConfigurationKeys.METHOD));
//
//            final String ignorePattern = getString(ConfigurationKeys.IGNORE_PATTERN);
//            final String ignoreUrlPatternType = getString(ConfigurationKeys.IGNORE_URL_PATTERN_TYPE);
//
//            if (ignorePattern != null) {
//                final Class<? extends UrlPatternMatcherStrategy> ignoreUrlMatcherClass = PATTERN_MATCHER_TYPES.get(ignoreUrlPatternType);
//                if (ignoreUrlMatcherClass != null) {
//                    this.ignoreUrlPatternMatcherStrategyClass = ReflectUtils.newInstance(ignoreUrlMatcherClass.getName());
//                } else {
//                    try {
//                        logger.trace("Assuming {} is a qualified class name...", ignoreUrlPatternType);
//                        this.ignoreUrlPatternMatcherStrategyClass = ReflectUtils.newInstance(ignoreUrlPatternType);
//                    } catch (final IllegalArgumentException e) {
//                        logger.error("Could not instantiate class [{}]", ignoreUrlPatternType, e);
//                    }
//                }
//                if (this.ignoreUrlPatternMatcherStrategyClass != null) {
//                    this.ignoreUrlPatternMatcherStrategyClass.setPattern(ignorePattern);
//                }
//            }
//
//            final Class<? extends GatewayResolver> gatewayStorageClass = getClass(ConfigurationKeys.GATEWAY_STORAGE_CLASS);
//
//            if (gatewayStorageClass != null) {
//                setGatewayStorage(ReflectUtils.newInstance(gatewayStorageClass));
//            }
//
//            final Class<? extends AuthenticationRedirectStrategy> authenticationRedirectStrategyClass = getClass(ConfigurationKeys.AUTHENTICATION_REDIRECT_STRATEGY_CLASS);
//
//            if (authenticationRedirectStrategyClass != null) {
//                this.authenticationRedirectStrategy = ReflectUtils.newInstance(authenticationRedirectStrategyClass);
//            }
//        }
//    }
//
//    @Override
//    public void init() {
//        super.init();
//
//        final String message = String.format(
//                "one of %s and %s must not be null.",
//                ConfigurationKeys.CAS_SERVER_LOGIN_URL.getName(),
//                ConfigurationKeys.CAS_SERVER_URL_PREFIX.getName());
//
//        CommonUtils.assertNotNull(this.casServerLoginUrl, message);
//    }
//
//    @Override
//    public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
//                               final FilterChain filterChain) throws IOException, ServletException {
//
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        if (isRequestUrlExcluded(request)) {
//            logger.debug("Request is ignored.");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final HttpSession session = request.getSession(false);
//        final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;
//
//        if (assertion != null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String serviceUrl = constructServiceUrl(request, response);
//        final String ticket = retrieveTicketFromRequest(request);
//        final boolean wasGatewayed = this.gateway && this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);
//
//        if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //修改CAS的源码 add by zqw
//        //因为使用的是REST接口，也不使用cookie,并且LoginFilter的优先级比
//        //当前的Filter优先级高，需要登录与否已经判断完了，这里无需重定向，不然无需权限控制的URL会陷入无限重定向中
//        if (isRedirectAfterValidation) {
//            final String modifiedServiceUrl;
//
//            logger.debug("no ticket and no assertion found");
//            if (this.gateway) {
//                logger.debug("setting gateway attribute in session");
//                modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
//            } else {
//                modifiedServiceUrl = serviceUrl;
//            }
//
//            logger.debug("Constructed service url: {}", modifiedServiceUrl);
//
//            final String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl,
//                    getProtocol().getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway, this.method);
//
//            logger.debug("redirecting to \"{}\"", urlToRedirectTo);
//            this.authenticationRedirectStrategy.redirect(request, response, urlToRedirectTo);
//        } else {
//            //进入下一个过滤器
//            filterChain.doFilter(request, response);
//        }
//    }
//
//    public final void setRenew(final boolean renew) {
//        this.renew = renew;
//    }
//
//    public final void setGateway(final boolean gateway) {
//        this.gateway = gateway;
//    }
//
//    public void setMethod(final String method) {
//        this.method = method;
//    }
//
//    public final void setCasServerUrlPrefix(final String casServerUrlPrefix) {
//        setCasServerLoginUrl(CommonUtils.addTrailingSlash(casServerUrlPrefix) + "login");
//    }
//
//    public final void setCasServerLoginUrl(final String casServerLoginUrl) {
//        this.casServerLoginUrl = casServerLoginUrl;
//    }
//
//    public final void setGatewayStorage(final GatewayResolver gatewayStorage) {
//        this.gatewayStorage = gatewayStorage;
//    }
//
//    private boolean isRequestUrlExcluded(final HttpServletRequest request) {
//        if (this.ignoreUrlPatternMatcherStrategyClass == null) {
//            return false;
//        }
//
//        final StringBuffer urlBuffer = request.getRequestURL();
//        if (request.getQueryString() != null) {
//            urlBuffer.append("?").append(request.getQueryString());
//        }
//        final String requestUri = urlBuffer.toString();
//        return this.ignoreUrlPatternMatcherStrategyClass.matches(requestUri);
//    }
//
//    public final void setIgnoreUrlPatternMatcherStrategyClass(
//            final UrlPatternMatcherStrategy ignoreUrlPatternMatcherStrategyClass) {
//        this.ignoreUrlPatternMatcherStrategyClass = ignoreUrlPatternMatcherStrategyClass;
//    }
//
//}
