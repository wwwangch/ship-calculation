package com.iscas.common.tools.junit5;

import org.junit.jupiter.api.*;

/**
 * //@Tag 表示为将测试类或者测试方法进行自定名称的标记，使用了标记后可以通过这些标记用来过滤、测试、发现和执行
 * //@Test 表示该方法是一个测试方法。与JUnit 4的@Test注解不同的是，它没有声明任何属性，因为JUnit Jupiter中的测试扩展是基于它们自己的专用注解来完成的。
 * //@BeforeEach 表示了在每一个测试方法（测试方法表示为所有了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法）之前执行。根据如上接口可以看到每次执行@Test方法之前都会先执行@BeforeEach注解的方法一次（此方法执行输出结果为BeforeEach）。注意不要与@BeforeAll概念混淆，@BeforeEach会每一个测试方法执行之前都会执行，@BeforeAll只会执行一次
 * //@AfterEach 表示在每一个测试方法执行之后都会执行
 * //@DisplayName 表示为测试类或测试方法声明了一个自定义显示的名称
 * //@Disabled 表示用来禁止整个测试类或者测试方法的执行
 * //@Nested 使用了这个注解的测试类表示这是一个嵌套的测试类，什么是嵌套测试类呢通俗的说就是在测试类中嵌套一个非静态的测试类（即内部类），并且可以嵌套多层
 * //@EnabledOnOs 表示允许在指定的操作系统上运行指定的测试类或者测试方法
 * //@DisabledOnOs 表示禁止在指定的操作系统上运行指定的测试类或者测试方法。
 * //@EnabledOnJre 表示运行在指定版本java的运行环境上运行测试类或者方法。
 * //@DisabledOnJre 表示禁止在指定版本java的运行环境上运行测试类或者方法。
 * //@EnabledIfSystemProperty 表示允许在满足JVM系统属性值的情况下运行测试类或者方法。其中name表示属性值的key，matches表示属性值是否匹配（可以用正则表达式）
 * //@DisabledIfSystemProperty 表示禁止在满足JVM系统属性值的情况下运行测试类或者方法。其中name表示属性值的key，matches表示属性值是否匹配（可以用正则表达式）
 * //@EnabledIfEnvironmentVariable 表示运允许在满足底层操作系统属性值的情况下运行测试类或者方法。其中name表示属性值的key，matches表示属性值是否匹配（可以用正则表达式）
 * //@DisabledIfEnvironmentVariable 表示运禁止在满足底层操作系统属性值的情况下运行测试类或者方法。其中name表示属性值的key，matches表示属性值是否匹配（可以用正则表达式）
 * //@ParameterizedTest 参数化测试
 * //@RepeatedTest 重复测试
 * //@TestFactory 动态测试
 * //@BeforeAll 表示使用了该注解的方法应该在当前类中所有使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之前执行 对应junit4的@BeforeClass
 * //@AfterAll 表示使用了该注解的方法应该在当前类中所有使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之后执行  对应junit4的@AfterClass
 * //@ExtendWith 用于注册自定义扩展
 * //@TestMethodOrder 确定类中的方法执行顺序
 *
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 9:58
 * @since jdk1.8
 */
@Tag("flag")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("基本注解")
public class BasicNotes {
    @Test
    @Tag("A")
    void a() {
        System.out.println("A");;
    }

    @Test
    @Tag("B")
    @DisplayName("起别名")
    void b() {
        Assertions.fail("a failing tes");
    }

    @Test
    @Disabled
    void c() {
        System.out.println("B");;
    }

    @BeforeAll
    static void initAll() {
        System.out.println("方法之前执行只执行一次");
    }

    @BeforeEach
    void init() {
        System.out.println("init");
    }

    @Test
    @Order(3)
    void d() {
        Assumptions.assumeTrue("abc".contains("d"));
        Assertions.fail("test should have been aborted");
    }

    @Test
    @Order(2)
    void e() {
        Assumptions.assumeTrue("abc".contains("a"));
        Assertions.fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("方法之后执行只执行一次");
    }
}
