package com.iscas.base.biz.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 将request的编码格式改为UTF-8，否则可能出现文件上传时文件名乱码的问题
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/9 16:37
 * @since jdk1.8
 */
@AllArgsConstructor
public class CharsetEncodingSetFilter extends OncePerRequestFilter {
    private String charset;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        filterChain.doFilter(request, response);
    }
}
