package com.example.study.bean.provider;

import org.springframework.stereotype.Component;

/**
 * @author ：xjh
 * @date ：Created in 2023/4/7 10:58
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class Student {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				'}';
	}
}
