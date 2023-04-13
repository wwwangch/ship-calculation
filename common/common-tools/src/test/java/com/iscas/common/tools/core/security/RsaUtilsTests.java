package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/27 15:51
 * @since jdk1.8
 */
public class RsaUtilsTests {

    @Test
    public void testRSA() {
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            //生成RSA公钥和私钥，并Base64编码
            KeyPair keyPair = RsaUtils.getKeyPair();
            String publicKeyStr = RsaUtils.getPublicKey(keyPair);
            String privateKeyStr = RsaUtils.getPrivateKey(keyPair);
            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
            System.out.println("RSA私钥Base64编码:" + privateKeyStr);

            //=========================使用公钥加密
            String encrypt = RsaUtils.encrypt("祖国", publicKeyStr, "utf-8");
            Assertions.assertNotNull(encrypt);

            //=========================使用私钥解密
            String decrypt = RsaUtils.decrypt(encrypt, privateKeyStr, "utf-8");
            Assertions.assertNotNull(decrypt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
