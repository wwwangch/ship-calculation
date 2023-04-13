package com.iscas.common.prometheus.tools.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机字符串工具类
 *
 * @author zhuquanwen
 * @date 2018/7/13 18:01
 **/
public final class RandomStringUtils {
    private RandomStringUtils() {
    }

    /**
     * 获得随机字母数字字符串
     *
     * @param length 随机串长度
     * @return {@link String}
     * @date 2018/7/13 18:02
     */
    public static String randomStr(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            //选择从数字，大写,小写中随机
            int r = random.nextInt(3);
            int charInfo =
            switch (r) {
                // 从数字中拿一个
                case 0 -> 48 + random.nextInt(10);
                // 从大写字母中拿一个
                case 1 -> 65 + random.nextInt(26);
                // 从小写字母中拿一个
                default -> 97 + random.nextInt(26);
            };
            sb.append((char) charInfo);
        }
        return sb.toString();
    }
}
