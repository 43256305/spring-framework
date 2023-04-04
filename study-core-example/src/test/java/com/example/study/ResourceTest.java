package com.example.study;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/4 14:26
 * @description：
 * @modified By：
 * @version:
 */
public class ResourceTest {

	@Test
	public void test() throws IOException {
		// 通过此一个resolver，我们可以获取不同的Resource。当然也可以使用不同的Resource子类。
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource("https://www.baidu.com/");
		Resource classpathResource = resolver.getResource("classpath:beanDefinition.xml");
		Resource[] resources = resolver.getResources("classpath*:META-INF/spring.factories");
		Resource fileResource = resolver.getResource("file://F://dockerfile");
	}

}
