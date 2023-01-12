package com.example.study.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：xjh
 * @date ：Created in 2023/1/11 11:19
 * @description：
 * @modified By：
 * @version:
 */
//@ControllerAdvice
public class MyControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder){
		//如果此方法写在注释了@ControllerAdvice的类中，则此绑定全局有效
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

}
