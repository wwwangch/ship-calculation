package com.iscas.common.tools.core.reflect.reflectTest;

import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/14 23:00
 * @since jdk1.8
 */
public class A {
    private static long xxx = 2;
    private List<A2> a2List;
    private Map<String, A2> map;
    private A1 a1;

    public int getA1Hash(){
        return a1.hashCode();
    }

    public String xxx(String str1, int str2, float[] str3){
        StringBuilder sb = new StringBuilder();
        sb.append(str1).append(str2).append(str3);
        return sb.toString();
    }


    public List<A2> getA2List() {
        return a2List;
    }

    public void setA2List(List<A2> a2List) {
        this.a2List = a2List;
    }

    public Map<String, A2> getMap() {
        return map;
    }

    public void setMap(Map<String, A2> map) {
        this.map = map;
    }

    public A1 getA1() {
        return a1;
    }

    public void setA1(A1 a1) {
        this.a1 = a1;
    }
}
