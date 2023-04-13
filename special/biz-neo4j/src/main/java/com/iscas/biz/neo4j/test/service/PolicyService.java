package com.iscas.biz.neo4j.test.service;

import com.iscas.biz.neo4j.test.domain.Policy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/22 15:54
 * @since jdk1.8
 */
@Service
public interface PolicyService {

    Collection<Policy> findAll();

    void save(Policy policy);

    List<Policy> findByTitleLike(String title);

    void update(Policy policy);

}
