package com.example.study.controller;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author ：xjh
 * @date ：Created in 2023/1/10 12:14
 * @description： 使用WebApplicationInitializer初始化web，使用此方法时，并不需要WEB-INF下面的xml相关配置文件
 * @modified By：
 * @version:
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {

		// Load Spring web application configuration
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);

		// 将容器放入DispatcherServlet
		// Create and register the DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app2", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/app2/*");
	}
}
