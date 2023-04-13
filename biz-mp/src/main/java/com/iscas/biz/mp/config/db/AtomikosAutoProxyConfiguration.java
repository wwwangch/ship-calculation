//package com.iscas.biz.mp.config.db;
//
//import com.atomikos.icatch.jta.UserTransactionImp;
//import com.atomikos.icatch.jta.UserTransactionManager;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.jta.JtaTransactionManager;
//
//import javax.transaction.SystemException;
//
///**
// * 事务管理配置
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/5/20 14:56
// * @since jdk1.8
// */
//@Configuration
//public class AtomikosAutoProxyConfiguration {
//
//    @Bean(name="atomikosTransactionManager",initMethod="init",destroyMethod="close")
//    public UserTransactionManager userTransactionManager(){
//        UserTransactionManager transactionManager = new UserTransactionManager();
//        transactionManager.setForceShutdown(false);
//        return transactionManager;
//    }
//
//    @Bean(name="atomikosUserTransaction")
//    public UserTransactionImp userTransactionImp() throws SystemException {
//        UserTransactionImp transactionImp = new UserTransactionImp();
//        transactionImp.setTransactionTimeout(120);
//        return transactionImp;
//    }
//
//    @Bean("springTransactionManager")
//    public JtaTransactionManager transactionManager(
//            @Qualifier("atomikosTransactionManager") UserTransactionManager transactionManager,
//            @Qualifier("atomikosUserTransaction") UserTransactionImp userTransaction) {
//        JtaTransactionManager jtaTM = new JtaTransactionManager();
//        jtaTM.setTransactionManager(transactionManager);
//        jtaTM.setUserTransaction(userTransaction);
//        jtaTM.setAllowCustomIsolationLevels(true);
//        return jtaTM;
//    }
//
////    @Bean(name = "txAdvice")
////    public TransactionInterceptor getAdvisor(@Qualifier("springTransactionManager") JtaTransactionManager txManager)
////            throws Exception {
////        TransactionInterceptor tsi = new TransactionInterceptor();
////        Properties properties = new Properties();
////        properties.setProperty("get*", "PROPAGATION_SUPPORTS");
////        properties.setProperty("select*", "PROPAGATION_SUPPORTS");
////        properties.setProperty("load*", "PROPAGATION_SUPPORTS");
////        properties.setProperty("query*", "PROPAGATION_SUPPORTS");
////        properties.setProperty("list*", "PROPAGATION_SUPPORTS");
////        properties.setProperty("add*", "PROPAGATION_REQUIRED");
////        properties.setProperty("insert*", "PROPAGATION_REQUIRED");
////        properties.setProperty("save*", "PROPAGATION_REQUIRED");
////        properties.setProperty("update*", "PROPAGATION_REQUIRED");
////        properties.setProperty("modify*", "PROPAGATION_REQUIRED");
////        properties.setProperty("do*", "PROPAGATION_REQUIRED");
////        properties.setProperty("del*", "PROPAGATION_REQUIRED");
////        properties.setProperty("remove*", "PROPAGATION_REQUIRED");
////        properties.setProperty("process*", "PROPAGATION_REQUIRED");
////        properties.setProperty("create*", "PROPAGATION_REQUIRED");
////        properties.setProperty("valid*", "PROPAGATION_REQUIRED");
////        properties.setProperty("do*", "PROPAGATION_REQUIRED");
////        properties.setProperty("write*", "PROPAGATION_REQUIRED");
////        properties.setProperty("cancel*", "PROPAGATION_REQUIRED");
////        properties.setProperty("*", "readOnly");
////        tsi.setTransactionAttributes(properties);
////        tsi.setTransactionManager(txManager);
////        return tsi;
////    }
////
////    @Bean
////    public BeanNameAutoProxyCreator txProxy() {
////        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
////        creator.setInterceptorNames("txAdvice");
////        creator.setBeanNames("*ServiceImpl");
////        creator.setProxyTargetClass(true);
////        return creator;
////    }
//
//}
