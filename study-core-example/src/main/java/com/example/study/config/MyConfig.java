package com.example.study.config;

import com.example.study.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

	@Bean
	public User user(){
		return new User("小明", 22);
	}

}
