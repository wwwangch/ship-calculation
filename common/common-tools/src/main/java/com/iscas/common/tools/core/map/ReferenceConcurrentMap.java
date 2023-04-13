package com.iscas.common.tools.core.map;


import java.io.Serializable;
import java.lang.ref.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 参考Hutool中的实现，方便不引用hutool的情况下使用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/29 13:31
 * @since jdk11
 */
public class ReferenceConcurrentMap<K, V> implements ConcurrentMap<K, V>, Iterable<Map.Entry<K, V>>, Serializable {

    final ConcurrentMap<Reference<K>, V> raw;
    private final ReferenceQueue<K> lastQueue;
    private final ReferenceType keyType;
    /**
     * 回收监听
     */
    private BiConsumer<Reference<? extends K>, V> purgeListener;

    // region 构造

    /**
     * 构造
     *
     * @param raw           {@link ConcurrentMap}实现
     * @param referenceType Reference类型
     */
    public ReferenceConcurrentMap(ConcurrentMap<Reference<K>, V> raw, ReferenceType referenceType) {
        this.raw = raw;
        this.keyType = referenceType;
        lastQueue = new ReferenceQueue<>();
    }
    // endregion

    /**
     * 设置对象回收清除监听
     *
     * @param purgeListener 监听函数
     */
    public void setPurgeListener(BiConsumer<Reference<? extends K>, V> purgeListener) {
        this.purgeListener = purgeListener;
    }

    @Override
    public int size() {
        this.purgeStaleKeys();
        return this.raw.size();
    }

    @Override
    public boolean isEmpty() {
        return 0 == size();
    }

    @Override
    public V get(Object key) {
        this.purgeStaleKeys();
        //noinspection unchecked
        return this.raw.get(ofKey((K) key, null));
    }

    @Override
    public boolean containsKey(Object key) {
        this.purgeStaleKeys();
        //noinspection unchecked
        return this.raw.containsKey(ofKey((K) key, null));
    }

    @Override
    public boolean containsValue(Object value) {
        this.purgeStaleKeys();
        return this.raw.containsValue(value);
    }

    @Override
    public V put(K key, V value) {
        this.purgeStaleKeys();
        return this.raw.put(ofKey(key, this.lastQueue), value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        this.purgeStaleKeys();
        return this.raw.putIfAbsent(ofKey(key, this.lastQueue), value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public V replace(K key, V value) {
        this.purgeStaleKeys();
        return this.raw.replace(ofKey(key, this.lastQueue), value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        this.purgeStaleKeys();
        return this.raw.replace(ofKey(key, this.lastQueue), oldValue, newValue);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        this.purgeStaleKeys();
        this.raw.replaceAll((kWeakKey, value) -> function.apply(kWeakKey.get(), value));
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        this.purgeStaleKeys();
        return this.raw.computeIfAbsent(ofKey(key, this.lastQueue), kWeakKey -> mappingFunction.apply(key));
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        this.purgeStaleKeys();
        return this.raw.computeIfPresent(ofKey(key, this.lastQueue), (kWeakKey, value) -> remappingFunction.apply(key, value));
    }

    /**
     * 从缓存中获得对象，当对象不在缓存中或已经过期返回Func0回调产生的对象
     *
     * @param key      键
     * @param supplier 如果不存在回调方法，用于生产值对象
     * @return 值对象
     */
    public V computeIfAbsent(K key, Func0<? extends V> supplier) {
        return computeIfAbsent(key, (keyParam) -> supplier.callWithRuntimeException());
    }

    @Override
    public V remove(Object key) {
        this.purgeStaleKeys();
        //noinspection unchecked
        return this.raw.remove(ofKey((K) key, null));
    }

    @Override
    public boolean remove(Object key, Object value) {
        this.purgeStaleKeys();
        //noinspection unchecked
        return this.raw.remove(ofKey((K) key, null), value);
    }

    @Override
    public void clear() {
        this.raw.clear();
        //noinspection StatementWithEmptyBody,AliControlFlowStatementWithoutBraces
        while (lastQueue.poll() != null) ;
    }

    @Override
    public Set<K> keySet() {
        // TODO 非高效方式的set转换，应该返回一个view
        final Collection<K> trans = trans(this.raw.keySet(), (reference) -> null == reference ? null : reference.get());
        return new HashSet<>(trans);
    }

    @Override
    public Collection<V> values() {
        this.purgeStaleKeys();
        return this.raw.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        this.purgeStaleKeys();
        return this.raw.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey().get(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.purgeStaleKeys();
        this.raw.forEach((key, value) -> action.accept(key.get(), value));
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return entrySet().iterator();
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        this.purgeStaleKeys();
        return this.raw.compute(ofKey(key, this.lastQueue), (kWeakKey, value) -> remappingFunction.apply(key, value));
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        this.purgeStaleKeys();
        return this.raw.merge(ofKey(key, this.lastQueue), value, remappingFunction);
    }

    /**
     * 清除被回收的键
     */
    private void purgeStaleKeys() {
        Reference<? extends K> reference;
        V value;
        while ((reference = this.lastQueue.poll()) != null) {
            value = this.raw.remove(reference);
            if (null != purgeListener) {
                purgeListener.accept(reference, value);
            }
        }
    }

    /**
     * 根据Reference类型构建key对应的{@link Reference}
     *
     * @param key   键
     * @param queue {@link ReferenceQueue}
     * @return {@link Reference}
     */
    private Reference<K> ofKey(K key, ReferenceQueue<? super K> queue) {
        return switch (keyType) {
            case WEAK -> new WeakKey<>(key, queue);
            case SOFT -> new SoftKey<>(key, queue);
            default -> throw new IllegalArgumentException("Unsupported key type: " + keyType);
        };
    }

    /**
     * 弱键
     *
     * @param <K> 键类型
     */
    private static class WeakKey<K> extends WeakReference<K> {
        private final int hashCode;

        /**
         * 构造
         *
         * @param key   原始Key，不能为{@code null}
         * @param queue {@link ReferenceQueue}
         */
        WeakKey(K key, ReferenceQueue<? super K> queue) {
            super(key, queue);
            hashCode = key.hashCode();
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            } else if (other instanceof WeakKey) {
                return equal(((WeakKey<?>) other).get(), get());
            }
            return false;
        }
    }

    /**
     * 弱键
     *
     * @param <K> 键类型
     */
    private static class SoftKey<K> extends SoftReference<K> {
        private final int hashCode;

        /**
         * 构造
         *
         * @param key   原始Key，不能为{@code null}
         * @param queue {@link ReferenceQueue}
         */
        SoftKey(K key, ReferenceQueue<? super K> queue) {
            super(key, queue);
            hashCode = key.hashCode();
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            } else if (other instanceof SoftKey) {
                return equal(((SoftKey<?>) other).get(), get());
            }
            return false;
        }
    }

    /**
     * 使用给定的转换函数，转换源集合为新类型的集合
     *
     * @param <F>        源元素类型
     * @param <T>        目标元素类型
     * @param collection 集合
     * @param function   转换函数
     * @return 新类型的集合
     * @since 5.4.3
     */
    public static <F, T> Collection<T> trans(Collection<F> collection, Function<? super F, ? extends T> function) {
        return new TransCollection<>(collection, function);
    }

    /**
     * 比较两个对象是否相等。<br>
     * 相同的条件有两个，满足其一即可：<br>
     * <ol>
     * <li>obj1 == null &amp;&amp; obj2 == null</li>
     * <li>obj1.equals(obj2)</li>
     * <li>如果是BigDecimal比较，0 == obj1.compareTo(obj2)</li>
     * </ol>
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否相等
     * @see Objects#equals(Object, Object)
     */
    public static boolean equal(Object obj1, Object obj2) {
        if (obj1 instanceof BigDecimal && obj2 instanceof BigDecimal) {
            if (obj1 == obj2) {
                // 如果用户传入同一对象，省略compareTo以提高性能。
                return true;
            }
            BigDecimal bigNum1 = (BigDecimal) obj1;
            BigDecimal bigNum2 = (BigDecimal) obj2;
            return 0 == bigNum1.compareTo(bigNum2);
        }
        return Objects.equals(obj1, obj2);
    }

    public enum ReferenceType {
        /**
         * 软引用，在GC报告内存不足时会被GC回收
         */
        SOFT,
        /**
         * 弱引用，在GC时发现弱引用会回收其对象
         */
        WEAK,
        /**
         * 虚引用，在GC时发现虚引用对象，会将{@link PhantomReference}插入{@link ReferenceQueue}。 <br>
         * 此时对象未被真正回收，要等到{@link ReferenceQueue}被真正处理后才会被回收。
         */
        PHANTOM
    }

    @FunctionalInterface
    public interface Func0<R> extends Serializable {
        /**
         * 执行函数
         *
         * @return 函数执行结果
         * @throws Exception 自定义异常
         */
        R call() throws Exception;

        /**
         * 执行函数，异常包装为RuntimeException
         *
         * @return 函数执行结果
         * @since 5.3.6
         */
        default R callWithRuntimeException() {
            try {
                return call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class TransCollection<F, T> extends AbstractCollection<T> {

        private final Collection<F> fromCollection;
        private final Function<? super F, ? extends T> function;

        /**
         * 构造
         *
         * @param fromCollection 源集合
         * @param function       转换函数
         */
        public TransCollection(Collection<F> fromCollection, Function<? super F, ? extends T> function) {
            if (fromCollection == null) {
                throw new IllegalArgumentException("[Assertion failed] - this argument:[fromCollection] is required; it must not be null");
            }
            if (function == null) {
                throw new IllegalArgumentException("[Assertion failed] - this argument:[function] is required; it must not be null");
            }
            this.fromCollection = fromCollection;
            this.function = function;
        }

        @Override
        public Iterator<T> iterator() {
            return new TransIter<>(fromCollection.iterator(), function);
        }

        @Override
        public void clear() {
            fromCollection.clear();
        }

        @Override
        public boolean isEmpty() {
            return fromCollection.isEmpty();
        }

        @Override
        public void forEach(Consumer<? super T> action) {
            fromCollection.forEach((f) -> action.accept(function.apply(f)));
        }

        @Override
        public boolean removeIf(Predicate<? super T> filter) {
            return fromCollection.removeIf(element -> filter.test(function.apply(element)));
        }

        @Override
        public Spliterator<T> spliterator() {
            return new TransSpliterator<>(fromCollection.spliterator(), function);
        }

        @Override
        public int size() {
            return fromCollection.size();
        }
    }

    public static class TransIter<F, T> implements Iterator<T> {

        private final Iterator<? extends F> backingIterator;
        private final Function<? super F, ? extends T> func;

        /**
         * 构造
         *
         * @param backingIterator 源{@link Iterator}
         * @param func            转换函数
         */
        public TransIter(final Iterator<? extends F> backingIterator, final Function<? super F, ? extends T> func) {
            this.backingIterator = backingIterator;
            this.func = func;
        }

        @Override
        public final boolean hasNext() {
            return backingIterator.hasNext();
        }

        @Override
        public final T next() {
            return func.apply(backingIterator.next());
        }

        @Override
        public final void remove() {
            backingIterator.remove();
        }
    }


    public static class TransSpliterator<F, T> implements Spliterator<T> {
        private final Spliterator<F> fromSpliterator;
        private final Function<? super F, ? extends T> function;

        public TransSpliterator(Spliterator<F> fromSpliterator, Function<? super F, ? extends T> function) {
            this.fromSpliterator = fromSpliterator;
            this.function = function;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            return fromSpliterator.tryAdvance(
                    fromElement -> action.accept(function.apply(fromElement)));
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            fromSpliterator.forEachRemaining(fromElement -> action.accept(function.apply(fromElement)));
        }

        @Override
        public Spliterator<T> trySplit() {
            Spliterator<F> fromSplit = fromSpliterator.trySplit();
            return (fromSplit != null) ? new cn.hutool.core.collection.TransSpliterator<>(fromSplit, function) : null;
        }

        @Override
        public long estimateSize() {
            return fromSpliterator.estimateSize();
        }

        @Override
        public int characteristics() {
            return fromSpliterator.characteristics()
                    & ~(Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.SORTED);
        }
    }
}
