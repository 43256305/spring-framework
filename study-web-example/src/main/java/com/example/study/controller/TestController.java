package com.example.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

//	最终的访问路径为：tomcat配置的application context + web.xml中的url-pattern + 此controller的requestMapping
	@GetMapping
	public String test(){
		return "Hello World!";
	}

}
