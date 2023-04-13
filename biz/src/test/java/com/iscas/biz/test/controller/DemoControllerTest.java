package com.iscas.biz.test.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mock单元测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/15 20:46
 * @since jdk1.8
 */
public class DemoControllerTest {
    private static MockMvc mockMvc = null;

    @BeforeAll
    public static void init() {
        WebApplicationContext context;
        mockMvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
    }

    @Test
    public void testTx() throws Exception {
     MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/tx")
             .accept(MediaType.APPLICATION_JSON)
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(MockMvcResultMatchers.status().isOk())
             .andExpect(MockMvcResultMatchers.content().string("tx"))
             .andDo(MockMvcResultHandlers.print())
             .andReturn();
     mvcResult.getResponse().setCharacterEncoding("utf-8");
     System.out.println(mvcResult.getResponse().getContentAsString());
    }

}