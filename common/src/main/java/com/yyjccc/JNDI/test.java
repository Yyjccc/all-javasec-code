package com.yyjccc.JNDI;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class test {
	public static void main(String[] args) {
		String uri = "rmi://127.0.0.1:8085/XnaaDLtW";
		InitialContext initialContext = null;
		try {
			initialContext = new InitialContext();

		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		try {
			initialContext.lookup(uri);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}

	}



}

