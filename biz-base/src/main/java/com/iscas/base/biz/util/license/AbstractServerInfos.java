package com.iscas.base.biz.util.license;


import com.iscas.templet.exception.Exceptions;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 用于获取客户服务器的基本信息，如：IP、Mac地址、CPU序列号、主板序列号等
 * @author zhuquanwen
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "rawtypes"})
public abstract class AbstractServerInfos {


    /**
     * 组装需要额外校验的License参数
     *
     */
    public LicenseCheckModel getServerInfos() throws Exception {
        LicenseCheckModel result = new LicenseCheckModel();


        result.setIpAddress(this.getIpAddress());
        result.setMacAddress(this.getMacAddress());
        result.setCpuSerial(this.getCPUSerial());
        result.setMainBoardSerial(this.getMainBoardSerial());


        return result;
    }

    /**
     * 获取IP地址
     *
     */
    protected abstract List<String> getIpAddress() throws Exception;

    /**
     * 获取Mac地址
     * @since 1.0.0
     */
    protected abstract List<String> getMacAddress() throws Exception;

    /**
     * 获取CPU序列号
     */
    protected abstract String getCPUSerial() throws Exception;

    /**
     * 获取主板序列号
     */
    protected abstract String getMainBoardSerial() throws Exception;

    /**
     * 获取当前服务器所有符合条件的InetAddress
     */
    protected List<InetAddress> getLocalAllInetAddress() throws Exception {
        List<InetAddress> result = new ArrayList<>(4);

        // 遍历所有的网络接口
        for (Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
            NetworkInterface iface = (NetworkInterface) networkInterfaces.nextElement();
            // 在所有的接口下再遍历IP
            for (Enumeration inetAddresses = iface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                InetAddress inetAddr = (InetAddress) inetAddresses.nextElement();

                //排除LoopbackAddress、SiteLocalAddress、LinkLocalAddress、MulticastAddress类型的IP地址
                if (!inetAddr.isLoopbackAddress() && !inetAddr.isLinkLocalAddress() && !inetAddr.isMulticastAddress()) {
                    result.add(inetAddr);
                }
            }
        }
        return result;
    }

    /**
     * 获取某个网络接口的Mac地址
     */
    protected String getMacByInetAddress(InetAddress inetAddr) {
        byte[] mac;
        try {
            mac = NetworkInterface.getByInetAddress(inetAddr).getHardwareAddress();
        } catch (SocketException e) {
            throw Exceptions.runtimeException(e);
        }
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                stringBuffer.append("-");
            }

            //将十六进制byte转化为字符串
            String temp = Integer.toHexString(mac[i] & 0xff);
            if (temp.length() == 1) {
                stringBuffer.append("0").append(temp);
            } else {
                stringBuffer.append(temp);
            }
        }
        return stringBuffer.toString().toUpperCase();
    }

}
