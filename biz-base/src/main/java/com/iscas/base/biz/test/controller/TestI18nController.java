package com.iscas.base.biz.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/i18n")
public class TestI18nController {

	@Autowired
	private MessageSource messageSource;

	/**
	 * 通过code获取国际化内容
	 * code为 messages.properties 中的 key
	 *
	 */
	@GetMapping()
	public String get(String code) {
		return messageSource.getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());
	}
}
