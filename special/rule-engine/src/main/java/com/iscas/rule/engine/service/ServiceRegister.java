package com.iscas.rule.engine.service;

import com.iscas.common.tools.assertion.AssertStrUtils;
import com.iscas.common.tools.core.reflect.ClassUtils;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import com.iscas.rule.engine.RuleEngineConstant;
import com.iscas.rule.engine.anno.REAutowired;
import com.iscas.rule.engine.anno.REComponent;
import com.iscas.rule.engine.exception.RuleException;
import com.iscas.rule.engine.factory.RuleEngineServiceFactory;
import com.iscas.rule.engine.util.ConfigUtils;
import com.iscas.rule.engine.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 业务注册工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 14:08
 * @since jdk1.8
 */
@Slf4j
@SuppressWarnings("JavadocDeclaration")
public class ServiceRegister {
    private ServiceRegister () {}

    /**
     * 业务注册
     * */
    public static void registService() throws RuleException {
        LogUtils.debug(log, "正在扫描规则引擎业务packages");
        //扫描包
        String scanPackages = ConfigUtils.readProp(RuleEngineConstant.COMPONENT_SCAN_PACKAGE);
        LogUtils.debug(log, "扫描规则引擎业务packages结束");
        AssertStrUtils.assertStrNotEmpty(scanPackages, "component.scan.packages配置不能为空");
        LogUtils.info(log, "正在注册业务类");
        registREComponentClasses(scanPackages);
        LogUtils.info(log, "注册业务类结束");

    }

    private static void registREComponentClasses(String scanPackages) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        Arrays.stream(scanPackages.split(",")).forEach(scanPackage -> {
            try {
                Set<Class<?>> set = ClassUtils.getClasses(scanPackage);
                classes.addAll(set);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        classes.stream().filter(tclass -> tclass.getAnnotation(REComponent.class) != null).forEach(reClass -> {
            try {
                createNewInstance(reClass, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Object createNewInstance(Class<?> reClass, boolean isField) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //尝试从一级缓存中获取对象
        Object o = RuleEngineServiceFactory.RULE_ENGINE_SERVICES_FIRST_CACHE.get(reClass);
        if (o == null) {
            //尝试从二级缓存中获取对象
            o = RuleEngineServiceFactory.RULE_ENGINE_SERVICES_SECOND_CACHE.get(reClass);
            if (o != null) {
                //移入一级缓存
                RuleEngineServiceFactory.RULE_ENGINE_SERVICES_SECOND_CACHE.remove(reClass);
                RuleEngineServiceFactory.RULE_ENGINE_SERVICES_FIRST_CACHE.put(reClass, o);
            } else {
                //二级缓存中也不存在此对象
                //创建一个新对象，必须有无参构造器
                o = reClass.getConstructor().newInstance();
//                Object[] os = new Object[1];
//                os[0] = o;
                final Object ox = o;
                //将创建的对象放入二级缓存
                RuleEngineServiceFactory.RULE_ENGINE_SERVICES_SECOND_CACHE.put(reClass, o);
                Field[] declaredFields = ReflectUtils.getCurrentFields(o.getClass());
                if (ArrayUtils.isNotEmpty(declaredFields)) {
                    Arrays.stream(declaredFields).filter(field -> field.getType().getAnnotation(REComponent.class) != null && field.getAnnotation(REAutowired.class) != null)
                            .forEach(field -> {
                                try {
                                    Object fieldO = createNewInstance(field.getType(), true);
                                    ReflectUtils.setValue(field, ox, fieldO);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                }
                if (!isField) {
                    //从二级缓存移入一级
                    RuleEngineServiceFactory.RULE_ENGINE_SERVICES_SECOND_CACHE.remove(reClass);
                    RuleEngineServiceFactory.RULE_ENGINE_SERVICES_FIRST_CACHE.put(reClass, o);
                }
            }
        }
        return o;
    }
}
