package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.test.model.TestE;
import com.iscas.base.biz.util.Excel2BeanUtils;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/5 19:32
 * @since jdk1.8
 */
@RestController
public class TestReadExcelController {
    @PostMapping("/testExcelRead")
    public ResponseEntity test1(HttpServletRequest request) throws Exception {
        ResponseEntity responseEntity = new ResponseEntity();
        request.setCharacterEncoding("UTF-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;

        Map map = multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
            Object obj = i.next();
            multipartFile = (MultipartFile) map.get(obj);
        }
        List<TestE> results = Excel2BeanUtils.excel2Bean(multipartFile.getInputStream(),"excelToBean.txt", TestE.class);
        responseEntity.setValue(results);
        return responseEntity;
    }
}
