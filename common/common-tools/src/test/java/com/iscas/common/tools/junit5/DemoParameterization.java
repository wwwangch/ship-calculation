package com.iscas.common.tools.junit5;

import com.iscas.common.tools.core.date.DateSafeUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.*;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.iscas.common.tools.junit5.DemoDynamic.isPalindrome;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

/**
 * //参数化测试 @ParameterizedTest
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 16:42
 * @since jdk1.8
 */
@Slf4j
public class DemoParameterization {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        assertTrue(isPalindrome(candidate));
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        Assertions.assertNotNull(unit);
    }

    @ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = { "ERAS", "FOREVER" })
    void testWithEnumSourceExclude(ChronoUnit unit) {
        Assertions.assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
    }

    @ParameterizedTest
    @EnumSource(mode = MATCH_ALL, names = "^.*DAYS$")
    void testWithEnumSourceRegex(ChronoUnit unit) {
        assertTrue(unit.name().endsWith("DAYS"));
    }

    static Stream<Integer> numSort() {
        return Stream.of(1, 2, 3, 4, 5, 6).sorted();
    }
    @ParameterizedTest
    @MethodSource("numSort")
    void printNum(int num) {
        Assertions.assertNotEquals(num, 9, "");
        System.out.println(num);
    }
    //DoubleStream，IntStream和LongStream
    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        Assertions.assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(DemoArg arg) {
        List<String> ls = arg.getLs();
        int num = arg.getNum();
        String val = arg.getVal();
        Assertions.assertEquals(5, val.length());
        Assertions.assertTrue(num >=1 && num <=2);
        Assertions.assertEquals(2, ls.size());
    }

    static Stream<DemoArg> stringIntAndListProvider() {
        return Stream.of(
                new DemoArg("apple", 1, Arrays.asList("a", "b")),
                new DemoArg("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @MethodSource("com.iscas.common.tools.junit5.DemoTestInfo#stringProvider")
    void testWithExternalMethodSource(String tinyString) {
        System.out.println(tinyString);
    }

    @ParameterizedTest
    @CsvSource({
            "apple,1",
            "banana,2",
            "'lemon, lime',0xF1"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromClasspath(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFile(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }
    public static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("apple", "banana").map(Arguments::of);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) throws ParseException {
        Person person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.getString(2),
                DateSafeUtils.parse(arguments.getString(3), "yyyy-MM-dd"));

        if (person.getFirstName().equals("Jane")) {
            assertEquals("F", person.getCode());
        }
        else {
            assertEquals("M", person.getCode());
        }
        assertEquals("Doe", person.getLastName());
        Date time = person.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        assertEquals(1990, calendar.get(Calendar.YEAR));
    }
    //自定义
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
        System.out.println(person);
    }
    public static class PersonAggregator implements ArgumentsAggregator {
        @SneakyThrows
        @Override
        public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
            return new Person(arguments.getString(0),
                    arguments.getString(1),
                    arguments.getString(2),
                    DateSafeUtils.parse(arguments.getString(3), "yyyy-MM-dd"));
        }
    }
}
