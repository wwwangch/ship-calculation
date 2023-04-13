package com.iscas.common.tools.core.valid;


import com.iscas.common.tools.constant.StrConstantEnum;

/**
 * 校验IP地址工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/17 13:50
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class IpValidUtils {

    private static final String PERIOD = StrConstantEnum.PERIOD.getValue();
    private static final String COLON = StrConstantEnum.COLON.getValue();
    private static final String COLON2 = StrConstantEnum.COLON.getValue().concat(StrConstantEnum.COLON.getValue());

    private static final int FOUR = 4;
    private static final int SEVEN = 7;
    private static final int EIGHT = 7;
    private static final int TWO = 2;

    private IpValidUtils() {
    }

    /**
     * 判断所有的IP地址是IPV4 还是IPV6
     *
     * @param ip ip地址字符串
     * @return String
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    public static String validIp4Or6(String ip) {

        if (!ip.contains(PERIOD) && !ip.contains(COLON)) {
            return "Neither";
        }
        //如果是IPV4
        if (ip.contains(PERIOD)) {
            if (ip.endsWith(PERIOD)) {
                return "Neither";
            }
            String[] arr = ip.split("\\.");
            if (arr.length != FOUR) {
                return "Neither";
            }

            for (int i = 0; i < FOUR; i++) {
                if (arr[i].length() == 0 || arr[i].length() > 3) {
                    return "Neither";
                }
                for (int j = 0; j < arr[i].length(); j++) {
                    if (arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') {
                        continue;
                    }
                    return "Neither";
                }
                boolean b = Integer.parseInt(arr[i]) > 255 || (arr[i].length() >= 2 && String.valueOf(arr[i]).startsWith("0"));
                if (b) {
                    return "Neither";
                }
            }
            return "IPv4";
        }//如果是IPV4

        //如果是IPV6
        if (ip.contains(COLON)) {
            if (ip.endsWith(COLON) && !ip.endsWith(COLON2)) {
                return "Neither";
            }
            //如果包含多个“::”，一个IPv6地址中只能出现一个“::”
            boolean b = ip.contains(COLON2) && ip.indexOf(COLON2, ip.indexOf(COLON2) + TWO) != -1;
            if (b) {
                return "Neither";
            }

            //如果含有一个“::”
            //noinspection DuplicateCondition
            if (ip.contains(COLON2)) {
                String[] arr = ip.split(COLON);
                //"1::"是最短的字符串
                if (arr.length > SEVEN || arr.length < 1) {
                    return "Neither";
                }
                for (String s : arr) {
                    if (s.equals(StrConstantEnum.EMPTY.getValue())) {
                        continue;
                    }
                    if (s.length() > 4) {
                        return "Neither";
                    }
                    for (int j = 0; j < s.length(); j++) {
                        boolean b1 = (s.charAt(j) >= '0' && s.charAt(j) <= '9') || (s.charAt(j) >= 'A' && s.charAt(j) <= 'F')
                                || (s.charAt(j) >= 'a' && s.charAt(j) <= 'f');
                        if (b1) {
                            continue;
                        }
                        return "Neither";
                    }
                }
                return "IPv6";
            }

            //如果不含有“::”
            //noinspection DuplicateCondition
            if (!ip.contains(COLON2)) {
                String[] arr = ip.split(COLON2);
                if (arr.length != EIGHT) {
                    return "Neither";
                }
                for (String s : arr) {
                    if (s.length() > 4) {
                        return "Neither";
                    }
                    for (int j = 0; j < s.length(); j++) {
                        boolean b1 = (s.charAt(j) >= '0' && s.charAt(j) <= '9') || (s.charAt(j) >= 'A' && s.charAt(j) <= 'F')
                                || (s.charAt(j) >= 'a' && s.charAt(j) <= 'f');
                        if (b1) {
                            continue;
                        }
                        return "Neither";
                    }
                }
                return "IPv6";
            }
        }//如果是IPV6
        return "Neither";
    }
}
