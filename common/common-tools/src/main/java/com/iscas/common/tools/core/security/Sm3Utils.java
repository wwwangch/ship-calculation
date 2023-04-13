package com.iscas.common.tools.core.security;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/23 9:13
 */
public class Sm3Utils {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private Sm3Utils() {
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 不使用密钥
     * sm3算法加密
     *
     * @param str 待加密字符串
     * @return 返回加密后，固定长度=64的16进制字符串
     */
    public static String encrypt(String str) {
        try {
            return encrypt(str, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 不使用密钥
     * sm3算法加密
     *
     * @param str 待加密字符串
     * @param charset 编码格式
     * @return 返回加密后，固定长度=64的16进制字符串
     */
    public static String encrypt(String str, String charset) throws UnsupportedEncodingException {
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        // 将字符串转换成byte数组
        byte[] srcData = str.getBytes(charset);
        // 调用hash
        byte[] resultHash = hash(srcData);
        // 将返回的hash值转换成16进制字符串
        return ByteUtils.toHexString(resultHash);
    }


    /**
     * 使用密钥
     * sm3算法加密
     *
     * @param key 密钥key
     * @param str 待加密字符串
     * @return 返回加密后，固定长度=64的16进制字符串
     */
    public static String hmacEncrypt(String key, String str) {
        try {
            return hmacEncrypt(key, str, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用密钥
     * sm3算法加密
     *
     * @param key 密钥key
     * @param str 待加密字符串
     * @param charset 编码格式
     * @return 返回加密后，固定长度=64的16进制字符串
     */
    public static String hmacEncrypt(String key, String str, String charset) throws UnsupportedEncodingException {
        byte[] keyBytes = key.getBytes(charset);
        byte[] strBytes = str.getBytes(charset);
        byte[] hmac = hmac(keyBytes, strBytes);
        return ByteUtils.toHexString(hmac);
    }

    /**
     * 判断源数据与加密数据是否一致
     * 通过验证原数组和生成的hash数组是否为同一数组，验证两者是否为同意数据
     *
     * @param srcStr 源字符串
     * @param sm3Str 加密后的数据
     * @return 校验结果
     */
    public static boolean verify(String srcStr, String sm3Str) {
        try {
            return verifyUseCharset(srcStr, sm3Str, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断源数据与加密数据是否一致
     * 通过验证原数组和生成的hash数组是否为同一数组，验证两者是否为同意数据
     *
     * @param srcStr 源字符串
     * @param sm3Str 加密后的数据
     * @param charset 编码格式
     * @return 校验结果
     */
    public static boolean verifyUseCharset(String srcStr, String sm3Str, String charset) throws UnsupportedEncodingException {
        byte[] srcData = srcStr.getBytes(charset);
        byte[] sm3Hash = ByteUtils.fromHexString(sm3Str);
        //通过摘要加密生成新的hash数组
        byte[] newHash = hash(srcData);
        return Arrays.equals(newHash, sm3Hash);
    }

    /**
     * 判断源数据与加密数据是否一致,使用HMAC
     * 通过验证原数组和生成的hash数组是否为同一数组，验证两者是否为同意数据
     *
     * @param srcStr 源字符串
     * @param sm3Str 加密后的数据
     * @return 校验结果
     */
    public static boolean hmacVerify(String key, String srcStr, String sm3Str) {
        try {
            return hmacVerifyUseCharset(key, srcStr, sm3Str, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断源数据与加密数据是否一致,使用HMAC
     * 通过验证原数组和生成的hash数组是否为同一数组，验证两者是否为同意数据
     *
     * @param srcStr 源字符串
     * @param sm3Str 加密后的数据
     * @param charset 编码格式
     * @return 校验结果
     */
    public static boolean hmacVerifyUseCharset(String key, String srcStr, String sm3Str, String charset) throws UnsupportedEncodingException {
        byte[] keyData = key.getBytes(charset);
        byte[] srcData = srcStr.getBytes(charset);
        byte[] sm3Hash = ByteUtils.fromHexString(sm3Str);
        //通过摘要加密生成新的hash数组
        byte[] newHash = hmac(keyData, srcData);
        return Arrays.equals(newHash, sm3Hash);
    }

    /**
     * 返回长度=32的byte数组
     * 生成对应的hash值
     *
     * @param srcData
     * @return byte[]
     */
    public static byte[] hash(byte[] srcData) {
        //摘要加密
        SM3Digest digest = new SM3Digest();
        //使用指定的数组更新摘要
        digest.update(srcData, 0, srcData.length);
        //获取摘要的长度
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    /**
     * 通过密钥进行加密
     * 指定密钥进行加密
     *
     * @param key     密钥
     * @param srcData 被加密的byte数组
     * @return byte[]
     */
    public static byte[] hmac(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }


////========================================下面的方式为使用JDK原生实现，但无法使用Hmac,暂时注释掉=================================
//
//    private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//    public static final byte[] IV = {0x73, (byte) 0x80, 0x16, 0x6f, 0x49, 0x14, (byte) 0xb2, (byte) 0xb9, 0x17, 0x24, 0x42,
//            (byte) 0xd7, (byte) 0xda, (byte) 0x8a, 0x06, 0x00, (byte) 0xa9, 0x6f, 0x30, (byte) 0xbc, (byte) 0x16, 0x31,
//            0x38, (byte) 0xaa, (byte) 0xe3, (byte) 0x8d, (byte) 0xee, 0x4d, (byte) 0xb0, (byte) 0xfb, 0x0e, 0x4e};
//    private static final Integer TJ_15 = Integer.valueOf("79cc4519", 16);
//    private static final Integer TJ_63 = Integer.valueOf("7a879d8a", 16);
//    private static final byte[] FirstPadding = {(byte) 0x80};
//    private static final byte[] ZeroPadding = {(byte) 0x00};
//
//
//    public static String encrypt(String source) throws IOException {
//        byte[] bytes = encrypt(source.getBytes(StandardCharsets.UTF_8));
//        return bytesToHexString(bytes);
//    }
//
//    public static byte[] encrypt(byte[] source) throws IOException {
//        byte[] m1 = padding(source);
//        int n = m1.length / (512 / 8);
//
//        byte[] b;
//        byte[] vi = IV.clone();
//        byte[] vi1 = null;
//        for (int i = 0; i < n; i++) {
//            b = Arrays.copyOfRange(m1, i * 64, (i + 1) * 64);
//            vi1 = CF(vi, b);
//            vi = vi1;
//        }
//        return vi1;
//    }
//
//
//    private static int T(int j) {
//        if (j >= 0 && j <= 15) {
//            return TJ_15.intValue();
//        } else if (j >= 16 && j <= 63) {
//            return TJ_63.intValue();
//        } else {
//            throw new RuntimeException("data invalid");
//        }
//    }
//
//    private static Integer FF(Integer x, Integer y, Integer z, int j) {
//        if (j >= 0 && j <= 15) {
//            return Integer.valueOf(x.intValue() ^ y.intValue() ^ z.intValue());
//        } else if (j >= 16 && j <= 63) {
//            return Integer.valueOf(
//                    (x.intValue() & y.intValue()) | (x.intValue() & z.intValue()) | (y.intValue() & z.intValue()));
//        } else {
//            throw new RuntimeException("data invalid");
//        }
//    }
//
//    private static Integer GG(Integer x, Integer y, Integer z, int j) {
//        if (j >= 0 && j <= 15) {
//            return Integer.valueOf(x.intValue() ^ y.intValue() ^ z.intValue());
//        } else if (j >= 16 && j <= 63) {
//            return Integer.valueOf((x.intValue() & y.intValue()) | (~x.intValue() & z.intValue()));
//        } else {
//            throw new RuntimeException("data invalid");
//        }
//    }
//
//    private static Integer P0(Integer x) {
//        return Integer
//                .valueOf(x.intValue() ^ Integer.rotateLeft(x.intValue(), 9) ^ Integer.rotateLeft(x.intValue(), 17));
//    }
//
//    private static Integer P1(Integer x) {
//        return Integer.valueOf(x.intValue() ^ Integer.rotateLeft(x.intValue(), 15) ^ Integer.rotateLeft(x.intValue(), 23));
//    }
//
//    private static byte[] padding(byte[] source) throws IOException {
//        if (source.length >= 0x2000000000000000L) {
//            throw new RuntimeException("src data invalid.");
//        }
//        long l = source.length * 8;
//        long k = 448 - (l + 1) % 512;
//        if (k < 0) {
//            k = k + 512;
//        }
//
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
//            baos.write(source);
//            baos.write(FirstPadding);
//            long i = k - 7;
//            while (i > 0) {
//                baos.write(ZeroPadding);
//                i -= 8;
//            }
//            baos.write(long2bytes(l));
//            return baos.toByteArray();
//        }
//    }
//
//    private static byte[] long2bytes(long l) {
//        byte[] bytes = new byte[8];
//        for (int i = 0; i < 8; i++) {
//            bytes[i] = (byte) (l >>> ((7 - i) * 8));
//        }
//        return bytes;
//    }
//
//    private static byte[] CF(byte[] vi, byte[] bi) throws IOException {
//        int a, b, c, d, e, f, g, h;
//        a = toInteger(vi, 0);
//        b = toInteger(vi, 1);
//        c = toInteger(vi, 2);
//        d = toInteger(vi, 3);
//        e = toInteger(vi, 4);
//        f = toInteger(vi, 5);
//        g = toInteger(vi, 6);
//        h = toInteger(vi, 7);
//
//        int[] w = new int[68];
//        int[] w1 = new int[64];
//        for (int i = 0; i < 16; i++) {
//            w[i] = toInteger(bi, i);
//        }
//        for (int j = 16; j < 68; j++) {
//            w[j] = P1(w[j - 16] ^ w[j - 9] ^ Integer.rotateLeft(w[j - 3], 15)) ^ Integer.rotateLeft(w[j - 13], 7)
//                    ^ w[j - 6];
//        }
//        for (int j = 0; j < 64; j++) {
//            w1[j] = w[j] ^ w[j + 4];
//        }
//        int ss1, ss2, tt1, tt2;
//        for (int j = 0; j < 64; j++) {
//            ss1 = Integer.rotateLeft(Integer.rotateLeft(a, 12) + e + Integer.rotateLeft(T(j), j), 7);
//            ss2 = ss1 ^ Integer.rotateLeft(a, 12);
//            tt1 = FF(a, b, c, j) + d + ss2 + w1[j];
//            tt2 = GG(e, f, g, j) + h + ss1 + w[j];
//            d = c;
//            c = Integer.rotateLeft(b, 9);
//            b = a;
//            a = tt1;
//            h = g;
//            g = Integer.rotateLeft(f, 19);
//            f = e;
//            e = P0(tt2);
//        }
//        byte[] v = toByteArray(a, b, c, d, e, f, g, h);
//        for (int i = 0; i < v.length; i++) {
//            v[i] = (byte) (v[i] ^ vi[i]);
//        }
//        return v;
//    }
//
//    private static int toInteger(byte[] source, int index) {
//        StringBuilder valueStr = new StringBuilder("");
//        for (int i = 0; i < 4; i++) {
//            valueStr.append(chars[(byte) ((source[index * 4 + i] & 0xF0) >> 4)]);
//            valueStr.append(chars[(byte) (source[index * 4 + i] & 0x0F)]);
//        }
//        return Long.valueOf(valueStr.toString(), 16).intValue();
//
//    }
//
//    private static byte[] toByteArray(int a, int b, int c, int d, int e, int f, int g, int h) throws IOException {
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(32);) {
//            baos.write(toByteArray(a));
//            baos.write(toByteArray(b));
//            baos.write(toByteArray(c));
//            baos.write(toByteArray(d));
//            baos.write(toByteArray(e));
//            baos.write(toByteArray(f));
//            baos.write(toByteArray(g));
//            baos.write(toByteArray(h));
//            return baos.toByteArray();
//        }
//    }
//
//    private static byte[] toByteArray(int i) {
//        byte[] byteArray = new byte[4];
//        byteArray[0] = (byte) (i >>> 24);
//        byteArray[1] = (byte) ((i & 0xFFFFFF) >>> 16);
//        byteArray[2] = (byte) ((i & 0xFFFF) >>> 8);
//        byteArray[3] = (byte) (i & 0xFF);
//        return byteArray;
//    }
//
//    private static String byteToHexString(byte ib) {
//        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//        char[] ob = new char[2];
//        ob[0] = Digit[(ib >>> 4) & 0X0f];
//        ob[1] = Digit[ib & 0X0F];
//        return new String(ob);
//    }
//
//    private static String bytesToHexString(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte aByte : bytes) {
//            sb.append(byteToHexString(aByte));
//        }
//        return sb.toString();
//    }
}
