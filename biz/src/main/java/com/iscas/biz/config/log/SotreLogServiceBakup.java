//package com.iscas.biz.config.log;
//
//import com.iscas.biz.mapper.LogMapper;
//import com.iscas.biz.model.Log;
//import com.iscas.biz.util.UserUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/2/21 18:16
// * @since jdk1.8
// */
//@Service
//public class SotreLogServiceBakup implements IStoreLogService {
//    @Autowired
//    private LogMapper logMapper;
//    @Override
//    public void store(Map<String, Object> logInfo) {
//        Log log = new Log();
////        log.setCreateTime(new Date());
//        log.setDescription((String) logInfo.get("desc"));
//        log.setType(((LogType)logInfo.get("type")).getIntVal());
//        String username = "匿名";
//        try {
//            String loginUsername = UserUtils.getLoginUsername();
//            if (StringUtils.isNotEmpty(loginUsername)) {
//                username = loginUsername;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.setOperate(username);
//        logMapper.insert(log);
//    }
//}
