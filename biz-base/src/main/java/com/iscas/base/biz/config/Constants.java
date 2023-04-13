package com.iscas.base.biz.config;

/**
 * 常量定义类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 18:47
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public interface Constants {
    /**权限配置XML名称*/
    String AUTH_CONFIG_XML_NAME = "auth.xml";

    /**token key*/
    String TOKEN_KEY = "Authorization";

    /**SESSION_LOGIN_KEY*/
    String SESSION_LOGIN_KEY = "SESSION_LOGIN_KEY";

    /**用户信息*/
    String SESSION_USER = "SESSION_USER";

    /**序号的Key*/
    String NUM_KEY = "num";

    /**超级管理员角色*/
    String SUPER_ROLE_KEY = "super";

    /**超级管理员用户*/
    String SUPER_USER_KEY = "admin";

    /**管理员角色*/
    String MANAGER_ROLE_KEY = "manager";

    /**企业角色*/
    String APPLY_ROLE_KEY = "apply";

    /**Appliction session*/
    String APPLICATION_SESSION_KEY = "sessions";

    /**参数缓存名称*/
    String CACHE_PARAM_NAME = "param";

    /**字典缓存名称*/
    String CACHE_DICT_NAME = "dict";

    /**跨域允许Origin的key*/
    String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    /**跨域允许Credentials的key*/
    String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    /**跨域允许Methods的key*/
    String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";

    /**跨域允许Methods的key*/
    String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    /**access日志访问开始时间*/
    String KEY_REQUEST_START_TIME = "KEY_REQUEST_START_TIME";

    /**请求参数校验失败的前缀*/
    String REQUEST_PARAM_VALID_ERROR_PREFIX = "请求参数校验失败:";

    String LOGIN_CACHE = "loginCache";
    String AUTH_CACHE = "auth";

    /**
     * 验证码缓存name
     * */
    String CAPTCHA_CACHE = "captcha";

    /**
     * 锁定用户缓存name
     * */
    String LOCK_USER_CACHE = "lockuser";

    String KEY_USER_TOKEN = "user-token:";

    /**日志链路追踪的请求头*/
    String TRACE_ID_HEADER = "x-traceId-header";

    /**
     * 日志链路追踪id日志标志
     */
    String LOG_TRACE_ID = "traceId";

}
