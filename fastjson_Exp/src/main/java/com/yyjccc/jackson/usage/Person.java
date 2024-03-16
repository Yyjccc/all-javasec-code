package com.yyjccc.jackson.usage;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
	private String name;
	private int age;

	//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS )
	private Sex sex;


	static {
		System.out.println("静态代码块");
	}

	public Person(){
		System.out.println("构造方法");
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		System.out.println("get方法");
		return name;
	}

	public void setName(String name) {
		System.out.println("set方法");
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
