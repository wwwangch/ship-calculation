package com.iscas.common.tools.ip2region;//package com.iscas.common.tools.ip2region;
//
//import org.lionsoul.ip2region.*;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
///**
// * IP地域相关工具
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/9/29 17:42
// * @since jdk1.8
// */
//public class Ip2RegionUtils {
//
//    private Ip2RegionUtils() {
//    }
//
//    /**
//     * 根据IP地址查IP地域信息
//     * @since jdk1.8
//     * @date 2018/9/30
//     * @param ip　IP地址
//     * @param dbPath ip2region.db文件所在位置，这个文件若没有请到以下地址下载：
//     *                  <a href="https://gitee.com/lionsoul/ip2region/tree/master/data">https://gitee.com/lionsoul/ip2region/tree/master/data</a>
//     * @throws DbMakerConfigException 异常
//     * @throws FileNotFoundException 异常
//     * @throws NoSuchMethodException 异常
//     * @throws InvocationTargetException 异常
//     * @throws IllegalAccessException 异常
//     * @return java.lang.String
//     */
//
//    @SuppressWarnings("ConstantConditions")
//    public static String getCityInfo(String ip, String dbPath) throws DbMakerConfigException, FileNotFoundException,
//            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        File file = new File(dbPath);
//        if (!file.exists()) {
//            System.out.println("Error: Invalid ip2region.db file");
//        }
//
//        //查询算法
//        //B-tree
//        int algorithm = DbSearcher.BTREE_ALGORITHM;
//        //DbSearcher.BINARY_ALGORITHM //Binary
//        //DbSearcher.MEMORY_ALGORITYM //Memory
//
//        DbConfig config = new DbConfig();
//        DbSearcher searcher = new DbSearcher(config, dbPath);
//        //define the method
//        Method method = null;
//        switch (algorithm) {
//            case DbSearcher.BTREE_ALGORITHM:
//                method = searcher.getClass().getMethod("btreeSearch", String.class);
//                break;
//            case DbSearcher.BINARY_ALGORITHM:
//                method = searcher.getClass().getMethod("binarySearch", String.class);
//                break;
//            case DbSearcher.MEMORY_ALGORITYM:
//                method = searcher.getClass().getMethod("memorySearch", String.class);
//                break;
//            default: break;
//        }
//
//        DataBlock dataBlock;
//        if (!Util.isIpAddress(ip)) {
//            System.out.println("Error: Invalid ip address");
//        }
//        dataBlock = (DataBlock) method.invoke(searcher, ip);
//        return dataBlock.getRegion();
//    }
//}
