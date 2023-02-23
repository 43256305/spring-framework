package com.example.study.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author ：xjh
 * @date ：Created in 2023/2/21 10:58
 * @description：
 * @modified By：
 * @version:
 */
public class LogResultAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println(method.getName() + " function returned value:" + returnValue);
	}

}
