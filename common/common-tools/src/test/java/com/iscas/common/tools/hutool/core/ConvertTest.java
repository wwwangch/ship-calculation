package com.iscas.common.tools.hutool.core;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 类型转换测试
 *
 * Convert类可以说是一个工具方法类，里面封装了针对Java常见类型的转换，用于简化类型转换。
 * Convert类中大部分方法为toXXX，参数为Object，可以实现将任意可能的类型转换为指定类型。
 * 同时支持第二个参数defaultValue用于在转换失败时返回一个默认值。
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 15:57
 * @since jdk1.8
 */
public class ConvertTest {
    /**
     *测试特定类型的转换通用方式
     */
    @Test
    public void test1(){
        Object obj = 123;

        Object object = Convert.convert(Object.class, obj);
        Assertions.assertEquals(123, object);

        String str = Convert.convert(String.class, obj);
        Assertions.assertEquals("123",str);

        Long lon = Convert.convert(Long.class, obj);
        Assertions.assertEquals(123, lon.longValue());

        Object[] objects = new Object[]{"123","456","125"};
        List<Integer> list = Convert.convert(List.class, objects);
        System.out.println(list);
    }

    /**
     * 测试特定类型转换 特定方式
     * */
    @Test
    public void test2(){
        Object obj = 123;
        String s = Convert.toStr(obj);
        Assertions.assertEquals("123",s);

        Character[] characters = Convert.toCharArray("123");
        System.out.println(Arrays.toString(characters));

        Number number = Convert.toNumber(obj);
        Assertions.assertEquals(123, number);

        BigDecimal bigDecimal = Convert.toBigDecimal(obj);
        Assertions.assertEquals(new BigDecimal(123),bigDecimal);

        Long aLong = Convert.toLong(obj);
        Assertions.assertEquals(123, aLong.longValue());

        Object[] objects = new Object[]{3,4,5,6,7,8};
        Integer[] integers = Convert.toIntArray(objects);
        System.out.println(Arrays.toString(integers));

        List<Integer> list1 = (List<Integer>) Convert.toList(Integer.class, objects);
        System.out.println(list1);

        Integer[] integers1 = Convert.toIntArray(list1);
        System.out.println(Arrays.toString(integers1));

        byte b = Convert.intToByte(123);
        Assertions.assertEquals(123, b);

        byte[] bytes = Convert.intToBytes(123);
        System.out.println(Arrays.toString(bytes));

        int i = Convert.bytesToInt(new byte[]{2, 3, 4, 5});
        System.out.println(i);

    }

    /**
     * 半全角转换
     * */
    @Test
    public void test3(){
        String str = ".w,we.wewge12456";

        String s = Convert.toSBC(str);
        System.out.println(s);
        String s1 = Convert.toDBC(str);
        System.out.println(s1);
    }

    /**
     * 16进制转化
     * */
    @Test
    public void test4(){
        String str = "this is test 这是一个测试";
        String s = Convert.toHex(str, CharsetUtil.CHARSET_UTF_8);
        System.out.println(s);

        byte[] bytes = Convert.hexToBytes(s);
        System.out.println(Arrays.toString(bytes));

        String s1 = Convert.hexToStr(s, CharsetUtil.CHARSET_UTF_8);
        System.out.println(s1);
    }

    /**
     * Unicode和字符串转换
     * */
    @Test
    public void test5(){
        String str = "this is test这是一个测试";
        String s = Convert.strToUnicode(str);
        System.out.println(s);

        String s1 = Convert.unicodeToStr(s);
        System.out.println(s1);
    }

    /**
     * 编码转化
     * */
    @Test
    public void test6(){
        String str = "我不是乱码";
        String s = Convert.convertCharset(str, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        System.out.println(s);

        String s1 = Convert.convertCharset(s, CharsetUtil.ISO_8859_1, CharsetUtil.UTF_8);
        System.out.println(s1);
    }
    /**
     * 时间转化
     * */
    @Test
    public void test7(){
        long time = System.currentTimeMillis();
        System.out.println(time);
        long l = Convert.convertTime(time, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println(l);

        String str = "2016-05-01 17:15:16";
        Date date = Convert.toDate(str);
        System.out.println(date);

    }

    /**
     * 金钱大小写转化
     * */
    @Test
    public void test8(){
        float money = 158.45F;
        String s = Convert.digitToChinese(money);
        System.out.println(s);
        Assertions.assertEquals("壹佰伍拾捌元肆角伍分",s);
    }

    /**
     * 原始类和包装类转化
     * */
    @Test
    public void test9(){
        Class<?> aClass = Convert.unWrap(Integer.class);
        System.out.println(aClass);
        Assertions.assertEquals(int.class, aClass);

        Class<?> wrap = Convert.wrap(int.class);
        System.out.println(wrap);
        Assertions.assertEquals(Integer.class, wrap);

    }

    /**
     * Map和Bean相互转化
     * */
    @Test
    public void test10(){
        A a = new A();
        a.setId("1");
        a.setName("zhangsan");
        a.setAge(12);
        B b = new B();
        b.setId("1");
        b.setName("lisi");
        a.setB(b);
        a.setBs(Arrays.asList(new B("1","wangwu"), new B("2", "zhaoliu")));
        Map mapx = new HashMap();
        mapx.put("a","A");
        a.setMap(mapx);

        Map map = Convert.convert(Map.class, a);
        System.out.println(map);


        A a2 = Convert.convert(A.class, map);
        System.out.println(a2);

        LinkedHashMap<?, ?> linkedHashMap = Convert.convert(LinkedHashMap.class, map);
        System.out.println(linkedHashMap);
    }

    @Data
    @ToString
    private static class A extends PA{
        private String id;
        private String name;
        private B b;
        private List<B> bs;
        private Map map;
    }

    @Data
    @ToString
    private static class PA{
        private Integer age;
    }
    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class B{
        private String id;
        private String name;

    }
}
