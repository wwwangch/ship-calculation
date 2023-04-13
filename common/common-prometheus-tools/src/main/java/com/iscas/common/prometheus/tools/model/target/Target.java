package com.iscas.common.prometheus.tools.model.target;

import com.iscas.common.prometheus.tools.model.Label;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/31 10:46
 */
@Data
public class Target implements Serializable {

    /**
     * discoveredLabels
     * */
    private List<Label> discoveredLabels;

    /**
     * labels
     * */
    private List<Label> labels;

    /**
     * scrapePool
     * */
    private String scrapePool;

    /**
     * scrapeUrl
     * */
    private String scrapeUrl;

    /**
     * globalUrl
     * */
    private String globalUrl;

    /**
    * 上一个错误
    * */
    private String lastError;

    /**
     * 上次抓取时间
     * */
    private LocalDateTime lastScrape;

    /**
     * lastScrapeDuration
     *  */
    private Double lastScrapeDuration;

    /**
    * 健康情况
    * */
    private String health;

    /**
     * 抓取间隔
     * */
    private String scrapeInterval;

    /**
     * 抓取超时时间
     * */
    private String scrapeTimeout;

}
