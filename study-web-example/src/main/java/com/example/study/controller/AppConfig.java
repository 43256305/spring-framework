package com.example.study.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @author ：xjh
 * @date ：Created in 2023/1/10 15:24
 * @description：
 * @modified By：
 * @version:
 */
@ComponentScan("com.example.study.controller")
public class AppConfig {

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		//通过自定义RequestMappingHandlerAdapter，设置程序支持的Method，注意，只有app2程序支持
		//由于RequestMappingHandlerAdapter的初始化流程都是在bean的声明周期中完成的，所有我们自定义此bean时并不需要做多余的操作，他就会自动帮我们调用afterPropertiesSet方法
		requestMappingHandlerAdapter.setSupportedMethods(HttpMethod.GET.name());
		return requestMappingHandlerAdapter;
	}

}
