package com.iscas.common.tools.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18 15:02
 * @since jdk1.8
 */
public class DemoConditions {
    //操作系统条件
    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMacOs() {
        // ...
        System.out.println("MAC");
    }

    @TestOnMac
    void testOnMac() {
        // ...
        System.out.println("自定义:MAC");
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void onLinuxOrMac() {
        // ...
        System.out.println("OS.LINUX, OS.MAC");
    }

    @Test
    @DisabledOnOs({OS.WINDOWS})
    void notOnWindows() {
        // ...
        System.out.println("WINDOWS");
    }

    @Test
    @DisplayName("WINDOWS才执行")
    @EnabledOnOs({OS.WINDOWS})
    void OnWindows() {

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(OS.MAC)
    @interface TestOnMac {
    }

    //Java运行时环境条件
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        // ...
        System.out.println("JRE.JAVA_8");
    }

    @Test
    @EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
    void onJava9Or10() {
        // ...
        System.out.println("JRE.JAVA_9, JRE.JAVA_10");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_11)
    void fromJava9to11() {
        // ...
        System.out.println("min = JRE.JAVA_9, max = JRE.JAVA_11");

    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_9)
    void fromJava9toCurrentJavaFeatureNumber() {
        // ...
        System.out.println("JRE.JAVA_9");

    }

    @Test
    @EnabledForJreRange(max = JRE.JAVA_11)
    void fromJava8To11() {
        // ...
        System.out.println("JRE.JAVA_11");

    }

    @Test
    @DisabledOnJre(JRE.JAVA_9)
    void notOnJava9() {
        // ...
        System.out.println("JRE.JAVA_9");

    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_11)
    void notFromJava9to11() {
        // ...
        System.out.println("min = JRE.JAVA_9, max = JRE.JAVA_11");

    }

    //系统属性条件
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        // ...
    }

    @Test
    @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    void notOnCiServer() {
        // ...
    }

    //环境变量条件
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() {
        // ...
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
    void notOnDeveloperWorkstation() {
        // ...
    }

    //自订条件
    @Test
    @EnabledIf("customCondition")
    void enabled() {
        // ...
    }

    @Test
    @DisabledIf("customCondition")
    void disabled() {
        // ...
    }

    boolean customCondition() {
        return true;
    }
}
