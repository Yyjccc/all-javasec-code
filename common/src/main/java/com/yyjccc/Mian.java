package com.yyjccc;

import java.io.IOException;

public class Mian {
	public static void main(String[] args) {
		String json = null;
		try {
			json = Http.doPost("http://47.120.45.216:8088/api/user/login", "json",new User("admin","admin"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(json);

	}
}
