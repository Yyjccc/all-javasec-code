package com.yyjccc.fastjson.exp;

import com.sun.org.apache.bcel.internal.classfile.Utility;

import java.io.IOException;

public class BcelUtil {

	public static String bcelEncode(byte[] bytecode)  {

		try {
			return "$$BCEL$$" + Utility.encode(bytecode, true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
