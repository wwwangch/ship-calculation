package com.iscas.common.tools.core.reflect;

import com.iscas.common.tools.core.array.ArrayUtils;
import com.iscas.common.tools.core.map.WeakConcurrentMap;
import com.iscas.common.tools.core.string.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 反射增强工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/13
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class ReflectUtils {

//    private static final VarHandle MODIFIERS;
//
//    static {
//        // ⽤于消除⾮法访问警告，仅⽤于未命名模块
////        Module javaBase = Field.class.getModule();
////        Module my = ReflectUtils.class.getModule();
////        javaBase.addOpens("java.lang.reflect", my);
////        javaBase.addOpens("java.util", my);
//        try {
//            var lookup = MethodHandles.privateLookupIn(Field.class, MethodHandles.lookup());
//            MODIFIERS = lookup.findVarHandle(Field.class, "modifiers", int.class);
//        } catch (IllegalAccessException | NoSuchFieldException ex) {
//            throw new RuntimeException(ex);
//        }
//    }


    /**
     * 私有构造方法，防止被实例化使用
     */
    private ReflectUtils() {
    }

    /**
     * 构造器的缓存
     */
    private static final Map<Class<?>, Constructor[]> CONSTRUCTORS_CACHE = new WeakConcurrentMap<>();

    /**
     * Field的缓存
     */
    private static final Map<Class<?>, Field[]> FIELDS_CACHE = new WeakConcurrentMap<>();

    /**
     * Field的缓存
     */
    private static final Map<Class<?>, Field[]> CURRENT_FIELDS_CACHE = new WeakConcurrentMap<>();

    /**
     * method的缓存
     */
    private static final Map<Class<?>, Method[]> METHODS_CACHE = new WeakConcurrentMap<>();

    /**
     * annotation-CLASS的缓存
     */
    private static final Map<Class<?>, Annotation[]> ANNOTATIONS_CLASS_CACHE = new WeakConcurrentMap<>();

    /**
     * annotation-METHOD的缓存
     */
    private static final Map<Method, Annotation[]> ANNOTATIONS_METHOD_CACHE = new WeakConcurrentMap<>();

    /**
     * annotation-FIELD的缓存
     */
    private static final Map<Field, Annotation[]> ANNOTATIONS_FIELD_CACHE = new WeakConcurrentMap<>();


    /**
     * 更改field的权限，可为编辑状态
     *
     * @param field field
     * @date 2022/6/29
     * @since jdk11
     */
    @SuppressWarnings({"AlibabaAvoidComplexCondition", "deprecation"})
    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    /**
     * 更改field的权限，可为编辑状态
     *
     * @param field field
     * @param obj   对象
     * @date 2022/6/29
     * @since jdk11
     */
    public static void makeAccessible(Field field, Object obj) {
        if ((!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) && !field.canAccess(obj)) {
            field.setAccessible(true);
        }
    }

    /**
     * 使final的field可修改
     * 注意的是基本数据类型和String不可修改，除非使用包装类或String初始值为new String
     *
     * JDK17修改属性的方式做了修改，以前的方式无法使用
     * 使用此方法在JDK17下需要添加JVM参数--add-opens java.base/java.lang.reflect=ALL-UNNAMED
     *
     * @param field field
     * @date 2022/6/29
     * @since jdk1.8
     */
//    public static void makeFinalModifiers(Field field) throws NoSuchFieldException, IllegalAccessException {
//        field.setAccessible(true);
//        Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true);
//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//    }
    public static void makeFinalModifiers(Field field) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        int mods = field.getModifiers();
        if (Modifier.isFinal(mods)) {
            var lookup = MethodHandles.privateLookupIn(Field.class, MethodHandles.lookup());
            VarHandle modifiers = lookup.findVarHandle(Field.class, "modifiers", int.class);
            modifiers.set(field, mods & ~Modifier.FINAL);
        }
    }

    public static class TestM {
        private Integer id;
        private String name;
        public final String aaa = new String("xxx");
        public final Integer bbb = 1;

        public void test() {
            System.out.println(2222);
        }
    }

    /**
     * 更改constructor的权限，可为编辑状态
     *
     * @param constructor 构造器
     * @date 2022/6/29
     * @since jdk1.8
     */
    @SuppressWarnings({"AlibabaAvoidComplexCondition", "deprecation"})
    public static <T> void makeAccessible(Constructor<T> constructor) {
        if ((!Modifier.isPublic(constructor.getModifiers()) ||
                !Modifier.isPublic(constructor.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(constructor.getModifiers())) && !constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
    }

    /**
     * 使final的constructor可修改
     *
     * @param constructor 构造器
     * @date 2022/6/29
     * @since jdk1.8
     */
    public static <T> void makeFinalModifiers(Constructor<T> constructor) throws NoSuchFieldException, IllegalAccessException {
        constructor.setAccessible(true);
        Field modifiersField = Constructor.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(constructor, constructor.getModifiers() & ~Modifier.FINAL);
    }

    /**
     * 更改Method的权限，可为编辑状态
     *
     * @param method 方法
     * @date 2022/6/29
     * @since jdk1.8
     */
    @SuppressWarnings({"AlibabaAvoidComplexCondition", "deprecation"})
    public static void makeAccessible(Method method) {
        if ((!Modifier.isPublic(method.getModifiers()) ||
                !Modifier.isPublic(method.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(method.getModifiers())) && !method.isAccessible()) {
            method.setAccessible(true);
        }
    }

    /**
     * 更改Method的权限，可为编辑状态
     *
     * @param method 方法
     * @param obj    对象
     * @date 2022/6/29
     * @since jdk1.8
     */
    @SuppressWarnings({"AlibabaAvoidComplexCondition"})
    public static void makeAccessible(Method method, Object obj) {
        if ((!Modifier.isPublic(method.getModifiers()) ||
                !Modifier.isPublic(method.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(method.getModifiers())) && !method.canAccess(obj)) {
            method.setAccessible(true);
        }
    }

    /**
     * 使final的method可修改
     *
     * @param method 方法
     * @date 2022/6/29
     * @since jdk1.8
     */
    public static void makeFinalModifiers(Method method) throws NoSuchFieldException, IllegalAccessException {
        method.setAccessible(true);
        Field modifiersField = Method.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(method, method.getModifiers() & ~Modifier.FINAL);
    }


    /**
     * 判断一个Class是否为包装类数据类型
     *
     * @param clz Class对象
     * @return boolean
     * @date 2018/7/16
     * @since jdk1.8
     */
    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断一个Class是否为基本数据类型
     *
     * @param clz Class对象
     * @return boolean
     * @date 2018/7/16
     * @since jdk1.8
     */
    public static boolean isPrimitive(Class clz) {
        try {
            return clz.isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否为基本类型（包括包装类和原始类）
     *
     * @param clazz 类
     * @return 是否为基本类型
     */
    public static boolean isBasicType(Class<?> clazz) {
        if (null == clazz) {
            return false;
        }
        return (clazz.isPrimitive() || isWrapClass(clazz));
    }

    /**
     * 对象是否为数组对象
     *
     * @return 是否为数组对象，如果为{@code null} 返回false
     */
    public static boolean isArray(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        return clazz.isArray();
    }

    /**
     * 是否为外部类引用字段，非静态内部类对象中会有一个引用外部类的field
     */
    public static boolean isOuterClassField(Field field) {
        return "this$0".equals(field.getName());
    }

    /**
     * 判断某个类是否是抽象的
     */
    public static boolean isAbstract(Class clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    /**
     * 判断某个方法是否是抽象的
     */
    public static boolean isAbstract(Method method) {
        return Modifier.isAbstract(method.getModifiers());
    }

    /**
     * 判断某个类是否是final
     */
    public static boolean isFinal(Class<?> clazz) {
        return Modifier.isFinal(clazz.getModifiers());
    }

    /**
     * 判断某个方法是否是final
     */
    public static boolean isFinal(Method method) {
        return Modifier.isFinal(method.getModifiers());
    }

    /**
     * 判断某个field是否是final
     */
    public static boolean isFinal(Field field) {
        return Modifier.isFinal(field.getModifiers());
    }

    /**
     * 判断某个field是否是public
     */
    public static boolean isPublic(Field field) {
        return Modifier.isPublic(field.getModifiers());
    }

    /**
     * 判断某个method是否是public
     */
    public static boolean isPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    /**
     * 判断某个class是否是public
     */
    public static boolean isPublic(Class clazz) {
        return Modifier.isPublic(clazz.getModifiers());
    }

    /**
     * 判断某个field是否是protected
     */
    public static boolean isProtected(Field field) {
        return Modifier.isProtected(field.getModifiers());
    }

    /**
     * 判断某个method是否是public
     */
    public static boolean isProtected(Method method) {
        return Modifier.isProtected(method.getModifiers());
    }

    /**
     * 判断某个class是否是Protected
     */
    public static boolean isProtected(Class clazz) {
        return Modifier.isProtected(clazz.getModifiers());
    }

    /**
     * 判断某个field是否是Private
     */
    public static boolean isPrivate(Field field) {
        return Modifier.isPrivate(field.getModifiers());
    }

    /**
     * 判断某个method是否是Private
     */
    public static boolean isPrivate(Method method) {
        return Modifier.isPrivate(method.getModifiers());
    }

    /**
     * 判断某个class是否是Private
     */
    public static boolean isPrivate(Class clazz) {
        return Modifier.isPrivate(clazz.getModifiers());
    }

    /**
     * 判断某个method是否是default
     */
    public static boolean isDefault(Method method) {
        return method.isDefault();
    }

    /**
     * 判断某个method是否是native
     */
    public static boolean isNative(Method method) {
        return Modifier.isNative(method.getModifiers());
    }

    /**
     * 判断某个method是否是synchronized
     */
    public static boolean isSynchronized(Method method) {
        return Modifier.isSynchronized(method.getModifiers());
    }

    /**
     * 判断某个method是否是static
     */
    public static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    /**
     * 判断某个class是否是static
     */
    public static boolean isStatic(Class clazz) {
        return Modifier.isStatic(clazz.getModifiers());
    }

    /**
     * 判断某个field是否是static
     */
    public static boolean isStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

    /**
     * 判断某个field是否是Transient
     */
    public static boolean isTransient(Field field) {
        return Modifier.isTransient(field.getModifiers());
    }

    /**
     * 判断某个field是否是volatile
     */
    public static boolean isVolatile(Field field) {
        return Modifier.isVolatile(field.getModifiers());
    }

    /**
     * 判断是否是equals方法
     */
    public static boolean isEqualsMethod(Method method) {
        return method != null && Objects.equals("equals", method.getName()) && method.getParameterCount() == 1 &&
                method.getParameterTypes()[0] == Object.class;
    }

    /**
     * 判断是否为hashcode方法
     */
    public static boolean isHashCodeMethod(Method method) {
        return method != null && Objects.equals("hashCode", method.getName()) && method.getParameterCount() == 0;
    }

    /**
     * 判断是否为toString方法
     */
    public static boolean isToStringMethod(Method method) {
        return method != null && Objects.equals("toString", method.getName()) && method.getParameterCount() == 0;
    }

    /**
     * 某个属性上是否有某个注解
     *
     * @param field           属性
     * @param annotationClass 注解的class类型
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean isAnnotationPresent(Field field, Class<? extends Annotation> annotationClass) {
        return field.isAnnotationPresent(annotationClass);
    }

    /**
     * 某个方法上是否有某个注解
     *
     * @param method          方法
     * @param annotationClass 注解的class类型
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean isAnnotationPresent(Method method, Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    /**
     * 某个类上是否有某个注解
     *
     * @param clazz           类
     * @param annotationClass 注解的class类型
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean isAnnotationPresentCurrent(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return clazz.isAnnotationPresent(annotationClass);
    }

    /**
     * 某个类上是否有某个注解,没有的话查询父类
     *
     * @param clazz           类
     * @param annotationClass 注解的class类型
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean isAnnotationPresent(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return getAnnotation(clazz, annotationClass) != null;
    }

    /**
     * 获取注解
     *
     * @param field           属性
     * @param annotationClass 注解的class类型
     * @return T
     * @date 2022/6/30
     * @since jdk11
     */
    public static <T extends Annotation> T getAnnotation(Field field, Class<? extends Annotation> annotationClass) {
        if (isAnnotationPresent(field, annotationClass)) {
            return (T) field.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * 某个方法上是否有某个注解
     *
     * @param method          方法
     * @param annotationClass 注解的class类型
     * @return T
     * @date 2022/6/30
     * @since jdk11
     */
    public static <T extends Annotation> T getAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        if (isAnnotationPresent(method, annotationClass)) {
            return (T) method.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * 某个类上是否有某个注解
     *
     * @param clazz           类
     * @param annotationClass 注解的class类型
     * @return T
     * @date 2022/6/30
     * @since jdk11
     */
    public static <T extends Annotation> T getAnnotationCurrent(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        if (isAnnotationPresent(clazz, annotationClass)) {
            return (T) clazz.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * 查询某个类上所有注解，包括其接口、父类
     *
     * @param clazz    类
     * @param useCache 是否使用缓存
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation[] getAnnotations(Class<?> clazz, boolean useCache) {
        if (useCache) {
            return ANNOTATIONS_CLASS_CACHE.computeIfAbsent(clazz, k -> getClassAnnotationsDirectly(clazz));
        } else {
            return getClassAnnotationsDirectly(clazz);
        }
    }

    /**
     * 查询某个类上所有注解，包括其接口、父类，默认使用缓存
     *
     * @param clazz 类
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation[] getAnnotations(Class<?> clazz) {
        return getAnnotations(clazz, true);
    }

    /**
     * 查询某个类上是否有某种注解，如果没有查询其接口、父类
     *
     * @param clazz    类
     * @param useCache 是否使用缓存
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass, boolean useCache) {
        Annotation[] annotations = getAnnotations(clazz, useCache);
        if (ArrayUtils.isNotEmpty(annotations)) {
            return Arrays.stream(annotations)
                    .filter(annotation -> annotationClass.isAssignableFrom(annotation.getClass()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * 查询某个类上是否有某种注解，如果没有查询其接口、父类，默认使用缓存
     *
     * @param clazz 类
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return getAnnotation(clazz, annotationClass, true);
    }

    /**
     * 查询某个Method上所有注解，包括其接口、父类
     *
     * @param method   方法
     * @param useCache 是否使用缓存
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation[] getAnnotations(Method method, boolean useCache) {
        if (useCache) {
            return ANNOTATIONS_METHOD_CACHE.computeIfAbsent(method, k -> getMethodAnnotationsDirectly(method));
        } else {
            return getMethodAnnotationsDirectly(method);
        }
    }

    /**
     * 查询某个类上所有注解，包括其接口、父类，默认使用缓存
     *
     * @param method 方法
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation[] getAnnotations(Method method) {
        return getAnnotations(method, true);
    }

    /**
     * 查询某个Field上所有注解，包括其接口、父类
     *
     * @param field    属性
     * @param useCache 是否使用缓存
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation[] getAnnotations(Field field, boolean useCache) {
        if (useCache) {
            return ANNOTATIONS_FIELD_CACHE.computeIfAbsent(field, k -> getFieldAnnotationsDirectly(field));
        } else {
            return getFieldAnnotationsDirectly(field);
        }
    }

    /**
     * 查询某个类上所有注解，包括其接口、父类，默认使用缓存
     *
     * @param field 属性
     * @return Annotation[]
     * @date 2022/6/30
     * @since jdk11
     */
    public static Annotation[] getAnnotations(Field field) {
        return getAnnotations(field, true);
    }


    private static Annotation[] getFieldAnnotationsDirectly(Field field) {
        return field.getAnnotations();
    }


    private static Annotation[] getMethodAnnotationsDirectly(Method method) {
        return method.getAnnotations();
    }

    private static Annotation[] getClassAnnotationsDirectly(Class<?> clazz) {
        Annotation[] annotations = new Annotation[0];
        Class<?> copyClass = clazz;
        // 获取类上的注解
        while (copyClass != null && copyClass != Object.class) {
            Annotation[] currentAnnos = copyClass.getDeclaredAnnotations();
            if (ArrayUtils.isNotEmpty(currentAnnos)) {
                annotations = ArrayUtils.addAll(annotations, currentAnnos);
            }
            copyClass = copyClass.getSuperclass();
        }
        // 获取接口的注解
        assert clazz != null;
        getInterfaceAnnotations(clazz.getInterfaces(), annotations);
        Set<Class> annoNames = new HashSet<>();
        return Arrays.stream(annotations).filter(annotation -> {
            boolean contains = annoNames.contains(annotation.getClass());
            annoNames.add(annotation.getClass());
            return !contains;
        }).toArray(Annotation[]::new);
    }

    private static void getInterfaceAnnotations(Class<?>[] interfaces, Annotation[] annotations) {
        if (ArrayUtils.isNotEmpty(interfaces)) {
            for (Class<?> anInterface : interfaces) {
                Annotation[] currentAnnos = anInterface.getDeclaredAnnotations();
                if (ArrayUtils.isNotEmpty(currentAnnos)) {
                    annotations = ArrayUtils.addAll(annotations, currentAnnos);
                }
                getInterfaceAnnotations(anInterface.getInterfaces(), annotations);
            }
        }
    }


    /**
     * 通过一个class和构造参数获取构造器,默认使用缓存
     *
     * @param clazz        类
     * @param paramClasses 构造参数
     * @return java.lang.reflect.Constructor<T>
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... paramClasses) {
        return getConstructor(clazz, true, paramClasses);
    }

    /**
     * 通过一个class和构造参数获取构造器
     *
     * @param clazz        类
     * @param useCache     是否使用缓存
     * @param paramClasses 构造参数
     * @return java.lang.reflect.Constructor<T>
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz, boolean useCache, Class<?>... paramClasses) {
        if (useCache) {
            Constructor<T>[] constructors = getConstructors(clazz);
            Class<?>[] parameterTypes;
            for (Constructor<T> constructor : constructors) {
                parameterTypes = constructor.getParameterTypes();
                if (isAllAssignableFrom(parameterTypes, paramClasses)) {
                    return constructor;
                }
            }
            return null;
        } else {
            try {
                return clazz.getDeclaredConstructor(paramClasses);
            } catch (NoSuchMethodException ignored) {
                return null;
            }
        }
    }

    /**
     * 获取一个class的所有构造器, 默尔使用缓存
     *
     * @param clazz 类
     * @return java.lang.reflect.Constructor<T>
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> Constructor<T>[] getConstructors(Class<T> clazz) {
        return getConstructors(clazz, true);
    }

    /**
     * 获取一个class的所有构造器
     *
     * @param clazz    类
     * @param useCache 是否使用缓存
     * @return java.lang.reflect.Constructor<T>
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> Constructor<T>[] getConstructors(Class<T> clazz, boolean useCache) {
        if (useCache) {
            return CONSTRUCTORS_CACHE.computeIfAbsent(clazz, Class::getDeclaredConstructors);
        } else {
            return (Constructor<T>[]) clazz.getDeclaredConstructors();
        }
    }

    /**
     * 实例化一个对象
     *
     * @param constructor 构造器
     * @param params      参数
     * @return T
     * @throws InvocationTargetException 异常
     * @throws InstantiationException    异常
     * @throws IllegalAccessException    异常
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> T newInstance(Constructor<T> constructor, Object... params) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        return constructor.newInstance(params);
    }

    /**
     * 实例化一个对象,使用缓存
     *
     * @param clazz    类
     * @param paramMap 参数键值对，key是class, value是实例化时使用的值,可以为空
     * @return T 实例化后的对象
     * @throws InvocationTargetException 异常
     * @throws InstantiationException    异常
     * @throws IllegalAccessException    异常
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> T newInstance(Class<T> clazz, LinkedHashMap<Class<?>, Object> paramMap) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        return newInstance(clazz, true, paramMap);
    }

    /**
     * 实例化一个对象
     *
     * @param clazz    类
     * @param useCache 是否使用缓存
     * @param paramMap 参数键值对，key是class, value是实例化时使用的值,可以为空
     * @return T 实例化后的对象
     * @throws InvocationTargetException 异常
     * @throws InstantiationException    异常
     * @throws IllegalAccessException    异常
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> T newInstance(Class<T> clazz, boolean useCache, LinkedHashMap<Class<?>, Object> paramMap) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<T> constructor;
        if (Objects.isNull(paramMap)) {
            constructor = getConstructor(clazz, useCache);
            if (constructor == null) {
                throw new RuntimeException("未能获取到构造器");
            }
            return newInstance(constructor);
        } else {
            constructor = getConstructor(clazz, useCache, paramMap.keySet().toArray(Class[]::new));
            if (constructor == null) {
                throw new RuntimeException("未能获取到构造器");
            }
            return newInstance(constructor, paramMap.values().toArray());
        }
    }

    /**
     * 获得一个类中所有字段列表，包括其父类中的字段<br>
     * 如果子类与父类中存在同名字段，只显示子类的字段
     *
     * @param beanClass    类
     * @param ignoreFields 忽略的属性
     * @return 字段列表
     */
    public static Field[] getFields(Class<?> beanClass, String... ignoreFields) {
        return getFields(beanClass, true, ignoreFields);
    }

    /**
     * 获得一个类中所有字段列表，包括其父类中的字段<br>
     * 如果子类与父类中存在同名字段，则这两个字段同时存在，子类字段在前，父类字段在后。
     *
     * @param beanClass    类
     * @param useCache     是否使用缓存
     * @param ignoreFields 忽略的属性
     * @return 字段列表
     */
    public static Field[] getFields(Class<?> beanClass, boolean useCache, String... ignoreFields) {
        if (useCache) {
            return FIELDS_CACHE.computeIfAbsent(beanClass, k -> getFieldsDirectly(beanClass, ignoreFields));
        } else {
            return getFieldsDirectly(beanClass, ignoreFields);
        }
    }

    /**
     * 获得一个类中所有字段列表，如果有重复的，只包含当前类的
     *
     * @param beanClass    类
     * @param ignoreFields 忽略的属性
     * @return 字段列表
     */
    public static List<Field> getDistinctFields(Class<?> beanClass, String... ignoreFields) {
        Field[] fields = getFields(beanClass, ignoreFields);
        Set<String> fieldNames = new HashSet<>();
        return Arrays.stream(fields).filter(field -> {
            boolean contains = fieldNames.contains(field.getName());
            fieldNames.add(field.getName());
            return !contains;
        }).toList();
    }

    /**
     * 只获取当前类的属性，默认使用缓存
     *
     * @param beanClass    类
     * @param ignoreFields 忽略的属性
     * @return 字段列表
     */
    public static Field[] getCurrentFields(Class<?> beanClass, String... ignoreFields) {
        return getCurrentFields(beanClass, true, ignoreFields);
    }

    /**
     * 只获取当前类的属性
     *
     * @param beanClass    类
     * @param useCache     是否使用缓存
     * @param ignoreFields 忽略的属性
     * @return 字段列表
     */
    public static Field[] getCurrentFields(Class<?> beanClass, boolean useCache, String... ignoreFields) {
        if (useCache) {
            return CURRENT_FIELDS_CACHE.computeIfAbsent(beanClass, k -> getCurrentFieldsDirectly(beanClass, ignoreFields));
        } else {
            return getCurrentFieldsDirectly(beanClass, ignoreFields);
        }
    }

    /**
     * 是否包含某个属性
     *
     * @param beanClass class
     * @param fieldName 属性名
     * @param useCache  是否使用缓存
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean containsField(Class<?> beanClass, String fieldName, boolean useCache) {
        return getField(beanClass, fieldName, useCache) != null;
    }

    /**
     * 是否包含某个属性，默认使用缓存
     *
     * @param beanClass class
     * @param fieldName 属性名
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean containsField(Class<?> beanClass, String fieldName) {
        return containsField(beanClass, fieldName, true);
    }


    /**
     * 是否包含某个属性,只查找当前类
     *
     * @param beanClass class
     * @param fieldName 属性名
     * @param useCache  是否使用缓存
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean containsCurrentField(Class<?> beanClass, String fieldName, boolean useCache) {
        return getCurrentField(beanClass, fieldName, useCache) != null;
    }

    /**
     * 是否包含某个属性，只查找当前类，默认使用缓存
     *
     * @param beanClass class
     * @param fieldName 属性名
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean containsCurrentField(Class<?> beanClass, String fieldName) {
        return containsCurrentField(beanClass, fieldName, true);
    }

    /**
     * 获取当前某个类的属性,不包括父类的， 使用缓存
     *
     * @param beanClass 类
     * @param fieldName field名称
     * @return Field
     */
    public static Field getCurrentField(Class<?> beanClass, String fieldName) {
        return getCurrentField(beanClass, fieldName, true);
    }

    /**
     * 获取某个类的属性，不包括父类的
     *
     * @param beanClass 类
     * @param fieldName field名称
     * @param useCache  是否使用缓存
     * @return Field
     */
    public static Field getCurrentField(Class<?> beanClass, String fieldName, boolean useCache) {
        Field[] fields = getCurrentFields(beanClass, useCache);
        return Arrays.stream(fields).filter(field -> Objects.equals(field.getName(), fieldName)).findFirst().orElse(null);
    }


    /**
     * 获取某个类的属性, 使用缓存
     *
     * @param beanClass 类
     * @param fieldName field名称
     * @return Field
     */
    public static Field getField(Class<?> beanClass, String fieldName) {
        return getField(beanClass, fieldName, true);
    }

    /**
     * 获取某个类的属性
     *
     * @param beanClass 类
     * @param fieldName field名称
     * @param useCache  是否使用缓存
     * @return Field
     */
    public static Field getField(Class<?> beanClass, String fieldName, boolean useCache) {
        Field[] fields = getFields(beanClass, useCache);
        return Arrays.stream(fields).filter(field -> Objects.equals(field.getName(), fieldName)).findFirst().orElse(null);
    }

    /**
     * 获取某个类的属性，返回Map，默认使用缓存
     *
     * @param beanClass    类
     * @param ignoreFields 忽略的属性
     * @return Map<String, Field>
     */
    public static Map<String, Field> getField2Map(Class<?> beanClass, String... ignoreFields) {
        return getField2Map(beanClass, true, ignoreFields);
    }

    /**
     * 获取某个类的属性，返回Map
     *
     * @param beanClass    类
     * @param useCache     是否使用缓存
     * @param ignoreFields 忽略的属性
     * @return Map<String, Field>
     */
    public static Map<String, Field> getField2Map(Class<?> beanClass, boolean useCache, String... ignoreFields) {
        return Arrays.stream(getFields(beanClass, useCache, ignoreFields))
                .collect(Collectors.toMap(Field::getName, field -> field));
    }

    /**
     * 将一个实体的数据按照某些字段名取出放入一个map
     *
     * @param obj        待转换的对象
     * @param needFields 需要转换的对象
     * @return java.util.Map
     * @date 2018/8/24
     * @since jdk1.8
     */
    public static Map<String, Field> getNeedField2Map(Object obj, String... needFields) {
        return Arrays.stream(getFields(obj.getClass())).filter(field -> ArrayUtils.contains(needFields, field.getName()))
                .collect(Collectors.toMap(Field::getName, field -> field));
    }

    private static Field[] getFieldsDirectly(Class<?> beanClass, String... ignoreFields) {
        Field[] fields = new Field[0];
        ignoreFields = ignoreFields == null ? new String[0] : ignoreFields;
        String[] finalIgnoreFields = ignoreFields;
        while (beanClass != Object.class) {
            Field[] declaredFields = beanClass.getDeclaredFields();
            declaredFields = Arrays.stream(declaredFields).filter(field -> !ArrayUtils.contains(finalIgnoreFields, field)).toArray(Field[]::new);
            if (ArrayUtils.isNotEmpty(declaredFields)) {
                fields = ArrayUtils.addAll(fields, declaredFields);
            }
            beanClass = beanClass.getSuperclass();
        }
        // 如果有名字重复的，只保留子类的
        Set<String> fieldNames = new HashSet<>();
        return Arrays.stream(fields).filter(field -> {
            boolean contains = fieldNames.contains(field.getName());
            fieldNames.add(field.getName());
            return !contains;
        }).toArray(Field[]::new);
    }

    private static Field[] getCurrentFieldsDirectly(Class<?> beanClass, String... ignoreFields) {
        ignoreFields = ignoreFields == null ? new String[0] : ignoreFields;
        String[] finalIgnoreFields = ignoreFields;
        Field[] declaredFields = beanClass.getDeclaredFields();
        return Arrays.stream(declaredFields).filter(field -> !ArrayUtils.contains(finalIgnoreFields, field)).toArray(Field[]::new);
    }

    /**
     * 通过field获取值,如果是静态的，obj传null
     *
     * @param field field
     * @param obj   对象，如果是静态的传null
     * @return Object
     */
    public static Object getValue(Field field, Object obj) throws IllegalAccessException {
        if (obj == null) {
            makeAccessible(field);
        } else {
            makeAccessible(field, obj);
        }
        return field.get(obj);
    }

    /**
     * 通过field获取值,默认使用缓存
     * 使用JDK17需要设置JVM参数--add-opens java.base/java.lang=ALL-UNNAMED
     *
     * @param obj       对象，如果获取静态属性，传空
     * @param clazz     class
     * @param fieldName 属性
     * @return Object
     */
    public static Object getValue(Object obj, Class clazz, String fieldName) throws IllegalAccessException {
        return getValue(obj, clazz, fieldName, true);
    }

    /**
     * 通过field获取值
     *
     * @param obj       对象，如果获取静态属性，传空
     * @param clazz     class
     * @param fieldName 属性
     * @param useCache  是否使用缓存
     * @return Object
     */
    public static Object getValue(Object obj, Class clazz, String fieldName, boolean useCache) throws IllegalAccessException {
        Field field = getField(clazz, fieldName, useCache);
        if (field == null) {
            throw new RuntimeException("无法获取field");
        }
        return getValue(field, obj);
    }

    /**
     * 通过field设置值,如果是静态的，obj传null
     *
     * @param field field
     * @param obj   对象，如果是静态的传null
     */
    public static void setValue(Field field, Object obj, Object value) throws IllegalAccessException {
        if (obj == null) {
            makeAccessible(field);
        } else {
            makeAccessible(field, obj);
        }
        field.set(obj, value);
    }

    /**
     * 通过field获取值,默认使用缓存
     *
     * @param obj       对象，如果获取静态属性，传空
     * @param clazz     class
     * @param fieldName 属性
     * @param value     属性值
     */
    public static void setValue(Object obj, Class clazz, String fieldName, Object value) throws IllegalAccessException {
        setValue(obj, clazz, fieldName, value, true);
    }

    /**
     * 通过field获取值
     *
     * @param obj       对象，如果获取静态属性，传空
     * @param clazz     class
     * @param fieldName 属性
     * @param value     属性值
     * @param useCache  是否使用缓存
     */
    public static void setValue(Object obj, Class clazz, String fieldName, Object value, boolean useCache) throws IllegalAccessException {
        Field field = getField(clazz, fieldName, useCache);
        if (field == null) {
            throw new RuntimeException("无法获取field");
        }
        setValue(field, obj, value);
    }

    /**
     * 获得一个类中所有函数，包括其父类中的函数，默认使用缓存
     *
     * @param beanClass 类
     * @return 函数列表
     */
    public static Method[] getMethods(Class<?> beanClass) {
        return getMethods(beanClass, true);
    }

    /**
     * 获得一个类中所有函数，包括其父类中的函数<br>
     *
     * @param beanClass 类
     * @param useCache  是否使用缓存
     * @return 函数列表
     */
    public static Method[] getMethods(Class<?> beanClass, boolean useCache) {
        if (useCache) {
            return METHODS_CACHE.computeIfAbsent(beanClass, k -> getMethodsDirectly(beanClass));
        } else {
            return getMethodsDirectly(beanClass);
        }
    }

    /**
     * 获得一个类的函数,默认使用缓存
     *
     * @param beanClass  类
     * @param methodName 方法名
     * @param paramTypes 方法参数
     * @return 函数列表
     */
    public static Method getMethod(Class<?> beanClass, String methodName, Class<?>... paramTypes) {
        return getMethod(beanClass, true, methodName, paramTypes);
    }

    /**
     * 获得一个类的函数
     *
     * @param beanClass  类
     * @param useCache   是否使用缓存
     * @param methodName 方法名
     * @param paramTypes 方法参数
     * @return 函数列表
     */
    public static Method getMethod(Class<?> beanClass, boolean useCache, String methodName, Class<?>... paramTypes) {
        Method[] methods = getMethods(beanClass, useCache);
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method method : methods) {
                String name = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (Objects.equals(name, methodName) && isAllAssignableFrom(parameterTypes, parameterTypes)) {
                    return method;
                }
            }
        }
        return null;
    }

    private static Method[] getMethodsDirectly(Class<?> beanClass) {
        if (beanClass.isInterface()) {
            return beanClass.getMethods();
        }
        Method[] methods = new Method[0];
        while (beanClass != Object.class) {
            Method[] declaredMethods = beanClass.getDeclaredMethods();
            if (ArrayUtils.isNotEmpty(declaredMethods)) {
                methods = ArrayUtils.addAll(methods, declaredMethods);
            }
            // 获取接口中的方法
            for (Class<?> ifc : beanClass.getInterfaces()) {
                for (Method m : ifc.getMethods()) {
                    if (!Modifier.isAbstract(m.getModifiers())) {
                        methods = ArrayUtils.add(methods, m);
                    }
                }
            }
            beanClass = beanClass.getSuperclass();
        }
        // 去重
        Set<String> set = new HashSet<>();
        List<Method> copyMethods = new ArrayList<>();
        String name;
        for (Method method : methods) {
            name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (ArrayUtils.isNotEmpty(parameterTypes)) {
                name += "%" + Arrays.stream(parameterTypes).map(Class::getName).collect(Collectors.joining("@"));
            }
            if (!set.contains(name)) {
                copyMethods.add(method);
            }
            set.add(name);
        }
        return copyMethods.toArray(Method[]::new);
    }


    /**
     * 反射执行一个对象的某个方法,不带参数
     *
     * @param obj    {@link Object} 任意一个对象
     * @param params 方法参数
     * @return java.lang.Object 方法返回的结果
     * @date 2018/7/13
     * @since jdk1.8
     */
    public static Object invokeMethod(Object obj, Method method, Object... params) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(obj, params);
    }

    /**
     * 是否包含某个方法
     *
     * @param beanClass  class
     * @param methodName 属性名
     * @param useCache   是否使用缓存
     * @param paramTypes 参数类型
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean containsMethod(Class<?> beanClass, String methodName, boolean useCache, Class<?>... paramTypes) {
        return getMethod(beanClass, useCache, methodName, paramTypes) != null;
    }

    /**
     * 是否包含某个方法,默认使用缓存
     *
     * @param beanClass  class
     * @param methodName 属性名
     * @param paramTypes 参数类型
     * @return boolean
     * @date 2022/6/30
     * @since jdk11
     */
    public static boolean containsMethod(Class<?> beanClass, String methodName, Class<?>... paramTypes) {
        return containsMethod(beanClass, methodName, true, paramTypes);
    }


    /**
     * 把一个Bean对象转换成Map对象
     *
     * @param obj     对象
     * @param ignores 忽略的fields
     * @return Map
     * @throws Exception 异常
     */
    public static Map convertBean2Map(Object obj, String[] ignores) throws Exception {
        Map map = new HashMap(16);
        Class clazz = obj.getClass();
        Field[] fields = getFields(clazz);
        Field field;
        for (Field item : fields) {
            field = item;
            // 定义fieldName是否在拷贝忽略的范畴内
            boolean flag = false;
            if (ignores != null && ignores.length != 0) {
                flag = isExistOfIgnores(field.getName(), ignores);
            }
            if (!flag) {
                Object value = getValue(obj, obj.getClass(), field.getName());
                if (null != value
                        && !StringUtils.isEmpty(value.toString())) {
                    map.put(field.getName(), value);
                }
            }
        }
        return map;
    }

    /**
     * 把一个Bean对象转换成Map对象</br>
     *
     * @param obj 对象
     * @return Map
     */
    public static Map convertBean2Map(Object obj) throws Exception {
        return convertBean2Map(obj, null);
    }

    /**
     * 把一个Map对象转换成Bean对象</br>
     *
     * @param map       map对象
     * @param beanClass class对象
     * @return Map
     */
    public static Object convertMap2Bean(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.getDeclaredConstructor().newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    /**
     * 实例化一个数组，一维的
     *
     * @param clazz 类型
     * @param len   长度
     * @return T[]
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> Object newInstanceArray(Class<T> clazz, int len) {
        return Array.newInstance(clazz, len);
    }

    /**
     * 实例化一个数组，多维的
     *
     * @param clazz 类型
     * @param len   长度
     * @return Object
     * @date 2022/6/29
     * @since jdk11
     */
    public static <T> Object newInstanceArray(Class<T> clazz, int... len) {
        return Array.newInstance(clazz, len);
    }

    /**
     * 判断fieldName是否是ignores中排除的
     *
     * @param fieldName fieldName
     * @param ignores   ignores
     * @return boolean
     */
    private static boolean isExistOfIgnores(String fieldName,
                                            String[] ignores) {
        boolean flag = false;
        for (String str : ignores) {
            if (str.equals(fieldName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName) throws Exception {
        // 构建一个可变字符串用来构建方法名称
        StringBuilder sb = new StringBuilder();
        Method setMethod;
        Method getMethod;
        PropertyDescriptor pd;
        boolean[] superC = new boolean[1];
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if (propertyName.equalsIgnoreCase(field.getName())) {
                superC[0] = true;
            }
        });
        Field f;
        //noinspection IfStatementWithIdenticalBranches
        if (superC[0]) {
            // 根据字段名来获取字段
            f = clazz.getDeclaredField(propertyName);
        } else {
            clazz = clazz.getSuperclass();
            //父类
            f = clazz.getDeclaredField(propertyName);
        }
        // 构建方法的后缀
        String methodEnd = propertyName.substring(0, 1).toUpperCase()
                + propertyName.substring(1);
        // 构建set方法
        sb.append("set").append(methodEnd);
        setMethod = clazz.getDeclaredMethod(sb.toString(),
                f.getType());
        // 清空整个可变字符串
        sb.delete(0, sb.length());
        // 构建get方法
        sb.append("get").append(methodEnd);
        // 构建get 方法
        getMethod =
                clazz.getDeclaredMethod(sb.toString());
        // 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
        pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
        return pd;
    }

    /**
     * 获取MethodHandle
     *
     * @param returnClass      返回值Class对象-void用void.class
     * @param clazz            方法所在的类
     * @param methodName       方法名
     * @param parameterClasses 方法参数类型
     * @return java.lang.invoke.MethodHandle
     * @throws NoSuchMethodException  noSuchMethodException
     * @throws IllegalAccessException illegalAccessException
     * @date 2021/10/27
     * @since jdk1.8
     */
    public static MethodHandle getMethodHandle(Class returnClass, Class clazz, String methodName, Class... parameterClasses) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(returnClass, parameterClasses);
        return MethodHandles.lookup().findVirtual(clazz, methodName, methodType);
    }

    /**
     * 获取静态方法的MethodHandle
     *
     * @param returnClass      返回值Class对象-void用void.class
     * @param clazz            方法所在的类
     * @param methodName       方法名
     * @param parameterClasses 方法参数类型
     * @return java.lang.invoke.MethodHandle
     * @throws NoSuchMethodException  noSuchMethodException
     * @throws IllegalAccessException illegalAccessException
     * @date 2021/10/31
     * @since jdk1.8
     */
    public static MethodHandle getStaticMethodHandle(Class returnClass, Class clazz, String methodName, Class... parameterClasses) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(returnClass, parameterClasses);
        return MethodHandles.lookup().findStatic(clazz, methodName, methodType);
    }

    /**
     * 获取构造器的MethodHandle
     *
     * @param clazz            构造器的类
     * @param parameterClasses 构造器参数
     * @return java.lang.invoke.MethodHandle
     * @throws NoSuchMethodException  noSuchMethodException
     * @throws IllegalAccessException illegalAccessException
     * @date 2021/10/31
     * @since jdk1.8
     */
    public static MethodHandle getConstructMethodHandle(Class clazz, Class... parameterClasses) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(void.class, parameterClasses);
        return MethodHandles.lookup().findConstructor(clazz, methodType);
    }

    /**
     * 获取Getter MethodType
     *
     * @param clazz     类
     * @param fieldName 成员变量名
     * @return java.lang.invoke.MethodHandle
     * @throws NoSuchFieldException   noSuch
     * @throws IllegalAccessException illegalAccessException
     * @date 2021/10/31
     * @since jdk1.8
     */
    public static MethodHandle getGetterMethodHandle(Class clazz, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        makeAccessible(field);
        return MethodHandles.lookup().unreflectGetter(field);
    }

    /**
     * 获取Setter MethodHandle
     *
     * @param clazz     构造器的类
     * @param fieldName 成员变量名
     * @return java.lang.invoke.MethodHandle
     * @throws NoSuchFieldException   noSuch
     * @throws IllegalAccessException illegalAccessException
     * @date 2021/10/31
     * @since jdk1.8
     */
    public static MethodHandle getSetterMethodHandle(Class clazz, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        //防止被漏洞软件扫描出漏洞，更改授权方式 add by zqw 2021-12-08
        ReflectUtils.makeAccessible(field);
        return MethodHandles.lookup().unreflectSetter(field);
    }

    /**
     * 通过Method获取 MethodHandle
     *
     * @param method 方法
     * @return java.lang.invoke.MethodHandle
     * @throws IllegalAccessException illegalAccessException
     * @date 2021/10/31
     * @since jdk1.8
     */
    public static MethodHandle getMethodHandle(Method method) throws IllegalAccessException {
        return MethodHandles.lookup().unreflect(method);
    }

    /**
     * 调用某个属性的get方法
     *
     * @param obj          对象
     * @param propertyName 属性名称
     * @return java.lang.Object
     * @date 2022/6/30
     * @since jdk11
     */
    public static Object invokeGet(Object obj, String propertyName) throws Exception {
        assert obj != null;
        assert propertyName != null;
        Class clazz = obj.getClass();
        Object result;
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz);
        Method rM = pd.getReadMethod();
        result = rM.invoke(obj);
        return result;
    }

    /**
     * 比较判断types1和types2两组类，如果types1中所有的类都与types2对应位置的类相同，或者是其父类或接口，则返回{@code true}
     *
     * @param types1 类组1
     * @param types2 类组2
     * @return 是否相同、父类或接口
     */
    private static boolean isAllAssignableFrom(Class<?>[] types1, Class<?>[] types2) {
        if (ArrayUtils.isEmpty(types1) && ArrayUtils.isEmpty(types2)) {
            return true;
        }
        if (null == types1 || null == types2) {
            // 任何一个为null不相等（之前已判断两个都为null的情况）
            return false;
        }
        if (types1.length != types2.length) {
            return false;
        }

        Class<?> type1;
        Class<?> type2;
        for (int i = 0; i < types1.length; i++) {
            type1 = types1[i];
            type2 = types2[i];
            boolean b = (isWrapClass(type1) && isPrimitive(type2)) ||
                    (isPrimitive(type1) && isWrapClass(type2));
            if (b) {
                // 原始类型和包装类型存在不一致情况
                return false;
            } else if (!type1.isAssignableFrom(type2)) {
                return false;
            }
        }
        return true;
    }


}
