package com.iscas.common.tools.constant;

/**
 * HTTP请求的常用KEY
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/25 17:40
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public interface HeaderKey {

    /**
     * Content-Type
     * */
    String CONTENT_TYPE = "Content-Type";

    /**
     * Content-disposition
     * */
    String CONTENT_DISPOSITION = "Content-disposition";

    /**
     * Accept
     * */
    String ACCEPT = "Accept";

    /**
     * Range
     * */
    String RANGE = "Range";

    /**
     * Content-Range
     * */
    String CONTENT_RANGE = "Content-Range";

    /**
     * Connection
     * */
    String CONNECTION = "Connection";

    /**
     *
     * Accept-Encoding
     * */
    String ACCEPT_ENCODING = "Accept-Encoding";

    /**
     * Content-Encoding
     * */
    String CONTENT_ENCODING = "Content-Encoding";


    /**
     * Content-Length
     * */
    String CONTENT_LENGTH = "Content-Length";

    /**
     * content-language
     * */
    String CONTENT_LANGUAGE = "content-language";

    /**
     * ETag
     * */
    String ETAG = "ETag";

    /**
     * Upgrade
     * */
    String UPGRADE = "Upgrade";

    /**X-FORWARDED-FOR*/
    String X_FORWARDED_FOR = "x-forwarded-for";

    /**Proxy-Client-IP*/
    String PROXY_CLIENT_IP = "Proxy-Client-IP";

    /**WL-Proxy-Client-IP*/
    String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**Expires*/
    String EXPIRES = "Expires";

    /**Cache-Control*/
    String CACHE_CONTROL = "Cache-Control";

    /**Pragma*/
    String PRAGMA = "Pragma";

    String ORIGIN = "Origin";
}
