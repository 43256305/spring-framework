package com.example.study.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ：xjh
 * @date ：Created in 2023/2/21 10:57
 * @description：
 * @modified By：
 * @version:
 */
public class LogArgsAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("function running:" + method.getName() + ", args:" + Arrays.toString(args));
	}

}
