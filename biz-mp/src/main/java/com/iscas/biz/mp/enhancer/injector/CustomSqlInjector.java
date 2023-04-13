package com.iscas.biz.mp.enhancer.injector;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.iscas.common.tools.core.io.file.ScannerUtils;
import com.iscas.common.tools.exception.lambda.Lambdas;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义SQL注入器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/23 14:18
 * @since jdk1.8
 */
public class CustomSqlInjector extends DefaultSqlInjector {
    private volatile List<AbstractMethod> methodCache;

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        if (methodCache == null) {
            synchronized (CustomSqlInjector.class) {
                if (methodCache == null) {
                    List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
                    methodList.addAll(getCustomMethods());
                    methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
                    methodCache = methodList;
                }
            }
        }
        return methodCache;
    }

    /**
     * 获取methods包下的所有方法类，并实例化
     */
    private List<? extends AbstractMethod> getCustomMethods() {
        String methodsPackageName = CustomSqlInjector.class.getPackageName() + ".methods";
        return ScannerUtils.getClasses(methodsPackageName)
                .stream().filter(AbstractMethod.class::isAssignableFrom)
                .map(Lambdas.wrapperFunction(clazz -> (AbstractMethod) clazz.getDeclaredConstructor().newInstance()))
                .toList();
    }

}
