package com.iscas.biz.neo4j.test.controller;

import com.iscas.biz.neo4j.test.domain.Policy;
import com.iscas.biz.neo4j.test.service.PolicyService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/22 15:56
 * @since jdk1.8
 */
@RestController
public class PolicyController extends BaseController {
    @Autowired
    private PolicyService policyService;

    @GetMapping("/testfindAll")
    public ResponseEntity testFindAll() {
        ResponseEntity response = getResponse();
        Collection<Policy> all = policyService.findAll();
        response.setValue(all);
        return response;
    }


    @GetMapping("/testSave")
    public ResponseEntity testSave() {
        ResponseEntity response = getResponse();
        Policy policy = new Policy();
        policy.setTitle("我是一个粉刷匠");
        Policy subPolicy = new Policy().setTitle("我是一个小粉刷匠");
        policy.getPolicys().add(subPolicy);
        policyService.save(policy);
        return response;
    }

    @GetMapping("/testUpdate")
    public ResponseEntity testUpdate() {
        ResponseEntity response = getResponse();
        List<Policy> policies = policyService.findByTitleLike("一个粉刷匠");
        Policy policy = policies.get(0);
        Policy subPolicy = new Policy().setTitle("我是一个小粉刷匠2号");
        policy.getPolicys().add(subPolicy);
        policyService.save(policy);
        return response;
    }

}
