package com.example.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Properties;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/4 15:17
 * @description：
 * @modified By：
 * @version:
 */
public class EnvironmentTest {

	@Test
	public void testEnvironment(){
		ApplicationContext applicationContext = new GenericApplicationContext();
		// 获取环境
		Environment environment = applicationContext.getEnvironment();
		System.out.println(environment.getProperty("JAVA_HOME"));
	}

	@Test
	public void testPropertySource(){
		StandardEnvironment environment = new StandardEnvironment();
		// 可以看到，只要我们创建了StandardEnvironment，则环境变量会自动注入
		System.out.println(environment.getProperty("JAVA_HOME"));

		// env中存在两个PropertySource，分别为：systemEnvironment和systemProperties
		// 注入自定义的PropertySource后，env将会包括三个PropertySource
		Properties properties = new Properties();
		properties.setProperty("age", "25");
		PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("my-propertysource", properties);
		MutablePropertySources propertySources = environment.getPropertySources();
		propertySources.addLast(propertiesPropertySource);
		System.out.println(environment.getProperty("age"));

		// 在实际使用中，我们可以通过@PropertySource注解引入PropertySource

	}

}
