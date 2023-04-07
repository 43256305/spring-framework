package com.example.study;

import com.example.study.bean.provider.Student;
import com.example.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.InstantiationStrategy;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/7 10:31
 * @description：
 * @modified By：
 * @version:
 */
public class BeanFactoryTest {

	@Test
	public void testObjectProvider(){
		//ObjectProvider用于解决构造器的循环依赖问题，并且ObjectProvider还可以用于注入集合类型的bean。
		//例如，如果有多个实现了同一接口的bean，我们可以使用ObjectProvider来注入一个包含所有实现类实例的集合，而不是注入一个单独的实例。
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.example.study.bean.provider");
		Student student = (Student) annotationConfigApplicationContext.getBean("student");
		student.setId(1);
		com.example.study.bean.provider.User user = (com.example.study.bean.provider.User) annotationConfigApplicationContext.getBean("user");
		System.out.println(user);
	}

	@Test
	public void testObjectFactory(){
		ObjectFactory<com.example.study.entity.User> factory = () -> {
			// 可以对bean做一下复杂操作，如返回代理类。循环依赖中三级缓存保存的就是ObjectFactory
			Random random = new Random();
			return new User("tom", random.nextInt(100));
		};
		System.out.println(factory.getObject());
	}

	@Test
	public void testHierarchicalBeanFactory(){
		DefaultListableBeanFactory parent = new DefaultListableBeanFactory();
		parent.registerSingleton("user", new User("tom", 12));
		DefaultListableBeanFactory child = new DefaultListableBeanFactory();
		child.setParentBeanFactory(parent);
		// 直接访问父factory的bean
		System.out.println(child.getBean("user"));
	}

	@Test
	public void testInstantiationStrategy() throws ClassNotFoundException {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClassName("com.example.study.entity.User");
		rootBeanDefinition.resolveBeanClass(Thread.currentThread().getContextClassLoader());

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 实例化策略，cglib用于当使用look-up或replace方法时将会生成子类
		InstantiationStrategy strategy = new CglibSubclassingInstantiationStrategy();
		Object user = strategy.instantiate(rootBeanDefinition, "user", beanFactory);
		System.out.println(user);
	}


}
