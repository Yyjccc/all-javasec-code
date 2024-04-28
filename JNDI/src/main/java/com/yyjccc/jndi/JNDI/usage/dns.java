package com.yyjccc.jndi.JNDI.usage;

import javax.naming.InitialContext;
import javax.naming.NamingException;


public class dns {
	public static void main(String[] args) throws NamingException {
	String url = "dns://v8qt8d.dnslog.cn";
	InitialContext initialContext = new InitialContext();
	initialContext.lookup(url);

	}
}
