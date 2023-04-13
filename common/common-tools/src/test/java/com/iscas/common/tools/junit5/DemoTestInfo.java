package com.iscas.common.tools.junit5;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * //构造函数和方法的依赖注入
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 16:22
 * @since jdk1.8
 */
@DisplayName("TestInfo Demo")
public class DemoTestInfo {
    DemoTestInfo(TestInfo testInfo) {
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        Assertions.assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        Assertions.assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2() {
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }
}

class TestReporterDemo {

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a status message");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
    }
}