package com.iscas.biz.neo4j.test.service.impl;


import com.iscas.biz.neo4j.test.domain.Policy;
import com.iscas.biz.neo4j.test.repository.PolicyRepository;
import com.iscas.biz.neo4j.test.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private PolicyRepository policyRepository;


    @Override
    public Collection<Policy> findAll() {
        return policyRepository.findAll();
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public List<Policy> findByTitleLike(String title) {
        return policyRepository.findByTitleLike(title);
    }

    @Override
    public void update(Policy policy) {
        policyRepository.save(policy);
    }
}
