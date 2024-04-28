package com.yyjccc.fastjson.usage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class test {
	public static void main(String[] args) {
		String d="{\"@type\":\"com.yyjccc.fastjson.usage..Person\",\"name\":\"yyjccc\",\"age\":18}";
		String data="{{\"x\":{\"@type\":\"com.yyjccc.fastjson.usage.Person\",\"name\":\"yyjccc\",\"age\":18}}:\"x\"}";
		//JSONObject jsonO = JSON.parseObject(data);
		//Object parse = JSON.parse(data);
		Person person = new Person("yyjccc", 18);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("x",person);
		System.out.println(jsonObject);
		//JSON.toJSON(person);
	}
}
