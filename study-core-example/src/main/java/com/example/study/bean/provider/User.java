package com.example.study.bean.provider;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/7 10:55
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class User {

	private Student student;

	private String name;

	private Integer age;

	public User(ObjectProvider<Student> objectProvider){
		// 注意这里
		this.student = objectProvider.getIfAvailable();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"student=" + student +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
