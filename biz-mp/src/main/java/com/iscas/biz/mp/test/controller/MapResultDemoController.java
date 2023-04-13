package com.iscas.biz.mp.test.controller;


import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.service.impl.MapResultService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MapResult 测试控制器
 *
 * @author admin*/
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/map")
@ConditionalOnMybatis
public class MapResultDemoController extends BaseController {
    @Autowired
    private MapResultService mapResultService;
    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        ResponseEntity responseEntity = getResponse();
        responseEntity.setValue(mapResultService.selectAll());
        return responseEntity;
    }

    @GetMapping("/findById")
    public ResponseEntity findById(Integer id){
        ResponseEntity responseEntity = getResponse();
        responseEntity.setValue(mapResultService.selectById(id));
        return responseEntity;
    }

}
