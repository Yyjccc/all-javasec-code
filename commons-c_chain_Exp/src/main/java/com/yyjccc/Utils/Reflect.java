package com.yyjccc.Utils;

import java.lang.reflect.Field;

public class Reflect {

	public static void setValue(Object source,String fieldName,Object value) throws NoSuchFieldException, IllegalAccessException {
		Field field=source.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(source,value);
	}

	public  static void setValue(Class clzz,Object source,String fieldName,Object value) throws NoSuchFieldException, IllegalAccessException {
		Field field = clzz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(source,value);
	}
}
