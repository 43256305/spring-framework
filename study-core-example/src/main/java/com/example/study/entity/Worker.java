package com.example.study.entity;

public class Worker extends User{

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Worker{" +
				"id=" + id +
				'}';
	}
}
