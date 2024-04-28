package com.yyjccc.fastjson.usage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person  {
	private String name;
	private int age;




	public String getName() {
		System.out.println("get name");
		return name;
	}

	public void setName(String name) {
		System.out.println("set name");
		this.name = name;
	}

	public int getAge() {
		System.out.println("get name");
		return age;
	}

	public void setAge(int age) {
		System.out.println("set age");
		this.age = age;
	}




}
