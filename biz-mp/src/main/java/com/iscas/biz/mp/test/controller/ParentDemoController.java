package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.model.Parent;
import com.iscas.biz.mp.test.service.IParentService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/parent")
@ConditionalOnMybatis
public class ParentDemoController extends BaseController {
    @Autowired
    private IParentService parentService;
    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        ResponseEntity responseEntity = getResponse();
        responseEntity.setValue(parentService.findCascadeAll());
        return responseEntity;
    }

    @PutMapping()
    public ResponseEntity insert(@RequestBody Parent parent){
        ResponseEntity responseEntity = getResponse();
        parentService.save(parent);
        responseEntity.setValue(parent.getId());
        return responseEntity;
    }

    @PostMapping("/findAllByCondition")
    public ResponseEntity findAllByCondition(@RequestBody Parent parent){
        ResponseEntity responseEntity = getResponse();
        responseEntity.setValue(parentService.findCascadeAllByCondition(parent));
        return responseEntity;
    }

}
