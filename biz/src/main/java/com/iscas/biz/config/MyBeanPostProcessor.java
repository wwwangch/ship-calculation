package com.iscas.biz.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义BeanPostProcessor
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/29
 * @since jdk11/
 */
@SuppressWarnings({"unused", "unchecked"})
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Override
    public Object postProcessBeforeInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
//        handleServerEndPoint(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
        // 使用Swagger3Config中的WebMvcEndpointHandlerMapping bean处理swagger3.0的问题
//        handleWebMvcRequestHandlerProvider(beanName, bean);
        return bean;
    }

//    @Deprecated
//    private void handleWebMvcRequestHandlerProvider(String beanName, Object bean) {
//        // 处理swagger 在spring boot2.6以上不可用的问题
//        boolean modify = (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) &&
//                defaultListableBeanFactory.containsBean(beanName);
//        if (modify) {
//            // 修改属性
//            try {
//                List<RequestMappingInfoHandlerMapping> handlerMappings = (List<RequestMappingInfoHandlerMapping>) ReflectUtils.getValue(bean,
//                        bean.getClass(), "handlerMappings");
//                List<RequestMappingInfoHandlerMapping> tmpHandlerMappings = handlerMappings.stream()
//                        .filter(mapping -> Objects.isNull(mapping.getPatternParser()))
//                        .collect(Collectors.toList());
//                handlerMappings.clear();
//                handlerMappings.addAll(tmpHandlerMappings);
//            } catch (Exception e) {
//                log.warn("修改WebMvcRequestHandlerProvider的属性：handlerMappings出错，可能导致swagger不可用", e);
//            }
//        }
//    }
//
//
//    private void handleServerEndPoint(Object bean) {
//        //获取serverEndpoint
//        ServerEndpoint serverEndpoint = AnnotationUtils.findAnnotation(bean.getClass(), ServerEndpoint.class);
//        if (!Objects.isNull(serverEndpoint)) {
//            //设置@ServerEndpoint注解支持继承，相当于注解@Inherited，应对动态代理导致类上的@ServerEndpoint注解丢失
//            InvocationHandler h = Proxy.getInvocationHandler(serverEndpoint);
//            try {
//                Field typeField = ReflectUtils.getField(h.getClass(), "type");
//                ReflectUtils.makeAccessible(typeField);
//                Field annotationTypeField = ReflectUtils.getField(Class.class, "annotationType");
//                ReflectUtils.makeAccessible(annotationTypeField);
//                Object o = annotationTypeField.get(typeField.get(h));
//                Field inheritedField = ReflectUtils.getField(o.getClass(), "inherited");
//                ReflectUtils.makeFinalModifiers(inheritedField);
//                inheritedField.set(o, true);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                throw Exceptions.runtimeException("修改@ServerEndPoint注解失败");
//            }
//        }
//    }


}
