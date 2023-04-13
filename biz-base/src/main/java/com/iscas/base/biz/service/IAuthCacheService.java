package com.iscas.base.biz.service;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/7 22:47
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface IAuthCacheService {
//    void remove(String key, String cacheKey);
//    void set(String key, Object value, String cacheKey, int ttl);
//    void set(String key, Object value, String cacheKey);
//    Object get(String key, String cacheKey);

    void rpush(String key, String value, String cacheKey);

    String lpop(String key, String cacheKey);

    int llen(String key, String cacheKey);

    boolean listContains(String key, String value);

//    String createCodeAndPut(String secretKey);

}
