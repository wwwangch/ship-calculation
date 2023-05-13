package com.iscas.biz.calculation.grpc;

import com.iscas.common.rpc.tools.grpc.client.GrpcClientUtils;
import io.grpc.ManagedChannel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 14:06
 */
@Component
@ConfigurationProperties(prefix = "grpc.client")
public class GrpcHolder {

    private String ip = "127.0.0.1";

    private Integer port = 50051;

    private CalculationGrpc.CalculationBlockingStub calculationBlockingStub;

    public CalculationGrpc.CalculationBlockingStub calculationBlockingStub() {
        CalculationGrpc.CalculationBlockingStub temp = calculationBlockingStub;
        if (null == temp) {
            synchronized (GrpcHolder.class) {
                temp = calculationBlockingStub;
                if (null == temp) {
                    ManagedChannel managedChannel = GrpcClientUtils.getManagedChannel(ip, port);
                    calculationBlockingStub = CalculationGrpc.newBlockingStub(managedChannel);
                }
            }
        }
        return calculationBlockingStub;
    }
}
