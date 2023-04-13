package com.iscas.common.tools.tuples;

import org.javatuples.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/21 21:42
 * @since jdk1.8
 */
public class JavaTuplesTests {
    @Test
    public void test() {
        //最多支持10个元素：
        //
        //Unit<A> (1 element)
        //Pair<A,B> (2 elements)
        //Triplet<A,B,C> (3 elements)
        //Quartet<A,B,C,D> (4 elements)
        //Quintet<A,B,C,D,E> (5 elements)
        //Sextet<A,B,C,D,E,F> (6 elements)
        //Septet<A,B,C,D,E,F,G> (7 elements)
        //Octet<A,B,C,D,E,F,G,H> (8 elements)
        //Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
        //Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)



        // 1元组
        Unit<String> u = new Unit<String>("rensanning.iteye.com");
        // 2元组
        Pair<String,Integer> p = Pair.with("rensanning.iteye.com", 9527);
        // 3元组
        Triplet<String,Integer,Double> triplet = Triplet.with("rensanning.iteye.com", 9527, 1.0);

        KeyValue<String,String> kv = KeyValue.with("rensanning.iteye.com", "9527");
        LabelValue<String,String> lv = LabelValue.with("rensanning.iteye.com", "9527");

        System.out.println(u.getValue0());
        System.out.println(p.getValue0());

    }
}
