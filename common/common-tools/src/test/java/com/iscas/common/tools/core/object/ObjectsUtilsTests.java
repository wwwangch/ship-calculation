package com.iscas.common.tools.core.object;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/18 10:26
 * @since jdk1.8
 */
public class ObjectsUtilsTests {

    private static List<A> as = new ArrayList<>();

    @BeforeAll
    public static void before() {
        C c1 = new C();
        c1.setP1(11);
        c1.setP2("xxx");
        HashSet<Double> set = new HashSet<>();
        set.add(6.7);
        set.add(7.9);
        c1.setP3(set);
        Map<String, Float> map = new HashMap<>();
        map.put("xxx", 5.0f);
        map.put("yyy", 5.6f);
        c1.setP4(map);

        C c2 = new C();
        c2.setP1(11);
        c2.setP2("xxx");
        HashSet<Double> set2 = new HashSet<>();
        set2.add(6.77);
        set2.add(17.9);
        c2.setP3(set);
        Map<String, Float> map2 = new HashMap<>();
        map2.put("zxxx", 19.3f);
        map2.put("weg", 5.8f);
        c2.setP4(map);

        List<C> objects = new ArrayList<>();
        objects.add(c1);
        objects.add(c2);
        B b = new B();
        b.setP1(1);
        b.setP2(c1);
        b.setP3(objects);

        A a = new A();
        a.setP1("cwegw");
        a.setP2(3);
        List<String> lll = new ArrayList<>();
        lll.add("xxx");
        a.setP3(lll);
        HashMap<String, B> mapx = new HashMap<>();
        mapx.put("w4", b);
        a.setP4(mapx);

        as.add(a);

    }

    /**
     * 测试单个对象
     * */
    @Test
    public void test() throws Exception {
        System.out.println("--------深度克隆测试 begin---------");
        System.out.println("==================原来的====================");
        System.out.println(as.get(0));

        A copyA = ObjectUtils.deepCopy(as.get(0), A.class, true, null);

        System.out.println("==================原来的====================");
        System.out.println(copyA);
        System.out.println("--------深度克隆测试 end---------");
    }

    @Test
    public void test2() throws Exception {
        System.out.println("--------深度克隆测试List begin---------");
        System.out.println("==================原来的====================");
        System.out.println(as);

        List<A> copyA = ObjectUtils.deepCopy(as, List.class, true, null);

        System.out.println("==================原来的====================");
        System.out.println(copyA);
        System.out.println("--------深度克隆测试List end---------");
    }





}
