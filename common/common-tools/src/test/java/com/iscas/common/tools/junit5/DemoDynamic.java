package com.iscas.common.tools.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * //动态测试
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 8:58
 * @since jdk1.8
 */
public class DemoDynamic {

    // This will result in a JUnitException!
    @TestFactory
    List<String> dynamicTestsWithInvalidReturnType() {
        return Arrays.asList("Hello");
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> Assertions.assertTrue(isPalindrome("madam"))),
                dynamicTest("2nd dynamic test", () -> Assertions.assertEquals(4, 2 * 2))
        );
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestsFromIterable() {
        return Arrays.asList(
                dynamicTest("3rd dynamic test", () -> Assertions.assertTrue(isPalindrome("madam"))),
                dynamicTest("4th dynamic test", () -> Assertions.assertEquals(4, 2 * 2))
        );
    }

    @TestFactory
    Iterator<DynamicTest> dynamicTestsFromIterator() {
        return Arrays.asList(
                dynamicTest("5th dynamic test", () -> Assertions.assertTrue(isPalindrome("madam"))),
                dynamicTest("6th dynamic test", () -> Assertions.assertEquals(4, 2 * 2))
        ).iterator();
    }

    @TestFactory
    DynamicTest[] dynamicTestsFromArray() {
        return new DynamicTest[] {
                dynamicTest("7th dynamic test", () -> Assertions.assertTrue(isPalindrome("madam"))),
                dynamicTest("8th dynamic test", () -> Assertions.assertEquals(4, 2 * 2))
        };
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Stream.of("racecar", "radar", "mom", "dad")
                .map(text -> dynamicTest(text, () -> Assertions.assertTrue(isPalindrome(text))));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromIntStream() {
        // Generates tests for the first 10 even integers.
        return IntStream.iterate(0, n -> n + 2).limit(10)
                .mapToObj(n -> dynamicTest("test" + n, () -> Assertions.assertTrue(n % 2 == 0)));
    }

    @TestFactory
    Stream<DynamicTest> generateRandomNumberOfTestsFromIterator() {

        // Generates random positive integers between 0 and 100 until
        // a number evenly divisible by 7 is encountered.
        Iterator<Integer> inputGenerator = new Iterator<Integer>() {

            Random random = new Random();
            int current;

            @Override
            public boolean hasNext() {
                current = random.nextInt(100);
                return current % 7 != 0;
            }

            @Override
            public Integer next() {
                return current;
            }
        };

        // Generates display names like: input:5, input:37, input:85, etc.
        Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

        // Executes tests based on the current input value.
        ThrowingConsumer<Integer> testExecutor = (input) -> Assertions.assertTrue(input % 7 != 0);

        // Returns a stream of dynamic tests.
        return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStreamFactoryMethod() {
        // Stream of palindromes to check
        Stream<String> inputStream = Stream.of("racecar", "radar", "mom", "dad");

        // Generates display names like: racecar is a palindrome
        Function<String, String> displayNameGenerator = text -> text + " is a palindrome";

        // Executes tests based on the current input value.
        ThrowingConsumer<String> testExecutor = text -> Assertions.assertTrue(isPalindrome(text));

        // Returns a stream of dynamic tests.
        return DynamicTest.stream(inputStream, displayNameGenerator, testExecutor);
    }

    @TestFactory
    Stream<DynamicNode> dynamicTestsWithContainers() {
        return Stream.of("A", "B", "C")
                .map(input -> DynamicContainer.dynamicContainer("Container " + input, Stream.of(
                        dynamicTest("not null", () -> Assertions.assertNotNull(input)),
                        DynamicContainer.dynamicContainer("properties", Stream.of(
                                dynamicTest("length > 0", () -> Assertions.assertTrue(input.length() > 0)),
                                dynamicTest("not empty", () -> Assertions.assertFalse(input.isEmpty()))
                        ))
                )));
    }

    @TestFactory
    DynamicNode dynamicNodeSingleTest() {
        return dynamicTest("'pop' is a palindrome", () -> Assertions.assertTrue(isPalindrome("pop")));
    }

    @TestFactory
    DynamicNode dynamicNodeSingleContainer() {
        return DynamicContainer.dynamicContainer("palindromes",
                Stream.of("racecar", "radar", "mom", "dad", "asvcdf")
                        .map(text -> dynamicTest(text, () -> Assertions.assertTrue(isPalindrome(text)))
                        ));
    }
    //回文
    public static boolean isPalindrome(String str) {
        //test for end of recursion
        if (str.length() < 2) {
            return true;
        }

        //check first and last character for equality
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }

        //recursion call
        return isPalindrome(str.substring(1, str.length() - 1));
    }
}
