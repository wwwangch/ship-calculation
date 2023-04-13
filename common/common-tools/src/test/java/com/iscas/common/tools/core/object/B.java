package com.iscas.common.tools.core.object;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/18 11:11
 * @since jdk1.8
 */
class B {
    public B() {}
    private int p1;
    private C p2;
    private List<C> p3 = new LinkedList<>();

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public C getP2() {
        return p2;
    }

    public void setP2(C p2) {
        this.p2 = p2;
    }

    public List<C> getP3() {
        return p3;
    }

    public void setP3(List<C> p3) {
        this.p3 = p3;
    }

    @Override
    public String toString() {
        return "B{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }
}
