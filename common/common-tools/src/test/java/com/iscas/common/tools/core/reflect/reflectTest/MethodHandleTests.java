package com.iscas.common.tools.core.reflect.reflectTest;

import com.iscas.common.tools.core.reflect.ReflectUtils;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * MethodHandle
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/27 18:03
 * @since jdk1.8
 */
public class MethodHandleTests {


    public MethodHandleTests() {

    }



    public void test(String name) {
        System.out.println(MessageFormat.format("你好 {0}", name));
    }

    public static void testStatic(String name) {
        System.out.println(MessageFormat.format("你好 {0}", name));
    }

    public String testExact(String name) {
        return "你好 " + name;
    }

    //测试调用普通的函数
    @Test
    public void _test() throws Throwable {
        MethodHandle methodHandle = ReflectUtils.getMethodHandle(void.class, MethodHandleTests.class, "test", String.class);
        methodHandle.invokeExact(new MethodHandleTests(), "张三");
    }

    //测试调用静态的函数
    @Test
    public void _testStatic() throws Throwable {
        MethodHandle methodHandle = ReflectUtils.getStaticMethodHandle(void.class, MethodHandleTests.class, "testStatic", String.class);
        methodHandle.invokeExact("张三");
    }

    //测试使用构造器
    @Test
    public void _testConstruct() throws Throwable {
        MethodHandle constructMethodHandle1 = ReflectUtils.getConstructMethodHandle(MethodHandleTests.class);
        Object methodHandle1 = constructMethodHandle1.invoke();
        System.out.println(methodHandle1);
    }

    //测试getter
    @Test
    public void _testGetter() throws Throwable {
        MethodHandle constructMethodHandle1 = ReflectUtils.getGetterMethodHandle(A.class, "a1");
        A a = new A();
        a.setA1(new A1());
        Object a1 = constructMethodHandle1.invoke(a);
        System.out.println(a1);
    }

    //测试setter
    @Test
    public void _testSetter() throws Throwable {
        MethodHandle constructMethodHandle1 = ReflectUtils.getSetterMethodHandle(A.class, "a1");
        A a = new A();
        constructMethodHandle1.invoke(a, new A1());
        System.out.println(a.getA1());
    }

    //测试通过Method获取MethodHandler
    @Test
    public void _testMethod() throws Throwable {
        Method method = MethodHandleTests.class.getDeclaredMethod("test", String.class);
        MethodHandle methodHandle = ReflectUtils.getMethodHandle(method);
        methodHandle.invoke(new MethodHandleTests(), "张三");
    }

    //  进行类型匹配的基本规则是对比返回值类型和每个参数的类型是否都可以相互匹配。假设源类型为S，目标类型为T，则基本规则如下：
    //        1、可以通过java的类型转换来完成，一般从子类转成父类，比如从String到Object类型；
    //        2、可以通过基本类型的转换来完成，只能将类型范围的扩大，比如从int切换到long；
    //        3、可以通过基本类型的自动装箱和拆箱机制来完成，例如从int到Integer；
    //        4、如果S有返回值类型，而T的返回值类型为void，则S的返回值会被丢弃。
    //        5、如果S的返回值是void，而T的返回值是引用类型，T的返回值会是null；
    //        6、如果S的返回值是void，而T的返回值是基本类型，T的返回值会是0；
    //测试invoke和invokeExact
    @Test
    public void _testExact() throws Throwable {
        MethodHandle methodHandle = ReflectUtils.getMethodHandle(String.class, MethodHandleTests.class, "testExact", String.class);
        MethodHandleTests methodHandleTests = new MethodHandleTests();
        Object str1 = methodHandle.invoke(methodHandleTests, "张三");
        System.out.println("返回值类型自动转换为Object>>>>" + str1);
        try {
            Object str2 = methodHandle.invokeExact(methodHandleTests, "张三");
        } catch (Throwable e) {
            System.out.println(">>>>不能自动将返回值String转为Object");
        }
        Object str3 = (String) methodHandle.invokeExact(methodHandleTests, "张三");
        System.out.println("invokeExact需要调用一个强转>>>>" + str3);
    }

}
