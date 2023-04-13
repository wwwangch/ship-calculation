package com.iscas.base.biz.util.license.server;

import com.iscas.base.biz.util.license.verify.LicenseVerify;
import com.iscas.base.biz.util.license.verify.LicenseVerifyParam;
import org.junit.jupiter.api.Test;

/**
 * 客户端校验license
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/22 10:33
 * @since jdk1.8
 */
public class LicenseVerifyTests {

    @Test
    public void verify() throws Exception {
        LicenseVerifyParam param = new LicenseVerifyParam();
        //对应生成license时设置的subject
        param.setSubject("license_demo");
        //对应导出的公钥的别名
        param.setPublicAlias("publicCert");
        //对应设置的storepass
        param.setStorePass("public_password1234");
        //生成license的地址
        param.setLicensePath("d:/license.lic");
        //公钥地址（给客户端时只需要给publicCerts.keystore）
        param.setPublicKeysStorePath("C:\\ideaProjects\\newframe\\biz-base\\src\\main\\resources\\truelicense\\publicCerts.keystore");

        LicenseVerify licenseVerify = new LicenseVerify();
        //安装证书（安装一次就可以了）
        licenseVerify.install(param);


        //验证license的时候不必每次都install
        for (int i = 0; i < 100; i++) {
            LicenseVerify lv = new LicenseVerify();
            boolean verify = lv.verify();
            System.out.println(verify);
        }

    }
}
