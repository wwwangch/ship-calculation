package com.iscas.common.tools.pinyin;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.rnkrsoft.bopomofo4j.Bopomofo4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;

/**
 *拼音工具测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/9 17:23
 * @since jdk1.8
 */
public class PinyinUtilsTests {
    @Test
    public void test1() throws BadHanyuPinyinOutputFormatCombination {
        //Hutool实现
        String sun = PinyinUtil.getPinyin("太阳");
        System.out.println("太阳====" + sun);

        //Pinyin4J实现
        System.out.println("美丽的中国====" + PinyinUtils.toPinYin("美丽的中国", "", PinyinUtils.Type.LOWERCASE));


        //Bopomofo4j实现
        //汉语句子->声母音调拼音
        Bopomofo4j.local();
        String v1 = Bopomofo4j.pinyin("中国人！",0, false, false, " ");
        System.out.println(v1);//控制台输出 zhōng guǒ rén！

        //汉语句子->数字音调拼音
        String v2 = Bopomofo4j.pinyin("患难与共的兄弟！！",1, false, false, " ");
        System.out.println(v2);//控制台输出 huan4 nan4 yu3 gong4 de0 xiong1 di4！！

        //汉语句子->无音调拼音
        String v3 = Bopomofo4j.pinyin("this is a pinyin library!这是一个汉语拼音库！！",2, false, false, " ");
        System.out.println(v3);//控制台输出 this is a pinyin library! zhe shi yi ge han yu pin yin ku！！

        //繁体->简体
        String v4 = Bopomofo4j.cht2chs("APM（Actions Per Minute）是一個在遊戲");
        System.out.println(v4);//APM（Actions Per Minute）是一个在游戏

        //简体->繁体
        String v5 = Bopomofo4j.chs2cht("APM（Actions Per Minute）是一个在游戏");
        System.out.println(v5);//APM（Actions Per Minute）是一個在遊戲
    }
}
