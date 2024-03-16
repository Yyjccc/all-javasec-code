package com.yyjccc.jackson.usage;

import lombok.Data;

@Data
public class Sex {
	private int id;
	private String name;

	static {
		System.out.println("sex static code");
	}
}
