package com.iscas.common.tools.core.object;

import com.iscas.common.tools.core.reflect.ReflectUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 对象操作工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/18 9:29
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class ObjectUtils {
    private static final String JAVA_TIME = "java.time";
    private ObjectUtils() {
    }

    /**
     * 有些属性有特殊用处，不需要拷贝
     */
    @SuppressWarnings("FieldMayBeFinal")
    private static String[] skipFieldGlobal = new String[]{"serialVersionUID"};

    /**
     * 深度克隆
     *
     * @param oriObj      对象
     * @param targetClass 返回的数据Class对象
     * @return T
     * @throws Exception exception
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static <T> T deepCopy(Object oriObj, Class<T> targetClass) throws Exception {
        return deepCopy(oriObj, targetClass, true, null);
    }

    /**
     * 深度克隆
     *
     * @param oriObj              对象
     * @param targetClass         返回数据的class对象
     * @param ignoreNonExistField 忽略的属性
     * @return T
     * @throws Exception exception
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static <T> T deepCopy(Object oriObj, Class<T> targetClass, boolean ignoreNonExistField) throws Exception {
        return deepCopy(oriObj, targetClass, ignoreNonExistField, null);
    }


    /**
     * <p>实现对象的深度拷贝,主要处理自定义类加载器加载的对象的类与当前JVM中类名字一样无法赋值的问题</p>
     * <p>使用反射创建新对象的方式递归注入新的属性，实例化采用了无参构造器，所以目标对象必须有无参构造器或默认无参构造器</p>
     * <p>支持集合、数组、Map类型以及各种嵌套</p>
     *
     * @param oriObj              待拷贝的对象
     * @param targetClass         目标结果类型，支持基本数据类型、包装类、Stirng、数组、枚举、Collection接口下的所有集合，Map接口下的所有集合，
     *                            其他类型都认为是普通JavaBean，如果是普通JavaBean，不要传入接口类型，无法实例化
     * @param ignoreNonExistField 如果为true, 忽略targetClass中有，但未在oriObj中找到的属性，会把值置为null
     *                            如果为fase,在oriObj中找不到此属性会抛出异常
     * @param skipFields          某些不必要的转化的字段可以跳过，可以为null
     * @return T
     * @throws Exception exception
     * @date 2020/3/18
     * @since jdk1.8
     */
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public static <T> T deepCopy(Object oriObj, Class<T> targetClass, boolean ignoreNonExistField, String[] skipFields) throws Exception {

        if (targetClass == null) {
            throw new RuntimeException(String.format("targetClass is required for '%s'", oriObj == null ? null : oriObj.getClass()));
        }
        if (oriObj == null) {
            return null;
        }
        T result = null;

        if (ReflectUtils.isWrapClass(targetClass) || targetClass == String.class ||
                targetClass.isPrimitive()) {
            //如果是基本数据类型，或者String，或者包装类型
            result = (T) oriObj;
        } else if (Collection.class.isAssignableFrom(targetClass)) {
            //集合
            Collection targetCollection;
            if (targetClass == List.class) {
                targetCollection = new ArrayList<>();
            } else if (targetClass == Set.class) {
                targetCollection = new HashSet<>();
            } else {
                targetCollection = (Collection) targetClass.getDeclaredConstructor().newInstance();
            }
            Collection oriCollection = (Collection) oriObj;
            for (Object next : oriCollection) {
                Class<?> aClass1 = next.getClass();
                Class<?> aClass = forName(aClass1, new HashMap<>(16));
                targetCollection.add(deepCopy(next, aClass, ignoreNonExistField, skipFields));
            }
            result = (T) targetCollection;
        } else if (Map.class.isAssignableFrom(targetClass)) {
            //map
            Map targetMap;
            if (targetClass == Map.class) {
                targetMap = new HashMap<>(16);
            } else {
                targetMap = (Map) targetClass.getDeclaredConstructor().newInstance();
            }
            Map<Object, Object> oriMap = (Map<Object, Object>) oriObj;
            for (Map.Entry<Object, Object> entry : oriMap.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                Object newKey;
                Object newValue;
                if (key == null) {
                    newKey = key;
                } else {
                    Class<?> keyClass = key.getClass();
                    //class.forname一下，获取主运行程序内的Class对象
                    Class<?> newKeyClass = forName(keyClass, new HashMap<>(16));
                    //递归调用
                    newKey = deepCopy(key, newKeyClass, ignoreNonExistField, skipFields);
                }

                if (value == null) {
                    newValue = value;
                } else {
                    Class<?> valueClass = value.getClass();
                    //class.forname一下，获取主运行程序内的Class对象
                    Class<?> newValueClass = forName(valueClass);
                    //递归调用
                    newValue = deepCopy(value, newValueClass, ignoreNonExistField, skipFields);
                }

                targetMap.put(newKey, newValue);
            }
            result = (T) targetMap;
        } else if (targetClass.isArray()) {
            //数组
            int length = Array.getLength(oriObj);
            Class<?> componentType = targetClass.getComponentType();
            Class<?> aClass = forName(componentType);
            Object newArray = Array.newInstance(aClass, length);
            for (int i = 0; i < length; i++) {
                Object o1 = Array.get(oriObj, i);
                Object newo1;
                Class<?> keyClass = o1.getClass();
                Class<?> newKeyClass = forName(keyClass);
                //递归调用
                newo1 = deepCopy(o1, newKeyClass, ignoreNonExistField, skipFields);
                Array.set(newArray, i, newo1);
            }
            result = (T) newArray;

        } else if (targetClass.isEnum()) {
            T[] enumConstants = targetClass.getEnumConstants();
            //使用枚举所在的序列复制
            Method method = oriObj.getClass().getMethod("ordinal");
            Object ordinal = method.invoke(oriObj);
            if (ArrayUtils.isNotEmpty(enumConstants)) {
                for (T enumConstant : enumConstants) {
                    Method oriOrdinalMethod = enumConstant.getClass().getMethod("ordinal");
                    Object oriOrdinal = oriOrdinalMethod.invoke(enumConstant);
                    if (Objects.equals(ordinal, oriOrdinal)) {
                        //如果序列一致，认为是同一个枚举
                        result = enumConstant;
                        break;
                    }
                }
            }

        } else {
            //其他的都认为是对象
            Field[] declaredFields = ReflectUtils.getCurrentFields(targetClass);
            if (ArrayUtils.isNotEmpty(declaredFields)) {
                T targetObj = targetClass.getDeclaredConstructor().newInstance();
                //获取待复制对象的class
                Class<?> oriClass = oriObj.getClass();
                label:
                for (Field targetField : declaredFields) {
                    //防止被漏洞软件扫描出漏洞，更改授权方式 add by zqw 2021-12-08
                    ReflectUtils.makeAccessible(targetField);
                    String name = targetField.getName();
                    //跳过某些属性
                    if (ArrayUtils.isNotEmpty(skipFieldGlobal)) {
                        for (String s : skipFieldGlobal) {
                            if (Objects.equals(s, name)) {
                                continue label;
                            }
                        }
                    }
                    if (ArrayUtils.isNotEmpty(skipFields)) {
                        for (String skipField : skipFields) {
                            if (Objects.equals(skipField, name)) {
                                continue label;
                            }
                        }
                    }

                    Field oriField = null;
                    try {
                        oriField = ReflectUtils.getCurrentField(oriClass, name);
                    } catch (Exception ignored) {
                    }
                    if (oriField == null) {
                        if (!ignoreNonExistField) {
                            throw new RuntimeException(String.format("属性：'%s' 在原对象中不存在", name));
                        } else {
                            targetField.set(targetObj, null);
                            continue;
                        }
                    }
                    Object oriData = ReflectUtils.getValue(oriField, oriObj);

                    Class<?> type = targetField.getType();
                    Object o = deepCopy(oriData, type, ignoreNonExistField, skipFields);

                    //设置属性
                    targetField.set(targetObj, o);
                }
                result = targetObj;
            }
        }
        return result;

    }

    private static Class<?> forName(Class<?> clazz) throws ClassNotFoundException {
        return forName(clazz, new HashMap<>(16));
    }

    private static Class<?> forName(Class<?> clazz, Map<String, Class> paramClassMap) throws ClassNotFoundException {
        Class<?> result;
        if (ReflectUtils.isWrapClass(clazz) || clazz == String.class ||
                clazz.isPrimitive()) {
            //如果是基本数据类型，或者String，或者包装类型
            result = clazz;
        } else {
            result = paramClassMap.get(clazz.getName());
            if (result == null) {
                result = Class.forName(clazz.getName());
            }
        }
        return result;
    }

    @SuppressWarnings("ConstantConditions")
    public static <T> T deepCopy(Object oriObj, Class<T> targetClass, boolean ignoreNonExistField, String[] skipFields, Map<String, Class> paramClassMap) throws Exception {

        if (targetClass == null) {
            throw new RuntimeException(String.format("targetClass is required for '%s'", oriObj == null ? null : oriObj.getClass()));
        }
        if (oriObj == null) {
            return null;
        }
        T result = null;

        if (ReflectUtils.isWrapClass(targetClass) || targetClass == String.class ||
                targetClass.isPrimitive()) {
            //如果是基本数据类型，或者String，或者包装类型
            result = (T) oriObj;
        } else if (Collection.class.isAssignableFrom(targetClass)) {
            //集合
            Collection targetCollection;
            if (targetClass == List.class) {
                targetCollection = new ArrayList<>();
            } else if (targetClass == Set.class) {
                targetCollection = new HashSet<>();
            } else {
                targetCollection = (Collection) targetClass.getDeclaredConstructor().newInstance();
            }
            Collection oriCollection = (Collection) oriObj;
            for (Object next : oriCollection) {
                Class<?> aClass1 = next.getClass();
                Class<?> aClass = forName(aClass1, paramClassMap);
                targetCollection.add(deepCopy(next, aClass, ignoreNonExistField, skipFields, paramClassMap));

            }
            result = (T) targetCollection;
        } else if (Map.class.isAssignableFrom(targetClass)) {
            //map
            Map targetMap;
            if (targetClass == Map.class) {
                targetMap = new HashMap<>(16);
            } else {
                targetMap = (Map) targetClass.getDeclaredConstructor().newInstance();
            }
            Map<Object, Object> oriMap = (Map<Object, Object>) oriObj;
            for (Map.Entry<Object, Object> entry : oriMap.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                Object newKey;
                Object newValue;
                if (key == null) {
                    newKey = key;
                } else {
                    Class<?> keyClass = key.getClass();
                    Class<?> newKeyClass = forName(keyClass);
                    //递归调用
                    newKey = deepCopy(key, newKeyClass, ignoreNonExistField, skipFields, paramClassMap);
                }

                if (value == null) {
                    newValue = value;
                } else {
                    Class<?> valueClass = value.getClass();
                    Class<?> newValueClass = forName(valueClass);
                    //递归调用
                    newValue = deepCopy(value, newValueClass, ignoreNonExistField, skipFields, paramClassMap);
                }

                targetMap.put(newKey, newValue);
            }
            result = (T) targetMap;
        } else if (targetClass.isArray()) {
            //数组
            int length = Array.getLength(oriObj);
            Class<?> componentType = targetClass.getComponentType();
            Class<?> aClass = forName(componentType);
            Object newArray = Array.newInstance(aClass, length);
            for (int i = 0; i < length; i++) {
                Object o1 = Array.get(oriObj, i);
                Object newo1;
                Class<?> keyClass = o1.getClass();
                Class<?> newKeyClass = forName(keyClass);
                //递归调用
                newo1 = deepCopy(o1, newKeyClass, ignoreNonExistField, skipFields, paramClassMap);
                Array.set(newArray, i, newo1);
            }
            result = (T) newArray;

        } else if (targetClass.isEnum()) {
            T[] enumConstants = targetClass.getEnumConstants();
            //使用枚举所在的序列复制
            Method method = oriObj.getClass().getMethod("ordinal");
            Method oriOrdinalMethod = oriObj.getClass().getMethod("ordinal");
            Object ordinal = method.invoke(oriObj);
            if (ArrayUtils.isNotEmpty(enumConstants)) {
                for (T enumConstant : enumConstants) {

                    Object oriOrdinal = oriOrdinalMethod.invoke(enumConstant);
                    if (Objects.equals(ordinal, oriOrdinal)) {
                        //如果序列一致，认为是同一个枚举
                        result = enumConstant;
                        break;
                    }
                }
            }

        } else {
            // 时间类型的不做处理
            if (targetClass.getName().startsWith(JAVA_TIME) ||
                    "java.util.Date".equals(targetClass.getName()) ||
                    "java.sql.Date".equals(targetClass.getName())) {
                return (T) oriObj;
            }

            //其他的都认为是对象
            List<Field> declaredFieldList = Arrays.stream(ReflectUtils.getFields(targetClass)).toList();
            Field[] declaredFields = declaredFieldList.toArray(Field[]::new);
            if (ArrayUtils.isNotEmpty(declaredFields)) {
                T targetObj = targetClass.getDeclaredConstructor().newInstance();
                //获取待复制对象的class
                Class<?> oriClass = oriObj.getClass();
                label:
                for (Field targetField : declaredFields) {
                    String name = targetField.getName();
                    //跳过某些属性
                    if (ArrayUtils.isNotEmpty(skipFieldGlobal)) {
                        for (String s : skipFieldGlobal) {
                            if (Objects.equals(s, name)) {
                                continue label;
                            }
                        }
                    }
                    if (ArrayUtils.isNotEmpty(skipFields)) {
                        for (String skipField : skipFields) {
                            if (Objects.equals(skipField, name)) {
                                continue label;
                            }
                        }
                    }

                    Field oriField = null;
                    try {
                        //noinspection OptionalGetWithoutIsPresent
                        oriField = Arrays.stream(ReflectUtils.getFields(oriClass)).filter(field -> field.getName().equals(name)).findFirst().get();
                    } catch (Exception ignored) {
                    }
                    if (oriField == null) {
                        if (!ignoreNonExistField) {
                            throw new RuntimeException(String.format("属性：'%s' 在原对象中不存在", name));
                        } else {
                            targetField.set(targetObj, null);
                            continue;
                        }
                    }
                    Object oriData = ReflectUtils.getValue(oriField, oriObj);

                    if (oriData != null) {
                        //目标类型
                        Class type = paramClassMap.get(oriData.getClass().getName());
                        if (type == null) {
                            type = oriData.getClass();
                        }
                        Object o = deepCopy(oriData, type, ignoreNonExistField, skipFields, paramClassMap);
                        //防止被漏洞软件扫描出漏洞，更改授权方式 add by zqw 2021-12-08
                        ReflectUtils.makeAccessible(targetField);
                        //设置属性
                        targetField.set(targetObj, o);
                    }

                }
                result = targetObj;
            }
        }
        return result;

    }


}
