package com.iscas.base.biz.autoconfigure.i18n;

import cn.hutool.core.util.StrUtil;
import com.iscas.common.tools.constant.HeaderKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/6 13:31
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Configuration(proxyBeanMethods = false)
public class I18nAutoConfiguration {

    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

    /**
     * 获取请求头国际化信息
     */
    static class I18nLocaleResolver implements LocaleResolver {

        @Override
        @NonNull
        public Locale resolveLocale(@NonNull HttpServletRequest httpServletRequest) {
            String language = httpServletRequest.getHeader(HeaderKey.CONTENT_LANGUAGE);
            Locale locale = Locale.getDefault();
            if (StrUtil.isNotBlank(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(@NotNull HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

        }
    }
}
