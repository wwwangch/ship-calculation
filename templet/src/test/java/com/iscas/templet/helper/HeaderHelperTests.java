package com.iscas.templet.helper;

import com.iscas.templet.exception.HeaderException;
import com.iscas.templet.view.table.TableHeaderResponseData;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/24 21:53
 * @since jdk1.8
 */
public class HeaderHelperTests {

    @Test
    public void test() throws HeaderException {
        TableHeaderResponseData headerResponseData = HeaderHelper.convertToHeader(AnnotationHeaderTestBean.class);
        System.out.println(headerResponseData);
    }

}
