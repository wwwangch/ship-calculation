package com.iscas.common.tools.core.classloader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 类加载器测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/11 10:07
 * @since jdk1.8
 */
public class JarClassloaderTests {
//    String jar2_5 = "C:/Users/Administrator/Desktop/jar/base2/2.5";
    String jar2_6 = "C:/Users/Administrator/Desktop/jar/base2/2.6/base2-2.6-RELEASE.jar";

    /**
     * 测试classloader，不带缓存，不从缓存读类的字节数组，也不存入缓存
     * */
    @Test
    @Disabled
    public void test2() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        JarLoader jarLoader2 = new JarLoader(new String[]{"H:/ideaProjects/integration-dev/ig-bootstrap/src/main/resources" +
                "/component/mysqlreader.jar"});
        ClassLoaderSwapper classLoaderSwapper2 = ClassLoaderSwapper.newCurrentThreadClassLoaderSwapper();
        classLoaderSwapper2.setCurrentThreadClassLoader(jarLoader2);
        Class<?> bClass = JarLoader.outerLoadClass("com.iscas.datasong.ig.component.mysqlreader.MysqlReader");
        Object o2 = bClass.getDeclaredConstructor().newInstance();

        classLoaderSwapper2.restoreCurrentThreadClassLoader();
        Method initMethod = bClass.getDeclaredMethod("init", Map.class);
        HashMap<String, Object> params2 = new HashMap<>();
        Object invoke2 = initMethod.invoke(o2, params2);

    }

    /**
     * 测试加载mysql-reader, 带缓存
     * */
    @Test
    @Disabled
    public void test3() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        System.out.println("=========================开始测试，读取指定jar包=========================");
        JarLoader jarLoader = new JarLoader(new String[]{"H:/ideaProjects/integration-dev/ig-bootstrap/src/main/resources/" +
                "component/mysqlreader.jar"});
        ClassLoaderSwapper classLoaderSwapper = ClassLoaderSwapper.newCurrentThreadClassLoaderSwapper();
        classLoaderSwapper.setCurrentThreadClassLoader(jarLoader);
        try {
            Class<?> adClass = JarLoader.outerLoadClass("com.iscas.datasong.ig.component.mysqlreader.MysqlReader");
//        Class<?> aClass = Class.forName("com.iscas.test.base2.MyEntity");
            Object o = adClass.getDeclaredConstructor().newInstance();

            Method initMethod = adClass.getDeclaredMethod("init", Map.class);
            HashMap<String, Object> params = new HashMap<>();
            Object invoke = initMethod.invoke(o, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            classLoaderSwapper.restoreCurrentThreadClassLoader();
        }





    }


    /**
     * 测试classloader，不带缓存，不从缓存读类的字节数组，也不存入缓存
     * */
    @Test
    @Disabled
    public void test4() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        JarLoader jarLoader2 = new JarLoader(new String[]{"H:/ideaProjects/integration-dev/ig-bootstrap/src/main/resources" +
                "/component/csvreader.jar"});
        ClassLoaderSwapper classLoaderSwapper2 = ClassLoaderSwapper.newCurrentThreadClassLoaderSwapper();
        classLoaderSwapper2.setCurrentThreadClassLoader(jarLoader2);
        Class<?> bClass = JarLoader.outerLoadClass("com.iscas.datasong.ig.component.csvreader.CsvReader");
        Object o2 = bClass.getDeclaredConstructor().newInstance();


        Method initMethod = bClass.getDeclaredMethod("init", Map.class);
        HashMap<String, Object> params2 = new HashMap<>();
        Object invoke2 = initMethod.invoke(o2, params2);
        classLoaderSwapper2.restoreCurrentThreadClassLoader();
    }

    /**
     * 测试classloader
     * */
    @Test
    public void testx() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        JarLoader jarLoader2 = new JarLoader(new String[]{"C:\\ideaProjects\\data-middle-office-branch-test\\dmo-biz\\dmo-db\\dmo-db-invoke\\src\\main\\" +
                "resources\\dboperates\\dmo-db-sftp-0.0.1.jar"});
        ClassLoaderSwapper classLoaderSwapper2 = ClassLoaderSwapper.newCurrentThreadClassLoaderSwapper();
        classLoaderSwapper2.setCurrentThreadClassLoader(jarLoader2);
        Class<?> bClass = JarLoader.outerLoadClass("cn.ac.iscas.dmo.db.sftp.SftpOperate");
        Object o2 = bClass.getDeclaredConstructor().newInstance();

        classLoaderSwapper2.restoreCurrentThreadClassLoader();
        Method initMethod = bClass.getDeclaredMethod("dbAlreadyInitialized", String.class);
        Object invoke2 = initMethod.invoke(o2, "test");
        System.out.println(invoke2);

    }

}
