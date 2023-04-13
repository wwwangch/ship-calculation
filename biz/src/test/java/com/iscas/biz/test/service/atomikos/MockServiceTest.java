package com.iscas.biz.test.service.atomikos;

import com.iscas.biz.BizApp;
import com.iscas.biz.test.service.db1.Db1TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * AtomikosTestService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>7月 16, 2021</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MockServiceTest {
    @Autowired
    private AtomikosTestService atomikosTestService;

    @MockBean
    private Db1TestService db1TestService;

    @Test
    public void test() {
        Mockito.when(db1TestService.test2())
                .thenReturn(false);
        atomikosTestService.test2();
    }



} 
