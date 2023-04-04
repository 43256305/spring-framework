package com.example.study;

import com.example.study.config.MyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.asm.ClassReader;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class BeanDefinitionTest {

	@Test
	public void testGenericBeanDefinition(){
		// 目前最常用的为GenericBeanDefinition以及他的子类
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClassName("com.example.study.entity.User");
		genericBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		genericBeanDefinition.setInitMethodName("init");
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("name", "user");
		propertyValues.addPropertyValue("age", 12);
		genericBeanDefinition.setPropertyValues(propertyValues);
	}

	@Test
	public void testRootBeanDefinition(){
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();

		// 所有的beanDefinition后面都会转换为rootBeanDefinition
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClassName("com.example.study.entity.User");
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("name", "小明");
		propertyValues.addPropertyValue("age", 25);
		rootBeanDefinition.setPropertyValues(propertyValues);
		simpleBeanDefinitionRegistry.registerBeanDefinition("user", rootBeanDefinition);

		// 指定父beanDefinition，后期可以将两个definition合并
		ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("user");
		childBeanDefinition.setBeanClassName("com.example.study.entity.User");
		propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("id", 1000);
		childBeanDefinition.setPropertyValues(propertyValues);
		simpleBeanDefinitionRegistry.registerBeanDefinition("worker", childBeanDefinition);
	}

	@Test
	public void testRegistryByXml(){
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
		// 读取xml文件，将读取到的bean注册到simpleBeanDefinitionRegistry中
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanDefinitionRegistry);
		xmlBeanDefinitionReader.loadBeanDefinitions("beanDefinition.xml");
		System.out.println(simpleBeanDefinitionRegistry.getBeanDefinition("user").getBeanClassName());
		System.out.println(simpleBeanDefinitionRegistry.getBeanDefinition("worker").getBeanClassName());
	}

	@Test
	public void testRegistryByAnnotated(){
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(simpleBeanDefinitionRegistry);
		annotatedBeanDefinitionReader.register(MyConfig.class);
		// 这里为false，想要解析@Configuration需要配合ConfigurationClassBeanDefinitionReader使用，而此类不让使用
		System.out.println(simpleBeanDefinitionRegistry.containsBeanDefinition("user"));
		// 这里为true
		System.out.println(simpleBeanDefinitionRegistry.containsBeanDefinition("myConfig"));
	}

	@Test
	public void testRegistryByScanner(){
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
		// ConfigurationClassBeanDefinitionReader进行@Configuration的加载时，就是使用了此类来扫描@ComponentScan指定的包下面的bean
		ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(simpleBeanDefinitionRegistry);
		classPathBeanDefinitionScanner.scan("com.example.study.config");
		// 这里同样为false
		System.out.println(simpleBeanDefinitionRegistry.containsBeanDefinition("user"));
		// 这里为true
		System.out.println(simpleBeanDefinitionRegistry.containsBeanDefinition("myConfig"));
	}

	@Test
	public void testAsm() throws IOException {
		// Spring中，通过将class文件以字节码文件(.class)的方式加载进来，使用asm来读取类的元数据，读取完成后，资源会被丢弃。整个过程没有用到jvm类加载。
		// asm测试
		ClassPathResource resource = new ClassPathResource("com/example/study/config/MyConfig.class");
		ClassReader classReader = new ClassReader(resource.getInputStream());
		// 打印一些元数据
		System.out.println(classReader.getClassName());
		// 常量池偏移量
		System.out.println(classReader.getItem(1));
		System.out.println(classReader.getAccess());
	}
}
