package com.iscas.common.tools.core.object;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/18 11:11
 * @since jdk1.8
 */
class C {
    public C() {}
    private Integer p1;
    private String p2;
    private Set<Double> p3 = new HashSet<>();
    private Map<String, Float> p4 = new HashMap<>();

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public Set<Double> getP3() {
        return p3;
    }

    public void setP3(Set<Double> p3) {
        this.p3 = p3;
    }

    public Map<String, Float> getP4() {
        return p4;
    }

    public void setP4(Map<String, Float> p4) {
        this.p4 = p4;
    }

    @Override
    public String toString() {
        return "C{" +
                "p1=" + p1 +
                ", p2='" + p2 + '\'' +
                ", p3=" + p3 +
                ", p4=" + p4 +
                '}';
    }
}
