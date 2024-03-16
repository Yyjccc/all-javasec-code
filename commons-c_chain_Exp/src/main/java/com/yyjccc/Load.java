package com.yyjccc;

import static java.lang.Class.forName;

public class Load {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		Class<?> aClass = forName("com.yyjccc.EvilClass.ExpClassLoad", false, loader);
		try {
			aClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
