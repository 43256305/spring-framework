package com.example.study.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/11 14:38
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class AnnotationService {

    @PostConstruct
    public void init(){
        System.out.println("init method");
    }

	@PreDestroy
	public void destroy(){
		System.out.println("destroy method");
	}

}
