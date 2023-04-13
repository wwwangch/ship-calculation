package com.iscas.common.tools.core.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * MD5加密解密工具类
 *
 * @author zhuquanwen
 * @date 2018/7/13
 **/

@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming", "AlibabaClassNamingShouldBeCamel"})
public final class MD5Utils {
    private static final int LENGTH_16 = 16;
    private static final int LENGTH_48 = 48;
    private static final int NUMBER_3 = 3;

    private static final String DEFAULT_CHARSET = "UTF-8";

    private MD5Utils() {
    }

    /**
     * MD5加密，不带盐
     *
     * @param input   输入字符串
     * @param charset 编码
     * @return java.lang.String
     * @throws NoSuchAlgorithmException 获取MessageDigest对象失败 {@link MessageDigest}
     * @date 2018/7/14
     */
    @SuppressWarnings("UnusedAssignment")
    public static String md5(String input, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        assert StringUtils.isNotBlank(input);
        byte[] byteArray = input.getBytes(charset);
        return doMd5(byteArray);
    }

    /**
     * MD5加密，不带盐
     *
     * @param input 输入字符串
     * @return java.lang.String
     * @throws NoSuchAlgorithmException 获取MessageDigest对象失败 {@link MessageDigest}
     * @date 2018/7/14
     */
    @SuppressWarnings("UnusedAssignment")
    public static String md5(String input) throws NoSuchAlgorithmException {
        try {
            return md5(input, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5加密，自动生成盐
     *
     * @param str     要加密的串
     * @param charset 编码格式
     * @return java.lang.String
     * @date 2018/7/14
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String saltMD5(String str, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        assert StringUtils.isNotBlank(str);
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < LENGTH_16) {
            sb.append("0".repeat(Math.max(0, LENGTH_16 - len)));
        }
        String salt = sb.toString();
        str = doMd5((str + salt).getBytes(charset));
        char[] cs = new char[LENGTH_48];
        for (int i = 0; i < LENGTH_48; i += NUMBER_3) {
            cs[i] = str.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = str.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * MD5加密，自动生成盐
     *
     * @param str 要加密的串
     * @return java.lang.String
     * @date 2018/7/14
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String saltMD5(String str) throws NoSuchAlgorithmException {
        try {
            return saltMD5(str, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密校验
     *
     * @param str 明文
     * @param md5 密文
     * @return boolean 校验成功与否
     * @date 2018/7/14
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static boolean saltVerify(String str, String md5) throws NoSuchAlgorithmException {
        try {
            return saltVerify(str, md5, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密校验
     *
     * @param str     明文
     * @param md5     密文
     * @param charset 编码格式
     * @return boolean 校验成功与否
     * @date 2018/7/14
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static boolean saltVerify(String str, String md5, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        assert StringUtils.isNotBlank(str);
        assert StringUtils.isNotBlank(md5);
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < LENGTH_48; i += NUMBER_3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return doMd5((str + salt).getBytes(charset)).equals(new String(cs1));
    }

    /**
     * hmacMD5
     *
     * @param key     密钥
     * @param input   输入字符串
     * @param charset 编码格式
     * @return java.lang.String
     * @date 2018/7/14
     */
    @SuppressWarnings("UnusedAssignment")
    public static String hmacMd5(String key, String input, String charset) throws UnsupportedEncodingException {
        byte[] bytes = input.getBytes(charset);
        byte[] keyBytes = key.getBytes(charset);
        byte[] hmacResult = doHmacMd5(keyBytes, bytes);
        return ByteUtils.toHexString(hmacResult);
    }

    /**
     * hmacMD5
     *
     * @param key   密钥
     * @param input 输入字符串
     * @return java.lang.String
     * @date 2018/7/14
     */
    @SuppressWarnings("UnusedAssignment")
    public static String hmacMd5(String key, String input) {
        try {
            return hmacMd5(key, input, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HMAC校验
     *
     * @param str     明文
     * @param md5     密文
     * @param charset 编码格式
     * @return boolean 校验成功与否
     * @date 2018/7/14
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static boolean hmacVerify(String key, String str, String md5, String charset) throws UnsupportedEncodingException {
        assert StringUtils.isNotBlank(str);
        assert StringUtils.isNotBlank(md5);
        assert StringUtils.isNotBlank(key);
        assert StringUtils.isNotBlank(charset);
        byte[] src = ByteUtils.fromHexString(md5);
        byte[] keyBytes = key.getBytes(charset);
        byte[] strBytes = str.getBytes(charset);
        byte[] target = doHmacMd5(keyBytes, strBytes);
        return Arrays.equals(src, target);
    }

    /**
     * HMAC校验
     *
     * @param str 明文
     * @param md5 密文
     * @return boolean 校验成功与否
     * @date 2018/7/14
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static boolean hmacVerify(String key, String str, String md5) {
        try {
            return hmacVerify(key, str, md5, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * 获取十六进制字符串形式的MD5摘要
//     */
//    private static String md5Hex(String src) throws NoSuchAlgorithmException {
//        MessageDigest md5 = MessageDigest.getInstance("md5");
//        byte[] bs = md5.digest(src.getBytes());
//        return new String(new Hex().encode(bs));
//
//    }

    /**
     * MD5加密，不带盐
     *
     * @param input 输入字符串
     * @return java.lang.String
     * @throws NoSuchAlgorithmException 获取MessageDigest对象失败 {@link MessageDigest}
     * @date 2018/7/14
     */
    public static String doMd5(byte[] input) throws NoSuchAlgorithmException {
        assert input != null;
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] md5Bytes = md5.digest(input);
        return toHexString(md5Bytes);
    }


    /**
     * 获取一个文件的md5值(可处理大文件)
     *
     * @return md5 value
     */
    public static String getFileMD5(InputStream is) {
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = is.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @NotNull
    private static byte[] doHmacMd5(byte[] keyBytes, byte[] bytes) {
        Digest mg = new MD5Digest();
        HMac hMac = new HMac(mg);
        hMac.init(new KeyParameter(keyBytes));
        hMac.update(bytes, 0, bytes.length);
        byte[] hmacResult = new byte[hMac.getMacSize()];
        hMac.doFinal(hmacResult, 0);
        return hmacResult;
    }

}
