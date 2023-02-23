package com.example.study.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author ：xjh
 * @date ：Created in 2023/2/21 13:37
 * @description：
 * @modified By：
 * @version:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//此方法会应用在所有非BeanPostProcessorbean自定义的初始化方法
		System.out.println("current beanName is " + beanName + ",MyBeanPostProcessor.postProcessAfterInitialization() is running ! ");
		return bean;
	}
}
