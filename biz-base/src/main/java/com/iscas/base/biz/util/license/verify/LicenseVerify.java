package com.iscas.base.biz.util.license.verify;

import com.iscas.base.biz.util.license.CustomKeyStoreParam;
import de.schlichtherle.license.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.prefs.Preferences;

/**
 * License校验类
 * @author zhuquanwen
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public class LicenseVerify {

    /**
     * 安装License证书
     */
    public synchronized LicenseContent install(LicenseVerifyParam param) throws Exception {
        LicenseContent result;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //1. 安装证书

        LicenseManager licenseManager = LicenseManagerHolder.getInstance(initLicenseParam(param));
        licenseManager.uninstall();

        result = licenseManager.install(new File(param.getLicensePath()));


        return result;
    }

    /**
     * 校验License证书
     *
     * @return boolean
     */
    public boolean verify() throws Exception {
        LicenseManager licenseManager = LicenseManagerHolder.getInstance(null);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //2. 校验证书

        LicenseContent licenseContent = licenseManager.verify();

        return true;

    }

    /**
     * 初始化证书生成参数
     *
     * @param param License校验类需要的参数
     * @return de.schlichtherle.license.LicenseParam
     */
    private LicenseParam initLicenseParam(LicenseVerifyParam param) {
        Preferences preferences = Preferences.userNodeForPackage(LicenseVerify.class);

        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());

        KeyStoreParam publicStoreParam = new CustomKeyStoreParam(LicenseVerify.class, param.getPublicKeysStorePath(),
                param.getPublicAlias(), param.getStorePass(), null);

        return new DefaultLicenseParam(param.getSubject(), preferences, publicStoreParam, cipherParam);
    }

}
