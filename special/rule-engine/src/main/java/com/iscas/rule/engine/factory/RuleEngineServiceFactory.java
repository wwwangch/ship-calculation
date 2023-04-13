package com.iscas.rule.engine.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 规则引擎用到的业务操作类的工厂
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 13:55
 * @since jdk1.8
 */
@SuppressWarnings({"JavadocDeclaration", "unchecked", "unused"})
public class RuleEngineServiceFactory {

    /**
     * 业务操作类一级缓存
     * */
    public static Map<Class<?>, Object> RULE_ENGINE_SERVICES_FIRST_CACHE = new ConcurrentHashMap<>();

    /**
     * 业务操作类二级缓存,处理循环引用的问题
     * */
    public static Map<Class<?>, Object> RULE_ENGINE_SERVICES_SECOND_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取一个业务类
     * */
    public static <T> T getService(Class<T> tClass) {
        return (T) RULE_ENGINE_SERVICES_FIRST_CACHE.get(tClass);
    }

    public static void removeAll() {
        RULE_ENGINE_SERVICES_SECOND_CACHE.clear();
        RULE_ENGINE_SERVICES_FIRST_CACHE.clear();
    }

    public static void remove(Class<?> tClass) {
        RULE_ENGINE_SERVICES_SECOND_CACHE.clear();
        RULE_ENGINE_SERVICES_FIRST_CACHE.clear();
    }
}
