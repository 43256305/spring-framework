package com.example.study.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop-config.xml");
		OrderService orderService = (OrderService) context.getBean("orderServiceImpl");
		orderService.createOrder("username", "product");
		context.close();
	}

}
