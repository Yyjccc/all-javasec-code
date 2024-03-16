package com.yyjccc.Xtream.Usage;

import lombok.Data;

@Data
public class Person implements Eat{
	private String name;
	private int age;


	@Override
	public void commonEat() {

	}
}
