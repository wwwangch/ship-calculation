package com.iscas.common.tools.emoji;

import com.vdurmont.emoji.EmojiParser;
import org.junit.jupiter.api.Test;

/**
 * emoji符号存储
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/29 17:34
 * @since jdk1.8
 */
public class EmojiTests {

    /**
     * 使用unicode替换字符串中的所有别名和html表示，请使用EmojiParser#parseToUnicode(String)。
     * */
    @Test
    public void test1() {
        String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
        String result = EmojiParser.parseToUnicode(str);
        System.out.println(result);
    }


    /**
     * 转为别名
     * */
    @Test
    public void test2() {
        String str =  "一个😀awesome😃string，带有一些😉emojis!";
        String alias =  EmojiParser.parseToAliases(str);
        System.out.println(alias);
    }

    /**
     * 转为html
     * */
    @Test
    public void test3() {
        String str =  "一个😀awesome😃string，带有一些😉emojis!";
        String html =  EmojiParser.parseToHtmlDecimal(str);
        System.out.println(html);
    }
}
