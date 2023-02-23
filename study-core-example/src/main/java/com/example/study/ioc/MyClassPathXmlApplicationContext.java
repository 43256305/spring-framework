package com.example.study.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：xjh
 * @date ：Created in 2023/2/8 10:17
 * @description：
 * @modified By：
 * @version:
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {


	@Override
	protected void initPropertySources() {
		//将user.name环境变量设置为必须
		getEnvironment().setRequiredProperties("user.name");
	}

	@Override
	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
		//自定义这两个属性
		setAllowBeanDefinitionOverriding(true);
		setAllowCircularReferences(true);
		super.customizeBeanFactory(beanFactory);
	}
}
