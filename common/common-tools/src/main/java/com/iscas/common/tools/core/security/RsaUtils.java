package com.iscas.common.tools.core.security;


import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/27 15:48
 * @since jdk1.8
 */

@SuppressWarnings("unused")
public class RsaUtils {
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjh7unmMBs" +
            "QrAxJRHd3iXZD65fZClHxyEjxTzQB5F/UbYmyrEBzZfgw7u0UgSqnc+SQX+HUScHuMG+eC+KotoNPY1JlOC8x" +
            "A358khG6rBB3WDIqoZJ3PnZP3DkJ0IM8RO70xMFmqnQhWuGKrC28vOpqwopo8GvEaCoq7ec39/oJhV7skoYt+X" +
            "fYGLld9HVaKZyAvpbZSXhoX8GAOlXpAukhjuRZfyX0+313ZS4DUYibqczwHS6KNR8Cv5bz6lZACwaLIruZtdyD" +
            "7XacjHsDlHEqSgPPqRCRJNk3KC82dyyLWfEAEwb/CHMfQrTAUeCJuMbwmcZ5HJ9P5lXb6zBH5rgwIDAQAB";
    private static final String PRIVATE_KEY = "RSA私钥Base64编码:MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjA" +
            "gEAAoIBAQCOHu6eYwGxCsDElEd3eJdkPrl9kKUfHISPFPNAHkX9RtibKsQHNl+DDu7RSBKqdz5JBf4dRJwe4wb5" +
            "4L4qi2g09jUmU4LzEDfnySEbqsEHdYMiqhknc+dk/cOQnQgzxE7vTEwWaqdCFa4YqsLby86mrCimjwa8RoKirt5" +
            "zf3+gmFXuyShi35d9gYuV30dVopnIC+ltlJeGhfwYA6VekC6SGO5Fl/JfT7fXdlLgNRiJupzPAdLoo1HwK/lvPq" +
            "VkALBosiu5m13IPtdpyMewOUcSpKA8+pEJEk2TcoLzZ3LItZ8QATBv8Icx9CtMBR4Im4xvCZxnkcn0/mVdvrME" +
            "fmuDAgMBAAECggEAA4Ye0oyP6SzkFLu8fejekBzCCqwAfCH/43BYi7l0cNBF5KsNy0P84EoJf+TymYl1YOgmIe" +
            "GmoVltvdplvLZSMiX8sWOWtqIrULL7AC2etamjQ8PF9eV40lc8dyR9pJL0hhh1NoUUep4BABmT1VFbYWSZaW/Yc" +
            "eipqpD9cQ2zQ28aDvQR0j1B0qEeWnfGqYYQKzR/v7MoXqOfrVJ1Jo7CbUbBuvwkr01RRl03k0u+cGFpq6gbp9i" +
            "GXJ7M7ss83+bZXKdjTzrXCEASQ68YJ4MvexL7mp+4L79eBrUOh/TGCOJazDTxa1PfKCE5FaD8TBrDFtle07EPV" +
            "I0XYASsvFp6KQKBgQDMf5qlwL4s524Eeg/W/xxFdpNX8TrWlGWrrGb/CCsKwIepRCNkwP98cCG75pB0EpyCRwGh" +
            "wbDOhzgbt7TbfqafpvIB5LyeQNAktYiPbMZTXDV1o3SVa5RKPiT/tW2Ponhbqp17kjJdSJyDD9eYXxrLQTsQ88" +
            "milC6nD3HalKme7QKBgQCx6bn3or+TP8EjS13VRd5xGwPW7csDTS87AO/pwvQHnRQGgOx987Lq856kcVJy+ocs" +
            "es1qWIsiqJKT5TKQh6jzYB2/PPq/rB9gCPM1fV6hb8iy7rNiQbe1LrFawK9fgEo7RewxmBCjN0OdtA22AyIL" +
            "IC+jtBNL1LGB84zYRl12LwKBgBXi2kQ/GptnsWidP7C84OO2SxKwaKGqhC8ZZnSJBUJDVMGS307bMPy6a4HWrUM" +
            "e8s0mmFAdkLSp2CFvSdXr+h1AGsqFFoLBYQVswE7JT3iAd+A9PC75soc3m3Iakr06oDL/UZd2EBnXuZh1S5etJg" +
            "r20kGANeZGga+zgXXpTzYJAoGBAI0bbmbtSvqz5zBiF0MPTlTw80OliI3OyvYGUUJbYIclW3upB2kCP1a/8IRGa" +
            "PlOoKVzpLaDEZ9kihUJBOjC4ApfolhKOiqJjrzxfExhaguqiEj6r4Xvz4/BP+NVzgJ10upeE+5lyFRbgaJz6yg" +
            "AJiEi3wX8zg0n3b3O+FeUv437AoGAVzH3ITlUWkiv438TxUREJlppZLOW/vDzhuueU5BnbwSP21tmClpWnM0lYZ" +
            "KgpagUVG0AWklv+HOGP9OaTwW9Pectz3nCtOoAVUdPFNAlHgu+I6wsNVMAlQALfLMMh9CJssbWG+21O196oO6Q" +
            "QsGqllLZFDy77TJQy0hgWnttcWM=";

    /**
     * 生成秘钥对
     * @since jdk1.8
     * @date 2021/1/6
     * @throws Exception 异常
     * @return java.security.KeyPair
     */
    public static KeyPair getKeyPair() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取公钥(Base64编码)
     * @since jdk1.8
     * @date 2021/1/6
     * @param keyPair 密钥对
     * @return java.lang.String
     */
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     * @since jdk1.8
     * @date 2021/1/6
     * @param keyPair 密钥对
     * @return java.lang.String
     */
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 将Base64编码后的公钥转换成PublicKey对象
     * @since jdk1.8
     * @date 2021/1/6
     * @param pubStr 公钥
     * @return java.security.PublicKey
     */
    public static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将Base64编码后的私钥转换成PrivateKey对象
     * @since jdk1.8
     * @date 2021/1/6
     * @param priStr 私钥
     * @return java.security.PrivateKey
     */
    public static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**公钥加密*/
    private static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    /**私钥解密*/
    private static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**字节数组转Base64编码*/
    private static String byte2Base64(byte[] bytes){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    /**Base64编码转字节数组*/
    private static byte[] base642Byte(String base64Key) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64Key);
    }


    /**
     * RSA 加密
     * @since jdk1.8
     * @date 2019/9/27
     * @param str 待加密的字符串
     * @param publicKeyStr 公钥字符串
     * @param charset 编码格式
     * @return java.lang.String
     */
    public static String encrypt(String str, String publicKeyStr, String charset) throws Exception {

        //将Base64编码后的公钥转换成PublicKey对象
        if (publicKeyStr == null) {
            publicKeyStr = PUBLIC_KEY;
        }
        if (charset == null) {
            charset = "utf-8";
        }
        PublicKey publicKey = string2PublicKey(publicKeyStr);
        //用公钥加密
        byte[] publicEncrypt = RsaUtils.publicEncrypt(str.getBytes(charset), publicKey);
        //加密后的内容Base64编码
        return RsaUtils.byte2Base64(publicEncrypt);
    }

    /**
     * RSA解密
     * @since jdk1.8
     * @date 2019/9/27
     * @param str 待解密的字符串
     * @param privateKeyStr 私钥字符串
     * @param charset 编码格式
     * @return java.lang.String
     */
    public static String decrypt(String str, String privateKeyStr, String charset) throws Exception {

        if (privateKeyStr == null) {
            privateKeyStr = PRIVATE_KEY;
        }
        if (charset == null) {
            charset = "utf-8";
        }
        PrivateKey privateKey = RsaUtils.string2PrivateKey(privateKeyStr);
        //加密后的内容Base64解码
        byte[] base642Byte = RsaUtils.base642Byte(str);
        //用私钥解密
        byte[] privateDecrypt = RsaUtils.privateDecrypt(base642Byte, privateKey);
        return new String(privateDecrypt, charset);
    }
}
