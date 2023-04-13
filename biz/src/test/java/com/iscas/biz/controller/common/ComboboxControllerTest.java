//package com.iscas.biz.controller.common;
//
//import com.iscas.biz.BizApp;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.annotation.Resource;
//
///**
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/6/19 20:39
// * @since jdk1.8
// */
//@SpringBootTest(classes = BizApp.class)
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//public class ComboboxControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void roleComboboxTest() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/combobox/role")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("操作成功")))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        mvcResult.getResponse().setCharacterEncoding("utf-8");
//        System.out.println(mvcResult.getResponse().getContentAsString());
//    }
//}