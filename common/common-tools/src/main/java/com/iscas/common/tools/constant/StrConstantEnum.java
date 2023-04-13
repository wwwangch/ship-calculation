package com.iscas.common.tools.constant;

/**
 * 特殊字符枚举
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/19 14:42
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public enum StrConstantEnum {

    /**
     * 分号
     * */
    SEMICOLON(";", "分号"),

    /**
     * 逗号
     * */
    COMMA(",", "逗号"),

    /**
     * 句号
     * */
    PERIOD(".", "句号"),

    /**
     * 冒号
     * */
    COLON(":", "冒号"),

    /**
     * 井号
     * */
    HASH("#", "井号"),

    /**
     * 星*号
     * */
    ASTERISK("*", "星号"),

    /**
     * 星**号
     * */
    DOUBLE_ASTERISK("**", "双星号"),

    /**
     *
     * 斜杠
     * */
    SLASH("/", "斜杠"),


    /**
     * 反斜杠
     * */
    BACKSLASH("\\", "反斜杠"),

    /**
     * 问号
     * */
    QUESTION("?", "问号"),

    /**
     * @
     * */
    AT("@", "艾特"),

    /**
     * $ 美元
     * */
    DOLLAR("$", "美元符号"),

    /**
     * % 百分号
     * */
    PERCENT("%", "百分号  "),

    /**
    * ^ 异或
    * */
    XOR("^", "异或符号"),

    /**
     * &与
     * */
    AND("&", "与符号"),

    /**
     * (左括号
     * */
    LEFT_BRACKET("(", "左括号"),

    /**
     * )右括号
     * */
    RIGHT_BRACKET(")", "右括号"),

    /**
     * -短横杠
     * */
    RUNG("-", "短横杠"),

    /**
     * _ 下划线
     * */
    UNDERLINE("_", "下划线"),

    /**
     * + 加号
     * */
    PLUS("+", "加号"),

    /**
     * =等于
     * */
    EQUALS("=", "等于号"),

    /**
     * |或
     * */
    OR("|", "或符号"),

    /**
     * [左中括号
     * */
    LEFT_PARENTHESIS("[", "左中括号"),

    /**
     * ]右中括号
     * */
    RIGHT_PARENTHESIS("]", "右中括号"),

    /**
     * {左大括号
     * */
    LEFT_BREACE("{", "左大括号"),

    /**
     * }右大括号
     * */
    RIGHT_BREACE("{", "右大括号"),


    /**
     * >大于
     * */
    GREATER_THAN(">", "大于号"),

    /**
     * <小于
     * */
    LESS_THAN("<", "小于号"),

    /**
     * 空字符串
     * */
    EMPTY("", "空字符串");


    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    StrConstantEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
