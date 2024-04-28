package com.yyjccc.jndi.RMI.exp;

import com.yyjccc.Utils.Tool;

public class decode {
	public static void main(String[] args) {
		Tool.mode="base64";
		try {
			Object o = Tool.unserialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
