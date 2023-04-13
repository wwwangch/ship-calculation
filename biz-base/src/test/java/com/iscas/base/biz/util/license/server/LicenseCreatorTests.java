package com.iscas.base.biz.util.license.server;

import com.iscas.base.biz.util.license.LicenseCheckModel;
import com.iscas.base.biz.util.license.LicenseCreator;
import com.iscas.base.biz.util.license.LicenseCreatorParam;
import com.iscas.base.biz.util.license.WindowsServerInfos;
import com.iscas.common.web.tools.json.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/22 8:36
 * @since jdk1.8
 */
public class LicenseCreatorTests {


    /***********************************************************/
    /***********************************************************/
    /*******************证书生成********************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/

    /**
     * 以下方式环境变量中必须使用jdk8，jdk11生成的证书有问题
     *
     *
     * 1、生成命令
     * keytool -genkeypair -keysize 1024 -validity 3650 -alias "privateKey" -keystore "privateKeys.keystore" -storepass "public_password1234" -keypass "private_password1234" -dname "CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN"
     *
     * 2、导出命令
     * keytool -exportcert -alias "privateKey" -keystore "privateKeys.keystore" -storepass "public_password1234" -file "certfile.cer"
     *
     * 3、导入命令
     * keytool -import -alias "publicCert" -file "certfile.cer" -keystore "publicCerts.keystore" -storepass "public_password1234"
     * */



    //测试获取机器信息
    @Test
    public void testGetComputeInfo() throws Exception {
        LicenseCheckModel serverInfos = new WindowsServerInfos().getServerInfos();
        System.out.println(serverInfos);
        System.out.println(JsonUtils.toJson(serverInfos));
    }


    //测试生成证书
    @Test
    public void testCreator() throws Exception {
        LicenseCreatorParam licenseCreatorParam = new LicenseCreatorParam();
        //项目
        licenseCreatorParam.setSubject("license_demo");

        //秘钥别称
        licenseCreatorParam.setPrivateAlias("privateKey");

        //密钥密码（需要妥善保管，不能让使用者知道）
        licenseCreatorParam.setKeyPass("private_password1234");

        // 访问秘钥库的密码
        licenseCreatorParam.setStorePass("public_password1234");

        //证书生成路径
        licenseCreatorParam.setLicensePath("d:/license.lic");

        //秘钥库存储路径
        licenseCreatorParam.setPrivateKeysStorePath("C:\\ideaProjects\\newframe\\biz-base\\src\\main\\resources\\truelicense\\privateKeys.keystore");

        // 证书生效时间
        licenseCreatorParam.setIssuedTime(new Date());

        //证书失效时间
        licenseCreatorParam.setExpiryTime(new Date(System.currentTimeMillis() + 10L * 356 * 24 * 3600 * 1000));
//        licenseCreatorParam.setExpiryTime(new Date(System.currentTimeMillis() + 20 * 1000));

        //用户类型
        licenseCreatorParam.setConsumerType("User");

        //用户数量
        licenseCreatorParam.setConsumerAmount(1);

        //描述信息
        licenseCreatorParam.setDescription("this is desc");

        //硬件信息
        LicenseCheckModel licenseCheckModel = JsonUtils.fromJson("{\"ipAddress\":[\"192.168.47.1\",\"192.168.126.1\",\"192.168.100.88\"],\"macAddress\":[\"00-50-56-C0-00-08\",\"00-50-56-C0-00-01\",\"A8-5E-45-9E-E9-E6\"],\"cpuSerial\":\"BFEBFBFF000906ED\",\"mainBoardSerial\":\"191161945108535\"}",
                LicenseCheckModel.class);
        licenseCreatorParam.setLicenseCheckModel(licenseCheckModel);

        LicenseCreator licenseCreator = new LicenseCreator(licenseCreatorParam);
        boolean b = licenseCreator.generateLicense();
        System.out.println(b);
    }

}
