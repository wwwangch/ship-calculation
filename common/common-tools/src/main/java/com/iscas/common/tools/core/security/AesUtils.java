package com.iscas.common.tools.core.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * <p>AES加解密工具类</p>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/13 17:31
 * @since jdk1.8
 **/
@SuppressWarnings("unused")
public final class AesUtils {
    private AesUtils() {

    }

    private static final String CKEY = "encryptionIntVec";

    /**
     * 密钥
     */
    public static final String CRYPT_KEY = "10f5dd7c2d45d247";

    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";

    public static String doDecrypt(String source) {
        String target = "";
        try {
            target = aesDecrypt(source);
        } catch (Exception ignored) {
        }
        return StringUtils.isNotBlank(target) ? target : source;
    }

    public static String doEncrypt(String source) {
        String target = "";
        try {
            target = aesEncrypt(source, CRYPT_KEY);
        } catch (Exception ignored) {
        }
        return StringUtils.isNotBlank(target) ? target : source;
    }

    /**
     * aes解密
     *
     * @param encrypt 内容
     * @return String
     * @throws Exception 异常
     */
    public static String aesDecrypt(String encrypt) throws Exception {
        return aesDecrypt(encrypt, CRYPT_KEY);
    }

    /**
     * aes加密
     *
     * @param content content
     * @return String
     * @throws Exception 异常
     */
    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, CRYPT_KEY);
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        // 这里的1代表正数
        return new BigInteger(1, bytes).toString(radix);
    }

    /**
     * common 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * common 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     */
    public static byte[] base64Decode(String base64Code) {
        return StringUtils.isEmpty(base64Code) ? null : java.util.Base64.getDecoder().decode(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        byte[] raw = encryptKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);

        //使用CBC模式，需要一个向量iv,可以增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(CKEY.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception 异常
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        byte[] raw = decryptKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        IvParameterSpec iv = new IvParameterSpec(CKEY.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception 异常
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

}
