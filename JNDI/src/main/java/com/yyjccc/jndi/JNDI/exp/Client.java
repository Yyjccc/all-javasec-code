package com.yyjccc.jndi.JNDI.exp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	public static void main(String[] args) throws NamingException {
		Context context=new InitialContext();
		String url="rmi://127.0.0.1:8085/MqiUmLyr";
		context.lookup(url);

	}
}
