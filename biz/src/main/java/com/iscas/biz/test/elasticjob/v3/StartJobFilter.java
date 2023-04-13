package com.iscas.biz.test.elasticjob.v3;//package com.iscas.biz.test.elasticjob;

import com.iscas.base.biz.config.elasticjob.v3.ConditionalOnElasticJob;
import com.iscas.base.biz.config.elasticjob.v3.ElasticJobEntity;
import com.iscas.base.biz.config.elasticjob.v3.ElasticJobHandler;
import com.iscas.base.biz.filter.started.AbstractStartedFilter;
import com.iscas.base.biz.filter.started.StartedFilterComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * 开启测试定时任务
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/17 14:21
 * @since jdk1.8
 */
@ConditionalOnElasticJob()
@StartedFilterComponent(order = 101)
@Slf4j
public class StartJobFilter extends AbstractStartedFilter {
    @Autowired
    private ElasticJobHandler elasticJobHandler;

    @Override
    public void doFilterInternal(ApplicationContext applicationContext) {
        //测试开启一个普通定时任务
        ElasticJobEntity simpleAddElasticJobEntity = new ElasticJobEntity()
                .setJobName("testAddSimpleJob")
                .setCron("0 0/5 * * * ?")
                .setType(1)
                .setShardingItemParameters("0=xxx")
                .setShardingTotalCount(1)
                .setJobParameter("test-add-simple");
        elasticJobHandler.startJob(simpleAddElasticJobEntity);

        //测试开启一个流式定时任务,不配置流式拉取，实际和普通的效果差不多
        ElasticJobEntity dataFlowAddElasticJobEntity = new ElasticJobEntity()
                .setJobName("testAddDataFlowJob")
                .setCron("0 0/3 * * * ?")
                .setType(1)
                .setShardingItemParameters("0=xxx")
                .setShardingTotalCount(1)
                .setJobParameter("test-add-data-flow");
        elasticJobHandler.startJob(dataFlowAddElasticJobEntity);

        //测试开启一个流式定时任务,配置为流式拉取，会一直拉取，直到fetchData没有获取到数据或者发生了重新分片才会停止
        ElasticJobEntity dataFlowWithStreamProcessAddElasticJobEntity = new ElasticJobEntity()
                .setJobName("testAddDataFlowJobWithStreamProcess")
                .setCron("0 0/2 * * * ?")
                .setType(1)
                .setShardingItemParameters("0=xxx")
                .setShardingTotalCount(1)
                .setJobParameter("test-add-data-flow-with-stream-process");
        elasticJobHandler.startJob(dataFlowWithStreamProcessAddElasticJobEntity);

        //脚本的未测试
//        elasticJobHandler.addScriptJob("testScriptJob-1", "0/15 * * * * ?", 2, "test parameter", "0=a,1=b", "d:/run.bat");

        super.doFilterInternal(applicationContext);
    }

    @Override
    public String getName() {
        return "测试elasticjob过滤器1";
    }
}
