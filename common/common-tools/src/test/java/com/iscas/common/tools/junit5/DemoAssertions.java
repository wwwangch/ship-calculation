package com.iscas.common.tools.junit5;

import com.iscas.common.tools.core.date.DateSafeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * //Assertions方法测试
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 13:57
 * @since jdk1.8
 */
public class DemoAssertions {
    private final Person person = new Person("Jane", "Doe", "M", DateSafeUtils.parse("2010-01-19", "yyyy-MM-dd"));

    public DemoAssertions() throws ParseException {
    }

    @Test
    void standardAssertions() {
        Assertions.assertEquals(2, 1 + 1);
        Assertions.assertEquals(4, 2 * 2,
                "The optional failure message is now the last parameter");
        Assertions.assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        Assertions.assertAll("person",
                () -> Assertions.assertEquals("Jane", person.getFirstName()),
                () -> Assertions.assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        Assertions.assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    Assertions.assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    Assertions.assertAll("first name",
                            () -> Assertions.assertTrue(firstName.startsWith("J")),
                            () -> Assertions.assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    Assertions.assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    Assertions.assertAll("last name",
                            () -> Assertions.assertTrue(lastName.startsWith("D")),
                            () -> Assertions.assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () ->
                System.out.println(1 / 0));
        Assertions.assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        Assertions.assertTimeout(Duration.ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = Assertions.assertTimeout(Duration.ofMinutes(2), () -> {
            return "a result";
        });
        Assertions.assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = Assertions.assertTimeout(Duration.ofMinutes(2), DemoAssertions::greeting);
        Assertions.assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        //毫秒
        Assertions.assertTimeout(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

    private static String greeting() {
        return "Hello, World!";
    }
}
