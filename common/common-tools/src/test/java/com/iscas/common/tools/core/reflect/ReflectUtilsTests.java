package com.iscas.common.tools.core.reflect;

import com.iscas.common.tools.core.reflect.reflectTest.A;
import com.iscas.common.tools.core.reflect.reflectTest.A1;
import com.iscas.common.tools.core.reflect.reflectTest.A11;
import com.iscas.common.tools.core.reflect.reflectTest.A2;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.JavaBean;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.*;

/**
 * 增强反射工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
public class ReflectUtilsTests {
    private static A a = null;

    //初始化测试数据
    private static A11 getA11(){
        SecureRandom random = new SecureRandom();
        A11 a11x = new A11();
        a11x.setData(new float[]{3,4,5.6f,4.4545f,random.nextFloat()});
        Map map1 = new HashMap<>();
        map1.put("wge","ewg" + UUID.randomUUID());
        map1.put("weg",null);
        a11x.setMap(map1);
        a11x.setX1("weg");
        a11x.setX2(232.3346 + random.nextDouble());
        a11x.setX4(34643.45777f);
        return a11x;
    }
    private static A2 getA2(){
        SecureRandom random = new SecureRandom();
        A2 a2 = new A2();
        a2.setX1(22 + random.nextInt(12));
        a2.setX2(235.2f);
        return a2;
    }
    static {
        a = new A();
        A1 a1 = new A1();
        a1.setA11List(Arrays.asList(getA11(),getA11(),getA11()));
        a.setA1(a1);
        a.setA2List(Arrays.asList(getA2(), getA2()));
        Map map1 = new HashMap();
        map1.put("qe",getA2());
        map1.put("wewg", getA2());
        a.setMap(map1);
    }

//    /**
//     * 通过反射获取某个对象的某个方法
//     * */
//    @Test
//    public void test1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        System.out.println("--------反射测试1 begin---------");
//        int hash = (int) ReflectUtils.invokeMethod(a, "getA1Hash");
//        System.out.println(hash);
//        System.out.println("--------反射测试1 end---------");
//    }
//
//    /**
//     * 通过反射获取某个对象的某个方法，并执行携带参数
//     * */
//    @Test
//    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        System.out.println("--------反射测试2 begin---------");
//        String result = (String) ReflectUtils.invokeMethodWithParam(a, "xxx","x",34,new float[]{3,5,6,5.6f,56.78f});
//        System.out.println(result);
//        System.out.println("--------反射测试2 end---------");
//    }

    /**判断一个类是不是基本数据类型*/
    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("--------反射测试3 begin---------");
        Integer a = 4;
        boolean flag = ReflectUtils.isWrapClass(a.getClass());
        Assertions.assertEquals(flag, true);
        boolean flag1 = ReflectUtils.isWrapClass(A.class);
        Assertions.assertEquals(flag1, false);
        System.out.println("--------反射测试3 end---------");
    }

//    /**
//     * 获取一个类和其父类的所有属性
//     * */
//    @Test
//    public void test4(){
//        System.out.println("--------反射测试4 begin---------");
//        List<Field> fields = ReflectUtils.findAllFieldsOfSelfAndSuperClass(A1111.class);
//        System.out.println(fields);
//        System.out.println("--------反射测试4 end---------");
//    }

    /**
     * 获取一个class的构造器
     * */
    @Test
    public void test5() throws NoSuchMethodException {
        HashMap<Object, Object> map = new HashMap<>();
        Constructor<? extends HashMap> construct = ReflectUtils.getConstructor(map.getClass(), int.class);
        Assertions.assertNotNull(construct);
        try {
            Constructor<? extends HashMap> construct2 = ReflectUtils.getConstructor(map.getClass(), Integer.class);
            System.out.println(construct2);
        } catch (Exception ignored) {
            ignored.printStackTrace();
            // 基本数据类型不能用包装类
        }
    }

    /**
     * 获取一个class的所有构造器
     * */
    @Test
    public void test6() {
        HashMap<Object, Object> map = new HashMap<>();
        Constructor<? extends HashMap>[] construct = ReflectUtils.getConstructors(map.getClass());
        System.out.println(Arrays.toString(construct));
    }

    /**
     * 获取一个class的构造器.不使用缓存
     * */
    @Test
    public void test7() throws NoSuchMethodException {
        HashMap<Object, Object> map = new HashMap<>();
        Constructor<? extends HashMap> constructor = ReflectUtils.getConstructor(map.getClass(), false, int.class);
        System.out.println(constructor);
    }


    /**
     * 实例化一个对象
     * */
    @Test
    public void test8() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<Object, Object> map = new HashMap<>();
        LinkedHashMap<Class<?>, Object> params = new LinkedHashMap<>();
        params.put(int.class, 32);
        HashMap hashMap = ReflectUtils.newInstance(map.getClass(), true, params);
        System.out.println(hashMap);
        HashMap hashMap2 = ReflectUtils.newInstance(map.getClass(), true, params);
        System.out.println(hashMap2);

        // 不使用缓存
        HashMap hashMap3 = ReflectUtils.newInstance(map.getClass(), false, params);
        System.out.println(hashMap3);

        // 不存在此构造器
        params.put(String.class, "xx");
        try {
            HashMap hashMap4 = ReflectUtils.newInstance(map.getClass(), false, params);
            System.out.println(hashMap4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性
     * */
    @Test
    public void test9() {
        Field[] fields = ReflectUtils.getFields(new HashMap<>().getClass());
        System.out.println(Arrays.toString(fields));
    }

    /**
     * 获取某一个属性
     * */
    @Test
    public void test10() {
        String s = "weweg";
        System.out.println(ReflectUtils.getField(String.class, "hash"));
    }

    /**
     * 获取属性
     * */
    @Test
    public void test11() {
        System.out.println(ReflectUtils.getField2Map(String.class));
    }

    /**
     * 获取属性值
     *
     * */
    @Test
    public void test12() throws IllegalAccessException {
        System.out.println(ReflectUtils.getValue(null, String.class, "serialPersistentFields"));
    }

    /**
     * 设置属性值
     * */
    @Test
    public void test13() throws IllegalAccessException {
        TestM testM = new TestM();
        testM.setId(1);
        testM.setName("张三");
        ReflectUtils.setValue(testM, TestM.class, "name", "李四");
        System.out.println(ReflectUtils.getValue(testM, TestM.class, "name"));
    }

    /**
     * 是否为父类引用字段
     * */
    @Test
    public void test14() {
        Assertions.assertTrue(Arrays.stream(ReflectUtils.getFields(TestM2.TestM3.class)).anyMatch(ReflectUtils::isOuterClassField));
    }

    /**
     * 获取method
     * */
    @Test
    public void test15() {
        System.out.println(Arrays.toString(ReflectUtils.getMethods(TestM2.class, true)));
    }

    /**
     * 获取method
     * */
    @Test
    public void test16() {
        Method method = ReflectUtils.getMethod(TestM2.class, "test");
        System.out.println(method);
    }


    /**
     * 调用method
     * */
    @Test
    public void test17() throws InvocationTargetException, IllegalAccessException {
        Method method = ReflectUtils.getMethod(TestM2.class, "test");
        TestM2 testM2 = new TestM2();
        ReflectUtils.invokeMethod(testM2, method);
    }

    /**
     * 是否包含某个属性
     * */
    @Test
    public void test18() {
        boolean flag = ReflectUtils.containsField(TestM2.class, "test");
        Assertions.assertFalse(flag);
    }

    /**
     * 是否包含某个方法
     * */
    @Test
    public void test19() {
        boolean flag = ReflectUtils.containsMethod(TestM.class, "test");
        Assertions.assertTrue(flag);
    }

    /**
     * 测试修改final属性
     * */
    @Test
    public void test20() throws NoSuchFieldException, IllegalAccessException {
        TestM testM = new TestM();
        Field aaaField = ReflectUtils.getField(TestM.class, "aaa");
        ReflectUtils.makeFinalModifiers(aaaField);
        ReflectUtils.setValue(aaaField, testM, "yyy");
        ReflectUtils.setValue(aaaField, testM, "zzz");
        ReflectUtils.makeFinalModifiers(aaaField);
        ReflectUtils.setValue(aaaField, testM, "aaa");
        System.out.println(testM);

        Field bbbField = ReflectUtils.getField(TestM.class, "bbb");
        ReflectUtils.makeFinalModifiers(bbbField);
        ReflectUtils.setValue(bbbField, testM, 2);
        System.out.println(testM);
    }



    /**
     *  实例化数组
     * */
    @Test
    public void test21() {
        TestM[] testMS = (TestM[]) ReflectUtils.newInstanceArray(TestM.class, 10);
        System.out.println(Arrays.toString(testMS));
        Class testMClass = TestM.class;
        Object[] objects = (Object[]) ReflectUtils.newInstanceArray(testMClass, 20);
        System.out.println(Arrays.toString(objects));

        int[] integers = (int[]) ReflectUtils.newInstanceArray(int.class, 20);
        System.out.println(Arrays.toString(integers));

        String[][] strArray = (String[][]) ReflectUtils.newInstanceArray(String.class, 20, 20);
        System.out.println(Arrays.toString(strArray));
    }


    /**
     *  获取注解
     * */
    @Test
    public void test22() {
        boolean annotationPresentCurrent = ReflectUtils.isAnnotationPresentCurrent(TestM.class, JavaBean.class);
        Assertions.assertTrue(annotationPresentCurrent);
        Annotation annotation = ReflectUtils.getAnnotation(TestM.class, JavaBean.class);
        System.out.println(annotation);
        Annotation[] annotations = ReflectUtils.getAnnotations(TestM.class);
        System.out.println(annotations[0]);
    }

    @Data
    @JavaBean
    public static class TestM {
        private Integer id;
        private String name;
        public final String aaa = new String("xxx");
        public final Integer bbb = 1;
        public void test() {
            System.out.println(2222);
        }
    }

    @Data
    public static class TestM2 extends TestM {
        @Override
        public void test() {
            System.out.println(3333);
        }
        @Data
        public class TestM3 {
            private String realName;
        }
    }

}
