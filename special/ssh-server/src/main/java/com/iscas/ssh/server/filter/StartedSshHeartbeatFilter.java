package com.iscas.ssh.server.filter;

import com.iscas.base.biz.filter.started.AbstractStartedFilter;
import com.iscas.base.biz.filter.started.StartedFilterComponent;
import com.iscas.ssh.server.service.SSHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/12 9:16
 * @since jdk1.8
 */
@StartedFilterComponent(order = 4)
public class StartedSshHeartbeatFilter extends AbstractStartedFilter {
    @Autowired
    private SSHService sshService;

    /**
     * 调用下一个过滤器
     * */
    @Override
    public void doFilterInternal(ApplicationContext applicationContext) {
        sshService.sendHeartbeat();
        sshService.clearLostConnection();
        super.doFilterInternal(applicationContext);
    }
    @Override
    public String getName() {
        return "ssh心跳处理过滤器";
    }
}
