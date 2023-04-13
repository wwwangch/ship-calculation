package com.iscas.common.tools.gis;

/**
 * 经纬度相关计算工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/17 15:29
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class LatLonUtils {
    private static final double EARTH_RADIUS = 6371.393 * 1000;

    private LatLonUtils() {
    }

    /**
     * 计算两个经纬度之间的距离
     *
     * @param lat1 lat1
     * @param lon1 lon1
     * @param lat2 lat2
     * @param lon2 lon2
     * @return double
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }


    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

}
