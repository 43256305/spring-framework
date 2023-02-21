package com.example.study;

import com.example.study.entity.SimpleBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-${user.name}.xml");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		SimpleBean bean = context.getBean(SimpleBean.class);
		bean.print();
		//没有指定id时，需要指定全类名才能获取bean，而不能使用simpleBean
		SimpleBean beanByName = (SimpleBean) context.getBean("com.example.study.entity.SimpleBean");
		beanByName.print();
		context.close();
	}

}
