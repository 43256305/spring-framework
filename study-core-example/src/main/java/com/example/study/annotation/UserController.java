package com.example.study.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/24 16:47
 * @description：
 * @modified By：
 * @version:
 */
@Controller
public class UserController {

	@Autowired
	@Qualifier(value = "userService")
//	@Resource
	private UserService userService;

}
