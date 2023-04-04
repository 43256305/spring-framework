package com.example.study;

import com.example.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/4 10:04
 * @description：
 *
 * 内省api：Introspector,BeanInfo,PropertyDescriptor
 * 内省是通过反射来操作bean
 * 实际使用中不太会用到内省相关类，可以直接使用BeanUtils
 *
 * BeanWrapper：同样可以操作bean
 * ResolvableType：可以获取父类或者接口、泛型、以及解析为最终的Class的能力
 * @modified By：
 * @version:
 */
public class BeanTest {

	@Test
	public void testIntrospect1() throws IntrospectionException {
		// 获取类的一些数据
		BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
		System.out.println(beanInfo.getBeanDescriptor());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println(propertyDescriptor.getName() + "  " + propertyDescriptor.getPropertyType());
		}
	}

	@Test
	public void testIntrospect2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		// 操作具体的bean，给bean的属性赋值
		User user = new User();
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", User.class);
		Method writeMethod = propertyDescriptor.getWriteMethod();
		writeMethod.invoke(user, "小明");
		Method readMethod = propertyDescriptor.getReadMethod();
		System.out.println(readMethod.invoke(user));
	}

	@Test
	public void testBeanWrapper() throws ClassNotFoundException {
		// spring中，beanDefinition一般是直接扫包读取字节码获取的元数据
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClassName("com.example.study.entity.User");
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("name", "user");
		propertyValues.addPropertyValue("age", 12);
		genericBeanDefinition.setPropertyValues(propertyValues);

		// 这些步骤是重复性的，可以循环操作
		Class<?> clasz = Class.forName(genericBeanDefinition.getBeanClassName());

		// 实例化并做属性填充
		BeanWrapper beanWrapper = new BeanWrapperImpl(clasz);
		beanWrapper.setPropertyValues(genericBeanDefinition.getPropertyValues());
		Object wrappedInstance = beanWrapper.getWrappedInstance();
		System.out.println(wrappedInstance);
		beanWrapper.setPropertyValue("name", "小明");
		System.out.println(wrappedInstance);
	}

	@Test
	public void testBatchCreate(){
		// 测试从xml中读取bd，通过BeanWrapper批量创建bean
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanDefinitionRegistry);
		xmlBeanDefinitionReader.loadBeanDefinitions("beanDefinition.xml");

		Arrays.stream(simpleBeanDefinitionRegistry.getBeanDefinitionNames()).forEach(beanDefinitionName -> {
			BeanDefinition beanDefinition = simpleBeanDefinitionRegistry.getBeanDefinition(beanDefinitionName);
			Class<?> clasz = null;
			try {
				clasz = Class.forName(beanDefinition.getBeanClassName());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			// 由于从xml中读取的值都是字符串类型，而我们的bean的属性包括各种各样的类型，所以这里为bean设置属性时会报错，因为没有类型转换器
			BeanWrapper beanWrapper = new BeanWrapperImpl(clasz);
			beanWrapper.setPropertyValues(beanDefinition.getPropertyValues());
			Object wrappedInstance = beanWrapper.getWrappedInstance();
			System.out.println(wrappedInstance);
		});

	}

	public HashMap<String, Integer> map = new HashMap<>(16);

	@Test
	public void testResolvableType() throws NoSuchFieldException {
		ResolvableType resolvableType = ResolvableType.forField(getClass().getField("map"));
		System.out.println(resolvableType.getSuperType());
		System.out.println(Arrays.toString(resolvableType.getInterfaces()));
		System.out.println(Arrays.toString(resolvableType.getGenerics()));
	}

	@Test
	public void testConversionService(){
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanDefinitionRegistry);
		xmlBeanDefinitionReader.loadBeanDefinitions("beanDefinition.xml");

		Arrays.stream(simpleBeanDefinitionRegistry.getBeanDefinitionNames()).forEach(beanDefinitionName -> {
			BeanDefinition beanDefinition = simpleBeanDefinitionRegistry.getBeanDefinition(beanDefinitionName);
			Class<?> clasz = null;
			try {
				clasz = Class.forName(beanDefinition.getBeanClassName());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			BeanWrapper beanWrapper = new BeanWrapperImpl(clasz);
			// 设置ConversionService，DefaultConversionService已经默认注册了很多常用Converter
			DefaultConversionService defaultConversionService = new DefaultConversionService();
			// 这里注册了我们定义的转换器
			defaultConversionService.addConverter(new MyConverter());
			beanWrapper.setConversionService(defaultConversionService);
			beanWrapper.setPropertyValues(beanDefinition.getPropertyValues());
			Object wrappedInstance = beanWrapper.getWrappedInstance();
			System.out.println(wrappedInstance);
		});
	}

	static class MyConverter implements Converter<TypedStringValue, Integer>{
		@Override
		public Integer convert(TypedStringValue source) {
			assert source.getValue() != null;
			return Integer.parseInt(source.getValue());
		}
	}



}
