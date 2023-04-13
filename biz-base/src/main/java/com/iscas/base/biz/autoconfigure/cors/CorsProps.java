package com.iscas.base.biz.autoconfigure.cors;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhuquanwen
 */
@ConfigurationProperties(prefix = "cors")
@Data
@NoArgsConstructor
public class CorsProps {
    private String originPattern = "*";
    private String credentials = "true";
    private String methods = "POST, GET, PUT, DELETE, OPTIONS";
    private String headers = "Content-Type, Data-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Accept, DataType, responseType";
    private List<String> ignoreUrls = new ArrayList<>();
    private String maxage = "3600";
}
