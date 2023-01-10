package com.example.study.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author ：xjh
 * @date ：Created in 2023/1/9 16:17
 * @description：测试BeanNameUrlHandlerMapping的使用
 * @modified By：
 * @version:
 */
@Component("/beanNameUrlTest")
public class BeanNameUrlController implements Controller {

	@Override
	public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
		System.out.println("BeanNameUrlController test");
		return null;
	}
}
