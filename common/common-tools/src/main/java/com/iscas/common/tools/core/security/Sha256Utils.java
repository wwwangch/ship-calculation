package com.iscas.common.tools.core.security;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA256加密工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/14 13:22
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class Sha256Utils {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private Sha256Utils() {
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str 待加密的报文
     * @return String
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static String encrypt(String str) throws NoSuchAlgorithmException {
        try {
            return encrypt(str, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str     待加密的报文
     * @param charset 编码格式
     * @return String
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static String encrypt(String str, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes(charset));
        return toHexString(messageDigest.digest());
    }

    /**
     * hmac-sha256加密
     *
     * @param key     秘钥
     * @param str     待加密的报文
     * @param charset 编码格式
     * @return String
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static String hmacEncrypt(String key, String str, String charset) throws UnsupportedEncodingException {
        byte[] keyBytes = key.getBytes(charset);
        byte[] strBytes = str.getBytes(charset);
        byte[] hmacResult = doHmac(keyBytes, strBytes);
        return toHexString(hmacResult);
    }

    /**
     * hmac-sha256加密
     *
     * @param key 秘钥
     * @param str 待加密的报文
     * @return String
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static String hmacEncrypt(String key, String str) {
        try {
            return hmacEncrypt(key, str, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * hmac-sha256加密
     *
     * @param key     秘钥
     * @param str     待校验的报文
     * @param sha     加密后的串
     * @param charset 编码格式
     * @return boolean
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static boolean hmacVerify(String key, String str, String sha, String charset) throws UnsupportedEncodingException {
        assert StringUtils.isNotBlank(sha);
        assert StringUtils.isNotBlank(str);
        assert StringUtils.isNotBlank(key);
        assert StringUtils.isNotBlank(charset);
        byte[] src = ByteUtils.fromHexString(sha);
        byte[] keyBytes = key.getBytes(charset);
        byte[] strBytes = str.getBytes(charset);
        byte[] target = doHmac(keyBytes, strBytes);
        return Arrays.equals(src, target);
    }

    /**
     * hmac-sha256加密
     *
     * @param key 秘钥
     * @param str 待校验的报文
     * @param sha 加密后的串
     * @return boolean
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static boolean hmacVerify(String key, String str, String sha) {
        try {
            return hmacVerify(key, str, sha, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static byte[] doHmac(byte[] keyBytes, byte[] strBytes) {
        Digest mg = new SHA256Digest();
        HMac hMac = new HMac(mg);
        hMac.init(new KeyParameter(keyBytes));
        hMac.update(strBytes, 0, strBytes.length);
        byte[] hmacResult = new byte[hMac.getMacSize()];
        hMac.doFinal(hmacResult, 0);
        return hmacResult;
    }

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String toHexString(byte[] input) {
        String result = "";
        for (int i = 0; i < input.length; i++) {
            result += HEX_CHARS[(input[i] >>> 4) & 0x0f];
            result += HEX_CHARS[(input[i]) & 0x0f];
        }
        return result;
    }

//    /**
//     * 将byte转为16进制
//     *
//     * @param bytes bytes
//     * @return String
//     */
//    private static String byte2Hex(byte[] bytes) {
//        StringBuilder stringBuffer = new StringBuilder();
//        String temp;
//        for (byte aByte : bytes) {
//            temp = Integer.toHexString(aByte & 0xFF);
//            if (temp.length() == 1) {
//                //1得到一位的进行补0操作
//                stringBuffer.append("0");
//            }
//            stringBuffer.append(temp);
//        }
//        return stringBuffer.toString();
//    }
}
