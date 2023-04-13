package com.iscas.common.tools.junit5.testInstance.second;

/**
 * //编写接口协定的测试
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 17:56
 * @since jdk1.8
 */
public class StringTests implements ComparableContract, EqualsContract {
    @Override
    public String createValue() {
        return "banana";
    }

    @Override
    public String createSmallerValue() {
        return "apple"; // 'a' < 'b' in "banana"
    }

    @Override
    public String createNotEqualValue() {
        return "cherry";
    }
}
