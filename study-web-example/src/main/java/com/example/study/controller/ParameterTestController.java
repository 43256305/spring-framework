package com.example.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xjh
 * @date ：Created in 2023/1/12 9:52
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/parameter")
public class ParameterTestController {

	@GetMapping("/requestParam")
	public String testRequestParam(@RequestParam(required = false) int value){
		System.out.println(value);
		return "Hello world!";
	}

	@GetMapping("/nestedObj")
	public String testNestedObj(User user){
		return user.toString();
	}

}
