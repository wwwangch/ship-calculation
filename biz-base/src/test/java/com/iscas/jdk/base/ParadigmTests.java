package com.iscas.jdk.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/1 13:53
 * @since jdk1.8
 */
public class ParadigmTests {
    @Test
    public void test1() {
        List<? extends Number> list = null;
        list = new ArrayList<Number>(); //正常
        list = new ArrayList<Float>();//正常
//        list = new ArrayList<Object>(); //编辑报错，必须是Number类型或其子类型
    }

    @Test
    public void test2() {
        List<? super   Number> list = null;
        list = new ArrayList<Number>(); //正常
//        list = new ArrayList<Float>();//编辑报错，必须是Number类型或其子类型
        list = new ArrayList<Object>(); //正常
    }

    @Test
    public void test3() {

    }

    static class A {
        public String name;
        public Integer age;

        public <T> T initT(Class<T> t) throws IllegalAccessException, InstantiationException {
//            t = new T();//编译报错，不能实例化泛型的对象
            T t1 = t.newInstance(); //编译可以通过
            return t1;
        }
    }

    static class B<T>{
        public T t1;
//        public static T t2; //编译报错
    }
}
