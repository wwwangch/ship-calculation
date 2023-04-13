package com.iscas.common.tools.core.object;

import java.util.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/18 11:10
 * @since jdk1.8
 */
class A {
    public A() {}
    private String p1;
    private int p2;
    private List<String> p3 = new ArrayList<>();
    private Map<String, B> p4 = new HashMap<>();
    private TestEnum p5 = TestEnum.A;
    private int[] arr = new int[10];

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public List<String> getP3() {
        return p3;
    }

    public void setP3(List<String> p3) {
        this.p3 = p3;
    }

    public Map<String, B> getP4() {
        return p4;
    }

    public void setP4(Map<String, B> p4) {
        this.p4 = p4;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public TestEnum getP5() {
        return p5;
    }

    public void setP5(TestEnum p5) {
        this.p5 = p5;
    }

    @Override
    public String toString() {
        return "A{" +
                "p1='" + p1 + '\'' +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                ", p5=" + p5 +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}
