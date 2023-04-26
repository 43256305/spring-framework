package com.example.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/11 14:40
 * @description：
 * @modified By：
 * @version:
 */
public class AnnotationTest {

	@Test
	public void testPostConstruct(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.study.annotation");
		context.close();
	}

}
