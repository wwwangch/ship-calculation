package com.iscas.common.tools.core.map;


import java.lang.ref.Reference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 线程安全的WeakMap实现，参考hutool，方便不引用hutool的情况下使用
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/29 13:35
 * @since jdk11
 */
public class WeakConcurrentMap<K, V> extends ReferenceConcurrentMap<K, V> {

    /**
     * 构造
     */
    public WeakConcurrentMap() {
        this(new ConcurrentHashMap<>());
    }

    /**
     * 构造
     *
     * @param raw {@link ConcurrentMap}实现
     */
    public WeakConcurrentMap(ConcurrentMap<Reference<K>, V> raw) {
        super(raw, ReferenceType.WEAK);
    }
}
