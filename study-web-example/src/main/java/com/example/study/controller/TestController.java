package com.example.study.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController implements ApplicationContextAware {

	private ApplicationContext applicationContext;

//	最终的访问路径为：tomcat配置的application context + web.xml中的url-pattern + 此controller的requestMapping
	@GetMapping
	public String test(){
		//可以看到，当两个DispatcherServlet时，这里容器的hashCode是不同的
		System.out.println(applicationContext.hashCode());
		return "Hello World!";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//这里注入的即是XmlWebApplicationContext
		this.applicationContext = applicationContext;
	}
}
