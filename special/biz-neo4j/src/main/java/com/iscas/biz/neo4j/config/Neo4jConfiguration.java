package com.iscas.biz.neo4j.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/4/28 14:43
 * @since jdk11
 */
@AutoConfiguration
@EnableNeo4jRepositories(basePackages = "com.iscas.biz.neo4j.test.repository")
@EntityScan(basePackages = "com.iscas.biz.neo4j.test.domain")
public class Neo4jConfiguration {
}
