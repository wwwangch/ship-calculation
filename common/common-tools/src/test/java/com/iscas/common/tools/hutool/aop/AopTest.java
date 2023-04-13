package com.iscas.common.tools.hutool.aop;

/**
 * 动态代理测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/30 9:33
 * @since jdk1.8
 */

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.Aspect;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * AOP模块单元测试
 * 自动选择代理方式，如果用户引入Cglib那么使用Cglib作代理，
 * 如果没有引入则使用JDK代理
 *
 */

public class AopTest {

    /**测试有接口，代理，使用工具内实现的TimeIntervalAspect*/
    @Test
    public void aopTest() {
        Animal cat = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
        String result = cat.eat();
        Assertions.assertEquals("猫吃鱼", result);
    }

    /**测试无接口，代理，使用工具内实现的TimeIntervalAspect*/
    @Test
    public void aopByCglibTest() {
        Dog dog = ProxyUtil.proxy(new Dog(), TimeIntervalAspect.class);
        String result = dog.eat();
        Assertions.assertEquals("狗吃肉", result);
    }

    /**代理，使用自定义Aspect*/
    @Test
    public void aopTest2() {
        TestClass testClass = ProxyUtil.proxy(new TestClass(), MyAspect.class);
        String result = testClass.testMethod("abcdefg");
        Assertions.assertEquals("gfedcba", result);
    }

    static interface Animal {
        String eat();
    }

    /**
     * 有接口
     *
     * @author looly
     *
     */
    static class Cat implements Animal {

        @Override
        public String eat() {
            return "猫吃鱼";
        }
    }

    /**
     * 无接口
     *
     * @author looly
     *
     */
    static class Dog {
        public String eat() {
            return "狗吃肉";
        }
    }


    static class TestClass{
        public String testMethod(String str){
//            if(1 == new Random().nextInt(2)){
//                throw new RuntimeException("error");
//            }
            return StringUtils.reverse(str);
        }
    }

    static class MyAspect implements Aspect{

        @Override
        public boolean before(Object target, Method method, Object[] args) {
            System.out.println("method before execute::::");
            System.out.println("target:" + target);
            System.out.println("method:" + method);
            System.out.println("args:" + Arrays.toString(args)) ;
            return true;
        }

        @Override
        public boolean after(Object target, Method method, Object[] args, Object returnVal) {
            System.out.println("method after execute:::");
            System.out.println("target:" + target);
            System.out.println("method:" + method);
            System.out.println("args:" + Arrays.toString(args)) ;
            return true;
        }

        @Override
        public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
            System.out.println("method error");
            return false;
        }
    }
}
