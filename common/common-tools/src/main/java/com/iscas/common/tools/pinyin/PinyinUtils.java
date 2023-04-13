package com.iscas.common.tools.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音转化工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/9 17:17
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class PinyinUtils {
    @SuppressWarnings("FieldMayBeFinal")
    private static HanyuPinyinOutputFormat format;

    static {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public enum Type {
        /**
         * 全部大写
         */
        UPPERCASE,
        /**
         * 全部小写
         */
        LOWERCASE,
        /**
         * 首字母大写
         */
        FIRSTUPPER
    }

    private PinyinUtils() {

    }

    /**
     * 汉字转为拼音
     *
     * @param str 汉字
     * @return java.lang.String
     * @throws BadHanyuPinyinOutputFormatCombination e
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, "", Type.UPPERCASE);
    }

    /**
     * 汉字转为拼音
     *
     * @param str   汉字
     * @param spera 转化结果的分割符
     * @return java.lang.String
     * @throws BadHanyuPinyinOutputFormatCombination e
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static String toPinYin(String str, String spera) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, spera, Type.UPPERCASE);
    }

    /**
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换
     * 如： 明天 转换成 MINGTIAN
     *
     * @param str：要转化的汉字
     * @param spera：转化结果的分割符
     * @param type：转换模式      全部大写UPPERCASE，全部小写LOWERCASE,首字母大写FIRSTUPPER
     * @throws BadHanyuPinyinOutputFormatCombination e
     */

    public static String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        if (type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        StringBuilder py = new StringBuilder();
        String temp;
        String[] t;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c <= 128) {
                py.append(c);
            } else {
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (t == null) {
                    py.append(c);
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTUPPER) {
                        temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                    }
                    py.append(temp).append(i == str.length() - 1 ? "" : spera);
                }
            }
        }
        return py.toString().trim();
    }
}
