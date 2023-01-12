package com.example.study.controller;

/**
 * @author ：xjh
 * @date ：Created in 2023/1/12 10:25
 * @description：
 * @modified By：
 * @version:
 */
public class User {

	private String name;

	private String id;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
