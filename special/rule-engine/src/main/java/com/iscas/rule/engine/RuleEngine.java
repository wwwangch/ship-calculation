package com.iscas.rule.engine;

import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.rule.engine.exception.RuleException;
import com.iscas.rule.engine.factory.RuleEngineServiceFactory;
import com.iscas.rule.engine.service.ExpressionActionService;
import com.iscas.rule.engine.service.MemoryQueueService;
import com.iscas.rule.engine.service.ServiceRegister;
import com.iscas.rule.engine.service.MyExpressionService;
import com.iscas.rule.engine.util.LogUtils;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *  规则引擎启动入口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 13:53
 * @since jdk1.8
 */
@Slf4j
@SuppressWarnings("JavadocDeclaration")
public class RuleEngine {
    public static String RULE_ENGINE_NO = null;

    /**
     * 开启规则引擎
     * */
    public void start() throws RuleException {
        RULE_ENGINE_NO = createRuleEngineNo();
        LogUtils.info(log, String.format("正在启动规则引擎:%s", RULE_ENGINE_NO));
        //注册业务service
        ServiceRegister.registService();
        //读取规则
        RuleEngineServiceFactory.getService(MyExpressionService.class).readExpression();
        //初始化队列
        RuleEngineServiceFactory.getService(MemoryQueueService.class).initQueues();
        //开启执行引擎
        RuleEngineServiceFactory.getService(ExpressionActionService.class).exec();

        LogUtils.info(log, String.format("规则引擎:%s已启动", RULE_ENGINE_NO));

    }

    /**
     * 生成规则引擎编号
     * */
    private String createRuleEngineNo() {
        return RandomStringUtils.randomStr(8);
    }

    public static void main(String[] args) throws RuleException {
        new RuleEngine().start();
    }

}
