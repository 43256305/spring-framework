package com.example.study.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/14 16:38
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/conversion")
public class ConversionController {

	@GetMapping("/single-parameter-integer")
	public Integer integer(@RequestParam("age") Integer age){
		// 这里会使用ConversionService进行转换
		return age;
	}

	@GetMapping("/single-parameter-string")
	public String string(@RequestParam("name") String name){
		// 这里会使用ConversionService进行转换
		return name;
	}

	@GetMapping("/initBinderTest")
	public String initBinderTest(Date date){
		// 这里会使用自定义的editor进行转换
		return date.toString();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		//通过此方法，我们能够将传入的yyyy-MM-dd格式的字符串转换成Date对象  注意，此binder只在此controller有效
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

}
