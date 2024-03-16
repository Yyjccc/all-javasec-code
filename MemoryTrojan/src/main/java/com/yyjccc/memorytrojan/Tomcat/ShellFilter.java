package com.yyjccc.memorytrojan.Tomcat;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class ShellFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig)  {
		System.out.println("Filter 初始构造完成");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("执行了操作");
		chain.doFilter(request,response);
	}

	@Override
	public void destroy() {
		System.out.println("filter 销毁");
	}
}
