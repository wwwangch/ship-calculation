package com.iscas.common.tools.core.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/1 14:40
 * @since jdk1.8
 */
public class LambdaTests {

    @Test
    public void test1() {
        /**
         * Intermediate：
         * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
         *
         * Terminal：
         * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
         *
         * Short-circuiting：
         * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
         * */

        //1.Collector.toCollection
        LinkedList<Integer> list1 = Stream.of(1, 2, 3).collect(Collectors.toCollection(LinkedList::new));
        System.out.println("测试Collector.toCollection:");
        list1.forEach(System.out::println);

        //2.IntStream
        System.out.println("测试IntStream:");
        IntStream.of(1, 2, 3).forEach(System.out::println);

        //3.DoubleStream
        System.out.println("测试DoubleStream:");
        DoubleStream.of(1.0, 2.1, 3.3).forEach(System.out::println);

        //4.IntStream range
        System.out.println("测试IntStream.range:");
        IntStream.range(1, 10).forEach(System.out::println);

        //5.Collector.joining
        System.out.println("测试Collector.joining:");
        String s = IntStream.range(1, 10).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));
        System.out.println(s);

        //6.mapToInt
        System.out.println("测试mapToInt");
        Stream.of("1", "2", "3", "4", "5").mapToInt(Integer::parseInt).forEach(System.out::println);

        //7.flatMap
        System.out.println("测试flatMap");
        Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 4))
                .flatMap(list -> list.stream())
                .forEach(System.out::println);

        //8.distinct
        System.out.println("测试distinct");
        Stream.of(1, 2, 2, 3, 4, 5).distinct().forEach(System.out::println);

        //9.sorted
        System.out.println("测试sorted");
        Stream.of(5, 3, 5, 1, 5, 6).distinct().sorted((i1, i2) -> i1 < i2 ? -1 : 1).forEach(System.out::println);

        //10.peek
        System.out.println("测试peek");
        Stream.of(5, 3, 5, 1, 5, 6).peek(System.out::println).collect(Collectors.toList());

        //11.limit
        System.out.println("测试limit");
        Stream.of(5, 3, 5, 1, 5, 6).limit(3).forEach(System.out::println);

        //12.skip
        System.out.println("测试skip");
        Stream.of(5, 3, 5, 1, 5, 6).skip(3).forEach(System.out::println);

        //13.parallel
        System.out.println("测试parallel");
        //并行不按顺序输出
        Stream.of(5, 3, 5, 1, 5, 6).parallel().forEach(System.out::println);

        //14.sequential
        System.out.println("测试sequential");
        Stream.of(5, 3, 5, 1, 5, 6).sequential().forEach(System.out::println);

        //15.unordered
        System.out.println("测试unordered");
        Stream.of(5, 3, 5, 1, 5, 6).map(i -> i + 1).unordered().forEach(System.out::println);

        //16.forEachOrdered
        System.out.println("测试forEachOrdered");
        Stream.of(5, 3, 5, 1, 5, 6).parallel().forEachOrdered(System.out::println);

        //17.toArray
        System.out.println("测试toArray");
        Object[] objects = Stream.of(5, 3, 5, 1, 5, 6).toArray();
        System.out.println(Arrays.toString(objects));

        //18.reduce-sum
        System.out.println("测试reduce-sum");
        System.out.println(Stream.of(1, 2, 3, 4, 5).reduce(10, Integer::sum));

        //19.reduce-sum2
        System.out.println("测试reduce-sum2");
        Stream.of(1, 2, 3, 4, 5, 6).reduce((x, y) -> x + y).ifPresent(System.out::println);

        //20.reduce-max
        System.out.println("测试reduce-max");
        Stream.of(1, 2, 3, 4, 5, 6).reduce(Integer::max).ifPresent(System.out::println);

        //21.max
        System.out.println("测试max");
        Stream.of(1, 2, 3, 4, 5, 6).max((x, y) -> x < y ? -1 : 1).ifPresent(System.out::println);

        //22.min
        System.out.println("测试min");
        Stream.of(1, 2, 3, 4, 5, 6).min((x, y) -> x < y ? -1 : 1).ifPresent(System.out::println);

        //23.count
        System.out.println("测试count");
        long count = Stream.of(1, 2, 3, 4, 5, 6).count();
        System.out.println(count);

        //24.anyMatch
        System.out.println("测试anyMatch");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).anyMatch(i -> i < 5));

        //25.allMatch
        System.out.println("测试allMatch");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).allMatch(i -> i < 5));

        //26.noneMatch
        System.out.println("测试noneMatch");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).noneMatch(i -> i < 5));

        //27.findFirst
        System.out.println("测试findFirst");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).findFirst());

        //28.findAny
        System.out.println("测试findAny");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).findAny());

        //29.iterator
        System.out.println("测试iterator");
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).iterator());

    }
}
